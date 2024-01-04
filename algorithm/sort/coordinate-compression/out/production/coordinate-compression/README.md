# 좌표 압축

**실버 2**

|시간 제한|	메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB	|53354	|22403|	17113|	39.853%|

## 문제

수직선 위에 N개의 좌표 X<sub>1</sub>, X<sub>2</sub>, ..., X<sub>N</sub>이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.

Xi를 좌표 압축한 결과 X'<sub>i</sub>의 값은 X<sub>i</sub> > X<sub>j</sub>를 만족하는 서로 다른 좌표의 개수와 같아야 한다.

X<sub>1</sub>, X<sub>2</sub>, ..., X<sub>N</sub>에 좌표 압축을 적용한 결과 X'<sub>1</sub>, X'<sub>2</sub>, ..., X'<sub>N</sub>를 출력해보자.

## 입력

첫째 줄에 N이 주어진다.

둘째 줄에는 공백 한 칸으로 구분된 X<sub>1</sub>, X<sub>2</sub>, ..., X<sub>N</sub>이 주어진다.

## 출력

첫째 줄에 X<sub>1</sub>, X<sub>2</sub>, ..., X<sub>N</sub>을 공백 한 칸으로 구분해서 출력한다

## 제한 

- 1 ≤ N ≤ 1,000,000
- -10<sup>9</sup> ≤ X<sub>i</sub> ≤ 10<sup>9</sup>

## 예제 입력 1

```
5
2 4 -10 4 -9
```

## 예제 출력 1

```
2 3 0 3 1
```

## 예제 입력 2

```
6
1000 999 1000 999 1000 999
```

## 예제 출력 2

```
1 0 1 0 1 0
```

## 문제 풀이 

**핵심: 만약 정확한 값이 필요 없고 값의 대소 관계만 필요하다면, 모든 수를 0 이상 N 미만의 수로 바꿀 수 있습니다.**

- int형 범위: 약 -21억 ~ 21억

현재 좌표가 - 10억 ~ 10억 범위를 가지니깐 10억을 더하면 0 ~ 20억의 범위를 갖는다.

현재 문제점은 정렬을 한다고 했을 때 X 요소들의 위치가 바뀌는 것 때문에 출력하기 힘들어 진다.

**풀이 과정**

1. 객체를 만들어서 현재 좌표 위치와 value를 넣어준다. 
2. value값을 비교해서 정렬을 한다.
3. 정렬을 한 후 작업 배열을 만들어 준 후 반복문을 실행한다.
   1. 해당 반복문에선 자신보다 작은 값들이 몇 개 있는지 체크
   2. 자신의 좌표 위치로 가서 작은 값들이 있는 갯수를 value로 넣어준다.
4. 이후 출력해주면 된다.

**주의 사항**

서로 다른 좌표의 개수이므로 중복되는 숫자는 한 개로 계산

## 나의 풀이

```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
   int idx;
   int value;
   int smallValueCount;

   Point(int idx, int value) {
      this.idx = idx;
      this.value = value;
   }

   @Override
   public int compareTo(Point o) {
      return this.value - o.value;
   }
}

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st;

      int N = Integer.parseInt(br.readLine());

      Point[] points = new Point[N];
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < N; i++) {
         int value = Integer.parseInt(st.nextToken());
         points[i] = new Point(i , value);
      }

      Arrays.sort(points);

      points[0].smallValueCount = 0;
      int count = 0;
      for(int i = 1 ; i < points.length; i++) {
         // 서로 다른 좌표 중에서 작은 값들의 갯수를 계산
         if(points[i].value == points[i - 1].value) points[i].smallValueCount = count;
            // 값이 바뀌는 순간이 선행 조건이기 때문에 count++가 아닌 ++count로 설정
         else points[i].smallValueCount = ++count;
      }

      int[] ans = new int[N];

      for(int i = 0; i < N; i++) {
         ans[points[i].idx] = points[i].smallValueCount;
      }

      for(int i = 0; i < ans.length; i++)
         bw.write(ans[i] + " ");
      bw.flush();
      bw.close();

   }
}
```