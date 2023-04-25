# 아기 상어 

**골드 3**

|시간 제한	|메모리 제한|	제출	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|
|2 초|	512 MB|	57799	|26648	|15934|	42.595%|

## 문제 

N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

- 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
- 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
- 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
  - 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
  - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

- 0: 빈 칸
- 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
- 9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.

## 출력 

첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

## 예제 입력 1

```
3
0 0 0
0 0 0
0 9 0
```

## 예제 출력 1

```
0
```

## 에제 입력 2

```
3
0 0 1
0 0 0
0 9 0
```

## 예제 출력 2

```
3
```

## 예제 입력 3

```
4
4 3 2 1
0 0 0 0
0 0 9 0
1 2 3 4
```

## 예제 출력 3

```
14
```

## 에제 입력 4

```
6
5 4 3 2 3 4
4 3 2 3 4 5
3 2 9 5 6 6
2 1 2 3 4 5
3 2 1 6 5 4
6 6 6 6 6 6
```

## 예제 출력 4

```
60
```

## 예제 입력 5

```
6
6 0 6 0 6 1
0 0 0 0 0 2
2 3 4 5 6 6
0 0 0 0 0 2
0 2 0 0 0 0
3 9 3 0 0 1
```

## 예제 출력 5

```
48
```

## 예제 입력 6

```
6
1 1 1 1 1 1
2 2 6 2 2 3
2 2 5 2 2 3
2 2 2 4 6 3
0 0 0 0 0 6
0 0 0 0 0 9
```

## 예제 출력 6

```
39
```

## 풀이 과정

1. 현재 사이즈를 가지고 먹을 수 있는 물고기 체크 
2. 1마리 이상이면 BFS 탐색 시작 
   1. BFS 탐색: 도달 가능 여부 확인
   2. 도달 가능하다면 return 값으로 걸린 시간과 위치 -> Class 이용
   3. 도달 불가능하다면 boolean 자료형 멤버 변수에 false 입력 
3. return 한 값을 받아서 상어 객체의 위치와 시간 수정

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 서, 북, 남, 동
  static int[] dr = {0, -1, 1, 0};
  static int[] dc = {-1, 0, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    int row = 0;
    int col = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          row = i;
          col = j;
        }
      }
    }

    System.out.println(simulation(grid, N, row, col));


  }
  static int simulation(int[][] grid, int N, int row, int col) {
    Shark shark = new Shark(row, col, 2);

    int time = 0;
    while (true) {
      int count = 0;
      boolean end = true;
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size)  {
            count++;
            end = false;
          }
        }

      if (end) break;

      if (count >= 1) {
        State state = bfs(grid, N, shark.row, shark.col, shark.size);
        if (state.success) {
          shark.row = state.row;
          shark.col = state.col;
          shark.size += state.size;
          time += state.time;
        }
        else break;
      }

    }

    return time;
  }
  static State bfs(int[][] grid, int N, int row, int col, int sharkSize) {

    Queue<State> q = new ArrayDeque<>();
    q.add(new State(row, col, 0));
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    while (!q.isEmpty()) {
      State curState = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol, N) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] != 0 && grid[nextRow][nextCol] < sharkSize) {
            int size = grid[nextRow][nextCol];
            grid[nextRow][nextCol] = 0;
            return new State(nextRow, nextCol, curState.time + 1, size, true);
          }
          else if (grid[nextRow][nextCol] == 0 || grid[nextRow][nextCol] == sharkSize) {
            q.add(new State(nextRow, nextCol, curState.time + 1));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
    return new State(row, col, 0, 0, false);
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}

class Shark {
  int row;
  int col;
  int size;

  Shark(int row, int col, int size) {
    this.row = row;
    this.col = col;
    this.size = size;
  }
}

class State {
  int row;
  int col;
  int time;
  int size;
  boolean success;

  State(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
    success = false;
  }

