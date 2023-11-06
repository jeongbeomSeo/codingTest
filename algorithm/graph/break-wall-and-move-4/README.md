# 벽 부수고 이동하기 4

**골드 2**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB	|17933|	5051	|3516|	25.270%|

## 문제 

N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 한 칸에서 다른 칸으로 이동하려면, 두 칸이 인접해야 한다. 두 칸이 변을 공유할 때, 인접하다고 한다.

각각의 벽에 대해서 다음을 구해보려고 한다.

- 벽을 부수고 이동할 수 있는 곳으로 변경한다.
- 그 위치에서 이동할 수 있는 칸의 개수를 세어본다.
  
한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다.

## 출력 

맵의 형태로 정답을 출력한다. 원래 빈 칸인 곳은 0을 출력하고, 벽인 곳은 이동할 수 있는 칸의 개수를 10으로 나눈 나머지를 출력한다.

## 예제 입력 1

```
3 3
101
010
101
```

## 예제 출력 1

```
303
050
303
```

## 예제 입력 2

```
4 5
11001
00111
01010
10101
```

## 예제 출력 2

```
46003
00732
06040
50403
```

## 코드 

**TLE**

```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j) - '0';
      }
    }

    int[][] result = new int[N][M];
    boolean[][] isVisited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) result[i][j]++;
        else if (!isVisited[i][j]) {
          bfs(grid, isVisited, result, i, j);
        }
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        bw.write(result[i][j] % 10 + "");
      }
      bw.write("\n");
    }

    bw.flush();
    bw.close();
  }
  static void bfs(int[][] grid, boolean[][] isVisited, int[][] result, int initRow, int initCol) {

    boolean[][] isVisitedBlock = new boolean[N][M];
    Queue<Point> q = new ArrayDeque<>();
    Queue<Point> nearBlockPoints = new ArrayDeque<>();
    Point initPoint = new Point(initRow, initCol);
    q.add(initPoint);
    isVisited[initRow][initCol] = true;
    int count = 1;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] == 0) {
            q.add(new Point(nextRow, nextCol));
            isVisited[nextRow][nextCol] = true;
            count++;
          }
          else if (!isVisitedBlock[nextRow][nextCol]) {
            nearBlockPoints.add(new Point(nextRow, nextCol));
            isVisitedBlock[nextRow][nextCol] = true;
          }
        }
      }
    }

    while (!nearBlockPoints.isEmpty()) {
      Point point = nearBlockPoints.poll();
      result[point.row][point.col] += count;
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < M;
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}
```

**AC**

```java
import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j) - '0';
      }
    }

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    map.put(1, 0);

    int num = 2;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 0) {
          int count = bfs(grid, num, i, j);
          map.put(num, count);
          num++;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] != 1) sb.append("0");
        else {
          HashSet<Integer> set = new HashSet<>();

          for (int d = 0; d < 4; d++) {
            int nextRow = i + dr[d];
            int nextCol = j + dc[d];

            if (isValidIdx(nextRow, nextCol)) set.add(grid[nextRow][nextCol]);
          }
          int sum = 1;
          for (int idx : set) sum += map.get(idx);

         sb.append(sum % 10);
        }
      }
      sb.append("\n");
    }

    System.out.println(sb);
  }
  static int bfs(int[][] grid, int num, int initRow, int initCol) {
    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    grid[initRow][initCol] = num;
    int count = 1;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol] == 0) {
          q.add(new Point(nextRow, nextCol));
          grid[nextRow][nextCol] = num;
          count++;
        }
      }
    }

    return count;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < M;
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
```

**TLE(함수 더 분리한 코드)**

```import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];

    for (int i = 0; i < N; i++) {
      String str = br.readLine();

      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j) - '0';
      }
    }

    int[] costGrid = new int[N * M];
    int[][] groupGrid = paintGroupgrid(grid, costGrid);
    char[][] result = queryResult(grid, groupGrid, costGrid);

    for (int i = 0; i < N; i++) {
      bw.write(String.valueOf(result[i]) + "\n");
    }
    bw.flush();
    bw.close();
  }
  private static char[][] queryResult(int[][] grid, int[][] groupGrid, int[] costGrid) {
    char[][] result = new char[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) {
          int sum = getSumCost(groupGrid, costGrid, i, j);
          result[i][j] = (char)(sum % 10 + '0');
        } else {
          result[i][j] = '0';
        }
      }
    }

    return result;
  }
  private static int getSumCost(int[][] groupGrid, int[] costGird, int row, int col) {

    boolean[] isVisited = new boolean[N * M];
    int sum = 1;
    for (int i = 0; i < 4; i++) {
      int nxtRow = row + dr[i];
      int nxtCol = col + dc[i];

      if (boundaryCheck(nxtRow, nxtCol) ) {
        int groupNum = groupGrid[nxtRow][nxtCol];
        if (!isVisited[groupNum]) {
          sum += costGird[groupNum];
          isVisited[groupNum] = true;
        }
      }
    }

    return sum;
  }
  private static int[][] paintGroupgrid(int[][] grid, int[] costGrid) {
    int[][] groupGrid = new int[N][M];

    int groupNum = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 0 && groupGrid[i][j] == 0) {
          costGrid[groupNum] = bfs(grid, groupGrid, i, j, groupNum);
          groupNum++;
        }
      }
    }

    return groupGrid;
  }
  private static int bfs(int[][] grid, int[][] groupGrid, int row, int col, int groupNum) {

    Queue<Node> q = new ArrayDeque<>();
    boolean[][] isVisitied = new boolean[N][M];
    q.add(new Node(row, col));
    groupGrid[row][col] = groupNum;
    isVisitied[row][col] = true;
    int count = 1;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nxtRow = curNode.row + dr[i];
        int nxtCol = curNode.col + dc[i];

        if (boundaryCheck(nxtRow, nxtCol) && grid[nxtRow][nxtCol] == 0 && !isVisitied[nxtRow][nxtCol]) {
          Node nxtNode = new Node(nxtRow, nxtCol);

          q.add(nxtNode);
          groupGrid[nxtRow][nxtCol] = groupNum;
          isVisitied[nxtRow][nxtCol] = true;
          count++;
        }
      }
    }
    return count;
  }
  static boolean boundaryCheck(int row, int col) {
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