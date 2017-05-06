package driverpkg;

import adapter.BuildAuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by RJ Rivera on 1/14/17.
 */
public class Driver {
    public static void main(String args[]) throws IOException {
        BuildAuto autoBuilder = new BuildAuto();
        autoBuilder.buildAuto("Car1.txt");
        autoBuilder.putAuto();
        autoBuilder.buildAuto("Car2.txt");
        autoBuilder.putAuto();
        System.out.println("----------------------------------------------------");
        System.out.println("Starting State of HashMap:");
        System.out.println("----------------------------------------------------");
        autoBuilder.iterateHashMap();
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        autoBuilder.startServer(2000);
    }
}
/*
 while(!autoBuilder.beingEdited()) {
            autoBuilder.getAutoMap().get(1).printOption();
            try{
                Thread.currentThread().sleep(15000);
            }catch(InterruptedException ie){
                System.out.println("Exception");
            }
        }
    Old Driver Code From Previous Labs
        //User Chosen Options (Lab 3)
        autoBuilder.readChosenOptions("optionChoices.txt");
        autoBuilder.printChosenOptions();

        //Iterate through LinkedHashMap (Lab 3)
        autoBuilder.iterateHashMap();

        //Update Demo(Lab 2)
        autoBuilder.updateOptionSetName("FordZTW", "Transmission", "Gear Box");
        autoBuilder.updateOptionPrice("FordZTW", "Brakes/Traction Control", "Standard", 5);

        //Lab 1 serialization
           model.Automobile autoInstance;
        utilpkg.FileIO io = new utilpkg.FileIO();

        //Create new Automobile from file
        model.Automobile automobile1 = io.readFile("Hello1.txt");

        //Print newly Built Automobile Object
        automobile1.print();

        //Serialize the automobile
        io.serialize(automobile1, "Automobile.ser");

        //Deserialize the Automobile object and print
        autoInstance = (model.Automobile) io.deserialize("Automobile.ser");
        autoInstance.print();
        */