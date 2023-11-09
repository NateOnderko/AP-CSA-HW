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

        // sort list max - min

        int size = cardList.size();

        for (int i = 0; i < size; i++) {

            int maxIndex = i;

            for (int j = i + 1; j < size; j++) {
                if (cardList.get(j).getCardVal() > cardList.get(maxIndex).getCardVal()) {
                    maxIndex = j;
                }
            }

            Card tempCard = cardList.get(i);
            cardList.set(i, cardList.get(maxIndex));
            cardList.set(maxIndex, tempCard);
        }

        for (Card c : cardList) {
            System.out.println(c);
        }

        // check pairs, trips, quads

        ArrayList<ArrayList<Card>> pairs = new ArrayList<>();
        ArrayList<ArrayList<Card>> trips = new ArrayList<>();
        ArrayList<ArrayList<Card>> quads = new ArrayList<>();


        ArrayList<Card> tempPairs = new ArrayList<>();
        tempPairs.add(cardList.get(0));

        for (int i = 1; i < size; i++) {
            System.out.println(tempPairs);
            if (cardList.get(i).getCardVal() == tempPairs.get(tempPairs.size() - 1).getCardVal()) {
                tempPairs.add(cardList.get(i));
            }
            else {
                int pairLen = tempPairs.size();
                if (pairLen == 2) {
                    pairs.add(copyList(tempPairs));
                }
                else if (pairLen == 3) {
                    trips.add(copyList(tempPairs));
                }
                else if (pairLen == 4) {
                    quads.add(copyList(tempPairs));
                }

                tempPairs.clear();
                tempPairs.add(cardList.get(i));
            }
        }

        System.out.println("Pairs: " + pairs);
        System.out.println("Trips: " + trips);
        System.out.println("Quads: " + quads);
    }


    public ArrayList<Card> copyList(ArrayList<Card> c) {
        ArrayList<Card> newList = new ArrayList<>();

        for(Card card : c)
        {
            newList.add(card);
        }

        return newList;
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
