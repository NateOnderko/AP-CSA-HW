public class Calculator {
    private int num1;
    private int num2;
    private int secret_num;

    public Calculator(int a, int b) {
        num1 = a;
        num2 = b;
    }

    public Calculator(int a, int b, int c) {
        num1 = a;
        num2 = b;
        secret_num = c;
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

        // this is never truly used because num1 and num2 are always ints
        // it's included to meet the requirements
    }

    public void print_nums() {
        System.out.println("Num 1: " + num1 + ", Num 2: " + num2 + ", Secret Num: " + secret_num);
    }

    public void change_nums(int a, int b) {
        num1 = a;
        num2 = b;

        System.out.println("NUMS CHANGED.");
    }

    public void change_nums(int a, int b, int c) {
        num1 = a;
        num2 = b;
        secret_num = c;

        System.out.println("NUMS CHANGED.");
    }
}
