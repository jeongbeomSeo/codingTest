import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시물레이션 문제
 * 1. 궁수 3명 배치 => 조합
 * 2. 시물레이션 시작 (턴제 방식)
 * 3. 궁수들 적 공격 (동시 공격)
 * 4. 적 이동
 * 5. 모든 턴 마무리, 제거한 적의 수 체크
 * 6. 1번 반복
 *
 * 해당 문제에서 주의할 점
 * - 턴제 방식 시물레이션에서 중요한 점은 원래 배치(Origin)는 저장해두고 다른 것에 복사(clone)하든가 해야 한다.
 * - 동시 공격 처리 주의
 */
public class Main{
  static int[] ax = {-1, 0, 1};
  static int[] ay = {0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int[][] origin = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        origin[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(origin, N, M ,D));
  }

  static int simulation(int[][] origin, int N, int M, int D) {

    // 최댓값
    int MAX_KILL = 0;

    // 궁수 배치 => 조합 (ArrayList<Integer[]> 이용)
    ArrayList<Integer[]> arrowList = new ArrayList<>();
    Integer[] arrows = new Integer[3];
    combination(M, 0, arrows, 0, arrowList);
    // 시물레이션 시작
    for (Integer[] arrow : arrowList) {
      // 죽인 적
      int killCount = 0;
      // 격자판 생성
      int[][] grid = new int[N][M];
      for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
          grid[i][j] = origin[i][j];

      // 턴제 방식 시작
      for (int turn = 1; turn <= N; turn++) {

        // 궁수 3명 적 공격 (bfs 탐색)
        for (int i = 0; i < 3; i++) {
          if(shot_bfs(grid, N, M, arrow[i], D)) killCount++;
        }


        // 적 처리 후 이동
        // 마지막 줄은 전부 0으로 초기화
        for (int j = 0; j < M; j++) {
          grid[N - 1][j] = 0;
        }

        // 0 ~ N - 2번째 줄은 적이 살아있으면 아래로 한 칸 내려주고 나머지는 전부 0으로 초기화
        // 주의할 점은 아래서부터 진행해야 덮어씌워지지 않는다.
        boolean change = false;
        for (int i = N - 2; i >= 0; i--) {
          for (int j = 0; j < M; j++) {
            if (grid[i][j] == 1) {
              grid[i + 1][j] = 1;
              change = true;
            }
            grid[i][j] = 0;
          }
        }
        if (!change) break;
      }

      MAX_KILL = Math.max(MAX_KILL, killCount);
    }
    return MAX_KILL;
  }

  static void combination(int M, int ptr, Integer[] arrows, int size, ArrayList<Integer[]> arrowList) {

    if (size == 3) {
      arrowList.add(new Integer[]{arrows[0], arrows[1], arrows[2]});
      return;
    }

    else {
      if (ptr < M)  {
        arrows[size] = ptr;
        combination(M, ptr + 1 , arrows, size + 1, arrowList);
        arrows[size] = 0;

        combination(M, ptr + 1, arrows, size, arrowList);
      }
    }
  }

  // BFS 로 탐색 우선순위 (왼쪽 -> 위 -> 오른쪽)
  // 궁수의 처음 위치는 (N - 1, arrow)
  // 노드 이용해서 x, y 좌표 + 이동 횟수 넣어주기
  static boolean shot_bfs(int[][] grid, int N, int M, int arrow, int D) {

    boolean[][] isVisited = new boolean[N][M];

    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(arrow, N - 1));
    isVisited[N - 1][arrow] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      if (curNode.d > D) continue;
      else if (grid[curNode.y][curNode.x] == 1) {
        grid[curNode.y][curNode.x] = -1;
        return true;
      }
      else if (grid[curNode.y][curNode.x] == -1) return false;

      for (int i = 0; i < 3; i++) {
        int curX = curNode.x + ax[i];
        int curY = curNode.y + ay[i];

        if (validationIdx(curX, curY, N, M) && !isVisited[curY][curX]) {
          q.add(new Node(curX, curY, curNode.d + 1));
          isVisited[curY][curX] = true;
        }
      }
    }
    return false;
  }

  static boolean validationIdx(int x, int y, int N, int M) {
    if (x < 0 || y < 0 || x > M - 1 || y > N - 1) return false;
    return true;
  }

}

class Node {
  int x;
  int y;
  int d;

  Node(int x, int y) {
    this.x = x;
    this.y = y;
    d = 1;
  }

  Node(int x, int y, int d) {
    this(x, y);
    this.d = d;
  }
}