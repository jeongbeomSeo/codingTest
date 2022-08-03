# Crane Puppet Game

## 문제 설명

게임개발자인 "죠르디"는 크레인 인형뽑기 기계를 모바일 게임으로 만들려고 합니다.
"죠르디"는 게임의 재미를 높이기 위해 화면 구성과 규칙을 다음과 같이 게임 로직에 반영하려고 합니다.

![crane_game_101](crane_game_101.png)

게임 화면은 **"1 x 1"** 크기의 칸들로 이루어진 **"N x N"** 크기의 정사각 격자이며 위쪽에는 크레인이 있고 오른쪽에는 바구니가 있습니다. 
(위 그림은 "5 x 5" 크기의 예시입니다). 각 격자 칸에는 다양한 인형이 들어 있으며 인형이 없는 칸은 빈칸입니다. 
모든 인형은 "1 x 1" 크기의 격자 한 칸을 차지하며 **격자의 가장 아래 칸부터 차곡차곡 쌓여 있습니다.** 게임 사용자는 크레인을 좌우로 움직여서 멈춘 위치에서 가장 위에 있는 인형을 집어 올릴 수 있습니다. 집어 올린 인형은 바구니에 쌓이게 되는 데, 이때 바구니의 가장 아래 칸부터 인형이 순서대로 쌓이게 됩니다. 다음 그림은 [1번, 5번, 3번] 위치에서 순서대로 인형을 집어 올려 바구니에 담은 모습입니다.

![crane_game_102](crane_game_102.png)

만약 같은 모양의 인형 두 개가 바구니에 연속해서 쌓이게 되면 두 인형은 터뜨려지면서 바구니에서 사라지게 됩니다. 위 상태에서 이어서 [5번] 위치에서 인형을 집어 바구니에 쌓으면 같은 모양 인형 두 개가 없어집니다.

![crane_game_103])(crane_game_103.gif)

크레인 작동 시 인형이 집어지지 않는 경우는 없으나 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않습니다. 또한 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다고 가정합니다. (그림에서는 화면표시 제약으로 5칸만으로 표현하였음)

게임 화면의 격자의 상태가 담긴 2차원 배열 board와 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때, 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- board 배열은 2차원 배열로 크기는 "5 x 5" 이상 "30 x 30" 이하입니다.
- board의 각 칸에는 0 이상 100 이하인 정수가 담겨있습니다.
  - 0은 빈 칸을 나타냅니다.
  - 1 ~ 100의 각 숫자는 각기 다른 인형의 모양을 의미하며 같은 숫자는 같은 모양의 인형을 나타냅니다.
- moves 배열의 크기는 1 이상 1,000 이하입니다.
- moves 배열 각 원소들의 값은 1 이상이며 board 배열의 가로 크기 이하인 자연수입니다.

## 입출력 예

|board| moves             |result|
|---------|-------------------|---|
|[[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]| [1,5,3,5,1,2,1,4] | 4     |

1. 입력값: [[0, 0, 0, 0, 0], [0, 0, 1, 0, 3], [0, 2, 5, 0, 1], [4, 2, 4, 4, 2], [3, 5, 1, 3, 1]], [1, 5, 3, 5, 1, 2, 1, 4]
   - 기댓값: 	4

## 입출력 예에 대한 설명

#### 입출력 예 #1

인형의 처음 상태는 문제에 주어진 예시와 같습니다. 크레인이 [1, 5, 3, 5, 1, 2, 1, 4] 번 위치에서 차례대로 인형을 집어서 바구니에 옮겨 담은 후, 상태는 아래 그림과 같으며 바구니에 담는 과정에서 터트려져 사라진 인형은 4개 입니다.

![crane_game_104](crane_game_104.jpg)

## 필요 개념

- ArrayList를 사용할 때 
  - 원소의 갯수를 가져오는 size()
  - 특정 Index의 Element value를 가져와 주는 get()
  - 특정 Index의 Element를 삭제해주는 remove()

## 참고한 사이트

- ArrayList size()
  - https://dev-bear.tistory.com/entry/JAVA-ArrayList
- ArrayList get()
  - https://codechacha.com/ko/java-collections-arraylist-get/

## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public int solution(int[][] board, int[] moves) {
    int answer = 0;

    ArrayList<Integer> bucket = new ArrayList<Integer>();

    for(int column : moves) {
      // moves의 배열에는 1부터 시작하는 column의 숫자들로 들어가 있다.
      column -= 1;
      int i = 0;
      while(board[i][column] == 0) {
        i += 1;
        // 해당 column이 다 비어 있는 경우도 처리해야 한다. continue가 아닌 break를 써야하는 것에 주의
        if (i == board.length) {
          i += 1;
          break;
        }
      }
      if(i > board.length) continue;
      // bucket이 비어있는 상황에서 아래 코드들을 실행하면 오류가 나오기 때문에 바로 넣어주고 다음 반복으로 넘기기
      if(bucket.size() == 0) {
        bucket.add(board[i][column]);
        board[i][column] = 0;
        continue;
      }
      // 사라지는 인형의 갯수는 두개씩 사라지니깐 주의.
      if(bucket.get(bucket.size() - 1) == board[i][column]) {
        //ArrayList.get이나 remove 경우 index를 받는데 size()는 Array안에 있는 원소의 갯수를 가져와준다. 그래서 -1을 해줘야 마지막 Index를 가져올 수 있다.
        bucket.remove(bucket.size() - 1);
        answer += 2;
      }
      else bucket.add(board[i][column]);

      board[i][column] = 0;
    }

    return answer;
  }

  public static void main(String args[]) {
    Solution solution = new Solution();
    int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
    int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

    System.out.println(solution.solution(board, moves));
  }
}
```

## 본받아야 될 코드

```java
import java.util.Stack;

class Solution {
  public int solution(int[][] board, int[] moves) {
    int answer = 0;
    Stack<Integer> stack = new Stack<>();
    for (int move : moves) {
      for (int j = 0; j < board.length; j++) {
        if (board[j][move - 1] != 0) {
          if (stack.isEmpty()) {
            stack.push(board[j][move - 1]);
            board[j][move - 1] = 0;
            break;
          }
          if (board[j][move - 1] == stack.peek()) {
            stack.pop();
            answer += 2;
          } else
            stack.push(board[j][move - 1]);
          board[j][move - 1] = 0;
          break;
        }
      }
    }
    return answer;
  }
}
```