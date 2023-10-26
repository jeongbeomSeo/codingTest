# N과 M (2)

**실버 3**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	512 MB	|51612	|38548|	27956|	74.207%|

## 문제

자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
- 고른 수열은 오름차순이어야 한다.

## 입력

첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

## 출력

한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

## 예제 입력 1

```
3 1
```

## 예제 출력 1

```
1
2
3
```

## 예제 입력 2

```
4 2
```

## 예제 출력 2

```
1 2
1 3
1 4
2 3
2 4
3 4
```

## 예제 입력 3

```
1 2 3 4
```

## 나의 코드

**Use Backtracking Algorithm**

```java
import java.util.Scanner;

public class Main {
  static int N;
  static int M;
  static int[] sequence;
  static boolean[] flag;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    sequence = new int[M];
    flag = new boolean[N + 1];

    nandm(0);
  }
  static void nandm(int size) {
    for(int i = 1; i <= N; i++) {
      if(M == 1) {
        System.out.println(i);
      }
      else {
        if(!flag[i]) {
          if(size >= 1) {
            if(sequence[size -1] > i) continue;
          }
          sequence[size] = i;
          if(size == M -1) print();
          else {
            flag[i] = true;
            nandm(size + 1);
            flag[i] = false;
          }
        }
      }
    }
  }
  static void print() {
    for(int i = 0; i < M; i++) {
      System.out.print(sequence[i] + " ");
    }
    System.out.println();
  }
}

```