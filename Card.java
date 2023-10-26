public class Card {

    private final String CARD_NAME;
    private final String SUIT;
    private final int CARD_VAL;

    public Card(String t, String s, int v) {
        CARD_NAME = t;
        SUIT = s;
        CARD_VAL = v;
    }

    public String getCardType() {
        return CARD_NAME;
    }

    public String getSuit() {
        return SUIT;
    }

    public int getCardVal() {
        return CARD_VAL;
    }

    public String toString() {
        return CARD_NAME + " of " + SUIT;
    }
}