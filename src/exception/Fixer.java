package exception;

import java.util.Scanner;

/**
 * Created by RJ Rivera on 2/10/2017.
 */
public class Fixer {
    public String fixNum(String optionName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("File is missing a Cost for Option \"" + optionName + "\"");
        System.out.println("How Much Does " + optionName + " Cost? : ");
        return sc.nextLine();
    }
}
