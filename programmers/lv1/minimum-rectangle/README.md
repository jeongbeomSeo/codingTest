# 최소직사각형

## 문제 설명

명함 지갑을 만드는 회사에서 지갑의 크기를 정하려고 합니다. 다양한 모양과 크기의 명함들을 모두 수납할 수 있으면서, 작아서 들고 다니기 편한 지갑을 만들어야 합니다. 이러한 요건을 만족하는 지갑을 만들기 위해 디자인팀은 모든 명함의 가로 길이와 세로 길이를 조사했습니다.

아래 표는 4가지 명함의 가로 길이와 세로 길이를 나타냅니다.

|명함 번호|	가로 길이|	세로 길이|
|---|---|---
|1	|60|	50|
|2	|30|	70|
|3	|60|	30|
|4	|80|	40|

가장 긴 가로 길이와 세로 길이가 각각 80, 70이기 때문에 80(가로) x 70(세로) 크기의 지갑을 만들면 모든 명함들을 수납할 수 있습니다. 하지만 2번 명함을 가로로 눕혀 수납한다면 80(가로) x 50(세로) 크기의 지갑으로 모든 명함들을 수납할 수 있습니다. 이때의 지갑 크기는 4000(=80 x 50)입니다.

모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어집니다. 모든 명함을 수납할 수 있는 가장 작은 지갑을 만들 때, 지갑의 크기를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- sizes의 길이는 1 이상 10,000 이하입니다.
  - sizes의 원소는 [w, h] 형식입니다.
  - w는 명함의 가로 길이를 나타냅니다.
  - h는 명함의 세로 길이를 나타냅니다.
  - w와 h는 1 이상 1,000 이하인 자연수입니다.

## 입출력 예

- 입력값: 	[[60, 50], [30, 70], [60, 30], [80, 40]]
  - 기댓값: 4000

- 입력값: 	[[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]
  - 기댓값: 120

- 입력값: 	[[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]
  - 기댓값: 133

| sizes                                         | 	result |
|-----------------------------------------------|---------|
 | [[60, 50], [30, 70], [60, 30], [80, 40]]      | 	4000   |
 | [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]] | 	120    |
 | [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]] | 	133    |

## 입출력 예 설명

#### 입출력 예 #1
문제 예시와 같습니다.

#### 입출력 예 #2
명함들을 적절히 회전시켜 겹쳤을 때, 3번째 명함(가로: 8, 세로: 15)이 다른 모든 명함보다 크기가 큽니다. 따라서 지갑의 크기는 3번째 명함의 크기와 같으며, 120(=8 x 15)을 return 합니다.

#### 입출력 예 #3
명함들을 적절히 회전시켜 겹쳤을 때, 모든 명함을 포함하는 가장 작은 지갑의 크기는 133(=19 x 7)입니다.

## 나의 코드

```java
class Solution {
  public int solution(int[][] sizes) {
    int answer = 0;
    for(int i = 0; i < sizes.length; i++) {
      if(sizes[i][1] > sizes[i][0]) {
        int temp = sizes[i][1];
        sizes[i][1] = sizes[i][0];
        sizes[i][0] = temp;
      }
    }
    int maxIndex = 0;
    int diffColMaxIndex = 0;
    for(int i = 0; i < sizes.length-1; i++) {
      if(sizes[i][0] < sizes[i+1][0]) {
        maxIndex = i + 1;
      }
      if(sizes[i][1] < sizes[i+1][1]) {
        diffColMaxIndex = i + 1;
      }
    }

    answer = sizes[maxIndex][0] * sizes[diffColMaxIndex][1];


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

    System.out.println(solution.solution(sizes));
  }
}
``` 
대소 비교할 때 이러한 이상한 짓은 하지말자
```java
        for(int i = 0; i < sizes.length-1; i++) {
        if(sizes[i][0] < sizes[i+1][0]) {
        maxIndex = i + 1;
        }
        if(sizes[i][1] < sizes[i+1][1]) {
        diffColMaxIndex = i + 1;
        }
        }
```
최대값을 그대로 계속 가져가야되는 것인데 저렇게 하면 앞 뒤 숫자만 계속 비교하는 것이 아닌가

```java
class Solution {
  public int solution(int[][] sizes) {
    int answer = 0;
    for(int i = 0; i < sizes.length; i++) {
      if(sizes[i][1] > sizes[i][0]) {
        int temp = sizes[i][1];
        sizes[i][1] = sizes[i][0];
        sizes[i][0] = temp;
      }
    }
    int maxIndex = 0;
    int diffColMaxIndex = 0;
    for(int i = 1; i < sizes.length; i++) {
      if(sizes[maxIndex][0] < sizes[i][0]) {
        maxIndex = i;
      }
      if(sizes[diffColMaxIndex][1] < sizes[i][1]) {
        diffColMaxIndex = i;
      }
    }

    answer = sizes[maxIndex][0] * sizes[diffColMaxIndex][1];


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

    System.out.println(solution.solution(sizes));
  }
}
```

## 코드 리뷰 

그렇게 어려운 것은 없었다.

다만 도중에 **큰 값이 있는 Index**를 뽑아내는 과정에서 저러한 짓을 했다는 것을 반성하고 충분한 복습이 필요할 것 같다.

다른 사람 문제 풀이를 보니깐 불필요한 메모리를 많이 사용한 것 과 불필요한 작업이 들어간 것 같다. 그 부분을 짚고 넘어가 볼 필요가 있어 보인다.

## 본 받을 만한 풀이

```java
class Solution {
    public int solution(int[][] sizes) {
        int max = 0;
        int min = 0;
        for (int[] size : sizes) {
            int paramMax = Math.max(size[0], size[1]);
            int paramMin = Math.min(size[0], size[1]);

            if (paramMax > max) {
                max = paramMax;
            }

            if (paramMin > min) {
                min = paramMin;
            }
        }
        return max * min;
    }
}

```

```java
class Solution {
    public int solution(int[][] sizes) {
        int length = 0, height = 0;
        for (int[] card : sizes) {
            length = Math.max(length, Math.max(card[0], card[1]));
            height = Math.max(height, Math.min(card[0], card[1]));
        }
        int answer = length * height;
        return answer;
    }
}

```

해당 문제에서 요구하는 것은 각 행마다 큰 값 끼리 비교하고 작은 값 들 중에서 큰 값을 뽑아내는 것이 관건이다.

이 부분을 잘 표현한 코드라고 생각한다.