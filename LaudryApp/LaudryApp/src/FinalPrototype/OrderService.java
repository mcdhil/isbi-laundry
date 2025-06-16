package FinalPrototype;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Service for managing order-related operations.
 */
public class OrderService {
    private DataStorage dataStorage;
    private PricingService pricingService;

    public OrderService(DataStorage dataStorage, PricingService pricingService) {
        this.dataStorage = dataStorage;
        this.pricingService = pricingService;
    }

    public Order createOrder(Customer customer) {
        if (customer == null) {
            System.err.println("Cannot create order: Customer is null.");
            return null;
        }
        Order order = new Order(customer);
        // dataStorage.addOrder(order); // Order is added after items are confirmed
        return order;
    }
    
    public boolean addItemToOrder(Order order, Garment garment, ServiceType serviceType, int quantity) {
        if (order == null || garment == null || serviceType == null || quantity <= 0) {
            System.err.println("Invalid input for adding item to order.");
            return false;
        }
        double unitPrice = garment.getBasePrice(serviceType);
        if (unitPrice == 0.0) {
             boolean serviceOffered = garment.getAllBasePrices().containsKey(serviceType);
            if (!serviceOffered) {
                System.err.println("Service '" + serviceType.getDisplayName() + 
                                   "' is not offered for garment '" + garment.getName() + "'. Item not added.");
                return false;
            }
        }

        OrderItem orderItem = new OrderItem(garment, serviceType, quantity, unitPrice);
        order.addOrderItem(orderItem);
        return true;
    }

    public void finalizeOrder(Order order) {
        if (order != null && !order.getOrderItems().isEmpty()) {
            dataStorage.addOrder(order); // Now add to central storage and customer history
            System.out.println("Order " + order.getOrderId() + " finalized and saved.");
        } else if (order != null && order.getOrderItems().isEmpty()) {
            System.out.println("Order " + order.getOrderId() + " is empty. Not saving.");
        } else {
            System.err.println("Cannot finalize null order.");
        }
    }


    public boolean updateOrderStatus(String orderId, OrderStatus newStatus) {
        Optional<Order> orderOpt = dataStorage.findOrderById(orderId);
        if (orderOpt.isPresent()) {
            orderOpt.get().setStatus(newStatus);
            return true;
        }
        return false;
    }
    
    public boolean updateOrderEstimatedCompletionDate(String orderId, LocalDate newDate) {
        Optional<Order> orderOpt = dataStorage.findOrderById(orderId);
        if (orderOpt.isPresent()) {
            orderOpt.get().setEstimatedCompletionDate(newDate);
            return true;
        }
        return false;
    }

    public Optional<Order> findOrderById(String orderId) {
        return dataStorage.findOrderById(orderId);
    }

    public List<Order> getAllOrders() {
        return dataStorage.getAllOrders();
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return dataStorage.findOrdersByCustomerId(customerId);
    }
    
    public List<Garment> getAvailableGarments() {
        return dataStorage.getAllGarments();
    }

    public Optional<Garment> getGarmentByName(String name) {
        return dataStorage.getGarmentByName(name);
    }
}
