package mistaomega.vending.util;

public class InsufficientChangeException extends RuntimeException{
    private final String message;

    public InsufficientChangeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
