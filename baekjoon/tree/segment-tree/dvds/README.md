# 디지털 비디오 디스크(DVDs)

**플래티넘 3**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|256 MB|	3612|	1534|	1157|	40.668%|

## 문제 

최근 유튜브와 같은 온라인 비디오 스트리밍 서비스 때문에 DVD 대여점들이 자취를 감추고 있다. 이러한 어려운 상황 속에서, DVD 대여점 주인들은 실낱같은 희망을 잡고자 인기있는 N개의 DVD들로 구성된 시리즈를 구매한다(각 DVD들은 0번부터 N-1 까지 이루어져 있다).

ACM 대여점의 주인 원주연 또한 울며 겨자먹기로 인기있는 시리즈물을 구매했고, 진열을 하기 위해 맞춤형 선반을 주문제작 하였다(맟춤제작이기 때문에 선반의 번호 또한 0번부터 N-1 까지 이루어져 있다). 주연이는 매우 정갈한 사람이기 때문에 DVD를 진열할 때 i번 DVD는 i번 선반에 진열을 한다.

이 시리즈의 열렬한 팬인 민호는 주연이네 대여점에 시리즈가 입고되었다는 소식을 듣고 찾아왔다. 시리즈물은 연속으로 봐야 흥미가 안떨어지기 때문에 민호는 L번부터 R번까지의 DVD들을 빌리려고 한다. 민호는 주연이가 매우 정갈한 성격인 것임을 알기에 주연이를 믿고 실제 DVD들의 번호를 확인하지 않고 L번 선반부터 R번 선반까지 존재하는 DVD들을 들고 카운터에 가져왔다.

그러나, 민호는 간과한 사실이 있다. 주연이네 대여점에는 진상 손님인 진일이가 찾아온다는 것이였다. 진일이는 선반 A 에 있는 DVD와 선반 B에 있는 DVD를 서로 바꿔 놓는다. 이러한 진일이의 몰상식한 행동때문에 민호와 같이 주연이를 믿고 DVD의 번호를 확인 안하는 선량한 고객들이 피해를 입는 사례들이 속출하였다. 아무 이유가 없는 묻지마 테러로 인해 가게매출이 떨어질 위기에 처하자 주연이는 진일이가 보일때마다 쫒아 냈지만, 시도때도없이 찾아오는 진일이의 진상짓을 막기에는 역부족이였다.

이러한 주연이를 보고 안타까운 마음이 든 민호는 주연이를 위해 프로그램을 작성하기로 결심을 한다. 의욕이 넘치는 민호의 마음과는 달리 실력이 따라주지 못해 프로그램의 기능은 조촐하기만 하다. 프로그램의 기능은 다음과 같다.

손님이 L번 선반부터 R번 선반까지에 있는 DVD들을 가져 왔을때 실제로 DVD가 L번부터 R번까지 있나 확인을 해 줄 수 있다.
DVD의 순서는 상관이 없다. 예를 들어 손님이 2번 선반부터 4번 선반까지에 있는 DVD를 가져왔을 때 DVD가 2, 3, 4 순서로 진열되어 있건, 4, 2, 3 순서로 진열되어 있건 상관이 없다는 얘기다. 즉 L번부터 R번까지의 DVD가 있으면 된다.
문제의 단순화를위해 고객이 DVD를 빌려가면, 그 즉시 시청한뒤 바로 반납한다고 가정한다. 또한 가져다 놓는 위치는 빌리기 전과 동일하다(4, 3, 2 순서로 진열되어 있었으면 다시 4, 3, 2 순서로 진열한다).

## 입력 

첫 번째 줄에는 테스트 케이스의 수 T가 주어진다. (T ≤ 20 인 자연수)

각각의 테스트 케이스 첫 번째 줄에는 DVD들의 수를 의미하는 정수 N 과 대여점에서 일어나는 사건의 수를 의미하는 정수 K 가 주어진다. (1 ≤ N ≤ 100,000 , 1 ≤ K ≤ 50,000)

이어서 대여점에서 일어나는 사건 K 개가 주어진다. 각각의 줄은 세 정수 Q, A, B 을 포함한다. (Q는 0또는 1이고, 0 ≤ A ≤ B < N )

Q는 0 일때, 진상 손님 진일이가 선반 A의 DVD와 선반 B의 DVD를 서로 바꿔 끼우는 사건을 의미한다.

Q가 1 일때는 손님이 선반 A부터 선반 B에 있는 DVD를 카운터에 가져오는 사건을 의미한다. 위에서도 언급했듯이 이 사건이 DVD들의 위치를 바꾸는 일은 일어나지 않는다.

