import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
        int[][] mazePosition = createPDFMaze();
        int row = 0, column = 0;

        boolean done = false;
        while(!done) {
            System.out.println("Please enter the row you would like to start at (0-7)");
            row = UtilityClass.validateRange(0, 7);

            System.out.println("Please enter the column you would like to start at");
            column = UtilityClass.validateRange(0, 7);

            if(mazePosition[row][column] == 1) {
                System.out.println("You are starting at position [" +row+ ", " +column+ "]. Navigating the maze...");
                done = true;
            }
            else {
                System.out.println("Invalid position! You must choose a position on a path.");
            }
        }

        // to avoid pushing paths twice from an intersection that happens to be at a dead end
        Deque<String> visitedIntersections = new ArrayDeque<>();

        positions.push(new int[]{row, column});

        // checking for all possible paths from the user's starting position
        if(mazePosition[row-1][column] == 1) {
            // ie. 34right.
            visitedIntersections.push(row+ "" +column+ "up");
            positions.push(new int[] {row, column});
            directions.push(W);
        }
        if(mazePosition[row+1][column] == 1 && !visitedIntersections.contains(row+ "" +column+ "down")) {
            visitedIntersections.push(row+ "" +column+ "down");
            positions.push(new int[] {row, column});
            directions.push(E);
        }
        if(mazePosition[row][column-1] == 1 && !visitedIntersections.contains(row+ "" +column+ "left")) {
            visitedIntersections.push(row+ "" +column+ "left");
            positions.push(new int[] {row, column});
            directions.push(S);
        }
        if(mazePosition[row][column+1] == 1 && !visitedIntersections.contains(row+ "" +column+ "right")) {
            visitedIntersections.push(row+ "" +column+ "right");
            positions.push(new int[] {row, column});
            directions.push(N);
        }

        while(!exitFound) {
            // re-assign variables every time a dead end is found
            int[] newRowAndColumn = new int[2];
            String directionToMove = "";

            if(!positions.isEmpty() && !directions.isEmpty()) {
                newRowAndColumn = positions.pop();
                directionToMove = directions.pop();
            }
            else {
                System.out.println("There are no exits in this maze!\n");
                menuOptions();
            }

            row = newRowAndColumn[0];
            column = newRowAndColumn[1];

            if(directionToMove.equals("left") || directionToMove.equals("right")) {
                handleLeftRightMovement(directionToMove, row, column, mazePosition, positions, directions, visitedIntersections);
            }
            else {
                handleUpDownMovement(directionToMove, row, column, mazePosition, positions, directions, visitedIntersections);
            }
        }

        menuOptions();
    }

    public static void handleLeftRightMovement(String directionToMove, int row, int column, int[][] mazePosition, Deque<int[]> positions, Deque<String> directions, Deque<String> visitedIntersections) {
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
            if(column <= 0 || column >= mazePosition[0].length - 1) {
                System.out.println("Exit found in " +attempts+ " attempts!\n");
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way and only push it if it's not already in vistedIntersections
            if(mazePosition[row-1][column] == 1 && !visitedIntersections.contains(row+ "" +column+ "up")) {
                // ie. 34right.
                visitedIntersections.push(row+ "" +column+ "up");
                positions.push(new int[] {row, column});
                directions.push(N);
            }
            if(mazePosition[row+1][column] == 1 && !visitedIntersections.contains(row+ "" +column+ "down")) {
                visitedIntersections.push(row+ "" +column+ "down");
                positions.push(new int[] {row, column});
                directions.push(S);
            }
        }
    }

    public static void handleUpDownMovement(String directionToMove, int row, int column, int[][] mazePosition, Deque<int[]> positions, Deque<String> directions, Deque<String> visitedIntersections) {
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
            if(row <= 0 || row >= mazePosition[0].length - 1) {
                System.out.println("Exit found in " +attempts+ " attempts!\n");
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way
            if(mazePosition[row][column-1] == 1 && !visitedIntersections.contains(row+ "" +column+ "left")) {
                visitedIntersections.push(row+ "" +column+ "left");
                positions.push(new int[] {row, column});
                directions.push(W);
            }
            if(mazePosition[row][column+1] == 1 && !visitedIntersections.contains(row+ "" +column+ "right")) {
                visitedIntersections.push(row+ "" +column+ "right");
                positions.push(new int[] {row, column});
                directions.push(E);
            }
        }
    }

    public static int[][] createPDFMaze() {
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

    public static int[][] createMazeWithLoop() {
        return new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public static int[][] createMazeWithNoExit() {
        return new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    // parameters act as a way to keep track where the user is moving
    public static void outputMaze(int startingRow, int startingColumn) {
        int[][] maze = createPDFMaze();

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
