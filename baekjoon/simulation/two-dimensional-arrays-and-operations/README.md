# 이차원 배열과 연산 

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|0.5 초 (추가 시간 없음)|	512 MB	|18614|	8743	|5723	|44.037%|

## 문제 

크기가 3×3인 배열 A가 있다. 배열의 인덱스는 1부터 시작한다. 1초가 지날때마다 배열에 연산이 적용된다.

- R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 ≥ 열의 개수인 경우에 적용된다.
- C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수인 경우에 적용된다.
한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다. 그 다음, 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다. 그 다음에는 배열 A에 정렬된 결과를 다시 넣어야 한다. 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.

예를 들어, [3, 1, 1]에는 3이 1번, 1가 2번 등장한다. 따라서, 정렬된 결과는 [3, 1, 1, 2]가 된다. 다시 이 배열에는 3이 1번, 1이 2번, 2가 1번 등장한다. 다시 정렬하면 [2, 1, 3, 1, 1, 2]가 된다.

정렬된 결과를 배열에 다시 넣으면 행 또는 열의 크기가 달라질 수 있다. R 연산이 적용된 경우에는 가장 큰 행을 기준으로 모든 행의 크기가 변하고, C 연산이 적용된 경우에는 가장 큰 열을 기준으로 모든 열의 크기가 변한다. 행 또는 열의 크기가 커진 곳에는 0이 채워진다. 수를 정렬할 때 0은 무시해야 한다. 예를 들어, [3, 2, 0, 0]을 정렬한 결과는 [3, 2]를 정렬한 결과와 같다.

행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.

배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.

## 입력 

첫째 줄에 r, c, k가 주어진다. (1 ≤ r, c, k ≤ 100)

둘째 줄부터 3개의 줄에 배열 A에 들어있는 수가 주어진다. 배열 A에 들어있는 수는 100보다 작거나 같은 자연수이다.

## 출력 

A[r][c]에 들어있는 값이 k가 되기 위한 연산의 최소 시간을 출력한다. 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.

## 예제 입력 1

```
1 2 2
1 2 1
2 1 3
3 3 3
```

## 예제 출력 1

```
0
```

## 예제 입력 2

```
1 2 1
1 2 1
2 1 3
3 3 3
```

## 예제 출력 2

```
1
```

## 예제 입력 3

```
1 2 3
1 2 1
2 1 3
3 3 3
```

## 예제 출력 3

```
2
```

## 힌트

배열 A의 초기값이 아래와 같은 경우를 생각해보자.

```
1 2 1
2 1 3
3 3 3
```

가장 처음에는 행의 개수 ≥ 열의 개수 이기 때문에, R 연산이 적용된다. 편의상 정렬 중간 단계는 (수, 수의 등장 횟수)로 표현한다.

```
1 2 1 → (2, 1), (1, 2)         → 2 1 1 2
2 1 3 → (1, 1), (2, 1), (3, 1) → 1 1 2 1 3 1
3 3 3 → (3, 3)                 → 3 3
```

크기가 가장 큰 행은 2번 행이고, 나머지 행의 뒤에 0을 붙여야 한다.

```
2 1 1 2 0 0
1 1 2 1 3 1
3 3 0 0 0 0
```

다음에 적용되는 연산은 행의 개수 < 열의 개수이기 때문에 C 연산이다. 

```
1 3 1 1 3 1
1 1 1 1 1 1
2 1 2 2 0 0
1 2 1 1 0 0
3 0 0 0 0 0
1 0 0 0 0 0
```

연산이 적용된 결과는 위와 같다.

## 코드 

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int r, c, k;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    r = Integer.parseInt(st.nextToken()) - 1;
    c = Integer.parseInt(st.nextToken()) - 1;
    k = Integer.parseInt(st.nextToken());

    int[][] grid = new int[3][3];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    if (isValidIdx(3, 3) && grid[r][c] == k) System.out.println(0);
    else System.out.println(simulation(grid));

  }
  static int simulation(int[][] grid) {
    int time = 0;

    while (time <= 100) {

      int rowLen = grid.length;
      int colLen = grid[0].length;

      if (isValidIdx(rowLen, colLen) && grid[r][c] == k) break;

      if (rowLen >= colLen) {
        grid = RCalc(grid, rowLen, colLen);
      }
      else {
        grid = CCalc(grid, rowLen, colLen);
      }
      time++;
    }

    if (time > 100) return -1;
    return time;
  }
  static int[][] RCalc(int[][] grid, int rowLen, int colLen) {
    ArrayList<Sorting>[] lists = new ArrayList[rowLen];
    int maxSize = 0;

    for (int i = 0; i < rowLen; i++) {
      lists[i] = new ArrayList<>();
      for (int j = 0; j < colLen; j++) {
        int num = grid[i][j];
        if (num == 0) continue;
        int k;
        for (k = 0; k < lists[i].size(); k++) {
          if (lists[i].get(k).num == num) {
            lists[i].get(k).count++;
            break;
          }
        }
        if (k == lists[i].size()) lists[i].add(new Sorting(num, 1));
      }
      maxSize = Math.max(maxSize, lists[i].size());
    }

    int newColSize = Math.min(maxSize * 2, 100);

    int[][] newGrid = new int[rowLen][newColSize];
    for (int i = 0; i < rowLen; i++) {
      Collections.sort(lists[i]);
      for (int j = 0; j < lists[i].size(); j++) {
        newGrid[i][2 * j] = lists[i].get(j).num;
        newGrid[i][2 * j + 1] = lists[i].get(j).count;
      }
    }
    return newGrid;
  }
  static int[][] CCalc(int[][] grid, int rowLen, int colLen) {
    ArrayList<Sorting>[] lists = new ArrayList[colLen];
    int maxSize = 0;

    for (int j = 0; j < colLen; j++) {
      lists[j] = new ArrayList<>();
      for (int i = 0; i < rowLen; i++) {
        int num = grid[i][j];
        if (num == 0) continue;
        int k;
        for (k = 0; k < lists[j].size(); k++) {
          if (lists[j].get(k).num == num) {
            lists[j].get(k).count++;
            break;
          }
        }
        if (k == lists[j].size()) lists[j].add(new Sorting(num, 1));
      }
      maxSize = Math.max(maxSize, lists[j].size());
    }

    int newRowSize = Math.min(maxSize * 2, 100);
    int[][] newGrid = new int[newRowSize][colLen];
    for (int j = 0; j < colLen; j++) {
      Collections.sort(lists[j]);
      for (int i = 0; i < lists[j].size(); i++) {
        newGrid[2 * i][j] = lists[j].get(i).num;
        newGrid[2 * i + 1][j] = lists[j].get(i).count;
      }
    }

    return newGrid;
  }
  static boolean isValidIdx(int rowLen, int colLen) {
    return r < rowLen && c < colLen;
  }
}
class Sorting implements Comparable<Sorting>{
  int num;
  int count;

  Sorting(int num, int count) {
    this.num = num;
    this.count = count;
  }

  @Override
  public int compareTo(Sorting o) {
    if (this.count > o.count) return 1;
    else if (this.count < o.count) return -1;
    else {
      return this.num - o.num;
    }
  }
}
```