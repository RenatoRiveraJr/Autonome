package client;

import model.Automobile;
import model.OptionSet;

import java.io.*;
import java.util.Properties;

/**
 * Created by RJ Rivera on 3/9/2017.
 */
public class CarModelOptionsIO {

    //Instance Variables
    private Properties props;

    //Setters
    public void setPropObj(Properties props){
        this.props=props;
    }

    //Getters
    public Properties getPropObj(){
        return this.props;
    }

    //methods
    public void writePropFile(Automobile auto){
        try {

            Properties properties = new Properties();
            properties.setProperty("CarMake", auto.getMake());
            properties.setProperty("CarModel", auto.getModel());
            properties.setProperty("price", auto.getBasePrice().toString());
            for(Integer j = 1; j < auto.getOpset().length+1; j++) {
                properties.setProperty("Option" + j.toString(), auto.getOptionSetName(j-1));
                int numOptions = auto.getNumOptions(j-1);
                for (Integer i = 1; i < numOptions + 1; i++) {
                    properties.setProperty("Option" + j.toString() + "Value" + i.toString() + "a", auto.getOptionName(i - 1, j-1));
                    properties.setProperty("Option" + j.toString() + "Value" + i.toString() + "b", auto.getOptionCost(i - 1, j-1).toString());
                }
            }

            File file = new File("car1.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "Properties File");
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Properties loadPropFile(String path){
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(path)) {
            props.load(in);

            String carMake = props.getProperty("CarMake");
            if (!carMake.equals(null)) {
                String carModel = props.getProperty("CarModel");
                String price = props.getProperty("Price");
                String option1 = props.getProperty("Option1");
                String option1Value1a = props.getProperty("Option1Value1a");
                String option1Value2a = props.getProperty("Option1Value2a");
                String option1Value3a = props.getProperty("Option1Value3a");
                String option1Value4a = props.getProperty("Option1Value4a");
                String option1Value5a = props.getProperty("Option1Value5a");
                String option1Value6a = props.getProperty("Option1Value6a");
                String option1Value7a = props.getProperty("Option1Value7a");
                String option1Value8a = props.getProperty("Option1Value8a");
                String option1Value9a = props.getProperty("Option1Value9a");
                String option1Value10a = props.getProperty("Option1Value10a");
                String option1Value1b = props.getProperty("Option1Value1b");
                String option1Value2b = props.getProperty("Option1Value2b");
                String option1Value3b = props.getProperty("Option1Value3b");
                String option1Value4b = props.getProperty("Option1Value4b");
                String option1Value5b = props.getProperty("Option1Value5b");
                String option1Value6b = props.getProperty("Option1Value6b");
                String option1Value7b = props.getProperty("Option1Value7b");
                String option1Value8b = props.getProperty("Option1Value8b");
                String option1Value9b = props.getProperty("Option1Value9b");
                String option1Value10b = props.getProperty("Option1Value10b");
                String option2 = props.getProperty("Option2");
                String option2Value1a = props.getProperty("Option2Value1a");
                String option2Value2a = props.getProperty("Option2Value2a");
                String option2Value1b = props.getProperty("Option2Value1b");
                String option2Value2b = props.getProperty("Option2Value2b");
                String option3 = props.getProperty("Option3");
                String option3Value1a = props.getProperty("Option3Value1a");
                String option3Value2a = props.getProperty("Option3Value2a");
                String option3Value3a = props.getProperty("Option3Value3a");
                String option3Value1b = props.getProperty("Option3Value1b");
                String option3Value2b = props.getProperty("Option3Value2b");
                String option3Value3b = props.getProperty("Option3Value3b");
                String option4 = props.getProperty("Option4");
                String option4Value1a = props.getProperty("Option4Value1a");
                String option4Value2a = props.getProperty("Option4Value2a");
                String option4Value1b = props.getProperty("Option4Value1b");
                String option4Value2b = props.getProperty("Option4Value2b");
                String option5 = props.getProperty("Option5");
                String option5Value1a = props.getProperty("Option5Value1a");
                String option5Value2a = props.getProperty("Option5Value2a");
                String option5Value1b = props.getProperty("Option5Value1b");
                String option5Value2b = props.getProperty("Option5Value2b");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't find props file, Try again");
            return props;
        }
        return props;
    }
}
