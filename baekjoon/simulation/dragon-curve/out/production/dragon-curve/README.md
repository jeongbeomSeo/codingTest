# 드래곤 커브

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|22908	|13092|	8906	|54.823%|

## 문제 

드래곤 커브는 다음과 같은 세 가지 속성으로 이루어져 있으며, 이차원 좌표 평면 위에서 정의된다. 좌표 평면의 x축은 → 방향, y축은 ↓ 방향이다.

1. 시작 점
2. 시작 방향
3. 세대

0세대 드래곤 커브는 아래 그림과 같은 길이가 1인 선분이다. 아래 그림은 (0, 0)에서 시작하고, 시작 방향은 오른쪽인 0세대 드래곤 커브이다.

![](http://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/1.png)

1세대 드래곤 커브는 0세대 드래곤 커브를 끝 점을 기준으로 시계 방향으로 90도 회전시킨 다음 0세대 드래곤 커브의 끝 점에 붙인 것이다. 끝 점이란 시작 점에서 선분을 타고 이동했을 때, 가장 먼 거리에 있는 점을 의미한다.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/2.png)

2세대 드래곤 커브도 1세대를 만든 방법을 이용해서 만들 수 있다. (파란색 선분은 새로 추가된 선분을 나타낸다)

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/3.png)

3세대 드래곤 커브도 2세대 드래곤 커브를 이용해 만들 수 있다. 아래 그림은 3세대 드래곤 커브이다.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/4.png)

즉, K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다.

크기가 100×100인 격자 위에 드래곤 커브가 N개 있다. 이때, 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구하는 프로그램을 작성하시오. 격자의 좌표는 (x, y)로 나타내며, 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표이다.

## 입력 

첫째 줄에 드래곤 커브의 개수 N(1 ≤ N ≤ 20)이 주어진다. 둘째 줄부터 N개의 줄에는 드래곤 커브의 정보가 주어진다. 드래곤 커브의 정보는 네 정수 x, y, d, g로 이루어져 있다. x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다. (0 ≤ x, y ≤ 100, 0 ≤ d ≤ 3, 0 ≤ g ≤ 10)

입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다. 드래곤 커브는 서로 겹칠 수 있다.

방향은 0, 1, 2, 3 중 하나이고, 다음을 의미한다.

- 0: x좌표가 증가하는 방향 (→)
- 1: y좌표가 감소하는 방향 (↑)
- 2: x좌표가 감소하는 방향 (←)
- 3: y좌표가 증가하는 방향 (↓)

## 출력 

첫째 줄에 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 출력한다.

## 예제 입력 1

```
3
3 3 0 1
4 2 1 3
4 2 2 1
```

## 예제 출력 1

```
4
```

## 예제 입력 2

```
4
3 3 0 1
4 2 1 3
4 2 2 1
2 7 3 4
```

## 예제 출력 2

```
11
```

## 예제 입력 3

```
10
5 5 0 0
5 6 0 0
5 7 0 0
5 8 0 0
5 9 0 0
6 5 0 0
6 6 0 0
6 7 0 0
6 8 0 0
6 9 0 0
```

## 예제 출력 3

```
8
```

## 예제 입력 4

```
4
50 50 0 10
50 50 1 10
50 50 2 10
50 50 3 10
```

## 예제 출력 4

```
1992
```

## 힌트

|예제 1| 예제 2                                                                                 |
|---|--------------------------------------------------------------------------------------|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/ex1.png)| ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/ex2.png) |

## 코드 

