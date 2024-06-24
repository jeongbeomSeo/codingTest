# 박성원

**플래티넘 5**

|시간 제한	|메모리 제한	|제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	|10842|3204|	2182|	26.898%|

## 문제 

박성원은 이 문제를 풀지 못했다.

서로 다른 정수로 이루어진 집합이 있다. 이 집합의 순열을 합치면 큰 정수 하나를 만들 수 있다. 예를 들어, {5221,40,1,58,9}로 5221401589를 만들 수 있다. 합친수가 정수 K로 나누어 떨어지는 순열을 구하는 프로그램을 작성하시오.

하지만, 박성원은 이 문제를 풀지 못했다.

따라서 박성원은 그냥 랜덤하게 순열 하나를 정답이라고 출력하려고 한다. 이 문제에는 정답이 여러 개 있을 수도 있고, 박성원이 우연히 문제의 정답을 맞출 수도 있다.

박성원이 우연히 정답을 맞출 확률을 분수로 출력하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 집합의 수의 개수 N이 주어진다. N은 15보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 집합에 포함된 수가 주어진다. 각 수의 길이는 길어야 50인 자연수이다. 마지막 줄에는 K가 주어진다. K는 100보다 작거나 같은 자연수이다.

## 출력 

첫째 줄에 정답을 기약분수 형태로 출력한다. p/q꼴로 출력하며, p는 분자, q는 분모이다. 정답이 0인 경우는 0/1로, 1인 경우는 1/1로 출력한다.

## 예제 입력 1

```
3
3
2
1
2
```

## 예제 출력 1

```
1/3
```

## 예제 입력 2

```
5
10
100
1000
10000
100000
10
```

## 예제 출력 2

```
1/1
```

## 예제 입력 3

```
5
11
101
1001
10001
100001
10
```

## 예제 출력 3

```
0/1
```

## 예제 입력 4

```
9
13
10129414190271203
102
102666818896
1216
1217
1218
101278001
1000021412678412681
21
```

## 예제 출력 4

```
5183/36288
```

## 문제 풀이 Point

1. 50자리 수의 나머지 계산 (손 풀이 방식의 나머지 구하기 이용)
2. 최대 공약수로 나누는 gcd 이용
3. 미리 사용될 나머지 값들 Regist 등록 
4. 외판원 순회와 비슷한 형태의 풀이
   1. 방문한 노드(숫자)로 나눈다고 가정하면 이전까지 방문했던 이력이 필요.
   2. 평범하게 생각해보면 순서도 중요할 것 같지만 나머지로 전부 분리가 가능한 시점에서 중요한 이력은 이전까지 방문한 노드의 이력과 그에 대한 나머지 값
   3. 따라서 ```15!```가 아닌 방문했던 노드의 이력과 K값으로 나눴을 때 나올 수 있는 나머지 값들로 반복문을 한정 짓기

중요한 것은 비트마스크를 이용하여 기록하고 ```0(아무것도 방문 X)```부터 ```(1 << N) - 1```까지 탐색하면서 해당 이력에 1로 포함되어 있는 노드의 집합들은 무조건 탐색이 완료된 상태라는 것이다.

즉, 00111을 탐색할 시점에서는 00011 이든가, 00100 이든가 모두 탐색된 상태라는 것

따라서 풀이에서 첫번째 반복문의 조건을 이력으로 설정해서 순차적으로 방문 가능 노드를 업데이트 하는 것이다.

## 풀이 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());

        int[] regist = getRegist(arr, K);
        int[] tenRegist = new int[51];
        tenRegist[0] = 1 % K;
        for (int i = 1; i < 51; i++) {
            tenRegist[i] = (tenRegist[i - 1] * 10) % K;
        }

        long[][] dp = new long[K][(1 << N)];

        dp[0][0] = 1;

        for (int cur = 0; cur < (1 << N); cur++) {
            for (int i = 0; i < N; i++) {
                if (((1 << i) & cur) != 0) continue;
                int nxtState = cur | (1 << i);

                for (int j = 0; j < K; j++) {
                    int nxtK = (j * tenRegist[arr[i].length()] + regist[i]) % K;
                    dp[nxtK][nxtState] += dp[j][cur];
                }
            }
        }

        long denominator = 1L;
        for (int i = 2; i <= N; i++) denominator *= i;
        long numerator = dp[0][(1 << N) - 1];
        long gcd = getGCD(denominator, numerator);
        System.out.println(numerator / gcd + String.valueOf("/") + denominator / gcd);
    }
    private static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
    private static int[] getRegist(String[] arr, int K) {
        int[] regist = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            regist[i] = getRemain(arr[i], K);
        }

        return regist;
    }
    private static int getRemain(String numStr, int K) {

        int num = 0;
        for (int i = 0; i < numStr.length(); i++) {
            num *= 10;
            num += numStr.charAt(i) - '0';
            num %= K;
        }

        return num;
    }
}
```