package exceptionHandling;

public class WarrantyInUseException extends Exception{
    public WarrantyInUseException(String message){
        super(message);
    }
}
