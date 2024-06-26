# 길의 개수

**플래티넘 3**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	3167	|1214	|990|	37.989%|

## 문제 

세준이는 정문이를 데리러 공항으로 가기로 했다. 하지만, 방금 세준이는 정문이의 비행기가 연착된다는 전화를 받았다. 세준이는 정문이가 정확하게 몇 분 늦는지 알고 있고, 그 시간 동안 밖에서 드라이브를 하려고 한다. 정문이가 늦는 시간을 T라고 하자.

세준이는 자기가 지금 있는 위치에서부터, 공항까지 정확하게 T분만에 도착하는 경로의 개수를 구하고 싶다.

길의 정보는 인접행렬로 주어진다. A[i][j]가 0이라면 i에서 j로 가는 길이 없는 것이고, A[i][j] ≤ 5라면, 정확히 그 만큼의 시간이 걸리는 i에서 j로 가는 길이 있는 것이다.

## 입력 

첫째 줄에 교차점의 개수 N이 주어진다. N은 10보다 작거나 같고, 시작점의 위치 S와 끝점의 위치 E, 그리고 정문이가 늦는 시간 T도 주어진다. S와 E는 N보다 작거나 같은 자연수이다. T는 1,000,000,000보다 작거나 같은 자연수이다. 둘째 줄부터 길의 정보가 주어진다.

## 출력 

첫째 줄에 경로의 개수를 1,000,003로 나눈 나머지를 출력한다.

## 예제 입력 1

```
3 1 3 5
012
201
120
```

## 예제 출력 1

```
8
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private final static int MOD = 1_000_003;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken()) - 1;
        int T = Integer.parseInt(st.nextToken());

        long[][] grid = initGrid(N);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                int c = str.charAt(j) - '0';

                if (c != 0) {
                    grid[5 * i][5 * j + (c - 1)] = 1;
                }
            }
        }

        System.out.println(solution(grid, S, E, T));
    }
    private static long solution(long[][] grid, int S, int E, int T) {

        long[][] result = solve(grid, T);

        return result[5 * S][5 * E];
    }
    private static long[][] solve(long[][] baseGrid, int T) {

        if (T == 1) {
            return baseGrid;
        }

        if (T % 2 == 0) {
            long[][] result = solve(baseGrid, T / 2);
            return mulMat(result, result);
        } else {
            long[][] result = solve(baseGrid, (T - 1) / 2);
            return mulMat(mulMat(result, result), baseGrid);
        }
    }
    private static long[][] mulMat(long[][] grid1, long[][] grid2) {

        long[][] result = new long[grid1.length][grid1[0].length];

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[i].length; j++) {
                for (int k = 0; k < grid1.length; k++) {
                    result[i][j] += (grid1[i][k] * grid2[k][j]) % MOD;
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
    private static long[][] initGrid(int N) {

        long[][] grid = new long[5 * N][5 * N];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= 4; j++) {
                grid[5 * i + j][5 * i + (j - 1)] = 1;
            }
        }

        return grid;
    }
}
```