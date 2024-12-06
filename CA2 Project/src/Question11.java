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

// Theos path: /Users/user/Documents/GitHub/CA2-OOP/CA2 Project/src/cityconnections.txt

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

        // no matter what, cities with the shortest distance will always be first in the queue
        PriorityQueue<DistanceTo> distances = new PriorityQueue<>(new DistanceComparator());
        Map<String, Integer> shortestKnownDistances = new HashMap<>();

        distances.add(new DistanceTo(location, 0));

        while(!distances.isEmpty()) {
            DistanceTo currentDistanceTo = distances.remove();
            String currentCity = currentDistanceTo.getTarget();
            int shortestCurrentDistance = currentDistanceTo.getDistance();

            // puts top element of PriorityQueue as shortest getDistance() have priority
            shortestKnownDistances.put(currentCity, shortestCurrentDistance);

            // break the code if we're at the intended city (destination)
            if(currentCity.equals(destination)) {
                System.out.println("The shortest distance from " +location+ " to " +destination+ " is " +shortestCurrentDistance+ ". Returning to main menu...\n");
                menuOptions();
            }

            for(DistanceTo connectedCity : connectionsMap.get(currentCity)) {
                String neighbour = connectedCity.getTarget();
                int neighbourDistance = connectedCity.getDistance();

                int newDistance = shortestCurrentDistance + neighbourDistance;

                // only add it to PriorityQueue if the city is not yet in Map or if newDistance is shorter than distance of current neighbour city
                if (!shortestKnownDistances.containsKey(neighbour) || newDistance < shortestKnownDistances.get(neighbour)) {
                    distances.add(new DistanceTo(neighbour, newDistance));
                }
            }
        }
    }

    public static void addAllConnectionsFromFile() {
        try {
            File file = new File("/Users/user/Documents/GitHub/CA2-OOP/CA2 Project/src/cityconnections.txt");
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
