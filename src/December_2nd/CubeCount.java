package December_2nd;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CubeCount {
    int finalScore = 0;
    Path path = Paths.get("src/December_2nd/testInput");
    public void countCubesPartOne(){
        int allowedRed = 12;
        int allowedGreen = 13;
        int allowedBlue = 14;

        try(BufferedReader bf = Files.newBufferedReader(path)){
            String read;
            int game = 1;

            while((read = bf.readLine()) != null){
                read = read.substring(read.indexOf(":") + 1);
                String [] games = read.split(";");
                boolean validGame = true;

                for(String s: games){
                    String [] sets = s.split(",");

                    for(String ss: sets){
                        if(Character.isDigit(ss.charAt(1)) && Character.isDigit(ss.charAt(2))) {
                            int checker = (Character.getNumericValue(ss.charAt(1))) * 10 +
                                    Character.getNumericValue(ss.charAt(2));

                            if (ss.contains("red") && checker > allowedRed ||
                                    ss.contains("blue") && checker > allowedBlue ||
                                    ss.contains("green") && checker > allowedGreen) {
                                validGame = false;
                                break;
                            }
                        }
                    }
                }
                if(validGame){
                    finalScore += game;
                }
                game++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(finalScore);
    }

    public void countCubesPartTwo(){
        try(BufferedReader bf = Files.newBufferedReader(path)){
            String read;

            while((read = bf.readLine()) != null){
                int redCount = 0;
                int greenCount = 0;
                int blueCount = 0;
                int amountOfCubes;
                read = read.substring(read.indexOf(":") + 1);
                String [] games = read.split(";");

                for(String s: games){
                    String [] sets = s.split(",");

                    for(String ss: sets){
                        if(Character.isDigit(ss.charAt(1)) && Character.isDigit(ss.charAt(2))) {
                            amountOfCubes = (Character.getNumericValue(ss.charAt(1))) * 10 +
                                    Character.getNumericValue(ss.charAt(2));
                        }else{
                            amountOfCubes = Character.getNumericValue(ss.charAt(1));
                        }

                        if (ss.contains("red") && amountOfCubes > redCount){
                            redCount = amountOfCubes;
                        }else if(ss.contains("green") && amountOfCubes > greenCount){
                            greenCount = amountOfCubes;
                        }else if(ss.contains("blue") && amountOfCubes > blueCount){
                            blueCount = amountOfCubes;
                        }
                    }
                }
                finalScore += redCount * greenCount * blueCount;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(finalScore);
    }
}