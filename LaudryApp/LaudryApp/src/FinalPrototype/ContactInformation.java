package FinalPrototype;

public class ContactInformation {

	private String phoneNumber;
    private String email;

    public ContactInformation(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Phone: " + phoneNumber + (email != null && !email.isEmpty() ? ", Email: " + email : "");
    }
}
