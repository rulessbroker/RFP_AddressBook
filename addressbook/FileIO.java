package com.bridgelabz.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JsonObject;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonString;
import com.opencsv.CSVWriter;

public class FileIO {
	static final String FILE_PATH = System.getProperty("user.dir").concat("//Files//");
	static Scanner sc = new Scanner(System.in);

	public enum FileType {
		TXT, CSV
	}

	static boolean read(File filePath) throws FileNotFoundException {
		for (File file : filePath.listFiles()) {
			System.out.println("AddressBook name: " + file.getName());
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		}
		return true;
	}

	static boolean writeTxtFile(ArrayList<Contact> contacts, String addressBookName) throws IOException {
		System.out.println("Enter file name - ");
		addressBookName = sc.next();
		File file = new File("Files//text.txt");
		boolean isCreated = file.createNewFile();
		if (!isCreated) {
			file.delete();
			file.createNewFile();
		}
		System.out.println("file created");
		FileWriter fileWriter = new FileWriter(file);
		String data = "";
		for (Contact contactPerson : contacts) {
			data = data.concat(contactPerson.toString()).concat("\n");
		}
		System.out.println(data);
		fileWriter.write(data);
		fileWriter.close();
		return true;
	}

	public static boolean writeCsvFile(ArrayList<Contact> contacts, String addressBookName) throws IOException {
		System.out.println("Enter file name - ");
		addressBookName = sc.next();
		File file = new File("Files//csv.csv");
		boolean isCreated = file.createNewFile();
		if (!isCreated) {
			file.delete();
			file.createNewFile();
		}
		System.out.println("file created");
		FileWriter fileWriter = new FileWriter(file);
		try (CSVWriter csvWriter = new CSVWriter(fileWriter)) {
			List<String[]> data = new ArrayList<>();
			for (Contact person : contacts) {
				String[] contactData = new String[] { person.getFirstName(), person.getLastName(), person.getAddress(),
						person.getCity(), person.getState(), String.valueOf(person.getZip()),
						String.valueOf(person.getPhoneNumber()), person.getEmail() };
				data.add(contactData);
			}
			csvWriter.writeAll(data);
		}
		fileWriter.close();
		return true;
	}

	public static void writeJsonFile(ArrayList<Contact> contacts, String addressBookName) throws IOException {
		File file = new File("Files//json.json");
		boolean isCreated = file.createNewFile();
		if (!isCreated) {
			file.delete();
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		Gson gson = new Gson();
		String data = "";
		for (Contact person : contacts) {
			data = data.concat(gson.toJson(person) + "\n");
		}
		writer.write(data);
		writer.close();
	}
}
