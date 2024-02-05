# 평범한 배낭 2

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	5148|	1204|	768|	23.479%|

## 문제

이 문제는 아주 평범한 배낭에 관한 두 번째 문제이다.

민호는 BOJ 캠프에 가기 위해 가방을 싸려고 한다. 가방에 어떠한 물건들을 넣냐에 따라 민호의 만족도가 달라진다. 집에 있는 모든 물건들을 넣으면 민호가 느낄 수 있는 만족도는 최대가 될 것이다. 하지만 민호가 들 수 있는 가방의 무게는 정해져 있어 이를 초과해 물건을 넣을수가 없다.

민호가 만족도를 최대로 느낄 수 있는 경우를 찾아보자.

단, 집에 동일한 물건들이 여러개가 있을 수 있기 때문에 한 물건을 두개 이상 챙기는 것도 가능하다.

## 입력 

첫 번째 줄에 N, M (1 ≤ N ≤ 100, 1 ≤ M ≤ 10,000) 이 빈칸을 구분으로 주어진다. N은 민호의 집에 있는 물건의 종류의 수이고 M은 민호가 들 수 있는 가방의 최대 무게다.

두 번째 줄부터 N개의 줄에 걸쳐 민호의 집에 있는 물건의 정보가 주어진다.

각각의 줄은 V, C, K (1 ≤ V ≤ M, 1 ≤ C, K ≤ 10,000, 1 ≤ V * K ≤ 10,000) 으로 이루어져 있다.

V는 물건의 무게, C는 물건을 가방에 넣었을 때 올라가는 민호의 만족도, K는 물건의 개수이다.

## 출력 

최대 무게를 넘기지 않게 물건을 담았을 때 민호가 느낄 수 있는 최대 만족도를 출력한다.

## 예제 입력 1

```
2 3
2 7 1
1 9 3
```

## 예제 출력 1

```
27
```

## 예제 입력 2

```
3 9
8 5 1
1 2 2
9 4 1
```

## 예제 출력 2

```
7
```

## 코드

**WA**

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
    int M = Integer.parseInt(st.nextToken());

    // row: 물건, col: 최대 허용치 무게, value: 만족도
    int[][] dp = new int[N + 1][M + 1];

    // row: 물건, col(value): {물건의 무게, 민호의 만족도, 물건의 개수}
    int[][] knapsack = new int[N + 1][3];

    for (int i = 1 ; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(dp, knapsack, N, M);

    System.out.println(dp[N][M]);
  }

  static void knapsack_dp(int[][] dp, int[][] knapsack, int N, int M) {
    for (int i = 1; i < N + 1; i++) {
      int curThingWeight = knapsack[i][0];
      int curThingHappyNum = knapsack[i][1];
      for (int w = 1; w < M + 1; w++) {
        if (curThingWeight > w) {
          dp[i][w] = dp[i - 1][w];
        }
        else {
          int maxUsingThingNum = w / curThingWeight;
          if (knapsack[i][2] >= maxUsingThingNum) {
            dp[i][w] = Math.max(Math.max(dp[i][w - curThingWeight] + curThingHappyNum, dp[i][w - 1]), dp[i - 1][w - curThingWeight] + curThingHappyNum);
          }
          else {
            dp[i][w] = Math.max(dp[i][w - 1], dp[i - 1][w - curThingWeight] + curThingHappyNum);
          }
          dp[i][w] = Math.max(dp[i - 1][w], dp[i][w]);
        }
      }
    }
  }
}
```

**중간에 멈춘 풀이**

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
    int M = Integer.parseInt(st.nextToken());

    int[][] knapsack = new int[N + 1][3];
    int[][] dp = new int[N + 1][M + 1];

    for (int i = 1; i < N + 1 ; i++) {
      st = new StringTokenizer(br.readLine());

      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(knapsack, dp, N, M);

    System.out.println(dp[N][M]);
  }
  static void knapsack_dp(int[][] knapsack, int[][] dp, int N, int M) {
    for (int i = 1; i < N + 1; i++) {
      int itemWeight = knapsack[i][0];
      int itemSatisfy = knapsack[i][1];
      int itemCount = knapsack[i][2];

      for (int w = 1; w < M + 1; w++) {
        if (itemWeight > w) {
          dp[i][w] = dp[i - 1][w];
        }
        else {
          // dp[i][w] = Math.max(dp[i - 1][w - itemWeight] + itemSatisfy * canUseItemCount, dp[i - 1][w]);
          // dp[i][w]를 채울 떄 knapsack[i]의 물건을 사용하면(이전에도 사용했는데 갯수가 남은 상황)에는 dp[i - 1][w - itemWeight]를 더하면 안된다.)
          // w % itemWeight를 이용해서 나머지 값을 이용

          int canUseItemCount = w / itemWeight > itemCount ? itemCount : w / itemWeight;
          int itemsTotalWeight = itemWeight * canUseItemCount;
          int remainderWeight = w - itemsTotalWeight > 0 ? w - itemsTotalWeight : w % itemWeight; // 주의: itemWeight 대신 itemTotalWeight 사용 X

          dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][remainderWeight] + itemSatisfy * canUseItemCount);
        }
      }
    }
  }
}
```

