# 집합

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1.5 초	|4 MB (하단 참고)	|80161	|24571|	17773	|29.257%|

## 문제 

비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

- add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
- remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
- check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
- toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
- all: S를 {1, 2, ..., 20} 으로 바꾼다.
- empty: S를 공집합으로 바꾼다. 

## 입력 

첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.

둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.

## 출력 

check 연산이 주어질때마다, 결과를 출력한다.

## 예제 입력 1

```
26
add 1
add 2
check 1
check 2
check 3
remove 2
check 1
check 2
toggle 3
check 1
check 2
check 3
check 4
all
check 10
check 20
toggle 10
remove 20
check 10
check 20
empty
check 1
toggle 1
check 1
toggle 1
check 1
```

## 예제 출력 1

```
1
1
0
1
0
1
0
1
0
1
1
0
0
0
1
0
```

## 코드 

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static BufferedWriter bw;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int M = Integer.parseInt(br.readLine());

    int set = 0;

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      String command = st.nextToken();
      int x = 0;
      if (!command.equals("all") && !command.equals("empty")) x = Integer.parseInt(st.nextToken());

      if (command.equals("add")) set = add(set, x);
      else if (command.equals("remove")) set = remove(set, x);
      else if (command.equals("check")) check(set, x);
      else if (command.equals("all")) set = all(set);
      else if (command.equals("toggle")) set = toggle(set, x);
      else if (command.equals("empty")) set = empty(set);
    }
    bw.flush();
    bw.close();
  }
  static int add(int set, int x) {
    return set | (1 << x);
  }
  static int remove(int set, int x) {
    return set & ~(1 << x);
  }
  static void check(int set, int x) throws IOException{
    if ((set & (1 << x)) == (1 << x)) bw.append(1 + "\n");
    else bw.append(0 + "\n");
  }
  static int toggle(int set, int x) {
    return set ^ (1 << x);
  }
  static int all(int set) {
    return -1;
  }
  static int empty(int set) {
    return 0;
  }
}
```