package com.bridgelabz.addressbook;

import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {

		System.out.println("\n***** WELCOME TO ADDRESS BOOK ****** \n");

		AddressBook addressBook = new AddressBook();
		int choice = 0;
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("Enter your choice --->\n");
			System.out.println("1.Add Contact\n" + "2.Edit Contact\n" + "3.Delete Contact\n" + "4.Exit\n");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				addressBook.addContact();
				break;
			case 2:
				addressBook.editContact();
				break;
			case 3:
				addressBook.deleteContact();
			case 4:
				System.out.println("* Successfully Exit *");
				break;
			default:
				System.out.println("Enter Valid Input !!!\n");
			}

		} while (choice != 4);

	}
}
