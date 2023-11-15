import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


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


    public boolean checkStraight(ArrayList<Card> tempHand) {
        int oldVal = tempHand.get(0).getCardVal();

        for (int i = 1; i < 5; i++) {
            int tempVal = tempHand.get(i).getCardVal();
            if (tempVal == oldVal - 1 || tempVal == 5 && oldVal == 14) {
                oldVal = tempVal;
            }
            else {
                return false;
            }
        }

        return true;
    }

    public boolean checkFlush(ArrayList<Card> tempHand) {
        String flushSuit = tempHand.get(0).getSuit();

        for (Card c : tempHand) {
            if (! c.getSuit().equals(flushSuit)) {
                return false;
            }
        }

        return true;
    }

    public void handStrength(Player p) {
        ArrayList<Card> cardList = new ArrayList<>();

        cardList.addAll(p.getHand());
        cardList.addAll(COMMUNITY_CARDS);

        // sort list max - min

        int size = 7;

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

        System.out.println("Card List: " + cardList);

        // check pairs, trips, quads

        ArrayList<ArrayList<Card>> pairs = new ArrayList<>();
        ArrayList<ArrayList<Card>> trips = new ArrayList<>();
        ArrayList<ArrayList<Card>> quads = new ArrayList<>();


        ArrayList<Card> tempPairs = new ArrayList<>();
        tempPairs.add(cardList.get(0));

        for (int i = 1; i < size; i++) {
            if (cardList.get(i).getCardVal() == tempPairs.get(tempPairs.size() - 1).getCardVal()) {
                tempPairs.add(cardList.get(i));
            }
            if (cardList.get(i).getCardVal() != tempPairs.get(tempPairs.size() - 1).getCardVal() || i == size - 1) {
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

        ArrayList<Card> tempCardList = new ArrayList<>();

        ArrayList<ArrayList<Card>> straights = new ArrayList<>();
        ArrayList<ArrayList<Card>> flushes = new ArrayList<>();
        ArrayList<ArrayList<Card>> straightFlushes = new ArrayList<>();

        double handVal = 0;
        int highCard;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                tempCardList.addAll(cardList);
                tempCardList.remove(Math.max(i, j));
                tempCardList.remove(Math.min(i, j));

                if (tempCardList.get(0).getCardVal() == 14 && tempCardList.get(1).getCardVal() == 5) {
                    highCard = 3;
                }

                else {
                    highCard = tempCardList.get(0).getCardVal() - 2;
                }


                if (checkStraight(tempCardList) && checkFlush(tempCardList)) {
                    straightFlushes.add(copyList(tempCardList));
                    handVal = Math.max(handVal, 8 * 13 + highCard);
                }

                else if (! quads.isEmpty()) {
                    if (tempCardList.get(0).getCardVal() == quads.get(0).get(0).getCardVal()) {
                        highCard = tempCardList.get(4).getCardVal();
                    }
                    else {
                        highCard = tempCardList.get(0).getCardVal();
                    }
                    handVal = Math.max(handVal, 7 * 13 + quads.get(0).get(0).getCardVal() - 2 + 0.01 * highCard);
                }

                else if (! trips.isEmpty() && ! pairs.isEmpty()) {
                    handVal = Math.max(handVal, 6 * 13 + trips.get(0).get(0).getCardVal() - 2 + 0.01 * (pairs.get(0).get(0).getCardVal() - 2));
                }

                else if (checkFlush(tempCardList)) {
                    flushes.add(copyList(tempCardList));
                    handVal = Math.max(handVal, 5 * 13 + highCard);
                }

                else if (checkStraight(tempCardList)) {
                    straights.add(copyList(tempCardList));
                    handVal = Math.max(handVal, 4 * 13 + highCard);
                }

                else if (! trips.isEmpty()) {
                    if (tempCardList.get(0).getCardVal() == trips.get(0).get(0).getCardVal()) {
                        highCard = tempCardList.get(3).getCardVal();
                    }
                    else {
                        highCard = tempCardList.get(0).getCardVal();
                    }
                    handVal = Math.max(handVal, 3 * 13 + trips.get(0).get(0).getCardVal() - 2 + 0.01 * highCard);
                }

                else if (pairs.size() >= 2) {
                    // two-pair
                }

                else if (! pairs.isEmpty()) {
                    if (tempCardList.get(0).getCardVal() == pairs.get(0).get(0).getCardVal()) {
                        highCard = tempCardList.get(2).getCardVal();
                    }
                    else {
                        highCard = tempCardList.get(0).getCardVal();
                    }
                    handVal = Math.max(handVal, 3 * 13 + pairs.get(0).get(0).getCardVal() - 2 + 0.01 * highCard);
                }

                else {
                    handVal = Math.max(handVal, tempCardList.get(0).getCardVal());
                }

                tempCardList.clear();
            }
        }

        System.out.println("Straights: " + straights);
        System.out.println("Flushes: " + flushes);

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
