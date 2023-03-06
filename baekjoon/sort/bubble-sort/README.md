# 버블 소트

**플래티넘 5**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|22005|	5991|	3956|	29.562%|

## 문제 

N개의 수로 이루어진 수열 A[1], A[2], …, A[N]이 있다. 이 수열에 대해서 버블 소트를 수행할 때, Swap이 총 몇 번 발생하는지 알아내는 프로그램을 작성하시오.

버블 소트는 서로 인접해 있는 두 수를 바꿔가며 정렬하는 방법이다. 예를 들어 수열이 3 2 1 이었다고 하자. 이 경우에는 인접해 있는 3, 2가 바뀌어야 하므로 2 3 1 이 된다. 다음으로는 3, 1이 바뀌어야 하므로 2 1 3 이 된다. 다음에는 2, 1이 바뀌어야 하므로 1 2 3 이 된다. 그러면 더 이상 바꿔야 할 경우가 없으므로 정렬이 완료된다.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 500,000)이 주어진다. 다음 줄에는 N개의 정수로 A[1], A[2], …, A[N]이 주어진다. 각각의 A[i]는 0 ≤ |A[i]| ≤ 1,000,000,000의 범위에 들어있다.

## 출력

첫째 줄에 Swap 횟수를 출력한다

## 예제 입력 1

```
3
3 2 1
```

## 예제 출력 1

```
3
```

## 풀이 방식

**핵심: 기본 계산 방식은 Index가 i인 요소가 j까지 이동할 때 swap 횟수는 |i - j|이다.**

**풀이 순서**

1. 빠른 정렬 방식으로 정렬된 배열 구하기
2. 반복문 Index n - 1부터 0까지 정렬된 배열 값들과 기존 배열에서 같은 값이 위치하는 index를 찾아서 swap횟수 더해가기
   1. 사잇값들 처리는 두 가지 방식이 존재
      1. 기존 배열에서 index를 앞으로 한칸씩 옮겨주기
      2. 따로 배열을 만들어 놓고 swap이 일어나는 중간 값은 +1씩 나중에 |i - (j + swap)|으로 계산 처리

- 문제점: 해당 방식은 정렬을 하고 나서부터 다시 계산을 하는 것이라서 비효율적이다. 

---

**핵심**
- 병합 정렬을 하면서 요소가 위치가 바뀌는 횟수는 버블 정렬에서의 swap횟수와 일치한다.
- swap 행위 자체가 양 쪽 요소의 index를 바꾸는 것이다. **바뀌는 것은 2개지만 행위는 1번**
  - 병합 정렬에선 left혹은 right를 기준으로 잡고 계산을 하면 된다. (양쪽 값 전부 고려할 필요 X)

**주의할 점**

1. 분할을 하고 병합 하는 과정에서 left에 있는 요소인지 right에 있는 요소인지 체크
2. right에 있다면 right의 첫번째 요소더라도 Index는 (center + 1) 근데, 사실 i는 left부터 시작해서 해당 index값을 그대로 나타내고 있음.  
3. 즉, right에 있는 요소가 k로 배치 될 때 swap 횟수 = **(i- k)**


## 잘못된 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] buff;
  static int swap;
  static void mergeSort(int[] nums) {
    int n = nums.length;

    buff = new int[n];
    swap = 0;

    __mergeSort(nums, 0, n - 1);

    buff = null;
  }

  static void __mergeSort(int[] nums, int left, int right) {
    if(left < right) {
      int i;
      int j = 0; int p = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p) {
        // swap 횟수 계산 후 처리
        // 오른쪽 기준
        if(buff[j] > nums[i]) {
          swap += (i - k);
          nums[k++] = nums[i++];
        }
        else nums[k++] = buff[j++];
      }

      while (j < p) {
        nums[k++] = buff[j++];
      }

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    // N = 0 ~ 500,000
    int[] nums = new int[N];
    for(int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    mergeSort(nums);

    System.out.println(swap);
  }
}
```

현재 N의 경우 0 ~ 500,000의 범위를 가지고 있다. 

만약 전부 순서가 역순으로 되어 있어서 전부 움직여야 한다면 499,999 + 499,998 + ... + 1일 것이다.

즉, (500,000 * 500,000) / 2 이다. 125,000,000,000 > 21억 

따라서, **long형**을 사용해야 한다.

## 나의 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] buff;
  static long swap;
  static void mergeSort(int[] nums) {
    int n = nums.length;

    buff = new int[n];
    swap = 0;

    __mergeSort(nums, 0, n - 1);

    buff = null;
  }

  static void __mergeSort(int[] nums, int left, int right) {
    if(left < right) {
      int i;
      int j = 0; int p = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p) {
        // swap 횟수 계산 후 처리
        // 오른쪽 기준
        if(buff[j] > nums[i]) {
          swap += (i - k);
          nums[k++] = nums[i++];
        }
        else nums[k++] = buff[j++];
      }

      while (j < p) {
        nums[k++] = buff[j++];
      }

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    // N = 0 ~ 500,000
    int[] nums = new int[N];
    for(int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    mergeSort(nums);

    System.out.println(swap);
  }
}
```

