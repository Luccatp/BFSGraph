import java.util.*;

class Main {
    public static void main(String[] args) {
        int[][] mapa = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };

        int[][] destinos = {
            {2, 2}, // Destino 1
            {4, 2}, // Destino 2
            {4, 4}  // Destino 3
        };

        int[] inicio = {0, 0}; // Posição inicial

        List<int[]> caminho = bfsMD(mapa, inicio, destinos);

        if (caminho != null) {
            System.out.println("Caminho encontrado:");
            for (int[] posicao : caminho) {
                System.out.println(Arrays.toString(posicao));
            }
        } else {
            System.out.println("Não foi possível encontrar um caminho que passe por todos os destinos.");
        }
    }

    public static List<int[]> bfsMD(int[][] mapa, int[] inicio, int[][] destinos) {
        int numDestinos = destinos.length;
        Set<String> visitados = new HashSet<>();
        Queue<int[]> fila = new LinkedList<>();
        Map<String, int[]> pais = new HashMap<>();
        Map<String, Integer> destinosVisitados = new HashMap<>();

        fila.offer(inicio);
        visitados.add(Arrays.toString(inicio));
        destinosVisitados.put(Arrays.toString(inicio), 0);

        while (!fila.isEmpty()) {
            int[] posicaoAtual = fila.poll();
            int destinosVisitadosCount = destinosVisitados.get(Arrays.toString(posicaoAtual));

            if (destinosVisitadosCount == numDestinos) {
                return reconstruirCaminho(inicio, posicaoAtual, pais);
            }

            List<int[]> vizinhos = obterVizinhos(mapa, posicaoAtual);

            for (int[] vizinho : vizinhos) {
                String chaveVizinho = Arrays.toString(vizinho);

                if (!visitados.contains(chaveVizinho)) {
                    visitados.add(chaveVizinho);
                    fila.offer(vizinho);
                    pais.put(chaveVizinho, posicaoAtual);

                    if (destinoEncontrado(vizinho, destinos)) {
                        destinosVisitados.put(chaveVizinho, destinosVisitadosCount + 1);
                    } else {
                        destinosVisitados.put(chaveVizinho, destinosVisitadosCount);
                    }
                }
            }
        }

        return null;
    }

    public static boolean destinoEncontrado(int[] posicao, int[][] destinos) {
        for (int[] destino : destinos) {
            if (Arrays.equals(posicao, destino)) {
                return true;
            }
        }
        return false;
    }

    public static List<int[]> obterVizinhos(int[][] mapa, int[] posicao)
