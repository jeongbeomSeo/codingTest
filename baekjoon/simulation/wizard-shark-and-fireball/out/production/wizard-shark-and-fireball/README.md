# 마법사 상어와 파이어볼 

**골드 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	17472	|6959	|4229	|36.276%|

## 문제 

어른 상어가 마법사가 되었고, 파이어볼을 배웠다.

마법사 상어가 크기가 N×N인 격자에 파이어볼 M개를 발사했다. 가장 처음에 파이어볼은 각자 위치에서 이동을 대기하고 있다. i번 파이어볼의 위치는 (ri, ci), 질량은 mi이고, 방향은 di, 속력은 si이다. 위치 (r, c)는 r행 c열을 의미한다.

격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.

파이어볼의 방향은 어떤 칸과 인접한 8개의 칸의 방향을 의미하며, 정수로는 다음과 같다.

|7	|0|	1|
|---|---|---|
|6	| |	2|
|5	|4|	3|

마법사 상어가 모든 파이어볼에게 이동을 명령하면 다음이 일들이 일어난다.

1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
   - 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
   1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
   2. 파이어볼은 4개의 파이어볼로 나누어진다.
   3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
      1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
      2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
      3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
   4. 질량이 0인 파이어볼은 소멸되어 없어진다.

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.

## 입력 

첫째 줄에 N, M, K가 주어진다.

둘째 줄부터 M개의 줄에 파이어볼의 정보가 한 줄에 하나씩 주어진다. 파이어볼의 정보는 다섯 정수 r<sub>i</sub>, c<sub>i</sub>, m<sub>i</sub>, s<sub>i</sub>, d<sub>i</sub>로 이루어져 있다.

서로 다른 두 파이어볼의 위치가 같은 경우는 입력으로 주어지지 않는다.

## 출력 

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 출력한다.

## 제한 

4 ≤ N ≤ 50
0 ≤ M ≤ N2
1 ≤ K ≤ 1,000
1 ≤ r<sub>i</sub>, c<sub>i</sub> ≤ N
1 ≤ m<sub>i</sub> ≤ 1,000
1 ≤ s<sub>i</sub> ≤ 1,000
0 ≤ d<sub>i</sub> ≤ 7

## 예제 입력 1

```
4 2 1
1 1 5 2 2
1 4 7 1 6
```

## 예제 출력 1

```
8
```

## 예제 입력 2

```
4 2 2
1 1 5 2 2
1 4 7 1 6
```

##  예제 출력 2

```
8
```

## 예제 입력 3

```
4 2 3
1 1 5 2 2
1 4 7 1 6
```

## 예제 출력 3

```
0
```

## 예제 입력 4

```
7 5 3
1 3 5 2 4
2 3 5 2 6
5 2 9 1 7
6 2 1 3 5
4 4 2 4 2
```

## 예제 출력 4

```
9
```