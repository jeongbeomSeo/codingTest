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
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      apple[i] = new int[]{r, c};
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[100001];
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx  = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      rotate[idx] = d;
    }

    System.out.println(simulation(apple, rotate, N, K));
  }
  static int simulation(int[][] apple, String[] rotate, int N, int K) {

    boolean[][] flag = new boolean[N][N];
    Deque<Node> deque = new ArrayDeque<>();
    deque.add(new Node(0, 0));
    flag[0][0] = true;
    int direction = 1;

    int t;
    for (t = 1; t < N * N; t++) {
      int size = deque.size();
      boolean head = true;
      int nextIdxRow = deque.peekFirst().row;
      int nextIdxCol = deque.peekFirst().col;

      for (int i = 0; i < size; i++) {
        if (head) {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;

          curNode.row += dr[direction];
          curNode.col += dc[direction];

          if (!isValidIdx(curNode.row, curNode.col, N) || flag[curNode.row][curNode.col]) return t;

          for (int appleIdx = 0; appleIdx < K; appleIdx++) {
            if (apple[appleIdx][0] != -1) {
              if (apple[appleIdx][0] == curNode.row && apple[appleIdx][1] == curNode.col) {
                Node addLastNode = deque.peekLast();
                if (size == 1) deque.addLast(new Node(nextIdxRow, nextIdxCol));
                else deque.addLast(new Node(addLastNode.row, addLastNode.col));
                size++;
                apple[appleIdx][0] = -1;
                break;
              }
            }
          }

          if (rotate[t] != null) {
            if (rotate[t].equals("L")) {
              if (direction != 0) direction--;
              else direction = 3;
            }
            else
              if (direction != 3) direction++;
              else direction = 0;
          }

          deque.addLast(new Node(curNode.row, curNode.col));
          flag[curNode.row][curNode.col] = true;
          head = false;
        }
        else {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;
          deque.addLast(new Node(nextIdxRow, nextIdxCol));
          flag[nextIdxRow][nextIdxCol] = true;
          nextIdxRow = curNode.row;
          nextIdxCol = curNode.col;

        }
      }
    }
    return t;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}