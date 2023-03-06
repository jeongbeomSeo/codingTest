# 전설

**플래티넘 3**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|3 초	|1024 MB	|3903|	545|	335|	12.733%|

## 문제 

Sogang ICPC Team에는 색상 이름과 닉네임의 순서로 이여서 팀명을 지으면 ICPC 리저널에서 수상할 수 있다는 전설이 있다. 색상 이름들과 닉네임들이 주어질 때, Q개의 팀에 대해 다음 리저널에서 수상할 수 있을지 전설에 기반해 알려주는 프로그램을 작성하자.

## 입력 

첫째 줄에는 색상의 종류 C와 닉네임의 개수 N이 주어진다. (1 ≤ C, N ≤ 4,000)

다음 C개의 줄에는 색상 이름 C개가 한 줄에 하나씩 주어진다. 색상 이름은 중복되지 않는다.

다음 N개의 줄에는 Sogang ICPC Team 구성원들의 닉네임 N개가 한 줄에 하나씩 주어진다. 닉네임도 중복되지 않는다.

다음 줄에는 팀의 개수 Q가 주어진다. (1 ≤ Q ≤ 20,000)

다음 Q개의 줄에는 팀명 Q개가 한 줄에 하나씩 주어진다. 팀명은 중복되지 않는다.

모든 색상 이름의 길이와 닉네임의 길이는 1,000글자를 넘지 않는다. 모든 팀명은 2,000글자를 넘지 않는다. 모든 문자열은 알파벳 소문자로만 이루어져 있다.

## 출력 

팀명이 입력된 순서대로, 전설에 따라 해당 팀이 다음 리저널에서 수상할 수 있다면 Yes, 아니라면 No를 출력한다.

## 예제 입력 1

```
4 3
red
blue
purple
orange
shift
joker
noon
5
redshift
bluejoker
purplenoon
orangeshift
whiteblue
```

## 예제 출력 1

```
Yes
Yes
Yes
Yes
No
```

## 풀이 과정

1. **Trie 사용**

## 오류 코드 

```java
  static boolean legend(ArrayList<Integer> idxs, String team, int teamLength, Tree tree) {
    for(int i = 0; i < idxs.size(); i++){
      int pt = idxs.get(i);
      idxs = tree.find(team, pt);
      if(idxs.get(idxs.size() - 1) == teamLength) {
        return true;
      }
    }
    return false;
  }
```

```java
  static boolean legend(ArrayList<Integer> idxs, String team, int teamLength, Tree tree) {
    ArrayList<Integer> lastIdxs = new ArrayList<>();
    for(int i = 0; i < idxs.size(); i++){
      int pt = idxs.get(i);
      lastIdxs = tree.find(team, pt);
      if(lastIdxs.get(lastIdxs.size() - 1) == teamLength) {
        return true;
      }
    }
    return false;
  }
```

위의 코드에서 아래 코드로 수정하였다.

이유는 idxs 배열은 처음에 앞의 단어를 찾을 때 저장해놨던 List이고, 뒤의 단어를 찾아서 인덱스를 저장하는 배열은 새롭게 만들어야 된다고 판단해서 바꿨다.

하지만 두 코드다 **IndexOutofBounds**를 출력하는데 이유는 다음과 같다.

```lastIdxs.get(lastIdxs.size() - 1)``` 마지막 요소를 가져오는 것에서 이와 같이 코드를 짤 때 주의할 점은 **size가 0인 경우 get함수 안의 매개 변수에는 -1이 들어가서 오류가 나온다**는 점이다.

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

abstract class Tree {

  Node root;
  void insert(String txt) {
    Node curNode = this.root;

    for(int i = 0; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
    }

    curNode.isTerminal = true;
  }

  ArrayList<Integer> find(String txt, int idx) {
    Node curNode = this.root;
    ArrayList<Integer> idxs = new ArrayList<>();
    int i;

    for(i = idx; i < txt.length(); i++) {
      curNode = curNode.childNode.getOrDefault(txt.charAt(i), null);
      if(curNode == null) {
        return idxs;
      }
      // 두 문자열이 포함 관계일 경우 문제 생김 ex) red redwine
      // 추가로 생각해야 될 것은 닉네임에 접미사 단어가 존재할 경우 ex) 색깔: red redwine 닉네임: wine
      if(curNode.isTerminal) {
        idxs.add(i + 1);
      };
    }

    return idxs;
  }

}

