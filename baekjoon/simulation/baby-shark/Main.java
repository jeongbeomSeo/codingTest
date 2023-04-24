import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 좌, 우, 하
  static int[] dr = {-1, 0, 0, 1};
  static int[] dc = {0, -1, 1, 0};

  // 좌, 우
  static int[] topDc = {-1, 1, 0};
  static int N;

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

    int time = simulation(grid, row, col);

    System.out.println(time);
  }
  static int simulation(int[][] grid, int row, int col) {

    Shark shark = new Shark(row, col);

    int t;
    for (t = 0; t <= N * N;) {

      int count = 0;
      for (int i = 0 ; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size) count++;
        }
      }

      // 먹을 수 있는 경우
      if (count >= 1) {
        State state = Bfs(grid, shark.row, shark.col, shark.size);
        if (state != null) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          t += state.time;
        }
        // 상황 종료
        else break;
      }
      // 상황 종료
      else break;
    }

    return t;
  }
  static State Bfs(int[][] grid, int row, int col, int size) {
    Queue<State> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][N];

    if (row == 0) {
      if (isValidIdx(row, col - 1)) {
        q.add(new State(row, col - 1, 1));
        isVisited[row][col - 1] = true;
      }
      if (isValidIdx(row, col + 1))  {
        q.add(new State(row, col + 1, 1));
        isVisited[row][col + 1] = true;
      }
      if (isValidIdx(row + 1, col)) {
        q.add(new State(row + 1, col, 1));
        isVisited[row + 1][col] = true;
      }
    }
    else  {
      q.add(new State(row, col, 0));
      isVisited[row][col] = true;
    }


    while (!q.isEmpty()) {
      State curState = q.poll();

      if (grid[curState.row][curState.col] != 0 && grid[curState.row][curState.col] < size) {
        grid[curState.row][curState.col] = 0;
        return curState;
      }

      // 맨 위인 경우
      if (curState.top) {
        for (int i = 0; i < 2; i++) {
          int curRow = curState.row;
          int curCol = curState.col + topDc[i];

          if (isValidIdx(curRow, curCol) &&
                  !isVisited[curRow][curCol] && grid[curRow][curCol] <= size) {
            q.add(new State(curRow, curCol, curState.time + 1));
            isVisited[curRow][curCol] = true;
          }
        }
      }
      // 아닌 경우
      else {
        for (int i = 0; i < 4; i++) {
          int curRow = curState.row + dr[i];
          int curCol = curState.col + dc[i];

          if (isValidIdx(curRow, curCol) &&
                  !isVisited[curRow][curCol] && grid[curRow][curCol] <= size) {
            q.add(new State(curRow, curCol, curState.time + 1));
            isVisited[curRow][curCol] = true;
          }
        }
      }
    }
   return null;
  }
  static boolean isValidIdx(int row, int col) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}

class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.size = 2;
    this.exp = 0;
  }
}

class State {
  int row;
  int col;
  int time;
  boolean top;


  State(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
    top = row == 0;
  }

}