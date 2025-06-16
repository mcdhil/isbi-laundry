package FinalPrototype;

public enum OrderStatus {

	PENDING("Pending"),
    PROCESSING("Processing"),
    READY_FOR_PICKUP("Ready for Pickup"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
