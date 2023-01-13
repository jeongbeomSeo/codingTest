# 통계학

**실버 3**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|256 MB	|124064|	27546|	22149|	25.401%|

## 문제

수를 처리하는 것은 통계학에서 상당히 중요한 일이다. 통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다. 단, N은 홀수라고 가정하자.

1. 산술평균 : N개의 수들의 합을 N으로 나눈 값
2. 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
3. 최빈값 : N개의 수들 중 가장 많이 나타나는 값
4. 범위 : N개의 수들 중 최댓값과 최솟값의 차이

N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 단, N은 홀수이다. 그 다음 N개의 줄에는 정수들이 주어진다. 입력되는 정수의 절댓값은 4,000을 넘지 않는다.

## 출력

첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.

둘째 줄에는 중앙값을 출력한다.

셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.

넷째 줄에는 범위를 출력한다.

## 예제 입력 1

```
5
1
3
8
-2
2
```

## 예제 출력 1

```
2
2
1
10
```

## 예제 입력 2

```
1
4000
```

## 예제 출력 2

```
4000
4000
4000
0
```

## 예제 입력 3

```
5
-1
-2
-3
-1
-2
```

## 예제 출력 3

```
-2
-2
-1
2
```

## 예제 입력 4

```
3
0
0
-1
```

## 예제 출력 4

```
0
0
0
1
```

**cf) (0 + 0 + (-1)) / 3 = -0.333333... 이고 이를 첫째 자리에서 반올림하면 0이다. -0으로 출력하면 안된다.**

## 참고한 사이트

- [[Java] 자바 소수점 n번째 자리까지 반올림하기](https://coding-factory.tistory.com/250)

## 오류 코드

**Count Sort**
```java
import java.io.*;

public class Main {
  static int countSort(int[] nums, int max) {
    int n = nums.length;

    int[] f = new int[max + 1];
    int[] b = new int[n];
    int mode = 0;
    int min = 4000; int secondMin = 4000;

    for(int i = 0; i < n; i++) f[nums[i]]++;
    for(int i = 1; i < f.length; i++) {
      if(f[mode] < f[i]) {
        mode = i;
      }
      if(f[mode] == f[i]) {
        if(min >= i) {
          secondMin = min;
          min = i;
        }
        else {
          if(secondMin > i) secondMin = i;
        }
        mode = secondMin;
      }
      f[i] += f[i - 1];
    }
    for(int i = n - 1; i >= 0; i--) b[--f[nums[i]]] = nums[i];
    for(int i = 0; i < n; i++) nums[i] = b[i];

    return mode;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    double sum = 0;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for(int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
      sum += nums[i];
      if(max < nums[i]) max = nums[i];
      if(min > nums[i]) min = nums[i];
    }


    int avg = (int)Math.round((sum / N));   // 산술 평균
    int mode = countSort(nums, max);        // 최빈 값
    int median = nums[N / 2];               // 중앙 값
    int range = max - min;                  // 범위

    System.out.println(avg);
    System.out.println(median);
    System.out.println(mode);
    System.out.println(range);
  }
}

```

> **❗도수 정렬을 할 때 주의할 점**
> 1. 배열의 요솟값들이 0이상의 정수로 이루어져 있는지 확인
> 2. 수의 범위가 작은지
> 3. max값을 구하기 간단한지


## 나의 코드

**최빈값 구하는 과정에서 애먹었다**
1. 문제를 풀 때 먼저 발생하는 사건(a)과 그 후에 발생하는 사건(b)이 있을 때 case 고려를 잘하자.
   1. a 사건만
   2. a -> b 사건 
   3. a -> b -> a 사건 ( 해당 문제의 경우 **b사건에서 했던 처리**들 중에 초기화 작업이든가 **필요한 작업 확인** )
2. 최소 범위가 음수라서 index로 활용하지 못할 때 **최소 값을 더해줌**으로써 사용 가능

```java
import java.io.*;

public class Main {

  static void shellSort(int[] nums) {
    int n = nums.length;
    int h;
    for(h = 1; h < n / 9; h = h * 3 + 1)
      ;

    for( ; h >= 1; h /= 3) {
      for(int i = h; i < n; i++) {
        int temp = nums[i];
        int j;
        for(j = i - h; j >= 0 && nums[j] > temp; j -= h)
          nums[j + h] = nums[j];
        nums[j + h] = temp;
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    double sum = 0;
    for(int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
      sum += nums[i];
    }

    // 정렬 하기 (오름 차순)
    shellSort(nums);

    int k = 0;
    int mode = 4000;          // 첫번째 최빈값 (가장 작은 값)
    int modeCount = 1;        // 해당 최빈값의 갯수
    int second = 4000;        // 두번째 최빈값 (두 번째로 작은 값)

    while (k < nums.length) {
      int curValue = nums[k++];
      int count = 1;

      while (k < nums.length && curValue == nums[k]) {
        k++;
        count++;
      }

      // 새롭게 갱신
      if(modeCount < count) {
        modeCount = count;
        mode = curValue;
        // 초기화
        second = 4000;
      }
      // 빈도 수 같은 경우
      else if(modeCount == count) {
        if(curValue < mode) {
          second = mode;
          mode = curValue;
        }
        else if(curValue < second)
          second = curValue;
      }
    }

    int avg = (int)Math.round((sum / N));           // 산술 평균
    int median = nums[nums.length / 2];             // 중앙 값
    mode = (second != 4000) ? second : mode;        // 최빈 값
    int range = nums[nums.length -1] - nums[0];     // 범위

    System.out.println(avg);
    System.out.println(median);
    System.out.println(mode);
    System.out.println(range);
  }
}

```

**다른 풀이**

**범위 값에서 ```최소 값을 더해줌으로써 Index를 올려서 사용``` 할 수 있도록 만들어 주는 기술** 

```java
import java.io.*;

public class Main {

  static void shellSort(int[] nums) {
    int n = nums.length;
    int h;
    for(h = 1; h < n / 9; h = h * 3 + 1)
      ;

    for( ; h >= 1; h /= 3) {
      for(int i = h; i < n; i++) {
        int temp = nums[i];
        int j;
        for(j = i - h; j >= 0 && nums[j] > temp; j -= h)
          nums[j + h] = nums[j];
        nums[j + h] = temp;
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    double sum = 0;
    for(int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
      sum += nums[i];
    }

    // 정렬 하기 (오름 차순)
    shellSort(nums);


    // 도수분포표
    // -4000 ~ 4000 까지라서 4000더해서 index 전부 0이상 만들기
    int[] f = new int[8001];
    for(int i = 0; i < nums.length; i++) f[nums[i] + 4000]++;

    int mode = 4000;
    int second = 4000;
    int count = 0;
    for(int i = 0; i < f.length; i++) {
      if(f[i] > count)  {
        mode = i - 4000;
        count = f[i];
        second = 4000;
      }
      else if(f[i] == count) {
        if(mode > i - 4000) {
          second = mode;
          mode = i - 4000;
        }
        else if(i - 4000 < second) second = i - 4000;
      }
    }


    int avg = (int)Math.round((sum / N));           // 산술 평균
    mode = (second != 4000) ? second : mode;        // 최빈 값
    int median = nums[nums.length / 2];             // 중앙 값
    int range = nums[nums.length -1] - nums[0];     // 범위

    System.out.println(avg);
    System.out.println(median);
    System.out.println(mode);
    System.out.println(range);
  }
}

```