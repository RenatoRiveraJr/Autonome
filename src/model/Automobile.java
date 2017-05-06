package model;

import java.util.ArrayList;

/**
 * Created by sdarthrahj on 1/14/17.
 */

public class Automobile implements java.io.Serializable {

    //Instance Variables
    private String name;
    private Float basePrice = 0f;
    private float finalPrice = 0;
    private OptionSet[] opset;
    private boolean hasBeenSerialized;
    //Instance Variables Added for Lab 3
    private String make;
    private String model;
    private ArrayList optNameList;
    private ArrayList<Option> choice;


    /*+++++++++++++++++++++Constructors+++++++++++++++++++++*/
    public Automobile(String newName, String modelName, Float newBasePrice, int numOfOptionSets)
    {
        name = newName;
        make = newName;
        model = modelName;
        basePrice = newBasePrice;
        opset = new OptionSet[numOfOptionSets];
        optNameList = new ArrayList(numOfOptionSets);
        choice = new ArrayList(numOfOptionSets);
    }
    public Automobile(String newName)
    {
        name = newName;
        opset = new OptionSet[5];
    }
    public Automobile() { }

    /*+++++++++++++++++++++Getters+++++++++++++++++++++*/
    //Getters for Automobile
    public String getName() {
        return name;
    }
    public Float getBasePrice() {
        return basePrice;
    }
    public float getFinalPrice() {
        return finalPrice;
    }
    public OptionSet[] getOpset() {
        return opset;
    }
    public OptionSet getOpset(int index){
        return opset[index];
    }
    public boolean hasBeenSerialized(){
        return hasBeenSerialized;
    }
    public ArrayList getOptNameList1() {
        return choice;
    }
    public ArrayList getOptNameList() {
        return optNameList;
    }
    public String getModel() {
        return model;
    }
    public String getMake() {
        return make;
    }
    //Getters for OptionSet
    public String getOptionSetName(int optSetNum){
        return opset[optSetNum].getName();
    }
    public int getNumOptions(int optSetNum){
        return opset[optSetNum].getNumOptions();
    }
    //Getters for Option
    public String getOptionName(int optNum, int optSetNum) {
        return opset[optSetNum].getOptionName(optNum);
    }

    public Float getOptionCost(int optNum, int optSetNum) {
        return opset[optSetNum].getOptionCost(optNum);
    }

