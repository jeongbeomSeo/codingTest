# 좌표 정렬하기

**실버 5**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|98225	|46519|	36009|	47.905%|

## 문제

2차원 평면 위의 점 N개가 주어진다. 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.

## 출력 

첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.

## 예제 입력 1

```
5
3 4
1 1
1 -1
2 2
3 3
```

## 예제 출력 1

```
1 -1
1 1
2 2
3 3
3 4
```

## 나의 코드

```java
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Point o) {
    if(this.x - o.x != 0) return this.x - o.x;
    else return this.y - o.y;
  }

  Comparator<Point> comparator = new Comparator<Point>() {
    @Override
    public int compare(Point o1, Point o2) {
      if(o1.x - o2.x != 0) return o1.x - o2.x;
      else return o1.y - o2.y;
    }
  };

}



public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Point[] points = new Point[N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      points[i] = new Point(x, y);
    }

    Arrays.sort(points);

    for(int i = 0; i < N; i++) {
      bw.write(points[i].x + " " + points[i].y + "\n");
    }
    bw.flush();
    bw.close();
  }
}

```

## 참고한 사이트 

- [[Java] 객체 정렬하기 1부 - Comparable vs Comparator | Engineering Blog by Dale Seo](https://www.daleseo.com/java-comparable-comparator/)