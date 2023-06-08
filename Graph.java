
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
        
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                char digit = map[row][col];
                Point point = new Point(col, row, digit);
                if (Character.isDigit(map[row][col])) {
                    Ports port = new Ports(Character.digit(digit, 10), col, row);
                    ports.add(port);
                    point.setIsPort();
                }
                
                points.put(Integer.toString(col) + Integer.toString(row), point);
            }
        }
        Collections.sort(ports);

        //Iterator<Ports> iterator = ports.iterator();
        //while (iterator.hasNext()) {
        //    Ports valor = iterator.next();
        //    System.out.println(valor.getValue());
        //}
        bfs(ports.get(0));
    }

    public void bfs(Ports inicio) {
        int wayCount = ports.size();
        LinkedList<Point> fila = new LinkedList<>();
        int visited = 0;
        
        fila.offer(this.points.get(Integer.toString(inicio.getCol()) + Integer.toString(inicio.getRow())));

        while (!fila.isEmpty()) {
            Point actualPosition = fila.poll();

            if (visited == ports.size()) {
                for (Ports port : ports) {
                    System.out.println(port.getCol() + " " + port.getRow());
                }
            }
            //System.out.println(actualPosition.getValue());
            List<Point> neighbors = getNeighborhood(actualPosition);
            
            for (Point neighbor : neighbors) {
                if (neighbor != null && !neighbor.getIsVisited()) {
                    neighbor.setIsVisited();
                    fila.offer(neighbor);
                    neighbor.setFather(actualPosition);

                    Ports nextPort = ports.get(0);
                    if (neighbor.getIsPort() && neighbor.getValue() == nextPort.getValue() && !nextPort.getVisited()) {
                        visited++;
                        ports.set(Math.negateExact(visited), nextPort);
                    }
                } 
            }
        }
    }

    public List<Point> getNeighborhood(Point actualPosition) {
        List<Point> neightbors = new ArrayList<>();
        int[][] direcoes = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        int lines = this.map.length;
        int columns = this.map[0].length;

        for (int[] direcao : direcoes) {
            int newCol = actualPosition.getCol() + direcao[0];
            int newRow = actualPosition.getRow() + direcao[1];
            
            if (newRow >= 0 && newRow < lines && newCol >= 0 && newCol < columns && this.map[newRow][newCol] != '*') {
                neightbors.add(this.points.get(Integer.toString(newCol) + Integer.toString(newRow)));
            }
        }

        return neightbors;
    }
}