해당 방식으로 풀다가 접근 방법이 보이기 시작한거 같다.

현재 생각하는 방법은 다음과 같다.

먼저 생각해야 할 것은 갯수가 하나가 아니기 때문에 이전에 대각선과 직선으로 처리하던 방식은 불가능 합니다.

여기서 이 문제를 풀기 위해선 대각선과 직선의 정확한 의미를 이해하셔야 합니다.

> **대각선**: i번쨰 Item을 **하나 사용**하고 **남은 나머지 하중에서의 최댓값을 더한 것**
> **직선**: i번쨰 Item을 사용하지 않고 **오로지 i - 1에서 처리한 현재 하중에서의 최댓값을 가져온 것**

핵심은 **나머지 하중**입니다. 

결국 이 문제는 이 방식의 확장판이라고 보면 됩니다. 

Item의 갯수가 하나가 아니기 때문에 대각선을 이용하기 위해서 Item의 갯수만큼 더 하중을 사용하기 때문에 왼쪽으로 더 갈 것이다.

여기서 남은 하중을 dp[i - 1][remainder]를 구하면 dp[i][w]를 구할 수 있을 것이다.

- **대각선**: dp[i - 1][remainer] + ItemSatisfy * UsingItemCount

사실 이 부분은 위에서 풀이 한 것과 거의 같은 접근입니다.

문제는 직선인데, 직선은 i번째 Item 사용하지 않고 그대로 i - 1번째로부터 value를 내린것이라고 말했습니다.

하지만, 이것은 이전과 상황이 달라졌습니다. Item을 여러 개 사용할 수 있게 되었기에, 대각선에서도 봤다싶히 남은 하중의 할용으로 dp를 처리했습니다.

직선의 경우도 동일합니다. 위에서 내려받은 값의 남은 하중을 받아서 그것을 i번째 행에서 가져와야 합니다. 

- **직선**: dp[i - 1][j] + dp[i][remainder]

사실, 대각선에서의 나머지 값은 구하는 과정에서 도출해 낼 수 있는데, i - 1번째에서 받은 칸에서의 나머지 값은 알 수 있을리가 없다.

즉, 따로 값을 저장해둘 필요가 있다. (다행히도, 앞에서도 remainder를 구하는 작업을 요구하기 때문에 시간 복잡도는 늘어나지 않고, 공간 복잡도만 늘어남)

중간에 무엇을 놓친것 같다....

**WA**

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
    int M = Integer.parseInt(st.nextToken());

    int[][] knapsack = new int[N + 1][3];
    int[][] dp = new int[N + 1][M + 1];
    int[][] remainder = new int[N + 1][M + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(knapsack, dp, remainder, N, M);

    System.out.println(dp[N][M]);
  }
  static void knapsack_dp(int[][] knapsack, int[][] dp, int[][] remainder, int N, int M) {
    for (int i = 1; i < N + 1; i++) remainder[0][i] = i;

    for (int i = 1; i < N + 1; i++) {
      int itemWeight = knapsack[i][0];
      int itemSatisfy = knapsack[i][1];
      int itemAmount = knapsack[i][2];
      for (int w = 1; w < M + 1; w++) {
        if (itemWeight > w) {
          dp[i][w] = dp[i - 1][w];
        }
        else {
          int canUseItemAmount = (w / itemWeight) > itemAmount ? itemAmount : w / itemWeight;
          int itemTotalWeight = canUseItemAmount * itemWeight;
          int diagonalRemainder = w - itemTotalWeight;

          int diagonalTotalSatisfy = dp[i - 1][diagonalRemainder] + itemSatisfy * canUseItemAmount;
          int upTotalSatisfy = dp[i - 1][w] + dp[i][remainder[i - 1][w]];

          if (diagonalTotalSatisfy > upTotalSatisfy) {
            dp[i][w] = diagonalTotalSatisfy;
            remainder[i][w] = remainder[i - 1][diagonalRemainder];
          }
          else if (diagonalTotalSatisfy == upTotalSatisfy) {
            if (remainder[i - 1][diagonalRemainder] >= remainder[i - 1][w]) {
              dp[i][w] = diagonalTotalSatisfy;
              remainder[i][w] = remainder[i - 1][diagonalRemainder];
            }
            else {
              dp[i][w] = upTotalSatisfy;
              remainder[i][w] = remainder[i - 1][w] % itemWeight;
            }
          }
          else {
            dp[i][w] = upTotalSatisfy;
            remainder[i][w] = remainder[i - 1][w] % itemWeight;
          }
        }
      }
    }
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Item[] items = new Item[N + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int quota = Integer.parseInt(st.nextToken());

      items[i] = new Item(weight, cost, quota);
    }

    int[][] table = new int[N + 1][M + 1];

    activeDp(items, table, N, M);

    System.out.println(table[N][M]);
  }
  private static void activeDp(Item[] items, int[][] table, int N, int M) {

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        if (j >= items[i].weight) {
          int max = table[i - 1][j];
          for (int k = 1; k <= items[i].quantity; k++) {
            if (j - k * items[i].weight < 0) break;
            max = Math.max(max, table[i - 1][j - k * items[i].weight] + k * items[i].cost);
          }
          table[i][j] = max;
        } else {
          table[i][j] = table[i - 1][j];
        }
      }
    }
  }
}
class Item {
  int weight;
  int cost;
  int quantity;

