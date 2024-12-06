/**
 *  Name: OLUWADAMILARE DAVID ADEKEYE
 *  Class Group: SD2B
 */
import java.util.Scanner;
import java.util.Stack;

public class Question2 {
    public static void main(String[] args) {
        System.out.println("DRIVEWAY PARKING SIMULATOR");

        stack();
    }

    public static void stack() {
        String[] options = {
                "Park a car (Enter a licence plate - 1, 2, 3, etc.)",
                "Retrieve a car (Enter a negative version of licence plate - -1, -2, -3)",
                "Quit application (Enter 0)"
        };

        UtilityClass.menuOptions(options);

        Stack<Integer> driveway = new Stack<>();
        Stack<Integer> street = new Stack<>();

        while(true) {
            System.out.print("\nEnter your action: ");
            int input = UtilityClass.validateInt();

            if (input == 0) {
                UtilityClass.endAppMessage();
                break;
            }
            // Pushing a car to the driveway stack
            else if (input > 0) {
                driveway.push(input);
                System.out.println("Car with licence number '" + input + "' has been parked in the driveway.");

                displayCars(driveway, "Driveway");
            }
            // Removing a car from the driveway
            else if (input < 0) {
                int carToRetrieve = -input;
                boolean found = false;

                // Move cars to the street until we find the requested car
                while (!driveway.isEmpty()) {
                    int topCar = driveway.pop();

                    if (topCar == carToRetrieve) {
                        System.out.println("Car with licence number '" + carToRetrieve + "' has been retrieved from the driveway.");
                        found = true;
                        break;
                    }
                    else {
                        System.out.println("Car with plate '" +topCar+ "' is moving onto the street");
                        street.push(topCar);
                    }
                }

                // If the car wasn't found in the driveway
                if (!found) {
                    System.out.println("Car " + carToRetrieve + " is not in the driveway.");
                }

                System.out.println("\nThe following cars on the street are now moving back to the driveway");
                displayCars(street, "Street");

                // Move cars back from the street to the driveway
                while (!street.isEmpty()) {
                    driveway.push(street.pop());
                }
            }
            else {
                System.out.println("Invalid input. Please enter a positive number, negative number, or 0.");
            }
        }
    }

    public static void displayCars(Stack<Integer> stack, String stackName) {
        Stack<Integer> clonedDriveway = (Stack<Integer>) stack.clone();

        if(clonedDriveway.isEmpty()) {
            System.out.println("No cars in the " +stackName);
        }
        else {
            while(!clonedDriveway.isEmpty()) {
                System.out.println("| " +clonedDriveway.pop()+ " |");
            }
        }
    }
}

