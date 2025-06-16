package FinalPrototype;

public enum ServiceType {
    WASH_AND_FOLD("Wash and Fold"),
    DRY_CLEANING("Dry Cleaning"),
    IRONING_ONLY("Ironing Only"),
    SPECIAL_CARE("Special Care Wash");

    private final String displayName;

    ServiceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
