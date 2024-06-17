# 책 페이지 

**플래티넘 5**

|시간 제한	|메모리 제한	|제출	|정답 |	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	18449	|5959	|4826|	42.799%|

## 문제 

지민이는 전체 페이지의 수가 N인 책이 하나 있다. 첫 페이지는 1 페이지이고, 마지막 페이지는 N 페이지이다. 각 숫자가 전체 페이지 번호에서 모두 몇 번 나오는지 구해보자.

## 출력 

첫째 줄에 0이 총 몇 번 나오는지, 1이 총 몇 번 나오는지, ..., 9가 총 몇 번 나오는지를 공백으로 구분해 출력한다.

## 예제 입력 1

```
11
```

## 예제 출력 1

```
1 4 1 1 1 1 1 1 1 1
```

## 예제 입력 2

```
7
```

## 예제 출력 2

```
0 1 1 1 1 1 1 1 0 0
```

## 예제 입력 3

```
19
```

## 예제 출력 3

```
1 12 2 2 2 2 2 2 2 2
```

## 예제 입력 4

```
999
```

## 예제 출력 4

```
189 300 300 300 300 300 300 300 300 300
```

## 예제 입력 5

```
543212345
```

## 예제 출력 5

```
429904664 541008121 540917467 540117067 533117017 473117011 429904664 429904664 429904664 429904664
```

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] result = new int[10];

        int pow = 0;
        while ((N = convertN(result, N, pow)) != 0) {
            int num = N / 10;
            result[0] -= (int)(Math.pow(10, pow));
            for (int i = 0; i < 10; i++) {
                result[i] += (num + 1) * (int) Math.pow(10, pow);
            }
            pow++;
            N /= 10;
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(result[i] + " ");
        }
    }
    private static int convertN(int[] result, int N, int pow) {

        while (N > 0 && N % 10 != 9) {
            int num = N;
            while (num != 0) {
                int r = num % 10;
                result[r] += (int) Math.pow(10, pow);
                num /= 10;
            }
            N--;
        }

        return N;
    }
}
```