# 수 정렬하기 3

**브론즈 1**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|5 초 (하단 참고)	|8 MB (하단 참고)|	210995|	49031|	37073	|23.512%|

## 문제 

N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

## 입력

첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.

## 출력

첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

## 예제 입력 1

```
10
5
2
3
1
4
2
3
5
1
7
```

## 예제 출력 1

```
1
1
2
2
3
3
4
5
5
7
```

## 시간 제한 

- Java 8: 3 초
- Java 8 (OpenJDK): 3 초
- Java 11: 3 초
- Kotlin (JVM): 3 초
- Java 15: 3 초

## 메모리 제한 
- Java 8: 512 MB
- Java 8 (OpenJDK): 512 MB
- Java 11: 512 MB
- Kotlin (JVM): 512 MB
- Java 15: 512 MB

## 나의 코드

**Countint Sort**

**AC**

```java
import java.io.*;

public class Main {
  static int max = 10001;
  static void countSort(int[] nums) {
    int n = nums.length;

    int[] f = new int[max];
    int[] b = new int[n];

    for(int i = 0; i < n; i++) f[nums[i]]++;
    for(int i = 1; i < f.length; i++) f[i] += f[i - 1];
    for(int i = n - 1; i >= 0; i--) b[--f[nums[i]]] = nums[i];
    for(int i = 0; i < n; i++) nums[i] = b[i];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(br.readLine());

    countSort(nums);

    for(int i = 0; i < N; i++)
      bw.write(nums[i] + "\n");
    bw.flush();
    bw.close();
  }
}
```

**TLE**

```java
import java.io.*;

public class Main {
  static int[] buff;
  static void mergeSort(int[] nums){
    int n = nums.length;
    buff = new int[n];

    __mergeSort(nums, 0, n - 1);

    buff = null;

  }
  static void __mergeSort(int[] nums, int left,int right) {
    if(left < right) {
      int i;
      int p = 0; int j = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p)
        nums[k++] = (buff[j] > nums[i]) ? nums[i++] : buff[j++];

      while (j < p)
        nums[k++] = buff[j++];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(br.readLine());

    mergeSort(nums);

    for(int i = 0; i < N; i++)
      bw.write(nums[i] + "\n");
    bw.flush();
    bw.close();
  }
}

```

**수의 범위가 작다면 카운팅 정렬을 사용하여 더욱 빠르게 정렬할 수 있습니다.**