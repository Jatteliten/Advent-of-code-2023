package December_3rd;

import java.util.ArrayList;

public class Gear {
    ArrayList<String> touchingCoordinates = new ArrayList<>();
    public Gear(int x, int y){
        touchingCoordinates.add("X: " + (x - 1) + " Y: " + (y - 1));
        touchingCoordinates.add("X: " + (x) + " Y: " + (y - 1));
        touchingCoordinates.add("X: " + (x + 1) + " Y: " + (y - 1));
        touchingCoordinates.add("X: " + (x - 1) + " Y: " + (y));
        touchingCoordinates.add("X: " + (x + 1) + " Y: " + (y));
        touchingCoordinates.add("X: " + (x - 1) + " Y: " + (y + 1));
        touchingCoordinates.add("X: " + (x) + " Y: " + (y + 1));
        touchingCoordinates.add("X: " + (x + 1) + " Y: " + (y + 1));
    }
}
