import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CalculateTrebuchet {
    public CalculateTrebuchet() throws IOException {
        String read;
        int finalSum = 0;
        int finalNumber;
        Path path = Paths.get("src/testInput");
        BufferedReader br = Files.newBufferedReader(path);

        while((read = br.readLine()) != null){
            read = replaceNumberSubStringWithInt(read);
            int firstNumber = 0;
            int secondNumber = 0;
            boolean firstNumberSet = false;
            for(int i = 0; i < read.length(); i++){
                if(Character.isDigit(read.charAt(i)) && !firstNumberSet){
                    firstNumber = Character.getNumericValue(read.charAt(i)) * 10;
                    firstNumberSet = true;
                }
                if(Character.isDigit(read.charAt(i))){
                    secondNumber = Character.getNumericValue(read.charAt(i));
                }
            }
            finalNumber = firstNumber + secondNumber;
            finalSum += finalNumber;
        }
        System.out.println(finalSum);
    }

    private String replaceNumberSubStringWithInt(String line){
        if(line.contains("one")){
            if(line.contains("oneight")){
                line = line.replace("oneight", "18");
            }
            if(line.contains("twone")){
                line = line.replace("twone", "21");
            }
            line = line.replace("one", "1");
        }
        if(line.contains("two")){
            if(line.contains("eightwo")){
                line = line.replace("eightwo", "82");
            }
            line = line.replace("two", "2");
        }
        if(line.contains("three")){
            if(line.contains("threeight")){
                line = line.replace("threeight", "38");
            }
            if(line.contains("eighthree")){
                line = line.replace("eighthree", "83");
            }
            line = line.replace("three", "3");

        }
        if(line.contains("four")){
            line = line.replace("four", "4");
        }
        if(line.contains("five")){
            if(line.contains("fiveight")){
                line = line.replace("fiveight", "58");
            }
            line = line.replace("five", "5");
        }
        if(line.contains("six")){
            line = line.replace("six", "6");
        }
        if(line.contains("seven")){
            line = line.replace("seven", "7");
        }
        if(line.contains("eight")){
            if(line.contains("nineight")){
                line = line.replace("nineight", "98");
            }
            line = line.replace("eight", "8");
        }
        if(line.contains("nine")){
            line = line.replace("nine", "9");
        }
        return line;
    }
}