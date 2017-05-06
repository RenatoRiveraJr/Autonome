package server;

import adapter.ProxyAutomobile;
import model.Automobile;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by RJ Rivera on 3/10/2017.
 */
public class BuildCarModelOptions {

    //Instance Variables
    private Properties props;

    //Constructors
    public BuildCarModelOptions() {
    }

    //Getters
    public Properties getProps() {
        return props;
    }

    //Setters
    public void setProps(Properties props) {
        this.props = props;
    }

    //Methods
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

    public void writePropFile(Automobile auto){

        Properties properties = new Properties();
        properties.setProperty("CarMake", auto.getMake());
        properties.setProperty("CarModel", auto.getModel());
        properties.setProperty("price", auto.getBasePrice().toString());
        Integer optionNum = auto.getOpset().length;
        properties.setProperty("OptionNum", optionNum.toString());
        for (Integer j = 1; j < auto.getOpset().length + 1; j++) {
            properties.setProperty("Option" + j.toString(), auto.getOptionSetName(j - 1));
            Integer numOptions = auto.getNumOptions(j - 1);
            properties.setProperty("Option" + j.toString() + "Num", numOptions.toString());
            for (Integer i = 1; i < numOptions + 1; i++) {
                properties.setProperty("Option" + j.toString() + "Value" + i.toString() + "a", auto.getOptionName(i - 1, j - 1));
                properties.setProperty("Option" + j.toString() + "Value" + i.toString() + "b", auto.getOptionCost(i - 1, j - 1).toString());
            }
        }
        setProps(properties);

    }
}
