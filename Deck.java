import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> DECK = new ArrayList<>();
    private final String[] SUITS = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private final String[] CARD_NAMES = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private final Random RAND = new Random();

    public Deck() {
        resetDeck();
    }

    public void resetDeck() {
        DECK.clear();
        for (String suit : SUITS) {
            for (int i = 0; i < CARD_NAMES.length; i++) {
                DECK.add(new Card(CARD_NAMES[i], suit, i + 2));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return DECK;
    }

    public Card pickCard() {
        int cardIndex = RAND.nextInt(DECK.size());
        Card cardPicked = DECK.get(cardIndex);
        DECK.remove(cardIndex);

        return cardPicked;
    }
}
