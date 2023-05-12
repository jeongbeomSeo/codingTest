import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  // 공백, 북, 남, 동, 서
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, 1, -1};

  static int R, C;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<Shark> sharks = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks.add(new Shark(row, col, speed, direction, size));
    }

    System.out.println(simulation(sharks, M));
  }
  static int simulation(ArrayList<Shark> sharks, int M) {

    int count_size = 0;
    int col = 1;
    for (; col <= C; col++) {

      // 상어 잡이
      int idx = fishing(sharks, col);

      if (idx != -1) {
        count_size += sharks.get(idx).size;
        sharks.remove(idx);
      }

      // 상어 이동
      active_move(sharks);
    }
    return count_size;
  }
  static int fishing(ArrayList<Shark> sharks, int col) {
    // 상어 잡이
    int idx = -1;
    for (int i = 0; i < sharks.size(); i++) {
      if (sharks.get(i).col == col) {
        if (idx == -1) idx = i;
        else {
          if (sharks.get(idx).row > sharks.get(i).row) idx = i;
        }
      }
    }
    return idx;
  }
  static void active_move(ArrayList<Shark> sharks) {

    int[][] buffer = new int[R + 1][C + 1];
    ArrayList<Integer> removeList = new ArrayList<>();

    for (int i = 0; i < sharks.size(); i++) {
      Shark shark = sharks.get(i);

      int count = 1;
      while (count++ <= shark.speed) {
        int row = shark.row + dr[shark.direction];
        int col = shark.col + dc[shark.direction];
        if (!isValidIdx(row, col)) {
          shark.direction = reverse(shark.direction);
          row = shark.row + dr[shark.direction];
          col = shark.col + dc[shark.direction];
        }

        shark.row = row;
        shark.col = col;
      }
      if (buffer[shark.row][shark.col] == 0) buffer[shark.row][shark.col] = i + 1;
      else {
        int prevSharkIdx = buffer[shark.row][shark.col] - 1;
        if (sharks.get(prevSharkIdx).size < shark.size) {
          buffer[shark.row][shark.col] = i + 1;
          removeList.add(prevSharkIdx);
        }
        else removeList.add(i);
      }
    }
    removeList.sort(Collections.reverseOrder());
    for (Integer integer : removeList) {
      sharks.remove((int) integer);
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }
  static int reverse(int direction) {
    if (direction == 1) return 2;
    else if (direction == 2) return 1;
    else if (direction == 3) return 4;
    else return 3;
  }
}

class Shark {
  int row;
  int col;
  int speed;
  int direction;
  int size;

  Shark(int row, int col, int speed, int direction, int size) {
    this.row = row;
    this.col = col;
    this.speed = speed;
    this.direction = direction;
    this.size = size;
  }
}