    /*+++++++++++++++++++++Setters+++++++++++++++++++++*/
    //Setters for Automobile
    public void setOpset(OptionSet[] opset) {
        this.opset = opset;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }
    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }
    public void setHasBeenSerialized(boolean x) {
        this.hasBeenSerialized = x;
    }
    /*public void setChosenOptList(ArrayList<Option> chosenOptList) {
        this.chosenOptList = chosenOptList;
    }*/
    public void setOptNameList(ArrayList optNameList) {
        this.optNameList = optNameList;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }

    //Setters for OptionSet
    public void setOptionSetName(String name, int optSetNum){
        opset[optSetNum].setName(name);
    }

    //Setters for Option
    public void setOptionName(String name, int optNum, int optSetNum) {
        opset[optSetNum].setOptionName(name, optNum);
    }
    public void setOptionCost(Float cost, int optNum, int optSetNum) {
        opset[optSetNum].setOptionCost(cost, optNum);
    }

    /*+++++++++++++++++++++Finders++++++++++++++++++++*/
    /*       I want these to ADD functionality        */
    /*       NOT reiterate Getter functionality       */

    //Finders for OptionSet
    public int findOptionSetNum(String s){
        for(int i = 0;i < opset.length; i++) {
            String name = opset[i].getName();
            if (name.equals(s))
                return i;//Index of Matching Opset Name
        }
        return -1;//If not found
    }
    //Rudimentary Finder for Option, Right now it's quadratic
    public int findOptionNum(String s, int optSetNum){
        int numOfOptions = opset[optSetNum].getOpt().length;
        for(int i = 0; i < numOfOptions; i++){
            String name = opset[optSetNum].getOptionName(i);
            if(name.equals(s))
                return i;
        }
        return -1;//If not found
    }

    /*++++++++++++++++++++++Updaters++++++++++++++++++++++*/
        /*       I want these to ADD functionality        */
        /*       NOT reiterate Setter functionality       */
    //Updaters for OptionSet
   public synchronized void updateOptionSetName(String name, String newName, boolean editStatus) {
       if(editStatus){
           opset[findOptionSetNum(name)].setName(newName);
           notifyAll();
       }
       else
           try{
               wait();
           }catch(InterruptedException iex) {
               // display any interruptions but continue
               System.err.println(iex);
           }
   }

    //Updaters for Option
    public synchronized void updateOptionName(String optSetName, String optName, String newName) {
        int optSetNum = findOptionSetNum(optSetName);
        int optNum = findOptionNum(optName, optSetNum);
        opset[optSetNum].setOptionName(newName, optNum);
    }
    public synchronized void updateOptionCost(String optSetName, String optName, Float newCost) {
        int optSetNum = findOptionSetNum(optSetName);
        if (optSetNum == -1)
            System.out.println("You have provided an Option Set Name that does not exist");

        int optNum = findOptionNum(optName, optSetNum);
        if (optNum == -1)
            System.out.println("You have provided an Option Name that does not exist");
        else
            opset[optSetNum].setOptionCost(newCost, optNum);
    }

    /*+++++++++++++++++++++Deleters+++++++++++++++++++++*/
    //Deleters for Automobile
    public void deleteOpsetArray() {
        this.opset = null;
    }

    //Deleters for OptionSet
    public void deleteOptionSet(String opSetName){
        int optSetNum = findOptionSetNum(opSetName);
        opset[optSetNum] = null;
    }

    //Deleters for Option
    public void deleteOption(String opSetName, String optToDelete){
        int optSetNum = findOptionSetNum(opSetName);
        int optNum = findOptionNum(optToDelete, optSetNum);
        opset[optSetNum].deleteOption(optNum);
    }

    /*+++++++++++++++++++++Instance Methods+++++++++++++++++++++*/
    //Instantiate number of options
    public void instantiateOptions(int optNum, int numOpt){
        opset[optNum] = new OptionSet(numOpt);
    }

    /*+++++++++++++++++++++Print Methods+++++++++++++++++++++*/
    //Print Entire Database
    public void print(){
        System.out.println("Make of Vehicle: " + this.make);
        System.out.println("Model of Vehicle: " + this.model);
        System.out.println("Base Price of Vehicle: $" + this.basePrice);
        System.out.println("Your " + this.name + " has choices from these " + opset.length + " sets of options:");
        System.out.println("---------------------------------------------------");
        for(int i = 0; i < opset.length;i++){
            System.out.println("Option Set " + (i+1) + ": " + opset[i].getName());
            System.out.println();
            System.out.format("%-32s %7s", "Choose From: ", "Change in Price: ");
            System.out.println();
            opset[i].printOptions();
            System.out.println("----------------------------------------------------");
            System.out.println("----------------------------------------------------");
        }
    }

    //Print Auto Object Properties
    public void printProperties(){
        System.out.println("BasePrice: " + this.basePrice);
        System.out.println("FinalPrice: " + this.finalPrice);
        System.out.println("Name: " + this.name);
        System.out.println("Has this been serialized? " + this.hasBeenSerialized);
        for(int i = 0; i < opset.length; i++)
        {
            System.out.println("Option #" + (i+1) + ": " + opset[i].getName());
        }
    }

    //Print OptionSet Properties
    public void printOptions(){
        for(int i = 0; i < opset.length; i++)
            opset[i].printProperties();
    }

    //Print Users Choices (Added for Lab 3)
    public void printChosenOptions(){
        int size = getOptNameList1().size();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Our Customer Has Chosen These Options:");
        System.out.println("----------------------------------------------------");

        for(int i = 0; i < size; i++){
            System.out.println("Option Category: " + getOptNameList().get(i));
            System.out.println();
            Option opt = (Option) getOptNameList1().get(i);
            System.out.format("%-32s %6s", "Chosen Option: ", "Cost: ");
            System.out.println();
            System.out.format("%-32s $%7.2f", opt.getName(), opt.getCost());
            System.out.println();
            System.out.println("----------------------------------------------------");
        }
    }

    public void printOptionSetNames() {
        for(int i = 0; i < opset.length; i++)
            System.out.println(opset[i].getName());
    }

}