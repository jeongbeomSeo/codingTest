import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] knapsack = new int[N + 1][2];
    int[][] dp = new int[N + 1][K + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(knapsack, dp, N, K);

    System.out.println(dp[N][K]);
  }
  static void knapsack_dp(int[][] knapsack, int[][] dp, int N, int K) {
    for (int i = 1; i < N + 1; i++) {
      for (int k = 1; k < K + 1; k++) {
        int curThingWeight = knapsack[i][0];
        if (curThingWeight > k)
          dp[i][k] = dp[i - 1][k];
        else {
          dp[i][k] = Math.max(dp[i - 1][k], knapsack[i][1] + dp[i - 1][k - curThingWeight]);
        }
      }
    }
  }
}