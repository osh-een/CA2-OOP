/**
 * Name: OLUWADAMILARE DAVID ADEKEYE
 * Class Group: SD2B
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Question3 { // Nested HTML (Stack)

    /*
     * This function tests the files in the files array to see if
     * they are valid.
     * tags_valid.txt should return true;
     * tags_invalid.txt should output as invalid;
     */

    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};

        // Check if the tags in the given files are valid or not
        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } 
            else {
                System.out.println("This file is invalid");
            }
        }
    }


    public static boolean validate(String filename) throws FileNotFoundException {
        // All tags that don't need a corresponding closing tag
        String[] selfClosingTags = {"<br>", "<hr>", "<img>", "<input>", "<link>", "<meta>"};

        // Stack of all openingTags that will be found in the file
        Stack<String> openingTags = new Stack<>();

        Scanner fileScanner = new Scanner(new File(filename));

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            // Make an array of the tags from the current line in the file. An item is added after every space
            String[] tags = line.split("\\s+");

            for (String tag : tags) {
                // Check if the tag is a closing tag
                if (tag.startsWith("</")) {
                    // Check if the openingTags is not empty and the top of the openingTags matches the closing tag
                    if (!openingTags.isEmpty() && openingTags.peek().equals(tag.replace("</", "<"))) {
                        openingTags.pop();
                    }
                    else {
                        return false;
                    }
                } 
                else if (!isSelfClosingTag(tag, selfClosingTags)) {
                    // Push the new opening tag to the stack
                    openingTags.push(tag);
                }
            }
        }

        // Close the scanner after reading the file
        fileScanner.close();

        // After processing all tags, if the openingTags is empty, it means all tags were properly closed
        return openingTags.isEmpty();
    }

    // Helper method to check if the tag is self-closing (e.g., <br>, <img>)
    private static boolean isSelfClosingTag(String tag, String[] selfClosingTags) {
        for (String selfClosingTag : selfClosingTags) {
            if (tag.equals(selfClosingTag)) {
                return true;  // Return true if the tag is found in the self-closing tags list
            }
        }
        return false;  // Return false if the tag is not a self-closing tag
    }
}
