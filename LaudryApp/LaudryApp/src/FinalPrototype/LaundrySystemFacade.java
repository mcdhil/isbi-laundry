package FinalPrototype;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Facade class to simplify interactions with the laundry system's services.
 * The main application UI will primarily interact with this facade.
 */
public class LaundrySystemFacade {
    private final CustomerService customerService;
    private final StaffService staffService;
    private final OrderService orderService;
    // private final PricingService pricingService; // Not directly exposed, used by OrderService
    private final DataStorage dataStorage; // For direct access to garment catalog if needed by UI

    public LaundrySystemFacade() {
        this.dataStorage = new DataStorage();
        // PricingService needs DataStorage to access garment prices
        PricingService pricingServiceInstance = new PricingService(this.dataStorage); 
        this.customerService = new CustomerService(this.dataStorage);
        this.staffService = new StaffService(this.dataStorage);
        this.orderService = new OrderService(this.dataStorage, pricingServiceInstance);
    }

    // Customer operations
    public Customer addCustomer(String name, String phone, String email, String street, String city, String state, String zip) {
        ContactInformation contact = new ContactInformation(phone, email);
        Address address = new Address(street, city, state, zip);
        return customerService.addCustomer(name, contact, address);
    }
    public List<Customer> getAllCustomers() { return customerService.getAllCustomers(); }
    public Optional<Customer> findCustomerById(String id) { return customerService.findCustomerById(id); }

    // Staff operations
    public Staff addStaff(String name, String phone, String email, String street, String city, String state, String zip, String role) {
        ContactInformation contact = new ContactInformation(phone, email);
        Address address = new Address(street, city, state, zip);
        return staffService.addStaff(name, contact, address, role);
    }
    public List<Staff> getAllStaff() { return staffService.getAllStaff(); }
    public Optional<Staff> findStaffById(String id) { return staffService.findStaffById(id); }

    // Order operations
    public Order createNewOrder(String customerId) {
        Optional<Customer> customerOpt = customerService.findCustomerById(customerId);
        if (customerOpt.isPresent()) {
            return orderService.createOrder(customerOpt.get());
        }
        System.err.println("Customer with ID " + customerId + " not found. Cannot create order.");
        return null;
    }

    public boolean addItemToOrder(Order order, String garmentName, ServiceType serviceType, int quantity) {
        if (order == null) {
            System.err.println("Order is null. Cannot add item.");
            return false;
        }
        Optional<Garment> garmentOpt = orderService.getGarmentByName(garmentName);
        if (garmentOpt.isPresent()) {
            return orderService.addItemToOrder(order, garmentOpt.get(), serviceType, quantity);
        }
        System.err.println("Garment '" + garmentName + "' not found in catalog.");
        return false;
    }
    
    public void finalizeAndSaveOrder(Order order) {
        orderService.finalizeOrder(order);
    }

    public List<Order> getAllOrders() { return orderService.getAllOrders(); }
    public Optional<Order> findOrderById(String id) { return orderService.findOrderById(id); }
    public List<Order> getOrdersByCustomer(String customerId) { return orderService.getOrdersByCustomerId(customerId); }
    public boolean updateOrderStatus(String orderId, OrderStatus status) { return orderService.updateOrderStatus(orderId, status); }
    public boolean updateOrderCompletionDate(String orderId, LocalDate date) { return orderService.updateOrderEstimatedCompletionDate(orderId, date); }


    // Garment & Service Info
    public List<Garment> getAvailableGarmentsFromCatalog() {
        return dataStorage.getAllGarments();
    }
    public ServiceType[] getAvailableServiceTypes() {
        return ServiceType.values();
    }
     public Optional<Garment> getGarmentByName(String name) {
        return dataStorage.getGarmentByName(name);
    }
}