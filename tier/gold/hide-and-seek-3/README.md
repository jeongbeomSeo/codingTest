# 숨바꼭질 3

## 문제 

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

## 입력 

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

## 출력 

수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

## 예제 입력 1

```
5 17
```

## 예제 출력 1

```
2
```

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(search(N, K));
    }
    private static int search(int N, int K) {

        final int SIZE = Math.min(Math.max(2 * N, 2 * K), 100001);
        int[] dist = new int[SIZE];
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(N, 0));
        Arrays.fill(dist, INF);
        dist[N] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > dist[curNode.idx]) continue;

            if (curNode.idx == K) {
                return dist[curNode.idx];
            }

            if (2 * curNode.idx < SIZE && dist[2 * curNode.idx] > curNode.cost) {
                dist[2 * curNode.idx] = curNode.cost;
                pq.add(new Node(2 * curNode.idx, curNode.cost));
            }

            if (1 + curNode.idx < SIZE && dist[1 + curNode.idx] > curNode.cost + 1) {
                dist[1 + curNode.idx] = curNode.cost + 1;
                pq.add(new Node(1 + curNode.idx, curNode.cost + 1));
            }

            if (curNode.idx - 1 >= 0 && dist[curNode.idx - 1] > curNode.cost + 1) {
                dist[curNode.idx - 1] = curNode.cost + 1;
                pq.add(new Node(curNode.idx - 1, curNode.cost + 1));
            }
        }

        return dist[K];
    }
}
class Node {
    int idx;
    int cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
```