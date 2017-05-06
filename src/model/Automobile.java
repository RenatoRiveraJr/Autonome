package model;

/**
 * Created by darthrahj on 1/14/17.
 */

public class Automobile implements java.io.Serializable {

    //Instance Variables
    private String name;
    private float basePrice = 0;
    private float finalPrice = 0;
    private OptionSet[] opset;
    private boolean hasBeenSerialized;

    /*+++++++++++++++++++++Constructors+++++++++++++++++++++*/
    public Automobile(String newName, float newBasePrice, int numOfOptionSets)
    {
        name = newName;
        basePrice = newBasePrice;
        opset = new OptionSet[numOfOptionSets];
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
    public float getBasePrice() {
        return basePrice;
    }
    public float getFinalPrice() {
        return finalPrice;
    }
    public OptionSet[] getOpset() {
        return opset;
    }
    public boolean hasBeenSerialized(){
        return hasBeenSerialized;
    }

    //Getters for OptionSet
    public String getOptionSetName(int optSetNum){
        return opset[optSetNum].getName();
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
    public int findOptionNum(String s){
        for (int i = 0; i < opset.length; i++){
            if(opset[i].findOptionNum(s) != -1)
                return opset[i].findOptionNum(s);
        }
        return -1;//If not found
    }

    /*++++++++++++++++++++++Updaters++++++++++++++++++++++*/
        /*       I want these to ADD functionality        */
        /*       NOT reiterate Setter functionality       */
    //Updaters for OptionSet
    public void updateOptionSetName(String name, String newName){
        opset[findOptionSetNum(name)].setName(newName);
    }

    //Updaters for Option
    public void updateOptionName(String optSetName, String optName, String newName) {
        int optNum = findOptionNum(name);
        int optSetNum = findOptionSetNum(optSetName);
        opset[optSetNum].setOptionName(newName, optNum);
    }
    public void updateOptionCost(String optSetName, String optName, Float newCost) {
        int optNum = findOptionNum(name);
        int optSetNum = findOptionSetNum(optSetName);
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
        int optNum = findOptionNum(optToDelete);
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
        System.out.println("Name of Vehicle: " + this.name);
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
}