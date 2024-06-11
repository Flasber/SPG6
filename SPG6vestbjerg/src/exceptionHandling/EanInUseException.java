package exceptionHandling;

public class EanInUseException extends Exception{
   public EanInUseException(String message){
    super(message);
   }
}
