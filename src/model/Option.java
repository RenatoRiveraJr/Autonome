package model;

/**
 * Created by RJ Rivera on 2/14/2017.
 */

public class Option implements java.io.Serializable{

    //Private Instance Variables
    private String name;
    private Float cost;
    //Constructor
    public Option(){ }
    public Option(String name, Float cost){
        this.name = name;
        this.cost = cost;
    }
    //Getters
    public String getName() {
            return name;
        }
    public Float getCost() {
            return cost;
        }
    //Setters
    public void setName(String name) {
            this.name = name;
        }
    public void setCost(Float cost) {
            this.cost = cost;
        }
}