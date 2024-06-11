# 습력자 초라기

**플래티넘 3**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB|	19771	|3354|	2307|	20.340%|

## 문제 

초라기는 한국의 비밀국방기지(원타곤)를 습격하라는 임무를 받은 특급요원이다. 원타곤의 건물은 도넛 형태이며, 초라기는 효율적인 타격 포인트를 정하기 위해 구역을 아래와 같이 두 개의 원 모양으로 나누었다. (그림의 숫자는 각 구역의 번호이다.)

![](https://www.acmicpc.net/upload/201003/dfck3232_34g7t9f4gp_b.jpg)

초라기는 각각 W명으로 구성된 특수소대를 다수 출동시켜 모든 구역에 침투시킬 예정이며, 각 구역 별로 적이 몇 명씩 배치되어 있는지는 초라기가 모두 알고 있다. 특수소대를 아래 조건에 따라 침투 시킬 수 있다.

1. 한 특수소대는 침투한 구역 외에, 인접한 한 구역 더 침투할 수 있다. (같은 경계를 공유하고 있으면 인접 하다고 한다. 위 그림에서 1구역은 2, 8, 9 구역과 서로 인접한 상태다.) 즉, 한 특수소대는 한 개 혹은 두 개의 구역을 커버할 수 있다.
2. 특수소대끼리는 아군인지 적인지 구분을 못 하기 때문에, 각 구역은 하나의 소대로만 커버해야 한다.
3. 한 특수소대가 커버하는 구역의 적들의 합은 특수소대원 수 W 보다 작거나 같아야 한다.
이때 초라기는 원타곤의 모든 구역을 커버하기 위해 침투 시켜야 할 특수 소대의 최소 개수를 알고 싶어 한다.

## 입력 

첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 구성되어있다.

첫째 줄에는 (구역의 개수)/2 값 N과 특수 소대원의 수 W가 주어진다. (1 ≤ N ≤ 10000, 1 ≤ W ≤ 10000).

둘째 줄에는 1~N번째 구역에 배치된 적의 수가 주어지고, 셋째 줄에는 N+1 ~ 2N번째 구역에 배치된 적의 수가 공백으로 구분되어 주어진다. (1 ≤ 각 구역에 배치된 최대 적의 수 ≤ 10000) 단, 한 구역에서 특수 소대원의 수보다 많은 적이 배치된 구역은 존재하지 않는다. (따라서, 각 구역에 배치된 최대 적의 수 ≤ W)

## 출력

각 테스트케이스에 대해서 한 줄에 하나씩 원타곤의 모든 구역을 커버하기 위해 침투 시켜야 할 특수 소대의 최소 개수를 출력하시오.

## 예제 입력 1

```
1
8 100
70 60 55 43 57 60 44 50
58 40 47 90 45 52 80 40
```

## 예제 출력 1

```
11
```

## 풀이 

**WA**

```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][2];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N == 1) {
                bw.write(getScore(arr[0][0], arr[0][1], W) + "\n");
                continue;
            }

            int[][] copy = new int[2][2];
            copy[0][0] = arr[0][0];
            copy[0][1] = arr[0][1];
            copy[1][0] = arr[N - 1][0];
            copy[1][1] = arr[N - 1][1];
            int result = Integer.MAX_VALUE;
            for (int c = 0; c < 4; c++) {
                if (c == 1) {
                    if (arr[0][0] + arr[N - 1][0] > W) continue;
                    arr[0][0] = W + 1;
                    arr[N - 1][0] = W + 1;
                } else if (c == 2) {
                    if (arr[0][1] + arr[N - 1][1] > W) continue;
                    arr[0][1] = W + 1;
                    arr[N - 1][1] = W + 1;
                } else if (c == 3) {
                    if (arr[0][0] + arr[N - 1][0] > W || arr[0][1] + arr[N - 1][1] > W) continue;
                    arr[0][0] = arr[0][1] = W + 1;
                    arr[N - 1][0] = arr[N - 1][1] = W + 1;
                }

                int[] dp = new int[N];
                dp[0] = getScore(arr[0][0], arr[0][1], W);
                dp[1] = getScore(arr[1][0], arr[1][1], W) + dp[0];
                dp[1] = Math.min(dp[1], getScore(arr[0][0], arr[1][0], W) + getScore(arr[0][1], arr[1][1], W));
                for (int i = 2; i < N; i++) {
                    dp[i] = getScore(arr[i][0], arr[i][1], W) + dp[i - 1];
                    dp[i] = Math.min(dp[i], getScore(arr[i - 1][0], arr[i][0], W) + getScore(arr[i - 1][1], arr[i][1], W) + dp[i - 2]);
                }

                arr[0][0] = copy[0][0];
                arr[0][1] = copy[0][1];
                arr[N - 1][0] = copy[1][0];
                arr[N - 1][1] = copy[1][1];

                if (c == 1) {
                    dp[N - 1] -= 1;
                } else if (c == 2) {
                    dp[N - 1] -= 1;
                } else if (c == 3) {
                    dp[N - 1] -= 1;
                    dp[N - 1] -= 1;
                }

                result = Math.min(result, dp[N - 1]);
            }

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static int getScore(int value1, int value2, int boundary) {
        if (value1 + value2 <= boundary) return 1;
        else return 2;
    }
}
```

**AC**

```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] arr = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j < N + 1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(query(arr, N, W) + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static int query(int[][] arr, int N, int W) {

        /**
         * 모양 기준으로 구분하기
         * dp[0]: arr의 0행의 n - 1과 n이 겹치는 경우
         * dp[1]: arr의 1행의 n - 1과 n이 겹치는 경우
         * dp[2]: arr의 같은 열이 겹치는 경우
         */

        int result = Integer.MAX_VALUE;

        for (int k = 0; k < 4; k++) {
            int[] buffer = preProcess(arr, k, N, W);

            if (k != 0 && buffer == null) continue;

            int[][] dp = new int[3][N + 1];
            dp[0][1] = 1;
            dp[1][1] = 1;
            dp[2][1] = getValue(arr[0][1], arr[1][1], W);
            for (int j = 2; j <= N; j++) {
                for (int i = 0; i < 3; i++) {
                    if (i != 2) {
                        dp[i][j] = Math.min(dp[2][j - 1] + 1, dp[Math.abs(i - 1)][j - 1] + getValue(arr[i][j - 1], arr[i][j], W));
                    } else {
                        dp[i][j] = Math.min(dp[0][j] + 1, dp[1][j] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[2][j - 1] + getValue(arr[0][j], arr[1][j], W));
                        dp[i][j] = Math.min(dp[i][j], dp[2][j - 2] + getValue(arr[0][j - 1], arr[0][j], W) + getValue(arr[1][j - 1], arr[1][j], W));
                    }
                }
            }
            int curResult = Math.min(dp[0][N] + 1, Math.min(dp[1][N] + 1, dp[2][N]));
            if (k == 1 || k == 2) curResult -= 1;
            else if (k == 3) curResult -= 2;
            result = Math.min(result, curResult);

            postProcess(arr, buffer, k, N);
        }
        return result;
    }
    private static int[] preProcess(int[][] arr, int k, int N, int W) {
        if (k == 0) return null;
        else if (N <= 2) return null;
        else if (k == 1) {
            if (arr[0][1] + arr[0][N] <= W) {
                int[] buffer = new int[] {arr[0][1], arr[0][N]};
                arr[0][1] = arr[0][N] = W;
                return buffer;
            } else {
                return null;
            }
        } else if (k == 2) {
            if (arr[1][1] + arr[1][N] <= W) {
                int[] buffer = new int[] {arr[1][1], arr[1][N]};
                arr[1][1] = arr[1][N] = W;
                return buffer;
            } else {
                return null;
            }
        } else {
            if (arr[0][1] + arr[0][N] <= W && arr[1][1] + arr[1][N] <= W) {
                int[] buffer = new int[] {arr[0][1], arr[0][N], arr[1][1], arr[1][N]};
                arr[0][1] = arr[0][N] = arr[1][1] = arr[1][N] = W;
                return buffer;
            } else {
                return null;
            }
        }
    }
    private static void postProcess(int[][] arr, int[] buffer, int k, int N) {
        if (k == 0) {
            return;
        }
        else if (k == 1) {
            arr[0][1] = buffer[0];
            arr[0][N] = buffer[1];
        } else if (k == 2) {
            arr[1][1] = buffer[0];
            arr[1][N] = buffer[1];
        } else {
            arr[0][1] = buffer[0];
            arr[0][N] = buffer[1];
            arr[1][1] = buffer[2];
            arr[1][N] = buffer[3];
        }
    }
    private static int getValue(int v1, int v2, int W) {
        if (v1 + v2 <= W) return 1;
        else return 2;
    }
}
```