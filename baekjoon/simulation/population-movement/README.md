# 인구 이동

**골드 5**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB|	56439	|22637	|13174	|36.968%|

## 문제 

N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.

오늘부터 인구 이동이 시작되는 날이다.

인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.

- 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
- 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
- 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
- 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
- 연합을 해체하고, 모든 국경선을 닫는다.
각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)

둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)

인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

## 출력 

인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

## 예제 입력 1

```
2 20 50
50 30
20 40
```

## 예제 출력 1

```
1
```

초기 상태는 아래와 같다.

![](https://upload.acmicpc.net/2993ef69-f57e-4d46-a9b3-eb3a05612dc7/-/preview/)

L = 20, R = 50 이기 때문에, 모든 나라 사이의 국경선이 열린다. (열린 국경선은 점선으로 표시)

![](https://upload.acmicpc.net/3e73073e-b68e-478b-90fd-f158f44863b7/-/preview/)

연합은 하나 존재하고, 연합의 인구는 (50 + 30 + 20 + 40) 이다. 연합의 크기가 4이기 때문에, 각 칸의 인구수는 140/4 = 35명이 되어야 한다. 

![](https://upload.acmicpc.net/78951cb1-213d-416b-a64d-fb80697af36a/-/preview/)

## 예제 입력 2

```
2 40 50
50 30
20 40
```

## 예제 출력 2

```
0
```

경계를 공유하는 나라의 인구 차이가 모두 L보다 작아서 인구 이동이 발생하지 않는다.

## 예제 입력 3

```
2 20 50
50 30
30 40
```

## 예제 출력 3

```
1
```

초기 상태는 아래와 같다.

![](https://upload.acmicpc.net/c70d5726-35d0-4af8-96f7-f01371db935f/-/preview/)

L = 20, R = 50이기 때문에, 아래와 같이 국경선이 열린다.

![](https://upload.acmicpc.net/eff2e0d7-3b05-4b4d-88d6-4fc56fd946c6/-/preview/)

인구 수는 합쳐져있는 연합의 인구수는 (50+30+30) / 3 = 36 (소수점 버림)이 되어야 한다.

![](https://upload.acmicpc.net/c54b09bd-7b13-4f41-9c80-271497c3239e/-/preview/)

## 예제 입력 4

```
3 5 10
10 15 20
20 30 25
40 22 10
```

## 예제 출력 4

```
2
```

## 예제 입력 5

```
4 10 50
10 100 20 90
80 100 60 70
70 20 30 40
50 20 100 10
```

## 예제 출력 5

```
3
```

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 복, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, L, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));

  }
  static int simulation(int[][] grid) {
    int time = 0;

    while (true) {
      boolean action = false;
      boolean[][] isVisited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!isVisited[i][j]) {
            if (bfs(grid, isVisited, i, j)) action = true;
          }
        }
      }
      if (!action) break;
      time++;
    }

    return time;
  }

  static boolean bfs(int[][] grid, boolean[][] isVisited, int row, int col) {
    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;

    Node[] nodeList = new Node[N * N];
    int sum = 0;
    int size = 0;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      nodeList[size++] = curNode;
      sum += grid[curNode.row][curNode.col];

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          int diff = Math.abs(grid[curNode.row][curNode.col] - grid[nextRow][nextCol]);
          if (diff >= L && diff <= R) {
            q.add(new Node(nextRow, nextCol));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }

    if (size > 1) {
      int average = sum / size;
      for (int i = 0; i < size; i++) {
        Node curNode = nodeList[i];
        grid[curNode.row][curNode.col] = average;
      }
      return true;
    }
    return false;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= N - 1 && col <= N - 1;
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
```

이 방식에서 Timeout Event 가 발생한 것은 극단적으로 인구 수가 쏠려 있는 상황에서 불필요한 탐색 과정이 이루어지는 경우 때문입니다.

그래서 첫번째 시물레이션이 끝나고 두번째 시물레이션부턴 변화가 일어난 영역에 관해서만 탐색을 합니다.

여기서 변화가 일어난 영역과 인접한 부분의 차이가 줄어들었거나, 많아 졌을 수 있어서 이렇게 탐색하면 인접한 영역도 탐색해야 하는 것 아니냐라고 생각할 수 있지만,

결국 변화가 일어난 영역을 탐색하면 거기에 해당하는 인접한 영역과의 차이를 검사하기 때문에 문제가 없다.

또한, 변화가 일어나지 않은 영역에 관해서는 주위에 변화가 일어난 영역이 없다면 몇번을 다시 탐색하든 결과가 바뀌지 않을 것이니 이 방법 사용이 가능합니다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 복, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, L, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));

  }
  static int simulation(int[][] grid) {
    int time = 0;

    boolean[][] lookChange = new boolean[N][N];
    while (true) {
      boolean action = false;
      boolean[][] isVisited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!isVisited[i][j]) {
            if (time == 0 && bfs(grid, isVisited, lookChange, i, j)) action = true;
            else if (lookChange[i][j]) {
              if (bfs(grid, isVisited, lookChange, i, j)) action = true;
            }
          }
        }
      }
      if (!action) break;
      time++;
    }

    return time;
  }

  static boolean bfs(int[][] grid, boolean[][] isVisited, boolean[][] lookChange, int row, int col) {
    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;

    Node[] nodeList = new Node[N * N];
    int sum = 0;
    int size = 0;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      nodeList[size++] = curNode;
      sum += grid[curNode.row][curNode.col];

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          int diff = Math.abs(grid[curNode.row][curNode.col] - grid[nextRow][nextCol]);
          if (diff >= L && diff <= R) {
            q.add(new Node(nextRow, nextCol));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }

    if (size > 1) {
      int average = sum / size;
      for (int i = 0; i < size; i++) {
        Node curNode = nodeList[i];
        lookChange[curNode.row][curNode.col] = true;
        grid[curNode.row][curNode.col] = average;
      }
      return true;
    }
    return false;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= N - 1 && col <= N - 1;
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
```