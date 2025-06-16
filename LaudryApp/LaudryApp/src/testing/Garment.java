package testing;

public class Garment {
	 private String itemName;
	    private int quantity;
	    private ServiceType serviceType;
	    // You might add weight if ServiceType.WASH_FOLD is priced by weight

	    public Garment(String itemName, int quantity, ServiceType serviceType) {
	        this.itemName = itemName;
	        this.quantity = quantity;
	        this.serviceType = serviceType;
	    }

	    // Getters
	    public String getItemName() { return itemName; }
	    public int getQuantity() { return quantity; }
	    public ServiceType getServiceType() { return serviceType; }

	    public double calculateCost() {
	        // Simplified cost calculation
	        return quantity * serviceType.getPrice();
	    }

	    @Override
	    public String toString() {
	        return "Item: " + itemName + ", Quantity: " + quantity + ", Service: " + serviceType + ", Cost: $" + String.format("%.2f", calculateCost());
	    }
}
