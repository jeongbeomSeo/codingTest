# 문자열 집합
**실버 3**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초 (하단 참고)|	1536 MB|	26596|	14342	|10779	|53.753%|

## 문제 

총 N개의 문자열로 이루어진 집합 S가 주어진다.

입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.

다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.

다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.

입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.

## 출력 

첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.

## 예제 입력 1

```
5 11
baekjoononlinejudge
startlink
codeplus
sundaycoding
codingsh
baekjoon
codeplus
codeminus
startlink
starlink
sundaycoding
codingsh
codinghs
sondaycoding
startrink
icerink
```

## 예제 출력 1

```
4
```

## 시간 제한 

- Java 8: 6 초
- Java 8 (OpenJDK): 6 초
- Java 11: 6 초
- Kotlin (JVM): 6 초

## 틀린 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  static Node rootNode = new Node();
  static class Node {
    Map<Character, Node> childNode = new HashMap<>();
    boolean isTerminal = false;
  }

  static void insert(String str) {
    Node node = rootNode;

    for(int i = 0 ; i < str.length(); i++) {
      node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
    }
    node.isTerminal = true;
  }

  static boolean find(String str) {
    Node node = rootNode;

    for(int i = 0; i < str.length(); i++) {
      node = node.childNode.getOrDefault(str.charAt(i), null);
      if(node == null) return false;
    }
    return node.isTerminal;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    for(int i = 0; i < N; i++)
      insert(br.readLine());

    int findCount = 0;
    for(int i = 0; i < M; i++)
      if (find(br.readLine())) findCount++;

    System.out.println(findCount);
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
class Node {

  private static Node root;
  private Node[] childNode;
  private boolean isTerminal;
  private int childNum;
  private char val;
  Node() {
    if(root == null) {
      root = this;
    }
    childNode = new Node['z' + 1];
    isTerminal = false;
    childNum = 0;
  }

  private int charToInt(char c) {
    return c - 'a';
  }

  void insert(String str) {
    Node curNode = this.root;

    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int idx = charToInt(c);

      if(curNode.childNode[idx] == null) {
        curNode.childNode[idx] = new Node();
        curNode.childNode[idx].val = c;
        curNode.childNum++;
      }
      curNode = curNode.childNode[idx];
    }
    curNode.isTerminal = true;
  }

  boolean find(String str) {
    Node curNode = this.root;

    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int idx = charToInt(c);

      if(curNode.childNode[idx] == null) return false;
      curNode = curNode.childNode[idx];
    }
    return curNode.isTerminal;
  }
}
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Node node = new Node();
    for(int i = 0; i < N; i++) {
      node.insert(br.readLine());
    }

    int findedNum = 0;
    for(int i = 0; i < M; i++) {
      if(node.find(br.readLine())) findedNum++;
    }
    System.out.println(findedNum);
  }
}
```