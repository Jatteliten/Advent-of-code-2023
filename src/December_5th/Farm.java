package December_5th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Farm {

    Path path = Paths.get("src/December_5th/Input");
    boolean seedFound = false;

    public void checkLocationPartOne(){
        try (BufferedReader bf = Files.newBufferedReader(path)){
            double[] seeds = readSeedsFromFile(bf);
            double[][] seedToSoil = readRatiosFromFile(bf);
            double[][] soilToFertilizer = readRatiosFromFile(bf);
            double[][] fertilizerToWater = readRatiosFromFile(bf);
            double[][] waterToLight = readRatiosFromFile(bf);
            double[][] lightToTemperature = readRatiosFromFile(bf);
            double[][] temperatureToHumidity = readRatiosFromFile(bf);
            double[][] humidityToLocation = readRatiosFromFile(bf);

            seeds = adjustLocation(seeds, seedToSoil);
            seeds = adjustLocation(seeds, soilToFertilizer);
            seeds = adjustLocation(seeds, fertilizerToWater);
            seeds = adjustLocation(seeds, waterToLight);
            seeds = adjustLocation(seeds, lightToTemperature);
            seeds = adjustLocation(seeds, temperatureToHumidity);
            seeds = adjustLocation(seeds, humidityToLocation);

            double lowestNumber = Double.MAX_VALUE;
            for(double d: seeds){
                if(d < lowestNumber){
                    lowestNumber = d;
                }
            }
            System.out.printf("%.0f\n", lowestNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkLocationPartTwo(){
        try (BufferedReader bf = Files.newBufferedReader(path)){
            double[][] seeds = readSeedsFromFilePartTwo(bf);
            double[][] seedToSoil = readRatiosFromFile(bf);
            double[][] soilToFertilizer = readRatiosFromFile(bf);
            double[][] fertilizerToWater = readRatiosFromFile(bf);
            double[][] waterToLight = readRatiosFromFile(bf);
            double[][] lightToTemperature = readRatiosFromFile(bf);
            double[][] temperatureToHumidity = readRatiosFromFile(bf);
            double[][] humidityToLocation = readRatiosFromFile(bf);

            double counter = 0;
            double number;
            while(true){
                number = counter;
                number = adjustLocationPartTwo(number, humidityToLocation);
                number = adjustLocationPartTwo(number, temperatureToHumidity);
                number = adjustLocationPartTwo(number, lightToTemperature);
                number = adjustLocationPartTwo(number, waterToLight);
                number = adjustLocationPartTwo(number, fertilizerToWater);
                number = adjustLocationPartTwo(number, soilToFertilizer);
                number = adjustLocationPartTwo(number, seedToSoil);
                checkIfSeedExists(number, seeds);
                if(seedFound){
                    break;
                }
                counter++;
            }
            System.out.printf("%.0f\n", counter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double[] readSeedsFromFile(BufferedReader bf) throws IOException {
        String reader = bf.readLine();
        bf.readLine();
        bf.readLine();
        reader = reader.replace("seeds: ", "");
        String[] seedStrings = reader.split(" ");
        double[] seeds = new double[seedStrings.length];
        for(int i = 0; i < seeds.length; i++){
            seeds[i] = Double.parseDouble(seedStrings[i]);
        }
        return seeds;
    }

    private double[][] readRatiosFromFile(BufferedReader bf) throws IOException{
        String reader;
        ArrayList<String> ratiosArrayList = new ArrayList<>();
        while((reader = bf.readLine()) != null){
            if(reader.isEmpty()){
                break;
            }
            ratiosArrayList.add(reader);
        }
        double[][] ratios = new double[ratiosArrayList.size()][3];
        for (int i = 0; i < ratios.length; i++){
            String[] temp = ratiosArrayList.get(i).split(" ");
            ratios[i][0] = Double.parseDouble(temp[0]);
            ratios[i][1] = Double.parseDouble(temp[1]);
            ratios[i][2] = Double.parseDouble(temp[2]);
        }
        bf.readLine();
        return ratios;
    }

    private double[] adjustLocation(double[] seeds, double[][] modifier){
        for(int i = 0; i < seeds.length; i++){
            for(int j = 0; j < modifier.length; j++){
                if(seeds[i] >= modifier[j][1] && seeds[i] <= (modifier[j][1] + modifier[j][2])){
                    seeds[i] -= (modifier[j][1] - modifier[j][0]);
                    break;
                }
            }
        }
        return seeds;
    }

    private double[][] readSeedsFromFilePartTwo(BufferedReader bf) throws IOException {
        String reader = bf.readLine();
        bf.readLine();
        bf.readLine();
        reader = reader.replace("seeds: ", "");
        String[] seedStrings = reader.split(" ");
        double[][] seeds = new double[seedStrings.length/2][2];
        for(int i = 0; i < seeds.length; i++){
            seeds[i][0] = Double.parseDouble(seedStrings[i*2]);
            seeds[i][1] = Double.parseDouble(seedStrings[i*2]) + Double.parseDouble(seedStrings[i*2+1]);
        }
        return seeds;
    }

    private double adjustLocationPartTwo(double number, double[][] modifier){
        for(int i = 0; i < modifier.length; i++){
            if(number >= modifier[i][0] && number <= (modifier[i][0] + modifier[i][2])){
                number += (modifier[i][1] - modifier[i][0]);
                return number;
            }
        }
        return number;
    }

    private void checkIfSeedExists(double number, double[][] seeds){
        for (int i = 0; i < seeds.length; i++){
            if(number > seeds[i][0] && number < seeds[i][1]){
                seedFound = true;
            }
        }
    }
}
