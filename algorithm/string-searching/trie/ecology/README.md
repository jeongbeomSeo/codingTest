# 생태학

**실버 2**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	256 MB|	12373|	4686|	3325|	36.323%|

## 문제 

생태학에서 나무의 분포도를 측정하는 것은 중요하다. 그러므로 당신은 미국 전역의 나무들이 주어졌을 때, 각 종이 전체에서 몇 %를 차지하는지 구하는 프로그램을 만들어야 한다.

## 입력 

프로그램은 여러 줄로 이루어져 있으며, 한 줄에 하나의 나무 종 이름이 주어진다. 어떤 종 이름도 30글자를 넘지 않으며, 입력에는 최대 10,000개의 종이 주어지고 최대 1,000,000그루의 나무가 주어진다.

## 출력 

주어진 각 종의 이름을 사전순으로 출력하고, 그 종이 차지하는 비율을 백분율로 소수점 4째자리까지 반올림해 함께 출력한다.

## 예제 입력 1 

```
Red Alder
Ash
Aspen
Basswood
Ash
Beech
Yellow Birch
Ash
Cherry
Cottonwood
Ash
Cypress
Red Elm
Gum
Hackberry
White Oak
Hickory
Pecan
Hard Maple
White Oak
Soft Maple
Red Oak
Red Oak
White Oak
Poplan
Sassafras
Sycamore
Black Walnut
Willow
```

## 예제 출력 1 

```
Ash 13.7931
Aspen 3.4483
Basswood 3.4483
Beech 3.4483
Black Walnut 3.4483
Cherry 3.4483
Cottonwood 3.4483
Cypress 3.4483
Gum 3.4483
Hackberry 3.4483
Hard Maple 3.4483
Hickory 3.4483
Pecan 3.4483
Poplan 3.4483
Red Alder 3.4483
Red Elm 3.4483
Red Oak 6.8966
Sassafras 3.4483
Soft Maple 3.4483
Sycamore 3.4483
White Oak 10.3448
Willow 3.4483
Yellow Birch 3.4483
```

## 나의 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
  static int totalNumber = 0;

  static Node rootNode = new Node();
  static class Node {
    Map<Character, Node> childNode = new TreeMap<>();
    boolean isTerminal;
    int count = 0;
    String value;
  }

  static void insertAndFind(String str) {
    Node curNode = rootNode;

    for(int i = 0; i < str.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
    }

    if(curNode.isTerminal) {
      curNode.count++;
      curNode.value = str;
    }
    else {
      curNode.isTerminal = true;
      curNode.count++;
    }
  }

  static public void printAll() {
    printAll(rootNode, ' ',new StringBuilder());
  }

  static private void printAll(Node curNode, char c, StringBuilder sb) {
    StringBuilder builder = new StringBuilder(sb);

    if(!curNode.equals(rootNode)) {
      builder.append(c);
    }

    if(curNode.isTerminal) {
      System.out.printf("%s %.4f\n", builder, ((double)curNode.count/(double)totalNumber) * 100);
    }

    curNode.childNode.forEach((key, node) -> {
      printAll(node, key, builder);
    });
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = "";
    while ((input = br.readLine()) != null && input.length() != 0) {
      totalNumber++;
      insertAndFind(input);
    }
    printAll();

  }
}
```

## 참고한 사이트 

- [[BOJ 백준] 4358번 : 생태학(Python, 파이썬)](https://paris-in-the-rain.tistory.com/116)
- [[Q&A] EOF를 어떻게 입력하나요? : 네이버 블로그](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tipsware&logNo=221315155895)
- [[C언어] C언어에서 EOF란? (C언어 파일 종료 코드) :: 안산드레아스](https://ansan-survivor.tistory.com/1301)
- [EOF의 사용 방법을 알아보자 (JAVA)](https://steady-coding.tistory.com/227)
- [[JAVA]Map이란? (HashMap, Hashtable, TreeMap)](https://devlogofchris.tistory.com/41) 
- [[Java]HashMap 키(Key) 정렬 방법](https://developer-talk.tistory.com/395)
- [[Java] 자바 소수점 원하는 자리수 만큼 출력 - 기역니은디귿](https://bullie.tistory.com/7?category=1009480)