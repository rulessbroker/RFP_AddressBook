package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
	Contact person = new Contact();
	 public List<Contact> people = new ArrayList<Contact>();
	Scanner sc = new Scanner(System.in);
	
	public void addContact() {
		
		System.out.println("First Name :");
	    person.firstName = sc.nextLine();
	    
	    System.out.println("Last Name :");
	    person.lastName = sc.nextLine();
	    
       System.out.println("Address :");
       person.address = sc.nextLine();

       System.out.println("City :");
       person.city = sc.nextLine();

       System.out.println("State :");
       person.state = sc.nextLine();

       System.out.println("Zip :");
       person.zip = sc.nextInt();

       System.out.println("Phone Number :");
       person.phoneNumber = sc.nextLong();
       
       System.out.println("Email :");
       person.email = sc.next();
   
       people.add(person);
	}
}
