# 바이러스

**플래티넘 5**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	1922|	667|	509|	40.269%|

## 문제

새로운 컴퓨터 바이러스가 발견되어서 이를 치료하는 백신 프로그램을 개발하려고 한다. 백신 프로그램을 개발하기 위해서는 바이러스 코드를 알아야 하는데, 감염된 프로그램들에 공통으로 존재하는 부분이 바이러스로 의심되는 부분이다. (프로그램의 코드는 양의 정수들의 나열로 표현된다.)

단, 바이러스는 자신이 탐지되는 것을 막기 위해서, 자신의 코드를 반대로 기입하기도 한다. 또한, 프로그램들의 코드 일부가 우연히 같을 수 있기 때문에, 공통으로 나타나는 코드의 길이가 K 이상인 경우에만 바이러스 코드로 추정한다.

- 프로그램 1: 10 8 23 93 21 42 52 22 13 1 2 3 4
- 프로그램 2: 1 3 8 9 21 42 52 22 13 41 42
- 프로그램 3: 9 21 42 52 13 22 52 42 12 21
- 예를 들어, K = 4이고, 바이러스에 감염된 3개의 프로그램의 코드가 위와 같다고 하면, 길이가 4인 "42 52 22 13" 코드가 프로그램 1과 2에 나타나고, "13 22 52 42"이 프로그램 3에 나타나므로 이 코드는 바이러스로 추정된다.

바이러스에 감염된 프로그램이 N 개 주어졌을 때, 바이러스 코드로 추정되는 부분이 있는지 여부를 판정하는 프로그램을 작성하시오.

## 입력 

첫 번째 줄에는 감염된 프로그램의 개수 N 과 바이러스 코드 추정을 위한 최소 길이를 나타내는 정수 K 가 주어진다. 단, 2 ≤ N ≤ 100이고, 4 ≤ K ≤ 1,000이다. 두 번째 줄부터 각 프로그램에 대한 정보가 주어지는데, 먼저 i 번째 프로그램의 길이를 나타내는 정수 M<sub>i</sub>가 주어지고, 다음 줄에 프로그램 코드를 나타내는 M<sub>i</sub>개의 양의 정수가 공백을 사이에 두고 주어진다. 단, 10 ≤ M<sub>i</sub> ≤ 1,000이고, 프로그램 코드를 나타내는 각 정수의 범위는 1이상 10,000 이하이다.

## 출력 

바이러스 코드로 추정되는 부분이 있으면 YES를 출력하고, 없으면 NO를 출력해야 한다.

## 예제 입력 1

```
3 4
13
10 8 23 93 21 42 52 22 13 1 2 3 4
11
1 3 8 9 21 42 52 22 13 41 42
10
9 21 42 52 13 22 52 42 12 21
```

## 예제 출력 1

```
YES
```

## 코드

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static long[] calcHash(ArrayList<Integer> pat, int K) {
    long fowardHash = 0;
    long reverseHash = 0;
    long power = 1;

    for(int i = 0; i < K; i++) {
      fowardHash += pat.get(i) * power;
      reverseHash += pat.get(K - 1 - i) * power;
      if(i != K -1) {
        power *= 2;
      }
    }
    return new long[]{fowardHash, reverseHash, power};
  }

  static long[] nextHash(ArrayList<Integer> pat, int pt, long[] txtHash, int K) {
    long power = txtHash[2];
    txtHash[0] = (txtHash[0] - pat.get(pt - 1)) / 2 + pat.get(K - 1 + pt) * power;
    txtHash[1] = 2 * (txtHash[1] - pat.get(pt - 1) * power) + pat.get(K - 1 + pt);

    return txtHash;
  }

  static boolean rkMatch(ArrayList txt, ArrayList pat, int pt, long[] mainHash, int K) {
    long[] txtHash = new long[3];
    int txtLen = txt.size();

    txtHash = calcHash(txt, K);
    for(int i = 0; i <= txtLen - K; i++) {
      if(txtHash[0] == mainHash[0] || txtHash[0] == mainHash[1]) {
        boolean check = true;
        for(int j = 0; j < K; j++) {
          if(pat.get(pt + j) != txt.get(i + j) && pat.get(pt + j) != txt.get(i + K - 1 - j)) {
            check = false;
            break;
          }
        }
        if(check) return true;
      }
      if(i != txtLen - K) {
        txtHash = nextHash(txt, i + 1, txtHash, K);
      }
    }


    return false;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Integer>[] programs = new ArrayList[N];
    for(int p = 0; p < N; p++) {
      int M = Integer.parseInt(br.readLine());

      programs[p] = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      for(int i = 0 ; i < M; i++) {
        int num = Integer.parseInt(st.nextToken());
        programs[p].add(num);
      }
    }

    boolean success = false;

    int program1Length = programs[0].size();
    long[] mainHash = calcHash(programs[0], K);
    for(int pt = 0; pt <= program1Length - K; pt++) {
      if(pt != 0) mainHash = nextHash(programs[0], pt, mainHash, K);
      int i;
      for(i = 1; i < N; i++) {
        if(!rkMatch(programs[i], programs[0], pt, mainHash, K)) break;
      }
      if(i == N) {
        success = true;
        break;
      }
    }
    if(success) System.out.println("YES");
    else System.out.println("NO");
  }
}
```

사실 해당 풀이 자체의 로직은 맞다고 판단하지만, 그것이 문제가 아니다. 

라빈 카프를 사용할 때 가장 주의할 점은 범위를 벗어나는 경우이다.

해당 코드의 경우 K가 1,000까지 가능하기 때문에 2<sup>1000</sup>이 곱해지는 경우도 생길 수 있다는 것이다.

해당 문제를 Rabin-Karp로 굳이 사용하고 싶다면 특정 범위를 설정해 두고 범위가 넘은 숫자의 경우 설쟁 해둔 숫자로 나눠 주는 형태(MOD)로 해싱 처리를 해야할 것이다.