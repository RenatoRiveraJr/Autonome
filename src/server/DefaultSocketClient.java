package server;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import model.Automobile;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by RJ Rivera on 3/10/2017.
 */
public class
DefaultSocketClient
        extends Thread implements SocketClientInterface,
        SocketClientConstants {

    //Instance Variables
    private Socket clientSocket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private PrintWriter printer = null;
    private BuildCarModelOptions carBuilder;
    private ProxyAutomobile proxy;
    private String request;

    //Getters
    public String getRequest() {
        return request;
    }
    //Setters
    public void setProxy(ProxyAutomobile proxy){this.proxy = proxy;}

    //Constructor
    public DefaultSocketClient(Socket clientSocket){
        this.clientSocket = clientSocket;
        carBuilder = new BuildCarModelOptions();
    }

    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }

    public boolean openConnection() {
        try {
            reader = new ObjectInputStream(clientSocket.getInputStream());
            writer =  new ObjectOutputStream(clientSocket.getOutputStream());
            printer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch(Exception e) {
            if (DEBUG)
                System.err.println ("Unable to obtain I/O Stream");
            return true;
        }
        return true;
    }

    public void handleSession() {

        try{
            while((request = (String) reader.readObject()) != null){
                if(request.equals("UploadPropFile")){
                    carBuilder.setProps((Properties)reader.readObject());
                    proxy.setAuto(carBuilder.buildCarFromProps());
                    proxy.putAuto();
                    sendOutput("Your File Has been Uploaded");
                    System.out.println("----------------------------------------------------");
                    System.out.println("----------------------------------------------------");
                    System.out.println("Newly Updated Hash Map:");
                    proxy.iterateHashMap();
                    System.out.println("----------------------------------------------------");
                }
                if(request.equals("Configure Car")) {
                    //Send A List of Cars
                    sendOutput(proxy.getMakeModelArr());
                    //Read their choice, cast to string, parseString to integer, get auto via key
                    Automobile a1 = proxy.getAutoMap().get(Integer.parseInt((String)reader.readObject()));
                    //Create Prop File
                    carBuilder.writePropFile(a1);
                    //Send Auto
                    sendOutput(carBuilder.getProps());
                }
                if(request.equals("Disconnect"));
                    break;
            }

        }catch (IOException e){

        }catch (ClassNotFoundException e){

        }
    }

    public void closeSession() {
        try{
            clientSocket.close();
            reader.close();
            writer.close();
            printer.close();
            System.out.println("Finished Handling Session. Now Closing Session");
        }catch(IOException e){
            System.err.println ("Error closing socket");
        }
    }

    public void sendOutput(Object obj){
        try {
            writer.writeObject(obj);
        }catch(IOException e){

        }
    }

}
