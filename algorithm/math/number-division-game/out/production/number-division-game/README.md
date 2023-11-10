# 수 나누기 게임

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|1024 MB	|1502	|555|	470	|42.266%|

## 문제 

《보드게임컵》을 준비하다 지친 은하는 보드게임컵 참가자들을 경기장에 몰아넣고 결투를 시키는 게임 《수 나누기 게임》을 만들었습니다.

《수 나누기 게임》의 규칙은 다음과 같습니다.

- 게임을 시작하기 전 각 플레이어는 1부터 1,000,000 사이의 수가 적힌 서로 다른 카드를 잘 섞은 뒤 한 장씩 나눠 가집니다.
- 매 턴마다 플레이어는 다른 플레이어와 한 번씩 결투를 합니다.
- 결투는 서로의 카드를 보여주는 방식으로 진행되며, 플레이어의 카드에 적힌 수로 다른 플레이어의 카드에 적힌 수를 나눴을 때, 나머지가 0이면 승리합니다. 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나누어 떨어지면 패배합니다. 둘 다 아니라면 무승부입니다. 
- 승리한 플레이어는 1점을 획득하고, 패배한 플레이어는 1점을 잃습니다. 무승부인 경우 점수의 변화가 없습니다.
- 본인을 제외한 다른 모든 플레이어와 정확히 한 번씩 결투를 하고 나면 게임이 종료됩니다.
《수 나누기 게임》의 결과를 가지고 한별이와 내기를 하던 은하는 게임이 종료되기 전에 모든 플레이어의 점수를 미리 알 수 있을지 궁금해졌습니다. 은하를 위해 각 플레이어가 가지고 있는 카드에 적힌 수가 주어졌을 때, 게임이 종료된 후의 모든 플레이어의 점수를 구해주세요.

## 입력 

첫 번째 줄에 플레이어의 수 N이 주어집니다.

두 번째 줄에 첫 번째 플레이어부터 N번째 플레이어까지 각 플레이어가 가지고 있는 카드에 적힌 정수 x<sub>1</sub>, ..., x<sub>N</sub>이 공백으로 구분되어 주어집니다.

## 출력 

첫 번째 플레이어부터 N번째 플레이어까지 게임이 종료됐을 때의 각 플레이어의 점수를 공백으로 구분하여 출력해주세요.

## 예제 입력 1

```
3
3 4 12
```

## 예제 출력 1

```
1 1 -2
```

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    int[] card = new int[N + 1];
    int[] checkNum = new int[1000001];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int num = Integer.parseInt(st.nextToken());

      checkNum[num] = i;
      card[i] = num;
    }

    int[] result = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = card[i] * 2; j <= 1000000; j += card[i]) {

        if (checkNum[j] != 0) {
          result[i]++;
          result[checkNum[j]]--;
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
```