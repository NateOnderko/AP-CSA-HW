import java.util.ArrayList;

public class Board {

    private final ArrayList<Card> COMMUNITY_CARDS = new ArrayList<>();
    private final ArrayList<Card> DISCARD_PILE = new ArrayList<>();
    private final ArrayList<Player> PLAYERS = new ArrayList<>();
    private final Deck DECK;
    private double pot;

    public Board(Deck d) {
        DECK = d;
        resetBoard();
    }

    public void resetBoard() {
        COMMUNITY_CARDS.clear();
        DISCARD_PILE.clear();
        DECK.resetDeck();
        pot = 0;
    }

    public void flop() {
        DISCARD_PILE.add(DECK.pickCard());
        COMMUNITY_CARDS.add(DECK.pickCard());
        COMMUNITY_CARDS.add(DECK.pickCard());
        COMMUNITY_CARDS.add(DECK.pickCard());
    }

    public void turnRiver() {
        DISCARD_PILE.add(DECK.pickCard());
        COMMUNITY_CARDS.add(DECK.pickCard());
    }

    public void deal() {
        for (Player p : PLAYERS) {
            p.resetHand(DECK);
        }
    }

    public ArrayList<Card> getCommunityCards() {
        return COMMUNITY_CARDS;
    }

    public ArrayList<Card> getDiscardPile() {
        return DISCARD_PILE;
    }

    public void addBet(double b) {
        pot += b;
    }

    public void addPlayer(Player p) {
        PLAYERS.add(p);
    }

    public void displayPlayerStats() {
        int i = 1;
        for (Player p : PLAYERS) {
            System.out.println("Player " + i + ": " + p);
            i += 1;
        }
    }
}
