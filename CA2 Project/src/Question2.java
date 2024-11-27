

/**
 *  Name:
 *  Class Group:
 */
import java.util.Scanner;
import java.util.Stack;

public class Question2 {
    public static void main(String[] args) {

        stack();

    }

    public static void stack() {
        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Driveway Parking Simulator!");

        System.out.println("1. Enter a positive number (e.g., 1, 2, 3) to park a car.");
        System.out.println("2. Enter a negative number (e.g., -2) to retrieve a car.");
        System.out.println("3. Enter '0' to stop the simulation.");
        System.out.println();

        while (true) {
            System.out.print("Enter your action: ");
            int input = sc.nextInt();

            if (input == 0) {
                System.out.println("Simulation ended.");
                break;
            } else if (input > 0) {
                // Add a car to the driveway
                driveway.push(input);
                System.out.println("Car " + input + " parked in the driveway.");
            } else if (input < 0) {
                // Remove a car from the driveway
                int carToRetrieve = -input;
                boolean found = false;

                // Move cars to the street until we find the requested car
                while (!driveway.isEmpty()) {
                    int topCar = driveway.pop();
                    if (topCar == carToRetrieve) {
                        System.out.println("Car " + carToRetrieve + " retrieved from the driveway.");
                        found = true;
                        break;
                    } else {
                        street.push(topCar);
                    }
                }

                // If the car wasn't found in the driveway
                if (!found) {
                    System.out.println("Car " + carToRetrieve + " is not in the driveway.");
                }

                // Move cars back from the street to the driveway
                while (!street.isEmpty()) {
                    driveway.push(street.pop());
                }
            } else {
                System.out.println("Invalid input. Please enter a positive number, negative number, or 0.");
            }

            // Print the current state of the driveway
            System.out.println("Current state of the driveway: " + driveway);
        }
        
        sc.close();
    }
}

