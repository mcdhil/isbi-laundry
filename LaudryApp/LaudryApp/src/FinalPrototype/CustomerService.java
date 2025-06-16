package FinalPrototype;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing customer-related operations.
 */
public class CustomerService {
    private DataStorage dataStorage;

    public CustomerService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public Customer addCustomer(String name, ContactInformation contactInfo, Address address) {
        Customer customer = new Customer(name, contactInfo, address);
        dataStorage.addCustomer(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return dataStorage.getAllCustomers();
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return dataStorage.findCustomerById(customerId);
    }
}