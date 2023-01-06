# 신고 결과 받기

## 문제 설명

신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.

- 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
  - 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
  - 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
- k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
  - 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.

다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다.

| 유저 ID     |유저가 신고한 ID|설명|
|-----------|---|---|
| "muzi"    |"frodo"|"muzi"가 "fredo"를 신고했습니다.|
| "apeach"  |"frodo"|"apeach"가 "frodo"를 신고했습니다.|
| "frodo"   |"neo"|"frodo"가 "neo"를 신고했습니다.|
| "muzi"    |"neo"|"muzi"가 "neo"를 신고했습니다.|
|  "apeach" |"muzi"|"apeach"가 "muzi"를 신고했습니다.|

각 유저별로 신고당한 횟수는 다음과 같습니다.

| 유저 ID   |신고당한 횟수|
|---------|---|
| "muzi"  |1|
| "frodo" |2|
| "frodo" |0|
|  "neo"  |2|

위 예시에서는 2번 이상 신고당한 "frodo"와 "neo"의 게시판 이용이 정지됩니다. 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.

| 유저 ID    | 유저가 신고한 ID        |정지된 ID|
|----------|-------------------|---|
| "muzi"   | ["frodo", "neo"]  |["frodo", "neo"]|
| "frodo"  | ["neo"]           |["neo"]|
| "apeach" | ["muzi", "frodo"] |["frodo"]|
|  "neo"   | 없음                |없음|

따라서 "muzi"는 처리 결과 메일을 2회, "frodo"와 "apeach"는 각각 처리 결과 메일을 1회 받게 됩니다.

이용자의 ID가 담긴 문자열 배열 **id_list**, 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 **report** , 정지 기준이 되는 신고 횟수 **k**가 매개변수로 주어질 때, 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- 2 ≤ **id_list**의 길이 ≤ 1,000
  - 1 ≤ **id_list**의 원소 길이 ≤ 10
  - **id_list**의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
  - **id_list**에는 같은 아이디가 중복해서 들어있지 않습니다.
- 1 ≤ **report**의 길이 ≤ 200,000
  - 3 ≤ **report**의 원소 길이 ≤ 21
  - **report**의 원소는 "이용자id 신고한id"형태의 문자열입니다.
  - 예를 들어 "muzi frodo"의 경우 "muzi"가 "frodo"를 신고했다는 의미입니다.
  - id는 알파벳 소문자로만 이루어져 있습니다.
  - 이용자id와 신고한id는 공백(스페이스)하나로 구분되어 있습니다.
  - 자기 자신을 신고하는 경우는 없습니다.
- 1 ≤ **k** ≤ 200, **k**는 자연수입니다.
- return 하는 배열은 **id_list**에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.

## 입출력 예

| id_list                            | report                                                               |k|result|
|------------------------------------|----------------------------------------------------------------------|---|---|
| ["muzi", "frodo", "apeach", "neo"] | ["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]   |2|[2,1,1,0]|
|  ["con", "ryan"]                   | ["ryan con", "ryan con", "ryan con", "ryan con"]                     |3|[0,0]|

## 입출력 예 설명

#### 입출력 예 #1

문제의 예시와 같습니다.

#### 입출력 예 #2

"ryan"이 "con"을 4번 신고했으나, 주어진 조건에 따라 한 유저가 같은 유저를 여러 번 신고한 경우는 신고 횟수 1회로 처리합니다. 따라서 "con"은 1회 신고당했습니다. 3번 이상 신고당한 이용자는 없으며, "con"과 "ryan"은 결과 메일을 받지 않습니다. 따라서 [0, 0]을 return 합니다.

## 제한시간 안내

- 정확성 테스트 : 10초

## 알아 둬야 할 개념

- 배열 에서 값을 비교할 때는 ==이 아닌 equals() 를 사용해줘야 한다.
  - ==의 경우 주소 값을 비교하는 것이다.
- 배열에서 indexOf()같은 함수를 사용하기 위해선 ArrayList로 바꿔주는 Arrays.asList()를 이용하자.
- 배열내에서 중복을 제거하는 방법 중 하나는 HashSet을 이용하는 방법이다.

## 내가한 풀이

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
class Solution {
  public int[] solution(String[] id_list, String[] report, int k) {
    int[] answer = new int[id_list.length];
    int count[] = new int[id_list.length];

    //report 중복 제거
    HashSet<String> hashSet = new HashSet<>(Arrays.asList(report));
    String[] reportResult = hashSet.toArray(new String[0]);

    // 신고 당한 사람 count 하기
    for(int i = 0; i < reportResult.length; i++) {
      String[] reportPart = reportResult[i].split(" ");
      int index;
      index = Arrays.asList(id_list).indexOf(reportPart[1]);
      if(index != -1) {
        count[index]++;
      }
    }

    for(int i = 0; i < count.length; i++) {
      // k값 이상 count된 사람
      if(count[i] >= k) {
        // report에서 신고 당한 사람 찾아서 신고한 사람 answer에 +1 해주기.
        for(int j = 0; j < reportResult.length; j++){
          String[] reportPart = reportResult[j].split(" ");
          if(reportPart[1].equals(id_list[i])) {
            int index = Arrays.asList(id_list).indexOf(reportPart[0]);
            answer[index]++;
          }
        }
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution sol = new Solution();
    String[] id_list = {"muzi", "frodo", "apeach", "neo"};
    String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k=2;
    System.out.println(Arrays.toString(sol.solution(id_list, report, k)));
  }
}

// 테스트 결과: 2개 시간 초과
```

```java
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.ArrayList;
class Solution {
  public int[] solution(String[] id_list, String[] report, int k) {
    int[] answer = new int[id_list.length];
    int count[] = new int[id_list.length];

    //report 중복 제거
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(report));
    String[] reportResult = linkedHashSet.toArray(new String[0]);

    // 신고 당한 사람 count 하기
    for(int i = 0; i < reportResult.length; i++) {
      String[] reportPart = reportResult[i].split(" ");
      int index;
      index = Arrays.asList(id_list).indexOf(reportPart[1]);
      if(index != -1) {
        count[index]++;
      }
    }

    for(int i = 0; i < count.length; i++) {
    // k값 이상 count된 사람
      if(count[i] >= k) {
        // report에서 신고 당한 사람 찾아서 신고한 사람 answer에 +1 해주기.
        for(int j = 0; j < reportResult.length; j++){
        String[] reportPart = reportResult[j].split(" ");
        if(reportPart[1].equals(id_list[i])) {
            int index = Arrays.asList(id_list).indexOf(reportPart[0]);
            answer[index]++;
          }
        }
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution sol = new Solution();
    String[] id_list = {"muzi", "frodo", "apeach", "neo"};
    String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k=2;
    System.out.println(Arrays.toString(sol.solution(id_list, report, k)));
  }
}

// hashset을 LinkedHashSet으로 교체

// 테스트 결과: 1개 시간 초과
```

## 풀이 과정에서 참고한 사이트

1. 문자열 비교
   - https://codechacha.com/ko/java-string-compare/
2. 배열 생성
   - https://yeolco.tistory.com/72
   - https://coding-factory.tistory.com/253
3. 배열 출력
   - https://korbillgates.tistory.com/120
4. ArrayList.indexOf 활용법
   - https://codechacha.com/ko/java-collections-arraylist-indexof/

