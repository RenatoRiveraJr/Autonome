package exception;

import utilpkg.FileIO;
/**
 * Created by RJ Rivera on 1/31/2017.
 */
public class AutoException extends Exception {
    private int errorno;
    private String errormsg;
    private String allPurposeStringObj;

    public AutoException() {
        super();
        writeToLog();
    }

    public AutoException(String errormsg) {
        super();
        this.errormsg = errormsg;
        writeToLog();
    }

    public AutoException(int errorno) {
        super();
        this.errorno = errorno;
        writeToLog();
    }

    public AutoException(int errorno, String errormsg) {
        super();
        this.errorno = errorno;
        this.errormsg = errormsg;
        writeToLog();
    }
    public AutoException(int errorno, String errormsg, String str) {
        super();
        this.errorno = errorno;
        this.errormsg = errormsg;
        this.allPurposeStringObj = str;
        writeToLog();
    }

    //Getters
    public int getErrorno() {
        return errorno;
    }
    public String getErrormsg() {
        return errormsg;
    }
    public String getAllPurposeStringObj() { return allPurposeStringObj;}

    //Setters
    public void setErrorno(int errorno) {
        this.errorno = errorno;
    }
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
    public void setAllPurposeStringObj(String str){
        this.allPurposeStringObj = str;
    }
    private void writeToLog(){
        FileIO io = new FileIO();
        io.writeToLog(getErrormsg());
    }

    //Fix Method that calls other methods based on error number
    public void fix(){
        Fixer fixer = new Fixer();
        switch(getErrorno()){
            case 1: setAllPurposeStringObj(fixer.fixNum(getAllPurposeStringObj()));
            default: break;
        }
    }
}
