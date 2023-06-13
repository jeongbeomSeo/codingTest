import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;

  static int[] throw_dr = {0, -1, 1, 0, 0};
  static int[] throw_dc = {0, 0, 0, -1, 1};

  // 북, 서, 남, 동
  static int[] move_dr = {0, -1, 0, 1, 0};
  static int[] move_dc = {0, 0, -1, 0, 1};
  static int sharkRow, sharkCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = N / 2;

    int[] oneDimensionGrid = new int[N * N];
    int[][] numberTagGrid = new int[N][N];
    paintNumberTagGrid(numberTagGrid);

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int idx = numberTagGrid[i][j];
        oneDimensionGrid[idx] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] throwCommands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      throwCommands[i][0] = Integer.parseInt(st.nextToken());
      throwCommands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(numberTagGrid, oneDimensionGrid, throwCommands));
  }
  static int simulation (int[][] numberTagGrid, int[] oneDimensionGrid, int[][] throwCommands) {

    int time = 0;
    int[] burstMarbleCount = new int[4];
    while (time < M) {

      active_throwMarble(numberTagGrid, oneDimensionGrid, throwCommands[time]);

      while (active_burst(oneDimensionGrid, burstMarbleCount));

      //sortingGrid(oneDimensionGrid);
      oneDimensionGrid = finalSortingGrid(oneDimensionGrid);
      time++;
    }

    return burstMarbleCount[1] + 2 * burstMarbleCount[2] + 3 * burstMarbleCount[3];
  }
  static int[] finalSortingGrid(int[] oneDimensionGrid) {

    Deque<Counter> counters = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (oneDimensionGrid[i] != 0) {
        if (counters.isEmpty()) counters.addLast(new Counter(oneDimensionGrid[i]));
        else if (counters.peekLast().number == oneDimensionGrid[i]) counters.peekLast().count++;
        else counters.addLast(new Counter(oneDimensionGrid[i]));
      }
    }

    int[] newOneDimensionGrid = new int[N * N];
    int ptr = 1;
    while (!counters.isEmpty()) {
      Counter counter = counters.pollFirst();

      newOneDimensionGrid[ptr++] = counter.count;
      if (ptr == N * N) break;
      newOneDimensionGrid[ptr++] = counter.number;
      if (ptr == N * N) break;
    }
    return newOneDimensionGrid;
  }
  static void sortingGrid (int[] oneDimensionGrid) {

    for (int i = 1; i < N * N - 1; i++) {
      if (oneDimensionGrid[i] == 0) {
        int j;
        for (j = i + 1; j < N * N && oneDimensionGrid[j] == 0; j++);
        if (j == N * N) break;
        oneDimensionGrid[i] = oneDimensionGrid[j];
        oneDimensionGrid[j] = 0;
      }
    }
  }
  static boolean active_burst(int[] oneDimensionGrid, int[] burstMarbleCount) {

    Queue<Point> q = new ArrayDeque<>();
    boolean isActiveBurst = false;

    for (int i = 1; i < N * N; i++) {
      if (oneDimensionGrid[i] != 0) {
        if (q.isEmpty()) q.add(new Point(i, oneDimensionGrid[i]));
        else if (q.peek().number == oneDimensionGrid[i]) q.add(new Point(i, oneDimensionGrid[i]));
        else {
          if (q.size() >= 4) {
            isActiveBurst = true;
            burstMarbleCount[q.peek().number] += q.size();
            while (!q.isEmpty()) oneDimensionGrid[q.poll().idx] = 0;
          }
          else while (!q.isEmpty()) q.poll();
          q.add(new Point(i, oneDimensionGrid[i]));
        }
      }
    }
    if (q.size() >= 4) {
      burstMarbleCount[q.peek().number] += q.size();
      while (!q.isEmpty()) {
        oneDimensionGrid[q.poll().idx] = 0;
      }
    }
    return isActiveBurst;
  }
  static void active_throwMarble(int[][] numberTagGrid, int[] oneDimensionGrid, int[] throwCommand) {

    int direction = throwCommand[0];
    int speed = throwCommand[1];

    int curRow = sharkRow;
    int curCol = sharkCol;
    while (speed-- > 0) {

      curRow += throw_dr[direction];
      curCol += throw_dc[direction];

      int idx = numberTagGrid[curRow][curCol];
      oneDimensionGrid[idx] = 0;
    }
  }
  static void paintNumberTagGrid(int[][] grid) {

    boolean[][] isVisited = new boolean[N][N];

    int curRow = sharkRow;
    int curCol = sharkCol;
    int direction = 1;

    int numberTag = 0;

    do {
      isVisited[curRow][curCol] = true;
      grid[curRow][curCol] = numberTag++;

      int leftDirection = direction + 1 != 5 ? direction + 1 : 1;

      int leftRow = curRow + move_dr[leftDirection];
      int leftCol = curCol + move_dc[leftDirection];

      if (!isVisited[leftRow][leftCol]) {
        curRow = leftRow;
        curCol = leftCol;
        direction = leftDirection;
      }
      else {
        curRow += move_dr[direction];
        curCol += move_dc[direction];
      }

    } while (curRow >= 0 && curCol >= 0);
  }
}

class Point {
  int idx;
  int number;

  Point(int idx, int number) {
    this.idx = idx;
    this.number = number;
  }
}

class Counter {
  int number;
  int count;

  Counter(int number) {
    this.number = number;
    this.count = 1;
  }
}