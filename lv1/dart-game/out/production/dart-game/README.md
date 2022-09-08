# 다트 게임

## 문제 설명

카카오톡에 뜬 네 번째 별! 심심할 땐? 카카오톡 게임별~

![dart game](./dart-game.png)

카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다. 다트 게임은 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임으로, 모두가 간단히 즐길 수 있다.
갓 입사한 무지는 코딩 실력을 인정받아 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다. 다트 게임의 점수 계산 로직은 아래와 같다.

1. 다트 게임은 총 3번의 기회로 구성된다.
2. 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
3. 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱 (점수1 , 점수2 , 점수3 )으로 계산된다.
4. 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스된다.
5. 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
6. 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
7. 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
8. Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
9. 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.
10. 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.

## 입력 형식

"점수|보너스|[옵션]"으로 이루어진 문자열 3세트.
예) ```1S2D*3T```

- 점수는 0에서 10 사이의 정수이다.
- 보너스는 S, D, T 중 하나이다.
- 옵선은 *이나 # 중 하나이며, 없을 수도 있다.

## 출력 형식

3번의 기회에서 얻은 점수 합계에 해당하는 정수값을 출력한다.
예) 37

## 입출력 예제

- 입력값: "1S2D*3T"
  - 기댓값: 	37

- 입력값: 	"1D2S#10S"
  - 기댓값: 9

- 입력값: "1D2S0T"
  - 기댓값: 3

- 입력값: "1S*2T*3S"
  - 기댓값: 23

- 입력값: "1D#2S*3S"
  - 기댓값: 5

- 입력값: "1T2D3D#"
  - 기댓값: -4

- 입력값: 	"1D2S3T*"
  - 기댓값: 59

|예제|	dartResult|	answer| 	설명                                                           |
|---|---|---|---------------------------------------------------------------|
|1|	1S2D*3T|	37| 	1<sup>1</sup> * 2 + 2<sup>2</sup> * 2 + 3<sup>3</sup>        |
|2|	1D2S#10S|	9| 	1<sup>2</sup> + 2<sup>1</sup> * (-1) + 10<sup>1</sup>        |
|3|	1D2S0T|	3	| 1<sup>2</sup> + 2<sup>1</sup> + 0<sup>3</sup>                 |
|4|	1S*2T*3S|	23| 	1<sup>1</sup> * 2 * 2 + 2<sup>3</sup> * 2 + 3<sup>1</sup>    |
|5|	1D#2S*3S|	5| 	1<sup>2</sup> * (-1) * 2 + 2<sup>1</sup> * 2 + 3<sup>1</sup> |
|6|	1T2D3D#|	-4| 	1<sup>3</sup> + 2<sup>2</sup> + 3<sup>2</sup> * (-1)         |
|7|	1D2S3T*|	59| 	1<sup>2</sup> + 2<sup>1</sup> * 2 + 3<sup>3</sup> * 2        |

## 필요 개념

## 참고한 사이트
- subStirng
  - https://jamesdreaming.tistory.com/81

- String -> char -> int
  - https://sunow.tistory.com/entry/JAVA-String%EC%9D%84-char%EB%A1%9C-char%EB%A5%BC-int%EB%A1%9C
- AscII code 
  - https://stepbystep1.tistory.com/10
- Java 거듭 제곱
  - https://coding-factory.tistory.com/531

## 오류 잡기

```java
class Solution {
  public int solution(String dartResult) {
    int answer = 0;


      else if(dartResult.indexOf('*') == 3 || dartResult.indexOf('#') == 3 ) {
        number[i] = dartResult.substring(0, 3);
        continue;    String[] number = new String[3];
        int[] intNum = new int[3];

        for(int i = 0; i < number.length; i++) {
          if(dartResult.indexOf('*') == -1 && dartResult.indexOf('#') == -1) {
            number[0] = dartResult.substring(0, 2);
            number[1] = dartResult.substring(2, 4);
            number[2] = dartResult.substring(4, 6);
            break;
          }
      }
      number[i] = dartResult.substring(0, 2);
      dartResult = dartResult.substring(2);
    }

    for(int i = 0; i < number.length; i++){
      int num;
      if(number[i].length() == 4) num = 10;
      else num = (int)number[i].charAt(0);
      
      if(number[i].charAt(1) == 'D') num *= num;
      else if(number[i].charAt(1) == 'T') num *= num * num;

      if(number[i].length() == 3) {
        if(number[i].charAt(3) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(3) == '*') intNum[i -1] *= 2;
          else intNum[i -1] *= (-1);
        }
      }
      intNum[i] = num;
    }

    answer = intNum[0] + intNum[1] + intNum[2];

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String dartResult = "1S2D*3T";
    System.out.println(solution.solution(dartResult));
  }
}
```

해당 코드와 같이 코드를 짜보았지만, 결과적으로 위에 subString하는과정에서 10이라는 숫자가 들어가는 경우를 재대로 생각하지 않았다.

## 나의 코드

