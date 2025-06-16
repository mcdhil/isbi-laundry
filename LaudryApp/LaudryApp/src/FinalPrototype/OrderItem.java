package FinalPrototype;

public class OrderItem {

	 private String orderItemId;
	    private Garment garment;
	    private ServiceType serviceType;
	    private int quantity;
	    private double unitPrice; // Price per unit for this specific garment and service at the time of order
	    private double itemSubtotal;

	    public OrderItem(Garment garment, ServiceType serviceType, int quantity, double unitPrice) {
	        if (garment == null || serviceType == null) {
	            throw new IllegalArgumentException("Garment and ServiceType cannot be null.");
	        }
	        if (quantity <= 0) {
	            throw new IllegalArgumentException("Quantity must be positive.");
	        }
	        if (unitPrice < 0) {
	            throw new IllegalArgumentException("Unit price cannot be negative.");
	        }
	        this.orderItemId = IdGenerator.generateOrderItemId();
	        this.garment = garment;
	        this.serviceType = serviceType;
	        this.quantity = quantity;
	        this.unitPrice = unitPrice;
	        this.itemSubtotal = unitPrice * quantity;
	    }

	    // Getters
	    public String getOrderItemId() { return orderItemId; }
	    public Garment getGarment() { return garment; }
	    public ServiceType getServiceType() { return serviceType; }
	    public int getQuantity() { return quantity; }
	    public double getUnitPrice() { return unitPrice; }
	    public double getItemSubtotal() { return itemSubtotal; }

	    @Override
	    public String toString() {
	        return String.format("%s - %s (Qty: %d @ $%.2f each) = $%.2f",
	                garment.getName(), serviceType.getDisplayName(), quantity, unitPrice, itemSubtotal);
	    }
}
