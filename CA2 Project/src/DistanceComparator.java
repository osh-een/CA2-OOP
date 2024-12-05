import java.util.Comparator;

public class DistanceComparator implements Comparator<DistanceTo> {

    @Override
    public int compare(DistanceTo distance1, DistanceTo distance2)
    {
        return Integer.compare(distance1.getDistance(), distance2.getDistance());
    }
}
