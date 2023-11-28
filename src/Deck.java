import java.util.ArrayList;

public class Deck {
    private int cardsLeft;
    private ArrayList<Card> cards;

    public Deck(String[] rank, String[] suits, int[] point)
    {
        cards = new ArrayList<Card>();
        for (int i = 0; i < rank.length; i++){
            for (String suit : suits) {
                cards.add(new Card(rank[i], suit, point[i]));
            }
        }
        cardsLeft = cards.size();
    }

    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    public Card deal()
    {
        if(isEmpty())
        {
            shuffle();
        }
    }

    public void shuffle()
    {
        for(int i = cards.size() - 1; i > 0; i--)
        {

        }
    }






}
