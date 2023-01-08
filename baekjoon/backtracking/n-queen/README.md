# N - Queen

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|10 초|	128 MB|	80894	|38979|	25392	|47.014%|

**해당 문제는 이전에 풀었던 것에서 복습 차원에서 한번 더 푼 것이다.**
[codingTest/algorithm/do-it-algorithm/recursive-function/n-queen at master · jeongbeomSeo/codingTest](https://github.com/jeongbeomSeo/codingTest/tree/master/algorithm/do-it-algorithm/recursive-function/n-queen)

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

## 잘못된 풀이

```java
import java.util.Scanner;

public class Main {
  static int N;
  static int[] chess;
  static boolean[] flag_a;
  static boolean[] flag_b;
  static boolean[] flag_c;

  static int success = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    chess = new int[N];
    flag_a = new boolean[N];
    flag_b = new boolean[2 * N - 1];
    flag_c = new boolean[2 * N - 1];


    backtracking(0);

    System.out.println(success);

  }
  static void backtracking(int ptr) {
    for(int i = 0; i < N; i++) {
      if(!flag_a[i] && !flag_b[i + ptr] && !flag_c[ N - 1 + ptr - i]) {
        chess[ptr] = i;
        if(ptr == N - 1) {
          success++;
        }
        else {
          flag_a[i] = flag_b[i] = flag_c[i] = true;
          backtracking(ptr + 1);
          flag_a[i] = flag_b[i] = flag_c[i] = false;
        }
      }
    }
  }
}
```

위의 코드에서 잘못된 것은 **flag_a, flag_b, flag_c를 처리하는 과정**에서 문제가 있다.

```java
if(!flag_a[i] && !flag_b[i + ptr] && !flag_c[ N - 1 + ptr - i]) {
```

처음 조건문은 이렇게 걸어놓았고 다음 배치하고나서 flag를 설정하는 부분을 봐보자.

```java
flag_a[i] = flag_b[i] = flag_c[i] = true;
```

이와 같이 해놓으면 의미가 없다. 당연히 위와 똑같이 해줘야 의미가 있는 것이다.

## 나의 코드

```java
import java.util.Scanner;

public class Main {
  static int N;
  static int[] chess;
  static boolean[] flag_a;
  static boolean[] flag_b;
  static boolean[] flag_c;

  static int success = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    chess = new int[N];
    flag_a = new boolean[N];
    flag_b = new boolean[2 * N - 1];
    flag_c = new boolean[2 * N - 1];

    backtracking(0);

    System.out.println(success);

  }
  static void backtracking(int ptr) {
    for(int i = 0; i < N; i++) {
      if(!flag_a[i] && !flag_b[i + ptr] && !flag_c[ N - 1 + ptr - i]) {
        chess[ptr] = i;
        if(ptr == N - 1) {
          success++;
        }
        else {
          flag_a[i] = flag_b[i + ptr] = flag_c[ N - 1 + ptr - i] = true;
          backtracking(ptr + 1);
          flag_a[i] = flag_b[i + ptr] = flag_c[ N - 1 + ptr - i] = false;
        }

      }

    }

  }
}
```

## 참고할만한 사이트

- [[자료구조&알고리즘] 8퀸 문제 - 자바](https://developer-hm.tistory.com/133)
- [8퀸 문제(8 Queen Problem)](https://velog.io/@jimmy48/8%ED%80%B8-%EB%AC%B8%EC%A0%9C)
- [알고리즘 기법[부분 탐색] - 분기 한정(Branch & Bound)](https://hcr3066.tistory.com/29)
- [[Algorithm] 백트래킹(Backtracking)이란? (feat. 예제포함)](https://fomaios.tistory.com/entry/Algorithm-%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9Backtracking%EC%9D%B4%EB%9E%80)