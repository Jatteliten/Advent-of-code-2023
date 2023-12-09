package December_8th;

import java.util.ArrayList;

public class Location {
    String name;
    ArrayList<String> locations = new ArrayList<>();

    public Location(String read){
        this.name = read.substring(0, 3);
        locations.add(read.substring(read.indexOf("(") + 1, read.indexOf(",")));
        locations.add(read.substring(read.indexOf(",") + 2, read.indexOf(")")));
    }
    public Location(){
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }
}
