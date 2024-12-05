import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *  Name:
 *  Class Group:
 */

public class Question11 {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, TreeSet<DistanceTo>> connectionsMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("SHORTEST DISTANCE ALGORITHM (DIJKSTRA)");
        addAllConnectionsFromFile(connectionsMap);
        menuOptions();
    }
    
    public static void menuOptions() {
        String[] options = {
                "1. View map",
                "2. Start algorithm",
                "3. Quit application"
        };
        
        UtilityClass.menuOptions(options);

        System.out.println("Please choose one of the above options (1-3)");
        int choice = UtilityClass.validateInt();

        if(choice == 1) {
            // code...
        }
        else if(choice == 2) {
            startAlgorithm();
        }
        else {
            System.out.println("Ending session...\nDone! Goodbye.");
        }
    }

    public static void startAlgorithm() {
        System.out.println("Where would you like to travel from?");
        String location = validateCity(connectionsMap);

        System.out.println("Where would you like to travel to?");
        String destination = validateCity(connectionsMap);
    }

    public static void addAllConnectionsFromFile(Map<String, TreeSet<DistanceTo>> connectionsMap) {
        try {
            File file = new File("/Users/user/Documents/GitHub/CA2-OOP/CA2 Project/src/q11/cityconnections.txt");
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNext()) {
                String city = fileScanner.next();
                String connectedCity = fileScanner.next();
                int distance = fileScanner.nextInt();

                // only add the city if it is not yet in connectionsMap
                if(!connectionsMap.containsKey(city)) {
                    connectionsMap.put(city, new TreeSet<>());
                }

                // add connectedCity to its corresponding city
                connectionsMap.get(city).add(new DistanceTo(connectedCity, distance));
            }
        }
        catch(IOException e) {
            System.out.println("File was not found! Ending session...");
        }
    }

    public static String validateCity(Map<String, TreeSet<DistanceTo>> connectionsMap) {
        String input = "";
        boolean done = false;

        while(!done) {
            if(scanner.hasNext()) {
                input = scanner.next();

                if(connectionsMap.get(input) == null) {
                    System.out.println("That city does not exist! Please enter a different city");
                }
                else {
                    done = true;
                }
            }
            else {
                System.out.println("Please enter a string value!");
                scanner.next();
            }
        }

        return input;
    }
}
