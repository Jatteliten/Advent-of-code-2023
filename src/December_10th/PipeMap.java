package December_10th;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PipeMap extends JFrame {
    Path path = Paths.get("src/December_10th/Input");
    ArrayList<String> mapSections = new ArrayList<>();
    double finalResult;
    ImageIcon vertical = new ImageIcon("src/December_10th/Images/vertical.png");
    ImageIcon horisontal = new ImageIcon("src/December_10th/Images/horisontal.png");
    ImageIcon leftDown = new ImageIcon("src/December_10th/Images/left-down.png");
    ImageIcon leftUp = new ImageIcon("src/December_10th/Images/left-up.png");
    ImageIcon rightDown = new ImageIcon("src/December_10th/Images/right-down.png");
    ImageIcon rightUp = new ImageIcon("src/December_10th/Images/right-up.png");
    ImageIcon red = new ImageIcon("src/December_10th/Images/red.png");
    ImageIcon S = new ImageIcon("src/December_10th/Images/S.png");

    public void findCreaturePartOne(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            while((reader = br.readLine()) != null){
                mapSections.add(reader);
            }

            MapSection[][] map = new MapSection[mapSections.size()][mapSections.get(0).length()];

            int Y = 0;
            int X = 0;
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(mapSections.get(i).charAt(j) == 'S'){
                        map[i][j] = new MapSection(true, mapSections.get(i).charAt(j));
                        Y = i;
                        X = j;
                    }else {
                        map[i][j] = new MapSection(false, mapSections.get(i).charAt(j));
                    }
                }
            }

            boolean start = true;
            boolean up = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;

            while(true){
                if(map[Y][X].getPipeType() == 'S'){
                    if(!start){
                        break;
                    }else{
                        start = false;
                        while(true) {
                            if (map[Y - 1][X].getPipeType() != '.' &&
                                    map[Y - 1][X].getPipeType() != '-' &&
                                    map[Y - 1][X].getPipeType() != 'J' &&
                                    map[Y - 1][X].getPipeType() != 'L') {
                                Y--;
                                up = true;
                                break;
                            } else if (map[Y + 1][X].getPipeType() != '.' &&
                                        map[Y + 1][X].getPipeType() != '-' &&
                                        map[Y + 1][X].getPipeType() != '7' &&
                                        map[Y + 1][X].getPipeType() != 'F') {
                                Y++;
                                down = true;
                                break;
                            } else if (map[Y][X - 1].getPipeType() != '.' &&
                                        map[Y][X - 1].getPipeType() != 'J' &&
                                        map[Y][X - 1].getPipeType() != '7' &&
                                        map[Y][X - 1].getPipeType() != '|') {
                                X--;
                                left = true;
                                break;
                            } else if (map[Y][X + 1].getPipeType() != '.'&&
                                        map[Y][X - 1].getPipeType() != 'L' &&
                                        map[Y][X - 1].getPipeType() != 'F' &&
                                        map[Y][X - 1].getPipeType() != '|') {
                                X++;
                                right = true;
                                break;
                            }
                        }
                    }
                }
                if(map[Y][X].getPipeType() == '-'){
                    if(left){
                        X--;
                    }else if(right){
                        X++;
                    }
                }else if(map[Y][X].getPipeType() == '|'){
                    if(up){
                        Y--;
                    }else if(down){
                        Y++;
                    }
                }else if(map[Y][X].getPipeType() == 'L'){
                    if(down){
                        X++;
                        down = false;
                        right = true;
                    }else{
                        Y--;
                        left = false;
                        up = true;
                    }
                }else if(map[Y][X].getPipeType() == 'J'){
                    if(down){
                        X--;
                        down = false;
                        left = true;
                    }else{
                        Y--;
                        right = false;
                        up = true;
                    }
                }else if(map[Y][X].getPipeType() == '7'){
                    if(up){
                        X--;
                        up = false;
                        left = true;
                    }else{
                        Y++;
                        right = false;
                        down = true;
                    }
                }else if(map[Y][X].getPipeType() == 'F'){
                    if(up){
                        X++;
                        up = false;
                        right = true;
                    }else{
                        Y++;
                        left = false;
                        down = true;
                    }
                }
                finalResult++;
            }
            finalResult++;
            finalResult = finalResult/2;

            System.out.println(finalResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void findCreaturePartTwo(){
        try(BufferedReader br = Files.newBufferedReader(path)){
            String reader;
            while((reader = br.readLine()) != null){
                mapSections.add(reader);
            }

            MapSection[][] map = new MapSection[mapSections.size()][mapSections.get(0).length()];

            int Y = 0;
            int X = 0;
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(mapSections.get(i).charAt(j) == 'S'){
                        map[i][j] = new MapSection(true, mapSections.get(i).charAt(j));
                        Y = i;
                        X = j;
                    }else {
                        map[i][j] = new MapSection(false, mapSections.get(i).charAt(j));
                    }
                }
            }

            boolean start = true;
            boolean up = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;

            while(true){
                map[Y][X].setLoop(true);
                if(map[Y][X].getPipeType() == 'S'){
                    if(!start){
                        break;
                    }else{
                        start = false;
                        while(true) {
                            if (map[Y - 1][X].getPipeType() != '.' &&
                                    map[Y - 1][X].getPipeType() != '-' &&
                                    map[Y - 1][X].getPipeType() != 'J' &&
                                    map[Y - 1][X].getPipeType() != 'L') {
                                Y--;
                                up = true;
                                break;
                            } else if (map[Y + 1][X].getPipeType() != '.' &&
                                    map[Y + 1][X].getPipeType() != '-' &&
                                    map[Y + 1][X].getPipeType() != '7' &&
                                    map[Y + 1][X].getPipeType() != 'F') {
                                Y++;
                                down = true;
                                break;
                            } else if (map[Y][X - 1].getPipeType() != '.' &&
                                    map[Y][X - 1].getPipeType() != 'J' &&
                                    map[Y][X - 1].getPipeType() != '7' &&
                                    map[Y][X - 1].getPipeType() != '|') {
                                X--;
                                left = true;
                                break;
                            } else if (map[Y][X + 1].getPipeType() != '.'&&
                                    map[Y][X - 1].getPipeType() != 'L' &&
                                    map[Y][X - 1].getPipeType() != 'F' &&
                                    map[Y][X - 1].getPipeType() != '|') {
                                X++;
                                right = true;
                                break;
                            }
                        }
                    }
                }
                if(map[Y][X].getPipeType() == '-'){
                    if(left){
                        X--;
                    }else if(right){
                        X++;
                    }
                }else if(map[Y][X].getPipeType() == '|'){
                    if(up){
                        Y--;
                    }else if(down){
                        Y++;
                    }
                }else if(map[Y][X].getPipeType() == 'L'){
                    if(down){
                        X++;
                        down = false;
                        right = true;
                    }else{
                        Y--;
                        left = false;
                        up = true;
                    }
                }else if(map[Y][X].getPipeType() == 'J'){
                    if(down){
                        X--;
                        down = false;
                        left = true;
                    }else{
                        Y--;
                        right = false;
                        up = true;
                    }
                }else if(map[Y][X].getPipeType() == '7'){
                    if(up){
                        X--;
                        up = false;
                        left = true;
                    }else{
                        Y++;
                        right = false;
                        down = true;
                    }
                }else if(map[Y][X].getPipeType() == 'F'){
                    if(up){
                        X++;
                        up = false;
                        right = true;
                    }else{
                        Y++;
                        left = false;
                        down = true;
                    }
                }
            }

            setLayout(new GridLayout(map.length, map[0].length));

            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(map[i][j].isLoop){
                        if(map[i][j].getPipeType() == '-'){
                            add(new JLabel(horisontal));
                        }else if(map[i][j].getPipeType() == 'L'){
                            add(new JLabel(rightUp));
                        }else if(map[i][j].getPipeType() == 'J'){
                            add(new JLabel(leftUp));
                        }else if(map[i][j].getPipeType() == 'F'){
                            add(new JLabel(rightDown));
                        }else if(map[i][j].getPipeType() == '7'){
                            add(new JLabel(leftDown));
                        }else if(map[i][j].getPipeType() == '|'){
                            add(new JLabel(vertical));
                        }else if(map[i][j].getPipeType() == 'S'){
                            add(new JLabel(S));
                        }
                    }else{
                        add(new JLabel(red));
                    }
                }
            }

            pack();
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
