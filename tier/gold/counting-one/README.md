# 1의 개수 세기

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	8121|	3101|	2467|	41.511%|

## 문제

두 자연수 A, B가 주어졌을 때, A ≤ x ≤ B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때 1의 개수의 합을 구하는 프로그램을 작성하시오.

즉, f(x) = x를 이진수로 표현 했을 때 1의 개수라고 정의하고, 아래 식의 결과를 구하자.

## 입력 

첫 줄에 두 자연수 A, B가 주어진다. (1 ≤ A ≤ B ≤ 10<sup>16</sup>)

## 출력 

1의 개수를 세어 출력한다.

## 예제 입력 1

```
2 12
```

## 예제 출력 1

```
21
```

## 풀이

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(sumCount(B) - sumCount(A) + Long.bitCount(A));
    }
    private static long sumCount(long target) {

        long sum = 1;

        long ptr = 1;
        while (ptr < target) {
            sum *= 2;
            sum += (ptr + 1);
            ptr += (ptr + 1);
        }

        while (ptr != target) {
            sum -= Long.bitCount(ptr--);
        }

        return sum;
    }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[] counts = getCounts(B);

        //System.out.println(count(counts, 1));
        System.out.println(count(counts, B) - count(counts, A - 1));
    }
    private static long count(long[] counts, long num) {

        long sum = 0L;
        while (num > 0) {
            int i = 0;
            while ((1L << (i + 1)) - 1 <= num) i++;

            // System.out.println(i);
            sum += counts[i] + num - (1 << i) + 1;
            num -= (1 << i);
        }

        return sum;
    }
    private static long[] getCounts(long max) {
        long[] counts = new long[(int)(Math.log10(max) / Math.log10(2)) + 1];

        counts[1] = 1;
        for (int i = 2; i < counts.length; i++) {
            counts[i] = 2 * counts[i - 1] + (long)Math.pow(2, i - 1);
        }
/*        for (long count : counts) {
            System.out.print(count + " ");
        }*/

        return counts;
    }
}
```

**AC**

주의할 점은 `(1 << i)` 연산 시 int 자료형을 넘어설 수 있으므로 `(1L << i)` 와 같이 해야된다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[] counts = getCounts();

//        System.out.println(query(counts, B));
        System.out.println(query(counts, B) - query(counts, A - 1));
    }
    private static long query(long[] counts, long num) {

        long result = 0L;
        for (int i = counts.length - 1; i >= 0; i--) {
            if ((num & (1L << i)) != 0) {
                result += counts[i] + (num - (1L << i) + 1);
                num -= (1L << i);
            }
        }
        return result;
    }
    private static long[] getCounts() {

        long[] counts = new long[63];
        counts[1] = 1;

        for (int i = 2; i < counts.length; i++) {
            counts[i] = 2 * counts[i - 1] + (long)Math.pow(2, i - 1);
        }

 /*       for (long count : counts) {
            System.out.println(count);
        }*/

        return counts;
    }
}
```