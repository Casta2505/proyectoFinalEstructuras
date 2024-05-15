package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPathBFS {
	public List<Vertex> shortestPath(Graph graph, Vertex source, Vertex destination) {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Integer> distance = new HashMap<>();
        Map<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();

        queue.add(source);
        distance.put(source, 0);
        parent.put(source, null);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current == destination) {
                break;
            }
            for (int i = 0; i < current.getAdyacentEdges().size(); i++) {
                Vertex neighbor = current.getAdyacentEdges().get(i).getDestination();
                if(!distance.containsKey(neighbor)) {
                	queue.add(neighbor);
                	distance.put(neighbor, distance.get(current)+1);
                	parent.put(neighbor, current);
                }
			}
        }
        List<Vertex> path = new ArrayList<>();
        Vertex current = destination;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
