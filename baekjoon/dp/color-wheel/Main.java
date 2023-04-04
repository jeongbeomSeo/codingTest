import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int[][] dp = new int[N][K];


  }
  static void init_dp(int[][] dp, int N, int K) {
    for (int j = 0; j < K; j++) {
      for (int i = 0; i < N; i++) {
        dp[i][j] +=
      }
    }
  }

}