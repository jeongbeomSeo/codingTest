# 신규 아이디 추천

## 문제 설명

카카오에 입사한 신입 개발자 **네오**는 "카카오계정개발팀"에 배치되어, 카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다. "네오"에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
다음은 카카오 아이디의 규칙입니다.

- 아이디의 **길이**는 3자 이상 15자 이하여야 합니다.
- 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
- 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

"네오"는 다음과 같이 7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.
신규 유저가 입력한 아이디가 **new_id** 라고 한다면,

```
1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
```

예를 들어, new_id 값이 "...!@BaT#*..y.abcdefghijklm" 라면, 위 7단계를 거치고 나면 new_id는 아래와 같이 변경됩니다.

1단계 대문자 'B'와 'T'가 소문자 'b'와 't'로 바뀌었습니다.
```
"...!@BaT#*..y.abcdefghijklm" → "...!@bat#*..y.abcdefghijklm"
```
2단계 '!', '@', '#', '*' 문자가 제거되었습니다.
```
"...!@bat#*..y.abcdefghijklm" → "...bat..y.abcdefghijklm"
```

3단계 '...'와 '..' 가 '.'로 바뀌었습니다.
```
"...bat..y.abcdefghijklm" → ".bat.y.abcdefghijklm"
```

4단계 아이디의 처음에 위치한 '.'가 제거되었습니다.
```
".bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
```

5단계 아이디가 빈 문자열이 아니므로 변화가 없습니다.
```
"bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
```

6단계 아이디의 길이가 16자 이상이므로, 처음 15자를 제외한 나머지 문자들이 제거되었습니다.
```
"bat.y.abcdefghijklm" → "bat.y.abcdefghi"
```

7단계 아이디의 길이가 2자 이하가 아니므로 변화가 없습니다.
```
"bat.y.abcdefghi" → "bat.y.abcdefghi"
```

따라서 신규 유저가 입력한 new_id가 "...!@BaT#*..y.abcdefghijklm"일 때, 네오의 프로그램이 추천하는 새로운 아이디는 "bat.y.abcdefghi" 입니다.

## 문제

신규 유저가 입력한 아이디를 나타내는 new_id가 매개변수로 주어질 때, "네오"가 설계한 7단계의 처리 과정을 거친 후의 추천 아이디를 return 하도록 solution 함수를 완성해 주세요.

## 제한사항

new_id는 길이 1 이상 1,000 이하인 문자열입니다.
new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.
new_id에 나타날 수 있는 특수문자는 ```-_.~!@#$%^&*()=+[{]}:?,<>/``` 로 한정됩니다.

## 입출력 예

| no  |new_id|result|
|-----|---------|------|
| 예1  |"...!@BaT#*..y.abcdefghijklm"|"bat.y.abcdefghi"|
| 예2  |"z-+.^."|"z--"|
| 예3  |"=.="|"aaa"|
| 예4  |"123_.def"|"123_.def"|
| 예5  |"abcdefghijklmn.p"|"abcdefghijklmn"|

## 입출력 예에 대한 설명

1. 입력값: "...!@BaT#*..y.abcdefghijklm"
   - 기댓값: "bat.y.abcdefghi"
2. 입력값: 	"z-+.^."
   - 기댓값: 	"z--"
7단계를 거치는 동안 new_id가 변화하는 과정은 아래와 같습니다.

1단계 변화 없습니다.
2단계 ```"z-+.^."``` → ```"z-.."```
3단계 ```"z-.."``` → ```"z-."```
4단계 ```"z-."``` → ```"z-"```
5단계 변화 없습니다.
6단계 변화 없습니다.
7단계 ```"z-"``` → ```"z--"```

3. 입력값: 	"=.="
   - 기댓값: "aaa"
7단계를 거치는 동안 new_id가 변화하는 과정은 아래와 같습니다.

1단계 변화 없습니다.
2단계 ```"=.="``` → ```"."```
3단계 변화 없습니다.
4단계 ```"." → ""``` (new_id가 빈 문자열이 되었습니다.)
5단계 ```""``` → ```"a"```
6단계 변화 없습니다.
7단계 ```"a"``` → ```"aaa"```
4. 입력값: 	"123_.def"
   - 기댓값: "123_.def"
1단계에서 7단계까지 거치는 동안 new_id("123_.def")는 변하지 않습니다.
즉, new_id가 처음부터 카카오의 아이디 규칙에 맞습니다.
5. 입력값: "abcdefghijklmn.p"
   - 기댓값: "abcdefghijklmn"
1단계 변화 없습니다.
2단계 변화 없습니다.
3단계 변화 없습니다.
4단계 변화 없습니다.
5단계 변화 없습니다.
6단계 ```"abcdefghijklmn.p"``` → ```"abcdefghijklmn."``` → ```"abcdefghijklmn"```
7단계 변화 없습니다.

## 참고한 사이트