  State(int row, int col, int time, int size, boolean success) {
    this(row, col, time);
    this.size = size;
    this.success = success;
  }
}
```

예제 3번부터 잘못된 출력값을 뽑아낸다.

현재 **먹는 순서**와 **경험치**를 고려 안했습니다.

이 부분을 처리 해도 아직 예제 4, 5가 잘못된 출력이 나옵니다. 

추가적으로 처음에 상어 위치를 입력받을 때 9로 입력 받은 부분을 0으로 수정해줘야 합니다.

그리고 일반적인 BFS 탐색 방식으로는 맨 위의 요소부터 처리하는 것에 문제가 발생합니다.

다음을 봐보자.

```
5 4 ■ . 3 4 
4 3 . 3 4 5 
3 2 . 5 6 6 
2 . . 3 4 5 
3 2 . 6 5 4 
6 6 6 6 6 6 
```

여기서 네모가 현재 위치인데 맨 위 오른쪽에 거리가 2이고 크기가 3인 물고기가 위치하고 있어서 저것을 먹어야 합니다.

하지만, 현재 코드로는 불가능합니다.

현재 위치 기준으로 Queue에 상, 좌, 우, 남으로 넣는데 이렇게 처리를 할 경우 맨 위의 물고기를 먼저 먹을 수 없습니다.

좌측 노드를 먼저 넣고 이것이 먼저 꺼내지면서 아래쪽 요소를 먼저 실행하기 때문입니다. 

time 별로 나눠야 될 수도 있을 것 같네요..

일단 현재까지 코드입니다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 좌, 남, 우
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    int row = 0;
    int col = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          grid[i][j] = 0;
          row = i;
          col = j;
        }
      }
    }

    System.out.println(simulation(grid, N, row, col));
  }
  static int simulation(int[][] grid, int N, int row, int col) {
    Shark shark = new Shark(row, col, 2);

    int time = 0;
    while (true) {
      int count = 0;
      boolean end = true;
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size)  {
            count++;
            end = false;
          }
        }

      if (end) break;

      if (count >= 1) {
        State state = bfs(grid, N, shark.row, shark.col, shark.size);
        if (state.success) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          time += state.time;
        }
        else break;
      }

    }

    return time;
  }
  static State bfs(int[][] grid, int N, int row, int col, int sharkSize) {

    Queue<State> q = new ArrayDeque<>();
    q.add(new State(row, col, 0));
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    while (!q.isEmpty()) {
      State curState = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol, N) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] != 0 && grid[nextRow][nextCol] < sharkSize) {
            grid[nextRow][nextCol] = 0;
            return new State(nextRow, nextCol, curState.time + 1, true);
          }
          else if (grid[nextRow][nextCol] == 0 || grid[nextRow][nextCol] == sharkSize) {
            q.add(new State(nextRow, nextCol, curState.time + 1));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
    return new State(row, col, 0, false);
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}

class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark(int row, int col, int size) {
    this.row = row;
    this.col = col;
    this.size = size;
    this.exp = 0;
  }
}

class State {
  int row;
  int col;
  int time;
  boolean success;

  State(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
    success = false;
  }

  State(int row, int col, int time, boolean success) {
    this(row, col, time);
    this.success = success;
  }
}

```

## 쓰레기 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 좌, 우, 하
  static int[] dr = {-1, 0, 0, 1};
  static int[] dc = {0, -1, 1, 0};

  // 좌, 우
  static int[] topDc = {-1, 1, 0};
  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];

    int row = 0;
    int col = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          row = i;
          col = j;
          grid[i][j] = 0;
        }
      }
    }

    int time = simulation(grid, row, col);

    System.out.println(time);
  }
  static int simulation(int[][] grid, int row, int col) {

    Shark shark = new Shark(row, col);

    int t;
    for (t = 0; t <= N * N;) {

      int count = 0;
      for (int i = 0 ; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size) count++;
        }
      }

      // 먹을 수 있는 경우
      if (count >= 1) {
        State state = Bfs(grid, shark.row, shark.col, shark.size);
        if (state != null) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          t += state.time;
        }
        // 상황 종료
        else break;
      }
      // 상황 종료
      else break;
    }

    return t;
  }
  static State Bfs(int[][] grid, int row, int col, int size) {
    Queue<State> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][N];

    if (row == 0) {
      if (isValidIdx(row, col - 1)) {
        q.add(new State(row, col - 1, 1));
        isVisited[row][col - 1] = true;
      }
      if (isValidIdx(row, col + 1))  {
        q.add(new State(row, col + 1, 1));
        isVisited[row][col + 1] = true;
      }
      if (isValidIdx(row + 1, col)) {
        q.add(new State(row + 1, col, 1));
        isVisited[row + 1][col] = true;
      }
    }
    else  {
      q.add(new State(row, col, 0));
      isVisited[row][col] = true;
    }


    while (!q.isEmpty()) {
      State curState = q.poll();

      if (grid[curState.row][curState.col] != 0 && grid[curState.row][curState.col] < size) {
        grid[curState.row][curState.col] = 0;
        return curState;
      }

      // 맨 위인 경우
      if (curState.top) {
        for (int i = 0; i < 2; i++) {
          int curRow = curState.row;
          int curCol = curState.col + topDc[i];

          if (isValidIdx(curRow, curCol) &&
                  !isVisited[curRow][curCol] && grid[curRow][curCol] <= size) {
            q.add(new State(curRow, curCol, curState.time + 1));
            isVisited[curRow][curCol] = true;
          }
        }
      }
      // 아닌 경우
      else {
        for (int i = 0; i < 4; i++) {
          int curRow = curState.row + dr[i];
          int curCol = curState.col + dc[i];

          if (isValidIdx(curRow, curCol) &&
                  !isVisited[curRow][curCol] && grid[curRow][curCol] <= size) {
            q.add(new State(curRow, curCol, curState.time + 1));
            isVisited[curRow][curCol] = true;
          }
        }
      }
    }
   return null;
  }
  static boolean isValidIdx(int row, int col) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}

