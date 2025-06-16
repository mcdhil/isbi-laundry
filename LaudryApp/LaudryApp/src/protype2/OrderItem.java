package protype2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderItem {
	 private String orderItemId;
	    private Garment garment;
	    private List<ServiceType> services;
	    private int quantity;
	    private String specialInstructions;
	    private double itemPrice; // Calculated price for this item and its services

	    /**
	     * Constructor for OrderItem.
	     * @param garment The garment for this order item.
	     * @param quantity The quantity of this garment.
	     */
	    public OrderItem(Garment garment, int quantity) {
	        this.orderItemId = UUID.randomUUID().toString();
	        this.garment = garment;
	        this.quantity = quantity;
	        this.services = new ArrayList<>();
	        this.specialInstructions = "";
	        this.itemPrice = 0.0; // Price will be calculated based on garment and services
	    }

	    // Getters
	    public String getOrderItemId() {
	        return orderItemId;
	    }

	    public Garment getGarment() {
	        return garment;
	    }

	    public List<ServiceType> getServices() {
	        return services;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public String getSpecialInstructions() {
	        return specialInstructions;
	    }

	    public double getItemPrice() {
	        calculateItemPrice(); // Ensure price is up-to-date
	        return itemPrice;
	    }

	    // Setters
	    public void setGarment(Garment garment) {
	        this.garment = garment;
	        calculateItemPrice();
	    }

	    public void setQuantity(int quantity) {
	        if (quantity > 0) {
	            this.quantity = quantity;
	            calculateItemPrice();
	        } else {
	            System.out.println("Quantity must be positive.");
	        }
	    }

	    public void setSpecialInstructions(String specialInstructions) {
	        this.specialInstructions = specialInstructions;
	    }

	    /**
	     * Adds a service to this order item.
	     * @param service The ServiceType to add.
	     */
	    public void addService(ServiceType service) {
	        if (service != null && !this.services.contains(service)) {
	            this.services.add(service);
	            calculateItemPrice();
	        }
	    }

	    /**
	     * Removes a service from this order item.
	     * @param service The ServiceType to remove.
	     */
	    public void removeService(ServiceType service) {
	        if (service != null) {
	            this.services.remove(service);
	            calculateItemPrice();
	        }
	    }

	    /**
	     * Calculates the price for this order item based on the garment, quantity, and services.
	     * This is a placeholder; actual pricing logic can be complex.
	     */
	    public void calculateItemPrice() {
	        double basePrice = 0.0;
	        if (garment != null) {
	            // Example: Garment might have a base processing fee or weight-based price
	            // For simplicity, let's assume a flat fee per garment type + service fees
	            basePrice = garment.getBaseProcessingCost(); // Hypothetical method in Garment
	        }

	        double servicesCost = 0.0;
	        for (ServiceType service : services) {
	            servicesCost += service.getBasePrice();
	        }

	        this.itemPrice = (basePrice + servicesCost) * quantity;
	    }

	    @Override
	    public String toString() {
	        StringBuilder servicesStr = new StringBuilder();
	        for (ServiceType s : services) {
	            servicesStr.append(s.getServiceName()).append(" ");
	        }
	        return "Item: " + (garment != null ? garment.getName() : "N/A") +
	               ", Qty: " + quantity +
	               ", Services: [" + servicesStr.toString().trim() + "]" +
	               ", Price: $" + String.format("%.2f", getItemPrice());
	    }
}
