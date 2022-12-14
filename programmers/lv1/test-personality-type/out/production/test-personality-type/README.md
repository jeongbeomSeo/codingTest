# 성격 유형 검사하기

## 문제 설명

나만의 카카오 성격 유형 검사지를 만들려고 합니다.
성격 유형 검사는 다음과 같은 4개 지표로 성격 유형을 구분합니다. 성격은 각 지표에서 두 유형 중 하나로 결정됩니다.

| 지표 번호                | 성격 유형           |
|----------------------|-----------------|
| 1번 지표                | 라이언형(R), 튜브형(T) |
| 2번 지표| 	콘형(C), 프로도형(F) |
| 3번 지표                | 제이지형(J), 무지형(M) |
| 4번 지표                | 어피치형(A), 네오형(N) |

4개의 지표가 있으므로 성격 유형은 총 16(=2 x 2 x 2 x 2)가지가 나올 수 있습니다. 예를 들어, "RFMN"이나 "TCMA"와 같은 성격 유형이 있습니다.

검사지에는 총 **n**개의 질문이 있고, 각 질문에는 아래와 같은 7개의 선택지가 있습니다.

- 매우 비동의
- 비동의
- 약간 비동의
- 모르겠음
- 약간 동의
- 동의
- 매우 동의

각 질문은 1가지 지표로 성격 유형 점수를 판단합니다.

예를 들어, 어떤 한 질문에서 4번 지표로 아래 표처럼 점수를 매길 수 있습니다.

| 선택지     | 성격 유형 점수               |
|---------|------------------------|
| 매우 비동의  | 네오형 3점                 |
 | 비동의	네오형 |  2점                    ||
 | 약간 비동의  | 	네오형 1점                |
 | 모르겠음    | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | 약간 동의   | 	어피치형 1점               |
 | 동의	어피치형 | 2점                     |
 | 매우 동의   | 	어피치형 3점               |

이때 검사자가 질문에서 **약간 동의** 선택지를 선택할 경우 어피치형(A) 성격 유형 1점을 받게 됩니다. 만약 검사자가 **매우 비동의** 선택지를 선택할 경우 네오형(N) 성격 유형 3점을 받게 됩니다.

**위 예시처럼 네오형이 비동의, 어피치형이 동의인 경우만 주어지지 않고, 질문에 따라 네오형이 동의, 어피치형이 비동의인 경우도 주어질 수 있습니다.**

하지만 각 선택지는 고정적인 크기의 점수를 가지고 있습니다.

- ```매우 동의```나 ```매우 비동의``` 선택지를 선택하면 3점을 얻습니다.
- ```동의```나 ```비동의``` 선택지를 선택하면 2점을 얻습니다.
- ```약간 동의```나 약간 ```비동의````` 선택지를 선택하면 1점을 얻습니다.
- ```모르겠음``` 선택지를 선택하면 점수를 얻지 않습니다.

검사 결과는 모든 질문의 성격 유형 점수를 더하여 각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형이라고 판단합니다. 단, 하나의 지표에서 각 성격 유형 점수가 같으면, 두 성격 유형 중 사전 순으로 빠른 성격 유형을 검사자의 성격 유형이라고 판단합니다.

질문마다 판단하는 지표를 담은 1차원 문자열 배열 ```survey```와 검사자가 각 질문마다 선택한 선택지를 담은 1차원 정수 배열 ```choices```가 매개변수로 주어집니다. 이때, 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- 1 ≤ ```survey```의 길이 ( = ```n```) ≤ 1,000
  - ```survey```의 원소는 ```"RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA"``` 중 하나입니다.
  - ```survey[i]```의 첫 번째 캐릭터는 i+1번 질문의 비동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
  - ```survey[i]```의 두 번째 캐릭터는 i+1번 질문의 동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다.
- ```choices```의 길이 = ```survey```의 길이
  - ```choices[i]```는 검사자가 선택한 i+1번째 질문의 선택지를 의미합니다.
  - 1 ≤ ```choices```의 원소 ≤ 7

