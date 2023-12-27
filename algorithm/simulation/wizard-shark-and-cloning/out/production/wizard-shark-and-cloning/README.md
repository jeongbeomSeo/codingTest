


# 마법사 상어와 복제

**골드 1**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|1024 MB|	5217	|2753|	1543	|50.891%|

## 문제 

마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그, 비바라기, 블리자드 마법을 할 수 있다. 오늘은 기존에 배운 물복사버그 마법의 상위 마법인 복제를 배웠고, 4 × 4 크기의 격자에서 연습하려고 한다. (r, c)는 격자의 r행 c열을 의미한다. 격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (4, 4)이다.

격자에는 물고기 M마리가 있다. 각 물고기는 격자의 칸 하나에 들어가 있으며, 이동 방향을 가지고 있다. 이동 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다. 마법사 상어도 연습을 위해 격자에 들어가있다. 상어도 격자의 한 칸에 들어가있다. 둘 이상의 물고기가 같은 칸에 있을 수도 있으며, 마법사 상어와 물고기가 같은 칸에 있을 수도 있다.

상어의 마법 연습 한 번은 다음과 같은 작업이 순차적으로 이루어진다.

1. 상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 조금 걸리기 때문에, 아래 5번에서 물고기가 복제되어 칸에 나타난다.
2. 모든 물고기가 한 칸 이동한다. 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다. 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기의 냄새는 아래 3에서 설명한다.
3. 상어가 연속해서 3칸 이동한다. 상어는 현재 칸에서 상하좌우로 인접한 칸으로 이동할 수 있다. 연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다. 연속해서 이동하는 중에 상어가 물고기가 있는 같은 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외되며, 제외되는 모든 물고기는 물고기 냄새를 남긴다. 가능한 이동 방법 중에서 제외되는 물고기의 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용한다. 사전 순에 대한 문제의 하단 노트에 있다.
4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
5. 1에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.

격자에 있는 물고기의 위치, 방향 정보와 상어의 위치, 그리고 연습 횟수 S가 주어진다. S번 연습을 모두 마쳤을때, 격자에 있는 물고기의 수를 구해보자.

## 입력 

첫째 줄에 물고기의 수 M, 상어가 마법을 연습한 횟수 S가 주어진다. 둘째 줄부터 M개의 줄에는 물고기의 정보 f<sub>x</sub>, f<sub>y</sub>, d가 주어진다. (f<sub>x</sub>, f<sub>y</sub>)는 물고기의 위치를 의미하고, d는 방향을 의미한다. 방향은 8 이하의 자연수로 표현하고, 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 마지막 줄에는 s<sub>x</sub>, s<sub>y</sub>가 주어지며, 상어가 (s<sub>x</sub>, s<sub>y</sub>)에 있음을 의미한다.

격자 위에 있는 물고기의 수가 항상 1,000,000 이하인 입력만 주어진다.

## 출력 

S번의 연습을 마친 후 격자에 있는 물고기의 수를 출력한다.

## 제한 

- 1 ≤ M ≤ 10
- 1 ≤ S ≤ 100
- 1 ≤ f<sub>x</sub>, f<sub>y</sub>, s<sub>x</sub>, s<sub>y</sub> ≤ 4
- 1 ≤ d ≤ 8
- 두 물고기 또는 물고기와 상어가 같은 칸에 있을 수도 있다.

## 예제 입력 1

```
5 1
4 3 5
1 3 5
2 4 2
2 1 6
3 4 4
4 2
```

## 예제 출력 1

```
9
```

격자의 초기 상태는 다음 그림과 같다. 상어가 있는 칸은 배경색이 어두운 칸, 물고기는 방향으로 표시했다.

