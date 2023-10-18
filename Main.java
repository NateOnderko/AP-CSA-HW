import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Poker Deluxe 3000! What is your Name: ");
        String name = s.nextLine();
        System.out.println("\nHello, " + name + "! How much would you like to start with?");

        Deck deck = new Deck();
        Player player = new Player(deck, s.nextDouble());

        System.out.println("\nYour hand is: " + player.handString());

    }
}
