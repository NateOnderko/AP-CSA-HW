import java.util.ArrayList;

public class Player {

    private double stack;
    private final ArrayList<Card> HAND = new ArrayList<>();

    public Player(Deck d, double s) {
        stack = s;
        resetHand(d);
    }

    public void resetHand(Deck d) {
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

    public String handString() {
        return HAND.get(0).getCardType() + " of " + HAND.get(0).getSuit() + " and " + HAND.get(1).getCardType() + " of " + HAND.get(1).getSuit();
    }

}
