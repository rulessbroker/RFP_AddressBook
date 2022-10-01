package com.bridgelabz.addressbook;

public class Contact {
	public String firstName;
	public String lastName;
	public long phoneNumber;
	public String address;
	public String city;
	public String state;
	public int zip;
	public String email;
	
//		Contact(String firstName, String lastName, long phoneNumber,
//				String address, String city, String state, int zip, String email){
//			this.firstName = firstName;
//			this.lastName = lastName;
//			this.phoneNumber = phoneNumber;
//			this.address = address;
//			this.city = city;
//			this.state = state;
//			this.zip = zip;
//			this.email = email;
//		}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", email=" + email
				+ "]";
	}
	
}