## 출력 

손님이 DVD를 카운터에 가져왔을 때 손님이 원하는 DVD가 전부 존재하면, (A번 선반부터 B번 선반까지에 있는 DVD를 전부 가져왔을 때 순서에 상관없이 A번 DVD부터 B번 DVD까지 있다면) "YES"를 출력하고, 그렇지 않다면 "NO"를 출력한다.

## 예제 입력 1

```
2
5 8
1 0 4
1 1 2
0 1 3
1 2 2
1 1 3
1 0 0
1 0 2
1 2 4
5 5
0 1 2
0 2 3
0 1 3
1 0 1
1 0 2
```

```
YES
YES
YES
YES
YES
NO
NO
YES
NO
```

## 풀이 방식 

### 원래 생각했던 풀이

해당 풀이는 잘못된 풀이라서 그것에 대한 피드백 위주로 정리를 하겠습니다.

먼저 방식은 **Segment Tree**입니다. 

**필요한 Data**
- 선반(0 ~ N - 1) -> 범위(start - end) 
- Leaf Node: 선반안에 있는 비디오 숫자, Internal Node: 구간 합 
- Leaf Node: 올바르게 비디오가 선반에 있는지, Internal Node: 구간 내에서 비디오 위치 변경 횟수

**동작 매커니즘**

1. **initST()**
   1. **Parmeter**: int[][] tree, int node, int start, int end, int[] original
      1. **tree**: Size = tree_size, 2 // tree[node][0] = 비디오 Num || Prefix Sum // tree[node][1] = (0 || 1) || (변경 횟수 n)
      2. **node**: tree의 Index // 
      3. **start**: 구간 내 선반의 처음 Index
      4. **end**: 구간 내 선반의 마지막 Index
      5. **original**: 구간 내 합을 담아둘 공간 // Size : tree_size - N (리프 노드 제외)
   2. **Return Type**: void
   3. **설명**: 세그먼트트리 형성 및 구간 내 합 저장
      1. 세그먼트 트리를 형성할 때 리프 노드에서는 선반 번호를 비디오 Num으로 넣어주고, 변경 사항이 없으므로 모든 리프 노드 tree[node][1] = 0이다.
      2. 재귀 함수에서 돌아오는 과정에서 구간 내 합을 저장해주고, 변경 횟수 = 0, original[node]에 구간 내 합을 저장해준다.
2. **update()**
   1. **Parameter**: int[][] tree, int node, int start, int end, int idx, int val
      1. **idx**: 변경할 선반 번호
      2. **val**: 해당 선반에 넣어줄 비디오 번호
   2. **Return Type**: int => (바뀌기 전 비디오 번호)
   3. **설명**: Swap() 과 같은 매커니즘으로 작동한다.
      1. 먼저 peek()함수를 통해서 선반 A, B중에 하나를 선택해서 해당 위치에 꽂혀있는 비디오 숫자를 가져온다.
      2. 그 후 다른 선반 하나를 먼저 update()를 통해서 바꿔준다. 이 과정에서 기존에 꽂혀있던 비디오 숫자는 return 값으로 받아온다.
      3. 그 후 나머지 선반을 update()를 통해서 바꿔주면 끝
3. **query()**
   1. **Parameter**: int[][] tree, int node, int start, int end, int left, int right, int[] original
      1. **left**: 원하는 구간 내 선반 중 작은 값(= 최솟값) 
      2. **right**: 원하는 구간 내 선반 중 큰 값(= 최댓값)
   2. **Return Type**: int ?? // boolean ??
   3. **설명**: Return Type을 결정하지 못했다. 처음에 생각해서 풀때는 boolean 으로 Return Type을 설정해서 풀 수 있을 것이라고 생각했지만, 안되고 그래서 int형으로 풀라고 헀지만 힘들어 보였다. 이유는 아래에 따로 정리하도록 하겠습니다.

