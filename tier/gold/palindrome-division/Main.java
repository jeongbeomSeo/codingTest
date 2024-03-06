import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String str = sc.nextLine();
    int strLen = str.length();

    boolean[][] dpTable = new boolean[strLen][strLen];

    dpTable[0][0] = true;
    for (int i = 1; i < strLen; i++) {
      dpTable[i][i] = true;

      if (str.charAt(i - 1) == str.charAt(i)) dpTable[i - 1][i] = true;
    }

    for (int i = 2; i < strLen; i++) {
      for (int j = 0; j < i - 1; j++) {
        if (dpTable[j + 1][i - 1] && str.charAt(j) == str.charAt(i)) dpTable[j][i] = true;
      }
    }

    int[] dp = new int[strLen];
    dp[0] = 1;

    for (int i = 1; i < strLen; i++) {
      dp[i] = dp[i - 1] + 1;

      for (int j = 0; j < i; j++) {
        if (dpTable[j][i]) {
          if(j == 0) {
            dp[i] = 1;
            break;
          } else {
            dp[i] = Math.min(dp[j - 1] + 1, dp[i]);
          }
        }
      }
    }

    System.out.println(dp[strLen - 1]);

    sc.close();
  }
}