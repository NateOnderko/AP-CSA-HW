import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter two integers.");

        final int num1 = s.nextInt();
        double num2 = s.nextDouble();

        s.close();

        try {
            int answer = num1 / (int) (num2 + 0.5);

            System.out.println(answer);

            boolean special_bool = true;

            System.out.print("Division: " + special_bool + ", Answer + 1 is: " + (answer++));
        }
        catch (ArithmeticException e) {
            System.out.println(e);
        }

    }
}