> **이유**
> 
> 먼저 처음에 생각했던 동작 방식을 적어보겠습니다.
> 재귀를 하면서 원하는 구간내에 도달했을 경우 다음과 같이 확인합니다.
> 1. 구간 내에서 변경 횟수가 0일시 return true
> 2. 구간 내에서 변경 횟수가 홀수일시 return false
> 3. 구간 내에서 변경 횟수가 0이 아닌 짝수일시 
>    1. original[]과 합계를 비교한다.
>       1. 같을 경우 요소 전부 확인 
>          1. 모두 원하는 구간내 값이면 true
>          2. 하나라도 구간외의 값이면 false
>       2. 다를 경우 return false
> 
> 이와 같은 방식으로 매커니즘을 짰습니다. 하지만, 여기선 큰 오류가 있습니다.
> 
> 해당 방식은 큰 오류는 **"둘로 나눠진 재귀함수에서 받아오는 Return 값이 같을까?"** 입니다. 
> 
> 즉, 해당 방식은 결국 left <= start && end <= right의 경우만 신경쓴 것이고, **구간이 찢어지는 경우** start < left && end <= right 라던지 left <= start && right <= end 는 고려하지 않은 것 입니다.
> 
> 재귀함수에서 돌아오는 과정에서 lQuery가 false이고, rQuery가 True라면 과연 어떻게 처리해야 할 것인가? 거기다가 현재 위에서 보면 true, false도 간단하게 나눠지는 것이 아니라 경우의 수가 꽤 된다. 
> 
> 만약, 구간이 두번 이상 찢어지고 양쪽 Return 값이 true, false로 나누어 지는 상황이 두번 이상 나온다면 매우 애매해질 것이다. 
> 
> 예를 들면 1 ~ 11 까지에서 3 이랑 7을 바꾸고 나서 선반 3 에서 7까지 비디오를 가져온다면 다음 풀이로는 올바른 답을 가져올 수 없다.
> 
> 그렇다고 int형을 쓴다는 것은 구간 내 합계를 비교하는 것인데, 이것도 근본적으로 구간이 찢어지는 경우 처리하기 힘들다.
> 
> 구간내 합계 tree[node][0] == original[node]랑 같은 경우 매번 모든 요소를 확인하는 행위는 너무 오랜 시간이 소모될 것이고, 또한 이 방식 대로라면 세그먼트 트리를 사용하는 이유조차 없어보인다. 

결국 해당 풀이로 잘못 접어든 이유는 다음과 같다고 생각합니다.

1. 세그먼트 트리에서 구간이 찢어지는 경우를 고려 X
2. 재귀 함수에서 돌아오는 과정에서 Return 값 활용 방식을 잘못 생각

### 올바른 풀이 

해당 문제에 올바른 풀이는 다음과 같습니다.

0 ~ N - 1 선반에 같은 숫자들의 비디오가 순서대로 꽂혀 있다. 

여기서, 내가 A 선반부터 B 선반 까지 비디오를 가져올 때 내부에서 비디오 교환이 매우 일어나도 바뀌지 않는 것이 있다.

바로 **최솟값**, **최댓값**입니다. 물론, 구간내 합도 변하지 않지만, 문제는 구간내의 숫자와 구간 외에의 숫자를 2번 이상 교체하여 총합을 유지시킬 수 있는 것이 문제다.

만약, 일반적인 세그먼트 트리 처럼 범위 값(start, end)이 원래 배열의 index와 같이 Value가 아닌 다른 값에 해당한다면 이와 같이 되지 않을 것입니다.

좀더 일반적으로 정리하자면 이와 같을 것입니다.

**0 ~ N - 1 까지 연속적으로 비디오 번호가 존재**

이 이유 때문에 교체가 일어나도 최솟값, 최댓값이 변경될 수 없는 이유입니다.

## 코드 

