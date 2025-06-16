package protype2;

public enum OrderStatus {
	PENDING("Pending Confirmation"),          // Order placed by customer, awaiting confirmation
    CONFIRMED("Confirmed & Awaiting Pickup"), // Order confirmed by staff
    PICKED_UP("Picked Up"),                   // Items collected from customer
    RECEIVED_AT_FACILITY("Received at Facility"),// Items arrived at laundry facility
    PROCESSING("Processing"),                 // General processing state
    WASHING("Washing"),                       // Items are being washed
    DRYING("Drying"),                         // Items are being dried
    IRONING("Ironing/Pressing"),              // Items are being ironed/pressed
    QUALITY_CHECK("Quality Check"),           // Items undergoing quality inspection
    READY_FOR_DELIVERY("Ready for Delivery"), // Items are ready to be delivered back
    OUT_FOR_DELIVERY("Out for Delivery"),     // Items are en route to customer
    DELIVERED("Delivered"),                   // Items successfully delivered to customer
    COMPLETED("Completed"),                   // Order fully completed and closed
    CANCELLED("Cancelled"),                   // Order was cancelled
    ON_HOLD("On Hold");                       // Order is temporarily paused

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
