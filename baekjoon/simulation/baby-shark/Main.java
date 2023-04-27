import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  // 북, 서, 남, 동
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];

    int row = 0;
    int col = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          row = i;
          col = j;
          grid[i][j] = 0;
        }
      }
    }
    System.out.println(simulation(grid, row, col));
  }

  static int simulation(int[][] grid, int row, int col) {

    Shark shark = new Shark(row, col);

    int time = 0;
    while (true) {
      int count = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size) count++;
        }
      }
      if (count >= 1) {
        State resultState = bfs(grid, shark);
        if (resultState != null) {
          shark.row = resultState.row;
          shark.col = resultState.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          time += resultState.time;
          grid[resultState.row][resultState.col] = 0;
        }
        else break;
      }
      else break;
    }
    return time;
  }

  static State bfs(int[][] grid, Shark shark) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<State> q = new ArrayDeque<>();
    q.add(new State(shark.row, shark.col));
    isVisited[shark.row][shark.col] = true;

    int curTime = 0;
    State priorityState = null;
    while (!q.isEmpty()) {
      State curState = q.poll();

      if (curTime != curState.time) {
        if (priorityState != null) return priorityState;
        else curTime = curState.time;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] <= shark.size) {
            State newState = new State(nextRow, nextCol, grid[nextRow][nextCol], curState.time + 1);
            if (grid[nextRow][nextCol] != 0 && grid[nextRow][nextCol] < shark.size) {
              if (priorityState != null) {
                if (!priorityState.priorityCheck(newState)) priorityState = newState;
              }
              else priorityState = newState;
            }
            q.add(newState);
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
    return null;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= N - 1 && col <= N - 1;
  }
}

class State {
  int row;
  int col;
  int fishSize;
  int time;

  State(int row, int col) {
    this.row = row;
    this.col = col;
    this.fishSize = 0;
    this.time = 0;
  }

  State(int row, int col, int fishSize, int time) {
    this(row, col);
    this.fishSize = fishSize;
    this.time = time;
  }

  boolean priorityCheck(State state) {
    if (this.row < state.row) return true;
    else if (this.row > state.row) return false;
    else {
      return this.col < state.col;
    }
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
}