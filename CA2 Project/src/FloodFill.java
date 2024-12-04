import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

//  Question 4 - Flood Fill (Stack, 2D Array) [10 marks]

//  In a paint program, a “flood fill” fills all empty pixels of a drawing with a given colour,
//  stopping when it reaches occupied pixels. 

//  In this exercise, you will implement a simple variation of this algorithm, flood-filling 
//  a 10 × 10 array of integers that are initially set to 0.

//  Prompt for the starting row and column (the starting cell for the flood fill).

//  •   Push the (row, column) pair onto a stack. 

//  You will need to provide a simple Cell class with two fields (storing row and column; (x,y)?).

//  •   Repeat the following operations until the stack is empty.

//  o   Pop the Cell (row, column pair) from the top of the stack.

//  o   If that cell has not yet been filled, fill the corresponding cell location with a
//          number 1, 2, 3, and so on (this number is incremented at each step to show
//          the order in which the squares are filled).

//  o   Push the coordinates of any unfilled neighbours in the north, east, south, or
//          west direction on the stack.F

//  •   When you are done (i.e. when the stack is empty), print the entire 2D array

/**
 * Name:
 * Class Group:
 */

public class FloodFill // Flood Fill (Stack, 2D Array)
{

    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int[][] arr = floodFillStart();

        System.out.println("Starting Array:");
        display(arr);

        int x = validateInt("row");
        int y = validateInt("column");

        fill(x, y, arr);

        System.out.println("Ending Array:");
        display(arr);

        kb.close();
    }

    /*
     * Starter function to create the 2D array and populate it with zeros
     */
    public static int[][] floodFillStart() {
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                arr[x][y] = 0;
            }
        }
        return arr;
    }

    /*
     * Helper function to display the image
     */
    public static void display(int[][] arr) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    private static void fill(int r, int c, int[][] arr) {
        Deque<Cell> stack = new ArrayDeque<>();
        int count = 1;
        stack.push(new Cell(r, c));

        while (!stack.isEmpty()) {
            Cell cell = stack.pop();
            int x = cell.getX();
            int y = cell.getY();

            if (!(x < 0 || x >= 10 || y < 0 || y >= 10 || arr[x][y] != 0)) {
                arr[x][y] = count++;

                stack.push(new Cell(x - 1, y));
                stack.push(new Cell(x + 1, y));
                stack.push(new Cell(x, y - 1));
                stack.push(new Cell(x, y + 1));
            }

        }
    }

    public static int validateInt(String word) {
        int num = 0;
        while (true) {
            try {
                System.out.print("Enter starting " + word + " (0-9): ");
                num = kb.nextInt();
                return num;
            } catch (InputMismatchException e) {
                kb.nextLine();
                System.out.println("Invalid coordinate. Please enter value between 0 and 9.");
            }
        }
    }
}