- java string char Array로 쪼개주기.
  - https://coding-factory.tistory.com/73
- java replace All
  - https://velog.io/@minji/Java-%EC%A0%95%EA%B7%9C%ED%91%9C%ED%98%84%EC%8B%9DString.replaceAll-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%B9%98%ED%99%98
- 정규 표현식
  - https://velog.io/@cha-suyeon/%EC%A0%95%EA%B7%9C-%ED%91%9C%ED%98%84%EC%8B%9D%EC%9D%B4%EB%9E%80-%EB%AC%B8%EC%9E%90-%ED%81%B4%EB%9E%98%EC%8A%A4-Dot.-%EB%B0%98%EB%B3%B5-%EB%B0%98%EB%B3%B5-%EB%B0%98%EB%B3%B5mn
- Java 정규 표현식
  - https://kggo.tistory.com/113
- 문자열 subString()
  - https://www.delftstack.com/ko/howto/java/how-to-remove-last-character-from-the-string-in-java/
- Java 빈 문자열 체크
  - https://hianna.tistory.com/530
- 정규 표현식 테스트 사이트
  - https://regexr.com/6qrqd

## 필요 개념

- String에 char문자를 더해줄 수 있다.
- 정규 표현식
- String 다루는 방법

## 나의 코드
```java
class Solution {
  public String solution(String new_id) {
    String answer = "";

    new_id = firstStep(new_id);
    new_id = secondStep(new_id);
    new_id = thirdStep(new_id);
    new_id = fourthStep(new_id);
    new_id = fifthStep(new_id);
    new_id = sixthStep(new_id);
    answer = seventhStep(new_id);

    return answer;
  }

  String firstStep(String new_id) {
    String new_id_lowerCase = new_id.toLowerCase();
    return new_id_lowerCase;
  }

  String secondStep(String new_id) {
    String convert_new_id = new_id.replaceAll("([^a-z1-9-_.])", "");
    return convert_new_id;
  }

  String thirdStep(String new_id) {
    String convert_new_id = new_id.replaceAll("(\\.{2})",".");
    return convert_new_id;
  }

  String fourthStep(String new_id) {
    String convert_new_id = new_id;
    while(convert_new_id.startsWith(".")) {
      convert_new_id = convert_new_id.replaceFirst("." , "");
    }
    while(convert_new_id.endsWith(".")){
      convert_new_id = convert_new_id.substring(0, convert_new_id.length() - 1);
    }
    return convert_new_id;
  }

  String fifthStep(String new_id) {
    String convert_new_id = new_id.isEmpty() ? "a" : new_id;
    return convert_new_id;
  }

  String sixthStep(String new_id) {
    String convert_new_id = new_id.length() > 15 ? (new_id.substring(0 , 15).endsWith(".") ? new_id.substring(0,14) : new_id.substring(0 , 15)) : new_id;
    return convert_new_id;
  }

  String seventhStep(String new_id) {
    String convert_new_id = new_id;
    while(convert_new_id.length() < 3) {
      convert_new_id = convert_new_id.concat(convert_new_id.substring(convert_new_id.length() - 1));
    }
    return convert_new_id;
  }


  public static void main(String[] args) {
    String input = "abcdefghijklmn.p";
    Solution solution = new Solution();
    String output = solution.solution(input);

    System.out.println("output = " + output);
  }
}
// 정확성: 61.5

class Solution {
  public String solution(String new_id) {
    String answer = "";

    new_id = firstStep(new_id);
    new_id = secondStep(new_id);
    new_id = thirdStep(new_id);
    new_id = fourthStep(new_id);
    new_id = fifthStep(new_id);
    new_id = sixthStep(new_id);
    answer = seventhStep(new_id);

    return answer;
  }

  String firstStep(String new_id) {
    // 소문자로 전부 변환
    String new_id_lowerCase = new_id.toLowerCase();
    return new_id_lowerCase;
  }

  String secondStep(String new_id) {
    // 주어진 문자 외에 문자 제거
    String convert_new_id = new_id.replaceAll("([^a-z1-9-_.])", "");
    return convert_new_id;
  }

  String thirdStep(String new_id) {
    // 최소 N번 반복 하는 문자 제거
    String convert_new_id = new_id.replaceAll("(\\.{2,})",".");
    return convert_new_id;
  }

  String fourthStep(String new_id) {
    //.(dot)으로 시작하거나 끝나는 문자열 처리
    String convert_new_id = new_id;
    while(convert_new_id.startsWith(".")) {
      convert_new_id = convert_new_id.substring(1);
    }
    while(convert_new_id.endsWith(".")){
      convert_new_id = convert_new_id.substring(0, convert_new_id.length() - 1);
    }
    return convert_new_id;
  }

  String fifthStep(String new_id) {
    //빈 문자열 "a"넣어주기
    String convert_new_id = new_id.isEmpty() ? "a" : new_id;
    return convert_new_id;
  }

  String sixthStep(String new_id) {
    //최고 문자열 길이에 맞게 문자열 조정 및 끝 문자 .(dot)일 경우 처리
    String convert_new_id = new_id.length() > 15 ? (new_id.substring(0 , 15).endsWith(".") ? new_id.substring(0,14) : new_id.substring(0 , 15)) : new_id;
    return convert_new_id;
  }

  String seventhStep(String new_id) {
    // 최소 문자열 길이 에 맞게 문자열 조정
    String convert_new_id = new_id;
    while(convert_new_id.length() < 3) {
      convert_new_id = convert_new_id.concat(convert_new_id.substring(convert_new_id.length() - 1));
    }
    return convert_new_id;
  }


  public static void main(String[] args) {
    String input = "%%-a...sd....f...vs...adASD...FASDF........../././././";
    Solution solution = new Solution();
    String output = solution.solution(input);

    System.out.println("output = " + output);
  }
}
// 정확성: 88.5

// 모든 숫자를 처리 할땐 0-9로 처리해야 된다.
class Solution {
  public String solution(String new_id) {
    String answer = "";

    new_id = firstStep(new_id);
    new_id = secondStep(new_id);
    new_id = thirdStep(new_id);
    new_id = fourthStep(new_id);
    new_id = fifthStep(new_id);
    new_id = sixthStep(new_id);
    answer = seventhStep(new_id);

    return answer;
  }

  String firstStep(String new_id) {
    // 소문자로 전부 변환
    String new_id_lowerCase = new_id.toLowerCase();
    return new_id_lowerCase;
  }

  String secondStep(String new_id) {
    // 주어진 문자 외에 문자 제거
    String convert_new_id = new_id.replaceAll("([^a-z0-9-_.])", "");
    return convert_new_id;
  }

  String thirdStep(String new_id) {
    // 최소 N번 반복 하는 문자 제거
    String convert_new_id = new_id.replaceAll("(\\.{2,})",".");
    return convert_new_id;
  }

  String fourthStep(String new_id) {
    //.(dot)으로 시작하거나 끝나는 문자열 처리
    String convert_new_id = new_id;
    while(convert_new_id.startsWith(".")) {
      convert_new_id = convert_new_id.substring(1);
    }
    while(convert_new_id.endsWith(".")){
      convert_new_id = convert_new_id.substring(0, convert_new_id.length() - 1);
    }
    return convert_new_id;
  }

  String fifthStep(String new_id) {
    //빈 문자열 "a"넣어주기
    String convert_new_id = new_id.isEmpty() ? "a" : new_id;
    return convert_new_id;
  }

  String sixthStep(String new_id) {
    //최고 문자열 길이에 맞게 문자열 조정 및 끝 문자 .(dot)일 경우 처리
    String convert_new_id = new_id.length() > 15 ? (new_id.substring(0 , 15).endsWith(".") ? new_id.substring(0,14) : new_id.substring(0 , 15)) : new_id;
    return convert_new_id;
  }

  String seventhStep(String new_id) {
    // 최소 문자열 길이 에 맞게 문자열 조정
    String convert_new_id = new_id;
    while(convert_new_id.length() < 3) {
      convert_new_id = convert_new_id.concat(convert_new_id.substring(convert_new_id.length() - 1));
    }
    return convert_new_id;
  }


  public static void main(String[] args) {
    String input = "-+";
    Solution solution = new Solution();
    String output = solution.solution(input);

    System.out.println("output = " + output);
  }
}
// 정확성: 100
```


