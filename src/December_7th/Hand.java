package December_7th;

import java.util.ArrayList;

public class Hand {
    String cards;
    int bid;
    int handStrength;
    ArrayList<Integer> cardsList = new ArrayList<>();

    public Hand(String cards, int bid, int day){
        this.cards = cards;
        for(int i = 0; i < cards.length(); i++){
            char card = cards.charAt(i);
            if(Character.isDigit(card)){
                cardsList.add(Character.getNumericValue(card));
            }else{
                cardsList.add(convertStringToInt(card, day));
            }
        }
        this.bid = bid;
    }

    private int convertStringToInt(char card, int day){
        if(day == 1) {
            return switch (card) {
                case 'T' -> 10;
                case 'J' -> 11;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                default -> 0;
            };
        }else{
            return switch (card) {
                case 'T' -> 10;
                case 'J' -> 1;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                default -> 0;
            };
        }
    }

    public int getHandStrength() {
        return handStrength;
    }

    public void setHandStrength(int handStrength) {
        this.handStrength = handStrength;
    }
    public String getCards() {
        return cards;
    }

    public ArrayList<Integer> getCardsList() {
        return cardsList;
    }

    public int getBid() {
        return bid;
    }

}
