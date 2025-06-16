package testing;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
  

public class LaundryService {
	
	    private List<Customer> customers = new ArrayList<>();
	    private List<Order> orders = new ArrayList<>();
	    private Type[] types = {
	    		new Wash(),
	    };
	    private Scanner scanner = new Scanner(System.in);

	    public void addCustomer() {
	        System.out.print("Enter customer name: ");
	        String name = scanner.nextLine();
	        System.out.print("Enter customer phone number: ");
	        String phone = scanner.nextLine();
	        Customer newCustomer = new Customer(name, phone);
	        customers.add(newCustomer);
	        System.out.println("Customer added successfully! " + newCustomer);
	    }

	    public Customer findCustomerById(String custId) {
	        for (Customer customer : customers) {
	            if (customer.getCustomerId().equalsIgnoreCase(custId)) {
	                return customer;
	            }
	        }
	        return null;
	    }
	    
	    public Order findOrderById(String orderId) {
	        for (Order order : orders) {
	            if (order.getOrderId().equalsIgnoreCase(orderId)) {
	                return order;
	            }
	        }
	        return null;
	    }

	    public void placeNewOrder() {
	        System.out.print("Enter Customer ID to place order: ");
	        String custId = scanner.nextLine();
	        Customer customer = findCustomerById(custId);

	        if (customer == null) {
	            System.out.println("Customer not found. Please add customer first.");
	            return;
	        }

	        Order newOrder = new Order(customer);
	        boolean addingItems = true;
	        while (addingItems) {
	            System.out.print("Enter garment name (e.g., Shirt, Pant) or type 'done' to finish: ");
	            String itemName = scanner.nextLine();
	            if (itemName.equalsIgnoreCase("done")) {
	                addingItems = false;
	                continue;
	            }

	            System.out.print("Enter quantity for " + itemName + ": ");
	            int quantity;
	            try {
	                quantity = Integer.parseInt(scanner.nextLine());
	                if (quantity <= 0) {
	                    System.out.println("Quantity must be positive.");
	                    continue;
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid quantity. Please enter a number.");
	                continue;
	            }
	            

	            System.out.println("Select service type:");
	            int i = 1;
	            for (ServiceType type : ServiceType.values()) {
	                System.out.println(i + ". " + type + " ($" + type.getPrice() + (type == ServiceType.WASH_FOLD ? "/unit or kg)" : "/item)"));
	                i++;
	            }
	            System.out.print("Enter service type number: ");
	            int serviceChoice;
	             try {
	                serviceChoice = Integer.parseInt(scanner.nextLine());
	                if (serviceChoice < 1 || serviceChoice > ServiceType.values().length) {
	                    System.out.println("Invalid choice.");
	                    continue;
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a number.");
	                continue;
	            }

	            ServiceType selectedService = ServiceType.values()[serviceChoice - 1];
	            newOrder.addItem(new Garment(itemName, quantity, selectedService));
	            System.out.println(itemName + " added to order.");
	        }

	        if (newOrder.getItems().isEmpty()) {
	            System.out.println("Order cancelled as no items were added.");
	        } else {
	            newOrder.calculateTotalAmount();
	            orders.add(newOrder);
	            System.out.println("Order placed successfully!");
	            System.out.println(newOrder);
	        }
	    }

	    public void viewOrderStatus() {
	        System.out.print("Enter Order ID to check status: ");
	        String orderId = scanner.nextLine();
	        Order order = findOrderById(orderId);
	        if (order != null) {
	            System.out.println("Order Details:");
	            System.out.println(order);
	        } else {
	            System.out.println("Order ID not found.");
	        }
	    }
	    
	    public void updateOrderStatus() {
	        System.out.print("Enter Order ID to update status: ");
	        String orderId = scanner.nextLine();
	        Order order = findOrderById(orderId);

	        if (order == null) {
	            System.out.println("Order ID not found.");
	            return;
	        }

	        System.out.println("Current Status: " + order.getStatus());
	        System.out.println("Select new status:");
	        int i = 1;
	        for (OrderStatus status : OrderStatus.values()) {
	            System.out.println(i + ". " + status);
	            i++;
	        }
	        System.out.print("Enter status number: ");
	        int statusChoice;
	        try {
	            statusChoice = Integer.parseInt(scanner.nextLine());
	             if (statusChoice < 1 || statusChoice > OrderStatus.values().length) {
	                System.out.println("Invalid choice.");
	                return;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a number.");
	            return;
	        }
	       

	        OrderStatus newStatus = OrderStatus.values()[statusChoice - 1];
	        order.setStatus(newStatus);
	        System.out.println("Order " + orderId + " status updated to " + newStatus);
	        System.out.println(order); // Show updated order
	    }
	    
	    public void viewAllOrders() {
	        if (orders.isEmpty()) {
	            System.out.println("No orders placed yet.");
	            return;
	        }
	        System.out.println("\n--- All Orders ---");
	        for (Order order : orders) {
	            System.out.println(order);
	            System.out.println("--------------------");
	        }
	    }

	    public void viewAllCustomers() {
	        if (customers.isEmpty()) {
	            System.out.println("No customers registered yet.");
	            return;
	        }
	        System.out.println("\n--- All Customers ---");
	        for (Customer customer : customers) {
	            System.out.println(customer);
	            System.out.println("--------------------");
	        }
	    }
}
