package December_8th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FollowMap {
    Path path = Paths.get("src/December_8th/Input");
    ArrayList<Location> locations = new ArrayList<>();
    double finalResult;

    public void walkDayOne(){
        try(BufferedReader bf = Files.newBufferedReader(path)){
            String instructions = bf.readLine();
            bf.readLine();

            String reader;
            while((reader = bf.readLine()) != null){
                locations.add(new Location(reader));
            }

            String searchFor = "AAA";
            Location currentLocation = new Location();
            for(Location l: locations){
                if(l.name.equals(searchFor)){
                    currentLocation = l;
                    break;
                }
            }

            while(!currentLocation.name.equals("ZZZ")){
                for(int i = 0; i < instructions.length(); i++){
                    if(instructions.charAt(i) == 'L'){
                        searchFor = currentLocation.getLocations().get(0);
                    }else{
                        searchFor = currentLocation.getLocations().get(1);
                    }
                    for(Location l: locations){
                        if (l.name.equals(searchFor)){
                            currentLocation = l;
                            break;
                        }
                    }
                    finalResult++;
                    if(currentLocation.name.equals("ZZZ")){
                        break;
                    }
                }
            }
            System.out.println(finalResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void walkDayTwo(){
        try(BufferedReader bf = Files.newBufferedReader(path)){
            String instructions = bf.readLine();
            bf.readLine();

            String reader;
            while((reader = bf.readLine()) != null){
                locations.add(new Location(reader));
            }

            char searchFor = 'A';
            ArrayList<Location> currentLocations = new ArrayList<>();
            for(Location l: locations){
                if(l.name.charAt(2) == searchFor){
                    currentLocations.add(l);
                }
            }
            double[] locationsZ = new double[currentLocations.size()];
            double[] locationsZFound = new double[currentLocations.size()];

            boolean endFound = false;
            ArrayList<Location> tempLocations = new ArrayList<>();

            while(!endFound){
                for(int i = 0; i < instructions.length(); i++){
                    if(instructions.charAt(i) == 'L'){
                        for(Location l: currentLocations){
                            for(Location ll: locations){
                                if(l.getLocations().get(0).equals(ll.getName())){
                                    tempLocations.add(ll);
                                    break;
                                }
                            }
                        }
                    }else{
                        for(Location l: currentLocations){
                            for(Location ll: locations){
                                if(l.getLocations().get(1).equals(ll.getName())){
                                    tempLocations.add(ll);
                                    break;
                                }
                            }
                        }
                    }

                    currentLocations.clear();
                    currentLocations.addAll(tempLocations);
                    tempLocations.clear();

                    int counter = 0;
                    for(Location l: currentLocations){
                        if(l.name.charAt(2) != 'Z'){
                            locationsZ[counter]++;
                        }else{
                            if(locationsZFound[counter] == 0) {
                                locationsZFound[counter] = locationsZ[counter];
                            }
                        }
                        counter++;
                    }
                    endFound = true;
                    for(Double d: locationsZFound){
                        if(d == 0){
                            endFound = false;
                        }
                    }
                    if(endFound){
                        for(Double d: locationsZFound){
                            System.out.println(d + 1);
                            //Feed this into LCM-calculator
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
