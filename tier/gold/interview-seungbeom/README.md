# 면접보는 승범이네

**골드 2**

## 문제 

마포구에는 모든 대학생이 입사를 희망하는 굴지의 대기업 ㈜승범이네 본사가 자리를 잡고 있다. 승범이는 ㈜승범이네의 사장인데, 일을 못 하는 직원들에게 화가 난 나머지 전 직원을 해고하고 신입사원을 뽑으려 한다. 1차 서류전형이 끝난 뒤 합격자들은 면접을 준비하게 되었다.

면접자들은 서로 다른 N개의 도시에 거주한다. 승범이는 면접자들의 편의를 위해 거주 중인 N개 도시 중 K개의 도시에 면접장을 배치했다. 도시끼리는 단방향 도로로 연결되며, 거리는 서로 다를 수 있다. 어떤 두 도시 사이에는 도로가 없을 수도, 여러 개가 있을 수도 있다. 또한 어떤 도시에서든 적어도 하나의 면접장까지 갈 수 있는 경로가 항상 존재한다.

모든 면접자는 본인의 도시에서 출발하여 가장 가까운 면접장으로 찾아갈 예정이다. 즉, 아래에서 언급되는 '면접장까지의 거리'란 그 도시에서 도달 가능한 가장 가까운 면접장까지의 최단 거리를 뜻한다.

속초 출신 승범이는 지방의 서러움을 알기에 각 도시에서 면접장까지의 거리 중, 그 거리가 가장 먼 도시에서 오는 면접자에게 교통비를 주려고 한다.

승범이를 위해 면접장까지의 거리가 가장 먼 도시와 그 거리를 구해보도록 하자.

## 입력 

첫째 줄에 도시의 수 N(2 ≤ N ≤ 100,000), 도로의 수 M(1 ≤ M ≤ 500,000), 면접장의 수 K(1 ≤ K ≤ N)가 공백을 두고 주어진다. 도시는 1번부터 N번까지의 고유한 번호가 매겨진다.

다음 M개의 줄에 걸쳐 한 줄마다 도시의 번호 U, V(U ≠ V)와 도로의 길이 C(1 ≤ C ≤ 100,000)가 공백을 두고 순서대로 주어진다. 이는 도시 U에서 V로 갈 수 있는 도로가 존재하고, 그 거리가 C라는 뜻이다.

마지막 줄에 면접장이 배치된 도시의 번호 K개가 공백을 두고 주어진다.

## 출력 

첫째 줄에 면접장까지 거리가 가장 먼 도시의 번호를 출력한다. 만약 그런 도시가 여러 곳이면 가장 작은 번호를 출력한다.

둘째 줄에 해당 도시에서 면접장까지의 거리를 출력한다.

## 예제 입력 1

```
6 10 2
2 6 2
1 4 1
6 1 5
2 5 1
5 1 4
4 5 6
6 2 3
3 5 1
3 1 1
5 2 2
1 2
```

## 예제 출력 1

```
4
8
```

## 오답 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static long[] dpTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isMeetingPlace = new boolean[N + 1];

        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[src].add(new Node(dst, cost));
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(graph[i], (o1, o2) -> o1.cost - o2.cost);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int meetingPlace = Integer.parseInt(st.nextToken());

            isMeetingPlace[meetingPlace] = true;
        }

        dpTable = new long[N + 1];
        Arrays.fill(dpTable, INF);

        int resultIdx = -1;
        long resultDist = -1;
        for (int i = 1; i < N + 1; i++) {
            long result = dfs(graph, isMeetingPlace, i);

            if (resultDist < result) {
                resultIdx = i;
                resultDist = result;
            }
        }
        System.out.println(resultIdx);
        System.out.println(resultDist);
    }
    private static long dfs(List<Node>[] graph, boolean[] isMeetingPlace, int idx) {

        if (dpTable[idx] != INF) {
            return dpTable[idx];
        }

        if (isMeetingPlace[idx]) {
            return 0;
        }

        for (int i = 0; i < graph[idx].size(); i++) {
            Node nxtNode = graph[idx].get(i);

            dpTable[idx] = Math.min(dpTable[idx], dfs(graph, isMeetingPlace, nxtNode.dst) + nxtNode.cost);
        }

        return dpTable[idx];
    }
}


/* TLE
private static int getShortestDist(List<Node>[] graph, boolean[] isMeetingPlace, int startIdx, int N) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    pq.add(new Node(startIdx, 0));

    int[] distTable = new int[N + 1];
    Arrays.fill(distTable, INF);
    distTable[startIdx] = 0;

    while (!pq.isEmpty()) {
        Node curNode = pq.poll();

        if (distTable[curNode.dst] < curNode.cost) continue;

        if (isMeetingPlace[curNode.dst]) return distTable[curNode.dst];

        for (int i = 0; i < graph[curNode.dst].size(); i++) {
            Node nxtNode = graph[curNode.dst].get(i);

            if (distTable[nxtNode.dst] > distTable[curNode.dst] + nxtNode.cost) {
                distTable[nxtNode.dst] = distTable[curNode.dst] + nxtNode.cost;

                pq.add(new Node(nxtNode.dst, distTable[nxtNode.dst]));
            }
        }
    }

    return 0;
}
*/
class Node {
    int dst;
    int cost;

    Node(int dst, int cost) {
        this.dst = dst;
        this.cost = cost;
    }
}
```
**TLE**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static long[] globalDistTable;
    private static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 역방향 그래프
        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        globalDistTable = new long[N + 1];
        Arrays.fill(globalDistTable, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 단방향을 역방향으로 넣어주기 위해 dst를 기준으로 src, cost를 만들어서 삽입
            graph[dst].add(new Node(src, cost));
        }

        boolean[] isMeetingArea = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            isMeetingArea[idx] = true;
        }

        for (int i = 1; i < N + 1; i++) {
            if (!isMeetingArea[i]) continue;

            updateDistTable(graph, i, N);
        }

        int resultIdx = -1;
        long resultDist = -1;

        for (int i = 1; i < N + 1; i++) {
            if (globalDistTable[i] > resultDist) {
                resultIdx = i;
                resultDist = globalDistTable[i];
            }
        }

        System.out.println(resultIdx);
        System.out.println(resultDist);
    }
    private static void updateDistTable(List<Node>[] graph, int startIdx, int N) {

        long[] distTable = new long[N + 1];
        Arrays.fill(distTable, INF);
        distTable[startIdx] = 0;
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1.cost - o2.cost));
        pq.add(new Node(startIdx, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > distTable[curNode.src] || distTable[curNode.src] >= globalDistTable[curNode.src]) continue;

            for (int i = 0; i < graph[curNode.src].size(); i++) {
                Node nxtNode = graph[curNode.src].get(i);

                if (distTable[nxtNode.src] > distTable[curNode.src] + nxtNode.cost) {
                    distTable[nxtNode.src] = distTable[curNode.src] + nxtNode.cost;

                    pq.add(new Node(nxtNode.src, distTable[nxtNode.src]));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            globalDistTable[i] = Math.min(globalDistTable[i], distTable[i]);
        }
    }
}
class Node {
    int src;
    long cost;

    Node(int src, long cost) {
        this.src = src;
        this.cost = cost;
    }
}
```