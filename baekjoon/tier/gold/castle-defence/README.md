# 캐슬 디펜스

**골드 3**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	512 MB	|28607|	10479|	6302|	32.217%|

## 문제 

캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.

성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다.

게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다. 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.

격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

## 입력 

첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.

## 출력 

첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

## 제한 

- 3 ≤ N, M ≤ 15
- 1 ≤ D ≤ 10

## 예제 입력 1

```
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
```

## 예제 출력 1

```
3
```

## 예제 입력 2

```
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
```

## 예제 출력 2

```
2
```

## 예제 입력 3

```
5 5 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
```

## 예제 출력 3

```
5
```

## 예제 입력 4

```
5 5 5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
```

## 예제 출력 4

```
15
```

## 예제 입력 5

```
6 5 1
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
```

## 예제 출력 5

```
9
```

## 예제 입력 6

```
6 5 2
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
```

## 예제 출력 6

```
14
```

## 코드 오류 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {-1, 0, 1};
  static int[] ay = {0, -1, 0};
  static boolean shot = false;
  static int N, M, D;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    int[][] enemy = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        enemy[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    defenceStart(enemy);
    System.out.println(max);

  }
  // 구현되어야 할 함수
  /*
  1. 궁수 배치
  2. 사냥 가능 범위 탐색, 체크 (동시 공격 => 죽은 적 -1로 체크)
  3. 사냥가능 적 횟수, 사냥당한 적 초기화
  4. 턴(총 5탄) 끝난후 모든 적 아래로 이동
   */

  static void defenceStart(int[][] enemy) {
    ArrayList<Integer[]> archerCase = new ArrayList<>();
    setArcher(archerCase);


    for (int tc = 0; tc < archerCase.size(); tc++) {
      int count = 0;
      int arrowOne = archerCase.get(tc)[0];
      int arrowTwo = archerCase.get(tc)[1];
      int arrowThree = archerCase.get(tc)[2];
      int[] arrow = new int[]{arrowOne, arrowTwo, arrowThree};


      for (int turn = 0; turn < 5; turn++) {
        for (int i = 0; i < 3; i++) {
          if (enemyShot(enemy, N - 1, arrow[i])) {
            count++;
          }
        }

        System.out.println(tc +"에서의 "+ turn);
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(enemy[i][j] + " ");
          }
          System.out.println();
        }
        System.out.println();

        // 마지막 라인 0으로 초기화
        for (int i = 0; i < M; i++) {
          enemy[N - 1][i] = 0;
        }

        // 마지막 라인제외하고 전부 내려주기
        boolean noEnemy = true;
        for (int i = N - 2; i >= 0 ; i--) {
          for (int j = 0; j < M; j++) {
            if (enemy[i][j] == -1) enemy[i][j] = 0;
            if (enemy[i][j] == 1) {
              enemy[i + 1][j] = 1;
              enemy[i][j] = 0;
              noEnemy = false;
            }
          }
        }
        if (noEnemy) break;
      }
      max = Math.max(max, count);
    }
  }

  static void setArcher(ArrayList<Integer[]> archerCase) {
    for (int i = 0; i < M; i++) {
      for (int j = i + 1; j < M; j++) {
        for (int k = j + 1; k < M; k++) {
          archerCase.add(new Integer[]{i, j, k});
        }
      }
    }
  }

  static boolean enemyShot(int[][] enemy, int i, int j) {

    Queue<Integer[]> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][M];

    q.add(new Integer[]{i, j, 1});
    isVisited[i][j] = true;

    while (!q.isEmpty()) {
      Integer[] curState = q.poll();
      int curPosI = curState[0];
      int curPosJ = curState[1];
      int curRange = curState[2];

      if (curRange > D) continue;
      if (enemy[curPosI][curPosJ] == 1)  {
        enemy[curPosI][curPosJ] = -1;
        return true;
      }
      if (enemy[curPosI][curPosJ] == -1) return false;

      for (int mv = 0; mv < 3; mv++) {
        int nextI = curPosI + ay[mv];
        int nextJ = curPosJ + ax[mv];
        if (checkIdx(nextI, nextJ) && !isVisited[nextI][nextJ]) {
          q.add(new Integer[]{nextI, nextJ, curRange + 1});
          isVisited[nextI][nextJ] = true;
        }
      }
    }

    return false;
  }
  static boolean checkIdx(int i, int j) {
    if (i < 0 || j < 0 || i > N - 1 || j > M - 1) return false;
    return true;
  }
}
```

시물레이션에서 가장 중요한 것은 turn마다 원래 상태로 초기화 시켜주는 작업이다.

일반적으로 그냥 배열을 쓰는 경우 오류가 발생할 가능성이 크다.

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {-1, 0, 1};
  static int[] ay = {0, -1, 0};
  static boolean shot = false;
  static int N, M, D;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    int[][] enemy = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        enemy[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    defenceStart(enemy);
    System.out.println(max);

  }
  // 구현되어야 할 함수
  /*
  1. 궁수 배치
  2. 사냥 가능 범위 탐색, 체크 (동시 공격 => 죽은 적 -1로 체크)
  3. 사냥가능 적 횟수, 사냥당한 적 초기화
  4. 턴(총 5탄) 끝난후 모든 적 아래로 이동
   */

  static void defenceStart(int[][] enemy) {
    ArrayList<Integer[]> archerCase = new ArrayList<>();
    setArcher(archerCase);


    for (int tc = 0; tc < archerCase.size(); tc++) {
      int count = 0;
      int arrowOne = archerCase.get(tc)[0];
      int arrowTwo = archerCase.get(tc)[1];
      int arrowThree = archerCase.get(tc)[2];
      int[] arrow = new int[]{arrowOne, arrowTwo, arrowThree};

      int[][] thisTurnEnemy = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M ;j++) {
          thisTurnEnemy[i][j] = enemy[i][j];
        }
      }

      for (int turn = 0; turn < 5; turn++) {
        for (int i = 0; i < 3; i++) {
          if (enemyShot(thisTurnEnemy, N - 1, arrow[i])) {
            count++;
          }
        }

        // 마지막 라인 0으로 초기화
        for (int i = 0; i < M; i++) {
          thisTurnEnemy[N - 1][i] = 0;
        }

        // 마지막 라인제외하고 전부 내려주기
        boolean noEnemy = true;
        for (int i = N - 2; i >= 0 ; i--) {
          for (int j = 0; j < M; j++) {
            if (thisTurnEnemy[i][j] == -1) thisTurnEnemy[i][j] = 0;
            if (thisTurnEnemy[i][j] == 1) {
              thisTurnEnemy[i + 1][j] = 1;
              thisTurnEnemy[i][j] = 0;
              noEnemy = false;
            }
          }
        }
        if (noEnemy) break;
      }
      max = Math.max(max, count);
    }
  }

  static void setArcher(ArrayList<Integer[]> archerCase) {
    for (int i = 0; i < M; i++) {
      for (int j = i + 1; j < M; j++) {
        for (int k = j + 1; k < M; k++) {
          archerCase.add(new Integer[]{i, j, k});
        }
      }
    }
  }

  static boolean enemyShot(int[][] enemy, int i, int j) {

    Queue<Integer[]> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][M];

    q.add(new Integer[]{i, j, 1});
    isVisited[i][j] = true;

    while (!q.isEmpty()) {
      Integer[] curState = q.poll();
      int curPosI = curState[0];
      int curPosJ = curState[1];
      int curRange = curState[2];

      if (curRange > D) continue;
      if (enemy[curPosI][curPosJ] == 1)  {
        enemy[curPosI][curPosJ] = -1;
        return true;
      }
      if (enemy[curPosI][curPosJ] == -1) return false;

      for (int mv = 0; mv < 3; mv++) {
        int nextI = curPosI + ay[mv];
        int nextJ = curPosJ + ax[mv];
        if (checkIdx(nextI, nextJ) && !isVisited[nextI][nextJ]) {
          q.add(new Integer[]{nextI, nextJ, curRange + 1});
          isVisited[nextI][nextJ] = true;
        }
      }
    }

    return false;
  }
  static boolean checkIdx(int i, int j) {
    if (i < 0 || j < 0 || i > N - 1 || j > M - 1) return false;
    return true;
  }
}
```

