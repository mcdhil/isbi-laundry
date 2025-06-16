package protype2;

public class Address {
	private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    
    public Address(String street, String city, String state, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    // Setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the full address as a formatted string.
     
     */
    public String getFullAddress() {
        return street + ", " + city + ", " + state + " " + zipCode + ", " + country;
    }

    /**
     * Placeholder for address validation logic.
     * @return true if the address is considered valid, false otherwise.
     */
    public boolean validateAddress() {
        // Basic validation: check if fields are not empty
        return street != null && !street.isEmpty() &&
               city != null && !city.isEmpty() &&
               zipCode != null && !zipCode.isEmpty();
        
        // More complex validation could involve checking against a database or API
    }

    @Override
    public String toString() {
        return getFullAddress();
    }
}
