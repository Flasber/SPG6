package exceptionHandling;

public class EmailInUseException extends Exception{
    public EmailInUseException(String message){
        super(message);
    }
}
