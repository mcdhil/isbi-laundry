package protype2;

public enum MachineStatus {
	AVAILABLE("Available"),             // Machine is free and operational
    BUSY("Busy"),                       // Machine is currently in use
    OUT_OF_SERVICE("Out of Service"),   // Machine is broken or not usable
    MAINTENANCE("Under Maintenance"),   // Machine is temporarily down for maintenance
    CLEANING("Cleaning Cycle");         // Machine is undergoing a cleaning cycle

    private final String displayName;

    MachineStatus(String displayName) {
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
