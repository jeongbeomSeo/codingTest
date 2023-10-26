# 스타트 택시

**골드 2**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)|	512 MB	|27692	|6434	|3849	|20.920%|

스타트링크가 "스타트 택시"라는 이름의 택시 사업을 시작했다. 스타트 택시는 특이하게도 손님을 도착지로 데려다줄 때마다 연료가 충전되고, 연료가 바닥나면 그 날의 업무가 끝난다.

택시 기사 최백준은 오늘 M명의 승객을 태우는 것이 목표이다. 백준이 활동할 영역은 N×N 크기의 격자로 나타낼 수 있고, 각 칸은 비어 있거나 벽이 놓여 있다. 택시가 빈칸에 있을 때, 상하좌우로 인접한 빈칸 중 하나로 이동할 수 있다. 알고리즘 경력이 많은 백준은 특정 위치로 이동할 때 항상 최단경로로만 이동한다.

M명의 승객은 빈칸 중 하나에 서 있으며, 다른 빈칸 중 하나로 이동하려고 한다. 여러 승객이 같이 탑승하는 경우는 없다. 따라서 백준은 한 승객을 태워 목적지로 이동시키는 일을 M번 반복해야 한다. 각 승객은 스스로 움직이지 않으며, 출발지에서만 택시에 탈 수 있고, 목적지에서만 택시에서 내릴 수 있다.

백준이 태울 승객을 고를 때는 현재 위치에서 최단거리가 가장 짧은 승객을 고른다. 그런 승객이 여러 명이면 그중 행 번호가 가장 작은 승객을, 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다. 택시와 승객이 같은 위치에 서 있으면 그 승객까지의 최단거리는 0이다. 연료는 한 칸 이동할 때마다 1만큼 소모된다. 한 승객을 목적지로 성공적으로 이동시키면, 그 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전된다. 이동하는 도중에 연료가 바닥나면 이동에 실패하고, 그 날의 업무가 끝난다. 승객을 목적지로 이동시킨 동시에 연료가 바닥나는 경우는 실패한 것으로 간주하지 않는다.

