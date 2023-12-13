import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());

    int[][] discs = new int[N + 1][M + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j < M + 1; j++) {
        discs[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotateInfo = new int[T][3];
    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        rotateInfo[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(discs, rotateInfo, N, M, T));
  }
  private static int simulation(int[][] discs, int[][] roateInfo, int N, int M, int T) {

    int time = 0;
    while (time != T) {

      int[] info = roateInfo[time];

      for (int i = info[0]; i < N + 1; i += info[0]) {
        discs[i] = rotate(discs[i], info[1], info[2], M);
      }

      int[][] nextDiscs = new int[N + 1][M + 1];
      if (hasRemoveE(discs, N, M)) {
        for (int i = 1; i < N + 1; i++) {
          nextDiscs[i] = getNextDiscRemoved(discs, i, N, M);
        }
      } else {
        double avg = getAverage(discs, N, M);
        for (int i = 1; i < N + 1; i++) {
          nextDiscs[i] = getNextDiscUsedAvg(discs[i], M, avg);
        }
      }

      discs = nextDiscs;
      time++;
    }

    return getFinalScore(discs, N, M);
  }
  private static int getFinalScore(int[][] discs, int N, int M) {
    int sum = 0;

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        sum += discs[i][j];
      }
    }

    return sum;
  }
  private static int[] getNextDiscRemoved(int[][] discs, int i, int N, int M) {

    int[] nextDisc = new int[M + 1];
    for (int j = 1; j < M + 1; j++) {
      if (discs[i][j] != 0 && hasNearEqualNumber(discs, i, j, N, M)) nextDisc[j] = 0;
      else nextDisc[j] = discs[i][j];
    }

    return nextDisc;
  }
  private static boolean hasNearEqualNumber(int[][] disc, int i, int j, int N, int M) {

    if (i == 1) {
      if (disc[i][j] == disc[i + 1][j]) return true;
    } else if (i == N) {
      if (disc[i][j] == disc[i - 1][j]) return true;
    } else {
      if (disc[i][j] == disc[i + 1][j] || disc[i][j] == disc[i - 1][j]) return true;
    }

    if (j == 1) {
      if (disc[i][j] == disc[i][j + 1] || disc[i][j] == disc[i][M]) return true;
    } else if (j == M) {
      if (disc[i][j] == disc[i][j - 1] || disc[i][j] == disc[i][1]) return true;
    } else {
      if (disc[i][j] == disc[i][j + 1] || disc[i][j] == disc[i][j - 1]) return true;
    }

    return false;
  }
  private static int[] getNextDiscUsedAvg(int[] disc, int M, double average) {

    int[] nextDisc = new int[M + 1];
    if (average != 0) {
      for (int i = 1; i < M + 1; i++) {
        if (disc[i] == 0) nextDisc[i] = 0;
        else if (disc[i] > average) {
          nextDisc[i] = disc[i] - 1;
        } else if (disc[i] < average){
          nextDisc[i] = disc[i] + 1;
        } else {
          nextDisc[i] = disc[i];
        }
      }
    }
    return nextDisc;
  }
  private static double getAverage(int[][] disc, int N, int M) {

    double sum = 0;
    int count = 0;
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        sum += disc[i][j];
        if (disc[i][j] != 0) count++;
      }
    }

    if (count == 0) return 0;
    return sum / count;
  }
  private static boolean hasRemoveE(int[][] discs, int N, int M) {

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        if (discs[i][j] != 0 && hasNearEqualNumber(discs, i, j, N, M)) return true;
      }
    }

    return false;
  }
  private static int[] rotate(int[] disc, int direction, int K, int M) {
    if (direction == 0) {
      return rotateFoward(disc, K, M);
    } else {
      return rotateReverse(disc, K, M);
    }
  }
  private static int[] rotateFoward(int[] disc, int K, int M) {

    int[] result = new int[M + 1];

    for (int i = M + 1 - K; i < M + 1; i++) {
      result[i - M + K] = disc[i];
    }
    System.arraycopy(disc, 1, result, K + 1, M - K);

    return result;
  }
  private static int[] rotateReverse(int[] disc, int K, int M) {

    int[] result = new int[M + 1];

    for (int i = K; i > 0; i--) {
      result[i + M - K] = disc[i];
    }
    System.arraycopy(disc, K + 1, result, 1, M - K);

    return result;
  }
}