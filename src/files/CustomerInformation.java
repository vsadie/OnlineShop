package files;

import java.io.*;
import java.util.ArrayList;

import customer.Customer;

/**
 *
 * This class holds the method for reading and writing from and to the
 * customerInformation txt file
 * 
 */

public class CustomerInformation {

	/* Location path of the file to read/write */
	private final static String FILENAME = "src/files/customerInformation.txt";

	/* Method to read the file */
	public static ArrayList<Customer> read() throws IOException {

		ArrayList<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		int counter = 1;

		File fileObject = new File(FILENAME);
		FileReader fReader = null;
		BufferedReader bReader = null;

		try {
			fReader = new FileReader(fileObject);
			bReader = new BufferedReader(fReader);
			String info;
			while ((info = bReader.readLine()) != null) {

				if (info.isBlank())
					break;
				switch (counter) {
				case 1:
					customer = new Customer();
					customer.setName(info.trim());
					break;
				case 2:
					customer.setUsername(info.trim());
					break;
				case 3:
					customer.setAddress(info.trim());
					break;
				case 4:
					customer.setNumber(info.trim());
					break;
				case 5:
					customer.setCoins(Float.parseFloat(info.trim()));
					customers.add(customer);
					counter = 1;
					continue;
				}

				counter++;

			}
		} catch (FileNotFoundException e) {
			System.out.println("\nError opening file \"" + FILENAME + "\"");
		}
		bReader.close();

		return customers;

	}

	/* Method to write on the file */
	public static void write(ArrayList<Customer> customers) {
		try {
			File file = new File(FILENAME);
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			for (Customer customer : customers) {
				bWriter.write(customer.getName());
				bWriter.newLine();
				bWriter.write(customer.getUsername());
				bWriter.newLine();
				bWriter.write(customer.getAddress());
				bWriter.newLine();
				bWriter.write(customer.getNumber());
				bWriter.newLine();
				bWriter.write(Float.toString(customer.getCoins()));
				bWriter.newLine();
			}

			bWriter.flush();
			bWriter.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