해당 코드로 해도 예제 입력 5 와 6에서 출력값이 8과 12로 나온다.

## 코드

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
  static int[] ax = {-1, 0, 1};
  static int[] ay = {0, -1, 0};
  static boolean shot = false;
  static int N, M, D;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    int[][] enemy = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        enemy[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    defenceStart(enemy);
    System.out.println(max);

  }
  // 구현되어야 할 함수
  /*
  1. 궁수 배치
  2. 사냥 가능 범위 탐색, 체크 (동시 공격 => 죽은 적 -1로 체크)
  3. 사냥가능 적 횟수, 사냥당한 적 초기화
  4. 턴(총 5탄) 끝난후 모든 적 아래로 이동
   */

  static void defenceStart(int[][] enemy) {
    ArrayList<Integer[]> archerCase = new ArrayList<>();
    setArcher(archerCase);


    for (int tc = 0; tc < archerCase.size(); tc++) {
      int count = 0;
      int arrowOne = archerCase.get(tc)[0];
      int arrowTwo = archerCase.get(tc)[1];
      int arrowThree = archerCase.get(tc)[2];
      int[] arrow = new int[]{arrowOne, arrowTwo, arrowThree};

      int[][] thisTurnEnemy = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M ;j++) {
          thisTurnEnemy[i][j] = enemy[i][j];
        }
      }

      for (int turn = 0; turn < N; turn++) {
        for (int i = 0; i < 3; i++) {
          if (enemyShot(thisTurnEnemy, N - 1, arrow[i])) {
            count++;
          }
        }
        /*
        // 사냥한 후 상태 체크
        System.out.println(tc+"에서의 "+ turn+"번째 사냥한 후");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(thisTurnEnemy[i][j]+" ");
          }
          System.out.println();
        }
        System.out.println();
       */

        // 마지막 라인 0으로 초기화
        for (int i = 0; i < M; i++) {
          thisTurnEnemy[N - 1][i] = 0;
        }

        // 마지막 라인제외하고 전부 내려주기
        boolean noEnemy = true;
        for (int i = N - 2; i >= 0 ; i--) {
          for (int j = 0; j < M; j++) {
            if (thisTurnEnemy[i][j] == -1) thisTurnEnemy[i][j] = 0;
            if (thisTurnEnemy[i][j] == 1) {
              thisTurnEnemy[i + 1][j] = 1;
              thisTurnEnemy[i][j] = 0;
              noEnemy = false;
            }
          }
        }
        if (noEnemy) break;
        /*
        System.out.println(turn+"번째 뒷 처리후 상태");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(thisTurnEnemy[i][j]+" ");
          }
          System.out.println();
        }
        System.out.println("현재 사냥 횟수: "+count);
        */
      }

      max = Math.max(max, count);
    }
  }

  static void setArcher(ArrayList<Integer[]> archerCase) {
    for (int i = 0; i < M; i++) {
      for (int j = i + 1; j < M; j++) {
        for (int k = j + 1; k < M; k++) {
          archerCase.add(new Integer[]{i, j, k});
        }
      }
    }
  }

  static boolean enemyShot(int[][] enemy, int i, int j) {

    Queue<Integer[]> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][M];

    q.add(new Integer[]{i, j, 1});
    isVisited[i][j] = true;

    while (!q.isEmpty()) {
      Integer[] curState = q.poll();
      int curPosI = curState[0];
      int curPosJ = curState[1];
      int curRange = curState[2];

      if (curRange > D) continue;
      if (enemy[curPosI][curPosJ] == 1)  {
        enemy[curPosI][curPosJ] = -1;
        return true;
      }
      if (enemy[curPosI][curPosJ] == -1) return false;

      for (int mv = 0; mv < 3; mv++) {
        int nextI = curPosI + ay[mv];
        int nextJ = curPosJ + ax[mv];
        if (checkIdx(nextI, nextJ) && !isVisited[nextI][nextJ]) {
          q.add(new Integer[]{nextI, nextJ, curRange + 1});
          isVisited[nextI][nextJ] = true;
        }
      }
    }

    return false;
  }
  static boolean checkIdx(int i, int j) {
    if (i < 0 || j < 0 || i > N - 1 || j > M - 1) return false;
    return true;
  }
}
```