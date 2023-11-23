# 윷놀이 (Small)

**플래티넘 5**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|5 초 	|512 MB|	44|	14	|12	|36.364%|

## 문제 

윷놀이는 명절에 즐기는 한국의 민속놀이로 반달 모양의 가락(윷)을 던져서 말을 움직여 노는 말판 놀이이다. 두 팀이 각각 말을 가지고 윷을 교대로 던져 결승점에 말을 모두 통과시키는 팀이 이기는 놀이이다. 윷놀이의 룰은 지역마다 다를 수 있으므로 여기서는 아래에 기재된 룰에 의해 진행된다고 가정한다.

- 도(Do): 앞으로 한 칸 움직인다. 
- 개(Gae): 앞으로 두 칸 움직인다. 
- 걸(Gul): 앞으로 세 칸 움직인다. 
- 윷(Yut): 앞으로 네 칸 움직이고, 윷을 한 번 더 던진다. 
- 모(Mo): 앞으로 다섯 칸 움직이고, 윷을 한 번 더 던진다.

윷을 던져 나오는 위의 5가지 경우에 따라서 말을 이동시킨다. 말을 이동시킬 때는 이동 가능한 말 중 임의의 1개를 골라 이동시킨다. 자신의 말이 자신의 다른 말과 같은 위치에 도달했을 때 업기라고 하여 다음 이동부터는 같이 이동한다. 만약 자신의 말이 다른 팀의 말이 있는 위치에 있는 경우 해당 말을 탈락시키고 윷을 다시 던져야 한다. 탈락한 말은 다시 처음부터 출발해야 한다. 단, 윷이나 모로 잡았을 때 두 번 던지는 것이 아니라 한 번 던질 수 있다. 윷을 던져 나온 순서대로 말을 이동시켜야 하므로, 모를 던진 후 걸이 나왔을 때 세 칸을 먼저 이동하고 다섯 칸을 다음에 이동하는 것은 불가능하다.

