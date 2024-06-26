# 합이 0인 네 정수

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|12 초| (추가 시간 없음)	|1024 MB	|37234|	9269|	5508|	22.610%|

## 문제

정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.

A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다. 다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다. 배열에 들어있는 정수의 절댓값은 최대 228이다.

## 출력 

합이 0이 되는 쌍의 개수를 출력한다.

## 예제 입력 1

```
6
-45 22 42 -16
-41 -27 56 30
-36 53 -37 77
-36 30 -75 -46
26 -38 -10 62
-32 -54 -6 45
```

## 예제 출력 1

```
5
```

## 풀이 

해당 문제는 시간 처리가 생명입니다.

배열 a, b, c, d를 가지고 시도한 방법을 나열해 보자면,

1. 배열 ab, cd의 합을 Map에 key, counter로 넣어서 계산 
2. 배열 ab, cd의 합을 List에 넣고 cd만 정렬한 후 이분 탐색
3. 배열 ab, cd의 합을 List에 넣고 ab, cd 정렬한 후 투 포인터
4. 배열 ab, cd의 합을 List에 넣고 ab, cd 정렬한 후 투 포인터 + 이분 탐색
5. 배열 ab, cd의 합을 Array에 넣고 ab, cd 정렬한 후 투 포인터
6. 배열 ab, cd의 합을 Array에 넣고 ab, cd 정렬한 후 투 포인터 + 이분 탐색

먼저, 1번의 경우 Map의 시간복잡도는 O(1)이라서 나머지 방식들보다 빠를 것 같지만, key가 많아지면서 내부 동작으로 인해 비효율적인 동작을 보여준다고 합니다.

따라서 1번은 시간 초과가 나옵니다.

2번의 경우 ab의 요소가 많기 때문에 이분 탐색의 시간도 무시할 수 없기 때문에 시간 초과가 나옵니다.

3번과 4번은 결국 List를 사용했을 때 내부 동작으로 인해 시간 초과가 나온 것인데, array의 크기를 동적으로 변경하는 작업을 수행하기 때문에 요소가 많은 해당 문제에서는 적합하지 않습니다.

따라서 5번, 6번으로 풀이했을 때 통과를 할 수 있습니다.

참고로 6번의 풀이가 5번의 풀이보다 높은 시간이 소요된 것을 확인하였습니다. 

해당 문제에선 요소가 많은 만큼 직접적으로 투포인터로만 탐색하는 것이 더 빠른 속도를 보여줬습니다.