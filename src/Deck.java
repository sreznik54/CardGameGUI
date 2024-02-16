import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    //instance varriables
    private int cardsLeft;
    private ArrayList<Card> cards;
    //constuctor
    public Deck(String[] rank, String[] suits, int[] point) {
        cards = new ArrayList<Card>();
        int cardIcon = 1;
        //makes a card for every suit of card
        for (int i = 0; i < rank.length; i++){
            for (String suit : suits) {
                Image c = new ImageIcon("Resources/Cards/" + cardIcon + ".png").getImage();
                cards.add(new Card(rank[i], suit, point[i], c));
                cardIcon++;
            }
        }
        //adds a card to the number of cards in the deck
        cardsLeft = cards.size();
    }
    //returns true if the arraylist is empty
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }
    //returns the amount of cards left
    public int getCardsLeft() {
        return cardsLeft;
    }
    //deals a card
    public Card deal() {
        //if the list is empty shuffles the cards
        if(isEmpty()) {
            shuffle();
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }
    //shuffles the deck of cards
    public void shuffle() {
        Card holder;
        for(int i = cards.size() - 1; i > 0; i--) {
            int randIndex = (int) (Math.random() * (i - 1));
            holder = cards.get(i);
            cards.set(i, cards.get(randIndex));
            cards.set(randIndex, holder);
        }
    }
    //to string for deck
    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
