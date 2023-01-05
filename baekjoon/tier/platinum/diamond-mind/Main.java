import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
  static int R,C;
  static int[] xDirection = {-1, 1, 1, -1};
  static int[] yDirection = {1, 1, -1, -1};
  static int[][] mine;
  static int max;
  static boolean success;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // Row, Column
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    // 나올 수 있는 가장 큰 다이아몬드의 크기
    max = Math.min((R + 1) / 2,(C + 1) / 2);

    // 배열 초기화
    mine = new int[R][C];

    // 배열 내용 입력 받기
    for(int i = 0; i < R; i++) {
      int[] nums = Stream.of(br.readLine().split(""))
              .mapToInt(Integer::parseInt)
              .toArray();

     mine[i] = nums;
    }
    int size = 0;
    for(int y = 0; y < mine.length; y++) {
      for(int x = 0; x < mine[y].length; x++) {
        if(mine[y][x] == 1) {
          int[] dest = {x, y};
          success = false;
          size = Math.max(diamondSizeCheck(1, x, y, 0, dest), size);
          if(size == max) {
            System.out.println(size);
            return;
          }
        }
      }
    }
    System.out.println(size);
  }


  static int diamondSizeCheck(int count, int x, int y, int ptr, int[] dest) {

    if (success) return count;

    for(int i = 0; i < 2; i++) {
      x += xDirection[ptr];
      y += yDirection[ptr];

      // 배열에서 벗어나지 않는지 Check
      if(ptrCheck(y, x)) {
        // 1인지 check
        if(mine[y][x] == 1) {

          // 최종 목적지 도달
          if(x == dest[0] && y == dest[1] && ptr == 4) {
            success = true;
            return count;
          }

          // 처음 진행하는 방향인 경우
          if(ptr == 1) {
            diamondSizeCheck(count + 1, x, y, ptr, dest);
          }
          // 방향 바뀐 경우 -> size 고정
          else diamondSizeCheck(count, x, y, ptr, dest);

          // 방향 틀어주기
          if(ptr < 4) ptr++;
        }
        else break;
      }
      // 배열에서 벗어난 경우
      else break;
    }

    return count;
  }

  static boolean ptrCheck(int y, int x) {
    if(y < 0 || y > R - 1 || x < 0 || x > C - 1) return false;
    else return true;
  }
}
