
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private final String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
    private final String[] value_names = {"Two", "Three", "Four", "Five", "Six", "Seven",
                                          "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10 ,10, 11};
    public Deck()
    {
        for(String s : suits){
            for(int i = 0; i < 13; i++){
                    deck.add(new Card(values[i], s, value_names[i]));
            }
        }
    }

    public void reset(){
        for(Card c : deck){
            if(!c.isInDeck())
                c.inverseInDeck();
        }
    }

    public Card draw_card(){
        Card c = deck.get((int)(Math.random() * 52));
        while(!c.isInDeck())
            c = deck.get((int)(Math.random() * 52));
        c.inverseInDeck();
        return c;
    }
}
