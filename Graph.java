

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class Graph {

    private final int V;
    private int E;
    private char[][] map;
    private List<Ports> ports = new LinkedList<>();
    private HashMap<String, Point> points = new HashMap<>();  

    public Graph(char[][] map) {
        this.map = map;
        this.V = map.length * map[0].length;
    
        char digit;
        for (int col = 0; col < map.length; col++) {
            for (int row = 0; row < map[0].length; row++) {
                digit = map[col][row];
                Point point =  new Point(col, row);
                if(Character.isDigit(digit)) {
                    System.out.println("opa");
                    Ports port = new Ports(digit, col, row);
                    ports.add(port);
                    point.setIsPort();
                }
                points.put(Integer.toString(col) + Integer.toString(row), point);
            }
        }
        Collections.sort(ports);
    }

    public void bfs(Point inicio) {
        int wayCount = ports.size();
        LinkedList<Point> fila = new LinkedList<>();
        int visited = 0;

        fila.add(inicio);

        while(!fila.isEmpty()) {
            Point actualPosition = fila.poll();

            if(visited == ports.size()) {
                //todo pegar caminho
            }

            Point[] vizinhos = getNeighborhood(actualPosition);
        }
    }

    public Point[] getNeighborhood(Point actualPosition) {
        // lembrar que o this.points tem a key com a concatenação de col + row
        // Point[] neighborhoods = new Integer[4];

        // pegar em cima baixo direita esquerda
        // neighborhoods[0] = this.points.get(Integer.toString(col) + Integer.toString(row));
        return new Point[1];
    }
}