# 색종이 올려 놓기 

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|5079	|1702	|1293	|34.702%|

## 문제 

크기가 모두 다른 직사각형 모양의 색종이가 여러 장 있다. 색종이를 하나씩 올려 놓아, 되도록 많은 장수의 색종이들을 쌓으려고 한다.

새로 한 장의 색종이를 올려 놓을 때는 지금까지 쌓아 놓은 색종이들 중 맨 위의 색종이 위에 올려놓아야 하며 아래의 두 조건을 모두 만족해야 한다.

- 조건 1 : 새로 올려 놓는 색종이는 맨 위의 색종이보다 크지 않아야 한다. 즉, 맨 위의 색종이 밖으로 나가지 않아야 한다.
- 조건 2 : 새로 올려 놓는 색종이와 맨 위의 색종이의 변들은 서로 평행해야 한다.(색종이를 90˚돌려 놓을 수 있다.)

예를 들어, 아래의 그림 중에서 위의 두 조건을 모두 만족하는 경우는 (나)뿐이다.


| ![](https://upload.acmicpc.net/aded1664-9f0a-4026-bd52-37b978453881/-/preview/)  | ![](https://upload.acmicpc.net/830e691c-1989-4613-8dc9-0257d20214fc/-/crop/188x156/246,0/-/preview/)  | ![](https://upload.acmicpc.net/aded1664-9f0a-4026-bd52-37b978453881/-/preview/)  | ![](https://upload.acmicpc.net/f18e7a59-08a6-4156-9690-e9ff061a9d1f/-/preview/)  |
|:--------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|
|                                       (가)                                        |                                                  (나)                                                  |                                       (다)                                        |                                       (라)                                        |

색종이는 두 변의 길이로 표현된다. (3, 5)는 두 변의 길이가 각각 3과 5인 색종이를 나타낸다. 예를 들어, 다음과 같이 7장의 색종이가 주어졌다고 하자 : (1, 2), (8, 7), (20, 10), (20, 20), (15, 12), (12, 14), (11, 12) 위의 조건을 만족하면서 가장 많이 쌓을 수 있는 색종이들의 순서는 (20, 20), (15, 12), (12, 14), (11, 12), (8, 7), (1, 2)이다.

입력으로 색종이들이 주어졌을 때, 위의 조건 1과 조건 2를 만족하면서 쌓을 수 있는 최대 색종이 장수를 구하는 프로그램을 작성하시오.

## 입력 

첫 번째 줄에는 색종이의 장수가 주어진다. 다음 줄부터 각 줄에 색종이의 두 변의 길이가 주어진다. 두 변의 길이는 한 칸 띄어 주어진다. 색종이의 최대 장수는 100이고, 각 변의 길이는 1000보다 작은 자연수이다.

## 출력 

쌓을 수 있는 최대 색종이 장수를 출력한다.

## 예제 입력 1

```
7
1 2
8 7
20 10
20 20
15 12
12 14
11 12
```

## 예제 출력 1

```
6
```

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] confetti = new int[N][2];

    // 색종이 크기 입력 받고 confetti[i][0]의 크기 비교 해서 내림차순 정렬
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      // i 행에서 0번째 원소가 1번째 원소보다 더 크도록 넣어주기.
      if (n1 < n2) {
        confetti[i][0] = n2;
        confetti[i][1] = n1;
      } else {
        confetti[i][0] = n1;
        confetti[i][1] = n2;
      }

      // 내림차순 정렬
      for (int j = i - 1; j >= 0; j--) {
        if (confetti[j][0] < confetti[j + 1][0]) {
          int[] temp = confetti[j];
          confetti[j] = confetti[j + 1];
          confetti[j + 1] = temp;
        } else if (confetti[j][0] == confetti[j + 1][0] && confetti[j][1] < confetti[j + 1][1]) {
          int[] temp = confetti[j];
          confetti[j] = confetti[j + 1];
          confetti[j + 1] = temp;
        }
        else break;
      }
    }

    int[] dp = new int[N];

    init_dp(dp, confetti, N);

    confetti_dp(dp, N);

    if (ans == 0) System.out.println(1);
    else System.out.println(ans);
  }
  static void init_dp(int[] dp, int[][] confetti, int N) {
    for (int i = 0; i < N; i++) {
      int count = 0;
      for (int j = i + 1; j < N; j++) {
        if (confetti[i][1] >= confetti[j][1])
          count++;
      }
      dp[i] = count;
    }
  }

  static void confetti_dp(int[] dp, int N) {
    int maxIdx;
    for (int i = 0; i < N; i = ++maxIdx) {
      maxIdx = i;
      if (i != N - 1 && dp[i] == 0) continue;

      for (int j = i + 1; j < N; j++) {
        if (dp[maxIdx] < dp[j]) maxIdx = j;
      }
      ans++;
    }
  }
}
```

**반례**

```
10
3 49
7 30
6 45
15 19
8 41
18 23
14 33
15 34
28 31
47 50
```

**정답**

```
4
```

**출력**

```
5
```