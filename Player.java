import java.util.ArrayList;

public class Player {

    private double stack;
    private double bet;
    private final String NAME;
    private final ArrayList<Card> HAND = new ArrayList<>();

    public Player(double s, String n) {
        stack = s;
        NAME = n;
    }

    public void resetHand(Deck d) {
        bet = 0;
        HAND.clear();
        HAND.add(d.pickCard());
        HAND.add(d.pickCard());
    }

    public ArrayList<Card> getHand() {
        return HAND;
    }

    public double getStack() {
        return stack;
    }

    public String getName() {
        return NAME;
    }

    public String toString() {
        return "Name: " + NAME + ", Stack: " + stack + ", Hand: " + HAND.get(0) + ", and " + HAND.get(1);
    }

    public void makeBet(double b) {
        bet = b;
        stack -= b;
    }

    public double getBet() {
        return bet;
    }
}
