import java.util.ArrayList;
import java.util.Random;

public class Deck {

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

    private ArrayList<Card> deck = new ArrayList<>();
    private String[] suits = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private String[] cardNames = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private Random rand = new Random();

    public Deck() {
        resetDeck();
    }

    public void resetDeck() {
        deck.clear();
        for (int j = 0; j < suits.length; j++) {
            for (int k = 0; k < cardNames.length; k++) {
                deck.add(new Card(cardNames[k], suits[j]));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card pickCard() {
        int cardIndex = rand.nextInt(deck.size());
        Card cardPicked = deck.get(cardIndex);
        deck.remove(cardIndex);

        return cardPicked;
    }
}