class Color extends Tree {

  Color() {
    if(root == null) root = new Node();
  }
}

class NickName extends Tree {

  NickName() {
    if(root == null) root = new Node();
  }
}

class Node {
  Map<Character, Node> childNode;
  boolean isTerminal;

  Node() {
    childNode = new HashMap<>();
    isTerminal = false;
  }

}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Tree colorTree = new Color();
    Tree nickNameTree = new NickName();

    for(int i = 0; i < C; i++) {
      colorTree.insert(br.readLine());
    }
    for(int i = 0; i < N; i++) {
      nickNameTree.insert(br.readLine());
    }

    int teamNames = Integer.parseInt(br.readLine());

    for(int teamName = 0; teamName < teamNames; teamName++) {
      String team = br.readLine();
      int teamLength = team.length();
      boolean legend = false;
      ArrayList<Integer> idxs = new ArrayList<>();

      // Color Tree 부터 확인
      idxs = colorTree.find(team, 0);

      // 성공시
      if(!idxs.isEmpty()) {
        legend = legend(idxs, team, teamLength, nickNameTree);
      }
      // 실패시
      else {
        idxs = nickNameTree.find(team, 0);
        // 성공시 colorTree 확인
        if(!idxs.isEmpty()) {
          legend = legend(idxs, team, teamLength, colorTree);
        }
      }
      if(legend) System.out.println("Yes");
      else System.out.println("No");
    }
  }
  static boolean legend(ArrayList<Integer> idxs, String team, int teamLength, Tree tree) {
    for(int i = 0; i < idxs.size(); i++){
      ArrayList<Integer> lastIdxs = new ArrayList<>();
      int pt = idxs.get(i);
      lastIdxs = tree.find(team, pt);
      int size = lastIdxs.size();
      if(size != 0)
        if(lastIdxs.get(size - 1) == teamLength) {
          return true;
        }
    }
    return false;
  }
}

