# 전깃줄 2

**플래티넘 5**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	10771	|4236	|3099	|39.624%|

## 문제

두 전봇대 A와 B 사이에 하나 둘씩 전깃줄을 추가하다 보니 전깃줄이 서로 교차하는 경우가 발생하였다. 합선의 위험이 있어 이들 중 몇 개의 전깃줄을 없애 전깃줄이 교차하지 않도록 만들려고 한다.

예를 들어, <그림 1>과 같이 전깃줄이 연결되어 있는 경우 A의 1번 위치와 B의 8번 위치를 잇는 전깃줄, A의 3번 위치와 B의 9번 위치를 잇는 전깃줄, A의 4번 위치와 B의 1번 위치를 잇는 전깃줄을 없애면 남아있는 모든 전깃줄이 서로 교차하지 않게 된다. 

![](https://upload.acmicpc.net/854620e2-d10b-4bb6-84f0-0dd4b89bfb13/-/preview/)
<그림 1>

전깃줄이 전봇대에 연결되는 위치는 전봇대 위에서부터 차례대로 번호가 매겨진다. 전깃줄의 개수와 전깃줄들이 두 전봇대에 연결되는 위치의 번호가 주어질 때, 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 최소 개수의 전깃줄을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다. 전깃줄의 개수는 100,000 이하의 자연수이다. 둘째 줄부터 한 줄에 하나씩 전깃줄이 A전봇대와 연결되는 위치의 번호와 B전봇대와 연결되는 위치의 번호가 차례로 주어진다. 위치의 번호는 500,000 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다. 

## 출력 

첫째 줄에 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다. 둘째 줄부터 한 줄에 하나씩 없애야 하는 전깃줄의 A전봇대에 연결되는 위치의 번호를 오름차순으로 출력한다. 만약 답이 두 가지 이상이라면 그 중 하나를 출력한다.

## 예제 입력 1

```
8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6
```

## 예제 출력 1

```
3
1
3
4
```

## 코드 

```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Wire[] wires = new Wire[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());

            wires[i] = new Wire(src, dst);
        }

        Arrays.sort(wires);

        int[] lisTable = active_LIS(wires, N);

        bw.write(lisTable.length + "\n");
        for (int i = 0; i < lisTable.length; i++) {
            bw.write(lisTable[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static int[] active_LIS(Wire[] wires, int N) {

        int[] table = new int[N];
        int[] idxs = new int[N];

        int size = 0;
        table[size++] = wires[0].dst;

        for (int i = 1; i < N; i++) {
            if (table[size - 1] < wires[i].dst) {
                table[size] = wires[i].dst;
                idxs[i] = size;
                size++;
            } else if (table[size - 1] > wires[i].dst) {
                int idx = lower_bound(table, 0, size, wires[i].dst);
                table[idx] = wires[i].dst;
                idxs[i] = idx;
            }
        }

        int tableSize = N - size;
        int[] lisTable = new int[tableSize];

        size--;
        for (int i = N - 1; i >= 0; i--) {
            if (idxs[i] == size) {
                size--;
            } else {
                lisTable[--tableSize] = wires[i].src;
            }
        }

        return lisTable;
    }
    private static int lower_bound(int[] table, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (table[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
class Wire implements Comparable<Wire>{
    int src;
    int dst;

    Wire(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public int compareTo(Wire o) {
        return this.src - o.src;
    }
}
```