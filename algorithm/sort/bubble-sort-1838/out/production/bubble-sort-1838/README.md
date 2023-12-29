# 버블 정렬

**골드 2**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB|	2214|	509|	399	|36.438%|


## 문제


버블 정렬이란 배열에서 서로 인접해 있는 값을 비교해서 작은 값이 더 뒤에 있을 때 두 값을 바꾸어 주는 과정을 계속 반복하는 정렬 방법이다. N개의 서로 다른 정수가 A[0], A[1], ..., A[N-1]의 정수형 배열에 저장되어 있고, 이를 오름차순으로 정렬하기 위해 태국이는 다음과 같은 코드를 작성하였다.

```java
for (i=0; i<N; i++) {
    flag = 0;
    for (j=0; j<N-1; j++) {
        if (A[j] > A[j+1]) {
            flag = 1;
            temp = A[j];
            A[j] = A[j+1];
            A[j+1] = temp;
        }
    }
}
```

하지만 주어진 배열 A에 따라 변수 i가 모든 loop를 반복하지 않아도 정렬이 완료되기도 한다. 따라서 도현이는 다음과 같이 코드를 개선하였다.

```java
for (i=0; i<N; i++) {
    flag = 0;
    for (j=0; j<N-1; j++) {
        if (A[j] > A[j+1]) {
            flag = 1;
            temp = A[j];
            A[j] = A[j+1];
            A[j+1] = temp;
        }
    }
    if (flag == 0) {
        break;
    }
}
```

도현이는 자신이 태국이보다 우월하다는 것을 증명하기 위해, 개선된 코드를 이용하여 주어진 배열 A를 정렬해 보려고 한다. 만일 정렬이 완료되었을 때(즉 for문을 빠져나왔을 때)의 i값이 작으면 작을수록 도현이의 코드가 더 빠른 것이 된다. 태국이를 이기고 싶은 도현이를 도와서, 배열 A에 저장된 수가 주어지면 정렬이 완료되었을 때 변수 i에 저장되어 있는 값을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에는 정수 N(1 ≤ N ≤ 500,000)이 주어진다. 다음 줄에 배열 A를 이루는 N개의 정수가 빈 칸을 사이에 두고 순서대로 주어진다. 주어지는 정수는 절댓값이 2,147,483,647을 넘지 않는다.

## 출력

첫째 줄에 정렬이 완료되었을 때 변수 i에 저장되어 있는 값을 출력한다.

## 예제 입력 1

```
5
30 10 44 27 49
```

## 예제 출력 1

```
2
```

## 풀이 방식

해당 문제는 버블 정렬을 아무리 개선해도 시간 초과가 나와버린다. 다른 방법을 이용해서 i값을 구해야 한다.

i값은 곧 몇번 loop를 했는지 구하는 것이다.

---

**핵심: (앞에서 뒤로 정렬시) 뒤에 있는 요소는 loop마다 한번씩 움직인다.**

객체를 만들어서 처음 입력받을 때의 Index와 Value를 넣어주고 처음 Index와 정렬 후 Index의 차이를 넣어줄 속성으로 총 세 가지의 멤버 변수들을 만들어 놓는다.

그 후 병합정렬을 한다.

병합 정렬을 하면서 마지막에 최종 병합에서 정렬을 하는 동시에 인덱스의 차이를 계산해서 넣어주는 과정을 추가해준다.

**Loop 횟수 = 최초 Index - 정렬 후 Index**

## 틀린 풀이 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }

  static int bubbleSort(int[] nums) {
    int N = nums.length;

    int i;
    int last = N - 1;
    for(i = 0; i < N; i++) {
      int flag = 0;
      int j;
      int target = 0;
      for(j = 0; j < last; j++) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
          flag = 1;
          target = j;
        }
      }
      // 반복문 다 돌고나서 바꿔줘야 한다.
      last = target;
      if (flag == 0) break;
    }
    return i;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(st.nextToken());

    System.out.println(bubbleSort(nums));

  }
}
```

## 나의 코드 

**AC**

| CASE              | 메모리     | 시간     |
|-------------------|---------|--------|
| Merge Sort        | 84864KB | 924ms  |
| Built-In Function | 72380KB | 1616ms |

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Number implements Comparable<Number>{
  int idx;
  int value;
  int swapCount;

  Number(int idx, int value) {
    this.idx = idx;
    this.value = value;
  }

  @Override
  public int compareTo(Number o) {
    return this.value - o.value;
  }
}
public class Main {
  static Number[] buff;

  static void mergeSort(Number[] nums) {
    int n = nums.length;
    buff = new Number[n];

    __mergeSort(nums, 0, n - 1);

    buff = null;
  }

  static void __mergeSort(Number[] nums, int left, int right) {
    if(left < right) {
      int i;
      int p = 0; int j = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p) {
        if(nums[i].value > buff[j].value)
          nums[k] = buff[j++];
        else
          nums[k] = nums[i++];
        if(right == nums.length - 1)
          nums[k].swapCount = nums[k].idx - k;
        k++;
      }

      while (j < p) {
        nums[k] = buff[j++];
        if(right == nums.length - 1)
          nums[k].swapCount = nums[k].idx - k;
        k++;
      }

      while (i <= right) {
        if(right == nums.length - 1)
          nums[k].swapCount = nums[k].idx - k;
        k++; i++;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Number[] nums = new Number[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      nums[i] = new Number(i, Integer.parseInt(st.nextToken()));
    }

    mergeSort(nums);

    int max = Integer.MIN_VALUE;
    for(int i = 0; i < N; i++) {
      if(nums[i].swapCount > max)
        max = nums[i].swapCount;
    }
    System.out.println(max);
  }
}
```

**Bulit-In Function 사용시**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Number implements Comparable<Number>{
  int idx;
  int value;

  Number(int idx, int value) {
    this.idx = idx;
    this.value = value;
  }

  @Override
  public int compareTo(Number o) {
    return this.value - o.value;
  }
}
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Number[] nums = new Number[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      nums[i] = new Number(i, Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(nums);

    int max = Integer.MIN_VALUE;
    for(int i = 0; i < N; i++) {
      if(nums[i].idx - i > max) max = nums[i].idx - i;
    }

    System.out.println(max);

  }
}
```