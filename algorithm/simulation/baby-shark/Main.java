import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][N];
    Shark shark = null;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());

        if (grid[i][j] == 9) {
          shark = new Shark(i, j);
          grid[i][j] = 0;
        }
      }
    }

    System.out.println(simulation(grid, shark, N));

  }
  private static int simulation(int[][] grid, Shark shark, int N) {

    int time = 0;

    while (true) {
      int usedTime = search(grid, shark, N);
      if (usedTime == 0) break;
      time += usedTime;

    };

    return time;
  }
  private static int search(int[][] grid, Shark shark, int N) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Point> q = new ArrayDeque<>();
    Queue<Point> fish = new PriorityQueue<>((o1, o2) -> {
      if (o1.row != o2.row) return o1.row - o2.row;
      return o1.col - o2.col;
    });

    Point initPoint = new Point(shark.row, shark.col, 0);
    q.add(initPoint);
    isVisited[initPoint.row][initPoint.col] = true;

    int prevTime = 0;
    while (!q.isEmpty()) {
      Point point = q.poll();

      if (prevTime != point.time) {
        if (!fish.isEmpty()) {
          eatFish(grid, fish.poll(), shark);
          return point.time;
        } else {
          prevTime = point.time;
        }
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidPoint(nextRow, nextCol, N)
                && !isVisited[nextRow][nextCol]
                && canGoSize(grid, nextRow, nextCol, shark)) {

          Point nextPoint = new Point(nextRow, nextCol, point.time + 1);
          if (grid[nextRow][nextCol] != 0 && canEat(grid, nextRow, nextCol, shark)) {
            fish.add(nextPoint);
          }
          q.add(nextPoint);
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    return 0;
  }
  private static boolean canGoSize(int[][] grid, int row, int col, Shark shark) {
    return grid[row][col] <= shark.size;
  }
  private static void eatFish(int[][] grid, Point point, Shark shark) {
    grid[point.row][point.col] = 0;
    shark.eatFish(point.row, point.col);
  }
  private static boolean canEat(int[][] grid, int row, int col, Shark shark) {
    if (grid[row][col] < shark.size) return true;

    return false;
  }
  private static boolean isValidPoint(int row, int col, int N) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  private static boolean isEnd(int[][] grid, Shark shark, int N) {

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] < shark.size) return true;
      }
    }

    return false;
  }
}
class Point {
  int row;
  int col;
  int time;

  Point (int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
  }
}
class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark (int row, int col) {
    this.row = row;
    this.col = col;
    this.size = 2;
    this.exp = 0;
  }

  public void eatFish(int row, int col) {
    this.row = row;
    this.col = col;

    expUp();
  }
  private void expUp() {
    this.exp += 1;

    if (size == exp) {
      this.size += 1;
      this.exp = 0;
    }
  }
}