/**
 * Name:
 * Class Group:
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
        // Update the file paths if needed
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};

        // Check the validity of each file
        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }


    public static boolean validate(String filename) throws FileNotFoundException {
        // Define self-closing tags that don't require a closing tag
        String[] selfClosingTags = {"<br>", "<hr>", "<img>", "<input>", "<link>", "<meta>", "<base>"};

        // Create a stack to keep track of opening tags
        Stack<String> stack = new Stack<>();

        // Open the file for reading
        Scanner sc = new Scanner(new File(filename));

        // Read all lines in the file
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            // Split the line into tags by spaces (assuming tags are separated by spaces)
            String[] tags = line.split("\\s+");

            // Process each tag in the line
            for (String tag : tags) {
                // Ignore empty strings
                if (tag.isEmpty()) {
                    continue;
                }

                // Check if the tag is a closing tag
                if (tag.startsWith("</")) {
                    String closingTag = tag;

                    // Check if the stack is not empty and the top of the stack matches the closing tag
                    if (!stack.isEmpty() && stack.peek().equals(closingTag.replaceAll("</", "<"))) {
                        stack.pop();  // Pop the matching opening tag from the stack
                    } else {
                        // If the stack is empty or the tags don't match, return false (tags are not properly nested)
                        sc.close();
                        return false;
                    }
                } else if (!isSelfClosingTag(tag, selfClosingTags)) {
                    // If it's an opening tag and not a self-closing tag, push it onto the stack
                    stack.push(tag);
                }
            }
        }

        // Close the scanner after reading the file
        sc.close();

        // After processing all tags, if the stack is empty, it means all tags were properly closed
        return stack.isEmpty();
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
