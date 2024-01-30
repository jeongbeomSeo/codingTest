# 부분합

**골드 4**

|시간 제한	|메모리 제한	|제출	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|
|0.5 초 (하단 참고)|	128 MB|	85685	|23535|	16568|	25.706%|

## 문제

10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

## 출력 

첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.

## 예제 입력 1

```
10 15
5 1 3 5 10 7 4 9 2 8
```

## 예제 출력 1

```
2
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(queryResult(nums, N, S));
    }
    private static int queryResult(int[] nums, int N, int S) {

        long sum = 0;
        int prevPtr = 0;
        int nextPtr = 0;

        int minLength = INF;
        while (prevPtr < N) {
            if (sum >= S) {
                minLength = Math.min(minLength, nextPtr - prevPtr);
                sum -= nums[prevPtr];
                prevPtr++;
            } else {
                if (nextPtr == N) break;
                sum += nums[nextPtr];
                nextPtr++;
            }
        }

        return minLength != INF ? minLength : 0;
    }
}
```

**복습 풀이(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] numArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(queryByTwoPointer(numArray, N, S));
    }
    private static int queryByTwoPointer(int[] numArray, int N, int S) {

        int ptr1 = 0;
        int ptr2 = 0;

        int minLength = Integer.MAX_VALUE;
        int sum = numArray[0];
        while (ptr1 < N) {
            if (sum >= S) {
                minLength = Math.min(minLength, ptr2 - ptr1 + 1);
                sum -= numArray[ptr1++];
            } else {
                if (ptr2 == N - 1) break;
                sum += numArray[++ptr2];
            }
        }

        return minLength != INF ? minLength : 0;
    }
}
```