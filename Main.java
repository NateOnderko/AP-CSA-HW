import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter two integers.");

        final int num1 = s.nextInt(); // Only num1 is final; num2 can still be changed.
        int num2 = s.nextInt();

        s.close();

        num2++; // Adds 1 to num2

        try {
            System.out.print(num1 / num2);
        }
        catch (ArithmeticException e) {
            System.out.print("Can't divide by zero.");
        }

    }
}
