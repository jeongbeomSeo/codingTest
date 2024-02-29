import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
  private static final int[] DR = {-1, -1, -1, 0, 1, 1, 1, 0};
  private static final int[] DC = {-1, 0, 1, 1, 1, 0, -1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int w = Integer.parseInt(br.readLine());

    String[] words = new String[w];
    for (int i = 0; i < w; i++) {
      words[i] = br.readLine();
    }

    // 띄어쓰기 제거
    br.readLine();

    int b = Integer.parseInt(br.readLine());

    while (b-- != 0) {

      char[][] grid = new char[4][4];
      for (int i = 0; i < 4; i++) {
        String str = br.readLine();
        for (int j = 0; j < 4; j++) {
          grid[i][j] = str.charAt(j);
        }
      }

      Result result = startBoggle(words, grid);

      bw.write(result.score + " " + result.logestLengthWord + " " + result.count + "\n");

      if (b != 0) br.readLine();
    }

    bw.flush();
    bw.close();
  }
  private static Result startBoggle(String[] words, char[][] grid) {

    Result result = new Result();
    for (String curWord : words) {
      boolean isFind = false;
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          if (grid[i][j] == curWord.charAt(0)) {
            isFind = search(grid, curWord, i, j);
          }
          if (isFind) break;
        }
        if (isFind) break;
      }

      if (isFind) {
        int curWordLen = curWord.length();
        if (result.count == 0 || result.logestLengthWord.length() < curWordLen) {
          result.logestLengthWord = curWord;
        } else if (result.logestLengthWord.length() == curWordLen) {
          result.logestLengthWord = comparePriority(result.logestLengthWord, curWord, curWordLen);
        }
        result.score += convertScore(curWordLen);
        result.count++;
      }
    }

    return result;
  }
  private static boolean search(char[][] grid, String word, int initRow, int initCol) {

    int wordLen = word.length();
    Queue<Info> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[4][4];
    isVisited[initRow][initCol] = true;
    q.add(new Info(initRow, initCol, 0, isVisited));

    while (!q.isEmpty()) {
      Info curInfo = q.poll();

      if (curInfo.ptr == wordLen - 1) return true;

      for (int i = 0; i < 8; i++) {
        int nxtRow = curInfo.row + DR[i];
        int nxtCol = curInfo.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol) && (word.charAt(curInfo.ptr + 1) == grid[nxtRow][nxtCol])
                && !curInfo.isVisited[nxtRow][nxtCol]) {
          boolean[][] nxtIsVisited = copyBooleanTable(curInfo.isVisited);
          nxtIsVisited[nxtRow][nxtCol] = true;
          q.add(new Info(nxtRow, nxtCol, curInfo.ptr + 1, nxtIsVisited));
        }
      }

    }
    return false;
  }
  private static boolean[][] copyBooleanTable(boolean[][] table) {

    boolean[][] copy = new boolean[4][4];

    for (int i = 0; i < 4; i++) {
      copy[i] = Arrays.copyOf(table[i], 4);
    }

    return copy;
  }
  private static String comparePriority(String word1, String word2, int wordLen) {

    for (int i = 0; i < wordLen; i++) {
      if (word1.charAt(i) < word2.charAt(i)) return word1;
      else if (word1.charAt(i) > word2.charAt(i)) return word2;
    }

    return word1;
  }
  private static int convertScore(int wordLen) {
    switch (wordLen) {
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
      default:
        return 0;
    }
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Info {
  int row;
  int col;
  int ptr;
  boolean[][] isVisited;

  Info(int row, int col, int ptr, boolean[][] isVisited) {
    this.row = row;
    this.col = col;
    this.ptr = ptr;
    this.isVisited = isVisited;
  }
}
class Result {
  String logestLengthWord;
  int score;
  int count;

  Result () {
    this.logestLengthWord = "";
    this.score = 0;
    this.count = 0;
  }
}