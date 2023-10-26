# 비숍

**골드 1**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|10 초	|128 MB	|21298	|5226	|3610|	23.979%|

## 문제 

서양 장기인 체스에는 대각선 방향으로 움직일 수 있는 비숍(bishop)이 있다. < 그림 1 >과 같은 정사각형 체스판 위에 B라고 표시된 곳에 비숍이 있을 때 비숍은 대각선 방향으로 움직여 O로 표시된 칸에 있는 다른 말을 잡을 수 있다.

![](https://upload.acmicpc.net/c3f4ac55-3e37-4bed-a381-7d407b2f9b4f/-/preview/)

<그림 1>

그런데 체스판 위에는 비숍이 놓일 수 없는 곳이 있다. < 그림 2 >에서 체스판에 색칠된 부분은 비숍이 놓일 수 없다고 하자. 이와 같은 체스판에 서로가 서로를 잡을 수 없도록 하면서 비숍을 놓는다면 < 그림 3 >과 같이 최대 7개의 비숍을 놓을 수 있다.  색칠된 부분에는 비숍이 놓일 수 없지만 지나갈 수는 있다.

![](https://upload.acmicpc.net/3d44f5a2-bd28-41bd-9959-0f8f8bfbff3f/-/preview/)

<그림 2>

![](https://upload.acmicpc.net/49405f78-09c9-4220-8687-ec3269dd6c1b/-/preview/)

<그림 3>

정사각형 체스판의 한 변에 놓인 칸의 개수를 체스판의 크기라고 한다. 체스판의 크기와 체스판 각 칸에 비숍을 놓을 수 있는지 없는지에 대한 정보가 주어질 때, 서로가 서로를 잡을 수 없는 위치에 놓을 수 있는 비숍의 최대 개수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 체스판의 크기가 주어진다. 체스판의 크기는 10이하의 자연수이다. 둘째 줄부터 아래의 예와 같이 체스판의 각 칸에 비숍을 놓을 수 있는지 없는지에 대한 정보가 체스판 한 줄 단위로 한 줄씩 주어진다. 비숍을 놓을 수 있는 곳에는 1, 비숍을 놓을 수 없는 곳에는 0이 빈칸을 사이에 두고 주어진다.

## 출력 

첫째 줄에 주어진 체스판 위에 놓을 수 있는 비숍의 최대 개수를 출력한다.

## 예제 입력 1

```
5
1 1 0 1 1
0 1 0 0 0
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1
```

## 예제 출력 1

```
7
```

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int maxBishop = Integer.MIN_VALUE;

  static boolean[] flag_BR;
  static int[][] chess;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    chess = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        chess[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    flag_BR = new boolean[2 * N - 1];

    bishop(N, 0, 0, 0);

    System.out.println(maxBishop);

  }
  // 기울기 + 선분(line)을 기준으로 백트래킹
  // 선분 기준으로 맨 오른쪽 위의 값부터 대각선을 내려가면서 놓을 수 있는 부분 탐색
  static void bishop(int N, int line, int ptr, int count) {

    if (line == 2 * N - 1) {
      maxBishop = Math.max(maxBishop, count);
      return;
    }

    // line 의 값이 N을 넘어가면 i값이 늘어나기 시작하고, 열값은 N - 1부터 시작 고정
    int i = (line >= N) ? (line % (N - 1) != 0 ? line % (N - 1) : N - 1) : 0;
    boolean isValid = false;
    int nextPtr = (line >= N - 1) ? N - 1: line + 1;

    // 놓을 수 있는 곳이 없다면 그냥 넘어가기
    for (; i >= 0 && ptr >= 0 && i < N && ptr < N; ptr--, i++) {
      isValid = false;
      // 놓을 수 있는 자리와 기울기 -인 대각선에 놓여있는 비숍 체크
      if (chess[i][ptr] == 1 && !flag_BR[N - 1 - i + ptr]) {
        flag_BR[N - 1 - i + ptr] = true;
        bishop(N, line + 1, nextPtr, count + 1);
        flag_BR[N - 1 - i + ptr] = false;
        isValid = true;
      }
    }
    if (!isValid) bishop(N, line + 1, nextPtr, count);
  }
}
```