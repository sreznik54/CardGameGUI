import java.util.Scanner;
public class Game {
    private Deck deck;
    private Player player;
    public Game() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player's name: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
    }
    public void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Try to get as close to 21 without going over!");
        System.out.println();
    }

    public void playGame(){
        printInstructions();

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
