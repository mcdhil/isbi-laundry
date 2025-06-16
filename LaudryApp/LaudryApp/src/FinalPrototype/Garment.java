package FinalPrototype;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a type of garment and its base pricing for different services.
 */
public class Garment {
    private String name; // e.g., "Shirt", "Pants", "Jacket"
    private Map<ServiceType, Double> basePrices; // Price per unit for each service type

    public Garment(String name) {
        this.name = name;
        this.basePrices = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setBasePrice(ServiceType serviceType, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        basePrices.put(serviceType, price);
    }

    public double getBasePrice(ServiceType serviceType) {
        return basePrices.getOrDefault(serviceType, 0.0);
    }
    
    public Map<ServiceType, Double> getAllBasePrices() {
        return new HashMap<>(basePrices); // Return a copy
    }

    @Override
    public String toString() {
        return name + " (Prices: " + basePrices + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garment garment = (Garment) o;
        return Objects.equals(name, garment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
