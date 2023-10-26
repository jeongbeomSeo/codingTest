# 마법사 상어와 비바라기 

**골드 5**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)	|1024 MB	|11513	|5941	|4052|	49.957%|

## 문제 

마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 마법을 할 수 있다. 오늘 새로 배운 마법은 비바라기이다. 비바라기를 시전하면 하늘에 비구름을 만들 수 있다. 오늘은 비바라기를 크기가 N×N인 격자에서 연습하려고 한다. 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다. (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.

격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.

비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.

1. 모든 구름이 d<sub>i</sub> 방향으로 s<sub>i</sub>칸 이동한다.
2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
3. 구름이 모두 사라진다.
4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
- 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
- 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.

M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.

## 입력 

첫째 줄에 N, M이 주어진다.

둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.

다음 M개의 줄에는 이동의 정보 d<sub>i</sub>, s<sub>i</sub>가 순서대로 한 줄에 하나씩 주어진다.

## 출력 

첫째 줄에 M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.

## 제한 

- 2 ≤ N ≤ 50
- 1 ≤ M ≤ 100
- 0 ≤ A[r][c] ≤ 100
- 1 ≤ d<sub>i</sub> ≤ 8
- 1 ≤ s<sub>i</sub> ≤ 50

## 예제 입력 1

```
5 4
0 0 1 0 2
2 3 2 1 0
4 3 2 9 0
1 0 2 9 0
8 8 2 1 0
1 3
3 4
8 1
4 8
```

## 예제 출력 1

```
77
```

## 나의 코드 

**AC**

```java
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
```
