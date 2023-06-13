import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, M;
  static int sharkRow, sharkCol;
  // 북, 남, 서, 동
  static int[] throw_dr = {0, -1, 1, 0, 0};
  static int[] throw_dc = {0, 0, 0, -1, 1};
  // 남, 동, 북, 서
  static int[] move_dr = {0, 1, 0, -1, 0};
  static int[] move_dc = {0, 0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = (N + 1) / 2;

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotate_commands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      rotate_commands[i][0] = Integer.parseInt(st.nextToken());
      rotate_commands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(grid, rotate_commands));

  }
  static int simulation(int[][] grid, int[][] rotate_commands) {

    int time = 0;
    int[] burstMarbleNumber = new int[4];
    while (time < M) {

      activeThrow(grid, rotate_commands[time]);

      sortingGrid(grid);

      while (active_burst(grid, burstMarbleNumber)) {
        sortingGrid(grid);
      }

      finalSortingGrid(grid);

      time++;
    }

    return burstMarbleNumber[1] + 2 * burstMarbleNumber[2] + 3 * burstMarbleNumber[3];
  }
  static void finalSortingGrid(int[][] grid) {
    Deque<Counter> counters = new ArrayDeque<>();

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;

    while (grid[curRow][curCol] != 0) {

      isVisited[curRow][curCol] = true;

      if (counters.isEmpty()) counters.addLast(new Counter(grid[curRow][curCol]));
      else {
        if (counters.peekLast().number == grid[curRow][curCol]) counters.peekLast().count++;
        else counters.addLast(new Counter(grid[curRow][curCol]));
      }

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

    curRow = sharkRow;
    curCol = sharkCol - 1;
    curDirection = 1;

    for (int i = 0; i <= N; i++) Arrays.fill(isVisited[i], false);
    isVisited[sharkRow][sharkCol] = true;

    while (!counters.isEmpty()) {
      Counter counter = counters.pollFirst();

      grid[curRow][curCol] = counter.count;
      isVisited[curRow][curCol] = true;
      if (curRow == 1 && curCol == 1) break;

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];

      grid[curRow][curCol] = counter.number;
      isVisited[curRow][curCol] = true;
      if (curRow == 1 && curCol == 1) break;

      result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

  }
  static boolean active_burst(int[][] grid, int[] burstMarbleNumber) {

    Queue<Point> points = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;
    int curNumber = grid[curRow][curCol];

    boolean isActiveBurst = false;

    while (!(curRow == 1 && curCol == 1)) {

      isVisited[curRow][curCol] = true;

      if (grid[curRow][curCol] == curNumber) points.add(new Point(curRow, curCol));
      else {
        if (points.size() >= 4) {
          isActiveBurst = true;
          while (!points.isEmpty()) {
            Point point = points.poll();
            grid[point.row][point.col] = 0;
            burstMarbleNumber[curNumber]++;
          }
        }
        else while (!points.isEmpty()) points.poll();

        curNumber = grid[curRow][curCol];
        points.add(new Point(curRow, curCol));
      }

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

    return isActiveBurst;
  }
  static void sortingGrid(int[][] grid) {

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;

    while (!(curRow == 1 && curCol == 1)) {

      int nextRow;
      int nextCol;
      int nextDirection;

      isVisited[curRow][curCol] = true;
      boolean curValueIsZero = (grid[curRow][curCol] == 0);

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);
      nextRow = result[0];
      nextCol = result[1];
      nextDirection = result[2];

      // 현재 위치 0일 경우 처리
      if (curValueIsZero) {
        if(!paintZeroToOtherNumber(grid, isVisited, curRow, curCol, nextRow, nextCol, nextDirection)) break;
      }

      curRow = nextRow;
      curCol = nextCol;
      curDirection = nextDirection;
    }
  }
  static boolean paintZeroToOtherNumber(int[][] grid, boolean[][] isVisited, int curRow, int curCol, int nextRow, int nextCol, int nextDirection) {

    if (grid[nextRow][nextCol] != 0) {
      grid[curRow][curCol] = grid[nextRow][nextCol];
      grid[nextRow][nextCol] = 0;
    }
    else {
      if (nextRow == 1 && nextCol == 1) return false;

      int copyRow = nextRow;
      int copyCol = nextCol;
      int copyDirection = nextDirection;
      do {
        int[] findNextResult = findNextIdx(isVisited, copyRow, copyCol, copyDirection);

        copyRow = findNextResult[0];
        copyCol = findNextResult[1];
        copyDirection = findNextResult[2];

        if(copyRow == 1 && copyCol == 1) break;

      } while (grid[copyRow][copyCol] == 0);

      if (grid[copyRow][copyCol] == 0) return false;

      grid[curRow][curCol] = grid[copyRow][copyCol];
      grid[copyRow][copyCol] = 0;
    }
    return true;
  }
  static int[] findNextIdx(boolean[][] isVisited, int curRow, int curCol, int curDirection) {
    int[] result = new int[3];

    int leftDirection = getLeftDirection(curDirection);

    int leftRow = curRow + move_dr[leftDirection];
    int leftCol = curCol + move_dc[leftDirection];

    if (!isVisited[leftRow][leftCol]) {
      result[0] = leftRow;
      result[1] = leftCol;
      result[2] = leftDirection;
    }
    else {
      result[0] = curRow + move_dr[curDirection];
      result[1] = curCol + move_dc[curDirection];
      result[2] = curDirection;
    }
    return result;
  }
  static int getLeftDirection(int direction) {
    return direction + 1 != 5 ? direction + 1 : 1;
  }
  static void activeThrow(int[][] grid, int[] rotate_command) {

    int direction = rotate_command[0];
    int targetSpeed = rotate_command[1];

    int curRow = sharkRow;
    int curCol = sharkCol;

    while (targetSpeed-- > 0) {
      int nextRow = curRow + throw_dr[direction];
      int nextCol = curCol + throw_dc[direction];

      grid[nextRow][nextCol] = 0;

      curRow = nextRow;
      curCol = nextCol;
    }
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
class Counter {
  int number;
  int count;

  Counter (int number) {
    this.number = number;
    this.count = 1;
  }
}