**잘못된 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  // 동, 북, 서, 남
  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] curve = new int[N][4];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int col = Integer.parseInt(st.nextToken());
      int row = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int level = Integer.parseInt(st.nextToken());
      curve[i][0] = row;
      curve[i][1] = col;
      curve[i][2] = direction;
      curve[i][3] = level;
    }

    System.out.println(simulation(curve, N));
  }
  static int simulation(int[][] curve, int N) {

    boolean[][] isVisited = new boolean[101][101];

    int count = 0;
    for (int i = 0; i < N; i++) {
      int[][] coordinates = dragonCurve(curve[i]);

      for (int j = 0; j < coordinates.length; j++) {
        int row = coordinates[j][0];
        int col = coordinates[j][1];
        if (isValidIdx(row, col)) isVisited[row][col] = true;
      }
    }

    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (isVisited[i][j] && isVisited[i + 1][j] && isVisited[i][j + 1] && isVisited[i + 1][j + 1]) count++;
      }
    }

    return count;

  }
  static int[][] dragonCurve (int[] curve) {
    int curLevel = 1;

    int[][] coordinates = new int[2][2];
    coordinates[0][0] = curve[0];
    coordinates[0][1] = curve[1];
    coordinates[1][0] = curve[0] + dr[curve[2]];
    coordinates[1][1] = curve[1] + dc[curve[2]];

    while (curLevel <= curve[3]) {

      int beforeLength = coordinates.length;
      int lastRow = coordinates[beforeLength - 1][0];
      int lastCol = coordinates[beforeLength - 1][1];

      int[][] temp = new int[2 * beforeLength - 1][2];
      for (int i = 0; i < beforeLength; i++) {
        temp[i][0] = coordinates[i][0];
        temp[i][1] = coordinates[i][1];
      }
      for (int i = 0; i < beforeLength - 1; i++) {
          temp[beforeLength + i][0] = lastRow - (coordinates[i][1] - lastCol);
          temp[beforeLength + i][1] = lastCol + (coordinates[i][0]- lastRow);
      }
      coordinates = temp;
      curLevel++;
    }
    return coordinates;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= 100 && col <= 100;
  }
}
```

해당 문제는 풀이를 참고해서 문제를 풀었다. 좌표축에서 회전하는 문제에 취약한 것을 알아서 문제를 찾아보고 연습해 보기로 했고, 해당 문제도 추가적으로 익숙해질 때까지 풀자.

일단 **해당 문제 풀이의 핵심**은 끝 점을 기준으로 90도 시계방향 회전이 이전 단계까지 진행했던 순서를 거꾸로 진행하면서 각 순서에서의 방향을 반시계 방향으로 90도 회전한 방향과 일치하는 것이다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  // 동, 북, 서, 남
  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Curve[] curves = new Curve[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int col = Integer.parseInt(st.nextToken());
      int row = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int level = Integer.parseInt(st.nextToken());

      curves[i] = new Curve(row, col, direction, level);
    }

    System.out.println(simulation(curves, N));
  }
  static int simulation(Curve[] curves, int N) {

    boolean[][] isVisited = new boolean[101][101];

    for (int i = 0; i < N; i++) {
      Curve curve = curves[i];

      ArrayList<Integer[]> coordinates = dragonCurve(curve);

      for (int j = 0; j < coordinates.size(); j++) {
        int row = coordinates.get(j)[0];
        int col = coordinates.get(j)[1];

        if (isValidIdx(row, col)) isVisited[row][col] = true;
      }
    }

    int count = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (isVisited[i][j] && isVisited[i + 1][j] && isVisited[i][j + 1] && isVisited[i + 1][j + 1]) count++;
      }
    }
    return count;
  }
  static ArrayList<Integer[]> dragonCurve(Curve curve) {
    ArrayList<Integer[]> coordinates = new ArrayList<>();

    coordinates.add(new Integer[]{curve.row, curve.col});
    int row = curve.row + dr[curve.direction];
    int col = curve.col + dc[curve.direction];
    coordinates.add(new Integer[]{row, col});
    Stack<Integer> stack = new Stack<>();
    stack.add(curve.direction);
    int level = 1;
    while (level++ <= curve.level) {
      int size = stack.size();
      for (int i = size - 1; i >= 0; i--) {
        int d = (stack.get(i) + 1) % 4;

        row += dr[d];
        col += dc[d];
        coordinates.add(new Integer[]{row, col});
        stack.add(d);
      }
    }
    return coordinates;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= 100 && col <= 100;
  }
}

class Curve {
  int row;
  int col;
  int direction;
  int level;

  Curve(int row, int col, int direction, int level) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.level = level;
  }
}
```

> **참고한 사이트**
> 
> [백준 15685 드래곤 커브](https://velog.io/@skyepodium/%EB%B0%B1%EC%A4%80-15685-%EB%93%9C%EB%9E%98%EA%B3%A4-%EC%BB%A4%EB%B8%8C)