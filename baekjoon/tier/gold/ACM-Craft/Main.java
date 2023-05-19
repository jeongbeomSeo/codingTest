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
      for (int i = 0; i <= N; i++) {
        graph.add(new ArrayList<>());
      }

      int[] inDegree = new int[N + 1];

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        graph.get(n1).add(n2);
        inDegree[n2]++;
      }

      int target = Integer.parseInt(br.readLine());

      Queue<Integer> q = new LinkedList<>();
      int[] dp = new int[N + 1];

      for (int i = 1; i <= N; i++) {
        if (inDegree[i] == 0) q.add(i);
        dp[i] = cost[i];
      }

      while (!q.isEmpty()) {
        int curNode = q.poll();

        for (int i = 0; i < graph.get(curNode).size(); i++) {
          int nxtNode = graph.get(curNode).get(i);

          if (dp[curNode] + cost[nxtNode] > dp[nxtNode]) {
            dp[nxtNode] = dp[curNode] + cost[nxtNode];
          }
          if (--inDegree[nxtNode] == 0) {
            q.add(nxtNode);
          }
        }
      }

      System.out.println(dp[target]);
    }
  }
}