```

위의 풀이는 색상과 닉네임의 순서가 정해져 있지 않다고 생각해서 푼 잘못된 풀이다.

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Tree {
  Node colorRoot;
  private Node nickNameRoot;

  Tree() {
    if(colorRoot == null) colorRoot = new Node();
    if(nickNameRoot == null) nickNameRoot = new Node();
  }
  void insert(String txt, int type) {
    // type이 0이면 ColorRoot, 아니면 nickNameRoot
    Node curNode = (type == 0) ? colorRoot : nickNameRoot;

    for(int i = 0; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
    }

    curNode.isTerminal = true;
  }

  boolean find(Node curNode, String txt, int idx, int txtLength, int type) {

    for(int i = idx; i < txtLength; i++) {
      curNode = curNode.childNode.getOrDefault(txt.charAt(i), null);

      if(curNode == null) return false;
      // 색상 단어를 찾았을 경우
      else if(curNode.isTerminal) {
        if(type == 1 && i == txtLength - 1) return true;
        curNode = this.nickNameRoot;
        // 닉네임 단어를 찾았을 경우
        if(find(curNode, txt, i + 1,txtLength, 1)){
          return true;
        }
      }
    }
    return false;
  }
}

class Node {
  Map<Character, Node> childNode;
  boolean isTerminal;

  Node() {
    childNode = new HashMap<>();
    isTerminal = false;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Tree tree = new Tree();
    for(int i = 0; i < C; i++)
      tree.insert(br.readLine(), 0);

    for(int i = 0; i < N; i++)
      tree.insert(br.readLine(), 1);

    int teams = Integer.parseInt(br.readLine());

    for(int i = 0; i < teams; i++) {
      String team = br.readLine();
      if(tree.find(tree.colorRoot, team, 0, team.length(), 0))
        System.out.println("Yes");
      else
        System.out.println("No");
    }

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

class Tree {
  private Node colorRoot;
  private Node nickNameRoot;

  Tree() {
    if(colorRoot == null) colorRoot = new Node();
    if(nickNameRoot == null) nickNameRoot = new Node();
  }

  void insert(String txt, int type) {
    Node curNode = type == 0 ? colorRoot : nickNameRoot;

    // 정방향
    if(type == 0) {
      for(int i = 0; i < txt.length(); i++) {
        curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
      }
    }
    // 역방향
    else {
      for(int i = txt.length() - 1; i >= 0; i--) {
        curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
      }
    }

    curNode.isTerminal = true;
  }

  boolean find(String txt) {
    Node colorNode = colorRoot;
    Node nickNameNode;
    int i, j;

    for(i = 0; i < txt.length(); i++) {
      colorNode = colorNode.childNode.getOrDefault(txt.charAt(i), null);
      if(colorNode == null) return false;
      else if(colorNode.isTerminal) {
        nickNameNode = nickNameRoot;
        for(j = txt.length() - 1; j > i; j--) {
          nickNameNode = nickNameNode.childNode.getOrDefault(txt.charAt(j), null);
          if(nickNameNode == null) break;
        }
        if(nickNameNode != null && nickNameNode.isTerminal) return true;
      }
    }
  return false;
  }
}

class Node {
  Map<Character, Node> childNode;
  boolean isTerminal;

  Node() {
    childNode = new HashMap<>();
    isTerminal = false;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Tree tree = new Tree();
    for(int i = 0; i < C; i++)
      tree.insert(br.readLine(), 0);
    for(int i = 0; i < N; i++)
      tree.insert(br.readLine(), 1);

    int teamNum = Integer.parseInt(br.readLine());

    for(int i = 0; i < teamNum; i++) {
      if(tree.find(br.readLine()))
        System.out.println("Yes");
      else
        System.out.println("No");
    }
  }
}
```

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Trie {

  private Node root;

  Trie() {
    if(root == null) root = new Node();
  }

  private int charToInt(char c) {
    return c - 'a';
  }

  void insert(String txt) {
    Node curNode = this.root;

    for(int i = 0; i < txt.length(); i++) {
      int num = charToInt(txt.charAt(i));
      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNum++;
      }
      curNode = curNode.childNode[num];
    }
    curNode.isTerminal = true;
  }

  ArrayList<Integer> findIndex(String txt) {
    ArrayList<Integer> idxs = new ArrayList<>();
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      int idx = charToInt(txt.charAt(i));

      curNode = curNode.childNode[idx];
      if(curNode == null) break;
      else if(curNode.isTerminal) idxs.add(i + 1);
    }
    return idxs;
  }
}

class Node {
  Node[] childNode;
  boolean isTerminal;
  int childNum;

  Node() {
    childNode = new Node[26];
    isTerminal = false;
    childNum = 0;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Trie trie = new Trie();
    for(int i = 0; i < C; i++)
      trie.insert(br.readLine());
    Set<String> nickNames = new HashSet<>();
    for(int i = 0; i < N; i++)
      nickNames.add(br.readLine());

    int teamNum = Integer.parseInt(br.readLine());

    for(int team = 0; team < teamNum; team++) {
      String teamName = br.readLine();
      ArrayList<Integer> colorIndex = trie.findIndex(teamName);
      boolean success = false;
      for(int i = 0; i < colorIndex.size(); i++) {
        int idx = colorIndex.get(i);
        if(idx == teamName.length() - 1) continue;
        String subString = teamName.substring(idx);
        if(nickNames.contains(subString)) {
          success = true;
          break;
        }
      }
      if(success) System.out.println("Yes");
      else System.out.println("No");
    }
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Trie {

  private Node root;

  Trie() {
    if(root == null) root = new Node();
  }

  private int charToInt(char c) {
    return c - 'a';
  }

  void insert(String txt) {
    Node curNode = this.root;

    for(int i = 0; i < txt.length(); i++) {
      int num = charToInt(txt.charAt(i));
      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNum++;
      }
      curNode = curNode.childNode[num];
    }
    curNode.isTerminal = true;
  }

  ArrayList<Integer> findIndex(String txt) {
    ArrayList<Integer> idxs = new ArrayList<>();
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      int idx = charToInt(txt.charAt(i));

      curNode = curNode.childNode[idx];
      if(curNode == null) break;
      else if(curNode.isTerminal) idxs.add(i + 1);
    }
    return idxs;
  }
}

