package exceptionHandling;

public class CvrInUseException extends Exception{
   public CvrInUseException(String message){
    super(message);
   }
}
