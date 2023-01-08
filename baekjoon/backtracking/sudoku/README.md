# 스도쿠

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB|	77819|	22667|	14236	|26.891%|

## 문제

스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다. 이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.

![sudoku1](./sudoku1.png)

나머지 빈 칸을 채우는 방식은 다음과 같다.

1. 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
2. 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.

위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9까지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.

![sudoku2](./sudoku2.png)

또한 위쪽 가운데 위치한 3x3 정사각형의 경우에는 3을 제외한 나머지 숫자들이 이미 쓰여있으므로 가운데 빈 칸에는 3이 들어가야 한다.

![sudoku3](./sudoku3.png)

이와 같이 빈 칸을 차례로 채워 가면 다음과 같은 최종 결과를 얻을 수 있다.

![sudoku4](./sudoku4.png)

게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.

## 입력

아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다. 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.

## 출력

모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.

스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.

## 제한

- 12095번 문제에 있는 소스로 풀 수 있는 입력만 주어진다.
  - C++14: 80ms
  - Java: 292ms
  - PyPy3: 1172ms

## 예제 입력 1

```
0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0
```

## 예제 출력 1

```
1 3 5 4 6 9 2 7 8
7 8 2 1 3 5 6 4 9
4 6 9 2 7 8 1 3 5
3 2 1 5 4 6 8 9 7
8 7 4 9 1 3 5 2 6
5 9 6 8 2 7 4 1 3
9 1 7 6 5 2 3 8 4
6 4 3 7 8 1 9 5 2
2 5 8 3 9 4 7 6 1
```

