# 타일 채우기 3

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|8137	|2049	|1601	|25.461%|

## 문제 

2×N 크기의 벽을 2×1, 1×2, 1×1 크기의 타일로 채우는 경우의 수를 구해보자.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 1,000,000)이 주어진다.

## 출력 

첫째 줄에 경우의 수를 1,000,000,007로 나눈 나머지를 출력한다.

## 예제 입력 1

```
1
```

## 예제 출력 1

```
2
```

## 예제 입력 2

```
2
```

## 예제 출력 2

```
7
```

## 예제 입력 3

```
3
```

## 예제 출력 3

```
22
```

## 코드 

**AC**

```java
import java.util.Scanner;

public class Main {
  static int MOD = 1000000007;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    long[][] dp = new long[1000001][2];

    dp[0][0] = 1;
    dp[0][1] = dp[0][0] * 2;
    dp[1][0] = 2;
    dp[1][1] = 2 * dp[1][0] + dp[0][1];
    dp[2][0] = 7;
    dp[2][1] = 2 * dp[2][0] + dp[1][1];



    for (int i = 3; i <= N; i++) {
      long result = (2 * dp[i - 1][0] + 3 * dp[i - 2][0]) % MOD;
      dp[i][0] = (result + dp[i - 3][1]) % MOD;
      dp[i][1] = (2 * dp[i][0] + dp[i - 1][1]) % MOD;
    }

    System.out.println(dp[N][0]);
  }
}
```

**AC**

```java
import java.util.Scanner;

public class Main {
  static int MOD = 1000000007;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    long[][] dp = new long[1000001][2];

    dp[0][0] = 0;
    dp[1][0] = 2;
    dp[2][0] = 7;
    dp[2][1] = 1;

    for (int i = 3; i <= N; i++) {
      dp[i][1] = (dp[i - 3][0] + dp[i - 1][1]) % MOD;

      dp[i][0] = (2 * dp[i - 1][0] + 3 * dp[i - 2][0] + 2 * dp[i][1]) % MOD;
    }

    System.out.println(dp[N][0]);
  }
}
```

**매우 주의**
MOD가 10억이기 때문에 3을 곱하면 21억이 넘어가기 때문에 int형 배열을 사용하면 안됩니다! 
