# 스타트와 링크

**실버 2**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB|	73495	|36292|	21324|	46.206%|

## 문제

오늘은 스타트링크에 다니는 사람들이 모여서 축구를 해보려고 한다. 축구는 평일 오후에 하고 의무 참석도 아니다. 축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다. 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.

BOJ를 운영하는 회사 답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다. 능력치 S<sub>ij</sub>는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다. 팀의 능력치는 팀에 속한 모든 쌍의 능력치 S<sub>ij</sub>의 합이다. S<sub>ij</sub>는 S<sub>ji</sub>와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 S<sub>ij</sub>와 S<sub>ji</sub>이다.

N=4이고, S가 아래와 같은 경우를 살펴보자.


| i\j	 |1	|2	|3|	4|
|------|---|---|---|---|
| **1**	 | 	|1|	2	|3|
| **2**    |	4	| |	5	|6|
| **3**	   |7|	1|	 |	2|
| **4**	   |3	|4|	5|	 |

예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.

- 스타트 팀: S<sub>12</sub> + S<sub>21</sub> = 1 + 4 = 5
- 링크 팀: S<sub>34</sub> + S<sub>43</sub> = 2 + 5 = 7

1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.

- 스타트 팀: S<sub>13</sub> + S<sub>31</sub> = 2 + 7 = 9
- 링크 팀: S<sub>24</sub> + S<sub>42</sub> = 6 + 4 = 10

축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다. 위의 예제와 같은 경우에는 1, 4번이 스타트 팀, 2, 3번 팀이 링크 팀에 속하면 스타트 팀의 능력치는 6, 링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.

## 입력

첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다. 둘째 줄부터 N개의 줄에 S가 주어진다. 각 줄은 N개의 수로 이루어져 있고, i번 줄의 j번째 수는 S<sub>ij</sub> 이다. S<sub>ii</sub>는 항상 0이고, 나머지 S<sub>ij</sub>는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.

## 출력

첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.

## 예제 입력 1

```
4
0 1 2 3
4 0 5 6
7 1 0 2
3 4 5 0
```

## 에제 출력 1

```
0
```

## 예제 입력 2

```
6
0 1 2 3 4 5
1 0 2 3 4 5
1 2 0 3 4 5
1 2 3 0 4 5
1 2 3 4 0 5
1 2 3 4 5 0
```

## 예제 출력 2

```
2
```

## 예제 입력 3

```
8
0 5 4 5 4 5 4 5
4 0 5 1 2 3 4 5
9 8 0 1 2 3 1 2
9 9 9 0 9 9 9 9
1 1 1 1 0 1 1 1
8 7 6 5 4 0 3 2
9 1 9 1 9 1 0 9
6 5 4 3 2 1 9 0
```

## 예제 출력 3

```
1
```

## 힌트

예제 2의 경우에 (1, 3, 6), (2, 4, 5)로 팀을 나누면 되고, 예제 3의 경우에는 (1, 2, 4, 5), (3, 6, 7, 8)로 팀을 나누면 된다.

## 풀이 방식

먼저 문제를 풀기 위해서 크게 두 가지 과정을 나눴다.

1. Team을 두개로 나눈다.
   1. 중복되는 경우를 무조건 피해야 한다.
2. 최솟값을 구하기

사실 **Team을 나누는 것이 이 문제의 핵심** 이라고 본다.

Backtracking으로 해서 기존과 동일한 방식(```flag[]```)으로 하면 <sub>n</sub>P<sub>r</sub>의 방식을 사용하는 것이다.

하지만 해당 문제는 일단 팀1, 팀2의 구별 없이 사람을 나눴을 때 중복되는 경우 없이 나눠줘야 한다.

즉, 내가 팀1 구성이 완료 되면 자동적으로 팀2 구성원이 완성되기 때문에 팀 한개만 만들 생각으로 풀면 된다. 

예를 들어 6명에서 한 팀을 꾸린다고 하면 **<sub>6</sub>C<sub>3</sub>** 이렇게 나눠질 거이다. 하지만, 이것은 **팀1과 팀2를 구분한 경우**다.

**1번, 2번, 3번이 한팀하는 것과 4번, 5번, 6번이 한팀하는 경우는 같은 경우**라고 볼 수 있기 때문이다. 

즉, **<sub>N</sub>C<sub>N/2</sub> ÷ 2**가 총 경우의 수이다. 하지만 우리는 **경우의 수를 구하는 것이 아니라 경우 자체를 구하는 것**이다.

다른 방식으로 생각해보면, **팀1에 1번 선수를 고정해놓고 팀을 나누면 팀1과 팀2는 구분되기 때문에** <sub>N-1</sub>C<sub>N/2-1</sub>으로 처리할 수 있을 것이다.

최솟값을 구하는 방식은 두 가지 방식으로 생각했는데,

1. 1번팀을 짜는 과정에서 1번팀은 미리 계산후 팀 완성시 2번팀 계산하여 비교
2. 모든 팀 완성 후 비교

