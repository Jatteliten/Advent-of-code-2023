package December_3rd;

import java.util.ArrayList;

public class Engine {
    ArrayList<String> coordinates = new ArrayList<>();
    int engine;

    public Engine(String engine, int x, int y){
        for(int i = 0; i < engine.length(); i++){
            coordinates.add("X: " + (x + i) + " Y: " + y);
        }
        this.engine = Integer.parseInt(engine);
    }

    public ArrayList<String> getCoordinates() {
        return coordinates;
    }

    public int getEngine() {
        return engine;
    }
}
