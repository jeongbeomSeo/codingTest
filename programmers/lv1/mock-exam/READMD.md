# 모의고사

## 문제 설명

수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

## 제한 조건

- 시험은 최대 10,000 문제로 구성되어있습니다.
- 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
- 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.

## 입출력 예

- 입력값: {1, 2, 3, 4, 5}
  - 기댓값: {1}

- 입력값: {1, 3, 2, 4, 2}
  - 기댓값: {1, 2, 3}

| answers	    | return    |
|-------------|-----------|
 | [1,2,3,4,5] | 	[1]      |
 | [1,3,2,4,2] | 	[ 1,2,3] |

## 입출력 예 설명

**입출력 예 #1**

- 수포자 1은 모든 문제를 맞혔습니다.
- 수포자 2는 모든 문제를 틀렸습니다.
- 수포자 3은 모든 문제를 틀렸습니다.

따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.

**입출력 예 #2**

- 모든 사람이 2문제씩을 맞췄습니다.

## 풀이 방식

문제를 접했을 때 두가지 방식이 떠올랐다. 1번, 2번, 3번 수포자가 공통적으로 반복되는 구간 즉, 최소 공배수만큼 반복문을 처리해주는 방식,

또 다른 방식은 1번, 2번, 3번 수포자의 Array를 따로 만들어서 answers와 compare해주는 방식

1번은 너무 많은 코드가 소모 될 것을 추측이 되었다.

생각보다 오랜 시간이 쓰였다. 30분 이상이 소모된 것 같다.

일단 위에서도 써놨다싶히 3가지의 규칙을 어떻게 answers와 비교하면서 count를 할까? 이 부분에서 생각을 많이 했다.

그러던 중 반복되는 구간만 따로 만들어 놓고 반복문으로 길이가 넘어가면 나머지 처리(%)를 해서 계속해서 비교하면 되는 것을 깨달았다.

그리고 배열에서 큰 값을 가진 Index를 추출해내는 과정에서 시간이 많이 쓰였다.

좋은 방식의 코드가 생각나지 않아서 결국 max값을 꺼내서 값을 비교해가며 List에 넣어주고 마지막에 Array로 바꿔주는 형태로 마무리했다.

## 나의 코드

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] solution(int[] answers) {
    int[] count = new int[3];
    int[] first = {1,2,3,4,5};
    int[] second = {2,1,2,3,2,4,2,5};
    int[] third = {3,3,1,1,2,2,4,4,5,5};

    for(int i = 0; i < answers.length; i++) {
      int firstArrayIndex = i % 5;
      int secondArrayIndex = i % 8;
      int thirdArrayIndex = i % 10;
      if(answers[i] == first[firstArrayIndex]) {
        count[0]++;
      }
      if(answers[i] == second[secondArrayIndex]) {
        count[1]++;
      }
      if(answers[i] == third[thirdArrayIndex]) {
        count[2]++;
      }
    }

    //배열에서 가장 큰 값을 가진 Index 꺼내기
    int max = count[0];
    int eleCount = 0;
    for(int i = 1; i < count.length; i ++) {
      if(max < count[i]) {
        max = count[i];
      }
    }
    List<Integer> answer = new ArrayList<>();
    for(int i = 0; i < count.length; i++) {
      if(max == count[i]) {
        answer.add(i + 1);
      }
    }
    int[] result = answer.stream().mapToInt(i -> i).toArray();
    return result;
  }
  public static void main(String[] args){
    Solution solution= new Solution();
    int[] answers = {1, 2, 3, 4, 5};
    System.out.println(solution.solution(answers));
  }
}
```

## 다른 사람 풀이

```java
import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answer) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = new int[3];
        for(int i=0; i<answer.length; i++) {
            if(answer[i] == a[i%a.length]) {score[0]++;}
            if(answer[i] == b[i%b.length]) {score[1]++;}
            if(answer[i] == c[i%c.length]) {score[2]++;}
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) {list.add(1);}
        if(maxScore == score[1]) {list.add(2);}
        if(maxScore == score[2]) {list.add(3);}
        return list.stream().mapToInt(i->i.intValue()).toArray();
    }
}
```

```java
import java.util.*;

class Solution {
    public static int[] solution(int[] answers) {
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] hit = new int[3];
        for(int i = 0; i < hit.length; i++) {
            for(int j = 0; j < answers.length; j++) {
                if(patterns[i][j % patterns[i].length] == answers[j]) hit[i]++;
            }
        }

        int max = Math.max(hit[0], Math.max(hit[1], hit[2]));
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < hit.length; i++)
            if(max == hit[i]) list.add(i + 1);

        int[] answer = new int[list.size()];
        int cnt = 0;
        for(int num : list)
            answer[cnt++] = num;
        return answer;
    }
}
```

코드 풀이 자체는 비슷하다고 보지만, 코드의 깔끔한 정도가 다르다. 

문제점을 짚고 넘어가자.

1. max값을 구할 때 **Math.max()함수**를 사용하자.
2. 불필요한 변수 생성은 자제하자.
3. Element가 적을 경우에는 stream()을 사용하는 것보다 직접 넣어주는 것이 시간이 단축되는 경우가 많다.