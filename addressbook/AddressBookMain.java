package com.bridgelabz.addressbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) throws IOException, SQLException {

		System.out.println("\n***** WELCOME TO ADDRESS BOOK ****** \n");

		AddressBook addressBook = new AddressBook();
		int choice = 0;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("Enter your choice --->\n");
			System.out.println("1.Add Contact\n"
					+ "2.Edit Contact\n" + "3.Show Contact\n" + "4.Delete Contact\n"
					+ "5.Add New Addres Book\n" + "6.Select Address Book\n" 
					+ "7.Search Person\n" + "8.Number of Contacts\n" + "9.Sort Contacts\n" + "10.Write Address Book\n" + "11.Read Address Book\n" + "12.Exit\n");

			choice = input.nextInt();

			switch (choice) {
			case 1:
				 Contact contact = addressBook.createContact();
                 addressBook.addContact(contact);
				break;
			case 2:
				addressBook.editContact();
				break;
			case 3:
				 addressBook.viewContacts();
				 break;
			case 4:
				addressBook.deleteContact();
				break;
			case 5:
				addressBook.addNewAddressBook();
				break;
			case 6:
				addressBook.selectAddressBook();
				break;
			case 7:
				addressBook.searchContact();
				break;
			case 8:
				addressBook.showContactCount();
				break;
			case 9:
				addressBook.sortContact();
				break;
			case 10:
				addressBook.writeAddressBook();
				break;
			case 11:
				addressBook.readAddressBook();
				break;
			case 12:
				System.out.println("* Successfully Exit *");
				break;
			default:
				System.out.println("Enter Valid Input !!!\n");
			}

		} while (choice != 12);

	}
}
