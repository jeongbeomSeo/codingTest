import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int MOD = 1000000003;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    if (N / 2 < K)  {
      System.out.println(0);
      return;
    }

    // 선형으로 된 N개의 색상환에서 K개를 선택하는 경우
    int[][] dp = new int[N + 1][K + 1];

    // 선형 DP 리스트 초기화
    init_dp(dp, N, K);

    // 선형 DP 리스트로 구하였기 때문에 원형의 상황을 가정해서 구해야 한다.
    System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD);


  }
  static void init_dp(int[][] dp, int N, int K) {
    for (int i = 1; i < N + 1; i++) {
      dp[i][0] = 1;
      dp[i][1] = i;
    }
    for (int i = 3; i < N + 1; i++) {
      for (int j = 2; j <= (i + 1) / 2 && j < K + 1; j++) {
        // 원형이 아닌 선형 DP 리스트이다.
        dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
      }
    }
  }
}