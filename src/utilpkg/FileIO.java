package utilpkg;

import java.io.*;
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

            //Read First line and Set Name, Base price, num of Option Sets(Are these nested methods bad???)
            tokenHolder = new StringTokenizer(br.readLine(), ",");
            model.Automobile car = new model.Automobile(tokenHolder.nextToken(),
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

                //Read the options for as long as there are number of options
                for(int optNum = 0; optNum < numOptions; optNum++) {
                    car.setOptionName(tokenHolder.nextToken(), optNum, optSetNum);
                    car.setOptionCost(Float.parseFloat(tokenHolder.nextToken()), optNum, optSetNum);
                }
                //Move to next OptionSet
                optSetNum++;
            }
            return car;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    //Deserialze Object
    public Object deserialize(String fileName){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            return in.readObject();
        } catch(IOException|ClassNotFoundException e) { e.printStackTrace();}
        return null;
    }
}