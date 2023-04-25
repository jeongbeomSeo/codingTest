import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  // 북, 서, 남, 동
  public static int [] dc = {0, 0, 1, -1}, dr = {-1, 1, 0, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][N];

    int row = 0;
    int col = 0;
    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N;j ++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          row = i;
          col = j;
          grid[i][j] = 0;
        }
      }
    }

    System.out.println(simulation(grid, row , col));
  }
  static int simulation(int[][] grid, int row, int col) {

    int time = 0;
    Shark shark = new Shark(row, col);
    while (true) {
      int howManyCanEatFish = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size) howManyCanEatFish++;
        }
      }

      if (howManyCanEatFish >= 1) {
        State state = bfs(grid, shark.row, shark.col, shark.size);
        if (state != null) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          time += state.time;
          grid[state.row][state.col] = 0;
        }
        else break;
      }
      else break;
    }
    return time;
  }
  static State bfs(int[][] grid, int row, int col, int size) {

    Queue<State> q = new ArrayDeque<>();
    q.add(new State(row, col, grid[row][col], 0));
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    int time = 0;
    State priorityState = null;
    while (!q.isEmpty()) {
      State curState = q.poll();

      // 한 타임이 끝난 경우
      if (time != curState.time) {
        // 한 타임 동안 발견 못했을 경우
        if (priorityState == null) time = curState.time;
        // 발견한 경우
        else return priorityState;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] <= size) {
            State nextState = new State(nextRow, nextCol, grid[nextRow][nextCol], time + 1);

            // 물고기 사이즈 < 아기 상어 사이즈 (0이 아닌 경우)
            if (nextState.fishSize != 0 && nextState.fishSize < size){
              // 이전에 발견한 State가 있는 경우
              if (priorityState != null)  {
                if (!priorityState.checkPriority(nextState)) priorityState = nextState;
              }
              // 이전에 발견한 State가 없는 경우
              else priorityState = nextState;
            }
            q.add(nextState);
            isVisited[nextRow][nextCol] = true;
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
  int fishSize;
  int time;

  State(int row, int col, int fishSize, int time) {
    this.row = row;
    this.col = col;
    this.fishSize = fishSize;
    this.time = time;
  }

  boolean checkPriority(State other) {
    if (this.row < other.row) return true;
    else if (this.row > other.row) return false;
    else {
      if (this.col < other.col) return true;
      else return false;
    }
  }
}