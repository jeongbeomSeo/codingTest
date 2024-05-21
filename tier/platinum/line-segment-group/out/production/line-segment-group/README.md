# 선분 그룹

**플래티넘 5**

|시간 제한|	메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	|16141|	4817	|3312	|28.245%|

## 문제

N개의 선분들이 2차원 평면상에 주어져 있다. 선분은 양 끝점의 x, y 좌표로 표현이 된다.

두 선분이 서로 만나는 경우에, 두 선분은 같은 그룹에 속한다고 정의하며, 그룹의 크기는 그 그룹에 속한 선분의 개수로 정의한다. 두 선분이 만난다는 것은 선분의 끝점을 스치듯이 만나는 경우도 포함하는 것으로 한다.

N개의 선분들이 주어졌을 때, 이 선분들은 총 몇 개의 그룹으로 되어 있을까? 또, 가장 크기가 큰 그룹에 속한 선분의 개수는 몇 개일까? 이 두 가지를 구하는 프로그램을 작성해 보자.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 3,000)이 주어진다. 둘째 줄부터 N+1번째 줄에는 양 끝점의 좌표가 x1, y1, x2, y2의 순서로 주어진다. 각 좌표의 절댓값은 5,000을 넘지 않으며, 입력되는 좌표 사이에는 빈칸이 하나 있다.

## 출력 

첫째 줄에 그룹의 수를, 둘째 줄에 가장 크기가 큰 그룹에 속한 선분의 개수를 출력한다.

## 예제 입력 1

```
3
1 1 2 3
2 1 0 0
1 0 1 1
```

## 예제 출력 1

```
1
3
```

## 예제 입력 2

```
3
-1 -1 1 1
-2 -2 2 2
0 1 -1 0
```

## 예제 출력 2

```
2
2
```

## 잘못된 풀이

해당 풀이의 접근 방법은 선분 그룹을 집합으로 구성하여 현재 집합에 들어가 있는 선분들과 비교해가며 집합을 찾아가는 방식의 풀이다.

하지만, 이 풀이의 잘못된 부분은 현재 선분이 어떤 선분 그룹으로 들어가는 것을 결정하는 로직에서 아직 집합에 들어가 있지 않는 선분과는 비교하지 않는다는 점이다.

예를 들어, 1 2 3번 선분들이 있는데 해당 선분들은 각각 1번과 3번이 교차하고, 2번과 3번이 교차한다고 가정해보자.

아래의 로직대로 흘러가면, 1번이 새로운 집합을 만들고 2번이 자신의 집합을 찾아갈 때 1번과 교차하지 않기 때문에 새로운 집합으로 들어가게 된다.

이후 3번은 1번 혹은 2번이 있는 집합으로 들어가게 되면서 결국 예상했던 것과는 다르게 로직이 흘러가게 된다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] lines = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                lines[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Set<List<int[]>> lineGroups = new HashSet<>();

        for (int i = 0; i < lines.length; i++) {
            int[] line = lines[i];

            boolean isNewGroup = true;
            for (List<int[]> lineGroup : lineGroups) {
                for (int j = 0; j < lineGroup.size(); j++) {
                    if (isCross(lineGroup.get(j), line)) {
                        isNewGroup = false;
                        break;
                    }
                }
                if (!isNewGroup) {
                    lineGroup.add(line);
                    break;
                }
            }

            if (isNewGroup) {
                List<int[]> newGroup = new ArrayList<>();
                newGroup.add(line);
                lineGroups.add(newGroup);
            }
        }

        int maxSize = 0;
        for (List<int[]> lineGroup : lineGroups) {
            maxSize = Math.max(maxSize, lineGroup.size());
        }
        System.out.println(lineGroups.size());
        System.out.println(maxSize);
    }
    private static boolean isCross (int[] line1, int[] line2) {

        int firstCondition = ccw(line1[0], line1[1], line1[2], line1[3], line2[0], line2[1]) * ccw(line1[0], line1[1], line1[2], line1[3], line2[2], line2[3]);
        int secondCondition = ccw(line2[0], line2[1], line2[2], line2[3], line1[0], line1[1]) * ccw(line2[0], line2[1], line2[2], line2[3], line1[2], line1[3]);

        if (firstCondition == 0 && secondCondition == 0) {
            if (Math.min(line1[0], line1[2]) > Math.max(line2[0], line2[2]) || Math.min(line1[1], line1[3]) > Math.max(line2[1], line2[3])) {
                return false;
            }
            if (Math.min(line2[0], line2[2]) > Math.max(line1[0], line1[2]) || Math.min(line2[1], line2[3]) > Math.max(line1[1], line1[3])) {
                return false;
            }
            return true;
        }

        return firstCondition <= 0 && secondCondition <= 0;
    }

    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int result = x1 * y2 + x2 * y3 + x3 * y1 - (x1 * y3 + x2 * y1 + x3 * y2);
        return Integer.compare(result, 0);
    }
}
```