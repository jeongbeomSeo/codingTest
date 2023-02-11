# 용량 부족

**플래티넘 3**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	1296	|475|	322|	36.261%|

## 문제 

팀포2 덕후 연수는 팀포2를 다운받던 도중 하드 용량이 부족하다는 것을 알았다. 이때는 침착하게 팀포2를 하지 말거나 하드를 새로 사면 되지만 불가능했고, 결국 하드에서 쓸모없는 파일을 지워야만 한다.

연수는 또한 턱스 덕후여서 리눅스를 사용중이다. 리눅스에서 현재 디렉토리의 특정 파일을 지우려면 "rm 파일명"의 형식을 갖춰 명령하면 된다. 그러나 파일 개수가 너무 많을 경우 일일이 다 칠 수 없기에, 와일드카드 '*'를 사용할 수도 있다. "rm 문자열*" 형식으로 명령하면 현재 디렉토리에서 파일 이름이 "문자열"이거나 "문자열"로 시작하는 모든 파일이 한번에 삭제된다! 그러나 지워서는 안 되는 파일들 또한 존재한다. rm 명령어를 잘못 사용하여 이러한 파일들을 지우지 않도록 조심해야 할 것이다. 때에 따라서 "rm *"도 사용할 수 있긴 하다. 현재 디렉토리의 모든 파일을 지우고 싶을 때만...

모든 파일이 디렉토리 하나에 존재하고 연수가 그 디렉토리에 있을 때, 지우고 싶은 파일들을 모두 지우는 데 필요한 최소 rm 명령 횟수를 구하시오. 단, 사용할 수 있는 와일드카드는 '*' 뿐이며 반드시 제일 끝에만 사용해야 한다. 예를 들면 "rm *.txt"는 사용할 수 없다.

## 입력 

입력은 여러 개의 테스트 케이스로 주어지며, 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 다음과 같은 형식이다.

- 첫째 줄에 지워야 하는 파일의 개수 N<sub>1</sub>이 주어진다. (1 ≤ N<sub>1</sub> ≤ 1000)
- 이어서 N<sub>1</sub>개의 줄에 지워야 하는 파일명이 줄마다 하나씩 주어진다.
- 이어서 지우면 안 되는 파일의 개수 N<sub>2</sub>가 주어진다. (0 ≤ N<sub>2</sub> ≤ 1000)
- 이어서 N<sub<2</sub>개의 줄에 지우면 안 되는 파일명이 줄마다 하나씩 주어진다.

파일 이름은 모두 1글자 이상 20글자 이하이며, 영문 대소문자, 숫자, 점(.)으로만 이루어져 있다. 하나의 테스트 케이스에 등장하는 모든 파일 이름은 서로 다르다.

## 출력 

각 테스트 케이스마다 한 줄에 걸쳐 문제의 정답을 출력한다.

## 예제 입력 1

```
1
11
BAPC.in
BAPC.out
BAPC.tex
filter
filename
filenames
clean
cleanup.IN1
cleanup.IN2
cleanup.out
problem.tex
5
BAPC
files
cleanup
cleanup.IN
cleaning
```

## 예제 출력 1

```
8
```

## 풀이 과정 

1. **Trie 사용**
2. 삭제할 파일명을 전부 넣어준다.
3. Node의 속성에 **notCleanUp** 변수를 만들어 놓고 삭제 하지 말아야 되는 파일을 입력 받으면서 Node에 notCleanUp를 true로 설정해 준다.
4. 다시 한번 삭제할 파일들을 함수에 넣어주면서 다음과 같은 순서로 실행한다.
   1. **CurNode != null이 아닐 경우**
      1. notCleanUp == true일 경우 다음 문자로 넘어간다.
      2. notCleanUp == false일 경우 해당 Node를 null로 만들어 준다.(참고로 부모 노드를 삭제하면 Tree에선 아래로 갈 수단이 없기 때문에 부모 노드만 처리 해주는 것으로 충분하다.)
         1. 필요한 명령어 였으므로 count++
   2. **CurNode == null일 경우**
      1. 이미 **와일드 카드**를 사용해서 삭제된 파일이므로 함수를 종료한다.
