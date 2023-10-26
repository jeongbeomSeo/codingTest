# 가장 긴 증가하는 부분 수열

**실버 2**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|132969	|52533	|34647	|37.455%|

## 문제

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {**10**, **20**, 10, **30,** 20, **50**} 이고, 길이는 4이다.

## 입력 

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

## 예제 입력 1

```
6
10 20 10 30 20 50
```

## 예제 출력 1

```
4
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];
    int[] dp = new int[N];

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(lis_dp(dp, arr, N));
  }
  static int lis_dp(int[] dp, int[] arr, int N) {
    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j])
          dp[i] = Math.max(dp[i], dp[j] + 1);
      }
    }

    int max = dp[0];
    for (int i = 1; i < N; i++) {
      if (max < dp[i]) max = dp[i];
    }
    return max;
  }
}
```