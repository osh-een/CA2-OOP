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

    @Override
    // changed so that duplicate distances are not left out.
    public int compareTo(DistanceTo other) {
        int distanceComparison = Integer.compare(this.distance, other.distance);

        // if they are the same distance, compare the city names instead and only remove if city names are also the same
        if(distanceComparison == 0) {
            return this.target.compareTo(other.target); // Compare target names if distances are equal
        }

        return distanceComparison;
    }

    @Override
    public String toString() {
        return "(" +target+ ". Total distance travelled will be: " + distance + "km)";
    }
}