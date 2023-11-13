import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Board board = new Board(new Deck());
        board.welcomePlayers();

//        board.deal();
//        board.displayPlayerStats();
//        board.flop();
//        System.out.println(board.getCommunityCards());
//        board.turnRiver();
//        System.out.println(board.getCommunityCards());
//        board.turnRiver();
//        System.out.println(board.getCommunityCards());
//
//        for (int i = 0; i < board.getPlayers().size(); i++) {
//            board.handStrength(board.getPlayers().get(i));
//        }

        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card("Ace", "Hearts", 14));
        cardList.add(new Card("Ace", "Spades", 14));
        cardList.add(new Card("King", "Hearts", 13));
        cardList.add(new Card("King", "Spades", 13));
        cardList.add(new Card("Queen", "Hearts", 12));
        cardList.add(new Card("Jack", "Hearts", 11));
        cardList.add(new Card("Ten", "Hearts", 10));

        ArrayList<ArrayList<Card>> straights = new ArrayList<>();
        ArrayList<ArrayList<Card>> tempStraights = new ArrayList<>();

        for (int i = 0; i < cardList.size() - 4; i++) {
            tempStraights.clear();
            straights.addAll(board.checkStraight(cardList, tempStraights, i));
        }

        System.out.println("Straights: " + straights);


    }
}
