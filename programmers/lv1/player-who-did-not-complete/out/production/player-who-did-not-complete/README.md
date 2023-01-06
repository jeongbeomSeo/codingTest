# 완주하지 못한 선수

## 문제 설명

수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

## 제한사항

- 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
- completion의 길이는 participant의 길이보다 1 작습니다.
- 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
- 참가자 중에는 동명이인이 있을 수 있습니다.

## 입출력 예

|participant|	completion|	return|
|---|---|---|
|["leo", "kiki", "eden"]|	["eden", "kiki"]|	"leo"|
|["marina", "josipa", "nikola", "vinko", "filipa"]|	["josipa", "filipa", "marina", "nikola"]|	"vinko"|
|["mislav", "stanko", "mislav", "ana"]|	["stanko", "ana", "mislav"]|	"mislav"|

## 입출력 예 설명

- 입력값: 	{"leo", "kiki", "eden"], {"eden", "kiki"}
  - 출력값: 	"leo"

"leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

- 입력값: 	{"marina", "josipa", "nikola", "vinko", "filipa"}, {"josipa", "filipa", "marina", "nikola"}
  - 출력값: 	"vinko"
    
"vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

- 입력값: 	{"mislav", "stanko", "mislav", "ana"}, {"stanko", "ana", "mislav"}
  - 출력값: 	"mislav"

"mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

[출처](https://hsin.hr/coci/archive/2014_2015/contest2_tasks.pdf)

## 필요 개념
- List는 Interface이고 Interface를 인스턴스로 생성하여 사용한다면 당연히 오류가 나온다.

## 참고한 사이트
- Convert String Array to ArrayList
  - https://stackoverflow.com/questions/10530353/convert-string-array-to-arraylist
- List and ArraysList difference
  - https://yoon-dailylife.tistory.com/7
- Arrays.sort()
  - https://jamesdreaming.tistory.com/65
  

## 나의 코드

```java
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
  public String solution(String[] participant, String[] completion) {
    //List<String> participantList = Arrays.asList(participant);
    ArrayList<String> participantArrayList = new ArrayList<>(Arrays.asList(participant));

    for(String player : completion) {
      participantArrayList.remove(player);
    }

    return participantArrayList.get(0);
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"eden", "kiki"};

    System.out.println(solution.solution(participant,completion));
  }
}

만약 여기서 위에 주석 처리한 List를 사용해서 remove함수를 사용하면 Error가 나온다.

정확성: 50.0
효율성: 0.0
```

```java
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
  public String solution(String[] participant, String[] completion) {
    String answer = "";
    List<String> completionList = new ArrayList<>(Arrays.asList(completion));
    for(String player : participant) {
      if(completionList.contains(player)) {
        completionList.remove(player);
      }
      else {
        answer = player;
        break;
      }
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"eden", "kiki"};

    System.out.println(solution.solution(participant,completion));
  }
}

정확성: 50.0
효율성: 0.0
```

```java
import java.util.Arrays;

class Solution {
  public String solution(String[] participant, String[] completion) {
    String answer = "";
    Arrays.sort(participant);
    Arrays.sort(completion);
    for(int i = 0; i < participant.length; i++) {
      if(i == participant.length -1 || !participant[i].equals(completion[i])) {
        return participant[i];
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"eden", "kiki"};

    System.out.println(solution.solution(participant,completion));
  }
}

통과
```