## 잘못된 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static ArrayList<Integer[]> zero = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] sudoku = new int[9][9];

    for (int i = 0; i < 9; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 9; j++) {
        sudoku[i][j] = Integer.parseInt(st.nextToken());
        if(sudoku[i][j] == 0) zero.add(new Integer[]{i, j});
      }
    }

    solve(sudoku);

    for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print(sudoku[i][j] + " ");
      }
      System.out.println();
    }
  }

  static void solve(int[][] sudoku) {
    int i = 0;
    while (!zero.isEmpty()) {
      Integer[] index = zero.get(i);
      if(checkRow(sudoku, index[0], index[1])) zero.remove(i);
      else {
        if(checkColumn(sudoku, index[0], index[1])) zero.remove(i);
        else {
          if(checkBox(sudoku, index[0], index[1])) zero.remove(i);
            // 입력 불가능한 경우 나중에 다시 풀기
          else {
            if(i == zero.size() -1) i = 0;
            else i++;
          }
        }
      }
    }
  }
  static boolean checkRow(int[][] sudoku, int x, int y) {
    // 숫자(value)는 1 ~ 9까지 있기 때문에 10까지 index 설정
    boolean[] row = new boolean[10];
    for(int i = 0; i < sudoku[x].length; i++) {
      // target지점은 Pass
      if(i == y) continue;

      // target지점 외에 하나라도 0이 나오면 값을 맞출 수 없다.
      int value = sudoku[x][i];
      // 백트래킹
      if(value == 0) return false;
      else row[value] = true;
    }
    for(int i = 1; i < row.length; i++) {
      if(!row[i]) {
        sudoku[x][y] = i;
        return true;
      }
    }
    return false;
  }
  static boolean checkColumn(int[][] sudoku, int x, int y) {
    boolean[] column = new boolean[10];
    for(int i = 0 ; i < sudoku.length; i++) {
      // target지점은 Pass
      if(i == x) continue;

      int value = sudoku[i][y];
      if(value == 0) return false;
      else column[value] = true;
    }
    for(int i = 1; i < column.length; i++) {
      if(!column[i]) {
        sudoku[x][y] = i;
        return true;
      }
    }
    return false;
  }

  static boolean checkBox(int[][] sudoku, int x, int y) {
    boolean[] box = new boolean[10];
    int boxStartX = (x / 3) * 3;
    int boxStartY = (y / 3) * 3;
    for(int i = boxStartX; i < boxStartX + 3; i++) {
      for(int j = boxStartY; j < boxStartY + 3; j++) {
        if(i == x && j == y) continue;

        int value = sudoku[i][j];
        if(value == 0) return false;
        else box[value] = true;
      }
    }
    for(int i = 1; i < box.length; i++) {
      if(!box[i]) {
        sudoku[x][y] = i;
        return true;
      }
    }
    return false;
  }
}
```

해당 풀이의 문제점은 스도쿠를 푸는 방식을 잘 못 이해하고 있다는 것이 문제였다.

스도쿠는 행, 열, 박스 이렇게 고려해서 숫자가 하나로 딱 떨어지는 것이 아니다.

가령 숫자를 임의로 넣어보고 맞춰봐야 할 때도 있다.

위의 풀이는 행과 열 혹은 박스안의 숫자를 고려할 때 무조건 target숫자 외에 전부 채워져 있는 경우가 존재한다는 것을 가정하고 한 풀이다.

스도쿠에는 정답도 여러 가지인 경우가 존재하는 것처럼 이와 같이 풀면 잘못된 방식이다.

## 대표적 반례

**TEST CASE #1**

```
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
```

**SAMPLE ANSWER #1**

```
1 2 3 4 5 6 7 8 9
4 5 6 7 8 9 1 2 3
7 8 9 1 2 3 4 5 6
2 1 4 3 6 5 8 9 7
3 6 5 8 9 7 2 1 4
8 9 7 2 1 4 3 6 5
5 3 1 6 4 2 9 7 8
6 4 2 9 7 8 5 3 1
9 7 8 5 3 1 6 4 2
```

**TEST CASE #2**

```
0 2 0 9 0 5 0 0 0
5 9 0 0 3 0 2 0 0
7 0 0 6 0 2 0 0 5
0 0 9 3 5 0 4 6 0
0 5 4 0 0 0 7 8 0
0 8 3 0 2 7 5 0 0
8 0 0 2 0 9 0 0 4
0 0 5 0 4 0 0 2 6
0 0 0 5 0 3 0 7 0
```

**SAMPLE ANSWER #2**

```
3 2 1 9 7 5 6 4 8
5 9 6 8 3 4 2 1 7
7 4 8 6 1 2 9 3 5
1 7 9 3 5 8 4 6 2
2 5 4 1 9 6 7 8 3
6 8 3 4 2 7 5 9 1
8 1 7 2 6 9 3 5 4
9 3 5 7 4 1 8 2 6
4 6 2 5 8 3 1 7 9
```

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Zero {
  int y;
  int x;

  Zero(int y, int x) {
    this.y = y;
    this.x = x;
  }
}
public class Main {
  static int[][] sudoku;
  static ArrayList<Zero> zero;
  static boolean success = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    zero = new ArrayList<>();
    sudoku = new int[9][9];

    for(int i = 0; i < 9; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 9; j++) {
        sudoku[i][j] = Integer.parseInt(st.nextToken());
        if(sudoku[i][j] == 0) zero.add(new Zero(i, j));
      }
    }

    backtracking(0);
    for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print(sudoku[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void backtracking(int ptr) {
    int x = zero.get(ptr).x;
    int y = zero.get(ptr).y;

    for(int num = 1; num <= 9; num++) {

      if(check(x ,y, num)) {
        sudoku[y][x] = num;
        if(ptr == zero.size() - 1) {
          success = true;
          return;
        }
        else
          backtracking(ptr + 1);
      }
      if(success) return;
      sudoku[y][x] = 0;
    }
  }
  static boolean check(int x, int y, int num) {
    // 같은 행 열 확인
    for(int i = 0; i < 9; i++) {
      if(i != y) if(sudoku[i][x] == num) return false;
      if(i != x) if(sudoku[y][i] == num) return false;
    }
    int boxStartX = (x / 3) * 3;
    int boxStartY = (y / 3) * 3;
    for(int i = boxStartY; i < boxStartY + 3; i++) {
      for(int j = boxStartX; j < boxStartX + 3; j++) {
        if(i != y && j != x) if(sudoku[i][j] == num) return false;
      }
    }
    return true;
  }
}
```

## 참고한 사이트

- [[백준 / BOJ] - 2580번 스도쿠 C++ 풀이 및 반례 모음 :: Just Give Me The Code](https://cryptosalamander.tistory.com/59)
- [[백준2580] 스도쿠 / Java — 개발하는 사막여우](https://dev-note-97.tistory.com/59)