```java
class Solution {
  public int solution(String dartResult) {
    int answer = 0;

    String[] number = new String[3];
    int[] intNum = new int[3];

    if(dartResult.indexOf('*') == -1 && dartResult.indexOf('#') == -1) {
      number[0] = dartResult.substring(0, 2);
      number[1] = dartResult.substring(2, 4);
      number[2] = dartResult.substring(4, 6);
    }
    else {
      for (int i = 0; i < number.length; i++) {
        if(dartResult.indexOf('*') == 2 || dartResult.indexOf('#') == 2
        ) {
          number[i] = dartResult.substring(0, 3);
          if(i != 2) {
            dartResult = dartResult.substring(3);
          }
          continue;
        }
        else if(dartResult.indexOf('*') == 3 || dartResult.indexOf('#') == 3) {
          number[i] = dartResult.substring(0, 4);
          if(i != 2) {
            dartResult = dartResult.substring(4);
          }
          continue;
        }
        else if(dartResult.length() > 2) {
          if(dartResult.charAt(2) == 'S' || dartResult.charAt(2) == 'D' || dartResult.charAt(2) == 'T') {
            number[i] = dartResult.substring(0, 3);
            if(i != 2) {
              dartResult = dartResult.substring(3);
            }
            continue;
          }
        }
        number[i] = dartResult.substring(0, 2);
        if(i != 2) {
          dartResult = dartResult.substring(2);
        }
      }
    }

    for(int i = 0; i < number.length; i++){
      int num = 0;
      boolean ten = false;
      if(number[i].length() == 2) num = number[i].charAt(0) - '0';
      else if(number[i].length() == 3) {
        if(number[i].charAt(2) == '*' || number[i].charAt(2) == '#') {
          num = number[i].charAt(0)  - '0';
        }
        else {
          num = 10;
          ten = true;
        }
      }
      else if(number[i].length() == 4) {
        num = 10;
        ten = true;
      }

      if(ten) {
        if(number[i].charAt(2) == 'D') num *= num;
        else if(number[i].charAt(2) == 'T') num *= num * num;
      }
      else {
        if(number[i].charAt(1) == 'D') num *= num;
        else if(number[i].charAt(1) == 'T') num *= num * num;
      }

      if(!ten && number[i].length() == 3 || ten && number[i].length() == 4) {
        if(number[i].charAt(2) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(2) == '*') intNum[i -1] *= 2;
        }
      }
      intNum[i] = num;
    }

    answer = intNum[0] + intNum[1] + intNum[2];

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String dartResult = "1D2S#10S";
    System.out.println(solution.solution(dartResult));
  }
}

```
정확성: 87.5

합계: 87.5 / 100.0

```java
class Solution {
  public int solution(String dartResult) {
    int answer = 0;

    String[] number = new String[3];
    int[] intNum = new int[3];

    if(dartResult.indexOf('*') == -1 && dartResult.indexOf('#') == -1) {
      if(dartResult.length() == 6) {
        number[0] = dartResult.substring(0, 2);
        number[1] = dartResult.substring(2, 4);
        number[2] = dartResult.substring(4, 6);
      }
      else {
        for(int i = 0; i < number.length; i++) {
          if(dartResult.charAt(1) > '9') {
            number[i] = dartResult.substring(0, 2);
            dartResult = dartResult.substring(2);
          }
          else {
            number[i] = dartResult.substring(0, 3);
            dartResult = dartResult.substring(3);
          }
        }
      }
    }
    else {
      for (int i = 0; i < number.length; i++) {
        if(dartResult.indexOf('*') == 2 || dartResult.indexOf('#') == 2
        ) {
          number[i] = dartResult.substring(0, 3);
          if(i != 2) {
            dartResult = dartResult.substring(3);
          }
          continue;
        }
        else if(dartResult.indexOf('*') == 3 || dartResult.indexOf('#') == 3) {
          number[i] = dartResult.substring(0, 4);
          if(i != 2) {
            dartResult = dartResult.substring(4);
          }
          continue;
        }
        else if(dartResult.length() > 2) {
          if(dartResult.charAt(2) == 'S' || dartResult.charAt(2) == 'D' || dartResult.charAt(2) == 'T') {
            number[i] = dartResult.substring(0, 3);
            if(i != 2) {
              dartResult = dartResult.substring(3);
            }
            continue;
          }
        }
        number[i] = dartResult.substring(0, 2);
        if(i != 2) {
        dartResult = dartResult.substring(2);
        }
      }
    }

    for(int i = 0; i < number.length; i++){
      int num = 0;
      boolean ten = false;
      if(number[i].length() == 2) num = number[i].charAt(0) - '0';
      else if(number[i].length() == 3) {
        if(number[i].charAt(2) == '*' || number[i].charAt(2) == '#') {
          num = number[i].charAt(0)  - '0';
        }
        else {
          num = 10;
          ten = true;
        }
      }
      else if(number[i].length() == 4) {
        num = 10;
        ten = true;
      }

      if(ten) {
        if(number[i].charAt(2) == 'D') num *= num;
        else if(number[i].charAt(2) == 'T') num *= num * num;
      }
      else {
        if(number[i].charAt(1) == 'D') num *= num;
        else if(number[i].charAt(1) == 'T') num *= num * num;
      }

      if(!ten && number[i].length() == 3 ) {
        if(number[i].charAt(2) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(2) == '*') intNum[i -1] *= 2;
        }
      }
      else if(ten && number[i].length() == 4) {
        if(number[i].charAt(3) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(3) == '*') intNum[i -1] *= 2;
        }
      }
      intNum[i] = num;
    }

    answer = intNum[0] + intNum[1] + intNum[2];

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String dartResult = "0S0D10T";
    System.out.println(solution.solution(dartResult));
  }
}
```

문제를 풀기는 했지만 이렇게 모든 상황을 이러한 식으로 해결한 것은 처음이다.

중간에 포기할까 마음 먹긴 했지만, 일단 마무리는 지어보고 싶어서 끝까지 하였다.

시간이 늦어져서 다음날이라도 다른 방법으로 해 볼 생각이다.

