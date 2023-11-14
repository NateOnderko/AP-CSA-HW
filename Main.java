import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Board board = new Board(new Deck());
        board.welcomePlayers();

        board.deal();
        board.displayPlayerStats();
        board.flop();
        System.out.println(board.getCommunityCards());
        board.turnRiver();
        System.out.println(board.getCommunityCards());
        board.turnRiver();
        System.out.println(board.getCommunityCards());

        for (int i = 0; i < board.getPlayers().size(); i++) {
            board.handStrength(board.getPlayers().get(i));
        }


    }
}
