package mistaomega.vending.util;

public class IncompletePaymentException extends RuntimeException{
    private final String message;
    private final long remainingAmount;

    public IncompletePaymentException(String message, long remainingAmount){
        this.message = message;
        this.remainingAmount = remainingAmount;
    }

    public long getRemainingAmount() {
        return remainingAmount;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
