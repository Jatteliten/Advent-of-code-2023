package December_4th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScratchTickets {
    ArrayList<String> winningNumbers = new ArrayList<>();
    ArrayList<String> yourNumbers = new ArrayList<>();
    ArrayList<Ticket> rounds = new ArrayList<>();
    Path path = Paths.get("src/December_4th/Input");
    int finalResult = 0;

    public void calculateWinningsPartOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;

            while((reader = br.readLine()) != null){
                reader = reader.substring(reader.indexOf(": ") + 2);
                reader = reader.replace(" |", "");
                for(int i = 0; i < reader.length(); i+=3){
                    if(i < 30){
                        winningNumbers.add(reader.substring(i, i+2));
                    }else{
                        yourNumbers.add(reader.substring(i, i+2));
                    }
                }
                int counter = 0;
                for(String s: yourNumbers){
                    if(winningNumbers.contains(s)){
                        if(counter == 0){
                            counter++;
                        }else{
                            counter = counter*2;
                        }
                    }
                }
                finalResult += counter;
                winningNumbers.clear();
                yourNumbers.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(finalResult);
    }

    public void calculateWinningsPartTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;

            while((reader = br.readLine()) != null){
                reader = reader.substring(reader.indexOf(": ") + 2);
                reader = reader.replace(" |", "");
                Ticket t = new Ticket();
                for(int i = 0; i < reader.length(); i+=3){
                    if(i < 30){
                        t.addToWinningNumbers(reader.substring(i, i+2));
                    }else{
                        t.addToYourNumbers(reader.substring(i, i+2));
                    }
                }
                rounds.add(t);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int winningTickets = 0;
        for(int i = 0; i < rounds.size(); i++){
            for(int j = 0; j < rounds.get(i).getNumberOfTickets(); j++) {
                for (String s : rounds.get(i).getYourNumbers()) {
                    if (rounds.get(i).getWinningNumbers().contains(s)) {
                        winningTickets++;
                    }
                }
                for(int k = 0; k < winningTickets; k++){
                    try{rounds.get(i+k+1).incrementNumbersOfTickets();}
                    catch(IndexOutOfBoundsException e){
                    }
                }
                winningTickets = 0;
            }
        }
        for(Ticket t: rounds){
            finalResult += t.getNumberOfTickets();
        }
        System.out.println(finalResult);
    }
}
