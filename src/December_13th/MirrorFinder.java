package December_13th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class MirrorFinder {

    Path path = Paths.get("src/December_13th/testInput");
    double finalResult = 0;
    int points = 0;
    boolean found = false;
    boolean firstCheck = true;

    public void findMirrorsPartOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            ArrayList<String> input = new ArrayList<>();
            while((reader = br.readLine()) != null){
                points = 0;
                if(!reader.isEmpty()){
                    input.add(reader);
                }else{
                    firstCheck = true;
                    findMirrors(input);
                    if(points == 0){
                        firstCheck = false;
                        input = flipMirrors(input);
                        findMirrors(input);
                    }
                    input.clear();
                }
                finalResult += points;
                points = 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(finalResult);
    }

    private ArrayList<String> flipMirrors(ArrayList<String> input){
        ArrayList<String> temp = new ArrayList<>();
        Collections.reverse(input);
        for(int i = 0; i < input.get(0).length(); i++){
            StringBuilder flipped = new StringBuilder();

            for(String s: input){
                flipped.append(s.charAt(i));
            }
            temp.add(String.valueOf(flipped));
        }
        return temp;
    }

    private void findMirrors(ArrayList<String> input){
        for(int i = 1; i < input.size(); i++){
            found = false;
            int counter = 1;
            for(int j = i; j < input.size(); j++){
                try{
                    if(input.get(j).equals(input.get(j - counter))){
                        found = true;
                    }else{
                        found = false;
                        break;
                    }
                    counter+=2;
                }catch(IndexOutOfBoundsException e){
                    if(found){
                        break;
                    }
                }
            }
            if(found){
                if(firstCheck) {
                    points += i * 100;
                }else{
                    points += i;
                }
                break;
            }else{
            }
        }
    }

}
