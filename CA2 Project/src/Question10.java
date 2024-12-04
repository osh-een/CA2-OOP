import java.util.ArrayDeque;
import java.util.Deque;

// Bug in code: If an intersection happens to be at end of path, the paths will be checked twice at that intersection
// Plus have to manually give starting directions and position otherwise code breaks

public class Question10 {

    static String N = "up", E = "right", S = "down", W = "left";
    static boolean exitFound = false;
    static int attempts = 0;

    public static void main(String[] args) {
        System.out.println("BACKTRACKING ALGORITHM");
        menuOptions();
    }

    public static void menuOptions() {
        String[] options = {
                "Start Navigating",
                "Quit application"
        };
        UtilityClass.menuOptions(options);

        System.out.println("Choose one of the above options (1-2)");
        int choice = UtilityClass.validateRange(1, 2);

        if(choice == 1) {
            mazeMenu();
        }
        else {
            System.out.println("Ending session...\nDone! Goodbye.");
        }
    }

    public static void mazeMenu() {
        Deque<int[]> positions = new ArrayDeque<>();
        Deque<String> directions = new ArrayDeque<>();
        int row = 3, column = 4;
        int[][] mazePosition = createMaze();

        // Push all possible paths from the starting position [3][4]
        positions.push(new int[]{row, column});
        directions.push(W);

        positions.push(new int[]{row, column});
        directions.push(E);

        positions.push(new int[]{row, column});
        directions.push(N);

        positions.push(new int[]{row, column});
        directions.push(S);

        while(!exitFound) {
            // re-assign variables every time a dead end is found
            int[] newRowAndColumn = positions.pop();
            String directionToMove = directions.pop();

            row = newRowAndColumn[0];
            column = newRowAndColumn[1];

            if(directionToMove.equals("left") || directionToMove.equals("right")) {
                handleLeftRightMovement(directionToMove, row, column, mazePosition, positions, directions);
            }
            else {
                handleUpDownMovement(directionToMove, row, column, mazePosition, positions, directions);
            }
        }

        menuOptions();
    }

    public static void handleLeftRightMovement(String directionToMove, int row, int column, int[][] mazePosition, Deque<int[]> positions, Deque<String> directions) {
        boolean done = false;

        while(!done && !exitFound) {
            attempts++;

            System.out.println("ATTEMPT #" +attempts);
            outputMaze(row, column);

            // moving left
            if(directionToMove.equals("left")) {
                if(mazePosition[row][column-1] == 0) {
                    done = true;
                }
                else {
                    column--;
                }
            }
            // moving right
            else {
                if(mazePosition[row][column+1] == 0) {
                    done = true;
                }
                else {
                    column++;
                }
            }

            // checks to see if column is equal to either max or min bound of the array. This means we found exit
            if(column <= 0 || column >= mazePosition[0].length) {
                System.out.println("Exit found!\n");
                done = true;
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way
            if(mazePosition[row-1][column] == 1) {
                positions.push(new int[] {row, column});
                directions.push(W);
            }
            if(mazePosition[row+1][column] == 1) {
                positions.push(new int[] {row, column});
                directions.push(E);
            }
        }
    }

    public static void handleUpDownMovement(String directionToMove, int row, int column, int[][] mazePosition, Deque<int[]> positions, Deque<String> directions) {
        boolean done = false;

        while(!done && !exitFound) {
            attempts++;

            System.out.println("ATTEMPT #" +attempts);
            outputMaze(row, column);

            // moving up
            if(directionToMove.equals("up")) {
                if(mazePosition[row-1][column] == 0) {
                    done = true;
                }
                else {
                    row--;
                }
            }
            // moving down
            else {
                if(mazePosition[row+1][column] == 0) {
                    done = true;
                }
                else {
                    row++;
                }
            }

            // checks to see if column is equal to either max or min bound of the array. This means we found exit
            if(row <= 0 || row >= mazePosition[0].length) {
                System.out.println("Exit found!\n");
                done = true;
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way
            if(mazePosition[row][column-1] == 1) {
                positions.push(new int[] {row, column});
                directions.push(W);
            }
            if(mazePosition[row][column+1] == 1) {
                positions.push(new int[] {row, column});
                directions.push(E);
            }
        }
    }

    public static int[][] createMaze() {
        return new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    // parameters act as a way to keep track where the user is moving
    public static void outputMaze(int startingRow, int startingColumn) {
        int[][] maze = createMaze();

        for(int i = 0; i < maze.length; i++) {
            if(i != 0)  {
                System.out.println();
            }

            for(int j = 0; j < maze.length; j++) {
                // wall
                if(maze[i][j] == 0) {
                    System.out.print("[]");
                }
                else if(i == startingRow && j == startingColumn) {
                    System.out.print("=)");
                }
                // path (two spaces so it formats correctly on terminal)
                else {
                    System.out.print("  ");
                }
            }
        }

        System.out.println("\n");
    }

}