![](https://upload.acmicpc.net/3640352e-606b-48ef-843a-933723446286/-/preview/)

물고기가 한 칸 이동하면 다음과 같다.

![](https://upload.acmicpc.net/93f7191b-5394-41f3-b3f6-c3b9758e81a0/-/preview/)

상어는 [상, 상, 상]으로 이동한다. 이때 (3, 2)에 있는 물고기가 격자에서 제외된다. 물고기의 냄새가 있는 칸은 배경의 색이 빨간색이다.

![](https://upload.acmicpc.net/a81eccf3-6e9b-4b9f-a135-642a949dba73/-/preview/)

이제 복제 마법이 완료된다.

![](https://upload.acmicpc.net/fecb8fb1-a50d-47f2-9958-a0036523441f/-/preview/)

## 예제 입력 2

```
5 2
4 3 5
1 3 5
2 4 2
2 1 6
3 4 4
4 2
```

## 예제 출력 2

```
13
```

예제 1의 상태에서 연습을 한 번 더 해야 한다. 물고기가 한 칸 이동하면 다음 그림과 같다.

![](https://upload.acmicpc.net/e220aeab-f7bc-4485-8471-7e3aadb1d8bc/-/preview/)

상어는 [우, 우, 하]로 이동한다. (2, 4)에도 상어의 냄새가 있으나 상어의 위치와 겹쳐 그림에는 표시하지 않았다.

![](https://upload.acmicpc.net/2b32efab-4f3a-406a-bb74-1c0feb8d3168/-/preview/)

아직 격자에서 사라질 냄새는 없다. 복제 마법이 완료되면 다음과 같다.

![](https://upload.acmicpc.net/cf928ea6-08c4-4498-83ed-4b7e59beb38a/-/preview/)

## 예제 입력 3

```
5 3
4 3 5
1 3 5
2 4 2
2 1 6
3 4 4
4 2
```

## 예제 출력 3

```
17
```

예제 2의 상태에서 연습을 한 번 더 해야 한다. 물고기가 한 칸 이동하면 다음과 같다.

![](https://upload.acmicpc.net/a54b04d3-9e0f-4bcc-b50a-67137281fe35/-/preview/)

상어는 [좌, 좌, 상]으로 이동한다. 여기서 9마리의 물고기가 격자에서 제외된다. 첫 번째 연습에서 생긴 냄새가 격자에서 사라진다. 상어가 있는 칸에는 어두운 배경 대신 상어를 표시했다.

![](https://upload.acmicpc.net/b26b6bb0-3c5f-4651-bab8-fb52aed7278f/-/preview/)

복제 마법이 완료되면 격자의 상태는 아래 그림과 같아진다.

![](https://upload.acmicpc.net/97396767-043a-40ae-84b9-cda3742b23b8/-/preview/)

## 노트 

상어의 이동 방법 중 사전 순으로 가장 앞서는 방법을 찾으려면 먼저, 방향을 정수로 변환해야 한다. 상은 1, 좌는 2, 하는 3, 우는 4로 변환한다. 변환을 모두 마쳤으면, 수를 이어 붙여 정수로 하나 만든다. 두 방법 A와 B가 있고, 각각을 정수로 변환한 값을 a와 b라고 하자. a < b를 만족하면 A가 B보다 사전 순으로 앞선 것이다.

예를 들어, [상, 하, 좌]를 정수로 변환하면 132가 되고, [하, 우, 하]를 변환하면 343이 된다. 132 < 343이기 때문에, [상, 하, 좌]가 [하, 우, 하]보다 사전 순으로 앞선다.

총 43 = 64가지 방법을 사전 순으로 나열해보면 [상, 상, 상], [상, 상, 좌], [상, 상, 하], [상, 상, 우], [상, 좌, 상], [상, 좌, 좌], [상, 좌, 하], [상, 좌, 우], [상, 하, 상], ..., [우, 하, 하], [우, 하, 우], [우, 우, 상], [우, 우, 좌], [우, 우, 하], [우, 우, 우] 이다.

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
  static int M, S;
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    Deque<Fish> fishList = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishList.addLast(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);

    System.out.println(simulation(fishList, shark));
  }
  static int simulation(Deque<Fish> fishList, Shark shark) {

    Deque<Smell> smellList = new ArrayDeque<>();
    boolean[][] isSmellGrid = new boolean[5][5];
    while (S-- > 0) {

      Deque<Fish>[][] fishGrid = new Deque[5][5];
      Deque<Fish> copyedFish = fishCopy(fishList, fishGrid);

      fishGrid = activeMoveFish(fishGrid, isSmellGrid, shark);

      activeMoveShark(fishGrid, smellList, shark);

      smellList = activeSmell(smellList, isSmellGrid);

      fishList = integrateFish(copyedFish, fishGrid);
    }

    return countAllFish(fishList);
  }
  static int countAllFish(Deque<Fish> fishList) {
    return fishList.size();
  }
  static Deque<Fish> integrateFish(Deque<Fish> copyedFish, Deque<Fish>[][] fishGrid) {

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) copyedFish.add(fishGrid[i][j].poll());
        }
      }
    }

    return copyedFish;
  }
  static Deque<Smell> activeSmell(Deque<Smell> smellList, boolean[][] isSmellGrid) {

    Deque<Smell> newSmellList = new ArrayDeque<>();
    boolean[][] isSmell = new boolean[5][5];
    while (!smellList.isEmpty()) {
      Smell smell = smellList.pollLast();

      if(--smell.time != 0 && !isSmell[smell.row][smell.col]) {
        isSmellGrid[smell.row][smell.col] = true;
        newSmellList.addFirst(smell);
        isSmell[smell.row][smell.col] = true;
      }
      else if (!isSmell[smell.row][smell.col]) isSmellGrid[smell.row][smell.col] = false;
    }
    return newSmellList;
  }
  static void activeMoveShark(Deque<Fish>[][] fishGrid, Deque<Smell> smellList, Shark shark) {

    Queue<Shark> q = new ArrayDeque<>();
    int[] move_dr = {0, -1, 0, 1, 0};
    int[] move_dc = {0, 0, -1, 0, 1};
    q.add(shark);

    Shark maxShark = null;
    while (!q.isEmpty()) {
      Shark curShark = q.poll();

      if (curShark.time == 3) {
        if (maxShark == null) maxShark = curShark;
        else {
          if (maxShark.count < curShark.count) maxShark = curShark;
          else if (maxShark.count == curShark.count) {
            for (int c = 0; c < 3; c++) {
              if (maxShark.directionList[c] == curShark.directionList[c]) continue;
              else if (maxShark.directionList[c] > curShark.directionList[c]) {maxShark = curShark; break;}
              else break;
            }
          }
        }
        continue;
      }

      for (int i = 1; i <= 4; i++) {
        int nextRow = curShark.row + move_dr[i];
        int nextCol = curShark.col + move_dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          int count = 0;
          if (!curShark.isVisited[nextRow][nextCol] && fishGrid[nextRow][nextCol] != null) count = fishGrid[nextRow][nextCol].size();
          q.add(new Shark(nextRow, nextCol, curShark.count + count, curShark.time + 1, curShark.directionList, curShark.isVisited, i, curShark.pointList));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      Point point = maxShark.pointList[i];
      if (fishGrid[point.row][point.col] != null) {
        if (!fishGrid[point.row][point.col].isEmpty()) smellList.addLast(new Smell(point.row, point.col));
        while (!fishGrid[point.row][point.col].isEmpty()) fishGrid[point.row][point.col].poll();
      }
    }

    shark.row = maxShark.row;
    shark.col = maxShark.col;
  }
  static Deque<Fish>[][] activeMoveFish(Deque<Fish>[][] fishGrid, boolean[][] isSmellGrid, Shark shark) {


    Deque<Fish>[][] nextFishGrid = new ArrayDeque[5][5];

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        // 물고기가 있는 칸인 경우
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) {
            Fish fish = fishGrid[i][j].poll();

            int originDirection = fish.direction;

            do {
              int nextRow = fish.row + dr[fish.direction];
              int nextCol = fish.col + dc[fish.direction];

              if (isValidIdx(nextRow, nextCol) && !(nextRow == shark.row && nextCol == shark.col) && !isSmellGrid[nextRow][nextCol]) {
                fish.row = nextRow;
                fish.col = nextCol;
                break;
              }

              fish.direction = getNextDirection(fish.direction);
            } while (fish.direction != originDirection);

            if (nextFishGrid[fish.row][fish.col] == null) nextFishGrid[fish.row][fish.col] = new ArrayDeque<>();
            nextFishGrid[fish.row][fish.col].add(fish);
          }
        }
      }
    }

    return nextFishGrid;
  }
  static Deque<Fish> fishCopy (Deque<Fish> fishList, Deque<Fish>[][] fishGrid) {

    Deque<Fish> copyedFish = new ArrayDeque<>();
    while (!fishList.isEmpty()) {
      Fish fish = fishList.poll();

      copyedFish.addLast(new Fish(fish.row, fish.col, fish.direction));

      if (fishGrid[fish.row][fish.col] == null) fishGrid[fish.row][fish.col] = new ArrayDeque<>();
      fishGrid[fish.row][fish.col].add(fish);
    }
    return copyedFish;
  }
  static int getNextDirection(int direction) {
    return direction - 1 != 0 ? direction - 1 : 8;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
}
class Smell {
  int row;
  int col;
  int time;

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 3;
  }
}
class Shark {
  int row;
  int col;
  int count;
  int time;
  int[] directionList;
  boolean[][] isVisited;
  Point[] pointList;

  Shark (int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 0;
    this.time = 0;
    this.directionList = new int[3];
    isVisited = new boolean[5][5];
    isVisited[row][col] = false;
    pointList = new Point[3];
  }

  Shark (int row, int col, int count, int time, int[] directionList, boolean[][] isVisited, int direction, Point[] pointList) {
    this.row = row;
    this.col = col;
    this.count = count;
    this.time = time;
    this.directionList = Arrays.copyOf(directionList, 3);
    this.directionList[time - 1] = direction;
    this.isVisited = copyIsVisited(isVisited);
    this.isVisited[row][col] = true;
    this.pointList = copyPointList(pointList);
    this.pointList[time - 1] = new Point(row, col);
  }

  Point[] copyPointList(Point[] pointList) {
    Point[] newPointList = new Point[3];

    for (int i = 0; i < 3; i++) {
      if (pointList[i] != null) {
        newPointList[i] = new Point(pointList[i].row, pointList[i].col);
      }
      else newPointList[i] = null;
    }
    return newPointList;
  }

  boolean[][] copyIsVisited(boolean[][] isVisited) {
    boolean[][] newCopyIsVisited = new boolean[5][5];
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        newCopyIsVisited[i][j] = isVisited[i][j];
      }
    }
    return newCopyIsVisited;
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
class Fish {
  int row;
  int col;
  int direction;

  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
```

**AC(Better Solution)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int M, S;
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    Queue<Fish> fishList = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishList.add(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);


    System.out.println(simulation(fishList, shark));
  }
  static int simulation(Queue<Fish> fishList, Shark shark) {

    Deque<Smell> smells = new ArrayDeque<>();
    boolean[][] isSmellGrid = new boolean[5][5];

    while (S-- > 0) {
      Queue<Fish>[][] fishGrid = new ArrayDeque[5][5];

      // 1단계
      Queue<Fish> copyedFish = copyFish(fishList, fishGrid);

      // 2단계
      fishGrid = activeFishMove(fishGrid, isSmellGrid, shark);

      // 3단계
      activeSharkMove(fishGrid, smells, shark);

      // 4단계
      smells = activeDiffusionSmell(smells, isSmellGrid);

      fishList = integrateFish(fishGrid, copyedFish);
    }

    return fishList.size();
  }
  static Queue<Fish> integrateFish(Queue<Fish>[][] fishGrid, Queue<Fish> copyedFish) {

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) copyedFish.add(fishGrid[i][j].poll());
        }
      }
    }

    return copyedFish;
  }
  static Deque<Smell> activeDiffusionSmell(Deque<Smell> smells, boolean[][] isSmellGrid) {

    boolean[][] isVisited = new boolean[5][5];
    Deque<Smell> newSmells = new ArrayDeque<>();
    while (!smells.isEmpty()) {
      Smell smell = smells.pollLast();

      if (!isVisited[smell.row][smell.col]) {
        if (--smell.time != 0) {
          isSmellGrid[smell.row][smell.col] = true;
          isVisited[smell.row][smell.col] = true;
          newSmells.addFirst(smell);
        }
        else isSmellGrid[smell.row][smell.col] = false;
      }
    }

    return newSmells;
  }
  static void activeSharkMove (Queue<Fish>[][] fishGrid, Deque<Smell> smells, Shark shark) {

    Queue<MovingShark> q = new ArrayDeque<>();
    q.add(new MovingShark(shark.row, shark.col));

    int[] move_dr = {-1, 0, 1, 0};
    int[] move_dc = {0, -1, 0, 1};

    MovingShark lastShark = null;

    while (!q.isEmpty()) {
      MovingShark movingShark = q.poll();

      if (movingShark.time == 3) {
        if (lastShark == null) lastShark = movingShark;
        else {
          if (movingShark.count > lastShark.count) lastShark = movingShark;
          else if (movingShark.count == lastShark.count) {
            if (lastShark.compareDirection(movingShark)) lastShark = movingShark;
          }
        }
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = movingShark.row + move_dr[i];
        int nextCol = movingShark.col + move_dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          int count = 0;
          if (!movingShark.isVisited(nextRow, nextCol) && fishGrid[nextRow][nextCol] != null)
            count = fishGrid[nextRow][nextCol].size();

          q.add(new MovingShark(nextRow, nextCol, movingShark.time + 1, movingShark.count + count, i, movingShark.direction, movingShark.points));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      int[] point = lastShark.points[i];

      if (fishGrid[point[0]][point[1]] != null) {
        while (!fishGrid[point[0]][point[1]].isEmpty()) fishGrid[point[0]][point[1]].poll();
        smells.addLast(new Smell(point[0], point[1]));
      }
    }

    shark.row = lastShark.row;
    shark.col = lastShark.col;
  }
  static Queue<Fish>[][] activeFishMove (Queue<Fish>[][] fishGrid, boolean[][] isSmellGrid, Shark shark) {

    Queue<Fish>[][] copy = new ArrayDeque[5][5];
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) {
            Fish fish = fishGrid[i][j].poll();

            int originDirection = fish.direction;

            do {
              int nextRow = fish.row + dr[fish.direction];
              int nextCol = fish.col + dc[fish.direction];

              if (isValidIdx(nextRow, nextCol)
                      && !(shark.row == nextRow && shark.col == nextCol)
                      && !isSmellGrid[nextRow][nextCol]) {
                fish.row = nextRow;
                fish.col = nextCol;
                break;
              }

              fish.direction = getNextDirection(fish.direction);
            } while (originDirection != fish.direction);

            if (copy[fish.row][fish.col] == null) copy[fish.row][fish.col] = new ArrayDeque<>();
            copy[fish.row][fish.col].add(fish);
          }
        }
      }
    }
    return copy;
  }
  static Queue<Fish> copyFish (Queue<Fish> fishList, Queue<Fish>[][] fishGrid) {

    Queue<Fish> copy = new ArrayDeque<>();
    while (!fishList.isEmpty()) {
      Fish fish = fishList.poll();

      copy.add(new Fish(fish.row, fish.col, fish.direction));

      if (fishGrid[fish.row][fish.col] == null) fishGrid[fish.row][fish.col] = new ArrayDeque<>();
      fishGrid[fish.row][fish.col].add(fish);
    }

    return copy;
  }
  static int getNextDirection(int direction) {
    return direction - 1 != 0 ? direction - 1 : 8;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
}
class Smell {
  int row;
  int col;
  int time;

  Smell (int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 3;
  }
}

class Shark {
  int row;
  int col;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
  }
}

class MovingShark {
  int row;
  int col;
  int time;
  int count;
  int[] direction;
  int[][] points;

  MovingShark(int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 0;
    this.count = 0;
    this.direction = new int[3];
    this.points = new int[3][2];
  }

  MovingShark(int row, int col, int time, int count, int curDirection, int[] direction, int[][] points) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.count = count;
    this.direction = updateDirection(direction, curDirection, time);
    this.points = updatePoints(points, row, col, time);
  }

  boolean isVisited(int row, int col) {

    for (int i = 0; i < 3; i++) {
      if (this.points[i][0] == row && this.points[i][1] == col) return true;
    }
    return false;
  }

  int[][] updatePoints(int[][] points, int row, int col, int time) {
    int[][] copy = new int[3][2];
    for (int i = 0; i < 3; i++) {
      copy[i][0] = points[i][0];
      copy[i][1] = points[i][1];
    }
    copy[time - 1][0] = row;
    copy[time - 1][1] = col;
    return copy;
  }

  int[] updateDirection(int[] direction, int curDirection, int time) {
    int[] copy = new int[3];

    for (int i = 0; i < 3; i++) copy[i] = direction[i];

    copy[time - 1] = curDirection;
    return copy;
  }

  boolean compareDirection(MovingShark o) {
    for (int i = 0; i < 3; i++) {
      if (this.direction[i] > o.direction[i]) return true;
      else if (this.direction[i] < o.direction[i]) return false;
    }
    return false;
  }
}

class Fish {
  int row;
  int col;
  int direction;

  Fish (int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
```

**객체 지향적 풀이에 신경쓴 나머지 최적화가 되지 않은 풀이**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  private static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  private static final int[] shark_dr = {0, -1, 0, 1, 0};
  private static final int[] shark_dc = {0, 0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    List<Fish> fishList = new LinkedList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishList.add(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);

    System.out.println(simulation(shark, fishList, S));
  }
  private static int simulation(Shark shark, List<Fish> fishList, int S) {

    List<Smell> smellList = new ArrayList<>();
    boolean[][] smellGrid = new boolean[5][5];
    while (S != 0) {

      List<Fish> copyFishList = getCopyFishList(fishList);

      List<Fish>[][] fishGrid = moveFishes(fishList, smellGrid, shark);

      List<Point> pointList = moveShark(fishGrid, shark);

      smellList = countDown(smellList);

      delete(fishList, fishGrid, pointList, smellList);

      smellGrid = getNewSmellGrid(smellList);

      //System.out.println(fishList.size());
      fishList.addAll(copyFishList);
      //System.out.println(fishList.size());
      //System.out.println();
      S--;
    }

    return fishList.size();
  }
  private static boolean[][] getNewSmellGrid(List<Smell> smellList) {

    boolean[][] newGrid = new boolean[5][5];

    for (Smell smell : smellList) {
        newGrid[smell.row][smell.col] = true;
    }

    return newGrid;
  }
  private static List<Smell> countDown(List<Smell> smellList) {

    List<Smell> list = new ArrayList<>();

    boolean[][] isSmellGrid = new boolean[5][5];
    for (int i = smellList.size() - 1; i >= 0; i--) {
      Smell smell = smellList.get(i);

      if (isSmellGrid[smell.row][smell.col] || --smell.count == 0) continue;

      list.addFirst(smell);
      isSmellGrid[smell.row][smell.col] = true;
    }

    return list;
  }
  private static void delete(List<Fish> fishList, List<Fish>[][] fishGrid, List<Point> pointList, List<Smell> smellList) {

    for (Point point : pointList) {

      //System.out.println("row: " + point.row + " col: " + point.col);
      int i = 0;
      for (; i < fishGrid[point.row][point.col].size(); i++) {
        Fish fish = fishGrid[point.row][point.col].get(i);

        fishList.remove(fish);
      }
      if (i != 0) {
        smellList.add(new Smell(point.row, point.col));
      }
    }
  }
  private static List<Point> moveShark(List<Fish>[][] fishGrid, Shark shark) {

    Queue<MovingShark> buffer = new ArrayDeque<>();
    MovingShark result = null;

    buffer.add(new MovingShark(shark.row, shark.col));
    while (!buffer.isEmpty()) {
      MovingShark curShark = buffer.poll();

      if (curShark.count == 3) {
        if (result == null) {
          result = curShark;
        } else {
          if (result.compareTo(curShark) > 0) result = curShark;
        }

        continue;
      }

      for (int i = 1; i <= 4; i++) {
        int nextRow = curShark.row + shark_dr[i];
        int nextCol = curShark.col + shark_dc[i];

        if (isValidPoint(nextRow, nextCol)) {
          Point nextPoint = new Point(nextRow, nextCol);
          if (curShark.count == 2 && isVisited(curShark.pointHistory, nextPoint)) {
            buffer.add(
                    new MovingShark(nextRow, nextCol, curShark.count + 1,
                            curShark.eatingPoint, curShark.directionHistory, curShark.pointHistory, i));
          } else {
            buffer.add(
                    new MovingShark(nextRow, nextCol, curShark.count + 1,
                            curShark.eatingPoint + fishGrid[nextRow][nextCol].size(), curShark.directionHistory, curShark.pointHistory, i));
          }
        }
      }
    }

    shark.row = result.row;
    shark.col = result.col;

    return result.pointHistory;
  }
  private static List<Fish>[][] moveFishes(List<Fish> fishList, boolean[][] smellGrid, Shark shark) {

    //System.out.println("Fish Move Result Print");
    List<Fish>[][] fishGrid = initFishGrid();
    for (Fish fish : fishList) {

      Integer[] nextPoint = getNextPoint(fish, smellGrid, shark);

      fish.row = nextPoint[0];
      fish.col = nextPoint[1];
      fish.direction = nextPoint[2];

      //System.out.println("row: " + fish.row + " col: " + fish.col);
      fishGrid[fish.row][fish.col].add(fish);
    }
    //System.out.println("===========End============");

    return fishGrid;
  }
  private static List<Fish>[][] initFishGrid() {

    List<Fish>[][] grid = new List[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        grid[i][j] = new ArrayList<>();
      }
    }

    return grid;
  }
  private static Integer[] getNextPoint(Fish fish, boolean[][] smellGrid, Shark shark) {

    int direction = fish.direction;
    do {
      int nextRow = fish.row + dr[direction];
      int nextCol = fish.col + dc[direction];

      if (canMovePoint(nextRow, nextCol, smellGrid, shark)) {
        return new Integer[]{nextRow, nextCol, direction};
      }

      direction = getNextDirection(direction);
    } while (!(direction == fish.direction));

    return new Integer[]{fish.row, fish.col, fish.direction};
  }
  private static int getNextDirection(int direction) {
    return direction == 1 ? 8 : direction - 1;
  }
  private static boolean canMovePoint(int nextRow, int nextCol, boolean[][] smellGrid, Shark shark) {
    return isValidPoint(nextRow, nextCol) && !smellGrid[nextRow][nextCol] && !(shark.row == nextRow && shark.col == nextCol);
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
  private static List<Fish> getCopyFishList(List<Fish> fishList) {

    List<Fish> copy = new ArrayList<>();

      for (Fish fish : fishList) {
          copy.add(new Fish(fish.row, fish.col, fish.direction));
      }

    return copy;
  }
  private static boolean isVisited(List<Point> pointList, Point nextPoint) {

    for (Point point : pointList) {
      if (nextPoint.equals(point)) return true;
    }

    return false;
  }
}
class Smell {
  int row;
  int col;
  int count;

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 2;
  }
  Smell(int row, int col, int count) {
    this.row = row;
    this.col = col;
    this.count = count;
  }
}
class Fish {
  int row;
  int col;
  int direction;

  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
class Shark {
  int row;
  int col;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class MovingShark implements Comparable<MovingShark> {
  int row;
  int col;
  int count;
  int eatingPoint;
  List<Integer> directionHistory;
  List<Point> pointHistory;

  MovingShark(int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 0;
    this.eatingPoint = 0;
    this.directionHistory = new ArrayList<>();
    this.pointHistory = new ArrayList<>();
    pointHistory.add(new Point(row, col));
  }

  MovingShark(int row, int col, int count, int eatingPoint, List<Integer> directionHistory, List<Point> pointHistory, int direction) {
    this.row = row;
    this.col = col;
    this.count = count;
    this.eatingPoint = eatingPoint;
    this.directionHistory = initDirectionHistory(directionHistory, direction);
    this.pointHistory = initPointHistory(pointHistory, row, col);
  }
  private List<Point> initPointHistory(List<Point> pointHistory, int row, int col) {

    List<Point> list = new ArrayList<>(pointHistory);
    list.add(new Point(row, col));

    return list;
  }

  private List<Integer> initDirectionHistory(List<Integer> directionHistory, int direction) {

    List<Integer> list = new ArrayList<>(directionHistory);
    list.add(direction);

    return list;
  }

  @Override
  public int compareTo(MovingShark o) {
    if (this.eatingPoint != o.eatingPoint) return o.eatingPoint - this.eatingPoint;
    else {
      for (int i = 0; i < 3; i++) {
        int directionIdx = this.directionHistory.get(i);
        int compareIdx = o.directionHistory.get(i);
        if (directionIdx != compareIdx) return directionIdx - compareIdx;
      }
    }
    return 0;
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point p = (Point) obj;

      return this.row == p.row && this.col == p.col;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }
}
```

아래의 풀이는 위의 풀이를 좀 더 깔끔하게 풀어본 것인데, 결론적으로 말하자면 이것 또한 테스트 케이스에서 굉장히 많은 연산을 요구하는 케이스가 Time Limted Error가 발생한다고 보면된다.

이전 풀이에서는 삭제를 하는 작업이 없었으며, Fish들을 Map으로 분산시켜서 문제 해결을 했었다.

사실, Fish의 List들을 LinkedList로 관리를 하면 크게 상관 없을 것이라고 생각을 했지만 실제로는 그렇지 않은 것 같다.

remove랑 get 이 두 함수를 주로 사용하는데 이 두 함수 모두 순차 탐색을 통해서 노드의 위치를 찾기 때문에 결국은 모든 Fish들을 하나의 List로 관리할 경우 오래 걸립니다.

**LinkedList의 착각 풀이**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] shark_dr = {0, -1, 0, 1, 0};
    private static final int[] shark_dc = {0, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Fish> fishList = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishList.add(new Fish(row, col, direction));
        }

        st = new StringTokenizer(br.readLine());
        int sharkRow = Integer.parseInt(st.nextToken());
        int sharkCol = Integer.parseInt(st.nextToken());
        Point shark = new Point(sharkRow, sharkCol);

        System.out.println(simulation(fishList, shark, S));
    }
    private static int simulation(List<Fish> fishList, Point shark, int S) {

        int time = S;
        boolean[][] smellGrid = new boolean[5][5];
        Deque<Smell> smellDeque = new ArrayDeque<>();
        while (time != 0) {

            Queue<Fish> copyFishBuffer = getCopyFishBuffer(fishList);

            List<Integer>[][] fishIdxGrid = moveFishes(fishList, smellGrid, shark);

            List<Point> pointHistory = moveShark(fishIdxGrid, shark);

            eatingFish(fishIdxGrid, fishList, pointHistory, smellDeque);

            ResultProcessSmell resultProcessSmell = processSmell(smellDeque);
            smellGrid = resultProcessSmell.smellGrid;;
            smellDeque = resultProcessSmell.smellDeque;

            addCopyFish(fishList, copyFishBuffer);
            time--;
        }

        return fishList.size();
    }
    private static void addCopyFish(List<Fish> fishList, Queue<Fish> copyFishBuffer) {

        while (!copyFishBuffer.isEmpty()) {
            fishList.add(copyFishBuffer.poll());
        }
    }
    private static ResultProcessSmell processSmell(Deque<Smell> smellDeque) {

        boolean[][] newSmellGrid = new boolean[5][5];
        Deque<Smell> newSmellDeque = new ArrayDeque<>();
        while (!smellDeque.isEmpty()) {
            Smell smell = smellDeque.pollLast();

            if (--smell.count != 0 && !newSmellGrid[smell.row][smell.col]) {
                newSmellDeque.addFirst(smell);
                newSmellGrid[smell.row][smell.col] = true;
            }
         }

        return new ResultProcessSmell(newSmellGrid, newSmellDeque);
    }
    private static void eatingFish(List<Integer>[][] fishIdxGrid, List<Fish> fishList, List<Point> pointHistory, Deque<Smell> smellDeque) {

        Set<Integer> idxList = new TreeSet<>(Collections.reverseOrder());

        // 재방문하는 경우에 idx가 중복될 수 있다는 것을 고려해야함.
        for (Point point : pointHistory) {
            List<Integer> fishIdxListInGrid = fishIdxGrid[point.row][point.col];
            idxList.addAll(fishIdxListInGrid);
        }

        for (int idx : idxList) {
            Fish fish = fishList.get(idx);
            fishList.remove(idx);

            smellDeque.addLast(new Smell(fish.row, fish.col));
        }
    }
    private static List<Point> moveShark(List<Integer>[][] fishIdxGrid, Point shark) {

        Queue<MovingShark> movingSharkQueue = new ArrayDeque<>();
        movingSharkQueue.add(new MovingShark(shark.row, shark.col));

        MovingShark result = null;
        while(!movingSharkQueue.isEmpty()) {
            MovingShark curMovingShark = movingSharkQueue.poll();

            if (curMovingShark.movingCount == 3) {
                if (result == null) {
                    result = curMovingShark;
                } else {
                    if (result.compareTo(curMovingShark) > 0) result = curMovingShark;
                }
                continue;
            }

            for (int i = 1; i <= 4; i++) {
                int nextRow = curMovingShark.row + shark_dr[i];
                int nextCol = curMovingShark.col + shark_dc[i];

                if (isValidPoint(nextRow, nextCol)) {
                    Point nextPoint = new Point(nextRow, nextCol);
                    if (isVisitedPoint(curMovingShark.pointHistory, nextPoint)) {
                        movingSharkQueue.add(new MovingShark(
                                nextRow, nextCol, curMovingShark.eatingCount,
                                curMovingShark.movingCount + 1, curMovingShark.directionHistory, curMovingShark.pointHistory, i
                        ));
                    } else {
                        movingSharkQueue.add(new MovingShark(
                                nextRow, nextCol, curMovingShark.eatingCount + fishIdxGrid[nextRow][nextCol].size(),
                                curMovingShark.movingCount + 1, curMovingShark.directionHistory, curMovingShark.pointHistory, i
                        ));
                    }
                }
            }
        }

        if (result == null) {
            System.out.println("로직 오류 발생: result is null!");
            return null;
        }

        shark.row = result.row;
        shark.col = result.col;

        return result.pointHistory;
    }
    private static boolean isVisitedPoint(List<Point> pointHistory, Point point) {
        for (Point p : pointHistory) {
            if (p.row == point.row && p.col == point.col) return true;
        }

        return false;
    }
    private static List<Integer>[][] moveFishes(List<Fish> fishList, boolean[][] smellGrid, Point shark) {

        List<Integer>[][] fishIdxGrid = initFishIdxGrid();
        for (int i = 0; i < fishList.size(); i++) {
            Fish fish = fishList.get(i);
            Integer[] nextPoint = getNextPoint(fish, smellGrid, shark);

            fish.row = nextPoint[0];
            fish.col = nextPoint[1];
            fish.direction = nextPoint[2];

            fishIdxGrid[fish.row][fish.col].add(i);
        }

        return fishIdxGrid;
    }
    private static List<Integer>[][] initFishIdxGrid() {
        List<Integer>[][] grid = new List[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        return grid;
    }
    private static Integer[] getNextPoint(Fish fish, boolean[][] smellGrid, Point shark) {

        int curDirection = fish.direction;
        do {
            int nextRow = fish.row + dr[curDirection];
            int nextCol = fish.col + dc[curDirection];

            if (canMoveNextPoint(nextRow, nextCol, smellGrid, shark)) {
                return new Integer[]{nextRow, nextCol, curDirection};
            }

            curDirection = getNextDirection(curDirection);
        } while (curDirection != fish.direction);

        return new Integer[]{fish.row, fish.col, fish.direction};
    }
    private static int getNextDirection(int direction) {
        return direction == 1 ? 8 : direction - 1;
    }
    private static boolean canMoveNextPoint(int row, int col, boolean[][] smellGrid, Point shark) {
        return isValidPoint(row, col) && !smellGrid[row][col] && !isSharkPoint(row, col, shark);
    }
    private static boolean isSharkPoint(int row, int col, Point shark) {
        return row == shark.row && col == shark.col;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 1 && col >= 1 && row <= 4 && col <= 4;
    }
    private static Queue<Fish> getCopyFishBuffer(List<Fish> fishList) {

        Queue<Fish> copyFishBuffer = new ArrayDeque<>();
        for (Fish fish : fishList) {
            copyFishBuffer.add(new Fish(fish.row, fish.col, fish.direction));
        }

        return copyFishBuffer;
    }
}
class MovingShark implements Comparable<MovingShark>{
    int row;
    int col;
    int eatingCount;
    int movingCount;
    List<Integer> directionHistory;
    List<Point> pointHistory;

    MovingShark(int row, int col) {
        this.row = row;
        this.col = col;
        this.eatingCount = 0;
        this.movingCount = 0;
        this.directionHistory = new ArrayList<>();
        this.pointHistory = initPointHistory(row, col);
    }

    MovingShark(int row, int col, int eatingCount, int movingCount, List<Integer> directionHistory, List<Point> pointHistory, int direction) {
        this.row = row;
        this.col = col;
        this.eatingCount = eatingCount;
        this.movingCount = movingCount;
        this.directionHistory = getNewDirectionHistory(directionHistory, direction);
        this.pointHistory = getNewPointHistory(pointHistory, row, col);
    }
    private List<Integer> getNewDirectionHistory(List<Integer> directionHistory, int direction) {
        List<Integer> newDirectionHistory = new ArrayList<>(directionHistory);

        newDirectionHistory.add(direction);
        return newDirectionHistory;
    }
    private List<Point> getNewPointHistory(List<Point> pointHistory, int row, int col) {
        // 해당 기능이 재대로 Deep Copy가 되는지 확인 필요!!
        List<Point> newPointHistory = new ArrayList<>(pointHistory);

        newPointHistory.add(new Point(this.row, this.col));
        return newPointHistory;
    }
    private List<Point> initPointHistory(int row, int col) {
        List<Point> pointHistory = new ArrayList<>();

        pointHistory.add(new Point(row, col));
        return pointHistory;
    }

    @Override
    public int compareTo(MovingShark o) {
        if (this.eatingCount != o.eatingCount) return o.eatingCount - this.eatingCount;
        else {
            int thisIdx = Convert.getHistoryDirection(directionHistory);
            int compareIdx = Convert.getHistoryDirection(o.directionHistory);

            return thisIdx - compareIdx;
        }
    }
    private static class Convert {
        private static int getHistoryDirection(List<Integer> directionHistory) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : directionHistory) {
                sb.append(integer);
            }

            return Integer.parseInt(sb.toString());
        }
    }
}
class ResultProcessSmell {
    boolean[][] smellGrid;
    Deque<Smell> smellDeque;

    ResultProcessSmell(boolean[][] smellGrid, Deque<Smell> smellDeque) {
        this.smellGrid = smellGrid;
        this.smellDeque = smellDeque;
    }
}
class Smell {
    int row;
    int col;
    int count;

    Smell(int row, int col) {
        this.row = row;
        this.col = col;
        this.count = 3;
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
class Fish {
    int row;
    int col;
    int direction;

    Fish(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
}
```

**TLE(Map Grid 방식으로 풀었지만 시간 초과 나옴)**
```java
import java.io.*;
import java.util.*;

public class Main {
    private static final int[] DR = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] DC = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] SHARK_DR = {0, -1, 0, 1, 0};
    private static final int[] SHARK_DC = {0, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Fish>[][] fishListGrid = initFishListGrid();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishListGrid[row][col].add(new Fish(row, col, direction));
        }

        st = new StringTokenizer(br.readLine());
        int sharkRow = Integer.parseInt(st.nextToken());
        int sharkCol = Integer.parseInt(st.nextToken());

        Point sharkPoint = new Point(sharkRow, sharkCol);

        System.out.println(simulation(fishListGrid, sharkPoint, S));
    }
    private static int simulation(List<Fish>[][] fishListGrid, Point sharkPoint, int S) {

        Map<Integer, Integer> smellCountMap = initSmellCountMap();
        while (S != 0) {

            long startTime = System.currentTimeMillis();

            List<Fish>[][] copy = copyFishListGrid(fishListGrid);

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");

            fishListGrid = moveFishList(fishListGrid, sharkPoint, smellCountMap);

            // System.out.println("S is " + S);
            //printFishCountGrid(fishListGrid);


            sharkPoint = moveShark(fishListGrid, sharkPoint, smellCountMap);


            downSmellCount(smellCountMap);

            integrateFishListGrid(fishListGrid, copy);

            S--;
        }

        return getFishCount(fishListGrid);
    }
    private static void printFishCountGrid(List<Fish>[][] fishListGrid) {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.print(fishListGrid[i][j].size() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static Point moveShark(List<Fish>[][] fishListGrid, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        Queue<MovingShark> bufferMovingShark = new ArrayDeque<>();
        bufferMovingShark.add(new MovingShark(sharkPoint.row, sharkPoint.col));

        MovingShark result = null;
        while (!bufferMovingShark.isEmpty()) {
            MovingShark curMovingShark = bufferMovingShark.poll();

            if (curMovingShark.moveCount == 3) {
                if (result == null) {
                    result = curMovingShark;
                } else {
                    if (result.compareTo(curMovingShark) > 0) {
                        result = curMovingShark;
                    }
                }
                continue;
            }

            for (int i = 1; i <= 4; i++) {
                int nextRow = curMovingShark.row + SHARK_DR[i];
                int nextCol = curMovingShark.col + SHARK_DC[i];

                if (isValidPoint(nextRow, nextCol)) {
                    bufferMovingShark.add(new MovingShark(
                            nextRow, nextCol, i, curMovingShark.moveCount + 1,
                            getEatingCount(nextRow, nextCol, curMovingShark.eatingCount, fishListGrid[nextRow][nextCol].size(), curMovingShark.pointHistory),
                            curMovingShark.pointHistory, curMovingShark.directionHistory
                    ));
                }
            }
        }

        if (result == null) {
            System.out.println("Logic Error: Result Is Empty");
        }

        eatingFish(fishListGrid, result.pointHistory, smellCountMap);

        return new Point(result.row, result.col);
    }

    private static void eatingFish(List<Fish>[][] fishListGrid, List<Point> pointHistory, Map<Integer, Integer> smellCountMap) {

        for (Point point : pointHistory) {
            if (!fishListGrid[point.row][point.col].isEmpty()) {
                updateSmellCount(smellCountMap, point.row, point.col);
            }
            fishListGrid[point.row][point.col].clear();
        }
    }
    /*  Poing History가 전부 Smell Count로 처리되는 것이 아닌 먹이감이 있는 경우에만 Smell이 생성되는 것임.
      private static void eatingFish(List<Fish>[][] fishListGrid, List<Point> pointHistory, Map<Integer, Integer> smellCountMap) {
    
            for (Point point : pointHistory) {
                fishListGrid[point.row][point.col].clear();
                updateSmellCount(smellCountMap, point.row, point.col);
            }
        }*/
    private static int getEatingCount(int row, int col, int eatingCount, int fishCount, List<Point> pointHistory) {

        if (isVisitedPoint(row, col, pointHistory)) return eatingCount;

        return eatingCount + fishCount;
    }
    private static boolean isVisitedPoint(int row, int col, List<Point> pointHistory) {

        for (Point point : pointHistory) {
            if (point.row == row && point.col == col) return true;
        }

        return false;
    }
    private static List<Fish>[][] moveFishList(List<Fish>[][] fishListGrid, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        List<Fish>[][] newFishListGrid = initFishListGrid();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (Fish curFish : fishListGrid[i][j]) {

                    Fish movedFish = moveFish(curFish, sharkPoint, smellCountMap);

                    newFishListGrid[movedFish.row][movedFish.col].add(
                            new Fish(movedFish.row, movedFish.col, movedFish.direction));
                }
            }
        }

        return newFishListGrid;
    }
    private static Fish moveFish(Fish fish, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        int curDirection = fish.direction;

        do {
            int nextRow = fish.row + DR[curDirection];
            int nextCol = fish.col + DC[curDirection];

            if (canGoNextPoint(nextRow, nextCol, sharkPoint, smellCountMap)) {
                return new Fish(nextRow, nextCol, curDirection);
            }

            curDirection = getNextDirection(curDirection);
        } while (curDirection != fish.direction);

        return new Fish(fish.row, fish.col, fish.direction);
    }
    private static boolean canGoNextPoint(int nextRow, int nextCol, Point sharkPoint, Map<Integer, Integer> smellCountMap) {
        return isValidPoint(nextRow, nextCol) && !isSharkPoint(nextRow, nextCol, sharkPoint) && !isSmellInPoint(nextRow, nextCol, smellCountMap);
    }
    private static boolean isSmellInPoint(int row, int col, Map<Integer, Integer> smellCountMap) {
        int idx = getMapIdx(row, col);

        return smellCountMap.get(idx) != 0;
    }
    private static boolean isSharkPoint(int row, int col, Point sharkPoint) {
        return row == sharkPoint.row && col == sharkPoint.col;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 1 && col >= 1 && row <= 4 && col <= 4;
    }
    private static int getNextDirection(int direction) {
        return direction - 1 != 0 ? direction - 1 : 8;
    }

    private static int getFishCount(List<Fish>[][] fishListGrid) {

        int count = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                count += fishListGrid[i][j].size();
            }
        }

        return count;
    }
    private static void integrateFishListGrid(List<Fish>[][] fishListGrid, List<Fish>[][] copy) {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                fishListGrid[i][j].addAll(copy[i][j]);
            }
        }
    }
    private static List<Fish>[][] copyFishListGrid(List<Fish>[][] fishListGrid) {

        List<Fish>[][] copy = initFishListGrid();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < fishListGrid[i][j].size(); k++) {
                    Fish fish = fishListGrid[i][j].get(k);
                    copy[i][j].add(new Fish(fish.row, fish.col, fish.direction));
                }
            }
        }

        return copy;
    }
    private static void downSmellCount(Map<Integer, Integer> smellCountMap) {

        int MAX_IDX = 4 * 4;
        for (int i = 1; i < MAX_IDX + 1; i++) {
            if (smellCountMap.get(i) != 0) {
                smellCountMap.put(i, smellCountMap.get(i) - 1);
            }
        }
    }
    private static void updateSmellCount(Map<Integer, Integer> smellCountMap, int row, int col) {
        smellCountMap.put(getMapIdx(row, col), 3);
    }
    private static Map<Integer, Integer> initSmellCountMap() {
        Map<Integer, Integer> smellCountMap = new HashMap<>();

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                int idx = getMapIdx(i, j);
                smellCountMap.put(idx, 0);
            }
        }

        return smellCountMap;
    }
    private static int getMapIdx(int row, int col) {
        return 4 * (row - 1) + col;
    }
    private static List<Fish>[][] initFishListGrid() {

        List<Fish>[][] fishListGrid = new List[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                fishListGrid[i][j] = new LinkedList<>();
            }
        }
        return fishListGrid;
    }
}
class MovingShark implements Comparable<MovingShark>{
    int row;
    int col;
    int moveCount;
    int eatingCount;
    List<Point> pointHistory;
    List<Integer> directionHistory;
    MovingShark(int row, int col) {
        this.row = row;
        this.col = col;
        this.moveCount = 0;
        this.eatingCount = 0;
        pointHistory = new ArrayList<>();
        directionHistory = new ArrayList<>();
    }

    MovingShark(int row, int col, int direction, int moveCount, int eatingCount, List<Point> pointHistory, List<Integer> directionHistory) {
        this.row = row;
        this.col = col;
        this.moveCount = moveCount;
        this.eatingCount = eatingCount;
        this.pointHistory = initPointHistory(row, col, pointHistory);
        this.directionHistory = initDirectionHistory(direction, directionHistory);
    }
    private List<Point> initPointHistory(int row, int col, List<Point> pointHistory) {

        List<Point> newPointHistory = new ArrayList<>(pointHistory);
        newPointHistory.add(new Point(row, col));

        return newPointHistory;
    }
    private List<Integer> initDirectionHistory(int direction, List<Integer> directionHistory) {
        List<Integer> newDirectionHistory = new ArrayList<>(directionHistory);
        newDirectionHistory.add(direction);

        return newDirectionHistory;
    }

    @Override
    public int compareTo(MovingShark o) {
        if (this.eatingCount != o.eatingCount) return o.eatingCount - this.eatingCount;
        else {
            int size = this.directionHistory.size();
            if (size != o.directionHistory.size()) {
                System.out.println("Can't compare");
                return 0;
            } else {
                for (int i = 0; i < size; i++) {
                    int directionIdx = this.directionHistory.get(i);
                    int comp = o.directionHistory.get(i);
                    if (directionIdx != comp) return directionIdx - comp;
                }
            }
        }
        return 0;
    }
}
class Fish {
    int row;
    int col;
    int direction;

    Fish(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
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
**시간 분석 결과**
```markdown
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 0 milliseconds
Execution time: 1 milliseconds
Execution time: 1 milliseconds
Execution time: 1 milliseconds
Execution time: 3 milliseconds
Execution time: 4 milliseconds
Execution time: 10 milliseconds
Execution time: 24 milliseconds
Execution time: 63 milliseconds
Execution time: 193 milliseconds
Execution time: 559 milliseconds
Execution time: 1239 milliseconds
Execution time: 2972 milliseconds
Execution time: 6449 milliseconds
```

**문제 분석**

초기 분석: 객체의 생성을 너무 많이 호출하기 때문에 시간이 오래 걸리는 것으로 추측

분석 결과: LinkedList를 사용하여 get()을 사용하면 순차 탐색을 하기 때문에 시간 오버가 발생

문제 해결: LinkedList를 ArrayList로 바꿀 경우, index로 접근하여 요소에 접근하는 것이 O(1)이기 때문에 매우 빠르게 해결

