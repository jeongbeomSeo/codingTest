# 하노이 탑

**골드 4**

|시간 제한|	메모리 제한	|제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|1024 MB|	1064|	271|	185	|31.787%|

## 문제 

세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다. 각 원판은 반경이 큰 순서대로 쌓여있다. 이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.

1. 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
2. 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.

이 작업을 수행하는 필요한 이동 순서 중에서 K번째를 출력하는 프로그램을 작성하라. 단, 이동 횟수는 최소가 되어야 한다.

아래 그림은 원판이 5개인 경우의 예시이다.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/11729/hanoi.png)

## 입력 

첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N(1 ≤ N ≤ 60)과 K가 주어진다. 항상 K번째 이동이 존재하는 K만 주어진다.

## 출력 

첫째 줄에 K번째 수행 과정을 의미하는 두 정수 A B를 빈칸을 사이에 두고 출력한다. 이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다.

## 예제 입력 1

```
3 1
```

## 예제 출력 1

```
1 3
```

## 예제 입력 2

```
3 2
```

## 예제 출력 2

```
1 2
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        int[] result = recursive(N, 1, 3, K);

        System.out.println(result[0] + " " + result[1]);
    }
    private static int[] recursive(int N, int start, int end, long remain) {

        long curCount = (long)Math.pow(2, N) - 1;
        int[] result = null;
        if (remain - curCount > 0) {
            // N개 start -> end
            remain -= curCount;

            // N + 1블럭 start -> 6 - start - end로 이동
            remain--;

            if (remain == 0) {
                return new int[]{start, 6 - start - end};
            } else {
                result = recursive(N, end, 6 - start - end, remain);
            }
        } else {
            result = recursive(N - 1, start, 6 - start - end, remain);
        }

        return result;
    }
}
```