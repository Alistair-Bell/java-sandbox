/*
 * Adapted from https://github.com/juliehead/FileHandling.
 * This code is not licensed under the same as `license.txt` in the root of the project, visit the original codebase.
 * What has been improved?
	* Every time the program is run, overwrite the contents of the text file.
	* Get the user to type a list of 'n' students.
	* Stores the names in a text file.
	* Prints out names which only contain an 'e' or 'a'.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class adaptation_program {

    private static File myObj = new File("students.txt");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CreateFile();
		WriteToFile();
		ReadFile();
    }

    public static void CreateFile() {
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

	public static int GetInt(String message) {
		int returnValue;
		do {
			try {
				System.out.println(message);
				returnValue = scanner.nextInt();
				return returnValue;
			} catch (Exception e) {
				System.out.printf("Exception thrown %s\n", e.toString());
			}
			System.out.println("Expected an integer input!");
			scanner.nextLine();
		} while (true);
	}
	public static String[] GetNames() {
		String[] names = new String[GetInt("How may students do you want?")];
		/* Get our student names. */
		for (int i = 0; i < names.length; ++i) {
			names[i] = scanner.next();
		}
		return names;
	}
    public static void WriteToFile() {
		try {
			FileWriter writer = new FileWriter(myObj.getName(), false);
			for (String s : GetNames())
				writer.write(s + "\n");
			writer.close();
			System.out.println("Succesfully wrote to the file.");
		} catch (Exception e) {
			System.out.printf("Exception thrown %s.\n", e.toString());
		}
    }

    public static void ReadFile() {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
				/* Check that the names only has an 'e' or 'a' */
				if (data.indexOf('a') != -1 || data.indexOf('e') != -1) {
					System.out.printf("Name has 'e' or 'a' -> %s\n", data);
				}
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void DeleteFile() {
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}


