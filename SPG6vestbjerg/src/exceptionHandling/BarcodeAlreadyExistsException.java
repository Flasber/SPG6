package exceptionHandling;

public class BarcodeAlreadyExistsException extends Exception{
    public BarcodeAlreadyExistsException(String message){
        super(message);
    }
}
