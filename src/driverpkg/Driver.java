package driverpkg;

import adapter.BuildAuto;

/**
 * Created by RJ Rivera on 1/14/17.
 */
public class Driver {

    public static void main(String args[]) {

        BuildAuto autoBuilder = new BuildAuto();
        autoBuilder.buildAuto("BadFile.txt"); //Replace with GoodFile.txt to see working file

        autoBuilder.printAuto();
        autoBuilder.updateOptionSetName("FordZTW", "Transmission", "Gear Box");

        autoBuilder.updateOptionPrice("FordZTW", "Brakes/Traction Control",
                                                "Standard", 5);
        autoBuilder.printAuto();
    }
}