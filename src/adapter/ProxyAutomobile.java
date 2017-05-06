package adapter;
import exception.AutoException;
import model.*;
import utilpkg.*;

import java.io.IOException;

/**
 * Created by sRJ Rivera on 1/28/2017.
 */
public abstract class ProxyAutomobile {
    private Automobile a1;
    private FileIO io;
    private AutoException ae;

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

    //Setters
    public void setAuto(Automobile auto){
        this.a1 = auto;
    }

    //Methods
    public void buildAuto(String fileName){
        setAuto(getFile().readFile(fileName));
    }
    public void updateOptionSetName(String modelName, String optSetName, String newName){
        getAuto().updateOptionSetName(optSetName, newName);
    }
    public void updateOptionPrice(String modelName, String optSetName, String opt, float newPrice){
        getAuto().updateOptionCost(optSetName, opt, newPrice);
    }
    public void fix(){
        getAutoException().fix();
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
}