
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
                Point point = new Point(col, row);
                if (Character.isDigit(digit)) {
                    System.out.println("opa");
                    Ports port = new Ports(digit, col, row);
                    ports.add(port);
                    point.setIsPort();
                }
                points.put(Integer.toString(col) + Integer.toString(row), point);
            }
        }
        Collections.sort(ports);

        bfs(points.get(Integer.toString(ports.get(0).getCol()) + Integer.toString(ports.get(0).getRow())));
    }

    public void bfs(Point inicio) {
        int wayCount = ports.size();
        LinkedList<Point> fila = new LinkedList<>();
        int visited = 0;

        fila.offer(inicio);

        while (!fila.isEmpty()) {
            Point actualPosition = fila.poll();

            if (visited == ports.size()) {
                for (Ports port : ports) {
                    System.out.println(port.getCol() + " " + port.getRow());
                }
            }

            Point[] neighbors = getNeighborhood(actualPosition);

            for (Point neighbor : neighbors) {
                if (neighbor != null && !neighbor.getIsVisited()) {
                    neighbor.setIsVisited();
                    fila.offer(neighbor);
                    neighbor.setFather(actualPosition);

                    Ports nextPort = ports.get(0);
                    if (neighbor.getIsPort() && neighbor.getCol() == nextPort.getCol()
                            && neighbor.getRow() == nextPort.getRow() && !nextPort.getVisited()) {
                        visited++;
                        ports.set(-1, nextPort);
                    }
                }
            }
        }
    }

    public Point[] getNeighborhood(Point actualPosition) {
        Point[] neightbors = new Point[4];
        neightbors[0] = this.points
                .get(Integer.toString(actualPosition.getCol() - 1) + Integer.toString(actualPosition.getRow()));
        neightbors[1] = this.points
                .get(Integer.toString(actualPosition.getCol()) + Integer.toString(actualPosition.getRow() + 1));
        neightbors[2] = this.points
                .get(Integer.toString(actualPosition.getCol() + 1) + Integer.toString(actualPosition.getRow()));
        neightbors[3] = this.points
                .get(Integer.toString(actualPosition.getCol()) + Integer.toString(actualPosition.getRow() - 1));
        return neightbors;
    }
}