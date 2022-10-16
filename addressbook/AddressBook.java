package com.bridgelabz.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {

	public static List<Contact> people = new ArrayList<Contact>();
	static HashMap<String, ArrayList<Contact>> addressBookList = new HashMap<>();
	static String currentAddressBookName;
	static ArrayList<Contact> currentAddressBook;
	static HashMap<String, ArrayList<Contact>> cityContactList = new HashMap<>();
	static HashMap<String, ArrayList<Contact>> stateContactList = new HashMap<>();
	Scanner sc = new Scanner(System.in);

	Contact createContact() {
		System.out.println("Enter first name");
		String firstName = sc.next();
		System.out.println("Enter last name");
		String lastName = sc.next();
		System.out.println("Enter address");
		String address = sc.next();
		System.out.println("Enter city");
		String city = sc.next();
		System.out.println("Enter state");
		String state = sc.next();
		System.out.println("Enter ZipCode");
		int zipCode = sc.nextInt();
		System.out.println("Enter phoneNumber");
		long phoneNumber = sc.nextLong();
		System.out.println("Enter Email");
		String email = sc.next();

		Contact person = new Contact(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
		System.out.println("created new contact");
		return person;
	}

	void addContact(Contact person) throws SQLException {
		boolean isDuplicate = checkDuplicateContact(person);
		if (isDuplicate) {
			System.out.println("Contact name already exists");
		} else {
			JDBCService.addContact(person, currentAddressBookName);
			currentAddressBook.add(person);
			System.out.println("contact added to AddressBook " + currentAddressBookName);
			System.out.println(person);
		}
	}

	void editContact() throws SQLException {
		System.out.println("enter name to edit contact");
		JDBCService.updateContact(sc.next());
	}

	void deleteContact() {
		System.out.println("enter name to delete contact");
		String name = sc.next();
		for (Contact contact : currentAddressBook) {
			if (contact.getFirstName().equalsIgnoreCase(name)) {
				System.out.println("contact found:");
				System.out.println(contact);
				System.out.println("confirm to delete (y/n)");
				if (sc.next().equalsIgnoreCase("y")) {
					currentAddressBook.remove(contact);
					System.out.println("contact deleted");
				}
				return;
			}
		}
		System.out.println("Opps... contact not found");
	}

	void addNewAddressBook() {
		System.out.println("Enter name for AddressBook");
		String AddressBookName = sc.next();
		ArrayList<Contact> AddressBook = new ArrayList<Contact>();
		addressBookList.put(AddressBookName, AddressBook);
		System.out.println("new AddressBook created");
		currentAddressBook = addressBookList.get(AddressBookName);
		currentAddressBookName = AddressBookName;
	}

	void selectAddressBook() {
		System.out.println(addressBookList.keySet());
		System.out.println("enter name of address book");
		String addressBookName = sc.next();

		for (String key : addressBookList.keySet()) {
			if (key.equalsIgnoreCase(addressBookName)) {
				currentAddressBook = addressBookList.get(key);
				currentAddressBookName = key;
			}
		}
		System.out.println("current AddressBook is " + currentAddressBookName);

	}

	public void initializeCityAndStateContactList() {
		for (String key : addressBookList.keySet()) {
			for (Contact person : addressBookList.get(key)) {
				String city = person.getCity();
				if (cityContactList.containsKey(city)) {
					cityContactList.get(city).add(person);
				} else {
					ArrayList<Contact> list = new ArrayList<>();
					list.add(person);
					cityContactList.put(city, list);
				}

				String state = person.getState();
				if (stateContactList.containsKey(state)) {
					stateContactList.get(state).add(person);
				} else {
					ArrayList<Contact> list = new ArrayList<>();
					list.add(person);
					stateContactList.put(state, list);
				}
			}
		}
	}

	void viewContacts() {
		initializeCityAndStateContactList();
		System.out.println("*****************************\n1.View by City \n2.View by State");
		switch (sc.nextInt()) {
		case 1:
			viewContactByCity();
			break;
		case 2:
			viewContactByState();
			break;
		default:
			viewContacts();
			break;
		}
	}

	void viewContactByCity() {
		System.out.println("Enter City:");
		String city = sc.next();
		for (String key : cityContactList.keySet()) {
			if (key.equalsIgnoreCase(city)) {
				cityContactList.get(key).forEach(person -> System.out.println(person));
			}
		}
	}

	void viewContactByState() {
		System.out.println("Enter State:");
		String state = sc.next();
		for (String key : stateContactList.keySet()) {
			if (key.equalsIgnoreCase(state)) {
				stateContactList.get(state).forEach(person -> System.out.println(person));
			}
		}
	}

	boolean checkDuplicateContact(Contact newPerson) {
		return currentAddressBook.stream()
				.anyMatch((person) -> person.getFirstName().equalsIgnoreCase(newPerson.getFirstName()));
	}

	void searchContact() {
		System.out.println("1.Search by City \n2.Search by State");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("Enter city :");
			searchByCity(sc.next());
			break;
		case 2:
			System.out.println("Enter State :");
			searchByState(sc.next());
			break;
		default:
			searchContact();
			break;
		}
	}

	void searchByCity(String city) {
		System.out.println("Search Result: ");
		for (String addressBookName : addressBookList.keySet()) {
			addressBookList.get(addressBookName).forEach((person) -> {
				if (person.getCity().equalsIgnoreCase(city))
					System.out.println(person);
			});
		}
	}

	void searchByState(String state) {
		System.out.println("Search Result: ");
		for (String addressBookName : addressBookList.keySet()) {
			addressBookList.get(addressBookName).forEach((person) -> {
				if (person.getState().equalsIgnoreCase(state))
					System.out.println(person);
			});
		}
	}

	void showContactCount() throws SQLException {
		System.out.println("1.Count of City \n2.Count of State");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("Enter city :");
			String city = sc.next();
			System.out.println("Count: " + JDBCService.getContactCountByCity(city));
			break;
		case 2:
			System.out.println("Enter State :");
			String state = sc.next();
			System.out.println("Count: " + JDBCService.getContactCountByState(state));
			break;
		default:
			showContactCount();
			break;
		}
	}

	void sortContact() {
		List<Contact> allContacts = getAllContacts();
		List<Contact> sortedContacts;

		System.out.println("1.Sort By Name \n2.Sort By CIty \n3.Sort By State \n4.Sort By Zipcode \n5.back");
		switch (sc.nextInt()) {
		case 1:
			sortedContacts = allContacts.stream().sorted((x, y) -> x.getFirstName().compareTo(y.getFirstName()))
					.collect(Collectors.toList());
			sortedContacts.forEach(x -> System.out.println(x));
			break;
		case 2:
			sortedContacts = allContacts.stream().sorted((x, y) -> x.getCity().compareTo(y.getCity()))
					.collect(Collectors.toList());
			sortedContacts.forEach(x -> System.out.println(x));
			break;
		case 3:
			sortedContacts = allContacts.stream().sorted((x, y) -> x.getState().compareTo(y.getState()))
					.collect(Collectors.toList());
			sortedContacts.forEach(x -> System.out.println(x));
			break;
		case 4:
			sortedContacts = allContacts.stream().sorted((x, y) -> Integer.compare(x.getZip(), y.getZip()))
					.collect(Collectors.toList());
			sortedContacts.forEach(x -> System.out.println(x));
			break;
		case 5:
			break;
		default:
			sortContact();
			break;
		}
	}

	List<Contact> getAllContacts() {
		List<Contact> allContacts = new ArrayList<>();
		for (String key : addressBookList.keySet()) {
			allContacts.addAll(addressBookList.get(key));
		}
		return allContacts;
	}

	void readAddressBook() throws FileNotFoundException, SQLException {
		System.out.println(
				"Select option \n1.read from txt file \n2.read from csv file\n3.read from json file\n4.Read from Database\n5.back");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			FileIO.read(new File(FileIO.FILE_PATH.concat("txt//")));
			break;
		case 2:
			FileIO.read(new File(FileIO.FILE_PATH.concat("csv//")));
			break;
		case 3:
			FileIO.read(new File(FileIO.FILE_PATH.concat("json//")));
			break;
		case 4:
			JDBCService.readAddressBooks();
		case 5:
			break;
		default:
			readAddressBook();
		}
	}

	void writeAddressBook() throws IOException {
		System.out.println("Select option \n1.Write to txt file \n2.write to csv file \n3.write to json file \n4.back");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			for (String key : addressBookList.keySet()) {
				FileIO.writeTxtFile(addressBookList.get(key), key);
			}
			break;
		case 2:
			for (String key : addressBookList.keySet()) {
				FileIO.writeCsvFile(addressBookList.get(key), key);
			}
			break;
		case 3:
			for (String key : addressBookList.keySet()) {
				FileIO.writeJsonFile(addressBookList.get(key), key);
			}
		case 4:
			break;
		default:
			writeAddressBook();
			break;

		}
	}
}