import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter two integers, and one secret integer:");

        Calculator calc = new Calculator(s.nextInt(), s.nextInt(), s.nextInt());
        final boolean CALC_ONLINE = true;

        System.out.println();
        System.out.println("Please enter your favorite number: ");
        double favoriteNumber = s.nextDouble();

        s.close();

        System.out.println();
        System.out.println("Calculator online: " + CALC_ONLINE + ", Favorite Number: " + favoriteNumber);
        System.out.println();

        calc.print_nums();
        calc.divide();
        calc.multiply();
        calc.add();
        calc.subtract();
        System.out.println();
        calc.change_nums();
        calc.round_nums();
        System.out.println();
        calc.print_nums();
        calc.divide();
        calc.multiply();
        calc.add();
        calc.subtract();
    }
}
