package protype2;

import java.util.Date;
import java.util.UUID;

public class Payment {
	private String paymentId;
    private LaundryOrder order;
    private double amount;
    private Date paymentDate;
    private String paymentMethod; // e.g., "Credit Card", "Cash", "Online Wallet"
    private String paymentStatus; // e.g., "Pending", "Completed", "Failed", "Refunded"
    private String transactionId; // From payment gateway, if applicable

    /**
     * Constructor for Payment.
     * @param order The LaundryOrder for which this payment is made.
     * @param amount The amount to be paid.
     * @param paymentMethod The method of payment.
     */
    public Payment(LaundryOrder order, double amount, String paymentMethod) {
        this.paymentId = UUID.randomUUID().toString();
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending"; // Default status
        this.paymentDate = null; // Set when payment is processed
    }

    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public LaundryOrder getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    // Setters
    // Amount and order are typically set at creation and not changed.
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        System.out.println("Payment " + paymentId + " status updated to: " + paymentStatus);
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Simulates processing the payment.
     * In a real application, this would interact with a payment gateway.
     * @return true if payment is successful, false otherwise.
     */
    public boolean processPayment() {
        System.out.println("Processing payment of $" + String.format("%.2f", amount) +
                           " for order " + (order != null ? order.getOrderId() : "N/A") +
                           " via " + paymentMethod);

        // Simulate payment success/failure
        boolean paymentSuccessful = Math.random() > 0.1; // 90% success rate

        if (paymentSuccessful) {
            this.paymentDate = new Date();
            this.paymentStatus = "Completed";
            this.transactionId = "TXN_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            System.out.println("Payment successful. Transaction ID: " + this.transactionId);
            if (order != null) {
                // Optionally update order status or trigger next steps
                // order.setOrderStatus(OrderStatus.PAYMENT_COMPLETED); // Example
            }
            return true;
        } else {
            this.paymentStatus = "Failed";
            System.out.println("Payment failed.");
            return false;
        }
    }

    /**
     * Simulates issuing a receipt for the payment.
     */
    public void issueReceipt() {
        if ("Completed".equals(paymentStatus)) {
            System.out.println("--- Payment Receipt ---");
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Order ID: " + (order != null ? order.getOrderId() : "N/A"));
            System.out.println("Amount: $" + String.format("%.2f", amount));
            System.out.println("Date: " + paymentDate);
            System.out.println("Method: " + paymentMethod);
            System.out.println("Transaction ID: " + transactionId);
            System.out.println("Status: " + paymentStatus);
            System.out.println("-----------------------");
        } else {
            System.out.println("Cannot issue receipt for payment that is not completed. Status: " + paymentStatus);
        }
    }

    @Override
    public String toString() {
        return "Payment ID: " + paymentId + ", Order: " + (order != null ? order.getOrderId() : "N/A") +
               ", Amount: $" + String.format("%.2f", amount) + ", Status: " + paymentStatus;
    }
}
