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

        System.out.println(board.handStrength(board.getPlayers().get(0)));


    }
}
