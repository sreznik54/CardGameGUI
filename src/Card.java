public class Card {
    //instance varriables
    private String suit;
    private String rank;
    private int point;
    //constructor
    public Card(String rank, String suit, int point) {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
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
    //write out the card
    public String toString() {
        return rank + " of " + suit;
    }
}
