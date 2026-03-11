package Model;

public class Bottle {
    private String type;
    private int count;

    public Bottle(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }
    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return getType() + "-" + getCount();
    }
}
