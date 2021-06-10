import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    Deck deck = new Deck();

    public Blackjack() {// Constructor will handle window creation and important instance variable creations
        game();
    }
    public void game(){
        ArrayList<Card> userHand = new ArrayList<Card>();
        ArrayList<Card> cpuHand = new ArrayList<Card>();
        Scanner readString = new Scanner(System.in);

        int userWins = 0;
        int cpuWins = 0;
        int ties = 0;
        boolean gameLoop;

        System.out.println("Welcome to BB's Blackjack! Are you ready to play?(y/n)");


        while(true){ // Input Loop
            try{
                gameLoop = readString.nextLine().equalsIgnoreCase("y");
                break;
            }
            catch (Exception e){
                System.out.println("Incorrect Input, try again");
            }
        }

        while(gameLoop){
            userHand.clear();//Ensure hands are empty pre flop
            cpuHand.clear();
            deck.reset();

            int gameResult = 0; //0 - tie, 1 - win, 2 - loss
            userHand.add(deck.draw_card()); // The opening deal
            userHand.add(deck.draw_card());
            cpuHand.add(deck.draw_card());
            cpuHand.add(deck.draw_card());  //

            System.out.println("User Hand : " + hand_to_string(userHand) + "\nUser Value: " + hand_value(userHand));
            System.out.println("\nCPU Hand : " + cpuHand.get(0) + ", " + "??????????" + "\nCPU Value: " + "??????????");



            String hit;
            boolean userTurnEnd = false;
            while(!userTurnEnd){ //This is the meat and potatoes of the user's turn
                System.out.println("\nHit or Stand?(h/s)");
                while(true){// Input loop
                    try{
                        hit = readString.nextLine();
                        break;
                    }
                    catch (Exception e){
                        System.out.println("Incorrect Input, try again");
                    }
                }
                if(hit.equalsIgnoreCase("h")) {
                    userHand.add(deck.draw_card());

                    if (hand_value(userHand) == 21) {
                        System.out.println("User Hand : " + hand_to_string(userHand) + "\nUser Value: " + hand_value(userHand));
                        userTurnEnd = true;
                    }
                    else if (hand_value(userHand) > 21) {
                        System.out.println("User Hand : " + hand_to_string(userHand) + "\nUser Value: " + hand_value(userHand));
                        System.out.println("\nCPU Hand : " + hand_to_string(cpuHand) + "\nCPU Value: " + hand_value(cpuHand));
                        gameResult = 2;//I am able to decide that the cpu wins since the user has now busted and the cpu does not have to draw more cards
                        userTurnEnd = true;
                    }
                    else {
                        System.out.println("\nUser Hand : " + hand_to_string(userHand) + "\nUser Value: " + hand_value(userHand));
                    }
                }
                else if(hit.equalsIgnoreCase("s"))
                {
                    userTurnEnd = true;
                    System.out.println("\nUser Hand : " + hand_to_string(userHand) + "\nUser Value: " + hand_value(userHand));

                }
            }


            //When the user's turn is over, compare hands with cpu and play out cpu hand
            //Going to use a similar process to handle cpu hand.
            boolean cpuTurnEnd = false;
            if(gameResult == 0) {
                while (!cpuTurnEnd) {
                    if (hand_value(cpuHand) == 21) {
                        System.out.println("\nCPU Hand : " + hand_to_string(cpuHand) + "\nCPU Value: " + hand_value(cpuHand));
                        cpuTurnEnd = true;
                    } else if (hand_value(cpuHand) > 21) {
                        System.out.println("\nCPU Hand : " + hand_to_string(cpuHand) + "\nCPU Value: " + hand_value(cpuHand));
                        cpuTurnEnd = true;
                        gameResult = 1; // I can declare that the user won because this code only runs if the user has not busted
                    }
                    else if (hand_value(cpuHand) < 17){
                        cpuHand.add(deck.draw_card());
                    }
                    else{
                        System.out.println("\nCPU Hand : " + hand_to_string(cpuHand) + "\nCPU Value: " + hand_value(cpuHand));
                        cpuTurnEnd = true;
                    }
                }
            }

            //Results checker
            //The game result still being zero means I can declare that neither the computer or the user have busted.
            if(gameResult == 0){
                if(hand_value(cpuHand) > hand_value(userHand))
                {
                    gameResult = 2;
                }
                else if(hand_value(cpuHand) < hand_value(userHand)){
                    gameResult = 1;
                }
                // else, its a tie and the game result remains at 0
            }

            switch(gameResult){
                case 0:
                    ties++;
                    System.out.println("Tie!");
                    break;
                case 1:
                    userWins++;
                    System.out.println("You won!");
                    break;
                case 2:
                    cpuWins++;
                    System.out.println("You lose!");
                    break;
            }

            System.out.println("\nGame Over! Would you like to play again(y/n)");
            while(true){// Input loop for the gameloop condition
                try{
                    gameLoop = readString.nextLine().equalsIgnoreCase("y");
                    break;
                }
                catch (Exception e){
                    System.out.println("Incorrect Input, try again");
                }
            }
        }
        System.out.println("W/L/T: " + userWins + "/" + cpuWins + "/" + ties);

    }

    public int hand_value(ArrayList<Card> hand){
        int card_value = 0;
        int aces = 0; // Will keep track of the amount of aces left to change back into ones.
        for(Card c: hand){
           if(c.getValueName().equals("Ace")) {
               aces++;
           }
            if(card_value + c.getValue() > 21 && aces > 0){
                card_value -= 10;
                aces--;
            }
           card_value += c.getValue();
        }

        return card_value;
    }

    public String hand_to_string(ArrayList<Card> hand){
        String hand_string = "";
        for(int i = 0; i < hand.size(); i++){
            if(i < hand.size() - 1){
                hand_string += hand.get(i) + ", ";
            }
            else{
                hand_string += hand.get(i);
            }
        }
        return hand_string;
    }

}