결국 가장 중요한 것은 **Backtracking으로 조합(<sub>n</sub>C<sub>r</sub>)를 구현하는 것**이다.

## 잘못된 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] statusTable;
  static boolean[] flag;
  static int subMin = Integer.MAX_VALUE;
  static int[] team1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // N명 (짝수)
    N = Integer.parseInt(br.readLine());

    // 1 ~ N까지 Table로 활용
    statusTable = new int[N + 1][N + 1];

    for(int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j < N + 1; j++) {
        int num = Integer.parseInt(st.nextToken());
        statusTable[i][j] = num;
      }
    }

    // Team1에 1번 선수는 Fix로 고정
    flag = new boolean[N + 1];
    flag[1] = true;

    team1 = new int[N / 2];
    team1[0] = 1;

    teamUp(1);

    System.out.println(subMin);
  }
  static void teamUp(int ptr) {
    for(int i = 2; i < N + 1; i++) {
      if(!flag[i]) {
        team1[ptr] = i;
        if(ptr == (N / 2) - 1) {
          compareStatus();
        }
        else {
           flag[i] = true;
           teamUp(ptr + 1);
           flag[i] = false;
        }
      }
    }
  }

  static void compareStatus() {
    int team1Status = 0;
    int[] team2 = new int[N / 2];
    int team2Status = 0;
    for(int i = 0; i < team1.length - 1; i++) {
      for(int j = i + 1; j < team1.length; j++) {
        team1Status += (statusTable[team1[i]][team1[j]] + statusTable[team1[j]][team1[i]]);
      }
    }

    int idx = 0;
    for(int i = 2; i < flag.length; i++) {
      if(!flag[i]) team2[idx++] = i;
    }

    for(int i = 0; i < team2.length - 1; i++) {
      for(int j = i + 1; j < team2.length; j++) {
        team2Status += statusTable[team2[i]][team2[j]] + statusTable[team2[j]][team2[i]];
      }
    }
    subMin = Math.min(Math.abs(team1Status - team2Status), subMin);
  }
}


```

해당 코드는 ```ArrayIndexOutOfBoundsException```오류를 출력한다.

어디서 오류가 나는 것일까?

처음부터 잘 훑어 보니 teamup()함수에서 compareStatus()함수를 실행시킬 때 부족한 것이 있다.

그것은 바로 아래의 부분이다.

```java
      if(!flag[i]) {
        team1[ptr] = i;
        if(ptr == (N / 2) - 1) {
          compareStatus();
        }
        
        ...

        int idx = 0;
        for(int i = 2; i < flag.length; i++) {
        if(!flag[i]) team2[idx++] = i;
        }
```

해당 코드는 team1의 마지막 Index에 i를 채워넣고 ```compareStatus()```함수를 실행한다. 

하지만, 마지막 team1[ptr]=i를 하고 compareStatus() 실행하기 전에 ```flag[i] = true```를 해줬는가? 아니다. 

N-Queen문제나 이전에 했던 Backtracking문제에선 굳이 마지막에 ```flag[i]```를 처리해 줄 필요가 없었다. 단순히 출력만 해주면 되었기 때문이다.

하지만, 해당 코드는 **마지막 과정에서 flag배열을 사용하기 때문에 처리해줘야 한다.**

또한 해당 코드는 **TLE**가 나왔다.

```java
// TLE

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] statusTable;
  static boolean[] flag;
  static int subMin = Integer.MAX_VALUE;
  static int[] team1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // N명 (짝수)
    N = Integer.parseInt(br.readLine());

    // 1 ~ N까지 Table로 활용
    statusTable = new int[N + 1][N + 1];

    for(int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j < N + 1; j++) {
        int num = Integer.parseInt(st.nextToken());
        statusTable[i][j] = num;
      }
    }

    // Team1에 1번 선수는 Fix로 고정
    flag = new boolean[N + 1];
    flag[1] = true;

    team1 = new int[N / 2];
    team1[0] = 1;

    teamUp(1, 0);

    System.out.println(subMin);
  }
  static void teamUp(int ptr, int team1Score) {
    for(int i = 2; i < N + 1; i++) {
      if(!flag[i]) {
        int sum = 0;
        team1[ptr] = i;
        flag[i] = true;

        //team1 score 재계산 (현재 추가되는 팀원과 기존 팀원들과의 능력치 합계)
        for(int j = 0; j < ptr; j++) {
          sum += statusTable[team1[j]][i] + statusTable[i][team1[j]];
        }

        if(ptr == (N / 2) - 1) {
          calcStatus(team1Score + sum);
        }
        else {
          teamUp(ptr + 1, team1Score + sum);
        }
        flag[i] = false;
      }
    }
  }
  static void calcStatus(int team1Score) {
    int[] team2 = new int[N / 2];
    int idx = 0;
    for(int i = 2; i < flag.length; i++) {
      if(!flag[i]) team2[idx++] = i;
    }

    int team2Score = 0;
    for(int i = 0; i < team2.length - 1; i++) {
      for(int j = i + 1; j < team2.length; j++) {
        team2Score += statusTable[team2[i]][team2[j]] + statusTable[team2[j]][team2[i]];
      }
    }

    subMin = Math.min(Math.abs(team1Score - team2Score) , subMin);
  }
}

