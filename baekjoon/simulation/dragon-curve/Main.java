import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Curve[] curves = new Curve[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int col = Integer.parseInt(st.nextToken());
      int row = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int generation = Integer.parseInt(st.nextToken());
      curves[i] = new Curve(row, col, direction, generation);
    }

    System.out.println(simulation(curves, N));
  }
  static int simulation(Curve[] curves, int N) {

    boolean[][] isVisited = new boolean[101][101];
    for (int time = 0; time < N; time++) {
      Curve curve = curves[time];

      dragonCurve(curve, isVisited);
    }

    int count = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (isVisited[i][j] && isVisited[i][j + 1] && isVisited[i + 1][j] && isVisited[i + 1][j + 1]) count++;
      }
    }
    return count;
  }
  static void dragonCurve(Curve curve, boolean[][] isVisited) {

    int row = curve.row;
    int col = curve.col;
    int direction = curve.direction;
    int gen = curve.generation;

    isVisited[row][col] = true;
    row += dr[direction];
    col += dc[direction];

    isVisited[row][col] = true;

    Stack<Integer> stack = new Stack<>();
    stack.add(direction);

    while (gen-- > 0) {
      int size = stack.size();

      while (--size >= 0) {
        int curDirection = (stack.get(size) + 1) % 4;

        row += dr[curDirection];
        col += dc[curDirection];
        isVisited[row][col] = true;
        stack.add(curDirection);
      }
    }
  }
}
class Curve {
  int row;
  int col;
  int direction;
  int generation;

  Curve(int row, int col, int direction, int generation) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.generation = generation;
  }
}