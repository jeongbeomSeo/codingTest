import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] cityRule = new int[N + 1];
      List<Integer>[] graph = initGraph(N);

      int[] cost = new int[N + 1];
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i < N + 1; i++) {
        cost[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        cityRule[endNode]++;
        graph[startNode].add(endNode);
      }

      int targetNode = Integer.parseInt(br.readLine());

      if (T != 0) {
        bw.write(getResult(graph, cost, cityRule, targetNode, N) + "\n");
      }
      else {
        bw.write(String.valueOf(getResult(graph, cost, cityRule, targetNode, N)));
      }
    }

    bw.flush();
    bw.close();
  }
  private static int getResult(List<Integer>[] graph, int[] cost, int[] cityRule, int targetNode, int N) {

    int[] resultTimeTable = new int[N + 1];
    Queue<Integer> q = new ArrayDeque<>();

    for (int i = 1; i < N + 1; i++) {
      if (cityRule[i] == 0) {
        resultTimeTable[i] = Math.max(resultTimeTable[i], cost[i]);
        q.add(i);
      }
    }

    while (!q.isEmpty()) {
      int curIdx = q.poll();

      if (curIdx == targetNode) {
        break;
      }

      for (int i = 0; i < graph[curIdx].size(); i++) {
        int nxtNode = graph[curIdx].get(i);

        resultTimeTable[nxtNode] = Math.max(resultTimeTable[nxtNode], resultTimeTable[curIdx] + cost[nxtNode]);
        if (--cityRule[nxtNode] == 0) {
          q.add(nxtNode);
        }
      }
    }

    return resultTimeTable[targetNode];
  }
  private static List<Integer>[] initGraph(int N) {
    List<Integer>[] graph = new ArrayList[N + 1];

    for (int i = 0; i < N + 1; i++) {
      graph[i] = new ArrayList<>();
    }

    return graph;
  }
}