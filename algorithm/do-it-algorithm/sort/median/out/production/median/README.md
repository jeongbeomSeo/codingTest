# 중앙값

**플래티넘 5**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB	|4853	|1513|	1050|	34.988%|

## 문제

중앙값이란, 수열을 정렬했고, 그 크기가 N일 때, 1부터 시작해서 (N+1)/2번째 있는 원소가 그 수열의 중앙값이다. 예를 들어, {1, 2, 6, 5, 4, 3}에서는 3이고, {11, 13, 12, 15, 14}에서는 13이다.

오세준은 1초에 온도를 하나씩 재는 온도계를 만들었다. 이 온도계에는 작은 디스플레이 창이 하나 있는데, 이 창에는 지금부터 최근 K초 까지 온도의 중앙값을 표시해 준다. (온도를 재기시작한지 K초부터 표시한다. 그 전에는 아무것도 출력되지 않는다.)

오세준은 온도를 N초동안 쟀다. 그 시간 동안 온도계의 디스플레이 창에 뜨는 숫자의 합을 구하는 프로그램을 작성하시오.

다른 말로 하면, 길이가 N인 수열이 주어진다. 이 수열은 N-K+1 개의 길이가 K인 연속된 부분 수열이 존재한다. 이 부분 수열의 중앙값의 합을 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에 N과 K가 주어진다. N은 250,000보다 작거나 같은 자연수이고, K는 5,000보다 작거나 같은 자연수이다. N은 항상 K보다 크거나 같다. 둘째 줄부터 N개의 수가 한 줄에 하나씩 주어진다. 입력으로 주어지는 수는 65536보다 작거나 같은 자연수 또는 0이다.

## 출력 

첫째 줄에 정답을 출력한다. 정답은 2<sup>63</sup>-1보다 작거나 같다.

## 예제 입력 1

```
10 3
3
4
5
6
7
8
9
10
11
12
```

## 예제 출력 1

```
60
```

## 예제 입력 2

```
5 2
10
13
13
13
13
```

## 예제 출력 2

```
49
```

## 예제 입력 3

```
7 3
4123
19382
23581
23040
1743
18362
60593
```

## 예제 출력 3

```
102186
```

## 문제 풀이

1. N 과 K를 입력 받고 길이가 K인 배열을 생성해 놓는다.
2. N을 입력 받을때 길이가 K인 배열에 넣어주고 다 찼을 때 부터 함수를 작동 시킨다.
   1. 정렬
   2. 중앙값 추출
   3. 가장 먼저 입력 받은 숫자 추출 후 새로 숫자 하나 입력 받기
   4. 재정렬 
> 사실 이 과정에서 중요한 점이 두 가지 존재한다.
> 1. 가장 먼저 입력 받은 숫자를 어떻게 알아내고 처리할 것인가?
> 2. 기존에 정렬되어 있던 배열에 숫자 하나를 새롭게 받고 어떻게 재정렬을 할 것인가?
> 이 두 가지가 시간과 메모리 사용의 효율성을 차지할 것이다.

**세부 사항** 
1. 먼저 N개의 배열(a[N])을 만들어 놓고 순차적으로 입력 받으면서 길이가 K인 배열(a[K])도 따로 만들어서 넣어준다. 
2. a[K]가 전부 차면 정렬 후 중앙값 추출
3. a[N]에서 확인해서 가장 먼저 빠질 숫자 추출 동시에 해당 index에 새로운 값 입력 받아 넣어주기

해당 문제의 경우 같은 값의 순서는 고려하지 않는다. 

그래서 단순하게, a[N]에서 값을 확인해서 넣어준 요소 순서와 상관없이 값만 같으면 하나 빼주고 해당 index에 새로운 값을 넣어주면 된다.

이후 정렬은 **단순 삽입 정렬**을 이용해서 정렬 해주면 된다.

**순서는 다음과 같이 이루어 진다.**

1. **먼저 앞의 요소와 비교했을 때 작다면** 앞으로 이동하면서 크면 뒤의 요소와 비교.

2. **뒤의 요소와 비교했을 때 크면** 뒤로 이동 작으면 해당 위치에 두면 된다.

3. **값이 앞이나 뒤 요소랑 같을 시**에도 해당 위치에 두면 된다.

만약, **넣어준 순서가 중요한 경우**의 풀이를 생각해보면 다음과 같다.
   1. a[K]안에 존재하지 않는 숫자가 들어갈 때 
   2. a[K]안에 존재하는 숫자가 들어갈 때
