import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *  Name:
 *  Class Group:
 */

// Bugs:
    // 1. If there are two paths with the same distance, errors happen
    // 2. Can't backtrack ie. Princeton to Pendleton

    // pathnames
    //"C:\\Users\\larem\\Documents\\DKIT\\YEAR 2\\Computer Security\\CA2-OOP\\CA2 Project\\src\\cityconnections.txt"

public class Question11 {

    static Scanner scanner = new Scanner(System.in);

    static Map<String, TreeSet<DistanceTo>> connectionsMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("SHORTEST DISTANCE ALGORITHM (DIJKSTRA)");

        addAllConnectionsFromFile();

        menuOptions();
    }
    
    public static void menuOptions() {
        String[] options = {
                "Start algorithm",
                "Quit application"
        };
        
        UtilityClass.menuOptions(options);

        System.out.println("Please choose one of the above options (1-2)");
        int choice = UtilityClass.validateRange(1, 2);

        if(choice == 1) {
            startAlgorithm();
        }
        else {
            System.out.println("Ending session...\nDone! Goodbye.");
        }
    }

    public static void startAlgorithm() {
        System.out.println("Where would you like to travel from?");
        String location = validateCity();

        System.out.println("Where would you like to travel to?");
        String destination = validateCity();

        // Priority Queue to store cities and their distances (sorted by distance)
        PriorityQueue<DistanceTo> distances = new PriorityQueue<>(new DistanceComparator());
        // Map to store the shortest known distance to each city
        Map<String, Integer> shortestKnownDistances = new HashMap<>();
        // Set to track visited cities, to avoid reprocessing them
        Set<String> visitedCities = new HashSet<>();  // NEW: Track visited cities

        // Add the starting city to the priority queue with distance 0
        distances.add(new DistanceTo(location, 0));

        while (!distances.isEmpty()) {
            // Get the city with the smallest distance (priority queue will give the smallest distance first)
            DistanceTo currentDistanceTo = distances.remove();
            String currentCity = currentDistanceTo.getTarget();
            int shortestCurrentDistance = currentDistanceTo.getDistance();

            // NEW: Skip cities that have already been processed (i.e., cities with finalized distances)
            if (visitedCities.contains(currentCity)) {
                continue;
            }

            // Mark the current city as visited (finalized)
            visitedCities.add(currentCity);

            // Store the shortest known distance for the current city
            shortestKnownDistances.put(currentCity, shortestCurrentDistance);

            // If we have reached the destination, print the result and return to the main menu
            if (currentCity.equals(destination)) {
                System.out.println("The shortest distance from " + location + " to " + destination + " is " + shortestCurrentDistance + ". Returning to main menu...\n");
                menuOptions();
                return;  // Return to the main menu after printing the result
            }

            // Add all neighboring cities to the priority queue with updated distances
            for (DistanceTo connectedCity : connectionsMap.get(currentCity)) {
                String neighbour = connectedCity.getTarget();
                int neighbourDistance = connectedCity.getDistance();

                // Calculate the new distance to the neighboring city
                int newDistance = shortestCurrentDistance + neighbourDistance;

                // NEW: Only add the neighboring city to the priority queue if:
                // 1. It has not been visited yet.
                // 2. It has not been processed before or the new distance is shorter than its previous shortest distance.
                if (!visitedCities.contains(neighbour) && (!shortestKnownDistances.containsKey(neighbour) || newDistance < shortestKnownDistances.get(neighbour))) {
                    distances.add(new DistanceTo(neighbour, newDistance));
                }
            }
        }

        // If we exit the loop, it means there is no path to the destination
        System.out.println("No path found from " + location + " to " + destination + ". Returning to main menu...\n");
        menuOptions();  // Return to the main menu if no path is found
    }

    public static void addAllConnectionsFromFile() {
        try {
            File file = new File("C:\\Users\\larem\\Documents\\DKIT\\YEAR 2\\Computer Security\\CA2-OOP\\CA2 Project\\src\\cityconnections.txt");
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
