# 리모컨
**골드 5**

|시간 제한|	메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB	|101109	|24833|	17446|	23.095%|

## 문제 

수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.

수빈이가 지금 보고 있는 채널은 100번이다.

## 입력 

첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.

## 출력

첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.

## 예제 입력 1

```
5457
3
6 7 8
```

## 예제 출력 1

```
6
```

## 힌트 

예제 1의 경우 5455++ 또는 5459--

## 문제 접근 방식

처음에는 target의 자릿수마다 위 아래로 근접한 숫자를 체크해서 Queue에 넣어주는 방식을 고려했다.

하지만, 이러한 방식으로 구현할 경우 예제 7번에서 요구하는 출력값은 2228이지만, 10005가 나온다.

그 이유는 처음에 7과 0을 넣어주면서 시작할텐데 현재 구현 방식으로는 그 다음 숫자는 전부 0이 들어갈 것이기 때문이다.

**현재 코드**

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] notWorkingBtn = new boolean[10];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int brokenBtnNum = Integer.parseInt(st.nextToken());

                notWorkingBtn[brokenBtnNum] = true;
            }
        }

        System.out.println(queryResult(notWorkingBtn, N));
    }
    static int queryResult(boolean[] notWorkingBtn, int target) {
        int result = Math.abs(target - 100);

        return Math.min(result, getMinCount(notWorkingBtn, String.valueOf(target)));
    }
    private static int getMinCount(boolean[] notWorkingBtn, String target) {
        Queue<Node> q = new ArrayDeque<>();

        int size = target.length();

        q.add(new Node("", 0));

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.ptr == size) {
                min = Math.min(calcCount(curNode, target, size), min);
                continue;
            }

            updateQueue(q, curNode, notWorkingBtn, target);
        }
        return min;
    }
    private static int calcCount(Node node, String target, int size) {
        return Math.abs(Integer.parseInt(node.value) - Integer.parseInt(target)) + size;
    }
    private static void updateQueue(Queue<Node> q, Node curNode, boolean[] notWorkingBtn, String target) {

        int upNum = findNearestUpNum(notWorkingBtn, target, curNode.ptr);
        int downNum = findNearestDownNum(notWorkingBtn, target, curNode.ptr);

        if (upNum == downNum) {
            q.add(new Node(
                    curNode.value + getNumChar(upNum), curNode.ptr + 1));
        } else {
            q.add(new Node(
                    curNode.value + getNumChar(upNum), curNode.ptr + 1));

            q.add(new Node(
                    curNode.value + getNumChar(downNum), curNode.ptr + 1));
        }
    }
    private static int findNearestUpNum (boolean[] notWorkingBtn, String target, int ptr) {
        int idx = getNumIdx(target.charAt(ptr));
        while (notWorkingBtn[idx]) {
            idx = getUpIdx(idx);
        }
        return idx;
    }
    private static int findNearestDownNum (boolean[] notWorkingBtn, String target, int ptr) {
        int idx = getNumIdx(target.charAt(ptr));

        while (notWorkingBtn[idx]) {
            idx = getDownIdx(idx);
        }
        return idx;
    }
    private static int getNumIdx(char c) {
        return c - '0';
    }
    private static char getNumChar(int idx) {
        return (char)(idx + '0');
    }
    private static int getUpIdx(int idx) {
        return idx + 1 != 10 ? idx + 1 : 0;
    }
    private static int getDownIdx(int idx) {
        return idx - 1 != -1 ? idx - 1 : 9;
    }
}
class Node {
    String value;
    int ptr;

    Node(String value, int ptr) {
        this.value = value;
        this.ptr = ptr;
    }
}
```

다른 방식의 접근으로 생각해보자면 차례 차례 숫자를 넣어주면서 특정 시점에 target보다 낮은 숫자로 결정되는지, 높은 숫자로 결정되는지 확인하고 그 이후는 그것에 맞춰서 탐색하면 된다.

즉, 0번째 자리부터 순차적으로 탐색하면서 해당 숫자가 망가진 숫자가 아니라면 그대로 대입하고 넘어가면 될 것이다.

하지만 만약 망가진 숫자를 처음으로 확인이 된다면 그때부터 이전 방식과 같이 위 아래 인접한 숫자를 넣어주면 된다.

그리고 그 이후 부터는 위, 아래 인접한 숫자를 탐색하는 것은 낭비일 뿐더러 맞는 접근 방식이 아닌 것 같다.

왜냐하면 이제부턴 뒤에 숫자가 무엇이 오든 target보다 숫자가 큰지 작은지 결정이 되었기 떄문이다.

따라서 이것에 맞춰서 target보다 숫자가 작다면, 뒤에 숫자들은 무조건 높은 숫자가 대입되어야 한다.

반대로 target보다 숫자가 크다면, 뒤에 숫자들은 모두 낮은 숫자가 대입되어야 할 것이다.

**코드**

**AC(비효율적 풀이)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        boolean[] isBrokenBtn = new boolean[10];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                isBrokenBtn[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int MAX = getMAXNum(N);

        System.out.println(queryResult(MAX, isBrokenBtn, N));
    }
    private static int queryResult(int MAX, boolean[] isBrokenBtn, int target) {
        result = Math.abs(100 - target);

        recursive(MAX, isBrokenBtn, target, "");

        return result;
    }
    private static void recursive(int MAX, boolean[] isBrokenBtn, int target, String str) {

        int curNum = 0;
        if (!str.isEmpty()) {
            curNum = Integer.parseInt(str);
        }
        int length = str.length();
        if (curNum <= MAX && length <= String.valueOf(MAX).length()) {
            if (!str.isEmpty()) {
                result = Math.min(result, Math.abs(target - curNum) + length);
            }

            for (int i = 0; i <= 9; i++) {
                if (!isBrokenBtn[i]) {
                    recursive(MAX, isBrokenBtn, target, i + str);
                }
            }
        }
    }
    private static int getMAXNum(int N) {

        String str = String.valueOf(N);
        int length = str.length();

        String result = "2" +
                "0".repeat(length);

        return Integer.parseInt(result);
    }
}
```

