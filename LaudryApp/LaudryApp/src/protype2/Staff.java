package protype2;

public class Staff extends User{
	
	    private String staffId;
	    private String role; // e.g., "Operator", "Manager", "Delivery"
	    private String shift; // e.g., "Morning", "Evening"

	   
	    public Staff(String name, String phoneNumber, String email, String staffId, String role) {
	        super(name, phoneNumber, email);
	        this.staffId = staffId;
	        this.role = role;
	        this.shift = "N/A"; // Default shift
	    }

	    // Getters
	    public String getStaffId() {
	        return staffId;
	    }

	    public String getRole() {
	        return role;
	    }

	    public String getShift() {
	        return shift;
	    }

	    // Setters
	    public void setRole(String role) {
	        this.role = role;
	    }

	    public void setShift(String shift) {
	        this.shift = shift;
	    }

	    /**
	     * Updates the status of a given laundry order.
	     * @param order The LaundryOrder to update.
	     * @param newStatus The new OrderStatus for the order.
	     */
	    public void updateOrderStatus(LaundryOrder order, OrderStatus newStatus) {
	        if (order != null) {
	            System.out.println("Staff " + getName() + " (" + role + ") is updating order " + order.getOrderId() + " to " + newStatus);
	            order.setOrderStatus(newStatus);
	            // Potentially notify customer
	        } else {
	            System.out.println("Cannot update status for a null order.");
	        }
	    }

	    /**
	     * Assigns a machine to a specific laundry order.
	     * @param machine The Machine to assign.
	     * @param order The LaundryOrder that will use the machine.
	     */
	    public void assignMachine(Machine machine, LaundryOrder order) {
	        if (machine != null && order != null) {
	            System.out.println("Staff " + getName() + " is assigning machine " + machine.getMachineId() + " to order " + order.getOrderId());
	            machine.setCurrentOrder(order);
	            // Update machine status if necessary
	        } else {
	            System.out.println("Cannot assign machine: machine or order is null.");
	        }
	    }

	    @Override
	    public void viewProfile() {
	        System.out.println("--- Staff Profile ---");
	        System.out.println(super.toString());
	        System.out.println("Staff ID: " + staffId);
	        System.out.println("Role: " + role);
	        System.out.println("Shift: " + shift);
	    }

	    @Override
	    public void updateProfile() {
	        System.out.println("Updating profile for staff: " + getName());
	        // Logic to update staff profile details
	    }
}
