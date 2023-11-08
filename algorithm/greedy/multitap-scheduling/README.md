# 멀티탭 스케줄링

**골드 1**


|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB|	26414	|7087|	5296|	27.018%|

## 문제 

기숙사에서 살고 있는 준규는 한 개의 멀티탭을 이용하고 있다. 준규는 키보드, 헤어드라이기, 핸드폰 충전기, 디지털 카메라 충전기 등 여러 개의 전기용품을 사용하면서 어쩔 수 없이 각종 전기용품의 플러그를 뺐다 꽂았다 하는 불편함을 겪고 있다. 그래서 준규는 자신의 생활 패턴을 분석하여, 자기가 사용하고 있는 전기용품의 사용순서를 알아내었고, 이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법을 고안하여 보다 쾌적한 생활환경을 만들려고 한다.

예를 들어 3 구(구멍이 세 개 달린) 멀티탭을 쓸 때, 전기용품의 사용 순서가 아래와 같이 주어진다면,

1. 키보드
2. 헤어드라이기
3. 핸드폰 충전기
4. 디지털 카메라 충전기
5. 키보드
6. 헤어드라이기

키보드, 헤어드라이기, 핸드폰 충전기의 플러그를 순서대로 멀티탭에 꽂은 다음 디지털 카메라 충전기 플러그를 꽂기 전에 핸드폰충전기 플러그를 빼는 것이 최적일 것이므로 플러그는 한 번만 빼면 된다.

## 입력 

첫 줄에는 멀티탭 구멍의 개수 N (1 ≤ N ≤ 100)과 전기 용품의 총 사용횟수 K (1 ≤ K ≤ 100)가 정수로 주어진다. 두 번째 줄에는 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어진다. 각 줄의 모든 정수 사이는 공백문자로 구분되어 있다.

## 출력 

하나씩 플러그를 빼는 최소의 횟수를 출력하시오.

## 예제 입력 1

```
2 7
2 3 2 3 1 2 7
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
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] products = new int[K];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      products[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(greedy(products, N, K));

  }
  static int greedy(int[] products, int N, int K) {

    int count = 0;
    int[] tap = new int[N];
    boolean[] using = new boolean[K + 1];
    int size = 0;

    for (int i = 0; i < K; i++) {
      if (!using[products[i]]) {
        if (size < N) {
          tap[size++] = products[i];
        }
        else {
          boolean[] isNextUse = new boolean[N];
          int nextUseCount = 0;
          int maxIdx = 0;
          for (int j = i + 1; j < K; j++) {
            if (using[products[j]]) {
              for (int k = 0; k < N; k++) {
                if (tap[k] == products[j]) {
                  if (isNextUse[k]) break;
                  maxIdx = k;
                  isNextUse[k] = true;
                  nextUseCount++;
                  break;
                }
              }
            }
          }
          if (nextUseCount == N) {
            tap[maxIdx] = products[i];
            count++;
          }
          else {
            for (int j = 0; j < N; j++) {
              if (!isNextUse[j]) {
                tap[j] = products[i];
                count++;
                break;
              }
            }
          }
        }
        using[products[i]] = true;
      }
    }
    return count;
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] products = new int[K];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      products[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(greedy(products, N, K));

  }
  static int greedy(int[] products, int N, int K) {

    int count = 0;
    int[] tap = new int[N];
    boolean[] using = new boolean[K + 1];
    int size = 0;

    for (int i = 0; i < K; i++) {
      if (!using[products[i]]) {
        if (size < N) {
          tap[size++] = products[i];
        }
        else {
          boolean[] isNextUse = new boolean[N];
          int nextUseCount = 0;
          int maxIdx = 0;
          for (int j = i + 1; j < K; j++) {
            if (using[products[j]]) {
              for (int k = 0; k < N; k++) {
                if (tap[k] == products[j]) {
                  if (isNextUse[k]) break;
                  maxIdx = k;
                  isNextUse[k] = true;
                  nextUseCount++;
                  break;
                }
              }
            }
          }
          if (nextUseCount == N) {
            using[tap[maxIdx]] = false;
            tap[maxIdx] = products[i];
            count++;
          }
          else {
            for (int j = 0; j < N; j++) {
              if (!isNextUse[j]) {
                using[tap[j]] = false;
                tap[j] = products[i];
                count++;
                break;
              }
            }
          }
        }
        using[products[i]] = true;
      }
    }
    return count;
  }
}
```

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] tap = new int[N];
    int size = 0;
    boolean[] isUsing = new boolean[K + 1];

    int[] items = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      items[i] = Integer.parseInt(st.nextToken());
    }

    int count = 0;
    for (int i = 0; i < K; i++) {
      int item = items[i];
      if (isUsing[item]) continue;

      if (size < N) tap[size++] = item;
      else {
        int nextUsingCount = 0;
        int[] nextUsingIdx = new int[N];
        for (int j = i + 1; j < K; j++) {
          if (isUsing[items[j]]) {
            for (int k = 0; k < N; k++) {
              if (tap[k] == items[j]) {
                if (nextUsingIdx[k] != 0) break;
                nextUsingIdx[k] = j;
                nextUsingCount++;
                break;
              }
            }
          }
        }

        if (nextUsingCount != size) {
          for (int j = 0; j < N; j++) {
            if (nextUsingIdx[j] == 0) {
              isUsing[tap[j]] = false;
              tap[j] = item;
              break;
            }
          }
        }
        else {
          int maxIdx = 0;
          for (int j = 1; j < N; j++) {
            if (nextUsingIdx[j] > nextUsingIdx[maxIdx]) maxIdx = j;
          }
          isUsing[tap[maxIdx]] = false;
          tap[maxIdx] = item;
        }

        count++;
      }
      isUsing[item] = true;
    }
    System.out.println(count);
  }
}
```

**코드 분리**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[] buffer = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      buffer[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(buffer));
  }
  private static int queryResult(int[] buffer) {

    int count = 0;

    int[] tap = new int[N];
    boolean[] isUsing = new boolean[K + 1];

    int tapSize = 0;
    for (int i = 0; i < K; i++) {
      int item = buffer[i];

      if (isUsing[item]) continue;

      if (tapSize != N) {
        tap[tapSize++] = item;
      } else {
        int itemIdxPlugedTap = getRemoveableOutletIdx(tap, buffer, i);
        int itemPlugedTap = tap[itemIdxPlugedTap];

        tap[itemIdxPlugedTap] = item;
        isUsing[itemPlugedTap] = false;
        count++;
      }

      isUsing[item] = true;
    }

    return count;
  }
  private static int getRemoveableOutletIdx(int[] tap, int[] buffer, int curIdx) {

    int[] idxList = new int[N];
    for (int i = 0; i < N; i++) {
      int item = tap[i];
      idxList[i] = getNextUsageIndexOfItem(buffer, item, curIdx);
    }

    return findIndexForOutletRemovalFromArray(idxList);
  }
  private static int findIndexForOutletRemovalFromArray(int[] idxList) {
    int idx = 0;

    if (idxList[idx] == -1) return idx;

    for (int i = 1; i < N; i++) {
      if (idxList[i] == -1) return i;
      else if (idxList[idx] < idxList[i]) {
        idx = i;
      }
    }

    return idx;
  }
  private static int getNextUsageIndexOfItem(int[] buffer, int item, int curIdx) {

    for (int i = curIdx + 1; i < K; i++) {
      if (buffer[i] == item) return i;
    }

    return -1;
  }
}
```