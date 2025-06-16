package FinalPrototype;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Order> orderHistory;

    public Customer(String name, ContactInformation contactInfo, Address address) {
        super(IdGenerator.generateCustomerId(), name, contactInfo, address);
        this.orderHistory = new ArrayList<>();
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order) {
        this.orderHistory.add(order);
    }

    @Override
    public String toString() {
        return "Customer [" + super.toString() + ", Orders: " + orderHistory.size() + "]";
    }
}