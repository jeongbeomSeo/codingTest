import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {-1, 0, 1};
  static int[] ay = {0, -1, 0};
  static boolean shot = false;
  static int N, M, D;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    int[][] enemy = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        enemy[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    defenceStart(enemy);
    System.out.println(max);

  }
  // 구현되어야 할 함수
  /*
  1. 궁수 배치
  2. 사냥 가능 범위 탐색, 체크 (동시 공격 => 죽은 적 -1로 체크)
  3. 사냥가능 적 횟수, 사냥당한 적 초기화
  4. 턴(총 5탄) 끝난후 모든 적 아래로 이동
   */

  static void defenceStart(int[][] enemy) {
    ArrayList<Integer[]> archerCase = new ArrayList<>();
    setArcher(archerCase);


    for (int tc = 0; tc < archerCase.size(); tc++) {
      int count = 0;
      int arrowOne = archerCase.get(tc)[0];
      int arrowTwo = archerCase.get(tc)[1];
      int arrowThree = archerCase.get(tc)[2];
      int[] arrow = new int[]{arrowOne, arrowTwo, arrowThree};

      int[][] thisTurnEnemy = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M ;j++) {
          thisTurnEnemy[i][j] = enemy[i][j];
        }
      }

      for (int turn = 0; turn < N; turn++) {
        for (int i = 0; i < 3; i++) {
          if (enemyShot(thisTurnEnemy, N - 1, arrow[i])) {
            count++;
          }
        }
        /*
        // 사냥한 후 상태 체크
        System.out.println(tc+"에서의 "+ turn+"번째 사냥한 후");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(thisTurnEnemy[i][j]+" ");
          }
          System.out.println();
        }
        System.out.println();
       */

        // 마지막 라인 0으로 초기화
        for (int i = 0; i < M; i++) {
          thisTurnEnemy[N - 1][i] = 0;
        }

        // 마지막 라인제외하고 전부 내려주기
        boolean noEnemy = true;
        for (int i = N - 2; i >= 0 ; i--) {
          for (int j = 0; j < M; j++) {
            if (thisTurnEnemy[i][j] == -1) thisTurnEnemy[i][j] = 0;
            if (thisTurnEnemy[i][j] == 1) {
              thisTurnEnemy[i + 1][j] = 1;
              thisTurnEnemy[i][j] = 0;
              noEnemy = false;
            }
          }
        }
        if (noEnemy) break;
        /*
        System.out.println(turn+"번째 뒷 처리후 상태");
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            System.out.print(thisTurnEnemy[i][j]+" ");
          }
          System.out.println();
        }
        System.out.println("현재 사냥 횟수: "+count);
        */
      }

      max = Math.max(max, count);
    }
  }

  static void setArcher(ArrayList<Integer[]> archerCase) {
    for (int i = 0; i < M; i++) {
      for (int j = i + 1; j < M; j++) {
        for (int k = j + 1; k < M; k++) {
          archerCase.add(new Integer[]{i, j, k});
        }
      }
    }
  }

  static boolean enemyShot(int[][] enemy, int i, int j) {

    Queue<Integer[]> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N][M];

    q.add(new Integer[]{i, j, 1});
    isVisited[i][j] = true;

    while (!q.isEmpty()) {
      Integer[] curState = q.poll();
      int curPosI = curState[0];
      int curPosJ = curState[1];
      int curRange = curState[2];

      if (curRange > D) continue;
      if (enemy[curPosI][curPosJ] == 1)  {
        enemy[curPosI][curPosJ] = -1;
        return true;
      }
      if (enemy[curPosI][curPosJ] == -1) return false;

      for (int mv = 0; mv < 3; mv++) {
        int nextI = curPosI + ay[mv];
        int nextJ = curPosJ + ax[mv];
        if (checkIdx(nextI, nextJ) && !isVisited[nextI][nextJ]) {
          q.add(new Integer[]{nextI, nextJ, curRange + 1});
          isVisited[nextI][nextJ] = true;
        }
      }
    }

    return false;
  }
  static boolean checkIdx(int i, int j) {
    if (i < 0 || j < 0 || i > N - 1 || j > M - 1) return false;
    return true;
  }
}
