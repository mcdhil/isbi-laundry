package protype2;

import java.util.UUID;

public class ServiceType {
	private String serviceId;
    private String serviceName;
    private double basePrice;
    private String description;
    private int estimatedDurationMinutes; // Estimated time this service takes

    /**
     * Constructor for ServiceType.
     * @param serviceName The name of the service (e.g., "Standard Wash").
     * @param basePrice The base price for this service.
     * @param description A brief description of the service.
     * @param estimatedDurationMinutes Estimated duration in minutes.
     */
    public ServiceType(String serviceName, double basePrice, String description, int estimatedDurationMinutes) {
        this.serviceId = UUID.randomUUID().toString();
        this.serviceName = serviceName;
        this.basePrice = basePrice;
        this.description = description;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    // Getters
    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    // Setters
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice >= 0) {
            this.basePrice = basePrice;
        } else {
            System.out.println("Base price cannot be negative.");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstimatedDurationMinutes(int estimatedDurationMinutes) {
        if (estimatedDurationMinutes >= 0) {
            this.estimatedDurationMinutes = estimatedDurationMinutes;
        } else {
            System.out.println("Estimated duration cannot be negative.");
        }
    }

    /**
     * Provides details about the service.
     * @return A string containing service details.
     */
    public String getServiceDetails() {
        return "Service: " + serviceName + " (ID: " + serviceId + ")" +
               "\nPrice: $" + String.format("%.2f", basePrice) +
               "\nDescription: " + description +
               "\nEstimated Duration: " + estimatedDurationMinutes + " minutes";
    }

    @Override
    public String toString() {
        return serviceName + " ($" + String.format("%.2f", basePrice) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceType that = (ServiceType) o;
        return serviceId.equals(that.serviceId);
    }

    @Override
    public int hashCode() {
        return serviceId.hashCode();
    }
}
