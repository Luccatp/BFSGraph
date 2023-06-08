
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

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
    }

    public List<int[]> bfs(Ports inicio, Ports destino) {
        LinkedList<Point> fila = new LinkedList<>();
        Map<String, Point> pais = new HashMap<>();
        Set<String> visitados = new HashSet<>();
        //System.out.println(destino.getValue());
        fila.offer(this.points.get(inicio.getKey()));
        visitados.add(inicio.getKey());

        while (!fila.isEmpty()) {
            Point actualPosition = fila.poll();
            if(Character.isDigit(actualPosition.getValue())) {
                if (Character.digit(actualPosition.getValue(), 10) == destino.getValue()) {
                    //System.out.println("Encontrei");
                    //System.out.println("aaa");
                    return reconstruirCaminho(this.points.get(inicio.getKey()), actualPosition, pais);
                }
            }
            
            //System.out.print(actualPosition.getValue());
            List<Point> neighbors = getNeighborhood(actualPosition);
            
            for (Point neighbor : neighbors) {
                if (neighbor != null && !visitados.contains(neighbor.getKey())) {
                    fila.offer(neighbor);
                    visitados.add(neighbor.getKey());
                    pais.put(neighbor.getKey(), actualPosition);
                } 
            }
        }
        return null;
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

    public static List<int[]> reconstruirCaminho(Point inicio, Point destino, Map<String, Point> pais) {
        List<int[]> caminho = new ArrayList<>();
        Point actualPosition = destino;

        while (!actualPosition.getKey().equals(inicio.getKey()) ) {
            int[] position = new int[2];
            position[0] = actualPosition.getRow();
            position[1] = actualPosition.getCol();
            caminho.add(position);
            actualPosition = pais.get(actualPosition.getKey());
        }
        int[] initialPosition = new int[2];
        initialPosition[0] = inicio.getRow();
        initialPosition[1] = inicio.getCol();
        caminho.add(initialPosition);
        Collections.reverse(caminho);

        return caminho;
    }

    public void getResults() {
        //System.out.println(ports.get(7).getValue());

        for (int i = 0; i < ports.size(); i++) {
            System.out.println(ports.get(i).getValue());
            List<int[]> pathTo = bfs(ports.get(i), ports.get(i + 1));
            if(pathTo == null) {
                System.out.println("Sem caminho para o port " + ports.get(i + 1).getValue());
                continue;
            }
            for (int j = 0; j < pathTo.size(); j++) {
                System.out.println(pathTo.get(j)[0] + " " + pathTo.get(j)[1]);
            }
            System.out.println();
            pathTo.clear();
        }
    }
}