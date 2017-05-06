package driverpkg;

import adapter.BuildAuto;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Created by RJ Rivera on 1/14/17.
 */
public class Driver {

    public static void main(String args[]) {

        //HashMap
        Map<model.Automobile, Integer> autoMap = new LinkedHashMap<model.Automobile, Integer>();

        //Build 1st Automobile
        BuildAuto autoBuilder = new BuildAuto();
        autoBuilder.buildAuto("Car1.txt");
        autoBuilder.printAuto();
        autoMap.put(autoBuilder.getAuto(), autoMap.size()+1);

        //Build 2nd Automobile
        autoBuilder.buildAuto("Car2.txt");
        System.out.println("----------------------------------------------------");
        System.out.println("Car # 2");
        autoBuilder.printAuto();
        autoMap.put(autoBuilder.getAuto(), autoMap.size() + 1);

        //User Chosen Options
        autoBuilder.readChosenOptions("optionChoices.txt");
        autoBuilder.printChosenOptions();

        //Iterate through LinkedHashMap
        for (model.Automobile car: autoMap.keySet()) {
            System.out.println(car.getMake() + " " + car.getModel() + " @ index: " + autoMap.get(car));
        }
    }
}
/*
autoBuilder.updateOptionSetName("FordZTW", "Transmission", "Gear Box");
autoBuilder.updateOptionPrice("FordZTW", "Brakes/Traction Control", "Standard", 5);
*/