package q11;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *  Name:
 *  Class Group:
 */

public class Question11
{

    public static void main(String[] args) {
        System.out.println("SHORTEST DISTANCE ALGORITHM (DIJKSTRA)");
        startAlgorithm();
    }

    public static void startAlgorithm() {
        Map<String, TreeSet<DistanceTo>> connectionsMap = new HashMap<>();

        // All the Pendleton connections
        connectionsMap.put("Pendleton", new TreeSet<>());
        connectionsMap.get("Pendleton").add(new DistanceTo("Pierre", 2));
        connectionsMap.get("Pendleton").add(new DistanceTo("Pueblo", 8));
        connectionsMap.get("Pendleton").add(new DistanceTo("Phoenix", 4));

        // And so on...
        connectionsMap.put("Pierre", new TreeSet<>());
        connectionsMap.get("Pierre").add(new DistanceTo("Pueblo", 3));

        // And so on...
        connectionsMap.put("Pueblo", new TreeSet<>());
        connectionsMap.get("Pueblo").add(new DistanceTo("Peoria", 3));
        connectionsMap.get("Pueblo").add(new DistanceTo("Phoenix", 3));

        // And so on...
        connectionsMap.put("Phoenix", new TreeSet<>());
        connectionsMap.get("Phoenix").add(new DistanceTo("Peoria", 4));
        connectionsMap.get("Phoenix").add(new DistanceTo("Pittsburgh", 10));
        connectionsMap.get("Phoenix").add(new DistanceTo("Pensacola", 5));

        // You get the gist
        connectionsMap.put("Peoria", new TreeSet<>());
        connectionsMap.get("Peoria").add(new DistanceTo("Pittsburgh", 5));

        connectionsMap.put("Pittsburgh", new TreeSet<>());
        connectionsMap.get("Pittsburgh").add(new DistanceTo("Princeton", 2));
        connectionsMap.get("Pittsburgh").add(new DistanceTo("Pensacola", 4));

        connectionsMap.put("Pensacola", new TreeSet<>());
        connectionsMap.get("Pensacola").add(new DistanceTo("Princeton", 5));

        connectionsMap.put("Princeton", new TreeSet<>());
        connectionsMap.get("Princeton").add(new DistanceTo("Pittsburgh", 2));
        connectionsMap.get("Princeton").add(new DistanceTo("Pensacola", 5));
    }
}
