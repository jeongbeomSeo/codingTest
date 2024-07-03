# 볼록 껍질

**플래티넘 5**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	|28089|	8326|	4226|	26.284%|

## 문제 

다각형의 임의의 두 꼭짓점을 연결하는 선분이 항상 다각형 내부에 존재하는 다각형을 볼록 다각형이라고 한다. 아래 그림에서 (a)는 볼록 다각형이며, (b)는 볼록 다각형이 아니다.

![](https://www.acmicpc.net/JudgeOnline/upload/201005/convex(1).png)

조금만 생각해 보면 다각형의 모든 내각이 180도 이하일 때 볼록 다각형이 된다는 것을 알 수 있다. 편의상 이 문제에서는 180도 미만인 경우만을 볼록 다각형으로 한정하도록 한다.

2차원 평면에 N개의 점이 주어졌을 때, 이들 중 몇 개의 점을 골라 볼록 다각형을 만드는데, 나머지 모든 점을 내부에 포함하도록 할 수 있다. 이를 볼록 껍질 (CONVEX HULL) 이라 한다. 아래 그림은 N=10인 경우의 한 예이다.

![](https://www.acmicpc.net/JudgeOnline/upload/201005/convv.PNG)

점의 집합이 주어졌을 때, 볼록 껍질을 이루는 점의 개수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 점의 개수 N(3 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 점의 x좌표와 y좌표가 빈 칸을 사이에 두고 주어진다. 주어지는 모든 점의 좌표는 다르다. x좌표와 y좌표의 범위는 절댓값 40,000을 넘지 않는다. 입력으로 주어지는 다각형의 모든 점이 일직선을 이루는 경우는 없다.

## 출력 

첫째 줄에 볼록 껍질을 이루는 점의 개수를 출력한다.

볼록 껍질의 변에 점이 여러 개 있는 경우에는 가장 양 끝 점만 개수에 포함한다.

## 예제 입력 1

```
8
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
```

## 예제 출력 1

```
5
```

## 코드

**AC**

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

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            points[i] = new Point(x, y);
        }

        Point root = new Point(Long.MAX_VALUE, Long.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            if (root.y > points[i].y) root = points[i];
            else if (root.y == points[i].y) {
                if (root.x > points[i].x) root = points[i];
            }
        }

        Point finalRoot = root;
        Arrays.sort(points, (o1, o2) -> {
            int ccw = getCCW(finalRoot, o1, o2);

            if (ccw == 0) {
                long d1 = getDist(finalRoot, o1);
                long d2 = getDist(finalRoot, o2);

                if (d1 > d2) return 1;
                else if (d1 < d2) return -1;
                return 0;
            }
            if (ccw > 0) return -1;
            else return 1;
        });

        Deque<Point> stack = new ArrayDeque<>();
        stack.push(root);

        for (int i = 1; i < N; i++) {
            Point targetPoint = points[i];

            while (stack.size() > 1) {
                Point prevPoint = stack.pop();
                Point prevprevPoint = stack.pop();

                int ccw = getCCW(prevprevPoint, prevPoint, targetPoint);

                stack.push(prevprevPoint);
                if (ccw > 0) {
                    stack.push(prevPoint);
                    break;
                }
            }
            stack.push(targetPoint);
        }

        System.out.println(stack.size());
    }

    private static long getDist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
    private static int getCCW(Point stdP, Point p1, Point p2) {
        long v = (stdP.x * p1.y + p1.x * p2.y + p2.x * stdP.y) - (p1.x * stdP.y + p2.x * p1.y + stdP.x * p2.y);

        if (v > 0) return 1;
        else if (v < 0) return -1;
        return 0;
    }
}
class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
```