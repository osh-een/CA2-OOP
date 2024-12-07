import java.util.*;

// Bug for mazes with loops: Some paths may be checked twice

public class Question10 {

    static String N = "up", E = "right", S = "down", W = "left";

    static boolean exitFound = false;
    static int attempts = 0;

    static Deque<int[]> positions = new ArrayDeque<>();
    static Deque<String> directions = new ArrayDeque<>();
    // to avoid pushing paths twice from an intersection that happens to be at a dead end
    static ArrayList<String> visitedIntersections = new ArrayList<>();

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

        positions.push(new int[]{row, column});

        // checking for all possible paths from the user's starting position
        checkPathsAtIntersection(mazePosition, "left&right", row, column);
        checkPathsAtIntersection(mazePosition, "up&down", row, column);

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
                handleLeftRightMovement(directionToMove, row, column, mazePosition);
            }
            else {
                handleUpDownMovement(directionToMove, row, column, mazePosition);
            }
        }

        menuOptions();
    }

    public static void handleLeftRightMovement(String directionToMove, int row, int column, int[][] mazePosition) {
        boolean done = false;

        while(!done && !exitFound) {
            attempts++;

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

            System.out.println("ATTEMPT #" +attempts);
            outputMaze(row, column, mazePosition);

            // if the current position has a value of 2, the exit is found
            if(mazePosition[row][column] == 2) {
                System.out.println("Exit found in " +attempts+ " attempts!\n");
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way and only push it if it's not already in vistedIntersections
            checkPathsAtIntersection(mazePosition, "up&down", row, column);
        }
    }

    public static void handleUpDownMovement(String directionToMove, int row, int column, int[][] mazePosition) {
        boolean done = false;

        while(!done && !exitFound) {
            attempts++;

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

            System.out.println("ATTEMPT #" +attempts);
            outputMaze(row, column, mazePosition);

            // if the current position has a value of 2, the exit is found
            if(mazePosition[row][column] == 2) {
                System.out.println("Exit found in " +attempts+ " attempts!\n");
                exitFound = true;
            }

            // as we're moving along, check if there are any paths along the way
            checkPathsAtIntersection(mazePosition, "left&right", row, column);
        }
    }

    public static void checkPathsAtIntersection(int[][] mazePosition, String direction, int row, int column) {
        // this checks for paths below or above the user's position
        if(direction.equals("up&down")) {
            if(mazePosition[row-1][column] == 1 && !visitedIntersections.contains(row+ "" +column+ "up")) {
                // ie. 34right.
                visitedIntersections.add(row+ "" +column+ "up");
                positions.push(new int[] {row, column});
                directions.push(N);
            }
            if(mazePosition[row+1][column] == 1 && !visitedIntersections.contains(row+ "" +column+ "down")) {
                visitedIntersections.add(row+ "" +column+ "down");
                positions.push(new int[] {row, column});
                directions.push(S);
            }
        }
        // this checks for paths on the left or right of the user's position
        else {
            if(mazePosition[row][column-1] == 1 && !visitedIntersections.contains(row+ "" +column+ "left")) {
                visitedIntersections.add(row+ "" +column+ "left");
                positions.push(new int[] {row, column});
                directions.push(W);
            }
            if(mazePosition[row][column+1] == 1 && !visitedIntersections.contains(row+ "" +column+ "right")) {
                visitedIntersections.add(row+ "" +column+ "right");
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
                {2, 1, 1, 1, 1, 1, 1, 0},
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
                {2, 1, 1, 0, 0, 0, 1, 0},
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
    public static void outputMaze(int startingRow, int startingColumn, int[][] maze) {
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
