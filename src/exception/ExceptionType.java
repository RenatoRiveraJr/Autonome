package exception;

/**
 * Created by RJ Rivera on 2/9/2017.
 */
public enum ExceptionType {
    MissingNumber (1, "Type Mismatch: Expected Integer"),
    MissingFile   (2, "The File Name Is Not Correct");

    private int exceptionNum;
    private String exception;

    ExceptionType(int exceptionNum, String exception) {
        this.exceptionNum = exceptionNum;
        this.exception = exception;
    }

    private int getExceptionNum() { return exceptionNum; }
    private String getException() { return exception; }
}
