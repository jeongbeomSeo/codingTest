# 카드 게임

**플래티넘 5**

|시간 제한|	메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1.2 초|	512 MB	|6519|	2389|	1605|	

## 문제 

철수와 민수는 카드 게임을 즐겨한다. 이 카드 게임의 규칙은 다음과 같다.

1. N개의 빨간색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호가 매겨져 있다. 이 중에서 M개의 카드를 고른다.
2. N개의 파란색 카드가 있다. 각각의 카드는 순서대로 1부터 N까지 번호가 매겨져 있다. 이 중에서 빨간색에서 고른 번호와 같은 파란색 카드 M개를 고른다.
3. 철수는 빨간색 카드를 가지고 민수는 파란색 카드를 가진다.
4. 철수와 민수는 고른 카드 중에 1장을 뒤집어진 상태로 낸다. 그리고 카드를 다시 뒤집어서 번호가 큰 사람이 이긴다. 이 동작을 K번 해서 더 많이 이긴 사람이 최종적으로 승리한다. 한 번 낸 카드는 반드시 버려야 한다.

철수는 뛰어난 마술사이기 때문에 본인이 낼 카드를 마음대로 조작할 수 있다. 즉, 카드를 버리고 민수 몰래 다시 들고 온다거나 민수한테 없는 카드를 내기도 한다.

민수는 뛰어난 심리학자이기 때문에 철수가 낼 카드를 알아낼 수 있다. 그래서 민수는 철수가 낼 카드보다 큰 카드가 있다면 그 카드들 중 가장 작은 카드를 내기로 했다.

K번 동안 철수가 낼 카드가 입력으로 주어진다. 그렇다면 민수가 어떤 카드를 낼지 출력하라. 단, 민수가 카드를 내지 못하는 경우는 없다고 가정한다.

## 입력 

첫째 줄에 세 개의 자연수 N, M, K가 주어진다. (1 ≤ M ≤ N ≤ 4,000,000, 1 ≤ K ≤ min(M, 10,000))

다음 줄에 카드의 번호를 나타내는 M개의 자연수가 주어진다. 각각의 수들은 1 이상이고 N 이하이며 서로 다르다.

다음 줄에 K개의 자연수가 주어진다. i번째 수는 철수가 i번째로 내는 카드의 번호이다. 철수가 내는 카드 역시 1 이상 N 이하이다.

## 출력 

K줄에 걸쳐서 수를 출력한다. i번째 줄에는 민수가 i번째로 낼 카드의 번호가 출력되어야 한다.

## 예제 입력 1

```
10 7 5
2 5 3 7 8 4 9
4 1 1 3 8
```

## 예제 출력 1

```
5
2
3
4
9
```

## 코드 

**AC**

```java
import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] orders = new int[K];
        for (int i = 0; i < K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int[] disJointSet = new int[M];
        for (int i = 0; i < M; i++) {
            disJointSet[i] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < orders.length; i++) {
            int idx = upperBound(cards, 0, cards.length, orders[i]);

            int notUsedIdx = getNotUsedIdx(disJointSet, idx);
            bw.write(cards[notUsedIdx] + "\n");
            disJointSet[notUsedIdx] = notUsedIdx + 1;
        }

        bw.flush();
        bw.close();
    }
    private static int upperBound(int[] cards, int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (cards[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private static int getNotUsedIdx(int[] disJointSet, int idx) {
        if (disJointSet[idx] == idx) return disJointSet[idx];

        return disJointSet[idx] = getNotUsedIdx(disJointSet, disJointSet[idx]);
    }
}
```