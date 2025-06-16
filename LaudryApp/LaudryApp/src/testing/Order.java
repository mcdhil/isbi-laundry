package testing;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.UUID; 

public class Order {
	 private String orderId;
	    private Customer customer;
	    private List<Garment> items;
	    private Date orderDate;
	    private OrderStatus status;
	    private double totalAmount;

	    public Order(Customer customer) {
	    this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase(); // Simple unique ID
	    this.customer = customer;
	    this.items = new ArrayList<>();
	    this.orderDate = new Date(); // Current date and time
	    this.status = OrderStatus.PENDING;
	    this.totalAmount = 0.0;
	    }

	    // Getters
	    public String getOrderId() { return orderId; }
	    public Customer getCustomer() { return customer; }
	    public List<Garment> getItems() { return items; }
	    public OrderStatus getStatus() { return status; }
	    public double getTotalAmount() { return totalAmount; }

	    public void addItem(Garment garment) {
	        this.items.add(garment);
	        calculateTotalAmount(); // Recalculate when a new item is added
	    }

	    public void calculateTotalAmount() {
	        this.totalAmount = 0;
	        for (Garment item : items) {
	            this.totalAmount += item.calculateCost();
	        }
	    }

	    public void setStatus(OrderStatus status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Order ID: ").append(orderId).append("\n");
	        sb.append("Customer: ").append(customer.getName()).append(" (").append(customer.getCustomerId()).append(")\n");
	        sb.append("Order Date: ").append(orderDate).append("\n");
	        sb.append("Status: ").append(status).append("\n");
	        sb.append("Items:\n");
	        for (Garment item : items) {
	            sb.append("  - ").append(item).append("\n");
	        }
	        sb.append("Total Amount: $").append(String.format("%.2f", totalAmount));
	        return sb.toString();
	    }
}
