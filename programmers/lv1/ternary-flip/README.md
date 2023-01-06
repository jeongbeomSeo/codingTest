# 3진법 뒤지기

## 문제 설명

자연수 n이 매개변수로 주어집니다. n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- n은 1 이상 100,000,000 이하인 자연수입니다.

## 입출력 예

- 입력값: 45
  - 기댓값: 7
- 입력값: 125
  - 기댓값: 229

|n	|result|
|---|---|
|45	|7|
|125|	229|

## 입출력 예 설명

**입출력 예 #1**

답을 도출하는 과정은 다음과 같습니다.

| n (10진법)	  |n (3진법)|	앞뒤 반전(3진법)|	10진법으로 표현|
|------------|---|---|---|
| 45	        |1200	|0021|	7|
- 따라서 7을 return 해야 합니다.

**입출력 예 #2**

답을 도출하는 과정은 다음과 같습니다.

| n (10진법) |	n (3진법)|	앞뒤 반전(3진법)|	10진법으로 표현|
|----------|---|---|---|
| 125	     |11122|	22111|	229|
- 따라서 229를 return 해야 합니다.

## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public int solution(int n) {
    int answer = 0;

    ArrayList<Integer> ternary = new ArrayList<>();

    makeFlipTernary(ternary,n);
    String s = "";
    for(int i = 0; i < ternary.size(); i++) {
      s += ternary.get(i);
    }

    answer = Integer.parseInt(s, 3);

    return answer;
  }
  void makeFlipTernary(ArrayList ternary, int n) {
    while (n > 0) {
      ternary.add(n % 3);
      n /= 3;
    }
  }
}
```

## 다른 사람 코드

```java
class Solution {
    public int solution(int n) {
        String a = "";

        while(n > 0){
            a = (n % 3) + a;
            n /= 3;
        }
        a = new StringBuilder(a).reverse().toString();


        return Integer.parseInt(a,3);
    }
}
```

```reverse()```를 사용하지 않고, ```a = a + (n % 3);```이와 같이 해줄 수 있다는 점은 참고하자.