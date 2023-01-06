# 최대공약수와 최소공배수

**브론즈1**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB|	81926	|47315|	38434	|58.323%|

## 문제

두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

## 출력

첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

## 예제 입력 1

```
24 18
```

## 예제 출력 1

```
6
72
```

## 틀린 방법

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int num1 = Integer.parseInt(st.nextToken());
    int num2 = Integer.parseInt(st.nextToken());

    // Greates Common Divosor
    int gcd;
    if(num1 > num2)
      gcd = euclidean(num1, num2);
    else
      gcd = euclidean(num2, num1);

    int num1Div = num1 / gcd;
    int num2DIv = num2 / gcd;

    // Least Common Multiple
    int lcm = num1Div * num2DIv * gcd;
    System.out.println(gcd);
    System.out.println(lcm);
  }
  static int euclidean(int x, int y) {
    if(y == 0)
      return x;
    else
      euclidean(y, x % y);
    return 1;
  }
}

```

**출력**

```
1
432
```

즉, 마지막의 ```return 1```이 실행된 것이다. 

재귀 함수를 들어가는 과정에서 return 하는 것은 전 단계로 나오면서 Return 해주는 것뿐이지,

**모든 과정에서 빠져 나오는 것이 아니다.**

```java
  static int euclidean(int x, int y) {
    if(y == 0)
      return x;
    else
      return euclidean(y, x % y);
  }
```

이와 같은 방법으로 처리할 경우 **return구문에서 함수를 재실행 하는 것이기 때문에 되돌아 가는 일이 없다.**
