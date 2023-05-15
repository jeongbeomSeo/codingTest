# 친구 네트워크

**골드 2**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|3 초|	256 MB|	40125	|10973	|6741|	26.017%|

## 문제 

민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.

어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.

친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.

## 입력 

첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다. 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.

## 예제 입력 1

```
2
3
Fred Barney
Barney Betty
Betty Wilma
3
Fred Barney
Betty Wilma
Barney Betty
```

## 예제 출력 1

```
2
3
4
2
2
4
```

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());


    while (tc-- > 0) {
      int F = Integer.parseInt(br.readLine());
      Map<String, String> connect = new HashMap<>();
      Map<String, Integer> howMany = new HashMap<>();

      for (int i = 0; i < F; i++) {
        st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        String str2 = st.nextToken();

        System.out.println(union_merge(connect, howMany, str1, str2));
      }
    }
  }
  static String union_find(Map<String, String> connect, Map<String, Integer> howMany, String str) {
    if (!connect.containsKey(str)) {
      connect.put(str, str);
      howMany.put(str, 1);
      return str;
    }
    if (str.equals(connect.get(str))) return str;
    return connect.put(str, union_find(connect, howMany, connect.get(str)));
  }
  static int union_merge(Map<String, String> connect, Map<String, Integer> howMany, String str1, String str2) {
    str1 = union_find(connect, howMany, str1);
    str2 = union_find(connect, howMany, str2);


    if (str1.equals(str2)) return howMany.get(str1);
    else {
      if (compare(str1, str2)) {
        connect.put(str1, str2);
        howMany.put(str2, howMany.get(str1) + howMany.get(str2));
        return howMany.get(str2);
      }
      else {
        connect.put(str2, str1);
        howMany.put(str1, howMany.get(str1) + howMany.get(str2));
        return howMany.get(str1);
      }
    }
  }
  static boolean compare(String str1, String str2) {
    if (str1.length() > str2.length()) {
      return true;
    }
    else if (str1.length() < str2.length()) {
      return false;
    }
    else {
      for (int i = 0; i < str1.length(); i++) {
        if (str1.charAt(i) > str2.charAt(i)) {
          return true;
        }
        else if (str1.charAt(i) < str2.charAt(i)) {
          return false;
        }
      }
    }
    return true;
  }
}
```

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      int F = Integer.parseInt(br.readLine());

      Map<String, String> network = new HashMap<>();
      Map<String, Integer> count = new HashMap<>();

      while (F-- > 0) {
        st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        String str2 = st.nextToken();

        System.out.println(union_merge(network, count, str1, str2));
      }
    }
  }
  static String union_find(Map<String, String> network, Map<String, Integer> count, String str) {
    if (!network.containsKey(str)) {
      network.put(str, str);
      count.put(str, 1);
      return str;
    }
    if (network.get(str).equals(str)) return str;
    return network.put(str, union_find(network, count, network.get(str)));
  }

  static int union_merge(Map<String, String> network, Map<String, Integer> count, String str1, String str2) {
    str1 = union_find(network, count, str1);
    str2 = union_find(network, count, str2);

    if (str1.equals(str2)) return count.get(str1);
    network.put(str1, str2);
    count.put(str2, count.get(str1) + count.get(str2));
    return count.get(str2);
  }
  static boolean compare(String str1, String str2) {
    if (str1.length() > str2.length()) return true;
    else if (str1.length() < str2.length()) return false;
    else {
      for (int i = 0; i < str1.length(); i++) {
        if (str1.charAt(i) < str2.charAt(i)) return true;
        else if (str1.charAt(i) > str2.charAt(i)) return false;
      }
    }
    return false;
  }
}
```