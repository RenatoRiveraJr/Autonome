package utilpkg;

import exception.*;
import model.Option;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
/**
 * Created by RJ Rivera on 1/15/2017.
 */
public class FileIO {
    //Read the given file and populate Auto object
    public model.Automobile readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            //Instance Variables
            String currentLine;
            StringTokenizer tokenHolder;
            int numOptions;
            int optSetNum = 0;
            String curToken;
            String lastToken = null;
            boolean problemFixed = false;
            boolean wasProblem = false;
            boolean priceWasNotEmpty = true;

            //Read First line and Set Name, Base price, num of Option Sets
            tokenHolder = new StringTokenizer(br.readLine(), ",");
            model.Automobile car = new model.Automobile(tokenHolder.nextToken(), tokenHolder.nextToken(),
                                            Float.parseFloat(tokenHolder.nextToken()),
                                              Integer.parseInt(tokenHolder.nextToken()));

            //Read 1 line at a time
            while ((currentLine = br.readLine()) != null) {
                //Tokenize the current line
                tokenHolder = new StringTokenizer(currentLine, ",");

                //Read number of options to parse
                numOptions = Integer.parseInt(tokenHolder.nextToken());

                //Instantiate a new OptionSet and number of options within
                car.instantiateOptions(optSetNum, numOptions);

                //First token should be the name of the OptionSet
                car.setOptionSetName(tokenHolder.nextToken(), optSetNum); //set Name of option set

                /*****************************Updated Self Healing Option Reader***************************************/
                for(int optNum = 0; optNum < numOptions; optNum++) {
                    //Set Option Name
                    if(wasProblem && priceWasNotEmpty) {
                        car.setOptionName(lastToken, optNum, optSetNum);
                        wasProblem = false;
                    } else {
                        curToken = tokenHolder.nextToken();
                        car.setOptionName(curToken, optNum, optSetNum);
                        lastToken = curToken;
                    }

                    //Pull next token(Could be expected or unexpected input)
                    curToken = tokenHolder.nextToken();

                    //Cover edge case for whitespaces, or random characters
                    int strlen = curToken.length();
                    for(int i = 0; i < strlen;i++) {
                        char ch = curToken.charAt(i);
                        if (ch == ' ')
                            priceWasNotEmpty = false;
                        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
                            priceWasNotEmpty = true;
                            break;
                        }
                    }

                    //Problem contains methods for "trying" things
                    Problem p = new Problem();

                    //DO: Check/Fix on our token, WHILE: Token isn't a number
                    do {
                        try {
                            problemFixed = p.checkNum(curToken, lastToken);
                        } catch (AutoException ae) {
                            wasProblem = true;//If catch was entered, Update flag
                            lastToken = curToken;//Save token state
                            ae.fix();//Fix exception by asking user for missing cost
                            curToken = ae.getAllPurposeStringObj();//Update current token with user input
                        }
                    }while(!problemFixed);

                    //Set Option Cost With Working Token
                    car.setOptionCost(Float.parseFloat(curToken), optNum, optSetNum);
                }
                /*****************************Updated Self Healing Option Reader***************************************/
                //Move to next OptionSet
                optSetNum++;
            }
            return car;

        } catch (IOException e) {
            return null;
        }
    }
    //Record Error Messages in Log File
    public void writeToLog(String logMessage) {
        //Set my Date Object to pull Timestamps
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("E dd.MM.yyyy '@' hh:mm:ss a zzz");

        //If Log File Exists, Append it, Else Create
        try (FileWriter fw = new FileWriter("LogFile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
        {
            //Write our Error Message and Time Stamp
            out.println(logMessage + " " + date.toString());
        }
        catch(IOException e){
        }
    }
    //Serialize Object
    public boolean serialize(Object myObject, String fileName){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(myObject);
            out.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    //Deserialize Object
    public Object deserialize(String fileName){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            return in.readObject();
        } catch(IOException|ClassNotFoundException e) { e.printStackTrace();}
        return null;
    }

    public void readChosenOptions(model.Automobile car, String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){

            //Instance Variables
            StringTokenizer tokenHolder;

            //Read through All Lines
            for(int i = 0 ; i < 5; i++) {
                //Break Line into Tokens
                tokenHolder = new StringTokenizer(br.readLine(), ",");

                //Add Option name of Chosen Options
                car.getOptNameList().add(tokenHolder.nextToken());

                //Add Properties of Chosen Options
                Option newOpt = new Option(tokenHolder.nextToken(), Float.parseFloat(tokenHolder.nextToken()));
                car.getOptNameList1().add(newOpt);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
/*
Sources:
1: https://docs.oracle.com/javase/tutorial/essential/io/file.html
 */