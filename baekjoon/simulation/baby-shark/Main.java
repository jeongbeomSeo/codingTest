import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 좌, 남, 우
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    int row = 0;
    int col = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          grid[i][j] = 0;
          row = i;
          col = j;
        }
      }
    }

    System.out.println(simulation(grid, N, row, col));
  }
  static int simulation(int[][] grid, int N, int row, int col) {
    Shark shark = new Shark(row, col, 2);

    int time = 0;
    while (true) {
      int count = 0;
      boolean end = true;
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size)  {
            count++;
            end = false;
          }
        }

      if (end) break;

      if (count >= 1) {
        State state = bfs(grid, N, shark.row, shark.col, shark.size);
        if (state.success) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          time += state.time;
        }
        else break;
      }

    }

    return time;
  }
  static State bfs(int[][] grid, int N, int row, int col, int sharkSize) {

    Queue<State> q = new ArrayDeque<>();
    q.add(new State(row, col, 0));
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    while (!q.isEmpty()) {
      State curState = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol, N) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] != 0 && grid[nextRow][nextCol] < sharkSize) {
            grid[nextRow][nextCol] = 0;
            return new State(nextRow, nextCol, curState.time + 1, true);
          }
          else if (grid[nextRow][nextCol] == 0 || grid[nextRow][nextCol] == sharkSize) {
            q.add(new State(nextRow, nextCol, curState.time + 1));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
    return new State(row, col, 0, false);
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}

class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark(int row, int col, int size) {
    this.row = row;
    this.col = col;
    this.size = size;
    this.exp = 0;
  }
}

class State {
  int row;
  int col;
  int time;
  boolean success;

  State(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
    success = false;
  }

  State(int row, int col, int time, boolean success) {
    this(row, col, time);
    this.success = success;
  }
}
