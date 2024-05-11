# 환상의 듀엣

**플래티넘 5**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|256 MB|	1359|	699|	555|	56.288%|

## 문제

상덕이와 희원이는 소문난 환상의 듀엣으로, 노래방에 가서 노래를 자주 부르곤 한다. 

어느 날 상덕이는 백준이에게 선물 받은 악보를 가져왔다. 

악보에는 그 노래를 표현하는데 필요한 음의 높이가 순서대로 N개 적혀져 있었다. 

둘은 악보에 적혀 있는 모든 음들을 노래해야 하며, 각 음은 둘 중 한 사람에 의해서만 불러져야 한다. 

예를 들어 악보에 {3, 6, 2, 5, 4}가 적혀져 있을 때, 상덕이가 {3, 2, 4}을 노래하면 희원이는 {6, 5}를 노래할 것이고, 상덕이가 {6, 2, 5}을 노래하면 희원이는 {3, 4}를 노래할 것이다.

노래를 부르다 음의 높이를 변경하는 것은 힘든 일이다. 예를 들어 {4, 6}을 부르는 것은 {4, 4}를 부르는 것에 비해서 음의 변경이 발생하기 때문에 더 힘들다고 볼 수 있다. 

희원이는 {a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>k</sub>}라는 음들의 집합을 노래할 때 힘든 정도를 |a<sub>1</sub> - a<sub>2</sub>| + |a<sub>2</sub> - a<sub>3</sub>| + ... + |a<sub>k-1</sub> - a<sub>k</sub>|로 정의했다. 노래를 부르는 사람은 상덕이와 희원이 둘 뿐이므로, 음들을 집합은 두 개가 있을 것이다. 따라서 두 사람이 해당 악보를 노래를 할 때 힘든 정도는 두 집합의 힘든 정도의 합으로 표현될 수 있다.

상덕이와 희원이는 해당 악보를 노래할 때 힘든 정도를 최소화하고 싶다. 

예를 들어 악보가 {1, 3, 8, 12, 13}으로 주어진다하자. 

앞의 2개를 상덕이가 부르고 뒤의 3개를 희원이가 부르게되면 상덕이의 힘든 정도는 |1 - 3| = 2, 희원이의 힘든 정도는 |8 - 12| + |12 - 13| = 5가 되며 합인 7이 총 힘든 정도가 되고, 이 값은 나올 수 있는 힘든 정도 중에 가장 최솟값이다. 

상덕이와 희원이를 위해서 해당 악보를 노래할 때 힘들 수 있는 정도의 최솟값을 알려주는 프로그램을 작성해보자.

## 입력

첫 번째 줄에는 음의 개수 N (1 ≤ N ≤ 2,000)이 주어진다.

두 번째 줄에는 N개의 음의 높이가 공백(빈 칸)으로 구분되어 주어진다. 각 음의 높이의 범위는 1 이상 1,000,000 이하의 자연수이다.

## 출력 

상덕이과 희원이가 해당 악보를 노래할 때 힘들 수 있는 정도의 최솟값을 출력한다.

## 예제 입력 1

```
5
1 3 8 12 13
```

## 예제 출력 1

```
7
```

## 예제 입력 2

```
5
1 5 6 2 1
```

## 예제 출력 2

```
3
```

## 오류 코드 

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private static Map<Regist, Integer> register;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        register = new HashMap<>();

        register.put(new Regist(0, arr[0], -1), 0);

        System.out.println(dp(arr, new Regist(1, arr[0], -1)));
    }
    private static int dp(int[] arr, Regist regist) {

        if (regist.pos == arr.length) {
            return 0;
        }

        if (register.containsKey(regist)) {
            return register.get(regist);
        }

        int lValue = Math.abs(arr[regist.pos] - regist.lv);
        lValue += dp(arr, new Regist(regist.pos + 1, arr[regist.pos], regist.rv));

        int rValue = regist.rv != -1 ? Math.abs(arr[regist.pos] - regist.rv) : 0;
        rValue += dp(arr, new Regist(regist.pos + 1, regist.lv, arr[regist.pos]));

        register.put(regist, Math.min(lValue, rValue));
        return Math.min(lValue, rValue);
    }
}
class Regist {
    int pos;
    int lv;
    int rv;

    Regist(int pos, int lv, int rv) {
        this.pos = pos;
        this.lv = lv;
        this.rv = rv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, lv, rv);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Regist) {
            Regist o = (Regist) obj;
            return this.pos == o.pos && this.lv == o.lv && this.rv == o.rv;
        }
        return false;
    }
}
```

## 나의 코드 

**효율 떨어지는 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private static Map<Regist, Integer> register;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        register = new HashMap<>();

        register.put(new Regist(0, arr[0], -1), 0);

        System.out.println(dp(arr, new Regist(1, arr[0], -1)));
    }
    private static int dp(int[] arr, Regist regist) {

        if (regist.pos == arr.length) {
            return 0;
        }

        if (register.containsKey(regist)) {
            return register.get(regist);
        }

        int lValue = regist.lv != -1 ? Math.abs(arr[regist.pos] - regist.lv) : 0;
        lValue += dp(arr, new Regist(regist.pos + 1, arr[regist.pos], regist.rv));

        int rValue = regist.rv != -1 ? Math.abs(arr[regist.pos] - regist.rv) : 0;
        rValue += dp(arr, new Regist(regist.pos + 1, regist.lv, arr[regist.pos]));

        register.put(regist, Math.min(lValue, rValue));
        return Math.min(lValue, rValue);
    }
}
class Regist {
    int pos;
    int lv;
    int rv;

    Regist(int pos, int lv, int rv) {
        this.pos = pos;
        if (lv <= rv) {
            this.lv = lv;
            this.rv = rv;
        } else {
            this.lv = rv;
            this.rv = lv;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, lv, rv);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Regist) {
            Regist o = (Regist) obj;
            return this.pos == o.pos && this.lv == o.lv && this.rv == o.rv;
        }
        return false;
    }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * e1: 현재 위치(pos)
         * e3: 노래 부른 사람이 아닌 다른 사람의 가장 최근 노래 부른 위치(pos2)
         * MAX_MEMORY: N = 2,000일때, 2000 * 2 * 2000 B = 8,000,000B = 8000KB = 8MB
         */
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        dfs(dp, arr, 0, 0, 0);

        System.out.println(result);
    }
    private static void dfs(int[][] dp, int[] arr, int pos1, int pos2, int cost) {

        if (dp[pos1][pos2] <= cost) return;

        dp[pos1][pos2] = cost;

        if (pos1 == N - 1 || pos2 == N - 1) {
            result = Math.min(result, cost);
            return;
        }

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        dfs(dp, arr, nxtPos, pos2, cost + Math.abs(arr[nxtPos] - arr[pos1]));
        if (pos2 == 0) {
            dfs(dp, arr, pos1, nxtPos, cost);
        } else {
            dfs(dp, arr, pos1, nxtPos, cost + Math.abs(arr[nxtPos] - arr[pos2]));
        }
    }
}
```