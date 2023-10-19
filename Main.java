public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.getPlayers();
        game.getBoard().deal();
        game.getBoard().displayPlayerStats();
    }
}
