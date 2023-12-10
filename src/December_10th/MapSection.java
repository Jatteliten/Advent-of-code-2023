package December_10th;

public class MapSection {
    boolean start;
    char pipeType;
    boolean isLoop;

    public MapSection(boolean start, char pipeType){
        this.start = start;
        this.pipeType = pipeType;
        this.isLoop = false;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public boolean isStart() {
        return start;
    }

    public char getPipeType() {
        return pipeType;
    }

    public void setPipeType(char pipeType) {
        this.pipeType = pipeType;
    }
}
