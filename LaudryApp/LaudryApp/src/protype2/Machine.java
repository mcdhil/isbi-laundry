package protype2;

import java.util.UUID;

public class Machine {
	private String machineId;
    private String machineType; // e.g., "Washer", "Dryer", "Ironing Station"
    private MachineStatus status;
    private double capacityKg; // Capacity in kilograms, if applicable
    private LaundryOrder currentOrder; // The order currently being processed by this machine
    private String modelNumber;
    private String locationInFacility; // e.g., "Row A, Unit 3"

    /**
     * Constructor for Machine.
     * @param machineType The type of machine (e.g., "Washer").
     * @param capacityKg The capacity of the machine in kilograms.
     * @param modelNumber The model number of the machine.
     */
    public Machine(String machineType, double capacityKg, String modelNumber) {
        this.machineId = UUID.randomUUID().toString();
        this.machineType = machineType;
        this.capacityKg = capacityKg;
        this.modelNumber = modelNumber;
        this.status = MachineStatus.AVAILABLE; // Default status
        this.currentOrder = null;
        this.locationInFacility = "N/A";
    }

    // Getters
    public String getMachineId() {
        return machineId;
    }

    public String getMachineType() {
        return machineType;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public double getCapacityKg() {
        return capacityKg;
    }

    public LaundryOrder getCurrentOrder() {
        return currentOrder;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getLocationInFacility() {
        return locationInFacility;
    }

    // Setters
    public void setStatus(MachineStatus status) {
        this.status = status;
        System.out.println("Machine " + machineId + " (" + machineType + ") status set to: " + status);
    }

    public void setCurrentOrder(LaundryOrder currentOrder) {
        this.currentOrder = currentOrder;
        if (currentOrder != null) {
            setStatus(MachineStatus.BUSY);
            System.out.println("Machine " + machineId + " assigned to order: " + currentOrder.getOrderId());
        } else {
            // If order is null, machine might be becoming available
            if (this.status == MachineStatus.BUSY) { // Only change if it was busy
                 setStatus(MachineStatus.AVAILABLE);
            }
        }
    }

    public void setLocationInFacility(String locationInFacility) {
        this.locationInFacility = locationInFacility;
    }


    /**
     * Simulates starting the machine operation.
     */
    public void startMachine() {
        if (status == MachineStatus.AVAILABLE && currentOrder != null) {
            setStatus(MachineStatus.BUSY);
            System.out.println("Machine " + machineId + " (" + machineType + ") started for order " + currentOrder.getOrderId());
            // In a real app, this might trigger timers or other processes
        } else if (currentOrder == null) {
            System.out.println("Machine " + machineId + " cannot start: No order assigned.");
        } else if (status != MachineStatus.AVAILABLE) {
            System.out.println("Machine " + machineId + " cannot start: Not available (Status: " + status + ")");
        }
    }

    /**
     * Simulates stopping the machine operation.
     */
    public void stopMachine() {
        if (status == MachineStatus.BUSY) {
            System.out.println("Machine " + machineId + " (" + machineType + ") stopped.");
            // Potentially update order status, clear currentOrder, set machine to available
            // For now, let's assume it needs manual clearing of order and status update
        } else {
            System.out.println("Machine " + machineId + " is not currently busy.");
        }
    }

    /**
     * Sets the machine to out of service.
     */
    public void setOutOfService() {
        setStatus(MachineStatus.OUT_OF_SERVICE);
        this.currentOrder = null; // Clear any current order
        System.out.println("Machine " + machineId + " is now OUT OF SERVICE.");
    }

    /**
     * Sets the machine back to available (e.g., after maintenance).
     */
    public void setAvailable() {
        if (status == MachineStatus.OUT_OF_SERVICE || status == MachineStatus.MAINTENANCE) {
            setStatus(MachineStatus.AVAILABLE);
            System.out.println("Machine " + machineId + " is now AVAILABLE.");
        } else {
            System.out.println("Machine " + machineId + " cannot be set to available from current status: " + status);
        }
    }


    @Override
    public String toString() {
        return "Machine ID: " + machineId + ", Type: " + machineType +
               ", Capacity: " + capacityKg + "kg, Status: " + status +
               (currentOrder != null ? ", Current Order: " + currentOrder.getOrderId() : "");
    }
}
