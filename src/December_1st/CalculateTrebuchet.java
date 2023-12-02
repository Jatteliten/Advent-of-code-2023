package December_1st;

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
        Path path = Paths.get("src/December_1st/1Input");
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
        line = line.replace("oneight", "18");
        line = line.replace("twone", "21");
        line = line.replace("one", "1");
        line = line.replace("eightwo", "82");
        line = line.replace("two", "2");
        line = line.replace("threeight", "38");
        line = line.replace("eighthree", "83");
        line = line.replace("three", "3");
        line = line.replace("four", "4");
        line = line.replace("fiveight", "58");
        line = line.replace("five", "5");
        line = line.replace("six", "6");
        line = line.replace("seven", "7");
        line = line.replace("nineight", "98");
        line = line.replace("eight", "8");
        line = line.replace("nine", "9");
        return line;
    }

}