package testing;

public enum ServiceType {
	WASH_FOLD(5.0), // Price per KG or abstract unit
    DRY_CLEAN(10.0), // Price per item
    IRONING(2.0);    // Price per item

    private final double price;
    ServiceType(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
