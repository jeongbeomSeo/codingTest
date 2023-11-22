# 주사위 윷놀이 

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|12497	|5366|	3333	|39.697%|

## 문제 

주사위 윷놀이는 다음과 같은 게임판에서 하는 게임이다.

![](https://upload.acmicpc.net/43409ac6-54bf-4a21-b542-e01a8211e59f/-/preview/)

- 처음에는 시작 칸에 말 4개가 있다.
- 말은 게임판에 그려진 화살표의 방향대로만 이동할 수 있다. 말이 파란색 칸에서 이동을 시작하면 파란색 화살표를 타야 하고, 이동하는 도중이거나 파란색이 아닌 칸에서 이동을 시작하면 빨간색 화살표를 타야 한다. 말이 도착 칸으로 이동하면 주사위에 나온 수와 관계 없이 이동을 마친다.
- 게임은 10개의 턴으로 이루어진다. 매 턴마다 1부터 5까지 한 면에 하나씩 적혀있는 5면체 주사위를 굴리고, 도착 칸에 있지 않은 말을 하나 골라 주사위에 나온 수만큼 이동시킨다.
- 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
- 말이 이동을 마칠 때마다 칸에 적혀있는 수가 점수에 추가된다.
주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 최댓값을 구해보자.

## 입력 

첫째 줄에 주사위에서 나올 수 10개가 순서대로 주어진다.

## 출력 

얻을 수 있는 점수의 최댓값을 출력한다.

## 예제 입력 1

```
1 2 3 4 1 2 3 4 1 2
```

## 예제 출력 1

```
190
```

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int MAX = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dices = new int[10];
    for (int i = 0; i < 10; i++) {
      dices[i] = Integer.parseInt(st.nextToken());
    }

    int[] map = initMap();
    int[] shortMap = new int[4];
    shortMap[0] = 1; shortMap[1] = 21; shortMap[2] = 30; shortMap[3] = 27;
    boolean[] isUsing = new boolean[33];
    int[] points = initPoint();

    int[] horses = new int[4];

    dfs(horses, map, shortMap, points, dices, 0, 0, isUsing);

    System.out.println(MAX);
  }
  static void dfs(int[] horses, int[] map, int[] shortMap, int[] points, int[] dices, int ptr, int score, boolean[] isUsing) {
    if (ptr == 10) {
      MAX = Math.max(MAX, score);
    }
    else {
      int diceNum = dices[ptr];
      for (int i = 0; i < 4; i++) {
        if (horses[i] == -1) continue;

        int originPoint = horses[i];
        isUsing[horses[i]] = false;
        for (int j = 0; j < diceNum; j++) {
          if (j == 0 && horses[i] % 5 == 0 && horses[i] < 20) {
            horses[i] = shortMap[horses[i] / 5];
          }
          else horses[i] = map[horses[i]];

          if (horses[i] == -1) break;
        }

        if (horses[i] != -1) {
          if (isUsing[horses[i]]) {
            horses[i] = originPoint;
            continue;
          }
          else isUsing[horses[i]] = true;
        }

        if (horses[i] != -1) dfs(horses, map, shortMap, points, dices, ptr + 1, score + points[horses[i]], isUsing);
        else dfs(horses, map, shortMap, points, dices, ptr + 1, score, isUsing);

        if (horses[i] != -1) isUsing[horses[i]] = false;
        horses[i] = originPoint;
        isUsing[horses[i]] = true;
      }
    }
  }
  static int[] initPoint() {
    int[] points = new int[33];

    for (int i = 1; i <= 20; i++) {
      points[i] = 2 * i;
    }

    points[21] = 13; points[22] = 16; points[23] = 19; points[24] = 25;
    points[25] = 30; points[26] = 35; points[27] = 28; points[28] = 27;
    points[29] = 26; points[30] = 22; points[31] = 24;

    return points;
  }
  static int[] initMap() {
    int[] map = new int[33];

    for (int i = 0; i < 20; i++) {
      map[i] = i + 1;
    }

    map[20] = -1; map[21] = 22; map[22] = 23; map[23] = 24; map[24] = 25; map[25] = 26;
    map[26] = 20; map[27] = 28; map[28] = 29; map[29] = 24; map[30] = 31; map[31] = 24;

    return map;
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int MAX = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dices = new int[10];
    for (int i = 0; i < 10; i++) {
      dices[i] = Integer.parseInt(st.nextToken());
    }

    int[] map = initMap();
    int[] shortMap = new int[4];
    shortMap[0] = 1; shortMap[1] = 21; shortMap[2] = 30; shortMap[3] = 27;
    boolean[] isUsing = new boolean[33];
    int[] points = initPoint();

    int[] horses = new int[4];

    dfs(horses, map, shortMap, points, dices, 0, 0, isUsing);

    System.out.println(MAX);
  }
  static void dfs(int[] horses, int[] map, int[] shortMap, int[] points, int[] dices, int ptr, int score, boolean[] isUsing) {
    if (ptr == 10) {
      MAX = Math.max(MAX, score);
    }
    else {
      int diceNum = dices[ptr];
      for (int i = 0; i < 4; i++) {
        if (horses[i] == -1) continue;

        int originPoint = horses[i];
        isUsing[horses[i]] = false;
        for (int j = 0; j < diceNum; j++) {
          if (j == 0 && horses[i] % 5 == 0 && horses[i] < 20) {
            horses[i] = shortMap[horses[i] / 5];
          }
          else horses[i] = map[horses[i]];

          if (horses[i] == -1) break;
        }

        if (horses[i] != -1) {
          if (isUsing[horses[i]]) {
            horses[i] = originPoint;
            isUsing[horses[i]] = true;
            continue;
          }
          else isUsing[horses[i]] = true;
        }

        if (horses[i] != -1) dfs(horses, map, shortMap, points, dices, ptr + 1, score + points[horses[i]], isUsing);
        else dfs(horses, map, shortMap, points, dices, ptr + 1, score, isUsing);

        if (horses[i] != -1) isUsing[horses[i]] = false;
        horses[i] = originPoint;
        isUsing[horses[i]] = true;
      }
    }
  }
  static int[] initPoint() {
    int[] points = new int[33];

    for (int i = 1; i <= 20; i++) {
      points[i] = 2 * i;
    }

    points[21] = 13; points[22] = 16; points[23] = 19; points[24] = 25;
    points[25] = 30; points[26] = 35; points[27] = 28; points[28] = 27;
    points[29] = 26; points[30] = 22; points[31] = 24;

    return points;
  }
  static int[] initMap() {
    int[] map = new int[33];

    for (int i = 0; i < 20; i++) {
      map[i] = i + 1;
    }

    map[20] = -1; map[21] = 22; map[22] = 23; map[23] = 24; map[24] = 25; map[25] = 26;
    map[26] = 20; map[27] = 28; map[28] = 29; map[29] = 24; map[30] = 31; map[31] = 24;

    return map;
  }
}
```

**객체 지향 위주**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int MAX_SCORE = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dice = new int[10];
    for (int i = 0; i < 10; i++) {
      dice[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(dice));
  }
  private static int simulation(int[] dice) {

    Board board = Board.getInstance();
    Horse[] horses = initHorseTable(board.pointList[0]);

    backtracking(dice, 0, horses, board, 0);

    return MAX_SCORE;
  }
  private static Horse[] initHorseTable(Point head) {

    Horse[] horses = new Horse[4];
    for (int i = 0; i < 4; i++) {
      horses[i] = new Horse(head);
    }

    return horses;
  }
  private static void backtracking(int[] dice, int ptr, Horse[] horses, Board board, int score) {

    if (ptr == 10) {
      MAX_SCORE = Math.max(MAX_SCORE, score);
      return;
    }

    for (int i = 0; i < 4; i++) {
      if (!horses[i].isEnd() &&
              (horses[i].getCurrentPosition().idx != 0 || (i == 0 || horses[i - 1].getCurrentPosition().idx != 0))) {

        int backupIdx = horses[i].getCurrentPosition().idx;
        if (moveHorse(board, horses[i], dice[ptr])) {
          backtracking(dice, ptr + 1, horses, board, score + getPoint(horses[i]));
          backup(board, horses[i], backupIdx);
        }
      }
    }

  }
  private static void backup(Board board, Horse horse, int backupIdx) {

    horse.getCurrentPosition().removeHorse(horse);
    horse.backupPoint(board.pointList, backupIdx);
    horse.getCurrentPosition().addHorse(horse);
  }
  private static boolean moveHorse(Board board, Horse horse, int steps) {

    int curIdx = horse.getCurrentPosition().idx;

    int nextIdx = board.getNextPointIdx(curIdx, steps);

    if (board.pointList[nextIdx].hasHorse()) return false;

    board.pointList[curIdx].removeHorse(horse);
    horse.move(steps);
    board.pointList[horse.getCurrentPosition().idx].addHorse(horse);

    return true;
  }
  private static int getPoint(Horse horse) {
    return horse.getCurrentPosition().score;
  }
}
class Horse {
  private Point currentPosition;
  private boolean isEnd;

  Horse (Point head) {
    this.currentPosition = head;
  }
  public void move(int steps) {
    if (currentPosition.hasShortPath) {
      currentPosition = currentPosition.shortPath;
      steps--;
    }

    while(currentPosition.idx != 33 && steps-- != 0) {
      currentPosition = currentPosition.next;
    }

    if (currentPosition.idx == 33) this.isEnd = true;
  }

  public Point getCurrentPosition() {
    return currentPosition;
  }

  public boolean isEnd() {
    return isEnd;
  }
  public void backupPoint(Point[] pointList, int backupIdx) {
    this.currentPosition = pointList[backupIdx];
    isEnd = false;
  }
}
class Board {
  Point[] pointList = new Point[34];

  private Board() {
    pointList[0] = new Point(0, 0);

    for (int i = 1; i <= 20; i++) {
      pointList[i] = new Point(i, 2 * i);
      pointList[i - 1].next = pointList[i];
    }

    pointList[21] = new Point(21, 13);
    pointList[5].shortPath = pointList[21];

    pointList[22] = new Point(22, 16);
    pointList[21].next = pointList[22];

    pointList[23] = new Point(23, 19);
    pointList[22].next = pointList[23];

    // 중앙 Point
    pointList[24] = new Point(24, 25);
    pointList[23].next = pointList[24];

    pointList[25] = new Point(25, 22);
    pointList[10].shortPath = pointList[25];

    pointList[26] = new Point(26, 24);
    pointList[25].next = pointList[26];
    pointList[26].next = pointList[24];

    pointList[27] = new Point(27, 28);
    pointList[15].shortPath = pointList[27];

    pointList[28] = new Point(28, 27);
    pointList[27].next = pointList[28];

    pointList[29] = new Point(29, 26);
    pointList[28].next = pointList[29];
    pointList[29].next = pointList[24];

    pointList[30] = new Point(30, 30);
    pointList[24].next = pointList[30];

    pointList[31] = new Point(31, 35);
    pointList[30].next = pointList[31];
    pointList[31].next = pointList[20];

    pointList[32] = new Point(32, 0);
    pointList[20].next = pointList[32];

    pointList[33] = new Point(33, 0);
    pointList[32].next = pointList[33];
  }

  private static class ClassHolder {
    private static final Board INSTANCE = new Board();
  }

  public int getNextPointIdx(int startIdx, int steps) {

    int curIdx = startIdx;
    if (pointList[curIdx].hasShortPath) {
      curIdx = pointList[curIdx].shortPath.idx;
      steps--;
    }

    while (curIdx != 33 && steps-- != 0) {
      curIdx = pointList[curIdx].next.idx;
    }

    return curIdx;
  }

  public static Board getInstance() {
    return ClassHolder.INSTANCE;
  }
}
class Point {
  int idx;
  int score;
  Point next;
  Point shortPath;
  boolean hasShortPath;
  List<Horse> horsesOnPoint;

  Point (int idx, int score) {
    this.idx = idx;
    this.score = score;
    this.hasShortPath = (idx == 5 || idx == 10 || idx == 15);
    this.horsesOnPoint = new ArrayList<>();
  }

  public boolean hasHorse() {
    return idx != 33 && !horsesOnPoint.isEmpty();
  }

  public void addHorse(Horse horse) {
    horsesOnPoint.add(horse);
  }

  public void removeHorse(Horse horse) {
      horsesOnPoint.remove(horse);
  }
}
```