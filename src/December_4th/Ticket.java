package December_4th;

import java.util.ArrayList;

public class Ticket {
    int numberOfTickets = 1;
    ArrayList<String> yourNumbers = new ArrayList<>();
    ArrayList<String> winningNumbers = new ArrayList<>();

    public Ticket(){
    }

    public void incrementNumbersOfTickets(){
        numberOfTickets++;
    }
    public void addToYourNumbers(String s){
        yourNumbers.add(s);
    }
    public void addToWinningNumbers(String s){
        winningNumbers.add(s);
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public ArrayList<String> getYourNumbers() {
        return yourNumbers;
    }

    public ArrayList<String> getWinningNumbers() {
        return winningNumbers;
    }
}
