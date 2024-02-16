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
        window.repaint();
        System.out.print("Enter player's name: ");
        String playerName = s.nextLine();
        player = new Player(playerName);
        dealer = new Player("dealer");
    }
    //Runs the Game
    public void playGame(){
        printInstructions();
        //deals the hands
        boolean play = true;
        boolean win = true;
        boolean toHit = true;
        boolean end = true;
        int bet = 0;
        //starts running the game
        while(play) {
            //gets the players bet
            bet = getBet(player);
            deck.shuffle();
            startingHand(player);
            stage = 2;
            window.repaint();
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
                    stage = 4;
                    win = false;
                    break;
                }
                dealerPlay();
                System.out.println(dealer.getName() + ": " + dealer.getHand());
                if (player.handValue() <= dealer.handValue()) {
                    stage = 4;
                    win = false;
                }
                if (bust(dealer)) {
                    stage = 3;
                    win = true;
                    break;
                }
                end = false;
            }
            if(win){
                stage = 3;
            }
            //returns the player with their winnings or losings
            window.repaint();
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
        stage = 1;
        window.repaint();
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
        int cardCount = 0;
        while(!player.hand.isEmpty())
        {
            player.hand.remove(cardCount);
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
        stage = 2;
        player.addCard(deck.deal());
        window.repaint();
    }

    public int getStage(){
        return stage;
    }

    public ArrayList<Card> getPlayerHand(){
        return player.hand;
    }
    public ArrayList<Card> getDealerHand(){
        return dealer.hand;
    }

    //main
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
