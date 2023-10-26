# k번째 수

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	|33203	|12010	|8832	|37.319%|

## 문제 

세준이는 크기가 N×N인 배열 A를 만들었다. 배열에 들어있는 수 A[i][j] = i×j 이다. 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다. B를 오름차순 정렬했을 때, B[k]를 구해보자.

배열 A와 B의 인덱스는 1부터 시작한다.

## 입력 

첫째 줄에 배열의 크기 N이 주어진다. N은 10<sup>5</sup>보다 작거나 같은 자연수이다. 둘째 줄에 k가 주어진다. k는 min(10<sup>9</sup>, N<sup>2</sup>)보다 작거나 같은 자연수이다.

## 출력 

B[k]를 출력한다.

## 예제 입력 1

```
3
7
```

## 예제 출력 1

```
6
```

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());

    System.out.println(binarySearch(1, N * N + 1, N, k));
  }
  static long binarySearch(long left, long right, int N, int k) {
    while (left < right) {
      long mid = (left + right) / 2;

      int count = 0;
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (mid >= i * j) count++;
          else break;
        }
      }

      if (count < k) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());

    System.out.println(binarySearch(1, N * N + 1, N, k));
  }
  static long binarySearch(long left, long right, int N, int k) {
    while (left < right) {
      long mid = (left + right) / 2;

      int count = 0;

      int num = 1;
      while (mid >= num * num) num++;
      count = (num - 1) * (num - 1);

      int nextCount = 0;
      for (int i = num; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (mid >= i * j) nextCount++;
          else break;
        }
      }
      int squareNum = (int)Math.sqrt(mid);
      count += nextCount * 2;
      if (mid - squareNum * squareNum == 0) count++;

      if (count < k) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());
    long K = Long.parseLong(br.readLine());

    System.out.println(binarySearch(1, N * N, N, K));

  }
  static long binarySearch(long left, long right, long N, long K) {

    while (left < right) {
      long mid = (left + right) / 2;

      long count = 0;
      for (int i = 1; i <= N; i++) {
        long divNum = mid / i;
        if (divNum == 0) break;
        count += Math.min(divNum, N);
      }

      if (count < K) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
```