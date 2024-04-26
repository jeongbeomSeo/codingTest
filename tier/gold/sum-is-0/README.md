# 합이 0

**골드 4**

## 문제

Elly는 예상치 못하게 프로그래밍 대회를 준비하는 학생들을 가르칠 위기에 처했다. 대회는 정확히 3명으로 구성된 팀만 참가가 가능하다. 그러나 그녀가 가르칠 학생들에게는 큰 문제가 있었다. 코딩 실력이 좋으면 팀워크가 떨어지고, 팀워크가 좋을수록 코딩 실력이 떨어진다. 그리고 출전하고자 하는 대회는 코딩 실력과 팀워크 모두가 중요하다.

Elly는 그녀가 가르칠 수 있는 모든 학생들의 코딩 실력을 알고 있다. 각각의 코딩 실력 A<sub>i</sub>는 -10000부터 10000 사이의 정수로 표시되어 있다. 그녀는 팀워크와 코딩 실력이 모두 적절한 팀을 만들기 위해, 세 팀원의 코딩 실력의 합이 0이 되는 팀을 만들고자 한다. 이러한 조건 하에, 그녀가 대회에 출전할 수 있는 팀을 얼마나 많이 만들 수 있는지를 계산하여라.

N명의 학생들의 코딩 실력 A<sub>i</sub>가 -10000부터 10000사이의 정수로 주어질 때, 합이 0이 되는 3인조를 만들 수 있는 경우의 수를 구하여라.

## 입력 

입력은 표준 입력으로 주어진다.

첫 번째 줄에 학생의 수 N이 입력된다. 두 번째 줄에 N개의 그녀가 가르칠 학생들의 코딩 실력인 A<sub>i</sub>가 주어진다.

## 출력 

표준 출력으로 그녀가 고를 수 있는 팀의 수를 하나의 정수로 출력한다.

## 제한

- 1 ≤ N ≤ 10000
- -10000 ≤ A<sub>i</sub> ≤ 10000

## 예제 입력 1

```
10
2 -5 2 3 -4 7 -4 0 1 -6
```

## 예제 출력 1

```
6
```

## 나의 코드 

1. 이진 탐색을 이용한 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long result = 0L;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int sum = arr[i] + arr[j];

                if (sum > 0) break;

                int upperIdx = upper_bound(arr, j + 1, N, -sum);
                int lowerIdx = lower_bound(arr, j + 1, N, -sum);

                result += Math.max(0, upperIdx - lowerIdx);
            }
        }

        System.out.println(result);
    }
    private static int upper_bound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private static int lower_bound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
```

- 시간:  3088ms
- 메모리: 15892KB

2. 실패한 풀이 -> 양수, 0, 음수로 나누어서 CASE별로 푸는 방식

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer> positiveNumList = new ArrayList<>();
        List<Integer> zeroNumList = new ArrayList<>();
        List<Integer> negativeNumList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > 0) positiveNumList.add(num);
            else if (num == 0) zeroNumList.add(num);
            else negativeNumList.add(num);
        }

        // CASE
        // 1. 음수 2개 양수 1개
        // 2. 음수 1개 양수 2개
        // 3. 음수 1개 양수 1개 0 1개
        // 4. 0으로만 구성

        Collections.sort(positiveNumList);

        long result = 0L;
        for (int i = 0; i < negativeNumList.size() - 1; i++) {
            for (int j = i + 1; j < negativeNumList.size(); j++) {
                int target = -(negativeNumList.get(i) + negativeNumList.get(j));

                int upperIdx = upper_bound(positiveNumList, 0, positiveNumList.size(), target);
                int lowerIdx = lower_bound(positiveNumList, 0, positiveNumList.size(), target);

                result += Math.max(0, upperIdx - lowerIdx);
            }
        }

        for (int i = 0; i < negativeNumList.size(); i++) {
            int target = -negativeNumList.get(i);

            int ptr1 = 0;
            int ptr2 = positiveNumList.size() - 1;

            while (ptr1 < ptr2) {
                int sum = positiveNumList.get(ptr1) + positiveNumList.get(ptr2);

                if (sum == target) {
                    if (positiveNumList.get(ptr1) != positiveNumList.get(ptr2)) {
                        int upperPtr1 = upper_bound(positiveNumList, ptr1 + 1, positiveNumList.size(), positiveNumList.get(ptr1));
                        int lowerPtr2 = lower_bound(positiveNumList, 0, ptr2, positiveNumList.get(ptr2)) - 1;
                        result += (long) (upperPtr1 - ptr1) * (ptr2 - lowerPtr2);
                        ptr1 = upperPtr1;
                        ptr2 = lowerPtr2;
                    } else {
                        result += ((long) (ptr2 - ptr1 + 1) * (ptr2 - ptr1)) / 2;
                        break;
                    }
                } else if (sum > target) {
                    ptr2--;
                } else {
                    ptr1++;
                }
            }
        }

        if (zeroNumList.size() >= 1) {
            for (int i = 0; i < negativeNumList.size(); i++) {
                int target = -negativeNumList.get(i);

                int upperIdx = upper_bound(positiveNumList, 0, positiveNumList.size(), target);
                int lowerIdx = lower_bound(positiveNumList, 0, positiveNumList.size(), target);

                result += (long) Math.max(0, upperIdx - lowerIdx) * zeroNumList.size();
            }
        }

        if (zeroNumList.size() >= 3) {
            result += ((long) zeroNumList.size() * (zeroNumList.size() - 1) * (zeroNumList.size()) - 2) / (3 * 2);
        }

        System.out.println(result);
    }
    private static int upper_bound(List<Integer> arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private static int lower_bound(List<Integer> arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
```

