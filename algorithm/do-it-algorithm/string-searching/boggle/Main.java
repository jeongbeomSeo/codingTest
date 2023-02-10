import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static boolean find(char[][] grid, boolean[][] isVisitied, String txt, int idx, int y, int x, int txtLen) {

    if(idx == txtLen) return true;

    boolean success = false;

    for(int i = y - 1; i <= y + 1; i++) {
      if(i < 0 || i > 3) continue;

      for(int j = x - 1; j <= x + 1; j++) {
        if(j < 0 || j > 3 || (i == y && j == x)) continue;

        if(!isVisitied[i][j]) {
          if(grid[i][j] == txt.charAt(idx)){
            isVisitied[i][j] = true;
            success = find(grid, isVisitied, txt, idx + 1, i, j, txtLen);
            isVisitied[i][j] = false;
          }
        }
        if(success) return true;
      }
    }
    return false;
  }

  static int calcScore(int txtLen) {
    switch (txtLen) {
      case 1:
      case 2:
        return 0;
      case 3:
      case 4:
        return 1;
      case 5:
        return 2;
      case 6:
        return 3;
      case 7:
        return 5;
      case 8:
        return 11;
    }
    return 0;
  }

  static String maxLengthString(String maxLengthStr, String txt) {
    int maxLength = maxLengthStr.length();
    int txtLen = txt.length();
    if(maxLength == txtLen) {
      for(int i = 0; i < maxLength; i++) {
        if(maxLengthStr.charAt(i) > txt.charAt(i)) {
          return txt;
        } else if(maxLengthStr.charAt(i) == txt.charAt(i)) continue;
        else
          return maxLengthStr;
      }
    }
    if(maxLength < txtLen) return txt;
    else return maxLengthStr;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int w = Integer.parseInt(br.readLine());

    String[] dictionary = new String[w];

    for(int i = 0 ; i < w; i++)
      dictionary[i] = br.readLine();

    br.readLine();

    int boardNum = Integer.parseInt(br.readLine());
    for(int board = 0; board < boardNum; board++) {

      char[][] grid = new char[4][4];
      for(int i = 0; i < 4; i++) {
        String txts = br.readLine();
        for(int j = 0; j < 4; j++)
          grid[i][j] = txts.charAt(j);
      }

      int maxScore = 0;
      String maxLengthStr = "";
      int findString = 0;

      for(int dict = 0; dict < dictionary.length; dict++) {

        String txt = dictionary[dict];
        int txtLen = txt.length();
        char startChar = txt.charAt(0);

        boolean success = false;
        for(int i = 0; i < 4; i++) {
          for(int j = 0; j < 4; j++) {
            if(grid[i][j] == startChar) {
              boolean[][] isVisitied = new boolean[4][4];
              isVisitied[i][j] = true;
              success = find(grid, isVisitied, txt, 0 + 1, i, j, txtLen);
              if(success) break;
            }
          }
          if(success) break;
        }
        // 성공 시
        if(success) {
          findString++;
          maxScore += calcScore(txtLen);
          maxLengthStr = maxLengthString(maxLengthStr, txt);
        }
      }
      System.out.println(maxScore + " " + maxLengthStr + " " + findString);

      if(board != boardNum - 1) br.readLine();
    }
  }
}
