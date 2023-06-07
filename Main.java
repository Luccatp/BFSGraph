import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("casos-cohen/mapa2000.txt"));
            String[] firstLine = reader.readLine().split(" ");
            int col = Integer.parseInt(firstLine[0]);
            int linha = Integer.parseInt(firstLine[1]);
            

            char[][] map = new char[col][linha];

            for (int r = 0; r < col; r++) {
                map[r] = reader.readLine().toCharArray();
            }
            Graph graph = new Graph(map);

            reader.close();
        } catch (

        Exception e) {
            System.out.println(e.getMessage());
        }
    }
}