package files;

import java.io.*;
import java.util.HashMap;

/**
 *
 * This class holds the method for reading and writing from and to the
 * loginCredentials txt file
 * 
 */

public class LoginCredentials {

	/* Location path of the file to read/write */
	private final static String FILENAME = "src/files/loginCredentials.txt";

	/* Method to read the file */
	public static HashMap<String, String> read() throws IOException {

		HashMap<String, String> users = new HashMap<String, String>();

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

				String[] infos = info.split(":");
				users.put(infos[0].trim(), infos[1].trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("\nError opening file \"" + FILENAME + "\"");
		}
		bReader.close();

		return users;

	}

	/* Method to write on the file */
	public static void write(HashMap<String, String> users) {
		try {
			File file = new File(FILENAME);
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			for (HashMap.Entry<String, String> entry : users.entrySet()) {
				bWriter.write(entry.getKey() + ":" + entry.getValue());
				bWriter.newLine();
			}

			bWriter.flush();
			bWriter.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
