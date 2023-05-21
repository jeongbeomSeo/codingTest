import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int INF = 2501;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();

    int L = str.length();

    boolean[][] is_pd = new boolean[L][L];
    for (int i = 0; i < L; i++) {
      is_pd[i][i] = true;

      if (i != L - 1 && str.charAt(i) == str.charAt(i + 1)) is_pd[i][i + 1] = true;
    }

    for (int i = 2; i <= L - 1; i++) {
      for (int j = 0; j + i < L; j++) {
        if (str.charAt(j) == str.charAt(j + i) && is_pd[j + 1][j + i - 1]) is_pd[j][j + i] = true;
      }
    }

    int[] dp = new int[L];
    dp[0] = 1;
    for (int i = 1; i < L; i++) dp[i] = INF;

    for (int i = 1; i < L; i++) {
      dp[i] = dp[i - 1] + 1;
      for (int j = 0; j < i; j++) {
        if (is_pd[j][i]) {
          if (j == 0) dp[i] = 1;
          else dp[i] = Math.min(dp[j - 1] + 1, dp[i]);
        }
      }
    }
    System.out.println(dp[L - 1]);
  }
}