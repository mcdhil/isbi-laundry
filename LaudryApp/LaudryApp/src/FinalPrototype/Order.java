package FinalPrototype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's laundry order.
 */
public class Order {
    private String orderId;
    private Customer customer;
    private Staff staffAssigned; // Optional: staff member who processed the order
    private List<OrderItem> orderItems;
    private double totalAmount;
    private LocalDateTime orderDateTime;
    private LocalDate estimatedCompletionDate;
    private OrderStatus status;

    public Order(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null for an order.");
        }
        this.orderId = IdGenerator.generateOrderId();
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.totalAmount = 0.0;
        // Default estimated completion: 2 days from now
        this.estimatedCompletionDate = LocalDate.now().plusDays(2); 
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Staff getStaffAssigned() { return staffAssigned; }
    public void setStaffAssigned(Staff staffAssigned) { this.staffAssigned = staffAssigned; }
    public List<OrderItem> getOrderItems() { return new ArrayList<>(orderItems); } // Return a copy
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public LocalDate getEstimatedCompletionDate() { return estimatedCompletionDate; }
    public void setEstimatedCompletionDate(LocalDate estimatedCompletionDate) { this.estimatedCompletionDate = estimatedCompletionDate; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void addOrderItem(OrderItem item) {
        if (item != null) {
            this.orderItems.add(item);
            recalculateTotalAmount();
        }
    }

    public void removeOrderItem(String orderItemId) {
        orderItems.removeIf(item -> item.getOrderItemId().equals(orderItemId));
        recalculateTotalAmount();
    }

    private void recalculateTotalAmount() {
        this.totalAmount = 0;
        for (OrderItem item : orderItems) {
            this.totalAmount += item.getItemSubtotal();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId)
          .append("\nCustomer: ").append(customer.getName())
          .append(" (ID: ").append(customer.getId()).append(")")
          .append("\nOrder Date: ").append(orderDateTime.toLocalDate())
          .append("\nStatus: ").append(status.getDisplayName())
          .append("\nEstimated Completion: ").append(estimatedCompletionDate)
          .append("\nTotal Amount: $").append(String.format("%.2f", totalAmount));
        if (staffAssigned != null) {
            sb.append("\nProcessed by: ").append(staffAssigned.getName());
        }
        sb.append("\nItems:");
        if (orderItems.isEmpty()) {
            sb.append("\n  No items in this order.");
        } else {
            for (OrderItem item : orderItems) {
                sb.append("\n  - ").append(item.toString());
            }
        }
        return sb.toString();
    }}