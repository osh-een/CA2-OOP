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
}
