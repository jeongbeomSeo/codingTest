# DSLR

**골드 4**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|6 초|	256 MB	|71907|	17614|	11440|	20.823%|

## 문제

네 개의 명령어 D, S, L, R 을 이용하는 간단한 계산기가 있다. 이 계산기에는 레지스터가 하나 있는데, 이 레지스터에는 0 이상 10,000 미만의 십진수를 저장할 수 있다. 각 명령어는 이 레지스터에 저장된 n을 다음과 같이 변환한다. n의 네 자릿수를 d<sub>1</sub>, d<sub>2</sub>, d<sub>3</sub>, d<sub>4</sub>라고 하자(즉 n = ((d<sub>1</sub> × 10 + d<sub>2</sub>) × 10 + d<sub>3</sub>) × 10 + d<sub>4</sub>라고 하자)

1. D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
2. S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
3. L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d<sub>2</sub>, d<sub>3</sub>, d<sub>4</sub>, d<sub>1</sub>이 된다.
4. R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d<sub>4</sub>, d<sub>1</sub>, d<sub>2</sub>, d<sub>3</sub>이 된다.
위에서 언급한 것처럼, L 과 R 명령어는 십진 자릿수를 가정하고 연산을 수행한다. 예를 들어서 n = 1234 라면 여기에 L 을 적용하면 2341 이 되고 R 을 적용하면 4123 이 된다.

여러분이 작성할 프로그램은 주어진 서로 다른 두 정수 A와 B(A ≠ B)에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램이다. 예를 들어서 A = 1234, B = 3412 라면 다음과 같이 두 개의 명령어를 적용하면 A를 B로 변환할 수 있다.

1234 →<sub>L</sub> 2341 →<sub>L</sub> 3412
1234 →<sub>R</sub> 4123 →<sub>R</sub> 3412

따라서 여러분의 프로그램은 이 경우에 LL 이나 RR 을 출력해야 한다.

n의 자릿수로 0 이 포함된 경우에 주의해야 한다. 예를 들어서 1000 에 L 을 적용하면 0001 이 되므로 결과는 1 이 된다. 그러나 R 을 적용하면 0100 이 되므로 결과는 100 이 된다.

## 입력 

프로그램 입력은 T 개의 테스트 케이스로 구성된다. 테스트 케이스 개수 T 는 입력의 첫 줄에 주어진다. 각 테스트 케이스로는 두 개의 정수 A와 B(A ≠ B)가 공백으로 분리되어 차례로 주어지는데 A는 레지스터의 초기 값을 나타내고 B는 최종 값을 나타낸다. A 와 B는 모두 0 이상 10,000 미만이다.

## 출력

A에서 B로 변환하기 위해 필요한 최소한의 명령어 나열을 출력한다. 가능한 명령어 나열이 여러가지면, 아무거나 출력한다.

## 예제 입력 1

```
3
1234 3412
1000 1
1 16
```

## 예제 출력 1

```
3
1234 3412
1000 1
1 16
```

## 코드

**AC**

```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            String result = queryResult(a, b);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static String queryResult(int a, int b) {
        Queue<Node> q = new ArrayDeque<>();

        boolean[] isVisited = new boolean[10000];

        q.add(new Node(a, ""));
        isVisited[a] = true;

        String result = "";
        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.value == b) {
                result = curNode.command;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextValue = getNextValue(curNode.value, i);

                if (!isVisited[nextValue]) {
                    q.add(new Node(nextValue, getNextCommand(curNode.command, i)));
                    isVisited[nextValue] = true;
                }
            }
        }

        return result;
    }
    private static String getNextCommand(String command, int i) {
        if (i == 0) return command + "D";
        else if (i == 1) return command + "S";
        else if (i == 2) return command + "L";
        else return command + "R";
    }
    private static int getNextValue(int num, int i) {
        if (i == 0) return calcCase_D(num);
        else if (i == 1) return calcCase_S(num);
        else if (i == 2) return calcCase_L(num);
        else return calcCase_R(num);
    }

    private static int calcCase_D(int num) {
        return (2 * num) % 10000;
    }

    private static int calcCase_S(int num) {
        return num != 0 ? num - 1 : 9999;
    }

    private static int calcCase_L(int num) {
        StringBuilder sb = new StringBuilder();

        String strNum = fotmatStr(String.valueOf(num));

        int size = strNum.length();
        for (int i = 1; i < size; i++) {
            sb.append(strNum.charAt(i));
        }

        sb.append(strNum.charAt(0));

        return Integer.parseInt(sb.toString());
    }

    private static int calcCase_R(int num) {
        StringBuilder sb = new StringBuilder();

        String strNum = fotmatStr(String.valueOf(num));

        int size = strNum.length();
        sb.append(strNum.charAt(size - 1));

        for (int i = 0; i < size - 1; i++) {
            sb.append(strNum.charAt(i));
        }

        return Integer.parseInt(sb.toString());
    }
    private static String fotmatStr(String strNum) {
        int size = strNum.length();

        if (size < 4) {
            StringBuilder strNumBuilder = new StringBuilder(strNum);
            while (strNumBuilder.length() != 4) {
                strNumBuilder.insert(0, "0");
            }
            strNum = strNumBuilder.toString();
        }
        return strNum;
    }
}
class Node {
    int value;
    String command;

    Node (int value, String command) {
        this.value = value;
        this.command = command;
    }
}
```