> 기본적으로 이 두 case가 작동하기 전에 고려할 것은 당연히 이러한 case가 존재하니 값이 같은 숫자가 a[K]에 존재하고 그것이 이번 턴에 빠져야 되는 숫자라고 해보자.
> 
> 과연 무슨 숫자를 빼야할 것인가? 그래서 이것을 처리하기 위해서 먼저 넣은 숫자는 앞에 위치시키고 늦게 넣은 숫자는 뒤에 위치시키는 것을 규칙으로 설정
> 
> 이제 Case처리를 해보자.
> 
> 먼저 두 case모두 넣고 정렬하는 방식은 다음과 같다.
> 
> 1. 빠진 요소의 Index에 새로 받은 요소를 추가해 준다.
> 2. 뒤의 요소와 숫자를 비교해보고 크기가 작으면 해당 숫자만 앞으로 **단순 삽입 정렬** 반대의 경우 뒤로 해주면 된다.(**뒤의 요소와 확인하는 이유**: **앞의 요소와 비교시 크기가 같은 요소가 나오면** 뒤의 요소를 확인해야 되서 **탐색 방향이 전환되어 버림**)
> > 이때 숫자가 같은 경우는, 숫자가 더 큰 요소가 나올때 까지 뒤로 삽입 정렬을 해주면 된다. 
> 
> 위의 방식은 바로 근처 숫자 크기만 비교한 경우를 확인한 것이다.
> 
> 만약, a[K]에 넣고 이동하다가 같은 값의 존재를 확인할 경우. 즉 2번의 Case인 경우 구분해줘야 한다.
> 
> **1번의 Case의 경우** 그냥 넣어주고 정렬하면 된다. 
> 
> **2번의 Case의 경우** 위에서 설정해둔 규칙에 따라서 요소를 앞으로 이동하다가 같은 값이 나올 경우 멈추고 해당 index에 위치 시킨다.
> 
> 뒤로 이동하다가 나올 경우에는 더 큰 값을 마주칠 때 까지 계속 이동시킨다.

## 잘못된 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static void backSort(int temp, int[] sortTemp, int idx, int K) {
    int j;
    for(j = idx; j < K - 1 && sortTemp[j + 1] < temp; j++)
      sortTemp[j] = sortTemp[j + 1];
    sortTemp[j] = temp;
  }

  static void frontSort(int temp, int[] sortTemp, int idx, int K) {
    int j;
    for(j = idx; j > 0 && sortTemp[j - 1] > temp; j--)
      sortTemp[j] = sortTemp[j - 1];
    sortTemp[j] = temp;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // 배열 두 개 초기화(입력 받은 대로의 배열, 길이가 K인 수열)
    int[] timeTemp = new int[N];
    int[] sortTemp = new int[K];

    long sum = 0;

    for(int i = 0; i < N; i++) {
      int temp = Integer.parseInt(br.readLine());
      timeTemp[i] = temp;

      // time < K || time == K
      if(i <= K - 1) {
        sortTemp[i] = temp;
        // 중앙 값 표시 시작
        if(i == K - 1) {
          Arrays.sort(sortTemp);
          sum += sortTemp[(K - 1)/ 2];
        }
      }

      // time > K
      else if(i > K - 1) {
        // timeTemp에서 (i - K)위치에 있는 요소의 값과 같은 요소를 sortTemp에서 추출하고 temp 넣어주기
        int idx = 0;

        for(int j = 0; j < sortTemp.length; j++)
          if(sortTemp[j] == timeTemp[i - K]) {
            idx = j;
            sortTemp[j] = temp;
            break;
          }

        // 정렬 해주기 (양쪽 끝 index 처리 후 나머지 처리)
        if(idx == 0 || idx < K - 1 && sortTemp[idx] > sortTemp[idx + 1]) backSort(temp, sortTemp, idx, K);

        else frontSort(temp, sortTemp, idx, K);
        // 새로운 중앙 값 합 처리
        sum += sortTemp[K / 2];
      }

    }

    System.out.println(sum);
  }
}

```

**반례**

```
4 2
4
3
2
1
```

**출력**

```
8
```

**정답**

```
9
```
## 참고한 사이트

- [글자 수 byte제한이 65536인 이유 : 네이버 블로그](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kmh03214&logNo=221481511037)