![](https://upload.acmicpc.net/b4dfd78f-5276-44a4-a1f1-a5ccde6fbc8b/-/preview/)
<그림 1>

<그림 1>은 택시가 활동할 영역의 지도를 나타내며, 택시와 세 명의 승객의 출발지와 목적지가 표시되어 있다. 택시의 현재 연료 양은 15이다. 현재 택시에서 각 손님까지의 최단거리는 각각 9, 6, 7이므로, 택시는 2번 승객의 출발지로 이동한다.

|![](https://upload.acmicpc.net/3a0360d0-7aa4-4f6e-89aa-8f29ceb3db8d/-/preview/)|![](https://upload.acmicpc.net/fb1d41e5-a420-4957-8fe8-1a6f822b284e/-/preview/)|
|---|---|
|<그림 2>|<그림 3>

|![](https://upload.acmicpc.net/a4ad059c-f909-4cf2-a401-9d72a69a2549/-/preview/)|![](https://upload.acmicpc.net/3abc49bb-33a3-4828-a6c3-1be22fd3967d/-/preview/)|
|---|---|
|<그림 4>|<그림 5>|

<그림 4>와 <그림 5>는 택시가 1번 승객을 태워 목적지로 이동시키는 경로를 나타낸다. 남은 연료의 양은 15 - 7 - 7 + 7×2 = 15이다.

|![](https://upload.acmicpc.net/86aa0566-f468-4343-a83d-d978f0120cec/-/preview/)|![](https://upload.acmicpc.net/aebc9d40-2c56-4e6c-b914-d8d9b55f8ff5/-/preview/)|
|---|---|
|<그림 6>|<그림 7>|

<그림 6>과 <그림 7>은 택시가 3번 승객을 태워 목적지로 이동시키는 경로를 나타낸다. 최종적으로 남은 연료의 양은 15 - 5 - 4 + 4×2 = 14이다.

모든 승객을 성공적으로 데려다줄 수 있는지 알아내고, 데려다줄 수 있을 경우 최종적으로 남는 연료의 양을 출력하는 프로그램을 작성하시오.

## 입력 

첫 줄에 N, M, 그리고 초기 연료의 양이 주어진다. (2 ≤ N ≤ 20, 1 ≤ M ≤ N2, 1 ≤ 초기 연료 ≤ 500,000) 연료는 무한히 많이 담을 수 있기 때문에, 초기 연료의 양을 넘어서 충전될 수도 있다.

다음 줄부터 N개의 줄에 걸쳐 백준이 활동할 영역의 지도가 주어진다. 0은 빈칸, 1은 벽을 나타낸다.

다음 줄에는 백준이 운전을 시작하는 칸의 행 번호와 열 번호가 주어진다. 행과 열 번호는 1 이상 N 이하의 자연수이고, 운전을 시작하는 칸은 빈칸이다.

그다음 줄부터 M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다. 모든 출발지와 목적지는 빈칸이고, 모든 출발지는 서로 다르며, 각 손님의 출발지와 목적지는 다르다.

## 출력 

모든 손님을 이동시키고 연료를 충전했을 때 남은 연료의 양을 출력한다. 단, 이동 도중에 연료가 바닥나서 다음 출발지나 목적지로 이동할 수 없으면 -1을 출력한다. 모든 손님을 이동시킬 수 없는 경우에도 -1을 출력한다.

## 예제 입력 1

```
6 3 15
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
```

## 예제 출력 1

```
14
```

## 예제 입력 2

```
6 3 13
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
```

## 예제 출력 2

```
-1
```

## 예제 입력 3

```
6 3 100
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
```

## 예제 출력 3

```
-1
```

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int gas = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    int initRow = Integer.parseInt(st.nextToken()) - 1;
    int initCol = Integer.parseInt(st.nextToken()) - 1;

    Taxi taxi = new Taxi(initRow, initCol, gas);
    int idx = 2;
    Passenger[] passengers = new Passenger[M + 2];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int startRow = Integer.parseInt(st.nextToken()) - 1;
      int startCol = Integer.parseInt(st.nextToken()) - 1;
      int endRow = Integer.parseInt(st.nextToken()) - 1;
      int endCol = Integer.parseInt(st.nextToken()) - 1;

      grid[startRow][startCol] = idx;
      passengers[idx++] = new Passenger(startRow, startCol, endRow, endCol);
    }

    System.out.println(simulation(grid, passengers, taxi));
  }
  static int simulation(int[][] grid, Passenger[] passengers, Taxi taxi) {

    int count = 0;
    while (count < M) {

      int passengerIdx = findPassenger(grid, taxi);

      if (passengerIdx == -1) break;

      if (!goDest(grid, taxi, passengers[passengerIdx])) break;

      count++;
    }

    if (count != M) return -1;

    return taxi.gas;
  }
  static boolean goDest(int[][] grid, Taxi taxi, Passenger passenger) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Taxi> q = new ArrayDeque<>();
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (curTaxi.row == passenger.endPoint.row && curTaxi.col == passenger.endPoint.col) {
        result = curTaxi;
        break;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1, curTaxi.moveCount + 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    if (result == null || result.gas < 0) return false;
    else {
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas + 2 * result.moveCount;
      return true;
    }
  }
  // return Passenger Idx
  static int findPassenger(int[][] grid, Taxi taxi) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Taxi> q = new ArrayDeque<>();
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (result != null && result.gas != curTaxi.gas) break;

      if (grid[curTaxi.row][curTaxi.col] >= 2) {
        if (result == null) result = curTaxi;
        else {
          if (result.compare(curTaxi)) result = curTaxi;
        }
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }


    if (result == null || result.gas < 0) return -1;
    else {
      int idx = grid[result.row][result.col];
      grid[result.row][result.col] = 0;
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas;
      return idx;
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Taxi {
  int row;
  int col;
  int gas;
  int moveCount;

  Taxi (int row, int col, int gas) {
    this.row = row;
    this.col = col;
    this.gas = gas;
    this.moveCount = 0;
  }

  Taxi (int row, int col, int gas, int moveCount) {
    this(row, col, gas);
    this.moveCount = moveCount;
  }

  boolean compare(Taxi o) {
    if (this.row > o.row) return true;
    else if (this.row < o.row) return false;
    else return this.col > o.col;
  }
}
class Point {
  int row;
  int col;
  int gas;

  Point (int row, int col, int gas) {
    this.row = row;
    this.col = col;
    this.gas = gas;
  }
}
class Passenger {
  Point startPoint;
  Point endPoint;

  Passenger(int startRow, int startCol, int endRow, int endCol) {
    this.startPoint = new Point(startRow, startCol, 0);
    this.endPoint = new Point(endRow, endCol, 0);
  }
}
```