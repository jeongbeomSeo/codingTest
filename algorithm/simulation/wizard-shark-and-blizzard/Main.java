import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  // 서, 남, 동, 북
  static int[] move_dr = {0, 0, 1, 0, -1};
  static int[] move_dc = {0, -1, 0, 1, 0};
  static int sharkRow, sharkCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = (N + 1) / 2;

    int[][] numberTagGrid = initNumberTagGrid();

    int[] grid = new int[N * N];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        int idx = numberTagGrid[i][j];

        grid[idx] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] throwCommands = new int[M][2];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      throwCommands[i][0] = Integer.parseInt(st.nextToken());
      throwCommands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(numberTagGrid, grid, throwCommands));
  }
  static int simulation(int[][] numberTagGrid, int[] grid, int[][] throwCommands) {

    int time = 0;
    int[] burstMarbleCount = new int[4];

    while (time < M) {

      activeThrowMarble(numberTagGrid, grid, throwCommands[time]);

      sortGrid(grid);

      while (activeBurst(grid, burstMarbleCount));

      sortGrid(grid);

      grid = generateGrid(grid);

      time++;
    }
    return burstMarbleCount[1] + 2 * burstMarbleCount[2] + 3 * burstMarbleCount[3];
  }
  static int[] generateGrid(int[] grid) {

    Deque<Counter> counter = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (grid[i] != 0) {
        if (counter.isEmpty()) counter.add(new Counter(grid[i]));
        else {
          if (counter.peekLast().value == grid[i]) counter.peekLast().count++;
          else counter.addLast(new Counter(grid[i]));
        }
      }
    }

    int[] newGrid = new int[N * N];

    Counter buffer = null;
    for (int i = 1; i < N * N; i++) {

      if (i % 2 == 1) {
        if (counter.isEmpty()) break;
        buffer = counter.pollFirst();
      }

      if (i % 2 == 1) newGrid[i] = buffer.count;
      else newGrid[i] = buffer.value;
    }

    return newGrid;
  }
  static boolean activeBurst(int[] grid, int[] burstMarbleCount) {

    boolean isActive = false;
    int curValue = 0;
    Queue<Integer> idxs = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (grid[i] != 0) {
        if (curValue == 0) {
          curValue = grid[i];
          idxs.add(i);
        }
        else if (curValue == grid[i]) idxs.add(i);
        else {
          if (idxs.size() >= 4) {
            burstMarbleCount[curValue] += idxs.size();
            while (!idxs.isEmpty()) grid[idxs.poll()] = 0;
            isActive = true;
          }
          else {
            while (!idxs.isEmpty()) idxs.poll();
          }
          curValue = grid[i];
          idxs.add(i);
        }
      }
    }

    if (idxs.size() >= 4) {
      burstMarbleCount[curValue] += idxs.size();
      while (!idxs.isEmpty()) grid[idxs.poll()] = 0;
      isActive = true;
    }

    return isActive;
  }
  static void sortGrid(int[] grid) {

    for (int i = 1; i < N * N; i++) {
      if (grid[i] == 0) {
        int j;
        for (j = i + 1; j < N * N && grid[j] == 0; j++);
        if (j == N * N) break;
        grid[i] = grid[j];
        grid[j] = 0;
      }
    }
  }
  static void activeThrowMarble (int[][] numberTagGrid, int[] grid, int[] throwCommand) {
    int direction = throwCommand[0];
    int speed = throwCommand[1];

    int row = sharkRow;
    int col = sharkCol;
    while (speed-- > 0) {
      row += dr[direction];
      col += dc[direction];

      int idx = numberTagGrid[row][col];
      grid[idx] = 0;
    }
  }
  static int[][] initNumberTagGrid() {

    int[][] newGrid = new int[N + 1][N + 1];
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int num = 1;
    int row = sharkRow;
    int col = sharkCol - 1;
    int direction = 1;

    while (!(row == 1 && col == 0)) {

      newGrid[row][col] = num++;
      isVisited[row][col] = true;

      int[] nextPoint = findNextPoint(isVisited, row, col, direction);

      row = nextPoint[0];
      col = nextPoint[1];
      direction = nextPoint[2];
    }

    return newGrid;
  }
  static int[] findNextPoint (boolean[][] isVisited, int row, int col, int direction) {

    int[] nextPoint = new int[3];

    int leftDirection = getLeftDirection(direction);

    int leftRow = row + move_dr[leftDirection];
    int leftCol = col + move_dc[leftDirection];

    if (!isVisited[leftRow][leftCol]) {
      nextPoint[0] = leftRow;
      nextPoint[1] = leftCol;
      nextPoint[2] = leftDirection;
    }
    else {
      nextPoint[0] = row + move_dr[direction];
      nextPoint[1] = col + move_dc[direction];
      nextPoint[2] = direction;
    }

    return nextPoint;
  }
  static int getLeftDirection (int direction) {
    return direction + 1 != 5 ? direction + 1 : 1;
  }
}
class Counter {
  int value;
  int count;

  Counter(int value) {
    this.value = value;
    this.count = 1;
  }
}