class Node {
  Node[] childNode;
  boolean isTerminal;
  int childNum;

  Node() {
    childNode = new Node[26];
    isTerminal = false;
    childNum = 0;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Trie trie = new Trie();
    for(int i = 0; i < C; i++)
      trie.insert(br.readLine());
    Set<String> nickNames = new HashSet<>();
    for(int i = 0; i < N; i++)
      nickNames.add(br.readLine());

    int teamNum = Integer.parseInt(br.readLine());

    for(int team = 0; team < teamNum; team++) {
      String teamName = br.readLine();
      ArrayList<Integer> colorIndex = trie.findIndex(teamName);
      boolean success = false;
      for(int i = 0; i < colorIndex.size(); i++) {
        int idx = colorIndex.get(i);
        if(idx == teamName.length()) continue;
        String subString = teamName.substring(idx);
        if(nickNames.contains(subString)) {
          success = true;
          break;
        }
      }
      if(success) System.out.println("Yes");
      else System.out.println("No");
    }
  }
}
```

현재 풀이같은 경우 다른 사람 풀이를 참고해서 풀었다. 

두 가지 방식 다 트라이를 사용하는데,

1. Color의 경우 **순방향으로 노드에 넣어주고** 닉네임은 **역방향으로 노드에 넣어준다.** 그리고 찾은 문자열 길이를 각각 배열에 넣어주고 **서로의 배열에서 꺼낸 요소의 합 중에 팀명 문자열 길이랑 같은 조합이 있는지 확인**하는 방법
2. Color의 경우에만 트라이로 검사를 해주고 나머지 **닉네임의 경우는 Set Interface에 넣어준다.** Color만 트라이로 검사해서 index를 찾아주고 찾은 index를 활용해서 **subString()** 을 활용해 **문자열을 짤라 Set에 있는지 확인**하는 방법 

1번의 경우 역방향을 넣어줌으로써 불필요한 반복을 없애준다.

기본적인 방법으로 둘다 순방향으로 검사하고 Color 문자를 찾았을 때마다 닉네임을 순방향으로 검사해주는 것은 불필요한 반복이 계속된다.

예를 들어, 색깔 단어가 'a', 'ab', 'aba', 'abac', ... 이와 같이 되어있다면 매순간 노드마다 닉네임을 찾아야 될 것이다. 

하지만, 색깔 Trie에서 찾은 index(길이)를 배열에 넣어놔주고 닉네임의 경우 역방향으로 탐색해서 index(길이)를 넣어놔준다면 

서로의 배열에서 요소를 하나씩 꺼내서 팀명이 만들어 지는 조합(길이가 같아지는 조합)을 찾으면 되기 때문에 **트라이로 탐색하는 것이 각각 한번씩만 이루어질 것이다.**

2번의 경우 Color만 트라이로 검사하고 나머지는 내장 함수인 **subString()과 Set.contains()** 함수를 활용하는 방법이다.

2번 방식의 경우 Color만 Trie로 넣어주기 때문에 일단 삽입 과정이 절반으로 줄고, 탑색 과정도 줄어든다.

하지만, 내장 함수를 실행하는 시간이 존재하기 때문에 확실한 효율은 정확히 모르지만, **정확한건 Color만 Trie로 사용함으로써 Map Interface를 사용하지 않고 Array를 사용할 수 있게 되었다.**

메모리 초과를 신경써서 사용하기 힘들었지만, **사용 메모리가 절반**으로 줄었기 때문이다.


## 참고한 사이트

- [백준 19585 자바 - 전설 (boj 19585 java) - Nahwasa](https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-19585-%EC%9E%90%EB%B0%94-%EC%A0%84%EC%84%A4-boj-19585-java)