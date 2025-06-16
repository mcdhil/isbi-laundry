package FinalPrototype;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Main application class with console UI.
 */
public class LaundryApplication {
    private static LaundrySystemFacade facade = new LaundrySystemFacade();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Laundry Management System!");
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1: manageCustomers(); break;
                case 2: manageStaff(); break;
                case 3: manageOrders(); break;
                case 4: viewReports(); break; // Basic reports
                case 0: running = false; break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for using the Laundry Management System. Goodbye!");
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Customers");
        System.out.println("2. Manage Staff");
        System.out.println("3. Manage Orders");
        System.out.println("4. View Reports");
        System.out.println("0. Exit");
    }

    // --- Customer Management ---
    private static void manageCustomers() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Find Customer by ID");
            System.out.println("0. Back to Main Menu");
            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1: addCustomer(); break;
                case 2: viewAllCustomers(); break;
                case 3: findCustomer(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addCustomer() {
        System.out.println("\n--- Add New Customer ---");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email (optional): ");
        String email = scanner.nextLine();
        System.out.print("Enter Street Address: ");
        String street = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Zip Code: ");
        String zip = scanner.nextLine();

        Customer newCustomer = facade.addCustomer(name, phone, email, street, city, state, zip);
        System.out.println("Customer added successfully: " + newCustomer);
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- All Customers ---");
        List<Customer> customers = facade.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void findCustomer() {
        System.out.println("\n--- Find Customer ---");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        Optional<Customer> customerOpt = facade.findCustomerById(id);
        if (customerOpt.isPresent()) {
            System.out.println("Customer Found: " + customerOpt.get());
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

    // --- Staff Management ---
    private static void manageStaff() {
         boolean back = false;
        while (!back) {
            System.out.println("\n--- Staff Management ---");
            System.out.println("1. Add New Staff Member");
            System.out.println("2. View All Staff Members");
            System.out.println("3. Find Staff Member by ID");
            System.out.println("0. Back to Main Menu");
            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1: addStaff(); break;
                case 2: viewAllStaff(); break;
                case 3: findStaff(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void addStaff() {
        System.out.println("\n--- Add New Staff Member ---");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email (optional): ");
        String email = scanner.nextLine();
        System.out.print("Enter Street Address: ");
        String street = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Zip Code: ");
        String zip = scanner.nextLine();
        System.out.print("Enter Role (e.g., Attendant, Manager): ");
        String role = scanner.nextLine();

        Staff newStaff = facade.addStaff(name, phone, email, street, city, state, zip, role);
        System.out.println("Staff member added successfully: " + newStaff);
    }

    private static void viewAllStaff() {
        System.out.println("\n--- All Staff Members ---");
        List<Staff> staffList = facade.getAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            staffList.forEach(System.out::println);
        }
    }
    
    private static void findStaff() {
        System.out.println("\n--- Find Staff Member ---");
        System.out.print("Enter Staff ID: ");
        String id = scanner.nextLine();
        Optional<Staff> staffOpt = facade.findStaffById(id);
        if (staffOpt.isPresent()) {
            System.out.println("Staff Found: " + staffOpt.get());
        } else {
            System.out.println("Staff member with ID " + id + " not found.");
        }
    }

    // --- Order Management ---
    private static void manageOrders() {
        boolean back = false;
        while(!back) {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Create New Order");
            System.out.println("2. View Order Details");
            System.out.println("3. Update Order Status");
            System.out.println("4. View All Orders");
            System.out.println("5. View Orders for a Customer");
            System.out.println("0. Back to Main Menu");
            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1: createNewOrder(); break;
                case 2: viewOrderDetails(); break;
                case 3: updateOrderStatus(); break;
                case 4: viewAllOrders(); break;
                case 5: viewOrdersForCustomer(); break;
                case 0: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void createNewOrder() {
        System.out.println("\n--- Create New Order ---");
        System.out.print("Enter Customer ID for the order: ");
        String customerId = scanner.nextLine();
        Optional<Customer> customerOpt = facade.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            System.out.println("Customer not found. Please add the customer first or try a valid ID.");
            return;
        }
        
        Order currentOrder = facade.createNewOrder(customerId);
        if (currentOrder == null) {
             System.out.println("Failed to initialize order."); // Should be caught by customer check earlier
             return;
        }

        System.out.println("Order started for " + customerOpt.get().getName() + ". Order ID: " + currentOrder.getOrderId());

        boolean addingItems = true;
        while(addingItems) {
            System.out.println("\nAvailable Garments:");
            List<Garment> garments = facade.getAvailableGarmentsFromCatalog();
            for (int i = 0; i < garments.size(); i++) {
                Garment g = garments.get(i);
                System.out.println((i + 1) + ". " + g.getName());
                 g.getAllBasePrices().forEach((st, price) -> 
                    System.out.printf("    - %s: $%.2f%n", st.getDisplayName(), price)
                );
            }
            System.out.println("0. Finish Adding Items");

            int garmentChoice = readIntInput("Choose garment by number (or 0 to finish): ");
            if (garmentChoice == 0) {
                addingItems = false;
                continue;
            }
            if (garmentChoice < 1 || garmentChoice > garments.size()) {
                System.out.println("Invalid garment choice.");
                continue;
            }
            Garment selectedGarment = garments.get(garmentChoice - 1);

            System.out.println("\nAvailable Services for " + selectedGarment.getName() + ":");
            ServiceType[] services = facade.getAvailableServiceTypes();
            int serviceIdx = 1;
            for (ServiceType st : services) {
                if (selectedGarment.getBasePrice(st) > 0) { // Only show services applicable to garment
                    System.out.println(serviceIdx++ + ". " + st.getDisplayName());
                }
            }
             if (serviceIdx == 1) { // No services available for this garment
                System.out.println("No services available for " + selectedGarment.getName() + ". Please check garment setup.");
                continue;
            }
            System.out.println("0. Cancel Adding This Item");
            
            int serviceChoice = readIntInput("Choose service by number: ");
             if (serviceChoice == 0) continue;

            ServiceType selectedService = null;
            int currentDisplayedService = 1;
            for (ServiceType st : services) {
                 if (selectedGarment.getBasePrice(st) > 0) {
                    if (currentDisplayedService == serviceChoice) {
                        selectedService = st;
                        break;
                    }
                    currentDisplayedService++;
                }
            }

            if (selectedService == null) {
                System.out.println("Invalid service choice.");
                continue;
            }

            int quantity = readIntInput("Enter quantity for " + selectedGarment.getName() + " (" + selectedService.getDisplayName() + "): ");
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            boolean itemAdded = facade.addItemToOrder(currentOrder, selectedGarment.getName(), selectedService, quantity);
            if (itemAdded) {
                System.out.println("Added to order: " + quantity + "x " + selectedGarment.getName() + " - " + selectedService.getDisplayName());
                System.out.printf("Current Order Total: $%.2f%n", currentOrder.getTotalAmount());
            } else {
                System.out.println("Failed to add item. It might not be offered or price is not set.");
            }
        }

        if (!currentOrder.getOrderItems().isEmpty()) {
            System.out.println("\n--- Order Summary ---");
            System.out.println(currentOrder);
            System.out.print("Confirm and finalize order? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                facade.finalizeAndSaveOrder(currentOrder);
                System.out.println("Order successfully created and saved!");
            } else {
                System.out.println("Order creation cancelled.");
            }
        } else {
            System.out.println("No items added to order. Order not created.");
        }
    }

    private static void viewOrderDetails() {
        System.out.println("\n--- View Order Details ---");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        Optional<Order> orderOpt = facade.findOrderById(orderId);
        if (orderOpt.isPresent()) {
            System.out.println(orderOpt.get());
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }
    
    private static void updateOrderStatus() {
        System.out.println("\n--- Update Order Status ---");
        System.out.print("Enter Order ID to update: ");
        String orderId = scanner.nextLine();
        Optional<Order> orderOpt = facade.findOrderById(orderId);

        if (!orderOpt.isPresent()) {
            System.out.println("Order with ID " + orderId + " not found.");
            return;
        }
        System.out.println("Current status: " + orderOpt.get().getStatus().getDisplayName());

        System.out.println("Select new status:");
        OrderStatus[] statuses = OrderStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i].getDisplayName());
        }
        System.out.println("0. Cancel");
        int choice = readIntInput("Enter choice: ");

        if (choice > 0 && choice <= statuses.length) {
            OrderStatus newStatus = statuses[choice - 1];
            if (facade.updateOrderStatus(orderId, newStatus)) {
                System.out.println("Order status updated to " + newStatus.getDisplayName());
            } else {
                System.out.println("Failed to update order status."); // Should not happen if order was found
            }
        } else if (choice != 0) {
            System.out.println("Invalid status choice.");
        }
    }

    private static void viewAllOrders() {
        System.out.println("\n--- All Orders ---");
        List<Order> orders = facade.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> System.out.println(order + "\n--------------------"));
        }
    }
    
    private static void viewOrdersForCustomer() {
        System.out.println("\n--- View Orders for Customer ---");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        Optional<Customer> customerOpt = facade.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }
        
        List<Order> orders = facade.getOrdersByCustomer(customerId);
        if (orders.isEmpty()) {
            System.out.println("No orders found for customer " + customerOpt.get().getName() + " (ID: " + customerId + ").");
        } else {
            System.out.println("Orders for " + customerOpt.get().getName() + " (ID: " + customerId + "):");
            orders.forEach(order -> System.out.println(order + "\n--------------------"));
        }
    }

    // --- Reporting ---
    private static void viewReports() {
        System.out.println("\n--- Reports ---");
        System.out.println("1. Total Number of Customers");
        System.out.println("2. Total Number of Staff");
        System.out.println("3. Total Number of Orders");
        System.out.println("4. Total Revenue (Sum of all completed orders - Basic)");
        System.out.println("0. Back to Main Menu");
        int choice = readIntInput("Enter your choice: ");

        switch(choice) {
            case 1:
                System.out.println("Total Customers: " + facade.getAllCustomers().size());
                break;
            case 2:
                System.out.println("Total Staff: " + facade.getAllStaff().size());
                break;
            case 3:
                System.out.println("Total Orders: " + facade.getAllOrders().size());
                break;
            case 4:
                double totalRevenue = facade.getAllOrders().stream()
                                        .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                                        .mapToDouble(Order::getTotalAmount)
                                        .sum();
                System.out.printf("Total Revenue from Completed Orders: $%.2f%n", totalRevenue);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid report choice.");
        }
    }


    // --- Utility Methods ---
    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        return input;
    }
    
    @SuppressWarnings("unused") // Potentially useful for future date inputs
    private static LocalDate readDateInput(String prompt) {
        System.out.print(prompt + " (YYYY-MM-DD): ");
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date format. Please use YYYY-MM-DD: ");
            }
        }
    }
}