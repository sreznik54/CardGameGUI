import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand;
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

    public int handValue()
    {
        int sum = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            sum += hand.get(i).getPoint();
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }
}
