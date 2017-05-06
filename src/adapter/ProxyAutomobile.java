package adapter;
import exception.AutoException;
import model.*;
import server.BuildCarModelOptions;
import server.DefaultSocketClient;
import server.Server;
import utilpkg.*;

import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by RJ Rivera on 1/28/2017.
 */
public abstract class ProxyAutomobile {
    private Automobile a1;
    private FileIO io;
    private AutoException ae;
    private Server myServ;
    private static Map<Integer, Automobile > autoMap = new LinkedHashMap<Integer, Automobile>();
    private static boolean available = true;
    private String[] makeModelArr;

    public ProxyAutomobile(){
        this.a1 = new Automobile();
        this.io = new FileIO();
        this.ae = new AutoException();
    }

    //Getters
    public Automobile getAuto(){
        return this.a1;
    }
    public FileIO getFile(){
        return this.io;
    }
    public AutoException getAutoException() {
        return this.ae;
    }
    public Map<Integer, Automobile> getAutoMap() {
        return this.autoMap;
    }
    public boolean getEditStatus() {
        return this.available;
    }
    public String[] getMakeModelArr() {
        createMakeModelArr();
        return this.makeModelArr;
    }
    //Setters
    public void setAuto(Automobile auto){
        this.a1 = auto;
    }
    public void setAutoMap(Map<Integer, Automobile> autoMap){
        this.autoMap = autoMap;
    }
    public void setEditStatus(boolean status){
        this.available = status;
    }
    public void setMakeModelArr(String[] makeModelArr) {
        this.makeModelArr = makeModelArr;
    }

    //Methods
    public void startServer(int iPort){
        myServ = new Server(2000);
        myServ.startServer();
        myServ.waitForConnections(this);
    }
    public void buildAuto(String fileName){
        setAuto(getFile().readFile(fileName));
    }
    public void updateOptionSetName(int keyToBeEdited,String optSetName, String newName){
        getAutoMap().get(keyToBeEdited).updateOptionSetName(optSetName, newName, getEditStatus());
    }
    public void updateOptionName(int keyToBeEdited, String optSetName, String optName, String newName){
        getAutoMap().get(keyToBeEdited).updateOptionName(optSetName, optName, newName);
    }
    public void updateOptionPrice(int keyToBeEdited, String optSetName, String optName, Float newPrice){
        getAutoMap().get(keyToBeEdited).updateOptionCost(optSetName, optName, newPrice);
    }
    public void fix(){
        getAutoException().fix();
    }
    public void serialize(Object obj, String fileName){
        getFile().serialize(obj, fileName);
    }
    public void printAuto(){
        getAuto().print();
    }
    public void readChosenOptions(String fileName){
        getFile().readChosenOptions(getAuto(), fileName);
    }
    public void printChosenOptions() {
        getAuto().printChosenOptions();
    }
    public void putAuto() {
            getAutoMap().put(getAutoMap().size()+1, getAuto());
    }
    public synchronized void putAuto(Automobile a1) {
        while (available == false)
            try {
                // wait for Consumer to get value
                System.out.println("Put Waiting ");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Put done waiting");
            }
        available = false;
        setAuto(a1);
        putAuto();
        available = true;
        notifyAll();
    }
    public void iterateHashMap(){
        for(Map.Entry<Integer, Automobile> entry : getAutoMap().entrySet()){
            Integer key = entry.getKey();
            Automobile car = entry.getValue();
            System.out.println(car.getMake() + " " + car.getModel() + " @ " + key);
        }
    }
    public void createMakeModelArr(){
        int arraySize = getAutoMap().size();
        String[] tempMakeModelArr = new String[arraySize];
        for(Map.Entry<Integer, Automobile> entry : getAutoMap().entrySet()){
            Integer key = entry.getKey();
            Automobile car = entry.getValue();
            tempMakeModelArr[key-1] = car.getMake() + " " + car.getModel();
        }
        setMakeModelArr(tempMakeModelArr);
    }

}