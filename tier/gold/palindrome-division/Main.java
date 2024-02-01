import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  private static final int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();
    int strLen = str.length();

    boolean[][] isPalindrome = initIsPalindrome(str, strLen);

    updateIsPalindrome(str, strLen, isPalindrome);

    int[] dpTable = queryDp(isPalindrome, strLen);

    System.out.println(dpTable[strLen - 1]);
  }
  private static int[] queryDp(boolean[][] isPalindrome, int len) {

    int[] dpTable = new int[len];
    Arrays.fill(dpTable, INF);
    dpTable[0] = 1;

    for (int i = 1; i < len; i++) {
      dpTable[i] = dpTable[i - 1] + 1;
      for (int j = 0; j < i; j++) {
        if (isPalindrome[j][i]) {
          if (j == 0) {
            dpTable[i] = 1;
            break;
          }
          else dpTable[i] = Math.min(dpTable[i], dpTable[j - 1] + 1);
        }
      }
    }

    return dpTable;
  }
  private static void updateIsPalindrome(String str, int len, boolean[][] isPalindrome) {

    for (int i = 2; i < len; i++) {
      for (int j = 0; j < i - 1; j++) {
        if (isPalindrome[j + 1][i - 1] && str.charAt(j) == str.charAt(i)) isPalindrome[j][i] = true;
      }
    }
  }
  private static boolean[][] initIsPalindrome(String str, int len) {

    boolean[][] isPalindrome = new boolean[len][len];

    isPalindrome[0][0] = true;
    for (int i = 1; i < len; i++) {
      isPalindrome[i][i] = true;

      if (str.charAt(i - 1) == str.charAt(i)) isPalindrome[i - 1][i] = true;
    }

    return isPalindrome;
  }
}