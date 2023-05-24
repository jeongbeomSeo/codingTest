import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
      int[] inDegree = new int[N + 1];

      for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        graph.get(n1).add(n2);
        inDegree[n2]++;
      }

      int[] dp = new int[N + 1];

      Queue<Integer> q = new ArrayDeque<>();
      for (int i = 1; i <= N; i++) if (inDegree[i] == 0) {
        q.add(i);
        dp[i] = cost[i];
      }

      while (!q.isEmpty()) {
        int curIdx = q.poll();

        for (int i = 0; i < graph.get(curIdx).size(); i++) {
          int nextIdx = graph.get(curIdx).get(i);

          if (dp[nextIdx] < dp[curIdx] + cost[nextIdx]) {
            dp[nextIdx] = dp[curIdx] + cost[nextIdx];
          }

          if (--inDegree[nextIdx] == 0) q.add(nextIdx);
        }
      }
      int target = Integer.parseInt(br.readLine());
      System.out.println(dp[target]);
    }
  }
}