// Sam Reznik CardGameGUI
// AP CS2
// 2/16/2023
import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    // instance variables
    Scanner s = new Scanner(System.in);
    private GameView window;
    private Deck deck;
    private Player player;
    private Player dealer;
    private int stage;
    //constructor
    public Game() {
        window = new GameView(this);
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        stage = 0;
        // Paints the background to start the game
        window.repaint();
        System.out.print("Enter player's name: ");
        String playerName = s.nextLine();
        player = new Player(playerName);
        dealer = new Player("dealer");
    }
    // Runs the Game
    public void playGame(){
        printInstructions();
        // Deals the hands
        boolean play = true;
        boolean win = true;
        boolean toHit = true;
        boolean end = true;
        int bet = 0;
        // Starts running the game
        while(play) {
            // Gets the players bet
            bet = getBet(player);
            deck.shuffle();
            startingHand(player);
            // Changes what stage the game is in and paints that background
            stage = 2;
            window.repaint();
            // Gets the players final hand
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
            // Figures out if they won
            while (end) {
                if (bust(player)) {
                    // If player looses display lose
                    stage = 4;
                    win = false;
                    break;
                }
                dealerPlay();
                System.out.println(dealer.getName() + ": " + dealer.getHand());
                if (player.handValue() <= dealer.handValue()) {
                    // If player looses display lose
                    stage = 4;
                    win = false;
                }
                if (bust(dealer)) {
                    // If player wins display win
                    stage = 3;
                    win = true;
                    break;
                }
                end = false;
            }
            if(win){
                // If player wins display win
                stage = 3;
            }
            // Returns the player with their winnings or losings
            // Displays win or lose
            window.repaint();
            bet(player, win, bet);
            System.out.println("Points: " + player.getPoints());
            System.out.println("Do you want to keep playing? (y/n)");
            String toPlay = s.nextLine();
            // Sees if the want to play again
            if(toPlay.equals("n")) {
                play = false;
            }
            // Resets everything
            end = true;
            win = true;
            toHit = true;
            reset(player);
            reset(dealer);
        }
    }
    // The instructions for the game
    public void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Try to beat the dealer without going over 21");
        System.out.println();
    }
    // Adds or subtracts the players points depending on if they won or lost
    public void bet(Player player, boolean win, int bet) {
        if(win) {
            player.addPoints(bet);
        }
        else {
            player.addPoints(-bet);
        }
    }
    // Gets the players bet
    public int getBet(Player player) {
        // Displays the betting screen in game
        stage = 1;
        window.repaint();
        System.out.println(player.getName() +" you have "+ player.getPoints() + " how much do you want to bet:");
        int bet = s.nextInt();
        s.nextLine();
        return bet;
    }
    // Deals the starting hand for the player and the dealer
    public void startingHand(Player player) {
        for(int i =0; i < 2; i++) {
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());
        }
    }
    // Resets the players hand
    public void reset(Player player) {
        int cardCount = 0;
        while(!player.hand.isEmpty())
        {
            player.hand.remove(cardCount);
        }
    }
    // Plays the dealers hand based on black jack rules
    public void dealerPlay() {
        while(dealer.handValue() < 17) {
            dealer.addCard(deck.deal());
        }
    }
    // Returns true if the player goes over 21
    public boolean bust(Player player){
        if(player.handValue() > 21) {
            return true;
        }
        return false;
    }
    // Adds a card if the player hits
    public void hit(){
        stage = 2;
        player.addCard(deck.deal());
        window.repaint();
    }
    // Returns the stage the game is in
    public int getStage(){
        return stage;
    }
    // Returns players hand
    public ArrayList<Card> getPlayerHand(){
        return player.hand;
    }
    // Returns the dealers Hand
    public ArrayList<Card> getDealerHand(){
        return dealer.hand;
    }
    // Returns the players score
    public int getPlayerScore(){
        return player.getPoints();
    }
    // Returns the players name
    public String getPlayerName(){
        return player.getName();
    }

    //main
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
