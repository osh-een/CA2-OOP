import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Name: Theo Picar
 *  Class Group: SD2A
 */

public class Question6 { // Flight take-off (Queue)
    static Scanner scanner = new Scanner(System.in);

    static Queue<String> forTakeoff = new ArrayDeque<String>();
    static Queue<String> forLanding = new ArrayDeque<String>();

    public static void main(String[] args) {
        System.out.println("FLIGHT TAKE-OFF SIMULATOR");
        menuOptions();
    }

    public static void menuOptions() {
        System.out.println("Choose one of the options (1-5)\n" +
                "1. Queue takeoff\n" +
                "2. Queue landing\n" +
                "3. Send flight for take-off\n" +
                "4. View flights\n" +
                "5. End application");
        int choice = UtilityClass.validateRange(1, 5);

        menu(choice);
    }

    public static void menu(int choice) {
        String flightCode = "";

        if(choice == 1) {
            System.out.println("(Press 'q' to exit) Enter only the flight codes below:");

            while (!flightCode.equals("q")) {
                flightCode = validateFlightCode();

                if (!flightCode.equals("q")) {
                    forTakeoff.add("Flight-" +flightCode);
                    System.out.println(flightCode+ " in queue for takeoff");
                }
            }

            System.out.println("Ending...\n");
            menuOptions();
        }
        else if(choice == 2) {
            System.out.println("(Press 'q' to exit) Enter only the flight codes below:");

            while (!flightCode.equals("q")) {
                flightCode = validateFlightCode();

                if (!flightCode.equals("q")) {
                    forLanding.add("Flight-" +flightCode);
                    System.out.println(flightCode+ " in queue for landing");
                }
            }

            System.out.println("Ending...\n");

            menuOptions();
        }
        else if(choice == 3) {
            if(!forTakeoff.isEmpty()) {
                String takeoffFLight = forTakeoff.remove();
                System.out.println(takeoffFLight+ " is now taking off.");
            }
            else {
                System.out.println("No flights queued for take off");
            }

            if(!forLanding.isEmpty()) {
                String landingFlight = forLanding.remove();
                System.out.println(landingFlight+ " is now landing.\n");

                forTakeoff.add(landingFlight);
            }
            else {
                System.out.println("No flights queued for landing\n");
            }

            menuOptions();
        }
        else if(choice == 4) {
            System.out.println("Flights queued for landing:");
            UtilityClass.displayQueueString(forLanding);

            System.out.println("Flights queued for take-off:");
            UtilityClass.displayQueueString(forTakeoff);

            System.out.print("\n");

            menuOptions();
        }
        else {
            System.out.println("Ending session...\nDone. Goodbye!");
        }
    }

    public static String validateFlightCode() {
        String code = "";
        boolean done = false;
        String pattern = "^[A-Z0-9]{3,6}$";

        while(!done) {
            code = scanner.next();

            if(code.matches(pattern)) {
                done = true;
            }
            else if(code.equals("q")) {
                done = true;
            }
            else {
                System.out.println("Invalid flight code. Please try again");
            }
        }

        return code;
    }
}
