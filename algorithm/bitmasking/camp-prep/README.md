# 캠프 준비 

**골드 5**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|2867	|1991	|1565|	70.116%|

## 문제 

알고리즘 캠프를 열려면 많은 준비가 필요하다. 그 중 가장 중요한 것은 문제이다. 오늘은 백준이를 도와 알고리즘 캠프에 사용할 문제를 고르려고 한다.

백준이는 문제를 N개 가지고 있고, 모든 문제의 난이도를 정수로 수치화했다. i번째 문제의 난이도는 Ai이다.

캠프에 사용할 문제는 두 문제 이상이어야 한다. 문제가 너무 어려우면 학생들이 멘붕에 빠지고, 문제가 너무 쉬우면 학생들이 실망에 빠지게 된다. 따라서, 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다. 또, 다양한 문제를 경험해보기 위해 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.

캠프에 사용할 문제를 고르는 방법의 수를 구해보자.

## 입력 

첫째 줄에 N, L, R, X가 주어진다.

둘째 줄에는 문제의 난이도 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다.

## 출력 

캠프에 사용할 문제를 고르는 방법의 수를 출력한다.

## 제한 

- 1 ≤ N ≤ 15
- 1 ≤ L ≤ R ≤ 10<sup>9</sup>
- 1 ≤ X ≤ 10<sup>6</sup>
- 1 ≤ A<sub>i</sub> ≤ 10<sup>6</sup>

## 예제 입력 1

```
3 5 6 1
1 2 3
```

## 예제 출력 1

```
2
```
2번, 3번 문제를 고르는 방법, 모든 문제를 고르는 방법이 가능하다.

## 예제 입력 2

```
4 40 50 10
10 20 30 25
```

## 예제 출력 2

```
2
```

## 예제 출력 2

```
2
```

난이도가 10, 30인 문제를 고르거나, 20, 30인 문제를 고르면 된다.

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());

    int[] levels = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      levels[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(levels, N, L, R, X));
  }
  private static int queryResult(int[] levels, int N, int L, int R, int X) {

    int count = 0;
    for (int i = 0; i < (1 << N); i++) {
      if (Integer.bitCount(i) >= 2) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int j = 0; j < N; j++) {
          if ((i & (1 << j)) != 0) {
            min = Math.min(min, levels[j]);
            max = Math.max(max, levels[j]);
            sum += levels[j];
          }
        }
        int diff = max - min;
        if (sum < L || sum > R) continue;
        if (diff < X) continue;

        count++;
      }
    }
    return count;
  }
}
```