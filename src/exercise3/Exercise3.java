package exercise3;
/**Reads application names from a user-provided text file (reprompting until the file opens).
 * Then repeatedly prompts for a search phrase, prints all matching names, and writes the
 * matches to a user-specified output file (reprompting until the file can be opened).
 * Example paths: input "input-files/Applications.txt", output "output-files/results.txt".
* Copyright 2025 Howard Community College
* 
* @author Mario Pallares
* @version 1.0
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Exercise3 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // 1) Read application names from a file; reprompt on error
        List<String> apps = readAppNames(console);

        // 3) Loop until user chooses to exit
        while (true) {
            System.out.print("Enter a term to search for: ");
            String term = console.nextLine();

            // 2) Determine matches (case-sensitive, using String.matches)
            List<String> matches = new ArrayList<>();
            for (String app : apps) {
                if (containsPhrase(term, app)) {
                    matches.add(app);
                }
            }

            // Prompt for output file; reprompt on error. Also print matches to console.
            while (true) {
                System.out.print("Enter the output file name: ");
                String outName = console.nextLine().trim();
                try (Formatter out = new Formatter(outName)) {
                    for (String m : matches) {
                        System.out.println(m);
                        out.format("%s%n", m);
                    }
                    break; // success
                } catch (FileNotFoundException e) {
                    System.out.println("Cannot open file. Please try again.");
                }
            }

            System.out.print("\nSearch again (yes/no)? ");
            String again = console.nextLine().trim();
            if (!again.equalsIgnoreCase("yes")) {
                System.out.println("Goodbye!");
                break;
            }
        }

        console.close();
    }

    // Step 1: read all application names; allow bare filename, or in input-files/, or src/input-files/
    private static List<String> readAppNames(Scanner console) {
        while (true) {
            System.out.print("Enter file name: ");
            String name = console.nextLine().trim();

            File f = resolveInputFile(name);
            if (f != null) {
                try (Scanner in = new Scanner(f)) {
                    List<String> list = new ArrayList<>();
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        if (!line.isEmpty()) list.add(line);
                    }
                    return list;
                } catch (FileNotFoundException ignored) {
                    
                }
            }
            System.out.println("File not found. Please try again.");
        }
    }

    private static File resolveInputFile(String name) {
        File direct = new File(name);
        if (direct.exists()) return direct;

        File in1 = new File("input-files", name);
        if (in1.exists()) return in1;

        File in2 = new File("src/input-files", name);
        if (in2.exists()) return in2;

        return null;
    }

    // Step 2: substring match implemented with String.matches (case-sensitive)
    public static boolean containsPhrase(String search, String appName) {
        String regex = ".*" + Pattern.quote(search) + ".*";
        return appName.matches(regex);
    }
}
