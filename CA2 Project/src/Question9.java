import java.util.Scanner;
import java.util.Stack;

/**
 * Name:
 * Class Group:
 */
public class Question9 {

    /*
     * Reads in an equation from the user
     */
    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        System.out.println("Please enter equation");
        equation = in.nextLine().trim();
        int position = 0;
        while (!equation.equalsIgnoreCase("")) {
            if (equation.indexOf(" ") != -1) {
                position = equation.indexOf(" ");
            }
            String input = equation.substring(0, position);

            if (equation.length() > position + 1) {
                equation = equation.substring(position + 1);
            } else {
                equation = "";
            }

            if (input.equalsIgnoreCase("*") || input.equalsIgnoreCase("+") || input.equalsIgnoreCase("-")
                    || input.equalsIgnoreCase("/")) {
                if (input.equalsIgnoreCase("*")) {
                    stack.push(stack.pop() * stack.pop());
                } else if (input.equalsIgnoreCase("+")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (input.equalsIgnoreCase("-")) {
                    stack.push(stack.pop() - stack.pop());
                } else {
                    stack.push(stack.pop() / stack.pop());
                }

            } else {
                stack.push(Integer.parseInt(input));
            }
        }
        System.out.println("Answer: " + stack.pop());
    }
}