import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] strList = new String[3];

    for (int i = 0; i < 3; i++) {
      strList[i] = br.readLine();
    }

    int str1Size = strList[0].length();
    int str2Size = strList[1].length();
    int str3Size = strList[2].length();


    int[][][] table = initLcsTable_3D(strList[0], strList[1], strList[2], str1Size, str2Size, str3Size);

    System.out.println(table[str1Size][str2Size][str3Size]);
  }
  private static int[][][] initLcsTable_3D(String str1, String str2, String str3, int str1Size, int str2Size, int str3Size) {

    int[][][] table = new int[str1Size + 1][str2Size + 1][str3Size + 1];

    for (int i = 1; i < str1Size + 1; i++) {
      for (int j = 1; j < str2Size + 1; j++) {
        for (int k = 1; k < str3Size + 1; k++) {
          if (str1.charAt(i - 1) == str2.charAt(j - 1) && str1.charAt(i - 1) == str3.charAt(k - 1)) {
            table[i][j][k] = table[i - 1][j - 1][k - 1] + 1;
          } else {
            table[i][j][k] = Math.max(table[i - 1][j][k], Math.max(table[i][j - 1][k], table[i][j][k - 1]));
          }
        }
      }
    }

    return table;
  }
}