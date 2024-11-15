

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Name:
 *  Class Group:
 */

public class Question4  // Flood Fill (Stack, 2D Array)
{
    public static void main(String[] args) {
        start();
    }
    public static void start()
    {
        int[][] arr = floodFillStart();
    }

    /*
        Starter function to create the 2D array and populate it with zeros
     */
    public static int[][]  floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int
         x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
        return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void fill(int r, int c, int[][] arr)
    {

    }

}
