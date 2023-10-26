# 열쇠 

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB|	17368	|5020|	3396|	26.852%|

## 문제 

상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다. 빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다. 상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다. 상근이는 상하좌우로만 이동할 수 있다.

상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.

각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다. 다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.

- '.'는 빈 공간을 나타낸다.
- '*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
- '$'는 상근이가 훔쳐야하는 문서이다.
- 알파벳 대문자는 문을 나타낸다.
- 알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
- 마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.

상근이는 처음에는 빌딩의 밖에 있으며, 빌딩 가장자리의 벽이 아닌 곳을 통해 빌딩 안팎을 드나들 수 있다. 각각의 문에 대해서, 그 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다. 열쇠는 여러 번 사용할 수 있다.

## 출력 

각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.

## 예제 입력 1

```
3
5 17
*****************
.............**$*
*B*A*P*C**X*Y*.X.
*y*x*a*p**$*$**$*
*****************
cz
5 11
*.*********
*...*...*x*
*X*.*.*.*.*
*$*...*...*
***********
0
7 7
*ABCDE*
X.....F
W.$$$.G
V.$$$.H
U.$$$.J
T.....K
*SQPML*
irony
```

## 예제 출력 1

```
3
1
0
```

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  // 동, 남, 서, 북
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());
    while (tc-- > 0) {
      st = new StringTokenizer(br.readLine());

      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      Map<Character, ArrayList<Point>> doorPoints = new HashMap<>();

      char[][] grid = new char[h][w];
      for (int i = 0; i < h; i++) {
        String str = br.readLine();
        for (int j = 0; j < w; j++) {
          grid[i][j] = str.charAt(j);
          if (isDoor(grid[i][j])) {
            ArrayList<Point> doors = doorPoints.getOrDefault(grid[i][j], new ArrayList<Point>());
            doors.add(new Point(i, j));
            doorPoints.put(grid[i][j], doors);
          }
        }
      }

      boolean[] keyList = new boolean['z' - 'a' + 1];
      String keys = br.readLine();
      for (int i = 0; i < keys.length(); i++) {
        char key = keys.charAt(i);

        if (key == '0') break;
        keyList[getKeyIdx(key)] = true;
      }

      System.out.println(simulation(grid, keyList, doorPoints, h, w));
    }
  }
  static int simulation(char[][] grid, boolean[] keyList, Map<Character, ArrayList<Point>> doorPoints, int h, int w) {

    int result = 0;

    boolean[][] isVisited = new boolean[h][w];
    Queue<Point> q = getStartPoints(grid, keyList, isVisited, h, w);

    result = bfs(q, grid, isVisited, keyList, doorPoints, h, w);

    return result;
  }
  static Queue<Point> getStartPoints(char[][] grid, boolean[] keyList, boolean[][] isVisited, int h, int w) {

    Queue<Point> result = new ArrayDeque<>();

    int row = 0;
    int col = 0;
    int direction = 0;
    do {
      if (canGoInnerSpace(grid, keyList, row, col)) {
        result.add(new Point(row, col));
        isVisited[row][col] = true;
        if (isKey(grid[row][col])) {
          keyList[getKeyIdx(grid[row][col])] = true;
        }
      }
      Integer[] nextPoint = getNextPoint(row, col, direction, h, w);
      row = nextPoint[0];
      col = nextPoint[1];
      direction = nextPoint[2];
    } while (!(row == 0 && col == 0));

    return result;
  }
  static int bfs(Queue<Point> q, char[][] grid, boolean[][] isVisited, boolean[] keyList, Map<Character, ArrayList<Point>> doorPoints, int h, int w) {

    int count = 0;
    while (!q.isEmpty()) {
      Point point = q.poll();

      if (grid[point.row][point.col] == '$') {
        count++;
      }
      else if (isKey(grid[point.row][point.col])) {
        int idx = getKeyIdx(grid[point.row][point.col]);
        keyList[idx] = true;
        char door = getDoor(idx);
        if (doorPoints.containsKey(door)) {
          ArrayList<Point> list = doorPoints.get(door);
          for (int i = 0; i < list.size(); i++) {
            Point doorPoint = list.get(i);
            if (!isVisited[doorPoint.row][doorPoint.col]) {
              q.add(list.get(i));
              isVisited[list.get(i).row][list.get(i).col] = true;
            }
          }
        }
      }

      grid[point.row][point.col] = '.';

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol, h, w) && grid[nextRow][nextCol] != '*' && !isVisited[nextRow][nextCol]) {
          if (isDoor(grid[nextRow][nextCol]) && !keyList[getDoorIdx(grid[nextRow][nextCol])]) continue;
          q.add(new Point(nextRow, nextCol));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    return count;
  }
  static boolean canGoInnerSpace(char[][] grid, boolean[] keyList, int row, int col) {

    if (grid[row][col] == '.') return true;
    else if (isKey(grid[row][col])) return true;
    else if (isDoor(grid[row][col]) && keyList[getDoorIdx(grid[row][col])]) return true;
    else return false;
  }
  static char getDoor (int idx) {
    return (char)(idx + 'A');
  }
  static boolean isDoor(char c) {
    return c >= 'A' && c <= 'Z';
  }
  static boolean isKey(char c) {
    return c >= 'a' && c <= 'z';
  }
  static Integer[] getNextPoint (int row, int col, int direction, int h, int w) {

    int nextRow = row + dr[direction];
    int nextCol = col + dc[direction];

    if (!isValidIdx(nextRow, nextCol, h, w)) {
      direction = getNextDirection(direction);
      nextRow = row + dr[direction];
      nextCol = col + dc[direction];
    }

    return new Integer[]{nextRow, nextCol, direction};
  }
  static int getNextDirection(int direction) {
    return ++direction == 4 ? 0 : direction;
  }
  static boolean isValidIdx(int row, int col, int h, int w) {
    return row >= 0 && col >= 0 && row < h && col < w;
  }
  static int getDoorIdx(char c) {
    return c - 'A';
  }
  static int getKeyIdx(char c) {
    return c - 'a';
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

이와 같은 식으로 풀면 문이 겹쳐 있을 경우에 문제가 발생합니다. 

예를 들어 A문에 접근하기 위해서 B 문을 먼저 열어야 되는데 A 열쇠를 얻고 Map에 저장된 Point로 바로 이동해 버린다면 

문제가 발생하게 되는 것입니다. 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      st = new StringTokenizer(br.readLine());

      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      char[][] grid = new char[h][w];

      ArrayList<Point> startPoint = new ArrayList<>();

      for (int i = 0; i < h; i++) {
        String str = br.readLine();
        for (int j = 0; j < w; j++) {
          grid[i][j] = str.charAt(j);

          if (isBoundary(i, j, h, w)) {
            if (grid[i][j] != '*') startPoint.add(new Point(i, j));
          }
        }
      }

      boolean[] hasKey = new boolean['z' - 'a' + 1];

      String keys = br.readLine();
      if (keys.charAt(0) != '0') {
        for (int i = 0; i < keys.length(); i++) {
          int keyIdx = getKeyIdx(keys.charAt(i));
          hasKey[keyIdx] = true;
        }
      }
      System.out.println(simulation(grid, hasKey, startPoint, h, w));
    }
  }
  static int simulation (char[][] grid, boolean[] hasKey, ArrayList<Point> startPoint, int h, int w) {
    int result = 0;

    boolean getKeyThisTurn;
    do {
      getKeyThisTurn = false;
      boolean[][] isVisited = new boolean[h][w];
      for (int i = 0; i < startPoint.size(); i++) {
        Point point = startPoint.get(i);

        if (isDoor(grid[point.row][point.col])) {
          if (hasKey[getDoorIdx(grid[point.row][point.col])]) grid[point.row][point.col] = '.';
          else continue;
        }

        if (isKey(grid[point.row][point.col])) {
          int keyIdx = getKeyIdx(grid[point.row][point.col]);
          hasKey[keyIdx] = true;
          getKeyThisTurn = true;
          grid[point.row][point.col] = '.';
        }

        if (!isVisited[point.row][point.col]) {
          int[] bfsResult = bfs(grid, hasKey, isVisited, point.row, point.col, h, w);

          if (bfsResult[1] == 1) getKeyThisTurn = true;
          result += bfsResult[0];
        }
      }

    } while (getKeyThisTurn);

    return result;
  }
  static int[] bfs (char[][] grid, boolean[] hasKey, boolean[][] isVisited, int initRow, int initCol, int h, int w) {

    int[] result = new int[2];

    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    isVisited[initRow][initCol] = true;

    while (!q.isEmpty()) {
      Point point = q.poll();

      if (grid[point.row][point.col] == '$') {
        result[0]++;
      }
      else if (isKey(grid[point.row][point.col])) {
        int keyIdx = getKeyIdx(grid[point.row][point.col]);
        if (!hasKey[keyIdx]) result[1] = 1;
        hasKey[keyIdx] = true;
      }

      grid[point.row][point.col] = '.';

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol, h, w)
                && !isVisited[nextRow][nextCol]
                && grid[nextRow][nextCol] != '*') {
          if ((isDoor(grid[nextRow][nextCol]) && !hasKey[getDoorIdx(grid[nextRow][nextCol])])) continue;

          q.add(new Point(nextRow, nextCol));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    return result;
  }
  static boolean isValidIdx(int row, int col, int h, int w) {
    return row >= 0 && col >= 0 && row < h && col < w;
  }
  static boolean isBoundary (int row, int col, int h, int w) {
    return row == 0 || col == 0 || row == h - 1 || col == w - 1;
  }
  static boolean isKey(char c) {
    return c >= 'a' && c <= 'z';
  }
  static boolean isDoor(char c) {
    return c >= 'A' && c <= 'Z';
  }
  static int getKeyIdx(char c) {
    return c - 'a';
  }
  static int getDoorIdx(char c) {
    return c - 'A';
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