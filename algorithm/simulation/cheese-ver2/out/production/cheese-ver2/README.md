# 치즈 

**골드 3**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|21286	|9898	|7406|	46.023%|

## 문제 

N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다. 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다. 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

![](https://upload.acmicpc.net/a4998beb-104c-4e37-b3d7-fd91cd81464a/-/preview/)
<그림 1>

<그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다. 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

![](https://upload.acmicpc.net/e5d519ee-53ea-40a6-b970-710cca0db128/-/preview/)
<그림 2>

![](https://upload.acmicpc.net/a00b876a-86dc-4a82-a030-603a9b1593cc/-/preview/)
<그림 3>

모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다. 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

## 출력 

출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.

## 예제 입력 1

```
8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
```

## 예제 출력 1

```
4
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
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

    int[][] grid = new int[N][M];
    int cheese_amount = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 1) cheese_amount++;
      }
    }

    System.out.println(simulation(grid, cheese_amount));

  }

  static int simulation(int[][] grid, int cheese_amount) {

    int time = 0;
    while (true) {
      boolean[][] isVisited = new boolean[N][M];
      int[][] finalCount = new int[N][M];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (!isVisited[i][j] && grid[i][j] == 0) {
            int[][] count = bfs(grid, isVisited, i, j);
            if (count != null) {
              for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                  finalCount[row][col] += count[row][col];
                }
              }
            }
          }
        }
      }

      int counter = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (grid[i][j] == 1 && finalCount[i][j] >= 2) {
            grid[i][j] = 0;
            counter++;
          }
        }
      }
      cheese_amount -= counter;
      time++;

      if (cheese_amount == 0) break;
    }
    return time;
  }
  static int[][] bfs(int[][] grid, boolean[][] isVisited, int row, int col) {

    int[][] count = new int[N][M];
    Queue<Node> q = new LinkedList<>();
    Queue<Node> cheese = new LinkedList<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;

    boolean canReachOutside = false;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          if (grid[nextRow][nextCol] == 0){
            if (!isVisited[nextRow][nextCol])
              q.add(new Node(nextRow, nextCol));
          }
          else {
            if (!isVisited[nextRow][nextCol]) cheese.add(new Node(nextRow, nextCol));
            count[nextRow][nextCol]++;
          }
          isVisited[nextRow][nextCol] = true;
        }
        else canReachOutside = true;
      }
    }

    if (!canReachOutside) {
      while (!cheese.isEmpty()) {
        count = null;
        Node curNode = cheese.poll();
        isVisited[curNode.row][curNode.col] = false;
      }
    }

    return count;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < M;
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