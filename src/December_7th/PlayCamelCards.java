package December_7th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class PlayCamelCards {
    Path path = Paths.get("src/December_7th/Input");
    ArrayList<Hand> hands = new ArrayList<>();
    double finalResult;
    public void playDayOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            while((reader = br.readLine()) != null){
                Hand temp = new Hand(reader.substring(0, 5), Integer.parseInt(reader.substring(6)), 1);
                hands.add(temp);
            }
            for(Hand h: hands){
                int[] handValues = new int[14];
                boolean pairFound = false;
                boolean secondPairFound = false;
                boolean tripleFound = false;
                boolean houseFound = false;
                boolean quadFound = false;
                boolean quintFound = false;
                for(Integer i: h.getCardsList()){
                    handValues[i - 1]++;
                }
                for(int i: handValues){
                    if(i == 2){
                        if(pairFound){
                            secondPairFound = true;
                            pairFound = false;
                            break;
                        }else if(tripleFound){
                            houseFound = true;
                            tripleFound = false;
                            break;
                        }else {
                            pairFound = true;
                        }
                    }else if(i == 3){
                        if(pairFound){
                            houseFound = true;
                            pairFound = false;
                            break;
                        }
                        tripleFound = true;
                    }else if(i == 4){
                        quadFound = true;
                        break;
                    }else if(i == 5){
                        quintFound = true;
                        break;
                    }
                }
                if(pairFound){
                    h.setHandStrength(1);
                }else if(secondPairFound){
                    h.setHandStrength(2);
                }else if(tripleFound){
                    h.setHandStrength(3);
                }else if(houseFound){
                    h.setHandStrength(4);
                }else if(quadFound){
                    h.setHandStrength(5);
                }else if(quintFound){
                    h.setHandStrength(6);
                }else{
                    h.setHandStrength(0);
                }
            }
            hands.sort((h1, h2) -> Integer.compare(h2.getHandStrength(), h1.getHandStrength()));

            for(int i = 0; i < hands.size() - 1; i++){
                for(int j = 0; j < hands.size() - 1; j++){
                    if(hands.get(j).getHandStrength() == hands.get(j + 1).getHandStrength()){
                        for(int k = 0; k < hands.get(j).getCardsList().size(); k++){
                            if(hands.get(j).getCardsList().get(k) < hands.get(j + 1).getCardsList().get(k)){
                                Collections.swap(hands, j, j + 1);
                                break;
                            }else if(hands.get(j).getCardsList().get(k) > hands.get(j + 1).getCardsList().get(k)){
                                break;
                            }
                        }
                    }
                }
            }

            Collections.reverse(hands);
            for(int i = 0; i < hands.size(); i++){
                finalResult += hands.get(i).getBid() * (i + 1);
            }

            for(Hand h: hands){
                System.out.println(h.getCards());
            }

            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playDayTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            while((reader = br.readLine()) != null){
                Hand temp = new Hand(reader.substring(0, 5), Integer.parseInt(reader.substring(6)), 2);
                hands.add(temp);
            }
            for(Hand h: hands){
                int[] handValues = new int[14];
                boolean pairFound = false;
                boolean secondPairFound = false;
                boolean tripleFound = false;
                boolean houseFound = false;
                boolean quadFound = false;
                boolean quintFound = false;
                int wildCards = 0;
                for(Integer i: h.getCardsList()){
                    if(i == 1){
                        wildCards++;
                    }else {
                        handValues[i - 1]++;
                    }
                }
                for(int i: handValues){
                    if(i == 2){
                        if(pairFound){
                            secondPairFound = true;
                            pairFound = false;
                            break;
                        }else if(tripleFound){
                            houseFound = true;
                            tripleFound = false;
                            break;
                        }else {
                            pairFound = true;
                        }
                    }else if(i == 3){
                        if(pairFound){
                            houseFound = true;
                            pairFound = false;
                            break;
                        }
                        tripleFound = true;
                    }else if(i == 4){
                        quadFound = true;
                        break;
                    }else if(i == 5){
                        quintFound = true;
                        break;
                    }
                }
                if(pairFound){
                    if(wildCards == 3){
                        h.setHandStrength(6);
                    }else if(wildCards == 2){
                        h.setHandStrength(5);
                    }else if(wildCards == 1){
                        h.setHandStrength(3);
                    }else{
                        h.setHandStrength(1);
                    }
                }else if(secondPairFound){
                    if(wildCards == 1){
                        h.setHandStrength(4);
                    }else{
                        h.setHandStrength(2);
                    }
                }else if(tripleFound){
                    if(wildCards == 2){
                        h.setHandStrength(6);
                    }else if(wildCards == 1){
                        h.setHandStrength(5);
                    }else{
                        h.setHandStrength(3);
                    }
                }else if(houseFound){
                    h.setHandStrength(4);
                }else if(quadFound){
                    if(wildCards == 1){
                        h.setHandStrength(6);
                    }else{
                        h.setHandStrength(5);
                    }
                }else if(quintFound){
                    h.setHandStrength(6);
                }else{
                    if(wildCards == 1){
                        h.setHandStrength(1);
                    }else if(wildCards == 2){
                        h.setHandStrength(3);
                    }else if(wildCards == 3){
                        h.setHandStrength(5);
                    }else if(wildCards == 4){
                        h.setHandStrength(6);
                    }else if(wildCards == 5){
                        h.setHandStrength(6);
                    }else{
                        h.setHandStrength(0);
                    }
                }
            }
            hands.sort((h1, h2) -> Integer.compare(h2.getHandStrength(), h1.getHandStrength()));

            for(int i = 0; i < hands.size() - 1; i++){
                for(int j = 0; j < hands.size() - 1; j++){
                    if(hands.get(j).getHandStrength() == hands.get(j + 1).getHandStrength()){
                        for(int k = 0; k < hands.get(j).getCardsList().size(); k++){
                            if(hands.get(j).getCardsList().get(k) < hands.get(j + 1).getCardsList().get(k)){
                                Collections.swap(hands, j, j + 1);
                                break;
                            }else if(hands.get(j).getCardsList().get(k) > hands.get(j + 1).getCardsList().get(k)){
                                break;
                            }
                        }
                    }
                }
            }

            Collections.reverse(hands);
            for(int i = 0; i < hands.size(); i++){
                finalResult += hands.get(i).getBid() * (i + 1);
            }

            for(Hand h: hands){
                System.out.println(h.getCards());
            }

            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
