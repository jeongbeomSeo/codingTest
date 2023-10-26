# 소문난 칠공주

**골드 3**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|256 MB|	9587|	4976|	3267	|50.432%|

## 문제 

총 25명의 여학생들로 이루어진 여학생반은 5×5의 정사각형 격자 형태로 자리가 배치되었고, 얼마 지나지 않아 이다솜과 임도연이라는 두 학생이 두각을 나타내며 다른 학생들을 휘어잡기 시작했다. 곧 모든 여학생이 ‘이다솜파’와 ‘임도연파’의 두 파로 갈라지게 되었으며, 얼마 지나지 않아 ‘임도연파’가 세력을 확장시키며 ‘이다솜파’를 위협하기 시작했다.

위기의식을 느낀 ‘이다솜파’의 학생들은 과감히 현재의 체제를 포기하고, ‘소문난 칠공주’를 결성하는 것이 유일한 생존 수단임을 깨달았다. ‘소문난 칠공주’는 다음과 같은 규칙을 만족해야 한다.

1. 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
2. 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
3. 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
4. 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.

여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.

## 입력 

'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.

## 출력 

첫째 줄에 ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 출력한다.

## 예제 입력 1

```
YYYYY
SYSYS
YYYYY
YSYYS
YYYYY
```

## 예제 출력 1

```
2
```

## 힌트 

가능한 방법은 아래와 같다.

```
.....    .....
SYSYS    SYSYS
....Y    .Y...
....S    .S...
.....    .....
```

힌트에서 두번째 저 모양때문에 DFS를 쓰기 힘들어 보인다.

현재 문제에선 인전한 범위를 7칸으로 두고 있는데, DFS에서 움직이는 횟수를 Count하는 방식은 함수의 매개변수에서 연산 하는 방식을 처리하는 것인데,

이 방식은 재귀 함수로써 되돌아 오는 과정에서 매개 변수로 처리한 연산은 빠져나오고 나서 현재 변수에 적용되지 않습니다. 

하지만 오른쪽 힌트의 경우 DFS로 탐색할 경우 ㅜ모양의 탐색은 r 과 ㅡ 모양으로 탐색하는 것입니다. 

참고: https://code-lab1.tistory.com/218

## 틀린 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int[] ax = {-1, 1, 1, -1};
  static int[] ay = {0, -1, 1, 1};
  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] str = new String[5];

    for (int i = 0 ; i < 5; i++) {
      str[i] = br.readLine();
    }

    Map<Integer, Node> coordinate = new HashMap<>();
    Integer idx = 0;
    // 0 ~ 24
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        coordinate.put(idx++, new Node(j, i, str[i].charAt(j) == 'S' ? true : false));
      }
    }


    int[] arr = new int[7];

    combination(arr, coordinate, 0, 0);

    System.out.println(ans);

  }
  static void combination(int[] arr, Map<Integer, Node> coordinate, int ptr, int size) {

    if (size == 7) {
      if (isSomOk(arr, coordinate)) {
        if (adjOk(arr, coordinate)) ans++;
      }
    }

    else {
      if (ptr < 25) {
        arr[size] = ptr;
        combination(arr, coordinate,ptr + 1, size + 1);

        combination(arr, coordinate,  ptr + 1, size);
      }
    }

  }

  static boolean isSomOk(int[] arr, Map<Integer, Node> coordinate) {

    int somCount = 0;
    for (int i = 0; i < arr.length; i++) {
      Node curNode = coordinate.get(arr[i]);
      if (curNode.isSom) somCount++;
    }
    if (somCount >= 4) return true;
    return false;
  }

  static boolean adjOk(int[] arr, Map<Integer, Node> coordinate) {

    Queue<Node> q = new ArrayDeque<>();
    boolean[] isVisited = new boolean[arr.length];
    Node startNode = coordinate.get(arr[0]);
    q.add(startNode);
    isVisited[0] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        curNode.x += ax[i];
        curNode.y += ay[i];
        if(!curNode.isValidIdx()) continue;

        for (int j = 0; j < arr.length; j++) {
          if (!isVisited[j]) {
            if (curNode.compareTo(coordinate.get(arr[j])) == 0) {
              isVisited[j] = true;
              q.add(curNode);
            }
          }
        }
      }
    }

    for (boolean e : isVisited) {
      if (e == false) return false;
    }
    return true;

  }
}
class Node implements Comparable<Node>{
  int x;
  int y;
  boolean isSom;

  Node(int x, int y) {
    this.x = x;
    this.y = y;
    isSom = false;
  }

  Node(int x, int y, boolean isSom) {
    this(x, y);
    this.isSom = isSom;
  }

  public boolean isValidIdx() {
    if (x < 0 || y < 0 || x > 4 || y > 4) return false;
    return true;
  }

  @Override
  public int compareTo(Node o) {
    if (this.x == o.x  && this.y == o.y) return 0;
    else return -1;
  }
}
```

여기서 잘못된 점은 다음과 같다. 

Map Interface를 사용하면서 값을 해당 위치의 Node를 꺼내서 x와 y에 값을 더해주면서 확인하는 방식을 사용하고 있습니다.

```java
      for (int i = 0; i < 4; i++) {
        curNode.x += ax[i];
        curNode.y += ay[i];
```

여기서 주목할 점은 해당 코드의 4번의 반복 연산이 끝나면 최종 curNode의 좌표는 원래 위치에서 x는 그대로지만 y는 +1만큼 이동합니다.

문제점은 Map Interface에서 꺼내주는 Node는 클래스라서 curNode.x에 연산을 하는 순간 멤버 변수의 값이 변경되는 것입니다. 

따라서, 해당 코드에는 오류가 있는 것입니다.