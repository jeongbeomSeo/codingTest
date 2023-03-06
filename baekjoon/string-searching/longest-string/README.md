# 가장 긴 문자열

**플래티넘 3**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	256 MB|	6685|	1704|	968	|29.840%|

## 문제

상근이는 꿈에서 길이가 L인 문자열을 외웠다.

꿈에서 깬 상근이는 이 문자열을 종이에 적었다. 종이를 적던 중에 어떤 문자열은 두 번 이상 등장하는 것 같은 느낌을 받았다.

문자열이 주어졌을 때, 두 번 이상 등장한 부분 문자열 중 가장 길이가 긴 것을 찾는 프로그램을 작성하시오. (부분문자열은 겹쳐서 등장할 수도 있다)

## 입력 

첫째 줄에 L이 주어진다. (1 ≤ L ≤ 200,000) 다음 줄에는 길이가 L이면서 알파벳 소문자로 이루어진 문자열이 주어진다.

## 출력 

첫째 줄에 두 번 이상 등장하는 부분 문자열 중 길이가 가장 긴 것의 길이를 출력한다. 만약 그러한 문자열이 없을 때는 0을 출력한다.

## 예제 입력 1

```
11
sabcabcfabc
```

## 예제 출력 1

```
3
```

## 예제 입력 2

```
18
trutrutiktiktappop
```

## 예제 출력 2

```
4
```

## 예제 입력 3

```
6
abcdef
```

## 예제 출력 3

```
0
```

## 풀이 과정

1. Rabin Karp Algorithm
2. size를 txtLen(주어진 문자열 길이) - 1 ~ 2 까지 줄여가면서 함수를 반복한다.
3. 주어진 문자열에서 index: 0 에서 index: size - 1 까지 해시 값 합을 구한 후 배열에 넣어주고 그 다음 index : 1부터 시작하고 하면서 txtLen - size까지 반복한다.
4. 그 후 배열에서 같은 값이 존재하면 같은 값을 가진 두개의 index를 뽑아 내어 같은 문자열인지 확인

**효율성 높이기**

1. 두 번 이상 등장하는 부분 문자열 중 길이가 가장 긴 것의 길이를 찾는 것이기 때문에 size가 2부터 시작하면 결국 size가 txtLen - 1까지 무조건 다 검사해야한다. 따라서, 반대로 txtLen - 1 부터 2까지 확인 하다가 true를 출력하면 거기서 종료하는 방식을 이용
2. 해시 값을 구하는 과정과 해시 값을 비교하는 과정이 따로 진행되어 불필요한 반복이 나온다.
   1. 해시 값을 구한 동시에 단순히 배열에 삽입하는 것이 아닌 비교 과정을 통해 넣어준다. 
   2. 값이 같을 때 같은 문자열인지 확인 
   3. 다르다면 그 다음 해시값 구하기
> 이러한 방식에 가장 적합한 것이 **이진 트리**라고 생각

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static boolean rkMatch(String txt, int size) {
    int txtLen = txt.length();

    long[] hashTable = new long[txtLen - size + 1];

    for(int i = 0; i <= txtLen - size; i++) {
      long power = 2;
      long hash = 0;
      for(int j = i; j < size; j++) {
        hash += txt.charAt(j) * power;
        power *= 2;
      }
      hashTable[i] = hash;
    }

    for(int i = 0; i < hashTable.length - 1; i++) {
      for(int j = i + 1; j < hashTable.length; j++) {
        if(hashTable[i] == hashTable[j]) {
          int pt = i; int pp = j;
          while (pt < i + size && txt.charAt(pt) == txt.charAt(pp)) {
            pt++;
            pp++;
          }
          if(pt == i + size) return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int L = Integer.parseInt(br.readLine());
    String txt = br.readLine();

    int size = L - 1;
    while (size > 1) {
      int i = 0;
      if(rkMatch(txt, size)) {
        System.out.println(size);
        break;
      }
      size--;
    }
    if(size == 1) System.out.println(0);

  }
}
```

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Tree {
  private Node root;

  Tree() {
    root = null;
  }

  boolean insertAndCompare(Node node) {
    if(root == null) {
      root = node;
      return false;
    }

    Queue<Node> q = new LinkedList<>();
    int length = node.val.length();
    q.add(root);
    while (!q.isEmpty()) {
      Node curNode = q.poll();

      int diff = node.compareTo(curNode);
      if(diff == 0) {
        if(strCompare(node.val, curNode.val, length)) return true;
        else break;
      }
      else if(diff > 0) {
        if(curNode.right == null) {
          curNode.right = node;
          break;
        }
        else {
          q.add(curNode.right);
        }
      }
      else {
        if(curNode.left == null) {
          curNode.left = node;
          break;
        }
        else {
          q.add(curNode.left);
        }
      }
    }
    return false;
  }

  private boolean strCompare(String str, String txt, int length) {
    for(int i = 0; i < length; i++) {
      if(str.charAt(i) != txt.charAt(i)) return false;
    }
    return true;
  }

}

class Node implements Comparable<Node>{
  Node left;
  Node right;
  // Node parent;
  String val;
  int hashValue;

  Node(int hashValue, String val) {
    left = null;
    right = null;
    this.hashValue = hashValue;
    this.val = val;
  }

  @Override
  public int compareTo(Node o) {
    return this.hashValue - o.hashValue;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int L = Integer.parseInt(br.readLine());

    String str = br.readLine();
    int length = str.length() - 1;

    while (length >= 2) {
      if(logestMatch(str, length)) break;
      length--;
    }
    if(length >= 2) System.out.println(length);
    else System.out.println(0);
  }
  static boolean logestMatch(String str, int length) {
    Tree binaryTree = new Tree();
    int hashValue = 0;
    int power = 1;

    for(int i = 0; i <= str.length() - length; i++) {
      if(i == 0) {
        for(int j = 0 ; j < length; j++) {
          hashValue += str.charAt(length - 1 - j) * power;

          if(j != length - 1) {
            power *= 2;
          }
        }
      } else {
        hashValue = 2 * (hashValue - str.charAt(i - 1) * power) + str.charAt(length - 1 + i);
      }

      if(binaryTree.insertAndCompare(new Node(hashValue, str.substring(i, i + length)))){
        return true;
      }
    }
    return false;
  }
}
```


## 참고한 사이트 

- [자바 [JAVA] - Binary Search Tree (이진 탐색 트리) 구현하기](https://st-lab.tistory.com/300)
- [자바 [JAVA] - Binary Search Tree (이진 탐색 트리) 구현하기](https://k3068.tistory.com/30)
- [[Data Structure] Java 이진트리(Binary Tree) 구현](https://math-coding.tistory.com/164)
