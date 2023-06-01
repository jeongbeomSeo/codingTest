import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, T;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    int[][] disc = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        disc[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotateList = new int[T][3];
    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      rotateList[i][0] = Integer.parseInt(st.nextToken());
      rotateList[i][1] = Integer.parseInt(st.nextToken());
      rotateList[i][2] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(disc, rotateList));
  }
  static int simulation(int[][] disc, int[][] rotateList){

    int num = 0;
    while (num < T) {

      for (int i = 1; i <= N; i++) {
        if (i % rotateList[num][0] == 0) rotate(disc[i], rotateList[num][1], rotateList[num][2]);
      }

      boolean[][] delete = new boolean[N + 1][M + 1];

      boolean notDelete = true;
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j < M; j++) {
          if (disc[i][j] != 0 && disc[i][j] == disc[i][j + 1]) {
            delete[i][j] = delete[i][j + 1] = true;
            notDelete = false;
          }
        }
        if (disc[i][1] != 0 && disc[i][1] == disc[i][M]) {
          delete[i][1] = delete[i][M] = true;
          notDelete = false;
        }
      }
      for (int j = 1; j <= M; j++) {
        for (int i = 1; i < N; i++) {
          if (disc[i][j] != 0 && disc[i][j] == disc[i + 1][j]) {
            delete[i][j] = delete[i + 1][j] = true;
            notDelete = false;
          }
        }
      }

      if (!notDelete) {
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (delete[i][j]) disc[i][j] = 0;
          }
        }
      }
      else {
        int totalNumberSum = 0;
        int notZero = 0;
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (disc[i][j] != 0) {
              notZero++;
              totalNumberSum += disc[i][j];
            }
          }
        }
        double average = (double)totalNumberSum / notZero;
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (disc[i][j] != 0) {
              if (disc[i][j] > average) disc[i][j]--;
              else if (disc[i][j] < average) disc[i][j]++;
            }
          }
        }
      }
      num++;
    }

    int sum = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        sum += disc[i][j];
      }
    }
    return sum;
  }
  static void rotate(int[] disc, int direction, int count) {

    while (count-- > 0) {
      int temp;
      switch (direction) {
        case 0:
          temp = disc[M];
          for (int i = M; i > 1; i--) disc[i] = disc[i - 1];
          disc[1] = temp;
          break;
        case 1:
          temp = disc[1];
          for (int i = 1; i < M; i++) disc[i] = disc[i + 1];
          disc[M] = temp;
          break;
        default:
          break;
      }
    }
  }
}
