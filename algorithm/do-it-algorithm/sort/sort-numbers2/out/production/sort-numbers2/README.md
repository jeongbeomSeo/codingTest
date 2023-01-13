# 수 정렬하기 2

**실버 5**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB|	233837|	67593|	47015|	30.518%|

## 문제

N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

## 입력

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.

## 출력

첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

## 예제 입력 1

```
5
5
4
3
2
1
```

## 예제 출력 1

```
1
2
3
4
5
```

## 오류 코드 

```java
import java.io.*;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }
  static void heapSort(int[] nums) {
    int n = nums.length;

    for(int i = (n - 1) / 2; i >= 0; i--) {
      downHeap(nums, i, n - 1 );
    }

    for(int i = n - 1; i > 0; i--) {
      swap(nums, 0, i);
      downHeap(nums, 0, i - 1);
    }
  }
  static void downHeap(int[] nums, int left, int right) {
    int temp = nums[left];
    int parent;
    int child;

    for(parent = left; parent < (right + 1) / 2; parent = child) {
      int cl = parent * 2 + 1;
      int cr = cl + 1;
      child = (cr <= right && nums[cr] > nums[cl]) ? cr : cl;
      if(temp >= nums[cr]) break;
      nums[parent] = nums[child];
    }
    nums[parent] = temp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(br.readLine());

    heapSort(nums);

    for(int i = 0; i < N; i++)
      bw.write(nums[i] + "\n");
    bw.flush();
    bw.close();
  }
}

```

### 반례 

```
4
1
2
-1
-2
```

**출력**
**ArrayIndexOutOfBoundsException**

원인은 
```if(temp >= nums[cr]) break;```:: **cr -> child**

**정답**

```
-2
-1
1
2
```