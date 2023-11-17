import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 상, 하, 좌, 우
  private static final int[] dr = {0, -1, 1, 0, 0};
  private static final int[] dc = {0, 0, 0, -1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        int idx = Integer.parseInt(st.nextToken());

        if (idx != 0) {
          sharks[idx] = new Shark(idx, i, j);
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= M; i++) {
      int[][] priority = new int[5][5];
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 1; d <= 4; d++){
          priority[j][d] = Integer.parseInt(st.nextToken());
        }
      }
      sharks[i].priority = priority;
    }

    System.out.println(simulation(sharks, N, M, k));
  }
  private static int simulation(Shark[] sharks, int N, int M, int k) {

    int[][] smellIdxGrid = new int[N + 1][N + 1];
    Queue<Smell> smellBuffer = new ArrayDeque<>();

    int time = 0;
    while (time != 1001) {
      time++;

      paintSmell(sharks, smellBuffer, smellIdxGrid, M, k);

      int[][] sharkIdxGrid = activeShark(sharks, smellIdxGrid, N, M);

      smellBuffer = updateSmell(smellBuffer, smellIdxGrid, sharkIdxGrid);

      if (isEnd(sharks, M)) break;
    }

    return time != 1001 ? time : -1;
  }
  private static boolean isEnd(Shark[] sharks, int M) {

    for (int i = 2; i <= M; i++) {
      if (!sharks[i].isDead) return false;
    }

    return true;
  }
  private static Queue<Smell> updateSmell(Queue<Smell> smellBuffer, int[][] smellIdxGrid, int[][] sharkIdxGrid) {

    Queue<Smell> nextSmellBuffer = new ArrayDeque<>();

    while (!smellBuffer.isEmpty()) {
      Smell smell = smellBuffer.poll();

      if (--smell.age == 0) {
        smellIdxGrid[smell.row][smell.col] = 0;
      } else if (sharkIdxGrid[smell.row][smell.col] == 0) {
        smellIdxGrid[smell.row][smell.col] = smell.sharkIdx;
        nextSmellBuffer.add(smell);
      }
    }

    return nextSmellBuffer;
  }
  private static int[][] activeShark(Shark[] sharks, int[][] smellIdxGrid, int N, int M) {

    int[][] sharkIdxGrid = new int[N + 1][N + 1];
    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        int[] nextPoint = getNextPoint(shark, smellIdxGrid, N);

        if (nextPoint != null) {
          if (sharkIdxGrid[nextPoint[0]][nextPoint[1]] != 0 &&
                  sharkIdxGrid[nextPoint[0]][nextPoint[1]] < shark.idx) {
            shark.isDead = true;
            continue;
          }
          moveShark(shark, nextPoint, sharkIdxGrid);
        } else {
          nextPoint = getNextPointSmell(shark, smellIdxGrid, N);

          if (nextPoint != null) {
            moveShark(shark, nextPoint, sharkIdxGrid);
          }
        }

      }
    }

    return sharkIdxGrid;
  }
  private static void moveShark(Shark shark, int[] nextPoint, int[][] sharkIdxGrid) {
    shark.row = nextPoint[0];
    shark.col = nextPoint[1];
    shark.direction = nextPoint[2];
    sharkIdxGrid[shark.row][shark.col] = shark.idx;
  }
  private static int[] getNextPointSmell(Shark shark, int[][] smellIdxGrid, int N) {

    for (int i = 1; i <= 4; i++) {
      int nextRow = shark.row + dr[shark.priority[shark.direction][i]];
      int nextCol = shark.col + dc[shark.priority[shark.direction][i]];

      if (isValidIdx(nextRow, nextCol, N) &&
              smellIdxGrid[nextRow][nextCol] == shark.idx) {
        return new int[]{nextRow, nextCol, shark.priority[shark.direction][i]};
      }
    }
    return null;
  }
  private static int[] getNextPoint(Shark shark, int[][] smellIdxGrid, int N) {

    for (int i = 1; i <= 4; i++) {
      int nextRow = shark.row + dr[shark.priority[shark.direction][i]];
      int nextCol = shark.col + dc[shark.priority[shark.direction][i]];

      if (isValidIdx(nextRow, nextCol, N) &&
              smellIdxGrid[nextRow][nextCol] == 0) {
        return new int[]{nextRow, nextCol, shark.priority[shark.direction][i]};
      }
    }
    return null;
  }
  private static boolean isValidIdx(int row, int col, int N) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
  private static void paintSmell(Shark[] sharks, Queue<Smell> smellBuffer, int[][] smellIdxGrid, int M, int k) {

    for (int i = 1; i <= M; i++) {
      if (!sharks[i].isDead) {
        smellIdxGrid[sharks[i].row][sharks[i].col] = i;
        smellBuffer.add(new Smell(sharks[i].row, sharks[i].col, i, k));
      }
    }
  }
}
class Smell {
  int row;
  int col;
  int sharkIdx;
  int age;

  Smell(int row, int col, int sharkIdx, int age) {
    this.row = row;
    this.col = col;
    this.sharkIdx = sharkIdx;
    this.age = age;
  }

}
class Shark {
  int idx;
  int row;
  int col;
  int direction;
  int[][] priority;
  boolean isDead;

  Shark(int idx, int row, int col) {
    this.idx = idx;
    this.row = row;
    this.col = col;
  }
}