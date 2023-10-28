public class Main {

    public static void main(String[] args) {

        Board board = new Board(new Deck());
        board.welcomePlayers();

        for (int i = 0; i < 10; i++) {
            board.deal();
            board.displayPlayerStats();
            board.flop();
            System.out.println(board.getCommunityCards());
            board.turnRiver();
            System.out.println(board.getCommunityCards());
            board.turnRiver();
            System.out.println(board.getCommunityCards());

            int j = 1;
            for (Player p : board.getPlayers()) {
                System.out.println("Player " + j + ": ");
                board.handStrength(p);
                j += 1;
            }

            board.resetBoard();
        }

    }
}
