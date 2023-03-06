# N - Queen

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|10 초|	128 MB|	80894	|38979|	25392	|47.014%|

## 문제

N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 N이 주어진다. (1 ≤ N < 15)

## 출력

첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

## 예제 입력 1

```
8
```

## 예제 출력 1

```
92
```

## 나의 풀이

해당 문제는 Do it! 자료구조와 함께 배우는 알고리즘 입문에서 나오는 8-Queen을 학습한 후 문제를 풀었다.

해당 문제 풀이를 하면서 중요한 개념이 있다.

1. **가지 뻗기**
2. **분기 한정법**

먼저 가지 뻗기의 경우 재귀 함수에서 반복문을 사용할 때 어떠한 방식으로 작동하는지 이해해야 한다.

다음 코드를 봐보자.

```java
static void set(int i) {
  for(int j = 0; j < 8; j++) {
    pos[i] = j;
    if(i == 7)
      // 모든 열에 있는 Queen을 출력해주는 함수
      print();
    else
      set(i + 1);
  }
}
```

i가 0일 때 부터 시작한다고 보면, 처음에 pos[0] = 0되고, 그 이후 set(0 + 1)이 실행된다. 그러면 pos[1] = 0이 되고 그것이 pos[7] = 0까지 실행된 후 i == 7의 조건을 만족하여 print()함수가 실행된다.

이후, 재귀함수를 빠져나오면서 i = 7에서 i = 6인 재귀함수로 빠져나온다. i = 6인 재귀 함수에서 j가 1이 올라가면서 pos[6] = 1이 되고 다시 set(6 + 1)이 실행된다.

당연히, pos[6] = 1인 상태에서 pos[7]에 0 ~ 7까지 값이 대입되면서 print()함수가 실행될 것이다.

이러한 것이 반복된다. 마치 가지치기 하는 것 처럼 뻗어나가면서 진행하는 것이다. 이것이 **가지 뻗기**이다.

하노이 탑이나 8퀸 문제처럼 문제를 세분하고 세분된 작은 문제의 풀이를 결합해 전체 문제를 풀이하는 기법을 **분할 정복법**이라고도 한다.

cf) **다이나믹 프로그래밍(동적 계획법)**

---
그렇다면 **분기 한정법**은 무엇일까?

분기 한정법의 의미는 분기를 한정시켜서 쓸데없는 시간 낭비를 줄이는 방법을 의미한다.

**백트래킹**에서 부가적으로 발생한 알고리즘 설계 기법이다.
백트래킹이 허용되는 탐색에서 **더 이상 탐색할 필요가 없는 지점을 판단**하는 것이다. 

위의 코드에서 규칙을 하나 추가해보자. 

> 각 행에 퀸을 1개만 배치합니다.

```java
static void set(int i) {
  for(int j = 0; j < 8; j++) {
    if(flag[j] == false) {
      pos[i] = j;
      if(i == 7)
        print();
      else
      {
        flag[j] = true;
        set(i + 1);
        flag[j] = false;
      }
    }
  }
}
```

i가 0일때 부터 살펴보자면,

flag[0] == false를 만족해서 처음에는 post[0] = 0이 실행되고 이후 falg[0] = true가 되면서 set(0 + 1)이 실행될 것이다.

이후 i는 1이 되면서 다음 순서의 재귀함수가 실행될 것인데, 이때 j가 0일떄는 flag[0]은 true인 상태이기 때문에 j=1에서 조건문이 실행될 것이다.

pos[1] = 1 이 되면서 flag[1] = true가 되고 set(1 + 1)이 실행될 것이다. 조건에 맞지 않는 것은 지나치고 조건에 맞을 때만 실행해주는 것을 반복할 것이다.

이렇게 순차적으로 i = 7까지 갔다고 생각해보자. i = 7일때 0 부터 6까지의 행에는 퀸이 놓여있을 것이기 때문에 pos[7] = 7만 실행될 것이다.

이후 i = 6으로 빠져나오면 flag[6] = false로 되고 j = 7이 되면서 flag[7] = true인 상태로 set(6 + 1)이 실행될 것이다. 그러면 pos[7] = 6이 실행되고 다시 빠져나올 것이다.


## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] chess;
  static int N;
  // 같은 행 기준
  static boolean[] flag_a;
  // 기울기가 +인 대각선 기준
  static boolean[] flag_b;
  // 기울기가 -인 대각선 기준
  static boolean[] flag_c;
  // N개의 퀸 배치 성공
  static int counter = 0;
  /*
  static void print() {
    for(int i = 0; i < N; i++) {
      System.out.print(chess[i] + " ");
    }
    System.out.println();
  }
  */

  // i가 열, j가 행
  static void nQueen(int i) {
    for(int j = 0; j < N; j++) {
      // i를 움직이면서 열(Column)마다 하나씩 배치하고 있으므로 열은 신경 X
      // 행(Row)과 두 대각선(Diagonal) 방향만 고려
      if (!flag_a[j] && !flag_b[i + j] && !flag_c[N - 1 + i - j]) {

        // chess[i] = j가 의미하는 것은 i열의 j행에 Queen을 배치하겠다는 의미이다.
        chess[i] = j;

        // 다음 열에 Queen배치
        if(i < N - 1) {
          flag_a[j] = true;
          flag_b[i + j] = true;
          flag_c[N - 1 + i - j] = true;
          nQueen(i + 1);
          flag_a[j] = false;
          flag_b[i + j] = false;
          flag_c[N - 1 + i - j] = false;
        }
        else
          counter++;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // N X N Chess
    N = Integer.parseInt(br.readLine());

    chess = new int[N];
    flag_a = new boolean[N];
    flag_b = new boolean[2 * N -1];
    flag_c = new boolean[2 * N -1];

    nQueen(0);
    System.out.println(counter);
  }
}

```

## 참고할만한 사이트

- [[자료구조&알고리즘] 8퀸 문제 - 자바](https://developer-hm.tistory.com/133)
- [8퀸 문제(8 Queen Problem)](https://velog.io/@jimmy48/8%ED%80%B8-%EB%AC%B8%EC%A0%9C)
- [알고리즘 기법[부분 탐색] - 분기 한정(Branch & Bound)](https://hcr3066.tistory.com/29)
- [[Algorithm] 백트래킹(Backtracking)이란? (feat. 예제포함)](https://fomaios.tistory.com/entry/Algorithm-%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9Backtracking%EC%9D%B4%EB%9E%80)