|choices| 	뜻      |
|---|---------|
|1	| 매우 비동의  |
|2	| 비동의     |
|3| 	약간 비동의 |
|4	| 모르겠음    |
|5	| 약간 동의   |
|6	| 동의      |
|7	| 매우 동의   |

## 입출력 예

- 입력값: 	["AN", "CF", "MJ", "RT", "NA"], [5, 3, 2, 7, 5]
  - 기댓값: "TCMA"
- 입력값: 	["TR", "RT", "TR"], [7, 1, 3]
  - 기댓값: 	"RCJA"

| survey                         | 	choices	               | result  |
|--------------------------------|-------------------------|---------|
 | ["AN", "CF", "MJ", "RT", "NA"] | 	[5, 3, 2, 7, 5]| 	"TCMA" |
 | ["TR", "RT", "TR"]             | 	[7, 1, 3]	| "RCJA"  |

## 입출력 예 설명

#### 입출력 예 #1

1번 질문의 점수 배치는 아래 표와 같습니다.

| 선택지       | 	성격 유형 점수              |
|-----------|------------------------|
 | 매우 비동의    | 	어피치형 3점               |
 | 비동의	      | 어피치형 2점                |
 | 약간 비동의    | 	어피치형 1점               |
 | 모르겠음      | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | **약간 동의** | 	**네오형 1점**            |
 | 동의        | 	네오형 2점                |
 | 매우 동의     | 	네오형 3점                |

1번 질문에서는 지문의 예시와 다르게 비동의 관련 선택지를 선택하면 어피치형(A) 성격 유형의 점수를 얻고, 동의 관련 선택지를 선택하면 네오형(N) 성격 유형의 점수를 얻습니다.
1번 질문에서 검사자는 ```약간 동의``` 선택지를 선택했으므로 네오형(N) 성격 유형 점수 1점을 얻게 됩니다.

2번 질문의 점수 배치는 아래 표와 같습니다.

| 선택지	       | 성격 유형 점수               |
|------------|------------------------|
 | 매우 비동의     | 	콘형 3점                 |
 | 비동의        | 	콘형 2점                 |
 | **약간 비동의** | 	**콘형 1점**             |
 | 모르겠음       | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | 약간 동의      | 	프로도형 1점               |
 | 동의	        | 프로도형 2점                |
 | 매우 동의      | 	프로도형 3점               |

2번 질문에서 검사자는 ```약간 비동의``` 선택지를 선택했으므로 콘형(C) 성격 유형 점수 1점을 얻게 됩니다.

3번 질문의 점수 배치는 아래 표와 같습니다.

| 선택지     | 	성격 유형 점수              |
|---------|------------------------|
 | 매우 비동의  | 	무지형 3점                |
 | **비동의** | 	**무지형 2점**            |
 | 약간 비동의  | 	무지형 1점                |
 | 모르겠음    | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | 약간 동의   | 	제이지형 1점               |
 | 동의      | 	제이지형 2점               |
 | 매우 동의   | 	제이지형 3점               |

3번 질문에서 검사자는 ```비동의``` 선택지를 선택했으므로 무지형(M) 성격 유형 점수 2점을 얻게 됩니다.

4번 질문의 점수 배치는 아래 표와 같습니다.

| 선택지       | 	성격 유형 점수              |
|-----------|------------------------|
 | 매우 비동의    | 	라이언형 3점               |
 | 비동의	      | 라이언형 2점                |
 | 약간 비동의    | 	라이언형 1점               |
 | 모르겠음      | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | 약간 동의     | 	튜브형 1점                |
 | 동의	튜브형    | 2점                     |
 | **매우 동의** | 	**튜브형 3점**            |

4번 질문에서 검사자는 ```매우 동의``` 선택지를 선택했으므로 튜브형(T) 성격 유형 점수 3점을 얻게 됩니다.

5번 질문의 점수 배치는 아래 표와 같습니다.

| 선택지	      | 성격 유형 점수               |
|-----------|------------------------|
 | 매우 비동의    | 	네오형 3점                |
 | 비동의	      | 네오형 2점                 |
 | 약간 비동의    | 	네오형 1점                |
 | 모르겠음      | 	어떤 성격 유형도 점수를 얻지 않습니다 |
 | **약간 동의** | 	**어피치형 1점**           |
 | 동의        | 	어피치형 2점               |
 | 매우 동의     | 	어피치형 3점               |

