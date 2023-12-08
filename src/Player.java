import java.util.ArrayList;

public class Player {
    //instance varriables
    public ArrayList<Card> hand;
    private int points;
    private String name;
    //constructor
    public Player(String name){
        this.points = 1000;
        this.name = name;
        hand = new ArrayList<Card>();
    }
    //adds a card to the players hand
    public void addCard(Card newCard){
        hand.add(newCard);
    }
    // changes the amount of points the player has
    public void addPoints(int points){
        this.points += points;
    }
    //returns the value of the players hand
    public int handValue() {
        int sum = 0;
        for(int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getPoint();
        }
        return sum;
    }
    //returns the name of the player
    public String getName() {
        return name;
    }
    //returns the players hand
    public ArrayList<Card> getHand() {
        return hand;
    }
    //returns the players points
    public int getPoints() {
        return points;
    }
}
