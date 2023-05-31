import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {-1, 0, 1, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[M][N];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] room_number = new int[M][N];
    int number = 1;

    int maxSize = 0;
    int[] size = new int[50 * 50 + 1];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (room_number[i][j] == 0) {
          size[number] = bfs(grid, room_number, number, i, j);
          maxSize = Math.max(maxSize, size[number]);
          number++;
        }
      }
    }

    int connectMaxSize = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        for (int d = 0; d < 4; d++) {
          int nextRow = i + dr[d];
          int nextCol = j + dc[d];
          if (isValidIdx(nextRow, nextCol, M, N) && room_number[i][j] != room_number[nextRow][nextCol]) {
            connectMaxSize = Math.max(connectMaxSize, size[room_number[i][j]] + size[room_number[nextRow][nextCol]]);
          }
        }
      }
    }

    System.out.println(number - 1);
    System.out.println(maxSize);
    System.out.println(connectMaxSize);
  }
  static boolean isValidIdx(int row, int col, int M, int N) {
    return row >= 0 && col >= 0 && row < M && col < N;
  }
  // return: size
  static int bfs(int[][] grid, int[][] room_number, int number, int initRow, int initCol) {

    Queue<Point> q = new ArrayDeque<>();
    Point initPoint = new Point(initRow, initCol);
    int size = 0;
    q.add(initPoint);
    room_number[initPoint.row][initPoint.col] = number;
    size++;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        if ((grid[point.row][point.col] & (1 << i)) == 0) {
          int nextRow = point.row + dr[i];
          int nextCol = point.col + dc[i];

          if (room_number[nextRow][nextCol] == 0) {
            q.add(new Point(nextRow, nextCol));
            room_number[nextRow][nextCol] = number;
            size++;
          }
        }
      }
    }

    return size;
  }
}

class Point {
  int row;
  int col;

  Point(int row, int col) {
    this.row = row;
    this.col = col;
  }
}