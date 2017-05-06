package model;

/**
 * Created by RJ Rivera on 2/14/2017.
 */

public class Option implements java.io.Serializable{

    //Private Instance Variables
    private String name;
    private float cost;
    //Constructor
    public Option(){ }
    public Option(String name, float cost){
        this.name = name;
        this.cost = cost;
    }
    //Getters
    public String getName() {
            return name;
        }
    public float getCost() {
            return cost;
        }
    //Setters
    public void setName(String name) {
            this.name = name;
        }
    public void setCost(float cost) {
            this.cost = cost;
        }
}

