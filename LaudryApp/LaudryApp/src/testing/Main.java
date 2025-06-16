package testing;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		        Scanner scanner = new Scanner(System.in);
		        LaundryService laundryService = new LaundryService();
		        boolean running = true;

		        System.out.println("Welcome to the Simple Laundry System!");

		        while (running) {
		            System.out.println("\n--- Main Menu ---");
		            System.out.println("1. Add New Customer");
		            System.out.println("2. Place New Order");
		            System.out.println("3. View Order Status/Details");
		            System.out.println("4. Update Order Status");
		            System.out.println("5. View All Orders");
		            System.out.println("6. View All Customers");
		            System.out.println("7. Exit");
		            System.out.print("Enter your choice: ");

		            String choice = scanner.nextLine();

		            switch (choice) {
		                case "1":
		                    laundryService.addCustomer();
		                    break;
		                case "2":
		                    laundryService.placeNewOrder();
		                    break;
		                case "3":
		                    laundryService.viewOrderStatus();
		                    break;
		                case "4":
		                    laundryService.updateOrderStatus();
		                    break;
		                case "5":
		                    laundryService.viewAllOrders();
		                    break;
		                case "6":
		                    laundryService.viewAllCustomers();
		                    break;
		                case "7":
		                    running = false;
		                    System.out.println("Thank you for using the Laundry System. Goodbye!");
		                    break;
		                default:
		                    System.out.println("Invalid choice. Please try again.");
		            }
		        }
		        scanner.close();
		    }
	}


