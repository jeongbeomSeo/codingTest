import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
  private static final int[] DR = {-1, 0, 1, 0};
  private static final int[] DC = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[][] grid = new char[5][5];
    for (int i = 0; i < 5; i++) {
      String str = br.readLine();

      for (int j = 0; j < 5; j++) {
        grid[i][j] = str.charAt(j);
      }
    }

    System.out.println(simulation(grid));
  }
  private static int simulation(char[][] grid) {

    int result = 0;
    for (int i = ((1 << 7) - 1); i < (1 << 25); i++) {

      if (Integer.bitCount(i) == 7) {
        List<Point> pointList = new ArrayList<>();
        int somCount = 0;
        for (int j = 0; j < 25; j++) {
          if ((i & (1 << j)) != 0) {
            Point point = getPoint(j);
            if (grid[point.row][point.col] == 'S') somCount++;
            pointList.add(point);
          }
        }

        if (somCount >= 4) {
          result += (bfs(pointList) ? 1 : 0);
        }
      }
    }
    return result;
  }
  private static boolean bfs(List<Point> pointList) {

    int count = 1;
    Point initPoint = pointList.get(0);
    boolean[] isVisited = new boolean[7];
    Queue<Point> q = new ArrayDeque<>();
    q.add(initPoint);
    isVisited[0] = true;

    while (!q.isEmpty()) {
      Point curPoint = q.poll();

      for (int i = 0; i < 4; i++) {
        int nxtRow = curPoint.row + DR[i];
        int nxtCol = curPoint.col + DC[i];

        for (int j = 0; j < 7; j++) {
          if (!isVisited[j] && (pointList.get(j).row == nxtRow && pointList.get(j).col == nxtCol)) {
            q.add(pointList.get(j));
            isVisited[j] = true;
            count++;
          }
        }
      }
    }

    return count == 7;
  }
  private static Point getPoint(int index) {
    return new Point(index / 5, index % 5);
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}