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