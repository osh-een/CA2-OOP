import java.io.*;
import java.util.*;
import java.util.regex.*;

//  Write a program that reads a Java source file and constructs a Map identiferCountMap containing 
//  all of the identifiers in the file and a count of the number times each identifier occurs.

//  Create a second map that stores the identifier as a key and a corresponding list of all of the
//  lines of code that contain that key. Add the code line number to the start of each line of code.

//  Output a list of all identifiers, sorted in ascending order of identifier, showing the identifier,
//  its count and the list of lines of code that use that identifier.

//  Sample output:
//  boolean 2           [i.e. “boolean” is the identifier]
//  5. boolean finished;
//  12. public Boolean getStatus() {
//  int 1
//  6. private int age;

//  Identifiers are variable names, class names and keywords etc.
//  Read from Java source file in a line-by-line manner.

//  For simplicity, we will consider each string consisting only of letters, numbers, and
//  underscores an identifiers. Use a delimiter to filter e.g. "[^A-Za-z0-9_]+"). 

//  REFERENCES:
//  https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/regex/Pattern.html
//  https://www.geeksforgeeks.org/pattern-compilestring-method-in-java-with-examples/
//  https://javarevisited.blogspot.com/2017/09/java-8-sorting-hashmap-by-values-in.html#axzz8smlWLC9F
//  https://www.geeksforgeeks.org/for-each-loop-in-java/

/**
 * Name:
 * Class Group:
 */
public class Question5 { // Java Identifier Count (Map)

    public static void main(String[] args) throws FileNotFoundException {
        readgFile("OOP-CA2/src/Q5/Question5.java");
    }

    public static void readFile(String fileName) {

        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, ArrayList<Integer>> identifierLineCountMap = new HashMap<>();

        String identifierRegex = "[A-Za-z0-9_]+";
        Pattern pattern = Pattern.compile(identifierRegex);

        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
                line = line.replaceAll("\"(\\\\.|[^\"])*\"", "");
                line = line.replaceAll("//.*", "");
                line = line.replaceAll("/\\*.*?\\*/", "");

                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String identifier = matcher.group();
                    identifierCountMap.put(identifier, identifierCountMap.getOrDefault(identifier, 0) + 1);
                    identifierLineCountMap.put(identifier,
                            identifierLineCountMap.getOrDefault(identifier, new ArrayList<>()));
                    identifierLineCountMap.get(identifier).add(count);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        System.out.println("Identifiers, their counts, and the lines they appear on:");
        identifierCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String identifier = entry.getKey();
                    int c = entry.getValue();
                    ArrayList<Integer> lines = identifierLineCountMap.get(identifier);
                    System.out.println(identifier + ": " + c + " [lines: " + lines + "]");
                });
    }

    public static void readgFile(String fileName) {

        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, ArrayList<Integer>> identifierLineCountMap = new HashMap<>();
        ArrayList<String> linesList = new ArrayList<>();

        String identifierRegex = "[A-Za-z0-9_]+";
        Pattern pattern = Pattern.compile(identifierRegex);

        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
                count++;
                line = line.replaceAll("\"(\\\\.|[^\"])*\"", "");
                line = line.replaceAll("//.*", "");
                line = line.replaceAll("/\\*.*?\\*/", "");

                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String identifier = matcher.group();
                    identifierCountMap.put(identifier, identifierCountMap.getOrDefault(identifier, 0) + 1);
                    identifierLineCountMap.putIfAbsent(identifier, new ArrayList<>());
                    identifierLineCountMap.get(identifier).add(count);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        System.out.println("Identifiers, their counts, and the lines they appear on:");
        System.out.println(identifierCountMap);

        identifierCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String identifier = entry.getKey();
                    int c = entry.getValue();
                    ArrayList<Integer> lines = identifierLineCountMap.get(identifier);

                    System.out.printf("%-10s %d%n", identifier, c);
                    int[] cou = { 1 };
                    lines.forEach(
                            line -> {
                                System.out.printf("%4d. %s%n", cou[0], linesList.get(line - 1));
                                cou[0]++;
                            });
                });
    }

}