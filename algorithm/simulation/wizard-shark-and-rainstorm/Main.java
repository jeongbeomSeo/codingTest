import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] getCheckDr = {-1, -1, 1, 1};
  static int[] getCheckDc = {-1, 1, 1, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] waterAmounts = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        waterAmounts[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] command = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      command[i][0] = Integer.parseInt(st.nextToken());
      command[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(waterAmounts, command));
  }
  static int simulation(int[][] waterAmounts, int[][] command) {

    Queue<Cloud> clouds = new ArrayDeque<>();
    initCloud(clouds);

    int time = 0;
    while (time < M) {
      clouds = moveCloudAndRain(clouds, waterAmounts, command[time]);
      clouds = waterCopyBug(clouds, waterAmounts);

      time++;
    }

    return howManyWaterInBuckets(waterAmounts);
  }
  static int howManyWaterInBuckets(int[][] waterAmounts) {
    int sum = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        sum += waterAmounts[i][j];
      }
    }
    return sum;
  }
  static Queue<Cloud> waterCopyBug(Queue<Cloud> clouds, int[][] waterAmounts) {

    Queue<Cloud> nextClouds = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N + 1][N + 1];

    Queue<BucketPlusWater> bucketPlusWaters = new ArrayDeque<>();
    while (!clouds.isEmpty()) {
      Cloud beforeCloudPoints = clouds.poll();
      isVisited[beforeCloudPoints.row][beforeCloudPoints.col] = true;

      int amount = 0;
      for (int i = 0; i < 4; i++) {
        int nextRow = beforeCloudPoints.row + getCheckDr[i];
        int nextCol = beforeCloudPoints.col + getCheckDc[i];

        if (isValidIdx(nextRow, nextCol) && waterAmounts[nextRow][nextCol] != 0) {
          amount++;
        }
      }
      if (amount != 0) bucketPlusWaters.add(
              new BucketPlusWater(beforeCloudPoints.row, beforeCloudPoints.col, amount));

    }

    while (!bucketPlusWaters.isEmpty()) {
      BucketPlusWater bpw = bucketPlusWaters.poll();

      waterAmounts[bpw.row][bpw.col] += bpw.amount;
    }

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (!isVisited[i][j] && waterAmounts[i][j] >= 2) {
          nextClouds.add(new Cloud(i, j));
          waterAmounts[i][j] -= 2;
        }
      }
    }
    return nextClouds;
  }
  static Queue<Cloud> moveCloudAndRain(Queue<Cloud> clouds, int[][] waterAmounts, int[] command) {
    int direction = command[0];
    int speed = command[1];

    int remainSpeed = speed % N;

    Queue<Cloud> movedClouds = new ArrayDeque<>();
    while (!clouds.isEmpty()) {
      Cloud cloud = clouds.poll();

      int nextRow = cloud.row + dr[direction] * remainSpeed;
      int nextCol = cloud.col + dc[direction] * remainSpeed;

      if (nextRow > N) nextRow -= N;
      else if (nextRow <= 0) nextRow += N;

      if (nextCol > N) nextCol -= N;
      else if (nextCol <= 0) nextCol += N;

      cloud.row = nextRow;
      cloud.col = nextCol;
      waterAmounts[cloud.row][cloud.col]++;
      movedClouds.add(cloud);
    }

    return movedClouds;
  }
  static void initCloud(Queue<Cloud> clouds) {

    for (int i = N - 1; i <= N; i++) {
      for (int j = 1; j <= 2; j++) {
        clouds.add(new Cloud(i, j));
      }
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}
class BucketPlusWater {
  int row;
  int col;
  int amount;

  BucketPlusWater(int row, int col, int amount) {
    this.row = row;
    this.col = col;
    this.amount = amount;
  }
}
class Cloud {
  int row;
  int col;

  Cloud(int row, int col) {
    this.row = row;
    this.col = col;
  }
}