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