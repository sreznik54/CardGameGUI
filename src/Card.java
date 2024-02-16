import java.awt.*;

public class Card {
    //instance varriables
    private String suit;
    private String rank;
    private int point;
    private Image c;
    //constructor
    public Card(String rank, String suit, int point, Image c) {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
        this.c = c;
    }
    //gives the point value of a card
    public int getPoint() {
        return point;
    }
    //gives the rank of a card
    public String getRank() {
        return rank;
    }
    //gives the suit of a card
    public String getSuit() {
        return suit;
    }
    //changes the point value of a card
    public void setPoint(int point) {
        this.point = point;
    }
    //changes the rank of a card
    public void setRank(String rank){
        this.rank = rank;
    }
    //changes the suit of a card
    public void setSuit(String suit){
        this.suit = suit;
    }

    public Image getCardImage() {
        return c;
    }

    public void setC(Image c) {
        this.c = c;
    }

    //write out the card
    public String toString() {
        return rank + " of " + suit;
    }
}
