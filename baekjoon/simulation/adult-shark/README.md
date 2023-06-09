# 어른 상어
 
**골드 2**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|13155	|5482|	3354	|38.991%|

## 문제 

문제 양이 많고 내용을 넣기 힘들어서 링크를 달아놓도록 하겠습니다. 

https://www.acmicpc.net/problem/19237

## 오류 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    int[][] id_grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        id_grid[i][j] = Integer.parseInt(st.nextToken());
        if (id_grid[i][j] != 0) {
          sharks[id_grid[i][j]] = new Shark(i, j);
        }
        id_grid[i][j] = 0;
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].setDirection(Integer.parseInt(st.nextToken()));
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 0; d < 4; d++) {
          sharks[i].grid_direction[j][d] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, id_grid));

  }
  static int simulation(Shark[] sharks, int[][] id_grid) {

    int time = 0;

    Queue<Smell> pq = new PriorityQueue<>();

    while (time <= 1000) {
      time++;

      active_smell(sharks, id_grid, pq);

      pq = active_move(sharks, id_grid, pq);

      if (checkShark(sharks)) break;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean checkShark(Shark[] sharks) {
    int count = 0;
    for (int i = 1; i < sharks.length; i++) {
      Shark shark = sharks[i];
      if (!shark.isDead) count++;
    }
    if (count == 1) {
      return true;
    }
    else return false;
  }
  static Queue<Smell> active_move (Shark[] sharks, int[][] id_grid, Queue<Smell> pq) {

    Queue<Smell> newPQ = new PriorityQueue<>();
    int[][] idx_buffer = new int[N][N];

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        // baseDirection
        int bd = shark.direction;

        int d = 0;
        for (; d < 4; d++) {
          // nextDirection
          int nd = shark.grid_direction[bd][d];

          int nextRow = shark.row + dr[nd];
          int nextCol = shark.col + dc[nd];
          if (isValidIdx(nextRow, nextCol)
                  && id_grid[nextRow][nextCol] == 0) {
            if (idx_buffer[nextRow][nextCol] != 0) {
              shark.isDead = true;
              break;
            }
            idx_buffer[nextRow][nextCol] = i;
            shark.row = nextRow;
            shark.col = nextCol;
            break;
          }
        }
        if (d == 4) {
          for (d = 0; d < 4; d++) {
            // nextDirection
            int nd = shark.grid_direction[bd][d];

            int nextRow = shark.row + dr[nd];
            int nextCol = shark.col + dc[nd];
            if (isValidIdx(nextRow, nextCol)
                    && id_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              break;
            }
          }
        }
      }
    }

    while (!pq.isEmpty()) {
      Smell smell = pq.poll();
      smell.time--;

      if (smell.time == 0) id_grid[smell.row][smell.col] = 0;
      else newPQ.add(smell);
    }
    return newPQ;
  }
  static void active_smell (Shark[] sharks, int[][] id_grid, Queue<Smell> pq) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        id_grid[shark.row][shark.col] = i;
        pq.add(new Smell(i, K, shark.row, shark.col));
      }
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell implements Comparable<Smell>{
  int idx;
  int time;
  int row;
  int col;

  Smell (int idx, int time, int row, int col) {
    this.idx = idx;
    this.time = time;
    this.row = row;
    this.col = col;
  }

  @Override
  public int compareTo(Smell o) {
    if (this.time > o.time) return 1;
    else if (this.time < o.time) return -1;
    else {
      return this.idx - o.idx;
    }
  }
}
class Shark {
  int row;
  int col;
  int direction;
  boolean isDead;
  int[][] grid_direction;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    isDead = false;
    grid_direction = new int[5][4];
  }

  void setDirection(int direction) {
    this.direction = direction;
  }
}
```

일단 해당 코드의 첫번째 문제점 **상어가 이동을 완료한 후에 방향을 기록하지 않았습니다.**

즉, **객체 처리**를 재대로 하지 않았음.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][N + 1];
    Shark[] sharks = new Shark[M + 1];
    Queue<Smell> q = new ArrayDeque<>();

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] != 0) {
          sharks[grid[i][j]] = new Shark(i, j);
          q.add(new Smell(i, j, K));
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 1; d <= 4; d++) {
          sharks[i].grid_direction[j][d] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, grid, q));

  }
  static int simulation (Shark[] sharks, int[][] grid, Queue<Smell> q) {
    int time = 0;

    while (time <= 1000) {
      active_move(sharks, grid, q);
      q = active_smell_count(q, grid);
      active_smell(sharks, grid, q);
      time++;
      if (isEnd(sharks)) break;
    }
    if (time == 1001) return -1;
    return time;
  }
  static void active_smell (Shark[] sharks, int[][] grid, Queue<Smell> q) {
    for (int i = 1; i <= M; i++) {
      if (!sharks[i].isDead) {
        grid[sharks[i].row][sharks[i].col] = i;
        q.add(new Smell(sharks[i].row, sharks[i].col, K));
      }
    }
  }
  static void active_move (Shark[] sharks, int[][] grid, Queue<Smell> q) {
    int[][] idx_buffer = new int[N + 1][N + 1];

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];
      if (!shark.isDead) {
        int origin_d = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int next_d = shark.grid_direction[origin_d][j];

          int nextRow = shark.row + dr[next_d];
          int nextCol = shark.col + dc[next_d];

          if (isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol] == 0) {
            if (idx_buffer[nextRow][nextCol] != 0) {
              shark.isDead = true;
              break;
            }
            idx_buffer[nextRow][nextCol] = i;
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = next_d;
            break;
          }
        }

        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int next_d = shark.grid_direction[origin_d][j];

            int nextRow = shark.row + dr[next_d];
            int nextCol = shark.col + dc[next_d];

            if (isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = next_d;
              break;
            }
          }
        }
      }
    }
  }
  static Queue<Smell> active_smell_count (Queue<Smell> q, int[][] grid) {
    Queue<Smell> newQ = new ArrayDeque<>();

    while (!q.isEmpty()) {
      Smell smell = q.poll();

      if (--smell.time == 0) grid[smell.row][smell.col] = 0;
      else newQ.add(smell);
    }
    return newQ;
  }
  static boolean isEnd (Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) count++;
    }
    return count == 1;
  }
  static boolean isValidIdx (int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  boolean isDead;
  int[][] grid_direction;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.isDead = false;
    grid_direction = new int[5][5];
  }
}
class Smell {
  int row;
  int col;
  int time;

  Smell(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
  }
}
```

