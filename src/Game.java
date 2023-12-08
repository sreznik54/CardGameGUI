import java.sql.SQLOutput;
import java.util.Scanner;
public class Game {
    //instance varriables
    Scanner s = new Scanner(System.in);
    private Deck deck;
    private Player player;
    private Player dealer;
    //constructor
    public Game() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        System.out.print("Enter player's name: ");
        String playerName = s.nextLine();
        player = new Player(playerName);
        dealer = new Player("dealer");
    }
    //Runs the Game
    public void playGame(){
        //shuffles the deck
        deck.shuffle();
        printInstructions();
        //deals the hands
        startingHand(player);
        boolean play = true;
        boolean win = true;
        boolean toHit = true;
        boolean end = true;
        int bet = 0;
        //starts running the game
        while(play) {
            //gets the players bet
            bet = getBet(player);
            // gets the players final hand
            while (toHit) {
                System.out.println(player.getName() + ": " + player.getHand());
                System.out.println("Do you want to hit? (y/n)");
                String hit = s.nextLine();
                if (hit.equals("y")) {
                    hit();
                }
                if (hit.equals("n")) {
                    toHit = false;
                }
            }
            //figures out if they won
            while (end) {
                if (bust(player)) {
                    win = false;
                    break;
                }
                dealerPlay();
                System.out.println(dealer.getName() + ": " + dealer.getHand());
                if (player.handValue() <= dealer.handValue()) {
                    win = false;
                }
                if (bust(dealer)) {
                    win = true;
                    break;
                }
                end = false;
            }
            //returns the player with their winnings or losings
            bet(player, win, bet);
            System.out.println("Points: " + player.getPoints());
            System.out.println("Do you want to keep playing? (y/n)");
            String toPlay = s.nextLine();
            //sees if the want to play again
            if(toPlay.equals("n")) {
                play = false;
            }
            //resets everything
            end = true;
            win = true;
            toHit = true;
            reset(player);
            reset(dealer);
        }
    }
    //the instructions for the game
    public void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Try to beat the dealer without going over 21");
        System.out.println();
    }
    //adds or subtracts the players points depending on if they won or lost
    public void bet(Player player, boolean win, int bet) {
        if(win) {
            player.addPoints(bet);
        }
        else {
            player.addPoints(-bet);
        }
    }
    //gets the players bet
    public int getBet(Player player) {
        System.out.println(player.getName() +" you have "+ player.getPoints() + " how much do you want to bet:");
        int bet = s.nextInt();
        s.nextLine();
        return bet;
    }
    //deals the starting hand for the player and the dealer
    public void startingHand(Player player) {
        for(int i =0; i < 2; i++) {
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());
        }
    }
    //resets the players hand
    public void reset(Player player) {
        for(int i = 0; i < player.hand.size(); i++) {
            player.hand.remove(i);
        }
    }
    //plays the dealers hand based on black jack rules
    public void dealerPlay() {
        while(dealer.handValue() < 17) {
            dealer.addCard(deck.deal());
        }
    }
    //returns true if the player goes over 21
    public boolean bust(Player player){
        if(player.handValue() > 21) {
            return true;
        }
        return false;
    }
    //adds a card if the player hits
    public void hit(){
        player.addCard(deck.deal());
    }

    //main
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
