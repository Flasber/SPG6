package exceptionHandling;

public class PhoneNumberInUseException extends Exception {
    public PhoneNumberInUseException(String message){
        super(message);
    }
}
