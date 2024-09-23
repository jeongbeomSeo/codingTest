# 경찰차

**플래티넘 4**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	17329|	5759|	4048|34.798%|

## 문제 

어떤 도시의 중심가는 N개의 동서방향 도로와 N개의 남북방향 도로로 구성되어 있다.

모든 도로에는 도로 번호가 있으며 남북방향 도로는 왼쪽부터 1에서 시작하여 N까지 번호가 할당되어 있고 동서방향 도로는 위부터 1에서 시작하여 N까지 번호가 할당되어 있다. 또한 동서방향 도로 사이의 거리와 남 북방향 도로 사이의 거리는 모두 1이다. 동서방향 도로와 남북방향 도로가 교차하는 교차로의 위치는 두 도로의 번호의 쌍인 (동서방향 도로 번호, 남북방향 도로 번호)로 나타낸다. N이 6인 경우의 예를 들면 다음과 같다.

![](https://upload.acmicpc.net/6b5a6518-1801-46c9-9b17-49e8abb3bc88/-/preview/)

이 도시에는 두 대의 경찰차가 있으며 두 차를 경찰차1과 경찰차2로 부른다. 처음에는 항상 경찰차1은 (1, 1)의 위치에 있고 경찰차2는 (N, N)의 위치에 있다. 경찰 본부에서는 처리할 사건이 있으면 그 사건이 발생된 위치를 두 대의 경찰차 중 하나에 알려 주고, 연락 받은 경찰차는 그 위치로 가장 빠른 길을 통해 이동하여 사건을 처리한다. (하나의 사건은 한 대의 경찰차가 처리한다.) 그리고 사건을 처리 한 경찰차는 경찰 본부로부터 다음 연락이 올 때까지 처리한 사건이 발생한 위치에서 기다린다. 경찰 본부에서는 사건이 발생한 순서대로 두 대의 경찰차에 맡기려고 한다. 처리해야 될 사건들은 항상 교차로에서 발생하며 경찰 본부에서는 이러한 사건들을 나누어 두 대의 경찰차에 맡기되, 두 대의 경찰차들이 이동하는 거리의 합을 최소화 하도록 사건을 맡기려고 한다.

예를 들어 앞의 그림처럼 N=6인 경우, 처리해야 하는 사건들이 3개 있고 그 사건들이 발생된 위치 를 순서대로 (3, 5), (5, 5), (2, 3)이라고 하자. (3, 5)의 사건을 경찰차2에 맡기고 (5, 5)의 사건도 경찰차2에 맡기며, (2, 3)의 사건을 경찰차1에 맡기면 두 차가 이동한 거리의 합은 4 + 2 + 3 = 9가 되 고, 더 이상 줄일 수는 없다.

처리해야 할 사건들이 순서대로 주어질 때, 두 대의 경찰차가 이동하는 거리의 합을 최소화 하도록 사건들을 맡기는 프로그램을 작성하시오.

## 입력 

첫째 줄에는 동서방향 도로의 개수를 나타내는 정수 N(5 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 처리해야 하는 사건의 개수를 나타내는 정수 W(1 ≤ W ≤ 1,000)가 주어진다. 셋째 줄부터 (W+2)번째 줄까지 사건이 발생된 위치가 한 줄에 하나씩 주어진다. 경찰차들은 이 사건들을 주어진 순서대로 처리해야 한다. 각 위치는 동서방향 도로 번호를 나타내는 정수와 남북방향 도로 번호를 나타내는 정수로 주어지며 두 정수 사이에는 빈칸이 하나 있다. 두 사건이 발생한 위치가 같을 수 있다.

## 출력 

첫째 줄에 두 경찰차가 이동한 총 거리를 출력한다. 둘째 줄부터 시작하여 (i+1)번째 줄에 i(1 ≤ i ≤ W)번째 사건이 맡겨진 경찰차 번호 1 또는 2를 출력한다.

## 예제 입력 1

```
6
3
3 5
5 5
2 3
```

## 예제 출력 1

```
9
2
2
1
```

##  나의 코드

**AC**

```java
import java.io.*;
import java.util.*;

public class Main {
    private static int W;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        int[][] events = new int[W + 2][2];

        events[0] = new int[] {1, 1};
        events[1] = new int[] {N, N};
        for (int i = 2; i < events.length; i++) {
            st = new StringTokenizer(br.readLine());

            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[W + 2][W + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(activeDp(events, dp, 0, 1));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        printRoute(bw, events, dp, 0, 1);
        bw.flush();
        bw.close();
    }
    private static void printRoute(BufferedWriter bw, int[][] events, int[][] dp, int pos1, int pos2) throws IOException{

        if (pos1 == W + 1 || pos2 == W + 1) {
            return;
        }

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        int lDist = calcDistance(events, nxtPos, pos1) + dp[nxtPos][pos2];
        int rDist = calcDistance(events, nxtPos, pos2) + dp[pos1][nxtPos];

        if (lDist < rDist) {
            bw.write("1\n");
            printRoute(bw, events, dp, nxtPos, pos2);
        } else {
            bw.write("2\n");
            printRoute(bw, events, dp, pos1, nxtPos);
        }
    }
    private static int activeDp(int[][] events, int[][] dp, int pos1, int pos2) {

        if (pos1 == W + 1 || pos2 == W + 1) {
            return 0;
        }

        if (dp[pos1][pos2] != -1) {
            return dp[pos1][pos2];
        }

        dp[pos1][pos2] = INF;

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        int lResult = activeDp(events, dp, nxtPos, pos2) + calcDistance(events, nxtPos, pos1);
        int rResult = activeDp(events, dp, pos1, nxtPos) + calcDistance(events, nxtPos, pos2);

        int result = Math.min(lResult, rResult);

        return dp[pos1][pos2] = result;
    }
    private static int calcDistance(int[][] events, int prevPos, int nxtPos) {
        return Math.abs(events[nxtPos][0] - events[prevPos][0]) + Math.abs(events[nxtPos][1] - events[prevPos][1]);
    }
}
```