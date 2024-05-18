# 피보나치 수 3

**골드 2**

|시간 제한	|메모리 제한|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB	|32371	|11007|	8940|	39.742%|

## 문제

피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n ≥ 2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

## 출력 

첫째 줄에 n번째 피보나치 수를 1,000,000으로 나눈 나머지를 출력한다.

## 예제 입력 1

```
1000
```

## 예제 출력 1

```
228875
```

## 나의 코드

**분할 정복을 활용한 풀이**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Long, Long> dp;
    private static final int MOD = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        dp = new HashMap<>();

        System.out.println(query(N));
    }
    private static long query(long N) {

        if (N == 1) return 1;
        else if (N == 2) return 1;

        if (dp.containsKey(N)) return dp.get(N);

        long result;
        if (N % 2 == 0) {
            result = (query(N / 2) * query(N / 2 + 1) + query(N / 2 - 1) * query(N / 2)) % MOD;
        } else {
            result = (query((N + 1) / 2) * query((N - 1) / 2 + 1) + query((N + 1) / 2 - 1) * query((N - 1) / 2)) % MOD;
        }
        dp.put(N, result);
        return result;
    }
}
```

**파사노 주기를 활용한 풀이**

