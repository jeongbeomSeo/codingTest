# 체육복

## 문제 설명

점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다. 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

## 제한사항

- 전체 학생의 수는 2명 이상 30명 이하입니다.
- 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
- 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
- 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
- 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

## 입출력 예

- 입력값: 5, {2, 4}, {1, 3, 5}
  - 기댓값: 5

- 입력값: 5, {2, 4}, {3}
  - 기댓값: 4

## 입출력 예 설명

**예제 #1**
1번 학생이 2번 학생에게 체육복을 빌려주고, 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.

**예제 #2**
3번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.

## 나의 코드
```java
class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }

    for(int index: lost) {
      if(student[index - 2] == 1)  {
        student[index -2] = 0;
        continue;
      }
      else if(student[index - 1] == 1)  {
        student[index - 1] =0;
        continue;
      }
      else if(student[index] == 1) {
        student[index] = 0;
        continue;
      }
      // 체육복이 없는 사람 -> -1
      student[index - 1] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
}
정확성: 25.0
합계: 25.0

-> 런타임 에러가 대부분
```

```java
class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }

    for(int index: lost) {
       if(student[index - 1] == 1)  {
        student[index - 1] = 0;
        continue;
      }
      else if(index != 1 && student[index - 2] == 1)  {
        student[index -2] = 0;
        continue;
      }
      else if(index != n && student[index] == 1) {
        student[index] = 0;
        continue;
      }
      // 체육복이 없는 사람 -> -1
      student[index - 1] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
}

정확성: 85.0
합계: 85.0
```

```java
class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }
    //본인 -> 앞사람 -> 뒷사람순으로 여분 체육복 확인
    //이때 다음 사람이 체육복을 잃어 버렸을 경우를 생각해야함.
    for(int i = 0; i < lost.length; i++) {
      int index = lost[i] - 1;
      if(student[index] == 1) {
        student[index] = 0;
        continue;
      }
      else if(index != 0 && student[index-1] == 1 ) {
        student[index -1] = 0;
        continue;
      }
      else if(index != n-1 && student[index + 1] == 1) {
        // 뒷사람이 잃어버리지도 않았고 여분의 체육복을 가지고 있는 상태
        if(lost[i] + 1 != lost[i + 1]) {
          student[index + 1] = 0;
          continue;
        }
      }
      //뒷사람이 여분의 체육복을 가져온 동시에 잃어 버린 경우 -> 빌릴 수 없음.
      student[index] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] lost = {2, 4};
    int[] reserve = {3};
    System.out.println(solution.solution(n, lost, reserve));
  }
}

정확성: 75

```

```java
class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }
    //본인 -> 앞사람 -> 뒷사람순으로 여분 체육복 확인
    //이때 다음 사람이 체육복을 잃어 버렸을 경우를 생각해야함.
    for(int i = 0; i < lost.length; i++) {
      int index = lost[i] - 1;
      if(student[index] == 1) {
        student[index] = 0;
        continue;
      }
      else if(index != 0 && student[index-1] == 1 ) {
        student[index -1] = 0;
        continue;
      }
      else if(index != n-1 && student[index + 1] == 1) {
        // 뒷사람이 잃어버리지도 않았고 여분의 체육복을 가지고 있는 상태
        if(i+1 == lost.length || lost[i] + 1 != lost[i + 1]) {
          student[index + 1] = 0;
          continue;
        }
      }
      //뒷사람이 여분의 체육복을 가져온 동시에 잃어 버린 경우 -> 빌릴 수 없음.
      student[index] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] lost = {2, 4};
    int[] reserve = {3};
    System.out.println(solution.solution(n, lost, reserve));
  }
}

정확성: 90
```

```java
import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    // 정렬부터 시켜주기
    Arrays.sort(lost);
    Arrays.sort(reserve);

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }
    //본인 -> 앞사람 -> 뒷사람순으로 여분 체육복 확인
    //이때 다음 사람이 체육복을 잃어 버렸을 경우를 생각해야함.
    for(int i = 0; i < lost.length; i++) {
      int index = lost[i] - 1;
      if(student[index] == 1) {
        student[index] = 0;
        continue;
      }
      else if(index != 0 && student[index-1] == 1 ) {
        student[index -1] = 0;
        continue;
      }
      else if(index != student.length - 1 && student[index + 1] == 1) {
        // 뒷사람이 잃어버리지도 않았고 여분의 체육복을 가지고 있는 상태
        if(i+1 == lost.length || lost[i] + 1 != lost[i + 1]) {
          student[index + 1] = 0;
          continue;
        }
      }
      //뒷사람이 여분의 체육복을 가져온 동시에 잃어 버린 경우 -> 빌릴 수 없음.
      student[index] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] lost = {2, 4};
    int[] reserve = {3};
    System.out.println(solution.solution(n, lost, reserve));
  }
}
```

## 풀이 과정

해당 문제를 풀때 처음에 반복문을 두번 쓰기 싫어서 lost와 reserve 각각 두번 반복 하기 보단 reserve만 반복해줘서 나머지는 조건문으로 처리하려고 했다.

즉, reserve만 처리해주고 lost를 받아서 다 처리하면 되겠다고 생각했다.

하지만 사실상 그 과정에 더 많은 추가적인 비교가 들어가기 시작하면서 문제 풀이 하는데 시간이 오래 걸렸다.

또한 해당 문제에서는 처음과 마지막인 사람은 앞 뒤 사람에게 체육복을 빌릴 수 없다는 점과 정렬이 안되어 있는 경우도 생각해봐야 한다. 이것을 생각하는 데도 시간이 걸렸다.
  
아래 다른 사람 풀이를 보면 lost 와 reserve의 크기 만큼 반복문을 처리해주고 나서 n만큼 반복해주면서 나머지 처리를 한다.

즉, 본인의 체육복을 잃어버린 case, 여유분을 가지고 있는 case, 잃어버렸지만 여유분을 가지고있는 case를 처리해주고 나서 나머지 옆 사람에게 주는 case만 처리해준 것이다.

## 다른 사람 풀이

```java
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost) 
            people[l-1]--;
        for (int r : reserve) 
            people[r-1]++;

        for (int i = 0; i < people.length; i++) {
            if(people[i] == -1) {
                if(i-1>=0 && people[i-1] == 1) {
                    people[i]++;
                    people[i-1]--;
                }else if(i+1< people.length && people[i+1] == 1) {
                    people[i]++;
                    people[i+1]--;
                }else 
                    answer--;
            }
        }
        return answer;
    }
}
```

