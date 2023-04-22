import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int[][] apple = new int[K][2];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      apple[i][0] = Integer.parseInt(st.nextToken()) - 1;
      apple[i][1] = Integer.parseInt(st.nextToken()) - 1;
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[N * N + 1];

    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      String val = st.nextToken();
      rotate[idx] = val;
    }

    System.out.println(simulation(N, apple, K, rotate));
  }
  static int simulation(int N, int[][] apple,  int K, String[] rotate) {

    int time;
    boolean success = true;
    Deque<Node> deque = new ArrayDeque<>();
    boolean[][] isSnake = new boolean[N][N];
    deque.add(new Node(0, 0, true));
    isSnake[0][0] = true;
    int direction = 1;

    for (time = 1; time < N * N; time++) {
      boolean eatApple = false;

      int size = deque.size();

      int saveRow = -1;
      int saveCol = -1;
      while (size-- > 0) {
        Node curNode = deque.pollFirst();
        isSnake[curNode.row][curNode.col] = false;
        // 머리인 경우
        if (curNode.head) {
          saveRow = curNode.row;
          saveCol = curNode.col;
          // 헤드 부분 현재 방향 앞으로 이동
          curNode.row += dr[direction];
          curNode.col += dc[direction];

          // 방향 전환 유뮤 확인
          if (rotate[time] != null) {
            if (rotate[time].equals("L"))
              direction = (direction + 3) % 4;
            else
              direction = (direction + 1) % 4;
          }

          // 이동 후 제약 조건 두 개 확인
          if (!isValidIdx(curNode.row, curNode.col, N) || isSnake[curNode.row][curNode.col] ) {
            success = false;
            break;
          }

          // 사과 위치 확인
          for (int i = 0; i < K; i++) {
            int appleRow = apple[i][0];
            int appleCol = apple[i][1];

            // 사과를 먹은 경우 뒷부분은 바뀌지 않는다고 생각하고
            // 이전 머리 부분에 위치하고 있던 자리에 새로 노드 하나 만들어 주고, 기존 헤드는 위치를 옮겼으므로 그대로 삽입
            // 사과를 먹은 경우 해당 과정 처리후 다음 시간을 바로 이동
            // 해당 방식으로 처리를 하기 위해서는 사과를 먹기 전에 필요한 모든 과정이 이루어져야 합니다.
            if (curNode.row == appleRow && curNode.col == appleCol) {
              deque.addFirst(new Node(saveRow, saveCol, false));
              deque.addFirst(curNode);
              isSnake[saveRow][saveCol] = true;
              isSnake[curNode.row][curNode.col] = true;
              apple[i][0] = -1;
              eatApple = true;
              break;
            }
          }

          if (eatApple) break;

          // 일반적인 경우 덱의 맨 뒤에 넣어주기
          deque.addLast(curNode);
          isSnake[curNode.row][curNode.col] = true;

        }
        // 머리가 아닌 경우
        else {
          // saveRow에는 당연히 현재 위치를 넣어줘야 하는 부분
          int tempRow = curNode.row;
          int tempCol = curNode.col;
          // 이전 위치 받아서 노드에 넣어주기
          curNode.row = saveRow;
          curNode.col = saveCol;
          saveRow = tempRow;
          saveCol = tempCol;
          deque.addLast(curNode);
          isSnake[curNode.row][curNode.col] = true;
        }
      }
      if (!success) break;
    }
    return time;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}
class Node {
  int row;
  int col;
  boolean head;

  Node(int row, int col, boolean head) {
    this.row = row;
    this.col = col;
    this.head = head;
  }

}