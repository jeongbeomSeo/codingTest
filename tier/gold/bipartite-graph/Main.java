import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int COLOR = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        List<List<Integer>> graph;

        while (K-- != 0) {
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = initGraph(V);

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            int[] colors = new int[V + 1];

            boolean isBipartiteGraph = true;
            for (int i = 1; i < V + 1; i++) {
                if (colors[i] == 0) {
                    if (isBipartiteGraph(graph, colors, i, COLOR)) {
                        isBipartiteGraph = false;
                        break;
                    }
                }
            }

            if (isBipartiteGraph) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    private static boolean isBipartiteGraph(List<List<Integer>> graph, int[] colors, int start, int color) {

        colors[start] = color;

        for (int i = 0; i < graph.get(start).size(); i++) {
            int nxt = graph.get(start).get(i);

            if (colors[nxt] != 0) {
                if (colors[nxt] == color) return true;
                continue;
            }

            if (isBipartiteGraph(graph, colors, nxt, color * -1)) return true;
        }

        return false;
    }
    private static List<List<Integer>> initGraph(int V) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        return graph;
    }
}
