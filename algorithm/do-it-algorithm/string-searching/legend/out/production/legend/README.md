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

## 참고한 사이트

- [백준 19585 자바 - 전설 (boj 19585 java) - Nahwasa](https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-19585-%EC%9E%90%EB%B0%94-%EC%A0%84%EC%84%A4-boj-19585-java)