package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {

		System.out.println("\n***** WELCOME TO ADDRESS BOOK ****** \n");

		AddressBook addressBook = new AddressBook();
		int choice = 0;
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("Enter your choice --->\n");
			System.out.println("1.Add Contact\n" + "2.Edit Contact\n" +"3 Show Contact\n" + "4.Delete Contact\n" + 
								"5.Add New Address Book\n"+ "6.Exit\n");
			
			choice = input.nextInt();

			switch (choice) {
			case 1:
				addressBook.addContact();
				break;
			case 2:
				addressBook.editContact();
				break;
			case 3:
				addressBook.showContacts((ArrayList) addressBook.people);
				System.out.println(addressBook.people);
				break;
			case 4:
				addressBook.deleteContact();
				break;
			case 5:
				addressBook.addNewAddressBook();
				break;
			
			case 6:
				System.out.println("* Successfully Exit *");
				break;
			default:
				System.out.println("Enter Valid Input !!!\n");
			}

		} while (choice != 6);

	}
}
