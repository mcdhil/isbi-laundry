package protype2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class LaundryOrder {
	private String orderId;
    private Customer customer;
    private Date orderDate;
    private Date expectedDeliveryDate;
    private OrderStatus orderStatus;
    private double totalAmount;
    private List<OrderItem> items;
    private Address pickupAddress;
    private Address deliveryAddress; 

  
    public LaundryOrder(Customer customer) {
        this.orderId = "ID"+UUID.randomUUID().toString().substring(0,4).toUpperCase(); // Generate a unique order ID
        this.customer = customer;
        this.orderDate = new Date(); 
        this.orderStatus = OrderStatus.PENDING; // Default status
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        
        if (customer != null && customer.getAddress() != null) {
            this.pickupAddress = customer.getAddress();
            this.deliveryAddress = customer.getAddress();
        }
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    // Setters
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        // Potentially trigger notifications or other actions on status change
        System.out.println("Order " + orderId + " status changed to: " + orderStatus);
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Adds an item to the laundry order.
     * @param item The OrderItem to add.
     */
    public void addItem(OrderItem item) {
        if (item != null) {
            this.items.add(item);
            calculateTotal(); // Recalculate total when an item is added
        }
    }

    /**
     * Removes an item from the laundry order.
     * @param item The OrderItem to remove.
     */
    public void removeItem(OrderItem item) {
        if (item != null) {
            this.items.remove(item);
            calculateTotal(); // Recalculate total when an item is removed
        }
    }

    /**
     * Calculates the total amount for the order based on its items.
     */
    public void calculateTotal() {
        this.totalAmount = 0.0;
        for (OrderItem item : items) {
            this.totalAmount += item.getItemPrice();
        }
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
               ", Customer: " + (customer != null ? customer.getName() : "N/A") +
               ", Status: " + orderStatus +
               ", Total: $" + String.format("%.2f", totalAmount) +
               ", Items: " + items.size();
    }
}
