package scale;
import adapter.ProxyAutomobile;

import java.util.Scanner;

/**
 * Created by RJ Rivera on 2/25/2017.
 */
public class EditOptions extends ProxyAutomobile implements Runnable{

    private int keyToBeEdited;
    private int opToPerform;
    private String optSetName;
    private String optName;
    private String newSetName;
    private String newName;
    private Float newPrice;

    public EditOptions(int keyToBeEdited, String optSetName, String newSetName){
        this.keyToBeEdited = keyToBeEdited;
        this.optSetName = optSetName;
        this.newSetName = newSetName;
        this.opToPerform = 1;
    }
    public EditOptions(int keyToBeEdited, String optSetName, String optName, String newName){
        this.keyToBeEdited = keyToBeEdited;
        this.optSetName = optSetName;
        this.optName = optName;
        this.newName = newName;
        this.opToPerform = 2;
    }
    public EditOptions(int keyToBeEdited, String optSetName, String optName, Float newPrice){
        this.keyToBeEdited = keyToBeEdited;
        this.optSetName = optSetName;
        this.optName = optName;
        this.newPrice = newPrice;
        this.opToPerform = 3;
    }

    //Run Thread
    public void run() {
        boolean entered = false;
        while(entered == false)
            if(getEditStatus() == false) {
                setEditStatus(true);
                switch (opToPerform) {
                    case 1:
                        getInput();
                        updateOptionSetName(keyToBeEdited, optSetName, newSetName);
                        System.out.println("Here is the new Option Set");
                        getAutoMap().get(keyToBeEdited).printOptionSetNames();
                        setEditStatus(false);
                        entered = true;
                        break;
                    case 2:
                        updateOptionName(keyToBeEdited, optSetName, optName, newName);
                        notifyAll();
                        break;
                    case 3:
                        updateOptionPrice(keyToBeEdited, optSetName, optName, newPrice);
                        notifyAll();
                        break;
                    default:
                        System.out.println("No Change Made");
                        break;
                }
            } else {
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                entered = false;
            }
    }

    public void getInput(){
        switch (opToPerform) {
            case 1:
                Scanner user_input = new Scanner(System.in);
                System.out.println("Here are the current options for this Automobile:");
                getAutoMap().get(keyToBeEdited).printOptionSetNames();
                System.out.println("Which Option Set Name would you like to change?");
                optSetName = user_input.next();
                System.out.println("What would you like to change the Set Name to?");
                newSetName = user_input.next();
        }
    }
}