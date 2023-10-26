# 마법사 상어와 파이어볼 

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	17472	|6959	|4229	|36.276%|

## 문제 

어른 상어가 마법사가 되었고, 파이어볼을 배웠다.

마법사 상어가 크기가 N×N인 격자에 파이어볼 M개를 발사했다. 가장 처음에 파이어볼은 각자 위치에서 이동을 대기하고 있다. i번 파이어볼의 위치는 (ri, ci), 질량은 mi이고, 방향은 di, 속력은 si이다. 위치 (r, c)는 r행 c열을 의미한다.

격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.

파이어볼의 방향은 어떤 칸과 인접한 8개의 칸의 방향을 의미하며, 정수로는 다음과 같다.

|7	|0|	1|
|---|---|---|
|6	| |	2|
|5	|4|	3|

마법사 상어가 모든 파이어볼에게 이동을 명령하면 다음이 일들이 일어난다.

1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
   - 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
   1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
   2. 파이어볼은 4개의 파이어볼로 나누어진다.
   3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
      1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
      2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
      3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
   4. 질량이 0인 파이어볼은 소멸되어 없어진다.

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.

## 입력 

첫째 줄에 N, M, K가 주어진다.

둘째 줄부터 M개의 줄에 파이어볼의 정보가 한 줄에 하나씩 주어진다. 파이어볼의 정보는 다섯 정수 r<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub>, s<sub>i</sub>, d<sub>i</sub>로 이루어져 있다.

서로 다른 두 파이어볼의 위치가 같은 경우는 입력으로 주어지지 않는다.

## 출력 

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 출력한다.

## 제한 

4 ≤ N ≤ 50
0 ≤ M ≤ N2
1 ≤ K ≤ 1,000
1 ≤ r<sub>i</sub>, c<sub>i</sub> ≤ N
1 ≤ m<sub>i</sub> ≤ 1,000
1 ≤ s<sub>i</sub> ≤ 1,000
0 ≤ d<sub>i</sub> ≤ 7

## 예제 입력 1

```
4 2 1
1 1 5 2 2
1 4 7 1 6
```

## 예제 출력 1

```
8
```

## 예제 입력 2

```
4 2 2
1 1 5 2 2
1 4 7 1 6
```

##  예제 출력 2

```
8
```

## 예제 입력 3

```
4 2 3
1 1 5 2 2
1 4 7 1 6
```

## 예제 출력 3

```
0
```

## 예제 입력 4

```
7 5 3
1 3 5 2 4
2 3 5 2 6
5 2 9 1 7
6 2 1 3 5
4 4 2 4 2
```

## 예제 출력 4

```
9
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Queue<Fireball> q = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int mass = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      q.add(new Fireball(row, col, mass, speed, direction));
    }

    System.out.println(simulation(q));
  }
  static int simulation(Queue<Fireball> q) {
    Queue<Fireball>[][] grid = new Queue[N][N];
    Queue<Point> idxList = new ArrayDeque<>();

    while (K-- > 0) {
      active_fireball(q, grid, idxList);
      check_fireball(q, grid, idxList);
    }

    int sum = 0;
    while (!q.isEmpty()) sum += q.poll().mass;
    return sum;
  }
  static void active_fireball(Queue<Fireball> q, Queue<Fireball>[][] grid, Queue<Point> idxList) {

    while (!q.isEmpty()) {
      Fireball fireball = q.poll();

      // 이와 같이 처리하면 안됩니다.
      int remainMoveCount = fireball.speed % N;

      int nextRow = fireball.row + dr[fireball.direction] * remainMoveCount;
      int nextCol = fireball.col + dc[fireball.direction] * remainMoveCount;


      if (nextRow >= N) fireball.row = nextRow - N;
      else if(nextRow < 0) fireball.row = nextRow + N;
      else fireball.row = nextRow;

      if (nextCol >= N) fireball.col = nextCol - N;
      else if (nextCol < 0) fireball.col = nextCol + N;
      else fireball.col = nextCol;

      if (grid[fireball.row][fireball.col] == null) grid[fireball.row][fireball.col] = new ArrayDeque<>();

      if (grid[fireball.row][fireball.col].isEmpty()) {
        grid[fireball.row][fireball.col].add(fireball);
        idxList.add(new Point(fireball.row, fireball.col));
      }
      else grid[fireball.row][fireball.col].add(fireball);
    }
  }
  static void check_fireball(Queue<Fireball> q, Queue<Fireball>[][] grid, Queue<Point> idxList) {
    while (!idxList.isEmpty()) {
      Point point = idxList.poll();

      if (grid[point.row][point.col].size() == 1) {
        q.add(grid[point.row][point.col].poll());
      }
      else {
        int sumMass = 0;
        int sumSpeed = 0;
        int checkDirection = -1;
        boolean direction = true;
        int count = 0;
        while (!grid[point.row][point.col].isEmpty()) {
          Fireball fireball = grid[point.row][point.col].poll();

          sumMass += fireball.mass;
          sumSpeed += fireball.speed;
          if (checkDirection == -1) checkDirection = fireball.direction % 2;
          else {
            direction = (direction && checkDirection == fireball.direction % 2);
          }
          count++;
        }

        int resultMass = sumMass / 5;
        int resultSpeed = sumSpeed / count;
        if (resultMass == 0) continue;

        for (int i = 0; i <= 6; i += 2) {
          if (direction) q.add(new Fireball(point.row, point.col, resultMass, resultSpeed, i));
          else q.add(new Fireball(point.row, point.col, resultMass,  resultSpeed, i + 1));
        }
      }
    }
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
class Fireball {
  int row;
  int col;
  int mass;
  int direction;
  int speed;

  Fireball(int row, int col, int mass, int speed,  int direction) {
    this.row = row;
    this.col = col;
    this.mass = mass;
    this.speed = speed;
    this.direction = direction;
  }
}
```