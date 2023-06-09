import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] idx_grid = new int[N][N];
    Shark[] sharks = new Shark[M + 1];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        idx_grid[i][j] = Integer.parseInt(st.nextToken());
        if (idx_grid[i][j] != 0) {
          sharks[idx_grid[i][j]] = new Shark(i, j);
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int idx = 1; idx <= M; idx++) {
      for (int i = 1; i <= 4; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 1; j <= 4; j++) {
          sharks[idx].grid_direction[i][j] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, idx_grid));
  }
  static int simulation (Shark[] sharks, int[][] idx_grid) {

    int time = 0;
    Queue<Smell> smells = new ArrayDeque<>();
    active_smell(smells, sharks, idx_grid);

    while (time != 1001) {

      smells = active_move(smells, sharks, idx_grid);

      active_smell(smells, sharks, idx_grid);

      time++;
      if (isEnd(sharks)) break;
    }

    if (time == 1001) return -1;
    else return time;
  }
  static boolean isEnd (Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) {
      if (!sharks[i].isDead) count++;
    }
    return count == 1;
  }
  static Queue<Smell> active_move(Queue<Smell> smells, Shark[] sharks, int[][] idx_grid) {

    int[][] buffer = new int[N][N];

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        int baseDirection = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int nextDirection = shark.grid_direction[baseDirection][j];

          int nextRow = shark.row + dr[nextDirection];
          int nextCol = shark.col + dc[nextDirection];

          if (isValidIdx(nextRow, nextCol) && idx_grid[nextRow][nextCol] == 0) {
            if (buffer[nextRow][nextCol] != 0) {
              shark.isDead = true;
              break;
            }
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = nextDirection;
            buffer[nextRow][nextCol] = i;
            break;
          }
        }
        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int nextDirection = shark.grid_direction[baseDirection][j];

            int nextRow = shark.row + dr[nextDirection];
            int nextCol = shark.col + dc[nextDirection];

            if (isValidIdx(nextRow, nextCol) && idx_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = nextDirection;
              buffer[nextRow][nextCol] = i;
              break;
            }
          }
        }
      }
    }

    Queue<Smell> newSmellQueue = new ArrayDeque<>();
    // Smell decrease Count
    while (!smells.isEmpty()) {
      Smell smell = smells.poll();

      if (buffer[smell.row][smell.col] != 0 || --smell.time == 0) {
        idx_grid[smell.row][smell.col] = 0;
      }
      else newSmellQueue.add(smell);
    }
    return newSmellQueue;
  }
  static void active_smell(Queue<Smell> smells, Shark[] sharks, int[][] idx_grid) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        smells.add(new Smell(shark.row, shark.col, K, i));
        idx_grid[shark.row][shark.col] = i;
      }
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell {
  int row;
  int col;
  int time;
  int idx;

  Smell (int row, int col, int time, int idx) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.idx = idx;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  int[][] grid_direction;
  boolean isDead;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.grid_direction = new int[5][5];
    isDead = false;
  }
}