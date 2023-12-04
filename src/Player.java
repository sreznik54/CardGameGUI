import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int points;
    private String name;

    public Player(String name)
    {
        this.points = 1000;
        this.name = name;
        hand = new ArrayList<Card>();
    }

    public void addCard(Card newCard){
        hand.add(newCard);
    }
    public void addPoints(int points) {
        this.points += points;
    }


}
