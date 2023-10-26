# CCW

**골드 5**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|13013|	7892|	6421|	61.752%|

## 문제 

2차원 좌표 평면 위에 있는 점 3개 P<sub>1</sub>, P<sub>2</sub>, P<sub>3</sub>가 주어진다. P<sub>1</sub>, P<sub>2</sub>, P<sub>3</sub>를 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 P1의 (x<sub>1</sub>, y<sub>1</sub>), 둘째 줄에 P2의 (x<sub>2</sub>, y<sub>2</sub>), 셋째 줄에 P3의 (x<sub>3</sub>, y<sub>3</sub>)가 주어진다. (-10,000 ≤ x<sub>1</sub>, y<sub>1</sub>, x<sub>2</sub>, y<sub>2</sub>, x<sub>3</sub>, y<sub>3</sub> ≤ 10,000) 모든 좌표는 정수이다. P1, P2, P3의 좌표는 서로 다르다.

##  출력 

P<sub>1</sub>, P<sub>2</sub>, P<sub>3</sub>를 순서대로 이은 선분이 반시계 방향을 나타내면 1, 시계 방향이면 -1, 일직선이면 0을 출력한다.

## 예제 입력 1

```
1 1
5 5
7 3
```

## 예제 출력 1

```
-1
```

## 예제 입력 2

```
1 1
3 3
5 5
```

## 예제 출력 2

```
0
```

## 예제 입력 3

```
1 1
7 3
5 5
```

## 예제 출력 3

```
1
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] points = new int[3][2];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      points[i][0] = Integer.parseInt(st.nextToken());
      points[i][1] = Integer.parseInt(st.nextToken());
    }
    int result = ccw(points);
    if (result > 0) System.out.println(1);
    else if (result < 0) System.out.println(-1);
    else System.out.println(0);
  }
  static int ccw(int[][] points) {

    int a = points[0][0] * points[1][1] + points[1][0] * points[2][1] + points[2][0] * points[0][1];
    int b = points[1][0] * points[0][1] + points[2][0] * points[1][1] + points[0][0] * points[2][1];
    return a - b;
  }
}
```