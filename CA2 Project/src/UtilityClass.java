import java.util.Queue;
import java.util.Scanner;

public class UtilityClass {
    static Scanner scanner = new Scanner(System.in);

    public static int validateRange(int min, int max) {
        int input = 0;
        boolean done = false;

        while(!done) {
            if(scanner.hasNextInt()) {
                input = scanner.nextInt();

                if(input < min || input > max) {
                    System.out.println("Please enter a valid option (" +min+ "-" +max+ ")");
                }
                else {
                    done = true;
                }
            }
            else {
                System.out.println("Please enter only integer values");
                scanner.next();
            }
        }

        return input;
    }

    public static double validateDouble(int decimalPlaces) {
        double input = 0.0;
        boolean done = false;

        while(!done) {
            if(scanner.hasNextDouble()) {
                input = scanner.nextDouble();

                done = true;
            }
            else {
                System.out.println("Please enter a valid double value!");
                scanner.next();
            }
        }

        // 10^decimalPlaces
        double power = Math.pow(10, decimalPlaces);
        // multiply the input by 10^decimalPlaces and round it, then divide the rounded int by 10^decimalPlaces again
        return Math.round(input * power) / power;
    }

    public static String validatePattern(String pattern, String errorMessage) {
        String input = "";
        boolean done = false;

        while(!done) {
            input = scanner.next();

            if(input.matches(pattern)) {
                done = true;
            }
            else {
                System.out.println(errorMessage);
            }
        }

        return input;
    }

    public static void displayQueueString(Queue<String> queue) {
        int counter = 0;

        if(!queue.isEmpty()) {
            System.out.print("[");
            for (String item : queue) {
                counter++;

                if (counter != queue.size()) {
                    System.out.print(item + ", ");
                } else {
                    System.out.println(item + "]");
                }
            }
        }
        else {
            System.out.println("The queue is empty");
        }
    }

    public static void menuOptions(String[] options) {
        for(int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
    }
}
