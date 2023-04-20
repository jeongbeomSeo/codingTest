import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 동, 남, 서, 북;
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    // [K 개의 사과][row, col]
    int[][] apple = new int[K][2];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      apple[i][0] = Integer.parseInt(st.nextToken());
      apple[i][1] = Integer.parseInt(st.nextToken());
    }

    int L = Integer.parseInt(br.readLine());

    String[] changeDirection = new String[100];

    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      changeDirection[idx] = d;
    }

    int result = simulation(apple, changeDirection, N, K);

    System.out.println(result);

  }
  static int simulation(int[][] apple, String[] changeDirection, int N, int K) {

    // {row, col}
    Deque<Integer[]> deque = new ArrayDeque<>();
    boolean[][] flag = new boolean[N][N];

    deque.add(new Integer[]{0, 0});
    flag[0][0] = true;

    int size = 1;
    int curDirection = 0;
    for (int time = 1; time < N * N; time++) {
      int nextRow = -1;
      int nextCol = -1;
      boolean head = true;

      for (int i = 0; i < size; i++) {
        Integer[] curState = deque.getFirst();
        int curRow = curState[0];
        int curCol = curState[1];

        // 움직일 것이니깐 해당 부분 마킹 지워놓기
        flag[curRow][curCol] = false;

        // 머리일 경우 해당 과정 수행
        if (head) {
          nextRow = curRow;
          nextCol = curCol;

          curRow = curRow + dr[curDirection];
          curCol = curCol + dc[curDirection];

          // 움직인 부분에 벽이나 몸통이 있는 경우 종료
          if (flag[curRow][curCol] && !validIdx(curRow, curCol, N)) return time;

          // 사과 있는 위치로 이동한 것인지 확인
          for (int appIdx = 0; appIdx < K; appIdx++) {
            // 먹지 않은 것 중에 확인
            if (apple[appIdx][0] != -1) {
              if (nextRow == apple[appIdx][0] && nextCol == apple[appIdx][1]) {
                apple[appIdx][0] = -1;
                size++;
              }
            }
          }

          // 처리 끝난 후 머리일 경우 방향 전환
          if (changeDirection[time] != "") {
            if (changeDirection[time] == "L") {
              curDirection = curDirection != 0 ? curDirection - 1 : 3;
            }
            else
              curDirection = curDirection != 3 ? curDirection + 1 : 0;
          }
          // 모든 처리 끝난 후 해당 부분 위치 마킹
          deque.addLast(new Integer[]{curRow, curCol});
          flag[curRow][curCol] = true;
          head = false;
        }
        // 머리가 아닌 경우
        else {
          deque.addLast(new Integer[]{nextRow, nextCol});
          flag[nextRow][nextCol] = true;
          nextRow = curRow;
          nextCol = curCol;
        }

      }
    }
    return N * N;
  }
  static boolean validIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}
