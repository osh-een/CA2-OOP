public class DistanceTo implements Comparable<DistanceTo> {
    private String target;
    private int distance;

    public DistanceTo(String city, int dist) {
        target = city;
        distance = dist;
    }

    public String getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    // just so TreeSet ignores duplicates for the seeMap method
    @Override
    public int compareTo(DistanceTo other) {
        int compareDistances = Integer.compare(this.distance, other.distance);

        // If distances are equal, compare by target city name
        if (compareDistances == 0) {
            return this.target.compareTo(other.target);
        }

        return compareDistances;
    }
}