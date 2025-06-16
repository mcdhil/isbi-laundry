package testing;
import java.util.UUID;

public class Customer {
	private String customerId;
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.customerId = "CUST-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Phone: " + phoneNumber;
    }
}

