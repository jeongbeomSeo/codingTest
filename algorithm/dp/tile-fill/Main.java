import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[31];

    dp[0] = 1;
    dp[1] = 0;
    dp[2] = 3;
    dp[3] = 0;

    for (int i = 4; i <= N; i++) {
      int result = 0;

      result += 3 * dp[i - 2];

      for (int j = i - 4; j >= 0; j -= 2) result += 2 * dp[j];

      dp[i] = result;
    }

    System.out.println(dp[N]);
  }
}