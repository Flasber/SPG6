package exceptionHandling;

public class SkuAlreadyExistsException extends Exception{
    public SkuAlreadyExistsException(String message){
        super(message);
    }
}
