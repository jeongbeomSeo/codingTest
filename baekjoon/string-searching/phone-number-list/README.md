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

문제는 다음과 같다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
  private static Node root;
  private Node[] childNode;
  private int childNum;
  private boolean isTerminal;
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
        curNode.childNum++;
      }

      curNode = curNode.childNode[num];

      if(curNode.isTerminal || (i == nums.length() - 1 && curNode.childNum != 0)) return false;
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

    /*
      for(int i = 0; i < N; i++) {
        이와 같이 하면 입력 중간에 break가 걸려버리면 백준에서 입력해둔 예제가 문제가 될 수 있다.
        심지어 해당 문제는 Test Case가 있는 문제라서 오류가 나올 가능성이 있다.
        if(!node.insertAndCheck(br.readLine())) {
          consistency = false;
          break;
        }
        numbers[i] = br.readLine();
      }
    */

      for(int i = 0; i < N; i++) {
        if(!node.insertAndCheck(br.readLine())) consistency = false;
      }
      if(consistency) System.out.println("YES");
      else System.out.println("NO");
    }
  }
}

```

여기서 root 를 static으로 선언해 놓았는데, 이것을 안 썼을 때 이와 같이 했을 때 틀렸다고 나왔다.

> 
> 여기서 **기억할 점**은
> 
> 1. 함부로 static을 사용하지 말자.
> 2. 여러 인스턴스 사이에서 유일한 생성자를 만들고 싶을 때 위와 같이 인스턴스 변수를 만들어 놓고 생성자를 생성할 때 null인지 아닌지를 체크해서 생성해준다.
> 3. Test Case를 받는 문제에서 입력 받을 때 ```break```와 같은 것을 사용할 때는 주의하자.  
> 4. **접두어** 혹은 **접미어**가 나왔을 때는 ``startsWith(), endsWith()`` 함수를 떠올려 주자.
>   1. 다만 해당 풀이는 시간 초과가 나왔다. 
>   2. 해당 풀이를 성공시키려면 정렬을 사용했을 때 두 단어가 접두어 관계를 가지면 서로 바로 옆에 정렬 된다는 것을 이용해야한다. -> 다만 이 방법을 사용해도 Trie Algorithm 보단 느리다.

## 나의 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
  private Node root;
  private Node[] childNode;
  private int childNum;
  private boolean isTerminal;
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
        curNode.childNum++;
      }

      curNode = curNode.childNode[num];

      if(curNode.isTerminal || (i == nums.length() - 1 && curNode.childNum != 0)) return false;
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

    /*
      for(int i = 0; i < N; i++) {
        이와 같이 하면 입력 중간에 break가 걸려버리면 백준에서 입력해둔 예제가 문제가 될 수 있다.
        심지어 해당 문제는 Test Case가 있는 문제라서 오류가 나올 가능성이 있다. => 실제로 실행 결과 오류 나옴
        if(!node.insertAndCheck(br.readLine())) {
          consistency = false;
          break;
        }
        numbers[i] = br.readLine();
      }
    */

      for(int i = 0; i < N; i++) {
        if(!node.insertAndCheck(br.readLine())) consistency = false;
      }
      if(consistency) System.out.println("YES");
      else System.out.println("NO");
    }
  }
}
```

```java
// startsWith() 사용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int caseNum = Integer.parseInt(br.readLine());

    for(int tc = 0; tc < caseNum; tc++) {
      int n = Integer.parseInt(br.readLine());

      String[] txts = new String[n];
      boolean prefix = false;
      for(int i = 0; i < txts.length; i++) {
        txts[i] = br.readLine();
      }
      Arrays.sort(txts);
      for(int i = 0; i < txts.length - 1; i++) {
        if(txts[i + 1].startsWith(txts[i])) {
          prefix = true;
          break;
        }
      }
      if(prefix) System.out.println("NO");
      else System.out.println("YES");
    }

  }
}
```