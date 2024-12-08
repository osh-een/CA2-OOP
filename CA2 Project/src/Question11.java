import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *  Name:
 *  Class Group:
 */

// Bugs/Errors/Functionality not added:
    // 1. If there are two paths with the same distance, errors happen ie. Pueblo to Pittsburgh outputs 12
        // FIXED: Lazily though. Just swapped Peoria and Phoenix around just so Peoria is checked first
    // 2. Can't backtrack ie. Princeton to Pendleton
        // DONE: Left to right routes added first before right to left directions
    // 3. Can't display map of the cities and their connections

public class Question11 {

    static Scanner scanner = new Scanner(System.in);

    static Map<String, TreeSet<DistanceTo>> connectionsMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("SHORTEST DISTANCE ALGORITHM (DIJKSTRA)");

        menuOptions();
    }
    
    public static void menuOptions() {
        String[] options = {
                "Read file",
                "Start algorithm",
                "Quit application"
        };
        
        UtilityClass.menuOptions(options);

        System.out.println("Please choose one of the above options (1-3)");
        int choice = UtilityClass.validateRange(1, 3);

        if(choice == 1) {
            addAllConnectionsFromFile();
        }
        else if(choice == 2) {
            startAlgorithm();
        }
        else {
            System.out.println("Ending session...\nDone! Goodbye.");
        }
    }

    public static void startAlgorithm() {
        if(connectionsMap.isEmpty()) {
            System.err.println("Please start up the file before using the algorithm!\n");
            menuOptions();
        }

        System.out.println("Where would you like to travel from?");
        String location = validateCity();

        System.out.println("Where would you like to travel to?");
        String destination = validateCity();

        // no matter what, cities with the shortest distance will always be first in the queue because of comparator implemented
        PriorityQueue<DistanceTo> distances = new PriorityQueue<>();
        Map<String, Integer> shortestKnownDistances = new HashMap<>();

        distances.add(new DistanceTo(location, 0));

        while(!distances.isEmpty()) {
            // will always remove the shortest distance to a city first or remove whatever came into the queue first if distances are the same
            DistanceTo currentDistanceTo = distances.remove();

            String currentCity = currentDistanceTo.getTarget();
            int shortestCurrentDistance = currentDistanceTo.getDistance();

            if (currentCity.equalsIgnoreCase(location)) {
                System.out.println("\nYou are currently at your location (" + currentCity + "). You have not travelled yet");
            } else {
                System.out.println("Travelling to " + currentCity);
            }

            // puts top element of PriorityQueue as shortest getDistance() have priority
            shortestKnownDistances.put(currentCity, shortestCurrentDistance);

            // break the code if we're at the intended city (destination)
            if (currentCity.equals(destination)) {
                System.out.println("\nThe shortest distance from " + location + " to " + destination + " is " + shortestCurrentDistance + "km. Returning to main menu...\n");
                menuOptions();
            }

            // gets all the cities that are connected to the currentCity. For loop of the tree set with the key of currentCity
            System.out.println("All possible connections from " + currentCity + ":");
            for (DistanceTo connectedCity : connectionsMap.get(currentCity)) {
                String neighbourCity = connectedCity.getTarget();
                int neighbourDistance = connectedCity.getDistance();

                System.out.println("\t- " + neighbourCity + ", " + neighbourDistance + "km away");

                int newDistance = shortestCurrentDistance + neighbourDistance;

                // only add it to PriorityQueue if the city is not yet in the PriorityQueue to avoid going back same route. Shortest distance will come up on top
                if (!shortestKnownDistances.containsKey(neighbourCity)) {
                    distances.add(new DistanceTo(neighbourCity, newDistance));
                }
            }
            System.out.println(distances + "\n");
        }
    }

    public static void addAllConnectionsFromFile() {
        try {
            File file = new File("cityconnections.txt");
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNext()) {
                String city = fileScanner.next();
                String connectedCity = fileScanner.next();
                int distance = fileScanner.nextInt();

                // only add the city if it is not yet in connectionsMap
                if(!connectionsMap.containsKey(city)) {
                    connectionsMap.put(city, new TreeSet<>());
                    System.out.println("\nAdding a new city point: " +city);
                }

                // add connectedCity to its corresponding city
                connectionsMap.get(city).add(new DistanceTo(connectedCity, distance));
                System.out.println("Connecting " +connectedCity+ " with distance of " +distance+ "km to " +city);
            }

            System.out.println("Success! Returning to main menu...\n");
            menuOptions();
        }
        catch(IOException e) {
            System.err.println("File was not found! Ending session...");
        }
    }

    public static String validateCity() {
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
