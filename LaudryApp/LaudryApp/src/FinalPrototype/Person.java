package FinalPrototype;

import java.util.Objects;

public abstract class Person {

	private String id;
    private String name;
    private ContactInformation contactInfo;
    private Address address;

    public Person(String id, String name, ContactInformation contactInfo, Address address) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ContactInformation getContactInfo() { return contactInfo; }
    public void setContactInfo(ContactInformation contactInfo) { this.contactInfo = contactInfo; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Contact: " + contactInfo + ", Address: " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
