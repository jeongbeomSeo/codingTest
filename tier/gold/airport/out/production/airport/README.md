# 공항

**골드 2**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)	|256 MB|	22840|	8006|	6019|	35.205%|

## 문제

오늘은 신승원의 생일이다.

박승원은 생일을 맞아 신승원에게 인천국제공항을 선물로 줬다.

공항에는 G개의 게이트가 있으며 각각은 1에서 G까지의 번호를 가지고 있다.

공항에는 P개의 비행기가 순서대로 도착할 예정이며, 당신은 i번째 비행기를 1번부터 g<sub>i</sub> (1 ≤ g<sub>i</sub> ≤ G) 번째 게이트중 하나에 영구적으로 도킹하려 한다. 비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇄되고, 이후 어떤 비행기도 도착할 수 없다.

신승원은 가장 많은 비행기를 공항에 도킹시켜서 박승원을 행복하게 하고 싶어한다. 승원이는 비행기를 최대 몇 대 도킹시킬 수 있는가?

## 입력

첫 번째 줄에는 게이트의 수 G (1 ≤ G ≤ 10<sup>5</sup>)가 주어진다.

두 번째 줄에는 비행기의 수 P (1 ≤ P ≤ 10<sup>5</sup>)가 주어진다.

이후 P개의 줄에 gi (1 ≤ g<sub>i</sub> ≤ G) 가 주어진다.

## 출력 

승원이가 도킹시킬 수 있는 최대의 비행기 수를 출력한다.

## 예제 입력 1

```
4
3
4
1
1
```

## 예제 출력 1

```
2
```

## 코드

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        int[] orders = new int[P];
        for (int i = 0; i < orders.length; i++) {
            orders[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> remainGates = new ArrayList<>();
        for (int i = 1; i <= G; i++) {
            remainGates.add(i);
        }

        int count = 0;
        for (; count < Math.min(P, G); count++) {
            int gateNum = orders[count];

            int remainGateIdx = lowerBound(remainGates, 0, remainGates.size(), gateNum);

            if (remainGateIdx == remainGates.size()) break;

            if (remainGates.get(remainGateIdx) <= gateNum) {
                remainGates.remove(remainGateIdx);
            } else if (remainGateIdx > 0) {
                remainGates.remove(remainGateIdx - 1);
            } else {
                break;
            }
        }
        System.out.println(count);
    }
    private static int lowerBound(List<Integer> list, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
```