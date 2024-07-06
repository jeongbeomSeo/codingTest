# 0과 1

**플래티넘 5**

|시간 제한	|메모리 제한	|제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB|	2547|	1441	|1049|	57.828%|

## 문제 

폴란드 왕자 구사과는 다음과 같은 수를 좋아한다.

- 0과 1로만 이루어져 있어야 한다.
- 1이 적어도 하나 있어야 한다.
- 수의 길이가 100 이하이다.
- 수가 0으로 시작하지 않는다.

예를 들어, 101은 구사과가 좋아하는 수이다.

자연수 N이 주어졌을 때, N의 배수 중에서 구사과가 좋아하는 수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 테스트 케이스의 개수 T(T < 10)가 주어진다.

둘째 줄부터 T개의 줄에는 자연수 N이 한 줄에 하나씩 주어진다. N은 20,000보다 작거나 같은 자연수이다.

## 출력 

각각의 테스트 케이스마다 N의 배수이면서, 구사과가 좋아하는 수를 아무거나 출력한다. 만약, 그러한 수가 없다면 BRAK을 출력한다.

## 예제 입력 1

```
6
17
11011
17
999
125
173
```

## 예제 출력 1

```
11101
11011
11101
111111111111111111111111111
1000
1011001101
```
