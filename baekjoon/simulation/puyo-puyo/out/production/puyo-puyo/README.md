# Puyo Puyo

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|20706	|8213	|5971|	38.170%|

뿌요뿌요의 룰은 다음과 같다.

필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.

뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.

뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.

아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.

터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

## 입력 

총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.

이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.

R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.

입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.

## 출력 

현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.

## 예제 입력 1

```
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.
```

## 예제 출력 1

```
3
```

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, -1, 0};
  static int[] dc = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[][] grid = new String[12][6];

    Queue<Point> points = new LinkedList<>();
    for (int i = 0; i < 12; i++) {
      String str = br.readLine();
      for (int j = 0; j < 6; j++) {
        grid[i][j] = str.substring(j, j + 1);
        if (!grid[i][j].equals(".")) points.add(new Point(i, j));
      }
    }

    System.out.println(simulation(grid, points));
  }
  static int simulation(String[][] grid, Queue<Point> points) {

    int time = 0;

    while (!points.isEmpty()) {
      Point point = points.poll();

      if (grid[point.row][point.col].equals(".")) continue;

      if (bfs(grid, point, points)) time++;
    }
    return time;
  }
  static boolean bfs(String[][] grid, Point initPoint, Queue<Point> points) {

    boolean[][] isVisited = new boolean[12][6];
    String target = grid[initPoint.row][initPoint.col];
    Queue<Point> q = new LinkedList<>();

    q.add(new Point(initPoint.row, initPoint.col));
    isVisited[initPoint.row][initPoint.col] = true;
    Queue<Point> buffer = new LinkedList<>();
    buffer.add(initPoint);
    int count = 1;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol].equals(target)) {
          Point nextPoint = new Point(nextRow, nextCol);
          isVisited[nextRow][nextCol] = true;
          q.add(nextPoint);
          buffer.add(nextPoint);
          count++;
        }
      }
    }

    if (count < 4) return false;
    else {
      boolean[] isChangeCol = new boolean[6];
      while (!buffer.isEmpty()) {
        Point point = buffer.poll();

        grid[point.row][point.col] = ".";
        isChangeCol[point.col] = true;
      }
      for (int col = 0; col < 6; col++) {
        if (isChangeCol[col]) {
          for (int row = 11; row >= 0; row--) {
            if (grid[row][col].equals(".")) {
              int nextRow = row - 1;
              while (nextRow >= 0 && grid[nextRow][col].equals(".")) nextRow--;
              if (nextRow >= 0) {
                grid[row][col] = grid[nextRow][col];
                grid[nextRow][col] = ".";
                points.add(new Point(row, col));
              }
            }
          }
        }
      }
      return true;
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < 12 && col < 6;
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

터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

이 부분을 고려하지 않았다. 풀이 방식을 수정할 필요가 있다.

한 바퀴 전부 순환해서 터질수 있는 뿌요를 전부 저장해두고 그리고 터치고 내려주고 다시 전부 순화해가면서 확인하는 방식을 채택할 수 밖에 없다.

또한 위의 풀이에서 뿌요가 붙어서 터지는 것이 아니라 행에 대해서 건너 건너 터진다면 예를 들어서, 11, 9, 6 이런 식으로 터진다면 위와 같은 방식으로 풀이하는 것은 잘못된 풀이가 될 가능성이 크다.

왜냐하면 이 풀이는 수정되는 위치를 Queue에 저장해서 그 위치를 꺼내서 풀이하는 방식인데, 저런 식으로 터져 버린다면 원래는 10행에 있는 것도 고려하고 해야하는데 해당 풀이는 안하고 있다. 

