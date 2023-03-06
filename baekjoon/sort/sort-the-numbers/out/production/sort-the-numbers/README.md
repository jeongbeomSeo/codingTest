# 수 정렬하기

**브론즈 2**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|154069	|87578	|60633|	57.959%|

## 문제 

N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

## 입력

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 절댓값이 1,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.

## 출력

첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

## 예제 입력 1

```
5
5
2
3
4
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

## 나의 코드 

|**Sort**| 메모리     | 시간    |
|---|---------|-------|
|Merge Sort| 16184KB | 164ms |
|Quick Sort| 16000KB | 160ms |
|Heap Sort| 16124KB | 160ms |

**Merge Sort**
```java
import java.io.*;

public class Main {
  static int[] buff;

  static void mergeSort(int[] nums) {
    int n = nums.length;

    buff = new int[n];

    __mergeSort(nums, 0, n - 1);

    buff = null;
  }

  static void __mergeSort(int[] nums, int left, int right) {
    if(left < right) {
      int i;
      int p = 0; int j = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for (i = left; i <= center; i++)
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

    int n = Integer.parseInt(br.readLine());

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    mergeSort(nums);

    for (int i = 0; i < nums.length; i++) {
      bw.write(nums[i] + "\n");
    }
    bw.flush();
    bw.close();
  }
}
```

**Quick Sort**

```java
import java.io.*;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }

  static void quickSort(int[] nums, int left, int right) {
    int pl = left;
    int pr = right;
    int pivot = nums[(left + right) / 2];

    do {
      while (nums[pl] < pivot) pl++;
      while (nums[pr] > pivot) pr--;
      if(pl <= pr)
        swap(nums, pl++, pr--);
    } while (pl <= pr);

    if(left < pr) quickSort(nums, left, pr);
    if(pl < right) quickSort(nums, pl, right);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    quickSort(nums, 0, n - 1);

    for (int i = 0; i < nums.length; i++) {
      bw.write(nums[i] + "\n");
    }
    bw.flush();
    bw.close();
  }
}
```

**Heap Sort**

```java
import java.io.*;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }

  static void heapSort(int[] nums) {
    int n = nums.length;

    for(int i = (n - 1) / 2; i >= 0; i--) {
      downHeap(nums, i, n - 1);
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
      if(nums[child] <= temp) break;
      nums[parent] = nums[child];
    }
    nums[parent] = temp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    heapSort(nums);

    for (int i = 0; i < nums.length; i++) {
      bw.write(nums[i] + "\n");
    }
    bw.flush();
    bw.close();
  }
}

```