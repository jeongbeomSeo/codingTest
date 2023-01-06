# 숫자 문자열과 영단어

## 문제 설명

![img](./img.png)

네오와 프로도가 숫자놀이를 하고 있습니다. 네오가 프로도에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네주면 프로도는 원래 숫자를 찾는 게임입니다.

다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.

- 1478 → "one4seveneight"
- 234567 → "23four5six7"
- 10203 → "1zerotwozero3"

이렇게 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열 **s**가 매개변수로 주어집니다. **s**가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.

참고로 각 숫자에 대응되는 영단어는 다음 표와 같습니다.

|숫자|영단어|
|---|---|
|0|zero|
|1|one|
|2|two|
|3|three|
|4|four|
|5|five|
|6|six|
|7|seven|
|8|eight|
|9|nine|

## 제한사항

- 1 ≤ **s**의 길이 ≤ 50
- **s**가 "zero" 또는 "0"으로 시작하는 경우는 주어지지 않습니다.
- return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 **s**로 주어집니다.

## 입출력 예시 

| s                  | result |
|--------------------|--------|
| "one4seveneight"   | 1478   |
| "23four5six7"      | 234567 |
| "2three45sixseven" | 234567 |
| "123"              | 123    |

## 입출력 예 설명 

**입출력 예 #1**
- 문제 예시와 같습니다.
  
**입출력 예 #2**
- 문제 예시와 같습니다.

**입출력 예 #3**
- "three"는 3, "six"는 6, "seven"은 7에 대응되기 때문에 정답은 입출력 예 #2와 같은 234567이 됩니다.
- 입출력 예 #2와 #3과 같이 같은 정답을 가리키는 문자열이 여러 가지가 나올 수 있습니다.

**입출력 예 #4**
- s에는 영단어로 바뀐 부분이 없습니다.

## 제한사항 안내
- 정확성 테스트 : 10초

## 참고한 사이트
- Java String to Char Array
  - https://www.delftstack.com/ko/howto/java/how-to-convert-a-string-to-char-in-java/
- Java Char to int 
  - https://frhyme.github.io/java/java_basic02_char_to_int/
- Java String to int
  - https://nota.tistory.com/49
## 나의 코드 

- 도중에 코드 짜는 중 문제 발생 
```java
import java.util.ArrayList;

class Solution {
  public int solution(String s) {
    int answer = 0;

    char[] charArray = s.toCharArray();
    ArrayList<Integer> intArray = new ArrayList<>();
    for(int i = 0; i < charArray.length; i++) {
      if(charArray[i] >= 48 && charArray[i] <= 57){
        intArray.add(charArray[i] - '0');
        continue;
      }
      else if(charArray[i] == 'z') {
        intArray.add(0);
        i += 3;
        continue;
      }
      else if(charArray[i] == 'o') {
        intArray.add(1);
        i += 2;
        continue;
      }
      else if (charArray[i] == 't') {
        if(charArray[i + 1] == 'w') {
          intArray.add(2);
          i += 2;
          continue;
        }
        if(charArray[i + 1] == 'h') {
          intArray.add(3);
          i+= 4;
          continue;
        }
      }
      else if (charArray[i] == 'f') {
        if(charArray[i + 1] == 'o' ) {
          intArray.add(4);
          i += 3;
          continue;
        }
        if(charArray[i + 1] == 'i' ) {
          intArray.add(5);
          i += 3;
          continue;
        }
      }
      else if (charArray[i] == 's') {
          if(charArray[i + 1] == 'i' ) {
            intArray.add(6);
            i += 2;
            continue;
          }
          if(charArray[i + 1] == 'e' ) {
            intArray.add(7);
            i += 4;
            continue;
          }
        }
      else if (charArray[i] == 'e') {
          intArray.add(8);
          i += 4;
          continue;
        }
      else if(charArray[i] == 'n') {
          intArray.add(9);
          i += 3;
          continue;
      }
    }

    return answer;
  }
}

배열의 형태로 int값들이 ArrayList에 담겨있을텐데 그 과정에서 하나의 int로 합쳐주는 것을 할 줄 모른다.class 
내 생각엔 String으로 해서 숫자 형태를 가진 String 타입으로 +해서 만든다음 그것을 int로 바꿔줘야 될 것 같다.

- 최종 코드
import java.util.ArrayList;

class Solution {
  public int solution(String s) {
    int answer = 0;

    char[] charArray = s.toCharArray();
    String answerString = "";
    for(int i = 0; i < charArray.length; i++) {
      if(charArray[i] >= 48 && charArray[i] <= 57){
        answerString += charArray[i];
        continue;
      }
      else if(charArray[i] == 'z') {
        answerString += 0;
        // answerString += '0' ? 둘의 차이가 없는 것인가?
        i += 3;
        continue;
      }
      else if(charArray[i] == 'o') {
        answerString += 1;
        i += 2;
        continue;
      }
      else if (charArray[i] == 't') {
        if(charArray[i + 1] == 'w') {
          answerString += 2;
          i += 2;
          continue;
        }
        if(charArray[i + 1] == 'h') {
          answerString += 3;
          i+= 4;
          continue;
        }
      }
      else if (charArray[i] == 'f') {
        if(charArray[i + 1] == 'o' ) {
          answerString += 4;
          i += 3;
          continue;
        }
        if(charArray[i + 1] == 'i' ) {
          answerString += 5;
          i += 3;
          continue;
        }
      }
      else if (charArray[i] == 's') {
        if(charArray[i + 1] == 'i' ) {
          answerString += 6;
          i += 2;
          continue;
        }
        if(charArray[i + 1] == 'e' ) {
          answerString += 7;
          i += 4;
          continue;
        }
      }
      else if (charArray[i] == 'e') {
        answerString += 8;
        i += 4;
        continue;
      }
      else if(charArray[i] == 'n') {
        answerString += 9;
        i += 3;
        continue;
      }
    }

    answer = Integer.parseInt(answerString);
    return answer;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = 	"one4seveneight";

    int answer = solution.solution(input);

    System.out.println("answer = " + answer);
  }
}
```

## 본받아야 될 코드

```java
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder("");
        int len = s.length();
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        String[] alphabets = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0; i<10; i++){
            s = s.replaceAll(alphabets[i],digits[i]);
        }

        return Integer.parseInt(s);
    }
}
```