5번 질문에서 검사자는 ```약간 동의``` 선택지를 선택했으므로 어피치형(A) 성격 유형 점수 1점을 얻게 됩니다.

1번부터 5번까지 질문의 성격 유형 점수를 합치면 아래 표와 같습니다.

|지표 번호| 	성격 유형|	점수	성격| 유형	점수  |
|---|---------------------|----|---|
|1번 지표| 	라이언형(R)|	0|	튜브형(T)	3 |
|2번 지표| 	콘형(C)|	1	|프로도형(F)	0  |
|3번 지표| 	제이지형(J)|	0	|무지형(M)	2 |
|4번 지표| 	어피치형(A)|	1|	네오형(N)	1 |

각 지표에서 더 점수가 높은 ```T```,```C```,```M```이 성격 유형입니다.
하지만, 4번 지표는 1점으로 동일한 점수입니다. 따라서, 4번 지표의 성격 유형은 사전순으로 빠른 ```A```입니다.

따라서 ```"TCMA"```를 return 해야 합니다.

#### 입출력 예 #2

1번부터 3번까지 질문의 성격 유형 점수를 합치면 아래 표와 같습니다.

| 지표 번호 |	성격 유형|	점수	성격| 유형	점수|
|-------|---|---|---|
 | 1번 지표 |	라이언형(R)|	6|	튜브형(T)	1|
 | 2번 지표 |	콘형(C)|	0|	프로도형(F)	0|
 | 3번 지표 |	제이지형(J)|	0|	무지형(M)	0|
 | 4번 지표 |	어피치형(A)|	0|	네오형(N)	0|

1번 지표는 튜브형(T)보다 라이언형(R)의 점수가 더 높습니다. 따라서 첫 번째 지표의 성격 유형은 ```R```입니다.
하지만, 2, 3, 4번 지표는 모두 0점으로 동일한 점수입니다. 따라서 2, 3, 4번 지표의 성격 유형은 사전순으로 빠른 ```C```, ```J```, ```A```입니다.

따라서 ```"RCJA"```를 return 해야 합니다.

## 필요 개념

## 참고한 사이트

- 다차원 배열
  - https://simplex3510.tistory.com/214


## 나의 코드 

