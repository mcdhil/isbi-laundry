package protype2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LaundryApplication {

	private static Customer customer1;
    private static Staff staffBob;
    private static Machine washer1, dryer1;
    private static ServiceType washService, dryCleanService, ironService;
    private static Garment shirtGarment, suitGarment, dressGarment;
    private static NotificationService notificationService;
    private static List<LaundryOrder> allOrders = new ArrayList<>(); // To keep track of all orders


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeSystem(); // Initialize with some default data

        System.out.println("\nWelcome to the Super Clean Laundry Service App!");
        System.out.println("---------------------------------------------");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }


            switch (choice) {
                case 1: // View Customer Profile
                    if (customer1 != null) {
                        customer1.viewProfile();
                    } else {
                        System.out.println("No customer data available.");
                    }
                    break;
                case 2: // View Staff Profile
                    if (staffBob != null) {
                        staffBob.viewProfile();
                    } else {
                        System.out.println("No staff data available.");
                    }
                    break;
                case 3: // Place New Order
                    placeNewOrder(scanner);
                    break;
                case 4: // View Order Status
                    viewOrderStatus(scanner);
                    break;
                case 5: // Update Order Status (Staff)
                    updateOrderStatusByStaff(scanner);
                    break;
                case 6: // View Machine Status
                    viewAllMachineStatus();
                    break;
                case 7: // View All Orders
                    viewAllOrders();
                    break;
                case 0: // Exit
                    running = false;
                    System.out.println("Thank you for using the Laundry App! Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a newline for better readability
        }
        scanner.close();
    }

    /**
     * Initializes the system with some default data for demonstration.
     */
    private static void initializeSystem() {
        // Create services
        washService = new ServiceType("Wash & Fold", 5.00, "Standard wash and fold service.", 60);
        dryCleanService = new ServiceType("Dry Cleaning", 12.50, "Delicate dry cleaning.", 120);
        ironService = new ServiceType("Ironing", 3.00, "Professional ironing service.", 30);

        // Create garment types
        shirtGarment = new Garment("G001", "Men's Shirt", "Cotton", 1.50);
        suitGarment = new Garment("G002", "Business Suit", "Wool", 5.00);
        dressGarment = new Garment("G003", "Evening Dress", "Silk", 4.00);

        // Create a customer
        Address custAddress = new Address("123 Main St", "Anytown", "Anystate", "12345", "USA");
        customer1 = new Customer("Alice Wonderland", "555-0101", "alice@example.com", custAddress);
        customer1.setPreferences("Use gentle detergent.");

        // Create staff
        staffBob = new Staff("Bob TheOperator", "555-0202", "bob@laundry.com", "S001", "Operator");

        // Create machines
        washer1 = new Machine("Washer", 10.0, "WASHMASTER-5000");
        dryer1 = new Machine("Dryer", 10.0, "DRYLUXE-300");
        
        // Notification service
        notificationService = new NotificationService();

        // Create a sample order to have some data
        LaundryOrder initialOrder = new LaundryOrder(customer1);
        initialOrder.setExpectedDeliveryDate(new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));
        OrderItem shirtItem = new OrderItem(shirtGarment, 2);
        shirtItem.addService(washService);
        shirtItem.addService(ironService);
        initialOrder.addItem(shirtItem);
        initialOrder.calculateTotal();
        customer1.addOrderToHistory(initialOrder);
        allOrders.add(initialOrder);
        System.out.println("System initialized with sample data.");
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println("--- Laundry App Menu ---");
        System.out.println("1. View Customer Profile");
        System.out.println("2. View Staff Profile");
        System.out.println("3. Place New Order");
        System.out.println("4. View Specific Order Status");
        System.out.println("5. Update Order Status (Staff)");
        System.out.println("6. View Machine Status");
        System.out.println("7. View All Orders");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Handles the logic for placing a new order.
     * This is a simplified version for the CLI.
     * @param scanner Scanner object for user input.
     */
    private static void placeNewOrder(Scanner scanner) {
        if (customer1 == null) {
            System.out.println("No customer configured to place an order.");
            return;
        }
        System.out.println("--- Placing New Order for " + customer1.getName() + " ---");
        LaundryOrder newOrder = new LaundryOrder(customer1);
        newOrder.setExpectedDeliveryDate(new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000)); // 5 days

        boolean addingItems = true;
        while (addingItems) {
            System.out.println("Available garments to add:");
            System.out.println("  1. " + shirtGarment.getName() + " (" + shirtGarment.getMaterial() + ")");
            System.out.println("  2. " + suitGarment.getName() + " (" + suitGarment.getMaterial() + ")");
            System.out.println("  3. " + dressGarment.getName() + " (" + dressGarment.getMaterial() + ")");
            System.out.println("  0. Done adding items");
            System.out.print("Choose garment to add (or 0 to finish): ");
            int garmentChoice = -1;
            if(scanner.hasNextInt()){
                garmentChoice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine(); // consume invalid input
                continue;
            }


            if (garmentChoice == 0) {
                addingItems = false;
                break;
            }

            Garment selectedGarment = null;
            switch (garmentChoice) {
                case 1: selectedGarment = shirtGarment; break;
                case 2: selectedGarment = suitGarment; break;
                case 3: selectedGarment = dressGarment; break;
                default: System.out.println("Invalid garment choice."); continue;
            }

            System.out.print("Enter quantity for " + selectedGarment.getName() + ": ");
            int quantity = 1;
            if(scanner.hasNextInt()){
                quantity = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                 System.out.println("Invalid quantity, defaulting to 1.");
                 scanner.nextLine(); // consume invalid input
            }
            
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            OrderItem orderItem = new OrderItem(selectedGarment, quantity);

            // Simplified service addition - adding wash by default
            System.out.println("Adding default service: " + washService.getServiceName());
            orderItem.addService(washService);
            // You could expand this to let users choose services

            newOrder.addItem(orderItem);
            System.out.println(selectedGarment.getName() + " (x" + quantity + ") added to order.");
        }

        if (newOrder.getItems().isEmpty()) {
            System.out.println("Order cancelled as no items were added.");
        } else {
            newOrder.calculateTotal();
            customer1.placeOrder(newOrder); // This method also adds to history
            allOrders.add(newOrder);
            System.out.println("Order placed successfully! Order ID: " + newOrder.getOrderId());
            System.out.println("Total Amount: $" + String.format("%.2f", newOrder.getTotalAmount()));
            notificationService.notifyOrderStatusUpdate(newOrder);
        }
    }

    /**
     * Handles viewing the status of a specific order.
     * @param scanner Scanner object for user input.
     */
    private static void viewOrderStatus(Scanner scanner) {
        if (allOrders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }
        System.out.print("Enter Order ID to view status: ");
        String orderIdToView = scanner.nextLine();

        LaundryOrder foundOrder = findOrderById(orderIdToView);

        if (foundOrder != null) {
            System.out.println("--- Order Status ---");
            System.out.println("Order ID: " + foundOrder.getOrderId());
            System.out.println("Customer: " + foundOrder.getCustomer().getName());
            System.out.println("Status: " + foundOrder.getOrderStatus().getDisplayName());
            System.out.println("Total Amount: $" + String.format("%.2f", foundOrder.getTotalAmount()));
            System.out.println("Items:");
            for (OrderItem item : foundOrder.getItems()) {
                System.out.println("  - " + item);
            }
        } else {
            System.out.println("Order with ID '" + orderIdToView + "' not found.");
        }
    }
    
    /**
     * Finds an order by its ID from the list of all orders.
     * @param orderId The ID of the order to find.
     * @return The LaundryOrder if found, otherwise null.
     */
    private static LaundryOrder findOrderById(String orderId) {
        for (LaundryOrder order : allOrders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Handles updating an order's status by staff.
     * @param scanner Scanner object for user input.
     */
    private static void updateOrderStatusByStaff(Scanner scanner) {
        if (staffBob == null) {
            System.out.println("No staff configured to update orders.");
            return;
        }
        if (allOrders.isEmpty()) {
            System.out.println("No orders available to update.");
            return;
        }

        System.out.print("Enter Order ID to update: ");
        String orderIdToUpdate = scanner.nextLine();
        LaundryOrder orderToUpdate = findOrderById(orderIdToUpdate);

        if (orderToUpdate == null) {
            System.out.println("Order with ID '" + orderIdToUpdate + "' not found.");
            return;
        }

        System.out.println("Current status of order " + orderToUpdate.getOrderId() + ": " + orderToUpdate.getOrderStatus().getDisplayName());
        System.out.println("Available statuses to set:");
        int i = 0;
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(i + ". " + status.getDisplayName());
            i++;
        }
        System.out.print("Enter the number for the new status: ");
        int statusChoice = -1;
        
        if(scanner.hasNextInt()){
            statusChoice = scanner.nextInt();
            scanner.nextLine(); 
        } else {
            System.out.println("Invalid input for status choice.");
            scanner.nextLine(); // consume invalid input
            return;
        }


        if (statusChoice >= 0 && statusChoice < OrderStatus.values().length) {
            OrderStatus newStatus = OrderStatus.values()[statusChoice];
            staffBob.updateOrderStatus(orderToUpdate, newStatus);
            notificationService.notifyOrderStatusUpdate(orderToUpdate);
            System.out.println("Order " + orderToUpdate.getOrderId() + " status updated to " + newStatus.getDisplayName());

            // Simple machine assignment simulation if status is WASHING
            if (newStatus == OrderStatus.WASHING) {
                if (washer1.getStatus() == MachineStatus.AVAILABLE) {
                    staffBob.assignMachine(washer1, orderToUpdate);
                    washer1.startMachine();
                } else if (dryer1.getStatus() == MachineStatus.AVAILABLE && washer1.getStatus() != MachineStatus.AVAILABLE) {
                    // This logic is overly simple, just for demo. A real system would be more complex.
                    System.out.println("Washer1 is busy. Cannot assign to washing. This is a simplified demo.");
                } else {
                     System.out.println("All relevant machines are busy. Cannot start washing immediately.");
                }
            } else if (newStatus == OrderStatus.COMPLETED || newStatus == OrderStatus.DELIVERED) {
                 // If order is completed/delivered, free up machine if it was the current order
                if (washer1.getCurrentOrder() != null && washer1.getCurrentOrder().getOrderId().equals(orderToUpdate.getOrderId())) {
                    washer1.setCurrentOrder(null); // Frees the machine
                }
                if (dryer1.getCurrentOrder() != null && dryer1.getCurrentOrder().getOrderId().equals(orderToUpdate.getOrderId())) {
                    dryer1.setCurrentOrder(null); // Frees the machine
                }
            }


        } else {
            System.out.println("Invalid status choice.");
        }
    }

    /**
     * Displays the status of all configured machines.
     */
    private static void viewAllMachineStatus() {
        System.out.println("--- Machine Status ---");
        if (washer1 != null) {
            System.out.println(washer1);
        } else {
            System.out.println("Washer 1 not configured.");
        }
        if (dryer1 != null) {
            System.out.println(dryer1);
        } else {
            System.out.println("Dryer 1 not configured.");
        }
        // Add more machines if configured
    }
    
    /**
    * Displays all orders in the system.
    */
    private static void viewAllOrders() {
        System.out.println("--- All Orders ---");
        if (allOrders.isEmpty()) {
            System.out.println("No orders in the system yet.");
            return;
        }
        for (LaundryOrder order : allOrders) {
            System.out.println("ID: " + order.getOrderId() + ", Customer: " + order.getCustomer().getName() + 
                               ", Status: " + order.getOrderStatus().getDisplayName() + 
                               ", Total: $" + String.format("%.2f", order.getTotalAmount()));
        }
    }


    /**
     * Initializes the application, setting up necessary services or configurations.
     * This is a placeholder for more complex setup logic. (Not used in CLI main directly)
     */
    public void start() {
        System.out.println("Laundry Application starting...");
        // Initialize database connections, load configurations, etc.
        System.out.println("Laundry Application started successfully.");
    }

    /**
     * Shuts down the application, releasing resources.
     * This is a placeholder for cleanup logic. (Not used in CLI main directly)
     */
    public void shutdown() {
        System.out.println("Laundry Application shutting down...");
        // Close database connections, save state, etc.
        System.out.println("Laundry Application shut down successfully.");
    }
}