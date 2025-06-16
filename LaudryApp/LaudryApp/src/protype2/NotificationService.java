package protype2;

public class NotificationService {
	 public void sendNotification(User user, String message, NotificationType type) {
	        if (user == null) {
	            System.out.println("Cannot send notification: User is null.");
	            return;
	        }
	        if (message == null || message.isEmpty()) {
	            System.out.println("Cannot send notification: Message is empty.");
	            return;
	        }

	        System.out.println("--- Sending Notification ---");
	        System.out.println("Type: " + type);
	        System.out.println("Recipient: " + user.getName() + " (User ID: " + user.getUserId() + ")");

	        switch (type) {
	            case EMAIL:
	                System.out.println("To Email: " + user.getEmail());
	                break;
	            case SMS:
	                System.out.println("To Phone (SMS): " + user.getPhoneNumber());
	                break;
	            case IN_APP:
	                System.out.println("To In-App Messages for User: " + user.getName());
	                break;
	            default:
	                System.out.println("Unsupported notification type.");
	                return;
	        }
	        System.out.println("Message: " + message);
	        System.out.println("--- Notification Sent (Simulated) ---");
	    }

	    /**
	     * Sends a notification about an order status update.
	     * @param order The LaundryOrder that was updated.
	     */
	    public void notifyOrderStatusUpdate(LaundryOrder order) {
	        if (order == null || order.getCustomer() == null) {
	            System.out.println("Cannot send order status update: Order or customer is null.");
	            return;
	        }

	        User customer = order.getCustomer();
	        String message = "Dear " + customer.getName() +
	                         ", your laundry order #" + order.getOrderId() +
	                         " has been updated to: " + order.getOrderStatus().getDisplayName() + ".";

	        // Example: Send an email and an SMS
	        sendNotification(customer, message, NotificationType.EMAIL);
	        sendNotification(customer, message, NotificationType.SMS);
	    }

	    /**
	     * Subscribes a customer to order status updates (conceptual).
	     * In a real system, this might involve setting flags or registering listeners.
	     * @param customer The customer to subscribe.
	     * @param order The order to get updates for.
	     */
	    public void subscribeToOrderStatusUpdate(User customer, LaundryOrder order) {
	        System.out.println("User " + customer.getName() + " subscribed to updates for order " + order.getOrderId());
	        // Actual subscription logic would go here
	    }
}