  Item(int weight, int cost, int quantity) {
    this.weight = weight;
    this.cost = cost;
    this.quantity = quantity;
  }
}
```

해당 문제의 풀이 핵심은 주어진 양을 그대로 활용하면 시간 초과가 나오기 때문에 그것을 쪼개서 사용해야 합니다.

모든 수는 2진수의 형태로 표현이 가능한 것과 나머지를 이용하는 방식을 사용하면 다음과 같은 형태로 문제를 해결할 수 있습니다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Item> itemList = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int quantity = Integer.parseInt(st.nextToken());

      addItems(itemList, weight, cost, quantity);
    }

    System.out.println(getMaxCost(itemList, M));
  }
  private static int getMaxCost(List<Item> itemList, int M) {

    int listSize = itemList.size();
    int[][] table = new int[listSize + 1][M + 1];

    for (int i = 1; i < listSize + 1; i++) {
      Item item = itemList.get(i - 1);

      for (int j = 1; j < M + 1; j++) {
        if (j < item.weight) {
          table[i][j] = table[i - 1][j];
        } else {
          table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - item.weight] + item.cost);
        }
      }
    }

    return table[listSize][M];
  }
  private static void addItems(List<Item> itemList, int weight, int cost, int quantity) {
    for (int i = quantity; i > 0; i = (i >> 1)) {
      int remain = i - (i >> 1);

      itemList.add(new Item(weight * remain, cost * remain, remain));
    }
  }
}
class Item {
  int weight;
  int cost;
  int quota;

  Item(int weight, int cost, int quota) {
    this.weight = weight;
    this.cost = cost;
    this.quota = quota;
  }
}
```

**최종 복습 풀이(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Item[] initItemArray = new Item[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());

      initItemArray[i] = new Item(weight, cost, count);
    }

    List<SeparatedItem> itemList = dividedItemList(initItemArray, N);

    int rowLen = itemList.size();

    int[][] dpTable = new int[rowLen + 1][M + 1];

    for (int i = 1; i <= rowLen; i++) {
      for (int j = 0; j <= M; j++) {
        if (j >= itemList.get(i - 1).weight) {
          dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i - 1][j - itemList.get(i - 1).weight] + itemList.get(i - 1).cost);
        } else {
          dpTable[i][j] = dpTable[i - 1][j];
        }
      }
    }

    System.out.println(dpTable[rowLen][M]);
  }
  private static List<SeparatedItem> dividedItemList(Item[] initItemArray, int N) {

    List<SeparatedItem> itemList = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      int weight = initItemArray[i].weight;
      int cost = initItemArray[i].cost;
      int count = initItemArray[i].count;
      while (count != 1) {
        int curCount = count - count / 2;
        itemList.add(new SeparatedItem(weight * curCount, cost * curCount));

        count -= curCount;
      }
      itemList.add(new SeparatedItem(weight, cost));
    }

    return itemList;
  }
}
class SeparatedItem {
  int weight;
  int cost;

  SeparatedItem(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }
}
class Item {
  int weight;
  int cost;
  int count;

  Item (int weight, int cost, int count) {
    this.weight = weight;
    this.cost = cost;
    this.count = count;
  }
}
```