5. 만약 curNode.isTerminal == true인 Node까지 도달했다면 두가지 확인
   1. notCleanUp == true => count++만 해주고 함수를 종료
   2. notCleanUp == false => count++를 해주면서 curNode를 null로 만들어 주면서 함수 종료 

> 문제 풀이에선 count++대신 함수가 command 입력 여부를 boolean값으로 return 해주는 형태로 풀이함.

**주의 사항**

```java
  boolean commandCount(String txt) {
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      curNode = curNode.childNode.getOrDefault(txt.charAt(i), null);

      if(curNode == null) return false;

      else if(!curNode.notCleanUp) {
        curNode = null;
        return true;
      }

    }
    if(curNode.isTermial && !curNode.notCleanUp) curNode = null;

    return true;
  }
```

이와 같이 코드를 짜서 작동을 시켰지만, 재대로 결과가 안나와서 디버깅한 결과 curNode = null부분이 생각했던 대로 동작하지 않는다.

생각한 대로라면 curNode가 받고있는 주소지의 Node를 null로 만들줄 알았지만, 단순히 **curNode 변수가 가르키는 것을 null**로 만들 뿐 원래 주소지의 데이터 값은 변경되지 않는다.

지역 변수라도 주소값을 받아서 데이터를 처리하는 것이면 Call By Value로 동작하는 Java라도 값이 변경되는 줄 알았지만 해당 경우는 다른 것 같다.

생각 해본 결과 원인은 이와 같을 것이라고 **추측**된다.

- **주소 값을 받아서 해당 주소 값을 매개로하는 다른 데이터를 변경 하는 것은 가능**하다.
- 해당 경우, **curNode**는 함수 안에서 새로 생성한 **지역 변수**고 this.root의 주소 값을 받고 있다. 
- 따라서 curNode 자체를 Null로 만드는 행위는 지역 변수인 curNode를 건드리는 행위라서 본래 데이터의 값에는 영향이 없는 것이라고 생각한다. 

> **해결 방식**
> 
> Class Node 속성에 delete 추가 
## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Tree {
  private Node root;

  Tree() {
    if(root == null) root = new Node();
  }

  void insert(String txt, boolean notCleanUp) {
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
      if(notCleanUp) curNode.notCleanUp = true;
    }
    curNode.isTermial = true;
  }

  boolean commandCount(String txt) {
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      curNode = curNode.childNode.getOrDefault(txt.charAt(i), null);

      if(curNode.delete == true) return false;

      else if(!curNode.notCleanUp) {
        curNode.delete = true;
        return true;
      }

    }
    if(curNode.isTermial && !curNode.notCleanUp) curNode.delete = true;

    return true;
  }
}
class Node {
  Map<Character, Node> childNode;
  boolean isTermial;
  boolean notCleanUp;
  boolean delete;

  Node() {
    childNode = new HashMap<>();
    isTermial = false;
    notCleanUp = false;
    delete = false;
  }

}
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int testNum = Integer.parseInt(br.readLine());

    for(int tc = 0; tc < testNum; tc++) {
      int deleteFileNum = Integer.parseInt(br.readLine());

      Tree tree = new Tree();
      String[] deleteFile = new String[deleteFileNum];
      for(int i = 0; i < deleteFileNum; i++) {
        deleteFile[i] = br.readLine();
        tree.insert(deleteFile[i], false);
      }
      int notDeleteFineNum = Integer.parseInt(br.readLine());

      for(int i = 0; i < notDeleteFineNum; i++) {
        tree.insert(br.readLine(), true);
      }

      int count = 0;
      for(int i = 0; i < deleteFileNum; i++) {
        if(tree.commandCount(deleteFile[i])) count++;
      }

      System.out.println(count);
    }
  }
}

```

**반례**

**입력**

```
1
2
A
B
0
```

**출력**

```
1
```