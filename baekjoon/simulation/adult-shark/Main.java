import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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

    Shark[] sharks = new Shark[M + 1];
    boolean[][] shark_grid = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int idx = Integer.parseInt(st.nextToken());
        if (idx != 0) {
          sharks[idx] = new Shark(i, j);
          shark_grid[i][j] = true;
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 1; d <= 4; d++) {
          sharks[i].direction_grid[j][d] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, shark_grid));

  }
  static int simulation(Shark[] sharks, boolean[][] shark_grid) {
    int time = 0;

    int[][] smell_grid = new int[N][N];
    Queue<Smell> pq = new PriorityQueue<>();
    active_smell(pq, sharks, smell_grid);
    while (time != 1001) {

      active_move(sharks, smell_grid, shark_grid);

      pq = active_smell_time(pq, smell_grid, shark_grid);

      active_smell(pq, sharks, smell_grid);

      time++;
      if (isEnd(sharks)) break;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean isEnd(Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) if (!sharks[i].isDead) count++;
    return count == 1;
  }
  static void active_move(Shark[] sharks, int[][] smell_grid, boolean[][] shark_grid) {

    boolean[][] isShark = new boolean[N][N];
    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];
      if (!shark.isDead) {
        shark_grid[shark.row][shark.col] = false;
        int base_direction = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int next_direction = shark.direction_grid[base_direction][j];

          int nextRow = shark.row + dr[next_direction];
          int nextCol = shark.col + dc[next_direction];

          if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == 0) {
            if (isShark[nextRow][nextCol]) {
              shark.isDead = true;
              break;
            }
            isShark[nextRow][nextCol] = true;
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = next_direction;
            shark_grid[shark.row][shark.col] = true;
            break;
          }
        }
        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int next_direction = shark.direction_grid[base_direction][j];

            int nextRow = shark.row + dr[next_direction];
            int nextCol = shark.col + dc[next_direction];

            if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = next_direction;
              isShark[nextRow][nextCol] = true;
              shark_grid[nextRow][nextCol] = true;
              break;
            }
          }
        }
      }
    }
  }
  static void active_smell(Queue<Smell> pq, Shark[] sharks, int[][] smell_grid) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        smell_grid[shark.row][shark.col] = i;
        pq.add(new Smell(shark.row, shark.col, i, K));
      }
    }
  }
  static Queue<Smell> active_smell_time(Queue<Smell> pq, int[][] smell_grid, boolean[][] shark_grid) {
    Queue<Smell> newPQ = new PriorityQueue<>();

    while (!pq.isEmpty()) {
      Smell smell = pq.poll();

      if (!shark_grid[smell.row][smell.col]) {
        if (--smell.time == 0) smell_grid[smell.row][smell.col] = 0;
        else {
          smell_grid[smell.row][smell.col] = smell.idx;
          newPQ.add(smell);
        }
      }
    }
    return newPQ;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell implements Comparable<Smell> {
  int row;
  int col;
  int idx;
  int time;

  Smell(int row, int col, int idx, int time) {
    this.row = row;
    this.col = col;
    this.idx = idx;
    this.time = time;
  }

  @Override
  public int compareTo(Smell o) {
    return this.time - o.time;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  boolean isDead;
  int[][] direction_grid;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    direction_grid = new int[5][5];
  }
}