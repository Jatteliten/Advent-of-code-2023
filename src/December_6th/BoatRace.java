package December_6th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BoatRace {

    Path path = Paths.get("src/December_6th/Input");

    public void calculateWinsPartOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String timeReader = br.readLine();
            String distanceReader = br.readLine();
            timeReader = timeReader.replace("Time:", "");
            distanceReader = distanceReader.replace("Distance:", "");

            String[] timeStringArray = timeReader.split("\\s+");
            String[] distanceStringArray = distanceReader.split("\\s+");

            ArrayList<Integer> time = new ArrayList<>();
            ArrayList<Integer> distance = new ArrayList<>();

            for(int i = 0; i < timeStringArray.length; i++){
                String timeFromArray = timeStringArray[i];
                String distanceFromArray = distanceStringArray[i];
                if(!timeFromArray.equals("0") && !timeFromArray.isEmpty()){
                    time.add(Integer.parseInt(timeFromArray));
                    distance.add(Integer.parseInt(distanceFromArray));
                }
            }

            double finalResult = 1;
            ArrayList<Integer> wonRaces = new ArrayList<>();

            for(int i = 0; i < time.size(); i++){
                int win = 0;
                int timeLeft = time.get(i);
                int buttonPresses = 0;
                while(timeLeft != 0){
                    buttonPresses++;
                    timeLeft--;
                    double maxDistance = buttonPresses * timeLeft;
                    if(maxDistance > distance.get(i)){
                        win++;
                    }
                }
                wonRaces.add(win);
            }
            for(Integer i: wonRaces){
                finalResult = finalResult * i;
            }
            System.out.println(finalResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calculateWinsPartTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String timeReader = br.readLine();
            String distanceReader = br.readLine();
            timeReader = timeReader.replace("Time:", "");
            timeReader = timeReader.replace(" ", "");
            double time = Double.parseDouble(timeReader);
            distanceReader = distanceReader.replace("Distance:", "");
            distanceReader = distanceReader.replace(" ", "");
            double distance = Double.parseDouble(distanceReader);

            double timeLeft = time;
            double buttonPresses = 0;
            double finalResult = 0;
            while(timeLeft != 1){
                timeLeft--;
                buttonPresses++;
                if(timeLeft * buttonPresses > distance){
                    finalResult++;
                }
            }
            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