이 코드의 문제점은 만약 상하좌우에 냄새가 전부 존재해서 자신의 냄새가 있는 곳으로 이동하려고 할 경우 

Queue에 이미 냄새 정보가 들어가 있는 것을 고려하지 않았음.

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int idx = Integer.parseInt(st.nextToken());
        if (idx != 0) {
          sharks[idx] = new Shark(i, j);
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 1; d <= 4; d++) {
          sharks[i].direction_grid[j][d] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks));

  }
  static int simulation(Shark[] sharks) {
    int time = 0;

    int[][] smell_grid = new int[N][N];
    Queue<Smell> pq = new PriorityQueue<>();
    active_smell(pq, sharks, smell_grid);
    while (time != 1001) {

      active_move(sharks, smell_grid);

      pq = active_smell_time(pq, smell_grid);

      active_smell(pq, sharks, smell_grid);

      time++;
      if (isEnd(sharks)) break;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean isEnd(Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) if (!sharks[i].isDead) count++;
    return count == 1;
  }
  static void active_move(Shark[] sharks, int[][] smell_grid) {

    boolean[][] isShark = new boolean[N][N];
    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];
      if (!shark.isDead) {
        int base_direction = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int next_direction = shark.direction_grid[base_direction][j];

          int nextRow = shark.row + dr[next_direction];
          int nextCol = shark.col + dc[next_direction];

          if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == 0) {
            if (isShark[nextRow][nextCol]) {
              shark.isDead = true;
              break;
            }
            isShark[nextRow][nextCol] = true;
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = next_direction;
            break;
          }
        }
        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int next_direction = shark.direction_grid[base_direction][j];

            int nextRow = shark.row + dr[next_direction];
            int nextCol = shark.col + dc[next_direction];

            if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = next_direction;
              isShark[nextRow][nextCol] = true;
              break;
            }
          }
        }
      }
    }
  }
  static void active_smell(Queue<Smell> pq, Shark[] sharks, int[][] smell_grid) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        smell_grid[shark.row][shark.col] = i;
        pq.add(new Smell(shark.row, shark.col, i, K));
      }
    }
  }
  static Queue<Smell> active_smell_time(Queue<Smell> pq, int[][] smell_grid) {
    Queue<Smell> newPQ = new PriorityQueue<>();

    while (!pq.isEmpty()) {
      Smell smell = pq.poll();

      if (--smell.time == 0) smell_grid[smell.row][smell.col] = 0;
      else {
        smell_grid[smell.row][smell.col] = smell.idx;
        newPQ.add(smell);
      }
    }
    return newPQ;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell implements Comparable<Smell> {
  int row;
  int col;
  int idx;
  int time;

  Smell(int row, int col, int idx, int time) {
    this.row = row;
    this.col = col;
    this.idx = idx;
    this.time = time;
  }

  @Override
  public int compareTo(Smell o) {
    return this.time - o.time;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  boolean isDead;
  int[][] direction_grid;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    direction_grid = new int[5][5];
  }
}
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    boolean[][] shark_grid = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int idx = Integer.parseInt(st.nextToken());
        if (idx != 0) {
          sharks[idx] = new Shark(i, j);
          shark_grid[i][j] = true;
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int d = 1; d <= 4; d++) {
          sharks[i].direction_grid[j][d] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, shark_grid));

  }
  static int simulation(Shark[] sharks, boolean[][] shark_grid) {
    int time = 0;

    int[][] smell_grid = new int[N][N];
    Queue<Smell> pq = new PriorityQueue<>();
    active_smell(pq, sharks, smell_grid);
    while (time != 1001) {

      active_move(sharks, smell_grid, shark_grid);

      pq = active_smell_time(pq, smell_grid, shark_grid);

      active_smell(pq, sharks, smell_grid);

      time++;
      if (isEnd(sharks)) break;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean isEnd(Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) if (!sharks[i].isDead) count++;
    return count == 1;
  }
  static void active_move(Shark[] sharks, int[][] smell_grid, boolean[][] shark_grid) {

    boolean[][] isShark = new boolean[N][N];
    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];
      if (!shark.isDead) {
        shark_grid[shark.row][shark.col] = false;
        int base_direction = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int next_direction = shark.direction_grid[base_direction][j];

          int nextRow = shark.row + dr[next_direction];
          int nextCol = shark.col + dc[next_direction];

          if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == 0) {
            if (isShark[nextRow][nextCol]) {
              shark.isDead = true;
              break;
            }
            isShark[nextRow][nextCol] = true;
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = next_direction;
            shark_grid[shark.row][shark.col] = true;
            break;
          }
        }
        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int next_direction = shark.direction_grid[base_direction][j];

            int nextRow = shark.row + dr[next_direction];
            int nextCol = shark.col + dc[next_direction];

            if (isValidIdx(nextRow, nextCol) && smell_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = next_direction;
              isShark[nextRow][nextCol] = true;
              shark_grid[nextRow][nextCol] = true;
              break;
            }
          }
        }
      }
    }
  }
  static void active_smell(Queue<Smell> pq, Shark[] sharks, int[][] smell_grid) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        smell_grid[shark.row][shark.col] = i;
        pq.add(new Smell(shark.row, shark.col, i, K));
      }
    }
  }
  static Queue<Smell> active_smell_time(Queue<Smell> pq, int[][] smell_grid, boolean[][] shark_grid) {
    Queue<Smell> newPQ = new PriorityQueue<>();

    while (!pq.isEmpty()) {
      Smell smell = pq.poll();

      if (!shark_grid[smell.row][smell.col]) {
        if (--smell.time == 0) smell_grid[smell.row][smell.col] = 0;
        else {
          smell_grid[smell.row][smell.col] = smell.idx;
          newPQ.add(smell);
        }
      }
    }
    return newPQ;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell implements Comparable<Smell> {
  int row;
  int col;
  int idx;
  int time;

  Smell(int row, int col, int idx, int time) {
    this.row = row;
    this.col = col;
    this.idx = idx;
    this.time = time;
  }

  @Override
  public int compareTo(Smell o) {
    return this.time - o.time;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  boolean isDead;
  int[][] direction_grid;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    direction_grid = new int[5][5];
  }
}
```

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] idx_grid = new int[N][N];
    Shark[] sharks = new Shark[M + 1];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        idx_grid[i][j] = Integer.parseInt(st.nextToken());
        if (idx_grid[i][j] != 0) {
          sharks[idx_grid[i][j]] = new Shark(i, j);
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= M; i++) {
      sharks[i].direction = Integer.parseInt(st.nextToken());
    }

    for (int idx = 1; idx <= M; idx++) {
      for (int i = 1; i <= 4; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 1; j <= 4; j++) {
          sharks[idx].grid_direction[i][j] = Integer.parseInt(st.nextToken());
        }
      }
    }

    System.out.println(simulation(sharks, idx_grid));
  }
  static int simulation (Shark[] sharks, int[][] idx_grid) {

    int time = 0;
    Queue<Smell> smells = new ArrayDeque<>();
    active_smell(smells, sharks, idx_grid);

    while (time != 1001) {

      smells = active_move(smells, sharks, idx_grid);

      active_smell(smells, sharks, idx_grid);

      time++;
      if (isEnd(sharks)) break;
    }

    if (time == 1001) return -1;
    else return time;
  }
  static boolean isEnd (Shark[] sharks) {

    int count = 0;
    for (int i = 1; i <= M; i++) {
      if (!sharks[i].isDead) count++;
    }
    return count == 1;
  }
  static Queue<Smell> active_move(Queue<Smell> smells, Shark[] sharks, int[][] idx_grid) {

    int[][] buffer = new int[N][N];

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        int baseDirection = shark.direction;

        int j;
        for (j = 1; j <= 4; j++) {
          int nextDirection = shark.grid_direction[baseDirection][j];

          int nextRow = shark.row + dr[nextDirection];
          int nextCol = shark.col + dc[nextDirection];

          if (isValidIdx(nextRow, nextCol) && idx_grid[nextRow][nextCol] == 0) {
            if (buffer[nextRow][nextCol] != 0) {
              shark.isDead = true;
              break;
            }
            shark.row = nextRow;
            shark.col = nextCol;
            shark.direction = nextDirection;
            buffer[nextRow][nextCol] = i;
            break;
          }
        }
        if (j == 5) {
          for (j = 1; j <= 4; j++) {
            int nextDirection = shark.grid_direction[baseDirection][j];

            int nextRow = shark.row + dr[nextDirection];
            int nextCol = shark.col + dc[nextDirection];

            if (isValidIdx(nextRow, nextCol) && idx_grid[nextRow][nextCol] == i) {
              shark.row = nextRow;
              shark.col = nextCol;
              shark.direction = nextDirection;
              buffer[nextRow][nextCol] = i;
              break;
            }
          }
        }
      }
    }

    Queue<Smell> newSmellQueue = new ArrayDeque<>();
    // Smell decrease Count
    while (!smells.isEmpty()) {
      Smell smell = smells.poll();

      if (buffer[smell.row][smell.col] != 0 || --smell.time == 0) {
        idx_grid[smell.row][smell.col] = 0;
      }
      else newSmellQueue.add(smell);
    }
    return newSmellQueue;
  }
  static void active_smell(Queue<Smell> smells, Shark[] sharks, int[][] idx_grid) {

    for (int i = 1; i <= M; i++) {
      Shark shark = sharks[i];

      if (!shark.isDead) {
        smells.add(new Smell(shark.row, shark.col, K, i));
        idx_grid[shark.row][shark.col] = i;
      }
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Smell {
  int row;
  int col;
  int time;
  int idx;

  Smell (int row, int col, int time, int idx) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.idx = idx;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  int[][] grid_direction;
  boolean isDead;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
    this.grid_direction = new int[5][5];
    isDead = false;
  }
}
```