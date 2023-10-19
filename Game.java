import java.util.Scanner;

public class Game {

    private final Board board = new Board(new Deck());

    public void getPlayers() {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to Poker Deluxe 3000! How many players? ");
        int numPlayers = s.nextInt();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\nWhat is your Name? ");
            s.nextLine();
            String name = s.nextLine();
            System.out.println("Hello, " + name + "! How much would you like to start with? ");
            double stack = s.nextDouble();

            board.addPlayer(new Player(stack, name));
        }
    }

    public Board getBoard() {
        return board;
    }
}
