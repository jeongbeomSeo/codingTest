# 본대 산책 2

**골드 1**


|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	2222|	1806|	1574|	83.902%|

## 문제 

숭실 대학교 정보 과학관은 유배를 당해서  캠퍼스의 길 건너편에 있다. 그래서 컴퓨터 학부 학생들은 캠퍼스를 ‘본대’ 라고 부르고 정보 과학관을 ‘정보대’ 라고 부른다. 준영이 또한 컴퓨터 학부 소속 학생이라서 정보 과학관에 박혀있으며 항상 꽃 이 활짝 핀 본 대를 선망한다. 어느 날 준영이는 본 대를 산책하기로 결심하였다. 숭실 대학교 캠퍼스 지도는 아래와 같다.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12850/1.png)
(편의 상 문제에서는 위 건물만 등장한다고 가정하자)

한 건물에서 바로 인접한 다른 건물로 이동 하는 데 1분이 걸린다. 준영이는 산책 도중에 한번도 길이나 건물에 멈춰서 머무르지 않는다. 준영이는 할 일이 많아서 딱 D분만 산책을 할 것이다. (산책을 시작 한 지 D분 일 때, 정보 과학관에 도착해야 한다.) 이때 가능한 경로의 경우의 수를 구해주자.

## 입력 

D 가 주어진다 (1 ≤ D ≤ 1,000,000,000) 

## 출력 

가능한 경로의 수를 1,000,000,007로 나눈 나머지를 출력한다.

## 예제 입력 1

```
100000000
```

## 예제 출력 1

```
261245548
```

## 나의 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long[][] defaultGraph = {
            {0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1},
            {1, 1, 1, 0, 0, 0, 1, 0}
    };
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(query(N));
    }
    private static int query(int N) {

        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) result[i][i] = 1;

        while (N != 0) {
            int t = 0;
            while (Math.pow(2, t + 1) <= N) t++;

            result = matrixMultiplication(result, getGraph((int)Math.pow(2, t)));
            N -= (int)Math.pow(2, t);
        }

        return (int)(result[0][0] % MOD);
    }
    private static long[][] getGraph(int n) {

        if (n == 1) {
            return defaultGraph;
        }

        long[][] resultGraph = getGraph(n / 2);
        return matrixMultiplication(resultGraph, resultGraph);
    }
    private static long[][] matrixMultiplication(long[][] graph1, long[][] graph2) {

        long[][] result = new long[graph1.length][graph1[0].length];

        for (int i = 0; i < graph1.length; i++) {
            for (int j = 0; j < graph1[i].length; j++) {
                long sum = 0L;
                for (int k = 0; k < graph1[i].length; k++) {
                    sum += graph1[i][k] * graph2[k][j];
                    sum %= MOD;
                }

                result[i][j] = sum;
            }
        }

        return result;
    }
}
```

## 참고 풀이

```java
import java.util.*;
import java.io.*;

public class Main {
	public static long[][] TIM(long[][] A, long[][] B) {
		long[][] result = new long[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					result[i][j] += A[k][i] * B[j][k];
					result[i][j] %= 1000000007;
				}
			}
		}

		return result;
	}

	public static long[][] SOL(int D, long[][] base) {
		if (D == 1) {
			return base;
		}

		if (D % 2 == 0) {
			long[][] arr = SOL(D / 2, base); // DP의 미묘한 차이
			
			return TIM(arr, arr);
			// return TIM(SOL(D / 2, base), SOL(D / 2, base)); -> timeout
		}

		else {
			long[][] arr = SOL(D / 2, base);
			
			return TIM(TIM(arr, arr), base);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int N = Integer.parseInt(st.nextToken());

		long[][] base = new long[][] { { 0, 1, 1, 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 0, 0, 0, 0 },
				{ 1, 1, 0, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 1, 1, 0, 0 }, { 0, 0, 1, 1, 0, 1, 1, 0 },
				{ 0, 0, 0, 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 1, 1, 0 } };

		long[][] result = SOL(N, base);

		System.out.println(result[0][0]);
	}
}
```