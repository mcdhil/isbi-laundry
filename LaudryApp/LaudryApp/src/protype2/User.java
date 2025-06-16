package protype2;
import java.util.UUID;

public abstract class User {
	 private String userId;
	 private String name;
	 private String phoneNumber;
	 private String email;
	 
	public User(String userId, String name, String phoneNumber, String email) {
		super();
		this.userId = "ID" + UUID.randomUUID().toString().substring(0,4).toUpperCase();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	 public User(String name, String phoneNumber, String email) {
		 this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
	}

	public String getUserId() {
	        return userId;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public String getEmail() {
	        return email;
	    }

	    // Setters
	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public abstract void viewProfile();

	    
	    public abstract void updateProfile();

	    @Override
	    public String toString() {
	        return "User ID: " + userId + ", Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
	    }
	}	 

