package December_3rd;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class GondolaEngineSchematics {
    Path path = Paths.get("src/December_3rd/Input");
    ArrayList<String> engine = new ArrayList<>();
    ArrayList<Integer> numbers = new ArrayList<>();
    ArrayList<Character> notAllowed = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5',
            '6', '7', '8', '9', '.'));
    ArrayList <Gear> gears = new ArrayList<>();
    ArrayList <Engine> engines = new ArrayList<>();
    String lastLine = "";
    String nextLine = "";
    int finalResult = 0;

    public void checkEnginePartOne() {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String reader;
            while ((reader = bf.readLine()) != null) {
                engine.add("." + reader + ".");
            }
            for(int i = 0; i < engine.size(); i++){
                if(i == 0){
                    lastLine = fillStringWithPeriods();
                }
                if(i != engine.size() - 1){
                    nextLine = engine.get(i + 1);
                }else{
                    nextLine = fillStringWithPeriods();
                }
                for(int j = 0; j < engine.get(i).length(); j++){
                    String num = "";
                    int addIndex = 0;
                    if(Character.isDigit(engine.get(i).charAt(j))){
                        num += engine.get(i).charAt(j);
                        if(Character.isDigit(engine.get(i).charAt(j + 1))){
                            num += engine.get(i).charAt(j + 1);
                            addIndex++;
                            if(Character.isDigit(engine.get(i).charAt(j + 2))){
                                num += engine.get(i).charAt(j + 2);
                                addIndex++;
                            }
                        }
                    }
                    j += addIndex;
                    boolean acceptedEnginePart = false;
                    if(!num.isEmpty()){
                        for(int k = -1; k < num.length() + 1; k++){
                            if(!notAllowed.contains(lastLine.charAt(j - k)) ||
                                !notAllowed.contains(nextLine.charAt(j - k)) ||
                                !notAllowed.contains(engine.get(i).charAt(j + 1)) ||
                                !notAllowed.contains(engine.get(i).charAt(j - num.length()))){
                                acceptedEnginePart = true;
                            }
                        }
                        if(acceptedEnginePart){
                            numbers.add(Integer.valueOf(num));
                        }
                    }
                }
                lastLine = engine.get(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Integer i : numbers){
            finalResult += i;
        }
        System.out.println(finalResult);
    }

    public void checkEnginePartTwo(){
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String reader;
            while ((reader = bf.readLine()) != null) {
                engine.add(reader);
            }
            int y = 0;
            for(String s: engine){
                int x = 0;
                for(int i = 0; i < s.length(); i++){
                    char check = s.charAt(i);
                    if(check == '*'){
                        Gear g = new Gear(x, y);
                        gears.add(g);
                    }else if(Character.isDigit(check)){
                        String engine = String.valueOf(check);
                        int startX = x;
                        if(Character.isDigit(s.charAt(i + 1))){
                            engine += String.valueOf(s.charAt(i + 1));
                            i++;
                            x++;
                            if(Character.isDigit(s.charAt(i + 1))){
                                engine += String.valueOf(s.charAt(i + 1));
                                i++;
                                x++;
                            }
                        }
                        Engine g = new Engine(engine, startX, y);
                        engines.add(g);
                    }
                    x++;
                }
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Gear g: gears){
            int firstEngine = 0;
            int secondEngine = 0;
            boolean firstCheck = false;
            boolean secondCheck = false;
            for (Engine e: engines){
                if(secondCheck) {
                    break;
                }
                for(String s: e.getCoordinates()){
                    if(g.touchingCoordinates.contains(s)){
                        if(firstCheck){
                            secondEngine = e.getEngine();
                            secondCheck = true;
                            break;
                        }
                        firstEngine = e.getEngine();
                        firstCheck = true;
                        break;
                    }
                }
            }
            if(firstCheck && secondCheck){
                finalResult += (firstEngine * secondEngine);
            }
        }
        System.out.println(finalResult);
    }

    private String fillStringWithPeriods() {
        String line = "";
        for (int j = 0; j < engine.get(0).length(); j++) {
            line += ".";
        }
        return line;
    }
}