```java
class Solution {
  String[][] personality = {
          {"RT", "TR"},
          {"CF", "FC"},
          {"JM", "MJ"},
          {"AN", "NA"}
  };

  // 4가지 지표로 나눠서 점수 매기기.
  // R이 -, T가 +
  // C가 -, F가 +
  // J가 -, M이 +
  // A가 -, N이 +
  int[] result =  new int[4];

  int score(String survey,String[] question, int score) {
    if(score == 4) return 0;
    if(score > 4) score -= 4;
    if(score < 4) score = -score;
    if(survey.equals(question[0])) {
      return -score;
    }
    else if(survey.equals(question[1])){
      return score;
    }
    return 0;
  };

  public String solution(String[] survey, int[] choices) {
    String answer = "";

    for(int i = 0; i < survey.length; i++) {
      if(survey[i].equals(personality[0][0]) || survey[i].equals(personality[0][1])) {
        result[0] += score(survey[i], personality[0], choices[i]);
      }
      else if(survey[i].equals(personality[1][0]) || survey[i].equals(personality[1][1])){
        result[1] += score(survey[i], personality[1], choices[i]);
      }
      else if(survey[i].equals(personality[2][0]) || survey[i].equals(personality[2][1])){
        result[2] += score(survey[i], personality[2], choices[i]);
      }
      else if(survey[i].equals(personality[3][0]) || survey[i].equals(personality[3][1])){
        result[3] += score(survey[i], personality[3], choices[i]);
      }
    }

    answer += result[0] >= 0 ? "T" : "R";
    answer += result[1] >= 0 ? "C" : "F";
    answer += result[2] >= 0 ? "J" : "M";
    answer += result[3] >= 0 ? "A" : "N";

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
    int[] choices = {5, 3, 2, 7, 5};
    System.out.println(solution.solution(survey, choices));
  }
}

// 정확성: 10.0

class Solution {
 int[] score = new int[8];
 // 0 -> R
 // 1 -> T
 // 2 -> C
 // 3 -> F
 // 4 -> J
 // 5 -> M
 // 6 -> A
 // 7 -> N

 public String solution(String[] survey, int[] choices) {
  String answer = "";

  for(int i = 0; i < survey.length; i++) {
   boolean right = choices[i] > 4 ? true : false;
   int number = choices[i];
   if(survey[i].equals("RT") || survey[i].equals("TR")) {
    if(right) {
     number -= 4;
     if(survey[i].charAt(1) == 'R') score[0] += number;
     else score[1] += number;
    }
    else{
     if(survey[i].charAt(0) == 'R') score[0] += number;
     else score[1] += number;
    }
   }
   if(survey[i].equals("CF") || survey[i].equals("FC")) {
    if(right) {
     number -= 4;
     if(survey[i].charAt(1) == 'C') score[2] += number;
     else score[3] += number;
    }
    else {
     if(survey[i].charAt(0) == 'C') score[2] += number;
     else score[3] += number;
    }
   }
   if(survey[i].equals("JM") || survey[i].equals("MJ")) {
    if(right) {
     number -= 4;
     if(survey[i].charAt(1) == 'J') score[4] += number;
     else score[5] += number;
    }
    else {
     if(survey[i].charAt(0) == 'J') score[4] += number;
     else score[5] += number;
    }
   }
   if(survey[i].equals("AN") || survey[i].equals("NA")) {
    if(right) {
     number -= 4;
     if(survey[i].charAt(1) == 'A') score[6] += number;
     else score[7] += number;
    }
    else {
     if(survey[i].charAt(0) == 'A') score[6] += number;
     else score[7] += number;
    }
   }
  }
  answer += score[0] >= score[1] ? "R" : "T";
  answer += score[2] >= score[3] ? "C" : "F";
  answer += score[4] >= score[5] ? "J" : "M";
  answer += score[6] >= score[7] ? "A" : "N";


  return answer;
 }
 public static void main(String args[]) {
  Solution solution = new Solution();
  String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
  int[] choices = {5, 3, 2, 7, 5};

  System.out.println(solution.solution(survey, choices));
 }
}

// 정확성: 35.0

class Solution {
 public String solution(String[] survey, int[] choices) {
  String answer = "";
  int[][] personality = new int[4][2];
  // R T
  // C F
  // J M
  // A N
  for(int i = 0; i < survey.length; i++) {
   if(choices[i] == 4) continue;
   Boolean right = choices[i] > 4 ? true : false;
   switch (survey[i]) {
    case "RT":
    case "TR":
     if (right) {
      if (survey[i].charAt(1) == 'R') personality[0][0] += (choices[i] - 4);
      else personality[0][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'R') personality[0][0] += choices[i];
      else personality[0][1] += choices[i];
     }
     break;
    case "CF":
    case "FC":
     if (right) {
      if (survey[i].charAt(1) == 'C') personality[1][0] += (choices[i] - 4);
      else personality[1][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'C') personality[1][0] += choices[i];
      else personality[1][1] += choices[i];
     }
     break;
    case "JM":
    case "MJ":
     if (right) {
      if (survey[i].charAt(1) == 'J') personality[2][0] += (choices[i] - 4);
      else personality[2][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'J') personality[2][0] += choices[i];
      else personality[2][1] += choices[i];
     }
     break;
    case "AN":
    case "NA":
     if (right) {
      if (survey[i].charAt(1) == 'A') personality[3][0] += (choices[i] - 4);
      else personality[3][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'A') personality[3][0] += choices[i];
      else personality[3][1] += choices[i];
     }
     break;
   }
  }
  answer += personality[0][0] >= personality[0][1] ? "R" : "T";
  answer += personality[1][0] >= personality[1][1] ? "C" : "F";
  answer += personality[2][0] >= personality[2][1] ? "J" : "M";
  answer += personality[3][0] >= personality[3][1] ? "A" : "N";

  return answer;
 }
 public static void main(String args[]) {
  Solution solution = new Solution();
  String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
  int[] choices = {5, 3, 2, 7, 5};
  System.out.println(solution.solution(survey,choices));
 }
}

// 정확성 : 45.0

choices 숫자에서 오른쪽만 너무 신경쓰고 왼쪽은 신경 쓰지 않아도 된다는 생각에 왼쪽을 그냥 쓴 것이 문제였다.

디버깅을 함으로써 알아내었고, 왼쪽의 처음 시작이 1이 아니라 중간에서 시작한다는 것을 간과했다.

class Solution {
 public String solution(String[] survey, int[] choices) {
  String answer = "";
  int[][] personality = new int[4][2];
  // R T
  // C F
  // J M
  // A N
  for(int i = 0; i < survey.length; i++) {
   if(choices[i] == 4) continue;
   Boolean right = choices[i] > 4 ? true : false;
   switch (survey[i]) {
    case "RT":
    case "TR":
     if (right) {
      if (survey[i].charAt(1) == 'R') personality[0][0] += (choices[i] - 4);
      else personality[0][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'R') personality[0][0] += (4- choices[i]);
      else personality[0][1] += (4- choices[i]);
     }
     break;
    case "CF":
    case "FC":
     if (right) {
      if (survey[i].charAt(1) == 'C') personality[1][0] += (choices[i] - 4);
      else personality[1][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'C') personality[1][0] += (4- choices[i]);
      else personality[1][1] += (4- choices[i]);
     }
     break;
    case "JM":
    case "MJ":
     if (right) {
      if (survey[i].charAt(1) == 'J') personality[2][0] += (choices[i] - 4);
      else personality[2][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'J') personality[2][0] += (4- choices[i]);
      else personality[2][1] += (4- choices[i]);
     }
     break;
    case "AN":
    case "NA":
     if (right) {
      if (survey[i].charAt(1) == 'A') personality[3][0] += (choices[i] - 4);
      else personality[3][1] += (choices[i] - 4);
     } else {
      if (survey[i].charAt(0) == 'A') personality[3][0] += (4- choices[i]);
      else personality[3][1] += (4- choices[i]);
     }
     break;
   }
  }
  answer += personality[0][0] >= personality[0][1] ? "R" : "T";
  answer += personality[1][0] >= personality[1][1] ? "C" : "F";
  answer += personality[2][0] >= personality[2][1] ? "J" : "M";
  answer += personality[3][0] >= personality[3][1] ? "A" : "N";

  return answer;
 }
 public static void main(String args[]) {
  Solution solution = new Solution();
  String[] survey = {"CF", "CF", "CF", "CF", "CF"};
  int[] choices = {5, 3, 2, 7, 5};
  System.out.println(solution.solution(survey,choices));
 }
}

Clear
```

## 문제 코드 잡아내기

먼저 맨처음 코드는 문제점이 굉장히 많이 보인다.

1. 사전순으로 처리를 하기 위해선 R과 C, J, A가 +가 되도록 처리해줘야 한다.
2. 같은 변수로 조건문을 처리할 때는 else if를 써야함에 주의 해야 한다.
```java
  int score(String survey,String[] question, int score) {
        if(score == 4) return 0;
        if(score > 4) score -= 4;
        if(score < 4) score = -score;
        if(survey.equals(question[0])) {
        return -score;
        }
        else if(survey.equals(question[1])){
        return score;
        }
        return 0;
        };
```

해당 코드에서 그냥 계속해서 if문으로 처리함으로써 만약 score가 4보다 클 때 

score에서 4를 빼고 그 아래 if문도 만족해짐으로 그 아래 조건문도 실행한다.

다음과 같이 수정해줘야 한다.

```java
  int score(String survey,String[] question, int score) {
    if(score == 4) return 0;
    if(score > 4) score -= 4;
    if(score < 4) score = -(4 - score);
    if(survey.equals(question[0])) {
      return -score;
    }
    else if(survey.equals(question[1])){
      return score;
    }
    return 0;
  };
```