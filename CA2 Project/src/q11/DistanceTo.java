package q11;

public class DistanceTo implements Comparable{
    private String target;
    private int distance;

    public DistanceTo(String city, int dist) {
        target = city; distance = dist;
    }

    public String getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    public int compareTo(DistanceTo other) {
        return distance - other.distance;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
