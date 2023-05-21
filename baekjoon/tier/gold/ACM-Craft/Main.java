import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] cost = new int[N + 1];
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        cost[i] = Integer.parseInt(st.nextToken());
      }

      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
      int[] inDegree = new int[N + 1];
      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        graph.get(n1).add(n2);
        inDegree[n2]++;
      }

      int target = Integer.parseInt(br.readLine());

      System.out.println(query(graph, inDegree, cost, target, N));

    }

  }
  static int query(ArrayList<ArrayList<Integer>> graph, int[] inDegree, int[] cost, int target, int N) {

    Queue<Integer> q = new LinkedList<>();
    int[] result = new int[N + 1];
    for (int i = 1; i <= N; i++)
      if (inDegree[i] == 0) {
        q.add(i);
        result[i] = cost[i];
      }

    for (int count = 1; count <= N; count++) {
      int curIdx = q.poll();

      if (curIdx == target) break;

      for (int i = 0; i < graph.get(curIdx).size(); i++) {
        int nextIdx = graph.get(curIdx).get(i);

        if (result[nextIdx] < result[curIdx] + cost[nextIdx]) {
          result[nextIdx] = result[curIdx] + cost[nextIdx];
        }
        if (--inDegree[nextIdx] == 0) q.add(nextIdx);
      }
    }
    return result[target];
  }
}