public class Card {
    private String suit;
    private String rank;
    private int point;

    public Card(String rank, String suit, int point)
    {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public void setRank(String rank){
        this.rank = rank;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