**잘못된 풀이 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int h = (int)Math.ceil(Math.log(N) / Math.log(2));
      int tree_size = 1 << (h + 1);

      int[][] tree = new int[tree_size][2];
      int[] original = new int[tree_size - N];

      initST(tree, 1, 0, N - 1, original);

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        switch (Q) {
          case 0:
            int val = peek(tree, 1, 0, N - 1, n1);
            val = update(tree, 1, 0, N - 1, n2, val);
            update(tree, 1, 0, N - 1, n1, val);
            break;
          case 1:
            if (n1 > n2) {
              int temp = n1;
              n1 = n2;
              n2 = temp;
            }


            break;
          default:
            break;
        }
      }
    }

  }

  static void initST(int[][] tree, int node, int start, int end, int[] original) {
    if (start == end) {
      tree[node][0] = start;
      tree[node][1] = 0;
    }
    else {
      initST(tree, node * 2, start, (start + end) / 2, original);
      initST(tree, node * 2 + 1, (start + end) / 2 + 1, end, original);
      tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
      original[node] = tree[node][0];
      tree[node][1] = 0;
    }
  }

  static int peek(int[][] tree, int node, int start, int end, int idx) {
    if (idx < start || end < idx) {
      return 0;
    }
    if (start == idx) {
      return tree[node][0];
    }
    int val = peek(tree, node * 2, start, (start + end) / 2, idx);
    if(val != 0) return val;
    val = peek(tree, node * 2, (start + end) / 2 + 1, end, idx);
    return val;
  }

  static int update(int[][] tree, int node, int start, int end, int idx, int val) {
    if (idx < start || end < idx) {
      return 0;
    }
    if (start == idx) {
      // 비상 -> right 선반에 있는 것이 right DVD 가 아닐수도 있다.
      // tree[node][0] = right; <= 틀린 코드
      // 따라서 Swap() 처럼 3단계로 나눠서 진행해야 할 것 같다.
      // 1. peek로 A 위치에 무슨 값이 있는지 확인 -> a 2. update 함수로 B 위치에 DVD 값을 a로 변경 후 return b; 3. update 함수로 A 위치 값 b로 변경
      int temp = tree[node][0];
      tree[node][0] = val;
      tree[node][1] = (tree[node][0] == start) ? 0 : 1;
      return temp;
    }
    int lVal = update(tree, node * 2, start, (start + end) / 2, idx, val);
    int rVal = update(tree, node * 2 + 1, (start + end) / 2  + 1, end, idx, val);
    int temp = lVal != 0 ? lVal : rVal;
    tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
    tree[node][1] = tree[node * 2][1] + tree[node * 2 + 1][1];
    return temp;
  }

  // left right 고정, strat 와 end가 움직인다고 생각하자.

  // 해당 방식은 false가 어느 것에 대한 false인지 몰라서 구현하기 힘들어 보인다.
  static int query(int[][] tree, int node, int start, int end, int left, int right, int[] original) {
    if (end < left || right < start) return 0;
    if (left <= start && end <= right) {
      return tree[node][0];
    }
    int lSum = query(tree, node * 2, start, (start + end) / 2, left, right, original);
    int rSum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, original);

     int sum = lSum + rSum;

     if (tree[node][1] == 0) return 1;


  }
}
```

**WA(16%)**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int MIN = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int h = (int) Math.ceil(Math.log(N) / Math.log(2));
      int tree_size = 1 << (h + 1);

      // Max, Min
      int[][] tree = new int[tree_size][2];

      initST(tree, 1, 0, N - 1);

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        if (n1 > n2) {
          int temp = n1;
          n1 = n2;
          n2 = temp;
        }
        switch (Q) {
          case 0:
            int val = peek(tree, 1, 0, N - 1, n1);
            val = update(tree, 1, 0, N - 1, n2, val);
            update(tree, 1, 0, N - 1, n1, val);
            break;
          case 1:
            int[] result = query(tree, 1, 0, N - 1, n1, n2);
            if (result[0] == n2 && result[1] == n1) bw.write("YES\n");
            else bw.write("NO\n");
            break;
          default:
            break;
        }
      }
      bw.flush();
    }
    bw.close();
  }

  static void initST(int[][] tree, int node, int start, int end) {
    if (start == end) {
      tree[node][0] = tree[node][1] = start;
    } else {
      initST(tree, node * 2, start, (start + end) / 2);
      initST(tree, node * 2 + 1, (start + end) / 2 + 1, end);

      tree[node][0] = Math.max(tree[node * 2][0], tree[node * 2 + 1][0]);
      tree[node][1] = Math.min(tree[node * 2][1], tree[node * 2 + 1][1]);
    }
  }

  static int peek(int[][] tree, int node, int start, int end, int idx) {
    if (idx < start || end < idx) {
      return -1;
    }
    if (start == end) {
      return tree[node][0];
    }
    int val = peek(tree, node * 2, start, (start + end) / 2, idx);
    if (val != -1) return val;
    val = peek(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx);

    return val;
  }

  static int update(int[][] tree, int node, int start, int end, int idx, int val) {
    if (idx < start || end < idx) {
      return -1;
    }
    if (start == end) {
      int temp = tree[node][0];
      tree[node][0] = val;
      tree[node][1] = val;
      return temp;
    }
    int lVal = update(tree, node * 2, start, (start + end) / 2, idx, val);
    int rVal = update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, val);

    tree[node][0] = Math.max(tree[node * 2][0], tree[node * 2 + 1][0]);
    tree[node][1] = Math.min(tree[node * 2][0], tree[node * 2 + 1][1]);

    if (lVal != -1) return lVal;
    return rVal;
  }

  static int[] query(int[][] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return new int[]{MIN, INF};
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int[] lQuery = query(tree, node * 2, start, (start + end) / 2, left, right);
    int[] rQuery = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);

    int max = Math.max(lQuery[0], rQuery[0]);
    int min = Math.min(lQuery[1], rQuery[1]);

    return new int[]{max, min};
  }
}
```