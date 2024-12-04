//  Question 11 – Shortest Distance to City [20 marks] (Map, TreeSet, PriorityQueue)

//  Consider the problem of finding the least expensive routes to all cities in a 
//  network from a given starting point.

//  For example, in this network, the least expensive route from Pendleton to Peoria has cost 8
//  (going through Pierre and Pueblo). 

//  The following helper class expresses the distance to another city:

//  All direct connections between cities are stored in a Map<String,TreeSet<DistanceTo>>.

//  The algorithm now proceeds as follows:
//      Let from be the starting point.
//      Add DistanceTo(from, 0) to a priority queue.
//      Construct a map shortestKnownDistance from city names to distances.
//      While the priority queue is not empty
//          Get its smallest element.
//          If its target is not a key in shortestKnownDistance
//              Let d be the distance to that target.
//              Put (target, d) into shortestKnownDistance.
//              For all cities c that have a direct connection from target
//                  Add DistanceTo(c, d + distance from target to c) to the priority queue.

//  When the algorithm has finished, shortestKnownDistance contains the shortest distance
//  from the starting point to all reachable targets. 

//  Your task is to write a program that implements this algorithm. Your program should read in 
//  lines, from a file, of the form:
//  city1 city2 distance

//  The starting point is the first city in the first line. Print the shortest distances to all other
//  cities

package Q11;

import java.util.*;

class Question11 {

        public static void main(String[] args) {
                getConnections();
        }

        public static void getConnections() {

                String city1 = "Pendleton";
                String city2 = "Pierre";
                String city3 = "Pueblo";
                String city4 = "Phoenix";
                String city5 = "Pensacola";
                String city6 = "Pittsburgh";
                String city7 = "Peoria";
                String city8 = "Princeston";

                int d2 = 2;
                int d3 = 3;
                int d4 = 4;
                int d5 = 5;
                int d8 = 8;
                int d10 = 10;

                Map<String, TreeSet<DistanceTo>> map = new HashMap<>();

                addDistancesToMap(map, "Pendleton",
                                new DistanceTo(city2, d2),
                                new DistanceTo(city3, d8),
                                new DistanceTo(city4, d4));

                addDistancesToMap(map, "Pierre",
                                new DistanceTo(city3, d3),
                                new DistanceTo(city1, d8));

                addDistancesToMap(map, "Pueblo",
                                new DistanceTo(city1, d8),
                                new DistanceTo(city2, d3),
                                new DistanceTo(city4, d3),
                                new DistanceTo(city7, d3));

                addDistancesToMap(map, "Phoenix",
                                new DistanceTo(city1, d4),
                                new DistanceTo(city3, d3),
                                new DistanceTo(city5, d5),
                                new DistanceTo(city7, d4));

                addDistancesToMap(map, "Pensacola",
                                new DistanceTo(city4, d5),
                                new DistanceTo(city6, d4),
                                new DistanceTo(city8, d5));

                addDistancesToMap(map, "Pittsburgh",
                                new DistanceTo(city5, d4),
                                new DistanceTo(city7, d5),
                                new DistanceTo(city8, d2),
                                new DistanceTo(city4, d10));

                addDistancesToMap(map, "Peoria",
                                new DistanceTo(city3, d3),
                                new DistanceTo(city4, d4),
                                new DistanceTo(city6, d5));

                addDistancesToMap(map, "Princeton",
                                new DistanceTo(city5, d5),
                                new DistanceTo(city6, d2));

                for (Map.Entry<String, TreeSet<DistanceTo>> key : map.entrySet()) {
                        System.out.println(key.getKey() + " -> " + key.getValue());
                }
        }

        public static void addDistancesToMap(Map<String, TreeSet<DistanceTo>> map, String key,
                        DistanceTo... distances) {
                map.computeIfAbsent(key, k -> new TreeSet<>()).addAll(Arrays.asList(distances));
        }
}