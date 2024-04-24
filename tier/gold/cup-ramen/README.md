# 컵라면

**골드 2**

상욱 조교는 동호에게 N개의 문제를 주고서, 각각의 문제를 풀었을 때 컵라면을 몇 개 줄 것인지 제시 하였다. 하지만 동호의 찌를듯한 자신감에 소심한 상욱 조교는 각각의 문제에 대해 데드라인을 정하였다.

|문제 번호	|1	|2	|3	|4	|5	|6	|7|
|---|---|---|---|---|---|---|---|
|데드라인	|1	|1	|3	|3	|2	|2	|6|
|컵라면 수	|6	|7	|2	|1	|4	|5	|1|
위와 같은 상황에서 동호가 2, 6, 3, 1, 7, 5, 4 순으로 숙제를 한다면 2, 6, 3, 7번 문제를 시간 내에 풀어 총 15개의 컵라면을 받을 수 있다.

문제는 동호가 받을 수 있는 최대 컵라면 수를 구하는 것이다. 위의 예에서는 15가 최대이다.

문제를 푸는데는 단위 시간 1이 걸리며, 각 문제의 데드라인은 N이하의 자연수이다. 또, 각 문제를 풀 때 받을 수 있는 컵라면 수와 최대로 받을 수 있는 컵라면 수는 모두 231보다 작은 자연수이다.

## 입력 

첫 줄에 숙제의 개수 N (1 ≤ N ≤ 200,000)이 들어온다. 다음 줄부터 N+1번째 줄까지 i+1번째 줄에 i번째 문제에 대한 데드라인과 풀면 받을 수 있는 컵라면 수가 공백으로 구분되어 입력된다.

## 출력 

첫 줄에 동호가 받을 수 있는 최대 컵라면 수를 출력한다.

## 예제 입력 1

```
7
1 6
1 7
3 2
3 1
2 4
2 5
6 1
```

## 예제 출력 1

```
15
```

## 오답 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Queue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (!o1[0].equals(o2[0])) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            pq.add(new Integer[] {deadLine, reward});
        }

        int result = 0;
        for (int i = 0; i <= N; i++) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }

            if (pq.isEmpty()) break;

            result += pq.poll()[1];
        }

        System.out.println(result);
    }
}
```

**반례**
```
4
1 50
2 30
3 60
3 70

답:180
```