public class Calculator {

    private int num1;
    private int num2;


    public Calculator(int a, int b) {
        num1 = a;
        num2 = b;
    }

    public void divide() {
        try {
            int result = num1 / num2;
            System.out.println(num1 + " / " + num2 + " = " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Can't divide by zero.");
        }
    }

    public void multiply() {
        int result = num1 * num2;
        System.out.println(num1 + " * " + num2 + " = " + result);
    }

    public void add() {
        int result = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + result);
    }

    public void subtract() {
        int result = num1 - num2;
        System.out.println(num1 + " - " + num2 + " = " + result);
    }

    public void round_nums() {
        num1 = (int) (num1 + 0.5);
        num2 = (int) (num2 + 0.5);
        System.out.println("NUMS ROUNDED.");
    }

    public void print_nums() {
        System.out.println("Num 1: " + num1 + ", Num 2: " + num2);
    }

    public void change_nums() {
        num1 = Integer.MAX_VALUE;
        num2 = (num2 * -1) - 1;

        System.out.println("NUMS CHANGED.");
    }
}
