import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] cost = new int[N][3];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[N][3];

    for (int i = 0; i < 3; i++) {
      dp[0][i] = cost[0][i];
    }

    calc_dp(cost, dp, N);

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      min = Math.min(min, dp[N - 1][i]);
    }

    System.out.println(min);
  }
  static void calc_dp(int[][] cost, int[][] dp, int N) {
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][j];
        }
        else if (j == 1) {
          dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][j];
        }
      }
    }
  }
}