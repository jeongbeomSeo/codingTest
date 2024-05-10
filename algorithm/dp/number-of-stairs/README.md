# 계단 수

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	12739	|6646|	5062|	51.574%|

45656이란 수를 보자.

이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.

N이 주어질 때, 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. 0으로 시작하는 수는 계단수가 아니다.

## 입력 

첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

## 출력 

첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

## 예제 입력 1

```
10
```

## 예제 출력 1

```
1
```

## 힌트

참고로, N=1일때부터, N=40일 때 까지 답을 모두 더하면 126461847755이 나온다.

## 나의 코드

**비트필드를 사용하지 않은 풀이**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /**
         * ele1: 현재 계단 위치(높이)
         * ele2: 현재 계단 값
         * ele3: 현재까지 방문한 최대 계단 값(Max)
         * ele4: 현재까지 방문한 최소 계단 값(Min)
         */
        long[][][][] dp = new long[N + 1][10][10][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][i][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int min = j; min >= 0; min--) {
                    for (int max = j; max <= 9; max++) {
                        if (min == max) continue;

                        if (min == j) {
                            dp[i][j][min][max] = (dp[i - 1][j + 1][min][max] + dp[i - 1][j + 1][min + 1][max]) % MOD;
                        } else if (max == j) {
                            dp[i][j][min][max] = (dp[i - 1][j - 1][min][max] + dp[i - 1][j - 1][min][max - 1]) % MOD;
                        } else {
                            dp[i][j][min][max] = (dp[i - 1][j - 1][min][max] + dp[i - 1][j + 1][min][max]) % MOD;
                        }
                    }
                }
            }
        }

        long sum = 0L;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][0][9];
        }

        System.out.println(sum % MOD);
    }
}
```

**비트필드를 사용한 풀이**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /**
         * e1: 현재 계단 위치(level)
         * e2: 현재 계단 자리의 값
         * e3: 방문 여부(비트 필드)
         */
        long[][][] dp = new long[N + 1][10][(1 << 10)];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        /**
         * l: level
         * i: value
         * v: visit
         */
        for (int l = 2; l <= N; l++) {
            for (int i = 0; i <= 9; i++) {
                for (int v = 1; v < (1 << 10); v++) {
                    if (i != 0) {
                        dp[l][i][v | 1 << i] += dp[l - 1][i - 1][v];
                    }
                    if (i != 9) {
                        dp[l][i][v | 1 << i] += dp[l - 1][i + 1][v];
                    }
                    dp[l][i][v] %= MOD;
                }
            }
        }
        long sum = 0L;


        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][(1 << 10) - 1];
            sum %= MOD;
        }


        System.out.println(sum);
    }
}
```