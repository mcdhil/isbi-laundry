package FinalPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Simple in-memory data storage for the application.
 * In a real application, this would be replaced by a database.
 */
public class DataStorage {
    private final List<Customer> customers = new ArrayList<>();
    private final List<Staff> staffMembers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final Map<String, Garment> garmentCatalog = new HashMap<>(); // Key is garment name

    public DataStorage() {
        // Pre-populate some garments
        initializeGarmentCatalog();
    }

    private void initializeGarmentCatalog() {
        Garment shirt = new Garment("Shirt");
        shirt.setBasePrice(ServiceType.WASH_AND_FOLD, 2.50);
        shirt.setBasePrice(ServiceType.DRY_CLEANING, 5.00);
        shirt.setBasePrice(ServiceType.IRONING_ONLY, 1.50);
        garmentCatalog.put(shirt.getName().toLowerCase(), shirt);

        Garment pants = new Garment("Pants");
        pants.setBasePrice(ServiceType.WASH_AND_FOLD, 3.00);
        pants.setBasePrice(ServiceType.DRY_CLEANING, 6.00);
        pants.setBasePrice(ServiceType.IRONING_ONLY, 2.00);
        garmentCatalog.put(pants.getName().toLowerCase(), pants);

        Garment jacket = new Garment("Jacket");
        jacket.setBasePrice(ServiceType.DRY_CLEANING, 10.00);
        jacket.setBasePrice(ServiceType.SPECIAL_CARE, 12.00);
        garmentCatalog.put(jacket.getName().toLowerCase(), jacket);
        
        Garment dress = new Garment("Dress");
        dress.setBasePrice(ServiceType.DRY_CLEANING, 8.00);
        dress.setBasePrice(ServiceType.SPECIAL_CARE, 10.00);
        dress.setBasePrice(ServiceType.IRONING_ONLY, 3.00);
        garmentCatalog.put(dress.getName().toLowerCase(), dress);
    }

    // Customer methods
    public void addCustomer(Customer customer) { customers.add(customer); }
    public List<Customer> getAllCustomers() { return new ArrayList<>(customers); }
    public Optional<Customer> findCustomerById(String customerId) {
        return customers.stream().filter(c -> c.getId().equals(customerId)).findFirst();
    }

    // Staff methods
    public void addStaff(Staff staff) { staffMembers.add(staff); }
    public List<Staff> getAllStaff() { return new ArrayList<>(staffMembers); }
    public Optional<Staff> findStaffById(String staffId) {
        return staffMembers.stream().filter(s -> s.getId().equals(staffId)).findFirst();
    }

    // Order methods
    public void addOrder(Order order) { 
        orders.add(order); 
        // Also add to customer's history
        order.getCustomer().addOrderToHistory(order);
    }
    public List<Order> getAllOrders() { return new ArrayList<>(orders); }
    public Optional<Order> findOrderById(String orderId) {
        return orders.stream().filter(o -> o.getOrderId().equals(orderId)).findFirst();
    }
    public List<Order> findOrdersByCustomerId(String customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for(Order order : orders) {
            if(order.getCustomer().getId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }


    // Garment Catalog methods
    public Optional<Garment> getGarmentByName(String name) {
        return Optional.ofNullable(garmentCatalog.get(name.toLowerCase()));
    }
    public List<Garment> getAllGarments() {
        return new ArrayList<>(garmentCatalog.values());
    }
     public void addGarmentToCatalog(Garment garment) {
        if (garment != null && garment.getName() != null && !garment.getName().trim().isEmpty()) {
            garmentCatalog.put(garment.getName().toLowerCase(), garment);
        }
    }
}