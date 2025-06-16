package FinalPrototype;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing staff-related operations.
 */
public class StaffService {
    private DataStorage dataStorage;

    public StaffService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public Staff addStaff(String name, ContactInformation contactInfo, Address address, String role) {
        Staff staffMember = new Staff(name, contactInfo, address, role);
        dataStorage.addStaff(staffMember);
        return staffMember;
    }

    public List<Staff> getAllStaff() {
        return dataStorage.getAllStaff();
    }

    public Optional<Staff> findStaffById(String staffId) {
        return dataStorage.findStaffById(staffId);
    }
}