![](https://onlinejudgeimages.s3.amazonaws.com/problem/12425/images-43.png)

![](https://onlinejudgeimages.s3.amazonaws.com/problem/12425/images-42.png)

말은 출발지인 0번 지점에서부터 출발하여 결승점인 0번 지점을 지나치는 경로로 이동한다. 말은 말판의 외곽을 따라서 움직이며, 빨간 점으로 표시된 부분에 말이 멈췄을 때 빠른 길로 이동한다. 최초의 말은 말판 위에 있지 않으므로 윷을 던져 말판에 올려놓기 전에는 잡을 수 없다. 또한, 결승점을 완전히 지나쳐야 말을 통과시킨 것으로 인정되므로 19번 위치에서 말을 통과시키기 위해서는 두 칸 이상 이동해야 한다. 한 칸을 움직이게 되면 0번 지점에 있게 되고, 다른 말에 의해 탈락될 수 있다. 결승점을 통과한 말은 다시 사용할 수 없으며, 모든 말이 통과하는 순간 그 팀이 승리하게 되며 경기는 중단된다. 마지막에 윷이나 모를 던져서 승리를 했더라도 게임이 중단된 후에는 더이상 던지지 않는다.

용이네 가족은 명절을 맞이하여 A 팀과 B 팀으로 나누어 윷놀이를 하고 있었다. A 팀부터 먼저 시작하기로 했다. 그들은 선의의 경쟁을 펼치고 있었기 때문에, 종이에 어떤 팀이 무슨 어떻게 윷을 던졌는지 여부를(A: 도, B: 개, ...) 순서대로 모두 적어놓았다.

저녁식사 시간이 다 되었고, 게임이 진행중이거나 막 끝난 상황이었다. 그런데 불행히도 강아지 퍼피가 말판을 지나다니면서 흐트려트려 놓아 말판이 제대로 유지되었는지 확신할 수 없게 되었다. (출발하지 않은 말과 결승점을 통과한 말은 영향을 받지 않았다) 게다가 퍼피는 종이에서 각 윷을 던진 팀이 누구인지에 대한 정보도 모두 물어가 버렸다! 기억을 더듬어 말판을 복구하였지만, 이것이 종이에 기록된 던진 윷의 전체 목록과 순서에 맞는 말판 상태인지에 대해 확신이 없다. 이 작업은 너무 복잡하여 용이의 힘으로는 쉽지 않아 여러분에게 도움을 청하기로 했다. 용이의 고민을 해결해주자.

## 입력 

입력의 첫 줄에는 테스트 케이스의 숫자 T가 주어진다.
각 테스트 케이스는 다음과 같이 주어진다.

```
U N A B
윷0 ... 윷N-1
말A0 ... 말AA-1
말B0 ... 말BB-1
```

각 케이스의 첫 줄에는 다음과 같이 정수 4개가 주어진다. U는 한 팀에서 사용가능한 말의 수, N는 던져진 윷의 목록 개수이다. 그리고 A는 판 위에 놓여 있는 A팀 말의 개수이고, B는 판 위에 놓여 있는 B팀 말의 개수이다.
다음 줄에는 공백으로 분리되어 있는 던져진 윷의 목록이 들어온다.
그 다음 두 줄에는 각각 A팀과 B팀의 말의 위치가 공백으로 분리되어 들어온다.

## 제한 

- 1 ≤ T ≤ 50. 
- 0 ≤ A, B ≤ U. 
- 0 ≤ 말<sub>x</sub> ≤ 28. 
- 1 ≤ N ≤ 20. 
- 1 ≤ U ≤ 1.

## 출력 

각 테스트 케이스에 대한 출력은 "Case #x: y" 형태로 이루어져야 한다. x는 1부터 시작되는 케이스 번호이고, y는 검증 결과이다. 만약 주어진 데이터로 만들어질 수 있는 윷놀이 판이라면 "YES"를 그렇지 않다면 "NO"를 출력한다.

```
7
1 5 1 1
Do Gae Gul Do Gae
6
3
1 2 1 1
Gae Gae
2
2
1 2 0 1
Gae Gae

2
1 6 1 0
Do Mo Mo Mo Mo Gae
1

1 5 1 1
Mo Gul Gul Do Gul
27
6
2 5 2 1
Do Gul Do Gae Gae
1 3
5
2 3 2 1
Do Gae Gul
1 3
2
```

## 예제 출력 1

```
Case #1: YES
Case #2: NO
Case #3: YES
Case #4: NO
Case #5: YES
Case #6: NO
Case #7: YES

```

## 코드

**틀린 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    map = initMap();
    int tc = 1;
    while(tc <= T) {
      st = new StringTokenizer(br.readLine());

      int U = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      int[] points = new int[N];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        points[i] = getPoint(st.nextToken());
      }

      int[] aList = new int[U];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < A; i++) {
        aList[i] = Integer.parseInt(st.nextToken());
      }

      int[] bList = new int[U];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < B; i++) {
        bList[i] = Integer.parseInt(st.nextToken());
      }

      String result = "";
      if (simulation(U, N, A, B, points, aList, bList)) result = "YES";
      else result = "NO";
      System.out.println("Case #" + tc + ": " + result);
      tc++;
    }
  }
  static boolean simulation(int U, int N, int A, int B, int[] points, int[] aList, int[] bList) {

    int[] aBuffer = new int[U];
    int[] bBuffer = new int[U];

    return query(U, N, points, aList, bList, aBuffer, bBuffer, 0, A, B);
  }
  static boolean query(int U, int N, int[] points, int[] aList, int[] bList, int[] mainBuffer, int[] subBuffer, int ptr, int A, int B) {

    boolean isEnd = false;
    if (ptr == N) {
      isEnd = checkValidation(aList, bList, mainBuffer, subBuffer, A, B, U);
    }
    else {
      boolean[] isUsingThisTurn = new boolean[U];
      for (int i = 0; i < U; i++) {
        if (isUsingThisTurn[i]) continue;
        int[] mainBufferCopy = bufferCopy(mainBuffer);
        int[] subBufferCopy = bufferCopy(subBuffer);

        boolean isCatch = active(points, isUsingThisTurn, mainBufferCopy, subBufferCopy, ptr, i, U);
        if (isCatch) {
          isEnd = query(U, N, points, aList, bList, mainBufferCopy, subBufferCopy, ptr + 1, A, B);
        }
        else {
          if (points[ptr] <= 3) {
            isEnd = query(U, N, points, aList, bList, subBufferCopy, mainBufferCopy, ptr + 1, A, B);
          }
          else {
            isEnd = query(U, N, points, aList, bList, mainBufferCopy, subBufferCopy, ptr + 1, A, B);
          }
        }

        if (isEnd) break;
      }
    }

    return isEnd;
  }
  static boolean active(int[] points, boolean[] isUsingThisTurn, int[] mainBuffer, int[] subBuffer, int ptr, int i, int U) {
    int point = points[ptr];

    for (int j = i + 1; j < U; j++) {
      if (!isUsingThisTurn[j] && mainBuffer[i] == mainBuffer[j]) {
        mainBuffer[j] += point;
        isUsingThisTurn[j] = true;
      }
    }
    mainBuffer[i] += point;
    isUsingThisTurn[i] = true;

    boolean isCatch = false;
    for (int j = 0; j < U; j++) {
      if (mainBuffer[i] == subBuffer[j]) {
        isCatch = true;
        subBuffer[j] = 0;
      }
    }
    
    return isCatch;
  }
  static int[] bufferCopy (int[] buffer) {
    int[] newBuffer = new int[buffer.length];
    
    for (int i = 0; i < buffer.length; i++) {
      newBuffer[i] = buffer[i];
    }
    
    return newBuffer;
  }
  static boolean checkValidation(int[] aList, int[] bList, int[] aBuffer, int[] bBuffer, int A, int B, int U) {
    
    if (eachCheck(aList, aBuffer, U, A)) {
      return eachCheck(bList, bBuffer, U, B);
    }
    else {
      if (eachCheck(aList, bBuffer, U, A)) {
        return eachCheck(bList, aBuffer, U, B);
      }
    }
    return false;
  }
  static boolean eachCheck(int[] list, int[] buffer, int U, int matchCount) {
    int count = 0;
    boolean[] isCheck = new boolean[U];
    for (int i = 0; i < U; i++) {
      if (list[i] == 0) continue;
      int j;
      for (j = 0; j < U; j++) {
        if (list[i] == buffer[j] && !isCheck[j]) {
          isCheck[j] = true;
          count++;
          break;
        }
      }
      if (j == U) break;
    }
    
    return count == matchCount;
  }
  static int[] initMap() {
    int[] map = new int[29];

    for (int i = 0; i < 19; i++) {
      map[i] = i + 1;
    }
    map[19] = -1;

    for (int i = 20; i < 24; i++) {
      map[i] = i + 1;
    }
    map[24] = 15;

    for (int i = 25; i < 28; i++) {
      map[i] = i + 1;
    }
    map[28] = -1;

    return map;
  }
  static int getPoint(String yut) {
    if (yut.equals("Do")) return 1;
    else if (yut.equals("Gae")) return 2;
    else if (yut.equals("Gul")) return 3;
    else if (yut.equals("Yut")) return 4;
    else return 5;
  }
}
```

해당 코드는 비교 과정이 잘못 되어 있다. 

이와 같이 코드를 짰을 경우 eachCheck()는 matchCount가 0일때 재대로 동작하지 않습니다.

또한 list[i] == 0일 때 그냥 넘겨 버리면 말의 위치가 원점이 아닌 경우에도 나머지 말의 위치가 같아서 true를 출력하는 문제가 발생합니다.

어차피 list배열을 초기화 할 때 A, B 변수의 크기를 활용하였고, list에는 초기화 하지 않은 Default 값인 0이 담겨 있을 것입니다. 

따라서, 이와 같이 하는 것이 더 좋습니다.

```java
static boolean eachCheck(int[] list, int[] buffer, int U) {

  boolean[] isCheck = new boolean[U];
  boolean isFail = false;
  for (int i = 0; i < U; i++) {
    int j;
    for (j = 0; j < U; j++) {
      if (list[i] == buffer[j] && !isCheck[j]) {
        isCheck[j] = true;
        break;
      }
    }
    if (j == U) {
      isFail = true;
      break;
    }
  }
  
  return !isFail;
}
```

이 부분을 수정한다고 해도 이 코드는 잘못된 코드입니다.

일단 기본적으로 윷놀이에서 말이 이동할 때는 포인트를 가지고 바로 이동하는 방식으로 구현하면 안됩니다.

만들어 놓은 Map을 활용하지 않았고 말이 완주를 한 경우를 고려하지 않았다는 부분에서 문제가 발생합니다. 

또한 말이 한바퀴 돌아서 0번인 지점에 도착을 했을 때 처리 또한 안되어 있습니다.

