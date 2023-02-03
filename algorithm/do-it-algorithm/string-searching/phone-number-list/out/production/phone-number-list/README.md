# 전화번호 목록

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	256 MB|	36180|	11636	|7099|	29.990%|

## 문제 

전화번호 목록이 주어진다. 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.

전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.

예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자

- 긴급전화: 911
- 상근: 97 625 999
- 선영: 91 12 54 26

이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다. 전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다. 따라서, 이 목록은 일관성이 없는 목록이다. 

## 입력 

첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50) 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000) 다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다. 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.

## 출력 

각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.

## 예제 입력 1

```
2
3
911
97625999
91125426
5
113
12340
123440
12345
98346
```

## 예제 출력 1

```
NO
YES
```

## 오류 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
  private static Node root;
  private Node[] childNode;
  private int childNum;
  private boolean isTerminal;
  int val;
  Node() {
    if(root == null) root = this;
    childNode = new Node[10];
    childNum = 0;
    isTerminal = false;
  }

  boolean insertAndCheck(String nums) {
    Node curNode = this.root;

    for(int i = 0; i < nums.length(); i++) {
      int num = nums.charAt(i) - '0';

      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNode[num].val = num;
        curNode.childNum++;
      }
      else
        if(i == nums.length() - 1 || curNode.isTerminal) return false;

      curNode = curNode.childNode[num];
    }
    curNode.isTerminal = true;
    return true;
  }
}
public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int caseNum = Integer.parseInt(br.readLine());

    for(int tc = 0; tc < caseNum; tc++) {
      Node node = new Node();
      int N = Integer.parseInt(br.readLine());
      boolean consistency = true;

      for(int i = 0; i < N; i++) {
        if(!node.insertAndCheck(br.readLine())) {
          consistency = false;
          break;
        }
      }
      if(consistency) System.out.println("YES");
      else System.out.println("NO");

    }
  }
}
```

해당 코드로 실행하면 예제 1 의 Test Case 1에서 Yes가 나온다. 

그 이유는 순서의 문제다.

911을 받아서 Node를 생성할 때를 살펴보면 마지막 1을 넣어주고 childNode를 생성해주고 해당 노드에 ```isTerminal = true```처리를 해준다.(자식 노드가 아니다.)

하지만 91125426을 처리할 때를 보면 911까지 가고 나서 isTerminal은 true일 것이다. 하지만, ```childNode == null```이 아니다. 따라서 순서를 다시 처리해줘야 한다.


```java
  boolean insertAndCheck(String nums) {
    Node curNode = this.root;

    for(int i = 0; i < nums.length(); i++) {
      int num = nums.charAt(i) - '0';

      if(curNode.isTerminal || (i == nums.length() - 1 && curNode.childNum != 0)) return false;

      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNode[num].val = num;
        curNode.childNum++;
      }

      curNode = curNode.childNode[num];
    }
    curNode.isTerminal = true;
    return true;
  }
```

해당 코드에는 문제점이 있다.

```
      if(curNode.isTerminal || (i == nums.length() - 1 && curNode.childNum != 0)) return false;
```

해당 코드를 살펴보자면, Test Case 2에서 12345를 넣어주는 상황에서 마지막 i = 4까지 도달 했다고 해보자.

여기서 curNode는 val이 4이고 아직 childNum으로 5를 생성해준 상태가 아니다. 즉, curNode는 4인 상황이지만 i == nums.length() -1 을 만족하는 상황이다.

그래서 curNode.childNum != 0을 만족하고 오류가 나오는 것이다. 

이것을 해결해도 틀린 코드긴 하다. 

