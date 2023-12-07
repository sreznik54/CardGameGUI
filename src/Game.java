import java.sql.SQLOutput;
import java.util.Scanner;
public class Game {
    Scanner s = new Scanner(System.in);
    private Deck deck;
    private Player player;
    private Player dealer;
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
    public void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Try to beat the dealer without going over 21");
        System.out.println();
    }
    public void bet(Player player, boolean win, int bet)
    {
        if(win)
        {
            player.addPoints(bet);
        }
        else
        {
            player.addPoints(-bet);
        }
    }
    public int getBet(Player player)
    {
        System.out.println(player.getName() +" you have "+ player.getPoints() + " how much do you want to bet:");
        int bet = s.nextInt();
        s.nextLine();
        return bet;
    }
    public void startingHand(Player player)
    {
        for(int i =0; i < 2; i++)
        {
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());
        }
    }

    public void reset(Player player)
    {
        for(int i = 0; i < player.hand.size(); i++)
        {
            player.hand.remove(i);
        }
    }

    public void dealerPlay()
    {
        while(dealer.handValue() < 17)
        {
            dealer.addCard(deck.deal());
        }
    }
    public boolean bust(Player player){
        if(player.handValue() > 21)
        {
            return true;
        }
        return false;
    }

    public void hit(){
        player.addCard(deck.deal());
    }

    public void playGame(){
        deck.shuffle();
        printInstructions();
        startingHand(player);
        boolean play = true;
        boolean win = true;
        boolean toHit = true;
        boolean end = true;
        int bet = 0;
        while(play) {
            bet = getBet(player);
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
            bet(player, win, bet);
            System.out.println("Points: " + player.getPoints());
            System.out.println("Do you want to keep playing? (y/n)");
            String toPlay = s.nextLine();
            if(toPlay.equals("n"))
            {
                play = false;
            }
            end = true;
            win = true;
            toHit = true;
            reset(player);
            reset(dealer);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
