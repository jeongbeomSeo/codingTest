import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] grid = new String[5];

    for (int i = 0; i < 5; i++) {
      grid[i] = br.readLine();
    }

    System.out.println(queryResult(grid));
  }
  private static int queryResult(String[] grid) {

    int count = 0;
    for (int i = (1 << 7) - 1; i <= (1 << 5 * 5) - (1 << (5 * 5 - 7)); i++) {
      if (Integer.bitCount(i) == 7) {
        if (checkGroupValidation(grid, i)) {
          if (checkConnection(i))
            count++;
        }
      }
    }
    return count;
  }
  private static boolean checkGroupValidation(String[] grid, int num) {

    int somCount = 0;
    for (int i = 0; i < 25; i++) {
      if ((num & (1 << i)) != 0) {
        int[] point = getPoint(i);

        if (grid[point[0]].charAt(point[1]) == 'S') somCount++;
      }
    }
    return somCount >= 4;
  }
  private static boolean checkConnection(int num) {

    int count = 0;

    boolean[] isVisitied = new boolean[25];

    Queue<Integer> q = new ArrayDeque<>();
    int startIdx = findStartPoint(num);
    q.add(startIdx);
    isVisitied[startIdx] = true;

    while (!q.isEmpty()) {
      Integer idx = q.poll();

      count++;

      int[] curPoint = getPoint(idx);
      for (int i = 0; i < 4; i++) {
        int nxtRow = curPoint[0] + dr[i];
        int nxtCol = curPoint[1] + dc[i];

        int nxtIdx = getIdx(nxtRow, nxtCol);
        if (checkBoundary(nxtRow, nxtCol) && !isVisitied[nxtIdx] && isSelectedIdx(num, nxtIdx)) {
          q.add(nxtIdx);
          isVisitied[nxtIdx] = true;
        }
      }
    }

    return count == 7;
  }
  private static boolean isSelectedIdx(int num, int idx) {
    return (num & (1 << idx)) != 0;
  }
  private static boolean checkBoundary(int row, int col) {
    return row >= 0 && row < 5 && col >= 0 && col < 5;
  }
  private static int findStartPoint(int num) {
    int i;
    for (i = 0; i < 25; i++) {
      if ((num & (1 << i)) != 0) break;
    }
    return i;
  }
  private static int[] getPoint(int idx) {
    return new int[]{idx / 5, idx % 5};
  }
  private static int getIdx(int[] point) {
    return point[0] * 5 + point[1];
  }
  private static int getIdx(int row, int col) {
    return row * 5 + col;
  }
}