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

    int[][][] dpTable = new int[str1Len + 1][str2Len + 1][str3Len + 1];

    for (int i = 1; i <= str1Len; i++) {
      for (int j = 1; j <= str2Len; j++) {
        for (int k = 1; k <= str3Len; k++) {
          if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
            dpTable[i][j][k] = dpTable[i - 1][j - 1][k - 1] + 1;
          }
          else {
            dpTable[i][j][k] = Math.max(Math.max(dpTable[i][j][k - 1], dpTable[i][j - 1][k]), dpTable[i - 1][j][k]);
          }
        }
      }
    }

    System.out.println(dpTable[str1Len][str2Len][str3Len]);
  }
}