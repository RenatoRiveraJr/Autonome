package server;

import adapter.ProxyAutomobile;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Created by RJ Rivera on 3/10/2017.
 */
public class Server {
    //Instance Variables
    private ServerSocket serverSocket;
    private DefaultSocketClient clientSocket = null;
    private BuildCarModelOptions carBuilder;
    private int iPort;

    //Constructors
    public Server(int iPort) {
        this.iPort = iPort;
    }
    //Getters
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public int getiPort() {
        return iPort;
    }
    public BuildCarModelOptions getCarBuilder() {
        return carBuilder;
    }
    public DefaultSocketClient getClientSocket() {
        return clientSocket;
    }
    //Setters
    public void setCarBuilder(BuildCarModelOptions carBuilder) {
        this.carBuilder = carBuilder;
    }
    public void setiPort(int iPort) {
        this.iPort = iPort;
    }
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void setClientSocket(DefaultSocketClient clientSocket) {
        this.clientSocket = clientSocket;
    }
    //Methods

    public void startServer(){
        try{
            setServerSocket( new ServerSocket(getiPort()));
        }catch(IOException e){
            System.err.println("Could not listen on port" + getiPort());
            System.exit(1);
        }
    }

    public void waitForConnections(ProxyAutomobile proxy){

        while(true)
            try {
                System.out.println("Waiting for Connections...");
                setClientSocket(new DefaultSocketClient(getServerSocket().accept()));
                getClientSocket().setProxy(proxy);
                System.out.println("Connection Accepted");
                getClientSocket().start();
                try {
                    getClientSocket().join();
                }catch(InterruptedException e){}
                if(getClientSocket().getRequest().equals("Discconect")){
                    break;
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
    }
}
