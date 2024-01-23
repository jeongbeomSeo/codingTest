# 소문난 칠공주 

**골드 3**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB	|9711|	5043|	3302	|50.474%|

## 문제 

총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었고, 얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다. 곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며, 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.

위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고, ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다. ‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.

1. 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
2. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
3. 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
4. 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.

여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.

## 입력 

S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.

## 출력 

첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.

## 예제 입력 1

```
YYYYY
SYSYS
YYYYY
YSYYS
YYYYY
```

## 예제 출력 1

```
2
```

## 힌트 

가능한 방법은 아래와 같다.

```
.....    .....
SYSYS    SYSYS
....Y    .Y...
....S    .S...
.....    .....
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
  static int[] ax = {-1, 0, 1, 0};
  static int[] ay = {0, -1, 0, 1};
  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    char[][] grid = new char[5][5];
    for (int i = 0; i < 5; i++) {
      String str = br.readLine();
      for (int j = 0; j < 5; j++) {
        grid[i][j] = str.charAt(j);
      }
    }

    int[] arr = new int[7];
    combination(grid, 0, arr, 0);

    System.out.println(ans);


  }
  static int[][] convertNumToIdx(int[] arr) {
    int[][] member = new int[arr.length][2];
    for (int i = 0; i < arr.length; i++) {
      member[i][0] = arr[i] / 5;
      member[i][1] = arr[i] % 5;
    }
    return member;
  }
  static void combination(char[][] grid, int ptr, int[] arr, int size) {

    if (size == 7) {
      int[][] member = convertNumToIdx(arr);
      if (checkSom(member, grid)) {
        if (bfs(member)) ans++;
      }
    }

    else {
      if (ptr <= 24) {
        arr[size] = ptr;
        combination(grid,ptr + 1, arr, size + 1);
        arr[size] = 0;

        combination(grid, ptr + 1, arr, size);
      }
    }
  }

  static boolean checkSom(int[][] member, char[][] grid) {
    int som = 0;

    for (int i = 0; i < 7; i++) {
      if (grid[member[i][0]][member[i][1]] == 'S') som++;
    }

    if (som >= 4) return true;
    return false;
  }

  static boolean bfs(int[][] member) {
    boolean[] isVisited = new boolean[7];

    Queue<Integer> q = new ArrayDeque<>();
    q.add(0);
    isVisited[0] = true;

    while (!q.isEmpty()) {
      int idx = q.poll();
      int x = member[idx][1];
      int y = member[idx][0];

      for (int i = 0; i < 4; i++) {
        int nextX = x + ax[i];
        int nextY = y + ay[i];

        if (checkIdx(nextX, nextY)) {
          for (int j = 0; j < 7; j++) {
            if (!isVisited[j] && member[j][0] == nextY && member[j][1] == nextX) {
              isVisited[j] = true;
              q.add(j);
            }
          }
        }
      }
    }

    for (int i = 0; i < 7; i++) {
      if (isVisited[i] == false) return false;
    }
    return true;

  }
  static boolean checkIdx(int x, int y) {
    if (x < 0 || y < 0 || x > 4 || y > 4) return false;
    return true;
  }
}
```