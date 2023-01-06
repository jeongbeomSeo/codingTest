# 괄호 회전하기

## 문제 설명

다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.

- ```()```, ```[]```, ```{}``` 는 모두 올바른 괄호 문자열입니다.
- 만약 ```A```가 올바른 괄호 문자열이라면, ```(A)```, ```[A]```, ```{A}``` 도 올바른 괄호 문자열입니다. 예를 들어, ```[]``` 가 올바른 괄호 문자열이므로, ```([])``` 도 올바른 괄호 문자열입니다.
- 만약 ```A```, ```B```가 올바른 괄호 문자열이라면, ```AB``` 도 올바른 괄호 문자열입니다. 예를 들어, ```{}``` 와 ```([])``` 가 올바른 괄호 문자열이므로, ```{}([])``` 도 올바른 괄호 문자열입니다.

대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 ```s```가 매개변수로 주어집니다. 이 ```s```를 왼쪽으로 x (0 ≤ x < (```s```의 길이)) 칸만큼 회전시켰을 때 ```s```가 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- s의 길이는 1 이상 1,000 이하입니다.

## 입출력 예

- 입력값: 	"[](){}"
  - 기댓값: 3
- 입력값: 	"}]()[{"
  - 기댓값: 2
- 입력값: 	"[)(]"
  - 기댓값: 0
- 입력값: "}}}"
  - 기댓값: 0

## 입출력 예 설명

**입출력 예 #1**

- 다음 표는 "[](){}" 를 회전시킨 모습을 나타낸 것입니다.
  
|x	s|를 왼쪽으로 x칸만큼 회전	|올바른 괄호 문자열?|
|---|---|---|
|0	|"[](){}"	|O|
|1	|"](){}["	|X|
|2	|"(){}[]"	|O|
|3	|"){}[]("	|X|
|4	|"{}[]()"	|O|
|5	|"}[](){"	|X|

- 올바른 괄호 문자열이 되는 x가 3개이므로, 3을 return 해야 합니다.

**입출력 예 #2**

- 다음 표는 ```"}]()[{"``` 를 회전시킨 모습을 나타낸 것입니다.

|x	|s를 왼쪽으로 x칸만큼 회전	|올바른 괄호 문자열?|
|---|---|---|
|0	|"}]()[{"	|X|
|1	|"]()[{}"	|X|
|2	|"()[{}]"	|O|
|3	|")[{}]("	|X|
|4	|"[{}]()"	|O|
|5	|"{}]()["	|X|

- 올바른 괄호 문자열이 되는 x가 2개이므로, 2를 return 해야 합니다.

**입출력 예 #3**

s를 어떻게 회전하더라도 올바른 괄호 문자열을 만들 수 없으므로, 0을 return 해야 합니다.

**입출력 예 #4**

s를 어떻게 회전하더라도 올바른 괄호 문자열을 만들 수 없으므로, 0을 return 해야 합니다.

## 코드 오류 잡기

해당 코드를 봐보자.

```java
import java.util.Stack;

class Solution {
  public int solution(String s) {
    int answer = 0;
    for(int i = 0; i < s.length() - 1; i++) {
      Stack<Character> brackets = new Stack<>();
      boolean check = true;
      for(int j = i; j < s.length() + i; j++) {
        if(j >= s.length()) j %= s.length();
        // 처음에 나오는 것 cut
        if(brackets.empty() && (s.charAt(j) == '}' || s.charAt(j) == ']' || s.charAt(j) == ')') ) {
          check = false;
          break;
        }
        else if(s.charAt(j) == '{' || s.charAt(j) == '[' || s.charAt(j) == '(') brackets.add(s.charAt(j));
        else if(s.charAt(j) == '}') {
          if(brackets.pop() == '{') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(j) == ']') {
          if (brackets.pop() =='[') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(j) == ')') {
          if (brackets.pop() == '(') continue;
          else {
            check = false;
            break;
          }
        }
      }
      if(check) answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = "[](){}";
    System.out.println(solution.solution(input));
  }
}
```

해당 코드를 실행시켜보니, 테스트 1과 테스트 2가 무한 루프를 도는듯 하다.

무엇이 문제일까 디버깅을 해보고 알아냈다.

```java
      for(int j = i; j < s.length() + i; j++) {
        if(j >= s.length()) j %= s.length();
        ...
```

해당 코드를 봐보면, j = i를 처음에 초기값으로 준다. 

하지만, rotate를 하기위해서 이 코드 같은 경우 index 위치를 바꿔준다.

그것을 j를 그대로 사용해서 바꿔줬는데, 그러면서 j++하고 length를 넘어서는 순간 다시 0으로 돌려버린다.

하지만 반복문이 끝나는 조건문 부분을 봐보면 ```s.lenght() + i```이다. 이것은 평생 넘어설 일이 없다.

코드를 수정해 줄 필요가 있다.

## 나의 코드

```java
import java.util.Stack;

class Solution {
  public int solution(String s) {
    int answer = 0;
    for(int i = 0; i < s.length() - 1; i++) {
      Stack<Character> brackets = new Stack<>();
      boolean check = true;
      for(int j = i; j < s.length() + i; j++) {
        int index = j;
        if(index >= s.length()) index %= s.length();
        // 처음에 나오는 것 cut
        if(brackets.empty() && (s.charAt(index) == '}' || s.charAt(index) == ']' || s.charAt(index) == ')') ) {
          check = false;
          break;
        }
        else if(s.charAt(index) == '{' || s.charAt(index) == '[' || s.charAt(index) == '(') brackets.add(s.charAt(index));
        else if(s.charAt(index) == '}') {
          if(brackets.pop() == '{') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ']') {
          if (brackets.pop() =='[') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ')') {
          if (brackets.pop() == '(') continue;
          else {
            check = false;
            break;
          }
        }
      }
      if(check) answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = "[](){}";
    System.out.println(solution.solution(input));
  }
}
```

**채점 결과**

정확성: 92.9

합계: 92.9 / 100.0

해당 코드는 마지막에 만약 ```"[", "{", "("``` 이것들이 나온다면, 그냥 add만 되고 그대로 check는 True인 상태로 나와서 answer++를 실행히킬 것이다.

다음과 같이 수정을 해줬다.

---

```java
import java.util.Stack;

class Solution {
  public int solution(String s) {
    int answer = 0;
    for(int i = 0; i < s.length() - 1; i++) {
      Stack<Character> brackets = new Stack<>();
      boolean check = true;
      for(int j = i; j < s.length() + i; j++) {
        int index = j;
        if(index >= s.length()) index %= s.length();
        // 처음에 나오는 것 cut
        if(brackets.empty() && (s.charAt(index) == '}' || s.charAt(index) == ']' || s.charAt(index) == ')') ) {
          check = false;
          break;
        }
        else if(s.charAt(index) == '{' || s.charAt(index) == '[' || s.charAt(index) == '(') brackets.add(s.charAt(index));
        else if(s.charAt(index) == '}') {
          if(brackets.pop() == '{') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ']') {
          if (brackets.pop() =='[') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ')') {
          if (brackets.pop() == '(') continue;
          else {
            check = false;
            break;
          }
        }
      }
      if(brackets.empty() && check) answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = 	"[](){}";
    System.out.println(solution.solution(input));
  }
}
```