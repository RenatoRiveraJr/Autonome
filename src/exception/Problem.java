package exception;

public class Problem {

    public boolean checkNum(String str, String optName) throws AutoException
    {
        boolean flag = false;
        try {
            Float.parseFloat(str);
            flag = true;
        }
        catch(NumberFormatException f) {
            throw new AutoException(1, "NumberFormatException", optName);
        }
        return flag;
    }
}
