package December_12th;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpringFinder {
    Path path = Paths.get("src/December_12th/testInput");
    ArrayList<String> lines = new ArrayList<>();
    double finalResult;

    public void findSpring(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            while((reader = br.readLine()) != null){
                String sheet = reader.substring(0, reader.indexOf(" "));
                String springsString = reader.substring(reader.indexOf(" ") + 1);
                String[] springsArray = springsString.split(",");
                int [] springs = new int[springsArray.length];
                for(int i = 0; i < springsArray.length; i++){
                    springs[i] = Integer.parseInt(springsArray[i]);
                }

                for(int i = 0; i < sheet.length(); i++){
                    int counter = 0;
                    boolean check = true;
                    try{
                        for(int j = 0; j < springs.length; j++){
                            check = true;
                            while(sheet.charAt(counter + i) == '.'){
                                counter++;
                            }
                            for(int k = 0; k < springs[j]; k++){
                                if(sheet.charAt(k + counter + i) != '?' && sheet.charAt(k + counter + i) != '#'){
                                    check = false;
                                    break;
                                }else{
                                    System.out.println(i + " + " + counter + " + " + k);
                                    System.out.println(k+counter+i);
                                }
                            }
                            System.out.println();
                            counter += springs[j] + 1;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("hej");
                        break;
                    }
                    if(check){
                        finalResult++;
                    }
                }
                System.out.println("___________");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(finalResult);
    }
}