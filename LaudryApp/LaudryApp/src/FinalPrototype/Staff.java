package FinalPrototype;

import java.time.LocalDate;

/**
 * Represents a staff member.
 */
public class Staff extends Person {
    private String role;
    private LocalDate hireDate;

    public Staff(String name, ContactInformation contactInfo, Address address, String role) {
        super(IdGenerator.generateStaffId(), name, contactInfo, address);
        this.role = role;
        this.hireDate = LocalDate.now();
    }

    // Getters and Setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDate getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return "Staff [" + super.toString() + ", Role: " + role + ", Hired: " + hireDate + "]";
    }
}