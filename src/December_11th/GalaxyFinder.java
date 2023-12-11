package December_11th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GalaxyFinder {

    Path path = Paths.get("src/December_11th/Input");
    char[][] map;
    ArrayList<Galaxy> galaxies = new ArrayList<>();
    double finalResult;

    public void findShortestDistancesPartOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            ArrayList<String> lines = new ArrayList<>();
            while((reader = br.readLine()) != null){
                lines.add(reader);
                if(!reader.contains("#")){
                    lines.add(reader);
                }
            }
            map = new char[lines.size()][lines.get(0).length()];

            for(int i = 0; i < lines.size(); i++){
                for(int j = 0; j < lines.get(0).length(); j++){
                    map[i][j] = lines.get(i).charAt(j);
                }
            }

            int[] verticalGalaxyCounter = new int[map[0].length];
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(map[i][j] == '#'){
                        verticalGalaxyCounter[j]++;
                    }
                }
            }

            int counter = 0;
            for (int i = 0; i < verticalGalaxyCounter.length; i++){
                if(verticalGalaxyCounter[i] == 0){
                    counter++;
                }
            }

            char[][] newMap = new char[map.length][map[0].length+counter];

            for(int i = 0; i < map.length; i++){
                counter = 0;
                for(int j = 0; j < map[i].length; j++){
                    if(verticalGalaxyCounter[j] == 0){
                        newMap[i][j + counter] = '.';
                        counter++;
                    }
                    newMap[i][j + counter] = map[i][j];
                }
            }


            for(int i = 0; i < newMap.length; i++){
                for(int j = 0; j < newMap[i].length; j++){
                    if(newMap[i][j] == '#'){
                        Galaxy temp = new Galaxy(i, j);
                        galaxies.add(temp);
                    }
                }
            }

            for(int i = 0; i < galaxies.size() - 1; i++){
                for(int j = i + 1; j < galaxies.size(); j++){
                    if(galaxies.get(j).getX() < galaxies.get(i).getX()) {
                        for (int k = galaxies.get(j).getX(); k < galaxies.get(i).getX(); k++) {
                            finalResult += 1;
                        }
                    }else{
                        for (int k = galaxies.get(j).getX(); k > galaxies.get(i).getX(); k--) {
                            finalResult += 1;
                        }
                    }
                    if(galaxies.get(j).getY() < galaxies.get(i).getY()) {
                        for (int k = galaxies.get(j).getY(); k < galaxies.get(i).getY(); k++) {
                            finalResult += 1;
                        }
                    }else{
                        for (int k = galaxies.get(j).getY(); k > galaxies.get(i).getY(); k--) {
                            finalResult += 1;
                        }
                    }
                }
            }

            System.out.println(finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void findShortestDistancesPartTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<Integer> verticalCounter = new ArrayList<>();
            ArrayList<Integer> horizontalCounter = new ArrayList<>();
            int counter = 0;
            while((reader = br.readLine()) != null){
                lines.add(reader);
                if(!reader.contains("#")){
                    verticalCounter.add(counter);
                }
                counter++;
            }
            map = new char[lines.size()][lines.get(0).length()];

            for(int i = 0; i < lines.size(); i++){
                for(int j = 0; j < lines.get(0).length(); j++){
                    map[i][j] = lines.get(i).charAt(j);
                }
            }

            int[] horizontalGalaxyCounter = new int[map[0].length];
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(map[i][j] == '#'){
                        horizontalGalaxyCounter[j]++;
                    }
                }
            }

            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[i].length; j++){
                    if(map[i][j] == '#'){
                        Galaxy temp = new Galaxy(i, j);
                        galaxies.add(temp);
                    }
                }
            }

            counter = 0;
            for(int i: horizontalGalaxyCounter){
                if(i == 0){
                    horizontalCounter.add(counter);
                }
                counter++;
            }

            for(int i = 0; i < galaxies.size() - 1; i++){
                for(int j = i + 1; j < galaxies.size(); j++){
                    if(galaxies.get(i).getX() < galaxies.get(j).getX()) {
                        for (int k = galaxies.get(i).getX(); k < galaxies.get(j).getX(); k++) {
                            if (horizontalCounter.contains(k)) {
                                finalResult += 1000000;
                            }else{
                                finalResult++;
                            }
                        }
                    }else{
                        for (int k = galaxies.get(j).getX(); k < galaxies.get(i).getX(); k++) {
                            if (horizontalCounter.contains(k)) {
                                finalResult += 1000000;
                            }else{
                                finalResult++;
                            }
                        }
                    }
                    if(galaxies.get(i).getY() < galaxies.get(j).getY()) {
                        for (int k = galaxies.get(i).getY(); k < galaxies.get(j).getY(); k++) {
                            if (verticalCounter.contains(k)) {
                                finalResult += 1000000;
                            }else{
                                finalResult++;
                            }
                        }
                    }else{
                        for (int k = galaxies.get(j).getY(); k< galaxies.get(i).getY(); k++) {
                            if (verticalCounter.contains(k)) {
                                finalResult += 1000000;
                            }else{
                                finalResult++;
                            }
                        }
                    }
                }
            }

            System.out.printf("%.0f\n", finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