## 코드 리뷰

주어진 조건에 맞춰서 코드를 짜야 되었고 이 문제의 경우 정규 표현식의 사용을 요하는 부분이 많았다.
크게 어려운건 없었지만 정규 표현식을 표현해 내는 것에서 시간 소모가 좀 있었다.
그리고 마지막 7번째 가공에서 마지막 문자를 반복해서 length가 3이상이 되게 해야되는 것에서 고민을 많이했다.
charAt을 활용해서 마지막 문자를 가져오는 것까진 했는데 어떻게 붙어야 되는지를 고민했는데 아래 코드를 보고 본인이 어렵게 생각한 것이란 것을 알았다.
**String에 char문자를 마지막에 + 할 수 있다는 것을 기억하자.**

## 본받아야 될 코드 
```java
class Solution {
    public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]","");
        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}",".");
        temp = temp.replaceAll("^[.]|[.]$","");
        System.out.println(temp.length());
        if(temp.equals(""))
            temp+="a";
        if(temp.length() >=16){
            temp = temp.substring(0,15);
            temp=temp.replaceAll("^[.]|[.]$","");
        }
        if(temp.length()<=2)
            while(temp.length()<3)
                temp+=temp.charAt(temp.length()-1);

        answer=temp;
        return answer;
    }
}
```
