package client;

import adapter.ProxyAutomobile;
import model.Automobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by RJ Rivera on 3/12/2017.
 */
public class SelectCarOptions extends DefaultSocketClient{

    private Automobile auto;
    private Properties props;

    public SelectCarOptions  (String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }//constructor

    //Getters
    public Automobile getAuto() {
        return auto;
    }
    public Properties getProps() {
        return props;
    }
    //Setters
    public void setAuto(Automobile auto) {
        this.auto = auto;
    }
    public void setProps(Properties props) {
        this.props = props;
    }

    @Override
    public void run(){
        if (openConnection()) {
            handleSession();
            closeSession();
        }
        chooseOptions();
    }

    @Override
    public void handleSession(){
        try {
            String[] tempStr;
            String[] tempStr2;
            sendOutput("Configure Car");


            tempStr = (String[]) getReader().readObject();
            System.out.println("--------------------------");
            System.out.println("Here's a list of cars:");
            for(int i = 0; i< tempStr.length; i++)
                System.out.println((i+1) + ": " + tempStr[i]);
            System.out.println("--------------------------");
            System.out.println("Enter the number of the car you would like to configure:");

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            sendOutput(stdIn.readLine());

            setProps((Properties) getReader().readObject());
            setAuto(buildCarFromProps());
        }catch(IOException e){
            if (DEBUG) System.err.println
                    ("Unable to Read input ");
        }catch (ClassNotFoundException e){
            if (DEBUG) System.err.println
                    ("Class Not Found ");
        }
    }
    public Automobile buildCarFromProps(){
        int numOfOptions = Integer.parseInt(getProps().getProperty("OptionNum"));
        Automobile newCar = new Automobile(getProps().getProperty("CarMake"), getProps().getProperty("CarModel"), Float.parseFloat(getProps().getProperty("price")), numOfOptions);
        for(Integer i = 1; i < numOfOptions + 1; i++) {
            int numOfOptOptions = Integer.parseInt(getProps().getProperty("Option"+ i.toString() +"Num"));
            newCar.instantiateOptions(i-1, numOfOptOptions);
            for (Integer j = 1; j <numOfOptOptions + 1 ; j++){
                newCar.setOptionSetName(getProps().getProperty("Option"+ i.toString()), i-1);
                String temp = getProps().getProperty("Option"+ i.toString() + "Value" + j.toString() + "a");
                Float tempFloat = Float.parseFloat(getProps().getProperty("Option"+ i.toString() + "Value" + j.toString() + "b"));
                newCar.setOptionName(temp, j-1,  i-1);
                newCar.setOptionCost(tempFloat,j-1, i-1);
            }
        }

        return newCar;
    }

    public void chooseOptions(){
        try {
            getAuto().chooseOptions();
        }catch(IOException e){}
        getAuto().printChosenOptions();
    }
}

