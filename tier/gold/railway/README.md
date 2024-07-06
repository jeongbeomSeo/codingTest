# 철로

**골드 2**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	10030	|3981	|2740	|37.632%|

## 문제 

집과 사무실을 통근하는 n명의 사람들이 있다. 각 사람의 집과 사무실은 수평선 상에 있는 서로 다른 점에 위치하고 있다. 임의의 두 사람 A, B에 대하여, A의 집 혹은 사무실의 위치가 B의 집 혹은 사무실의 위치와 같을 수 있다. 통근을 하는 사람들의 편의를 위하여 일직선 상의 어떤 두 점을 잇는 철로를 건설하여, 기차를 운행하려고 한다. 제한된 예산 때문에, 철로의 길이는 d로 정해져 있다. 집과 사무실의 위치 모두 철로 선분에 포함되는 사람들의 수가 최대가 되도록, 철로 선분을 정하고자 한다.

양의 정수 d와 n 개의 정수쌍, (hi, oi), 1 ≤ i ≤ n,이 주어져 있다. 여기서 hi와 oi는 사람 i의 집과 사무실의 위치이다. 길이 d의 모든 선분 L에 대하여, 집과 사무실의 위치가 모두 L에 포함되는 사람들의 최대 수를 구하는 프로그램을 작성하시오.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/13334/1.png)
그림 1. 8 명의 집과 사무실의 위치

그림 1 에 있는 예를 고려해보자. 여기서 n = 8, (h<sub>1</sub>, o<sub>1</sub>) = (5, 40), (h<sub>2</sub>, o<sub>2</sub>) = (35, 25), (h<sub>3</sub>, o<sub>3</sub>) = (10, 20), (h<sub>4</sub>, o<sub>4</sub>) = (10, 25), (h<sub>5</sub>, o<sub>5</sub>) = (30, 50), 
(h<sub>6</sub>, o<sub>6</sub>) = (50, 60), (h<sub>7</sub>, o<sub>7</sub>) = (30, 25), (h<sub>8</sub>, o<sub>8</sub>) = (80, 100)이고, d = 30이다. 
이 예에서, 위치 10 과 40 사이의 빨간색 선분 L이, 가장 많은 사람들에 대하여 집과 사무실 위치 모두 포함되는 선분 중 하나이다. 따라서 답은 4 이다.

## 입력 

입력은 표준입력을 사용한다. 첫 번째 줄에 사람 수를 나타내는 양의 정수 n (1 ≤ n ≤ 100,000)이 주어진다. 다음 n개의 각 줄에 정수 쌍 (h<sub>i</sub>, o<sub>i</sub>)가 주어진다. 여기서 h<sub>i</sub>와 o<sub>i</sub>는 −100,000,000이상, 100,000,000이하의 서로 다른 정수이다. 마지막 줄에, 철로의 길이를 나타내는 정수 d (1 ≤ d ≤ 200,000,000)가 주어진다.

## 출력 

출력은 표준출력을 사용한다. 길이 d의 임의의 선분에 대하여, 집과 사무실 위치가 모두 그 선분에 포함되는 사람들의 최대 수를 한 줄에 출력한다. 

## 예제 입력 1

```
8
5 40
35 25
10 20
10 25
30 50
50 60
30 25
80 100
30
```

## 예제 출력 1

```
4
```

## 예제 입력 2

```
4
20 80
70 30
35 65
40 60
10
```

## 예제 출력 2

```
0
```

## 예제 입력 3

```
5
-5 5
30 40
-5 5
50 40
5 -5
10
```

## 예제 출력 3

```
3
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(Math.min(p1, p2), Math.max(p1, p2));
        }

        int d = Integer.parseInt(br.readLine());

        System.out.println(query(nodes, d));
    }
    private static int query(Node[] nodes, int d) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        int result = 0;

        Arrays.sort(nodes, (o1, o2) -> o1.end - o2.end);
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].end - d <= nodes[i].start) pq.add(nodes[i]);

            while (!pq.isEmpty() && pq.peek().start < nodes[i].end - d) pq.poll();

            result = Math.max(result, pq.size());
        }

        return result;
    }
}
class Node {
    int start;
    int end;

    Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
```