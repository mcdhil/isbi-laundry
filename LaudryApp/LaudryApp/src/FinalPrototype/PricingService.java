package FinalPrototype;

public class PricingService {
	private DataStorage dataStorage;

    public PricingService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public double calculateOrderItemPrice(Garment garment, ServiceType serviceType, int quantity) {
        if (garment == null) {
            System.err.println("Error: Garment cannot be null for price calculation.");
            return 0.0;
        }
        double basePrice = garment.getBasePrice(serviceType);
        if (basePrice == 0.0 && serviceType != null) {
             // Check if the service is valid for the garment but just has a 0 price set
            boolean serviceOffered = garment.getAllBasePrices().containsKey(serviceType);
            if (!serviceOffered) {
                 System.err.println("Warning: Service '" + serviceType.getDisplayName() + 
                                   "' is not offered for garment '" + garment.getName() + "'. Price set to 0.");
            } else {
                System.err.println("Warning: Base price for " + garment.getName() + " with service " + serviceType.getDisplayName() + " is $0.00.");
            }
        }
        return basePrice * quantity;
    }
}
