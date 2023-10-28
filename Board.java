import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.Collections;

public class Board {

    private final ArrayList<Card> COMMUNITY_CARDS = new ArrayList<>();
    private final ArrayList<Card> DISCARD_PILE = new ArrayList<>();
    private final ArrayList<Player> PLAYERS = new ArrayList<>();
    private final Scanner SCANNER = new Scanner(System.in);
    private final Deck DECK;
    private double pot;
    private double activeBet;
    private int dealerButton = 0;


    public Board(Deck d) {
        DECK = d;
        resetBoard();
    }

    public void resetBoard() {
        COMMUNITY_CARDS.clear();
        DISCARD_PILE.clear();
        DECK.resetDeck();
        pot = 0;
        activeBet = 0;

        if (PLAYERS.size() > 1) {
            dealerButton = (dealerButton + 1) % PLAYERS.size();
        }
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

    public ArrayList<Player> getPlayers() {
        return PLAYERS;
    }

    public void addBet(double b) {
        pot += b;
    }

    public void addPlayer(Player p) {
        PLAYERS.add(p);
    }

    public void welcomePlayers() {
        System.out.println("Welcome to Poker Deluxe 3000! How many players? ");
        int numPlayers = SCANNER.nextInt();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\nWhat is your Name? ");
            SCANNER.nextLine();
            String name = SCANNER.nextLine();
            System.out.println("Hello, " + name + "! How much would you like to start with? ");
            double stack = SCANNER.nextDouble();

            addPlayer(new Player(stack, name));
        }
    }

    public void displayPlayerStats() {
        int i = 1;
        for (Player p : PLAYERS) {
            System.out.println("Player " + i + ": " + p);
            i += 1;
        }
    }

    public void handStrength(Player p) {
        ArrayList<Card> cardList = new ArrayList<>();

        cardList.addAll(p.getHand());
        cardList.addAll(COMMUNITY_CARDS);

        ArrayList<Integer> cardValList = new ArrayList<>();

        for (Card c : cardList) {
            cardValList.add(c.getCardVal());
        }

        Collections.sort(cardValList);

        // check for pairs, three of a kind, and quads

        Map<Integer, Integer> cardDict = new HashMap<>();;

        ArrayList<Integer> pairs = new ArrayList<>();
        ArrayList<Integer> trips = new ArrayList<>();
        ArrayList<Integer> quads = new ArrayList<>();

        for (Integer cardVal: cardValList) {
            if (cardDict.get(cardVal) == null) {
                cardDict.put(cardVal, 1);
            }
            else {
                cardDict.put(cardVal, cardDict.get(cardVal) + 1);
            }
        }

        for (Integer v : cardDict.keySet()) {
            int cardNum = cardDict.get(v);

            if (cardNum == 2) {
                pairs.add(v);
            }
            else if (cardNum == 3) {
                trips.add(v);
            }
            else if (cardNum == 4) {
                quads.add(v);
            }
        }

        System.out.println("Pairs: " + pairs);
        System.out.println("Trips: " + trips);
        System.out.println("Quads: " + quads);

        // check for straight

        Set<Integer> cardValSet = new HashSet<>(cardValList);

        ArrayList<Integer> cardValListNoDuplicate = new ArrayList<>(cardValSet);

        int index = cardValListNoDuplicate.size() - 1;
        int checker = cardValListNoDuplicate.get(index) - 1;
        int straightVal = 1;

        ArrayList<Integer> straights = new ArrayList<>();

        while (checker >= 2 && index >= 0) {
            if (straightVal == 5 || ! cardValListNoDuplicate.contains(checker)) {
                if (straightVal == 5) {
                    straights.add(checker + 1);
                }

                if (index == 0) {
                    break;
                }

                index -= 1;
                checker = cardValListNoDuplicate.get(index) - 1;
                straightVal = 1;
            }
            else {
                checker -= 1;
                straightVal += 1;
            }

        }

        System.out.println("Straights: " + straights);
    }

    public void bettingRound(int start) {
        for (int i = 0; i < PLAYERS.size(); i++, start++) {
            if (activeBet - PLAYERS.get(start).getBet() > 0) {
                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Would you like to (f) Fold, (g) Call, or (r) Raise?");
            }
            else {
                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Would you like to (f) Fold, (c) Check, or (b) Bet/Raise?");
            }

            String input = SCANNER.nextLine().toLowerCase();

            if (input.equals("f")) {

            }
            else if (input.equals("c")) {
                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Checked.");
            }
            else if (input.equals("g")) {
                PLAYERS.get(start).makeBet(activeBet - PLAYERS.get(start).getBet());
                pot += activeBet - PLAYERS.get(start).getBet();

                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Called.");
            }
            else if (input.equals("b")) {
                System.out.println("How much would you like to bet? ");
                double bet = SCANNER.nextDouble();
                PLAYERS.get(start).makeBet(bet);
                activeBet += bet;
                pot += bet;

                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Bet " + activeBet + ".");
            }
            else if (input.equals("r")) {
                System.out.println("How much would you like to raise? ");
                double raise = SCANNER.nextDouble();
                PLAYERS.get(start).makeBet(activeBet + raise);
                activeBet += raise;
                pot += raise;

                System.out.println("Player " + (start % PLAYERS.size() + 1) + ": " + "Raised to " + activeBet + ".");
            }
        }
    }
}
