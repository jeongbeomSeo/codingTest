# 수들의 합 2

**실버 4**

|시간 제한|	메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|0.5 초|	128 MB|	43383	|20840|	14041|	48.296%|

## 문제 

N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 이 수열의 i번째 수부터 j번째 수까지의 합 A[i] + A[i+1] + … + A[j-1] + A[j]가 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)이 주어진다. 다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다. 각각의 A[x]는 30,000을 넘지 않는 자연수이다.

## 출력 

첫째 줄에 경우의 수를 출력한다.

## 예제 입력 1

```
4 2
1 1 1 1
```

## 예제 출력 1

```
3
```

## 예제 입력 2

```
10 5
1 2 3 4 2 5 3 1 1 2
```

## 예제 출력 2

```
3
```

## 코드 

**WA**

```java
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static Long M;
  static int N;
  static int ans;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Long.parseLong(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0 ; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ans = 0;
    bruteForce(arr);

    System.out.println(ans);
  }
  static void bruteForce(int[] arr) {

    int lPtr = 0;
    int rPtr = 0;
    Long sum = (long)0;
    while (lPtr < N) {
      while (rPtr < N && sum > M) sum -= arr[--rPtr];
      while (rPtr < N && sum < M) sum += arr[rPtr++];

      if (sum == M) {
        ans++;
      }
      sum -= arr[lPtr];
      lPtr++;
    }

  }
}
```

> **'=='대신 equals()를 사용하니깐 올바른 답이 나았다.**

이게 골때리는게 

입력으로 

```
3 30
10 20 3
```

이렇게 넣었을 때는 출력이 1로 나오고 

```
3 300
100 200 3
```

을 넣어주면 0이 나온다.

**AC**

```java
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static Long M;
  static int N;
  static int ans;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Long.parseLong(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0 ; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ans = 0;
    bruteForce(arr);

    System.out.println(ans);
  }
  static void bruteForce(int[] arr) {

    int lPtr = 0;
    int rPtr = 0;
    Long sum = (long)0;
    while (lPtr < N) {
      while (rPtr < N && sum > M) sum -= arr[--rPtr];
      while (rPtr < N && sum < M) sum += arr[rPtr++];

      if (sum.equals(M)) {
        ans++;
      }
      sum -= arr[lPtr];
      lPtr++;
    }

  }
}
```