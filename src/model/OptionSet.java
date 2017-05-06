package model;

/**
 * Created by sdarthrahj on 1/14/17.
 */

public class OptionSet implements java.io.Serializable{

    //Instance Variables
    private Option[] opt;
    private String name;

    /*+++++++++++++++++++++Constructors+++++++++++++++++++++*/
    protected OptionSet(){ }
    protected OptionSet(Option[] optArray, String name){
        this.opt = optArray;
        this.name = name;
    }
    protected OptionSet(int numOptions){
        opt = new Option[numOptions];
        for(int i = 0;i < numOptions; i++){
            opt[i] = new Option();
        }
    }

    /*+++++++++++++++++++++Getters++++++++++++++++++++++++++*/
    //Getters for OptionSet
    protected String getName() {
        return name;
    }
    protected Option[] getOpt() {
        return opt;
    }
    //Getters for Option
    protected String getOptionName(int optNum){
        return opt[optNum].getName();
    }
    protected Float getOptionCost(int optNum){
        return opt[optNum].getCost();
    }

    /*+++++++++++++++++++++Setters++++++++++++++++++++++++++*/
    //Setters for OptionSet
    protected void setName(String name) {
        this.name = name;
    }
    protected void setOptArray(Option[] optArray) {
        this.opt = optArray;
    }
    //Setters for Option
    protected void setOptionName(String name, int optNum){
        opt[optNum].setName(name);
    }
    protected void setOptionCost(Float cost, int optNum){
        opt[optNum].setCost(cost);
    }

    /*+++++++++++++++++++++Finders++++++++++++++++++++++++++*/
    //Finder for Option
    protected int findOptionNum(String s){
        for(int i = 0; i < opt.length; i++){
            if(opt[i].getName().equals(s))
                return i;
        }
        return -1;
    }

    /*+++++++++++++++++++++Deleters+++++++++++++++++++++++++*/
    //Deleter for Option
    protected void deleteOption(int optNum){
        opt[optNum] = null;
    }

    /*+++++++++++++++++++++Print Methods++++++++++++++++++++*/
    //Print Options
    protected void printOptions(){
        for(int i = 0; i < opt.length; i++){
            System.out.format("%-32s $%7.2f", opt[i].getName(), opt[i].getCost());
            System.out.println();
        }
    }
    protected void printProperties(){
        System.out.println("OptionSet Name: " + this.name);
        for (int i = 0; i < opt.length;i++) {
            System.out.println("Option Name: " + opt[i].getName());
            System.out.println("Option Cost: " + opt[i].getCost());
        }
    }
}