public class Card {

    private String cardType;
    private String suit;

    public Card(String t, String s) {
        cardType = t;
        suit = s;
    }

    public String getCardType() {
        return cardType;
    }

    public String getSuit() {
        return suit;
    }
}