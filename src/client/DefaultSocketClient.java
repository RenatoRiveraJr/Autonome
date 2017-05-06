package client;
import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import model.Automobile;

import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by RJ Rivera on 3/10/2017.
 */

public class
    DefaultSocketClient
    extends Thread implements SocketClientInterface,
                              SocketClientConstants {

    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private ProxyAutomobile proxy;
    private Socket sock;
    private String strHost;
    private int iPort;
    private int operation;

    //constructors
    public DefaultSocketClient() {
    }

    //Getter
    public ObjectInput getReader() {
        return this.reader;
    }

    //Setters
    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }


    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }//run

    public boolean openConnection() {

        try {
            sock = new Socket(strHost, iPort);
        } catch (IOException socketError) {
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        try {
            writer = new ObjectOutputStream(sock.getOutputStream());
            reader = new ObjectInputStream(sock.getInputStream());
        } catch (Exception e) {
            if (DEBUG) System.err.println
                    ("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }

    public void handleSession() {

    }

    public void sendOutput(Object props) {
        try {
            if (DEBUG) System.out.println("Sending Output...");
            writer.writeObject(props);
            if (DEBUG) System.out.println("Sent");
        } catch (IOException e) {
            if (DEBUG) System.out.println
                    ("Error writing to " + strHost);
        }
    }

    public void handleInput(String strInput) {
        System.out.println(strInput);
    }

    public void closeSession() {
        try {
            writer.close();
            reader.close();
            sock.close();
        } catch (IOException e) {
            if (DEBUG) System.err.println
                    ("Error closing socket to " + strHost);
        }
    }
}