2. Map을 활용하여 CASE 별로 구분한 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> pMap = new HashMap<>();
        Map<Integer, Integer> nMap = new HashMap<>();
        Map<Integer, Integer> zMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > 0) pMap.put(num, pMap.getOrDefault(num, 0) + 1);
            else if (num == 0) zMap.put(num, zMap.getOrDefault(num, 0) + 1);
            else nMap.put(num, nMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> pKeyList = new ArrayList<>(pMap.keySet());
        List<Integer> nKeyList = new ArrayList<>(nMap.keySet());

        // CASE
        // 1. 양수 2개 음수 1개
        // 2. 양수 1개 음수 2개
        // 3. 양수 1개 음수 1개 0 1개
        // 4. 0 3개

        long result = 0L;
        // CASE 1
        for (int i = 0; i < pKeyList.size() - 1; i++) {
            for (int j = i + 1; j < pKeyList.size(); j++) {
                int target = -(pKeyList.get(i) + pKeyList.get(j));

                result += (long) pMap.get(pKeyList.get(i)) * pMap.get(pKeyList.get(j)) * nMap.getOrDefault(target, 0);
            }
        }

        for (int i = 0; i < pKeyList.size(); i++) {
            if (pMap.get(pKeyList.get(i)) >= 2) {
                int target = -2 * pKeyList.get(i);

                result += (long) pMap.get(pKeyList.get(i)) * ((pMap.get(pKeyList.get(i))) - 1) / 2 * nMap.getOrDefault(target, 0);
            }
        }

        // CASE 2
        for (int i = 0; i < nKeyList.size() - 1; i++) {
            for (int j = i + 1; j < nKeyList.size(); j++) {
                int target = -(nKeyList.get(i) + nKeyList.get(j));

                result += (long) nMap.get(nKeyList.get(i)) * nMap.get(nKeyList.get(j)) * pMap.getOrDefault(target, 0);
            }
        }

        for (int i = 0; i < nKeyList.size(); i++) {
            if (nMap.get(nKeyList.get(i)) >= 2) {
                int target = -2 * nKeyList.get(i);

                result += (long) nMap.get(nKeyList.get(i)) * ((nMap.get(nKeyList.get(i))) - 1) / 2 * pMap.getOrDefault(target, 0);
            }
        }

        // CASE 3
        if (zMap.containsKey(0)) {
            if (zMap.get(0) >= 1) {
                for (int i = 0; i < pKeyList.size(); i++) {
                    for (int j = 0; j < nKeyList.size(); j++) {
                        if (pKeyList.get(i) + nKeyList.get(j) == 0) {
                            result += (long)pMap.get(pKeyList.get(i)) * nMap.get(nKeyList.get(j)) * zMap.get(0);
                        }
                    }
                }
            }

            // CASE 4
            if (zMap.get(0) >= 3) {
                int count = zMap.get(0);
                result += (long) count * (count - 1) * (count - 2) / (3 * 2);
            }
        }


        System.out.println(result);
    }
}
```

- 시간: 1264ms
- 메모리: 272712KB

3. counter[]를 이용해서 작은 부분에서부터 문제 해결하는 풀이

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] counter = new int[40001];

        long result = 0;
        for (int i = 0; i < N; i++) {
            result += counter[20000 - arr[i]];
            for (int j = 0; j < i; j++) {
                counter[20000 + (arr[j] + arr[i])]++;
            }
        }

        System.out.println(result);
    }
}
```

- 시간: 272ms
- 메모리: 18264KB