class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.size = 2;
    this.exp = 0;
  }
}

class State {
  int row;
  int col;
  int time;
  boolean top;


  State(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
    top = row == 0;
  }

}
```

## 다른 풀이

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  // 북, 서, 남, 동
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][N];

    int row = 0;
    int col = 0;
    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N;j ++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 9) {
          row = i;
          col = j;
          grid[i][j] = 0;
        }
      }
    }

    System.out.println(simulation(grid, row , col));
  }
  static int simulation(int[][] grid, int row, int col) {

    int time = 0;
    Shark shark = new Shark(row, col);
    while (time < N * N) {
      int howManyCanEatFish = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] < shark.size) howManyCanEatFish++;
        }
      }

      if (howManyCanEatFish >= 1) {
        State state = bfs(grid, shark.row, shark.col, shark.size);
        if (state != null) {
          shark.row = state.row;
          shark.col = state.col;
          shark.exp++;
          if (shark.size == shark.exp) {
            shark.size++;
            shark.exp = 0;
          }
          time += state.time;
          grid[state.row][state.col] = 0;
        }
        else break;
      }
      else break;
    }
    return time;
  }
  static State bfs(int[][] grid, int row, int col, int size) {

    Queue<State> q = new ArrayDeque<>();
    q.add(new State(row, col, grid[row][col], 0));
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    int time = 0;
    State priorityState = null;
    while (!q.isEmpty()) {
      State curState = q.poll();

      // 한 타임이 끝난 경우
      if (time != curState.time) {
        // 한 타임 동안 발견 못했을 경우
        if (priorityState == null) time = curState.time;
        // 발견한 경우
        else return priorityState;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curState.row + dr[i];
        int nextCol = curState.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] <= size) {
            State nextState = new State(nextRow, nextCol, grid[nextRow][nextCol], time + 1);

            // 물고기 사이즈 < 아기 상어 사이즈 (0이 아닌 경우)
            if (nextState.fishSize != 0 && nextState.fishSize < size){
              // 이전에 발견한 State가 있는 경우
              if (priorityState != null)  {
                if (!priorityState.checkPriority(nextState)) priorityState = nextState;
              }
              // 이전에 발견한 State가 없는 경우
              else priorityState = nextState;
            }
            q.add(nextState);
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
    return null;
  }
  static boolean isValidIdx(int row, int col) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    else return true;
  }
}
class Shark {
  int row;
  int col;
  int size;
  int exp;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.size = 2;
    this.exp = 0;
  }
}

class State {
  int row;
  int col;
  int fishSize;
  int time;

  State(int row, int col, int fishSize, int time) {
    this.row = row;
    this.col = col;
    this.fishSize = fishSize;
    this.time = time;
  }

  boolean checkPriority(State other) {
    if (this.row < other.row) return true;
    else if (this.row > other.row) return false;
    else {
      if (this.col < other.col) return true;
      else return false;
    }
  }
}
```