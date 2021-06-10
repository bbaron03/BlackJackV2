public class Card {
    private int value;
    private String suit;
    private String value_name;
    private boolean in_deck;
    public Card(int value, String suit, String value_name)
    {
        this.value =  value;
        this.suit = suit;
        this.value_name = value_name;
        this.in_deck = true;
    }

    //Accessors
    public String getSuit(){
        return suit;
    }
    public int getValue(){
        return value;
    }
    public String getValueName(){
        return value_name;
    }
    public boolean isInDeck(){
        return in_deck;
    }

    //Mutators
    public void inverseInDeck(){
        in_deck = !in_deck;
    }

    public String toString()
    {
        return value_name + " of " + suit;
    }
}
