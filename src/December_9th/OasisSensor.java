package December_9th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OasisSensor {
    Path path = Paths.get("src/December_9th/Input");
    ArrayList<ArrayList<Double[]>> allReadings = new ArrayList<>();
    double finalResult;

    public void reportDayOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;

            while((reader = br.readLine()) != null){
                ArrayList<Double[]> readings = new ArrayList<>();
                String[] temp = reader.split(" ");
                Double[] tempNumbers = new Double[temp.length];
                for(int i = 0; i < temp.length; i++){
                    tempNumbers[i] = Double.parseDouble(temp[i]);
                }
                readings.add(tempNumbers);
                boolean found = false;
                while(!found){
                    Double[] tempNumbersTwo = new Double[tempNumbers.length];
                    System.arraycopy(tempNumbers, 0, tempNumbersTwo, 0, tempNumbersTwo.length);
                    tempNumbers = new Double[tempNumbersTwo.length - 1];
                    for(int i = 0; i < tempNumbers.length; i++){
                        tempNumbers[i] = tempNumbersTwo[i+1] - tempNumbersTwo[i];
                    }
                    readings.add(tempNumbers);
                    found = true;
                    for(Double i: tempNumbers){
                        if(i != 0){
                            found = false;
                            break;
                        }
                    }
                }
                allReadings.add(readings);
            }

            for (ArrayList<Double[]> list: allReadings) {
                double lastNumber = 0;
                for (int i = list.size() - 1; i >= 0; i--) {
                    lastNumber += list.get(i)[list.get(i).length - 1];
                }
                finalResult += lastNumber;
            }
            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reportDayTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;

            while((reader = br.readLine()) != null){
                ArrayList<Double[]> readings = new ArrayList<>();
                String[] temp = reader.split(" ");
                Double[] tempNumbers = new Double[temp.length];
                for(int i = 0; i < temp.length; i++){
                    tempNumbers[i] = Double.parseDouble(temp[i]);
                }
                readings.add(tempNumbers);
                boolean found = false;
                while(!found){
                    Double[] tempNumbersTwo = new Double[tempNumbers.length];
                    System.arraycopy(tempNumbers, 0, tempNumbersTwo, 0, tempNumbersTwo.length);
                    tempNumbers = new Double[tempNumbersTwo.length - 1];
                    for(int i = 0; i < tempNumbers.length; i++){
                        tempNumbers[i] = tempNumbersTwo[i+1] - tempNumbersTwo[i];
                    }
                    readings.add(tempNumbers);
                    found = true;
                    for(Double i: tempNumbers){
                        if(i != 0){
                            found = false;
                            break;
                        }
                    }
                }
                allReadings.add(readings);
            }

            for (ArrayList<Double[]> list: allReadings) {
                double lastNumber = 0;
                for (int i = list.size() - 1; i >= 0; i--) {
                    lastNumber = list.get(i)[0] - lastNumber;
                }
                finalResult += lastNumber;
            }
            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
