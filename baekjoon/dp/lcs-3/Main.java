import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str1 = br.readLine();
    String str2 = br.readLine();
    String str3 = br.readLine();

    int str1Len = str1.length();
    int str2Len = str2.length();
    int str3Len = str3.length();

    int[][][] dp = new int[str1Len + 1][str2Len + 1][str3Len + 1];

    lcs_dp(dp, str1, str2, str3, str1Len, str2Len, str3Len);

    System.out.println(dp[str1Len][str2Len][str3Len]);

  }
  static void lcs_dp(int[][][] dp, String str1, String str2, String str3, int str1Len, int str2Len, int str3Len) {
    for (int i = 1; i < str1Len + 1; i++) {
      for (int j = 1; j < str2Len + 1; j++) {
        for (int k = 1; k < str3Len + 1; k++) {
          if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
            dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
          }
          else {
            dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
          }
        }
      }
    }
  }
}