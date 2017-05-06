package driverpkg;

/**
 * Created by darthrahj on 1/14/17.
 */
public class Driver {

    public static void main(String args[]) {

        model.Automobile autoInstance;
        utilpkg.FileIO io = new utilpkg.FileIO();

        //Create new Automobile from file
        model.Automobile automobile1 = io.readFile("Hello1.txt");

        //Print newly Built Automobile Object
        automobile1.print();

        //Serialize the automobile
        io.serialize(automobile1, "Automobile.ser");

        //Deserialize the Automobile object and print
        autoInstance = (model.Automobile) io.deserialize("Automobile.ser");
        autoInstance.print();

    }
}