**MLE**

```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());

            char[] array = convertToCharArray(Integer.parseInt(st.nextToken()));
            char[] target = convertToCharArray(Integer.parseInt(st.nextToken()));

            String resultCommand = bfs(target, array);

            bw.write(resultCommand + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static String bfs(char[] target, char[] initArray) {
        Queue<Registry> buffer = new ArrayDeque<>();
        buffer.add(new Registry(initArray));

        while (!buffer.isEmpty()) {
            Registry curRegistry = buffer.poll();

            for (int i = 0; i < 4; i++) {
                Registry nxtRegistry = modulate(curRegistry, i);
                if (compareCharArray(nxtRegistry.value, target)) return nxtRegistry.command;
                buffer.add(nxtRegistry);
            }
        }

        return null;
    }
    private static Registry modulate(Registry registry, int i) {
        if (i == 0) return new Registry(registry.command + "D", modulate_D(registry.value));
        else if (i == 1) return new Registry(registry.command + "S", modulate_S(registry.value));
        else if (i== 2) return new Registry(registry.command + "L", modulate_L(registry.value));
        else return new Registry(registry.command + "R", modulate_R(registry.value));
    }
    private static boolean compareCharArray(char[] arrayA, char[] arrayB) {

        for (int i = 0; i < 4; i++) {
            if (arrayA[i] != arrayB[i]) return false;
        }

        return true;
    }
    private static char[] convertToCharArray(int num) {
        char[] array = new char[4];

        for (int i = 3; i >= 0; i--) {
            // Check: (char)3 is '3'? NO! 3 + '0' -> '3' 해야함.
            array[i] = (char)(num % 10 + '0');

            num /= 10;
        }

        return array;
    }
    private static char[] modulate_D(char[] array) {
        int num = Integer.parseInt(String.valueOf(array));

        num = (num * 2) % 10000;

        return convertToCharArray(num);
    }
    private static char[] modulate_S(char[] array) {

        if (String.valueOf(array).equals("0000")) return new char[]{'9', '9', '9', '9'};

        char[] nextArray = new char[4];
        int i;
        for (i = 3; i >= 0; i--) {
            if (array[i] == '0') {
                nextArray[i] = '9';
            } else {
                nextArray[i] = (char)(array[i] - 1);
                i--;
                break;
            }
        }
        while (i >= 0) {
            nextArray[i] = array[i];
            i--;
        }

        return nextArray;
    }
    private static char[] modulate_R(char[] array) {

        char[] nextArray = new char[4];
        nextArray[0] = array[3];

        for (int i = 3; i >= 1; i--) {
            nextArray[i] = array[i - 1];
        }

        return nextArray;
    }
    private static char[] modulate_L(char[] array) {

        char[] nextArray = new char[4];
        nextArray[3] = array[0];
        for (int i = 1; i < 4; i++) {
            nextArray[i - 1] = array[i];
        }

        return nextArray;
    }
}
class Registry {
    String command;
    char[] value;

    Registry(char[] value) {
        command = "";
        this.value = value;
    }
    Registry(String command, char[] value) {
        this.command = command;
        this.value = value;
    }
}
```