```

해당 코드는 <sub>N-1</sub>C<sub>N/2-1</sub>을 재대로 구현하지 못했다. 해당 코드는 <sub>N-1</sub>P<sub>N/2-1</sub>를 구현한 것이다.

**반례**

```
20
0 23 39 39 93 81 61 5 27 72 93 10 9 16 29 82 74 97 10 52
24 0 23 8 42 5 92 37 53 14 84 14 100 75 61 88 24 15 50 12
8 15 0 27 15 87 38 60 88 20 89 7 29 100 82 69 46 21 84 31
27 89 3 0 13 16 47 75 23 10 20 20 69 45 69 3 59 93 20 40
49 3 13 60 0 33 19 92 49 26 61 4 87 59 57 45 81 1 85 36
19 28 83 3 32 0 10 97 31 21 67 32 52 33 17 47 24 31 42 95
31 48 6 99 11 88 0 50 43 79 98 80 62 37 51 24 61 40 17 22
27 48 29 95 53 97 35 0 34 92 70 6 31 58 71 68 47 29 9 36
3 75 95 43 56 11 76 95 0 53 50 93 59 83 76 92 79 91 51 42
35 11 72 57 68 23 57 96 33 0 34 59 75 12 88 10 61 24 72 39
88 7 47 24 30 58 4 23 11 93 0 99 2 46 3 82 6 85 20 74
38 81 43 31 30 96 27 18 91 4 55 0 84 79 89 79 91 47 24 14
78 1 14 44 14 77 7 71 26 39 10 63 0 8 80 9 83 42 88 18
77 30 30 50 33 24 88 82 80 92 71 82 20 0 98 86 83 65 49 17
46 20 51 94 44 45 93 28 37 49 2 15 1 73 0 4 20 77 59 70
81 37 29 24 29 79 59 78 89 81 58 89 27 34 94 0 70 14 39 80
47 7 6 4 95 68 16 37 84 60 23 44 88 51 63 100 0 55 27 62
14 70 70 34 11 75 90 12 62 84 61 53 57 14 100 26 92 0 99 17
75 48 18 22 35 77 81 80 45 63 50 57 93 94 17 95 30 72 0 2
30 28 12 76 39 87 45 36 71 16 86 48 65 100 38 61 24 64 96 0

// 정답
0
```

## 나의 코드

오름차순으로 구현하면서 **조합(<sub>N</sub>C<sub>r</sub>)** 을 구현하였다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] statusTable;
  static boolean[] flag;
  static int subMin = Integer.MAX_VALUE;
  static int[] team1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // N명 (짝수)
    N = Integer.parseInt(br.readLine());

    // 1 ~ N까지 Table로 활용
    statusTable = new int[N + 1][N + 1];

    for(int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j < N + 1; j++) {
        int num = Integer.parseInt(st.nextToken());
        statusTable[i][j] = num;
      }
    }

    // Team1에 1번 선수는 1번 팀 Fix로 고정
    flag = new boolean[N + 1];
    flag[1] = true;

    team1 = new int[N / 2];
    team1[0] = 1;

    teamUp(1, 0);

    System.out.println(subMin);
  }
  static void teamUp(int ptr, int team1Score) {
    for(int i = 2; i < N + 1; i++) {
      if(!flag[i]) {
        if(team1[ptr - 1] < i) {
          int sum = 0;
          team1[ptr] = i;
          flag[i] = true;

          //team1 score 재계산 (현재 추가되는 팀원과 기존 팀원들과의 능력치 합계)
          for(int j = 0; j < ptr; j++) {
            sum += statusTable[team1[j]][i] + statusTable[i][team1[j]];
          }

          if(ptr == (N / 2) - 1) {
            calcStatus(team1Score + sum);
          }
          else {
            teamUp(ptr + 1, team1Score + sum);
          }
          flag[i] = false;
        }
      }
    }
  }
  static void calcStatus(int team1Score) {
    int[] team2 = new int[N / 2];

    int idx = 0;
    for(int i = 2; i < flag.length; i++) {
      if(!flag[i]) team2[idx++] = i;
    }

    int team2Score = 0;
    for(int i = 0; i < team2.length - 1; i++) {
      for(int j = i + 1; j < team2.length; j++) {
        team2Score += statusTable[team2[i]][team2[j]] + statusTable[team2[j]][team2[i]];
      }
    }

    subMin = Math.min(Math.abs(team1Score - team2Score) , subMin);
  }
}
```

## 참고한 사이트

- [[수학] 순열, 조합 공식 총정리](https://coding-factory.tistory.com/606)