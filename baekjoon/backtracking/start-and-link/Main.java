import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] statusTable;
  static boolean[] flag;
  static int subMin = Integer.MAX_VALUE;
  static int[] team1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // N명 (짝수)
    N = Integer.parseInt(br.readLine());

    // 1 ~ N까지 Table로 활용
    statusTable = new int[N + 1][N + 1];

    for(int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j < N + 1; j++) {
        int num = Integer.parseInt(st.nextToken());
        statusTable[i][j] = num;
      }
    }

    // Team1에 1번 선수는 1번 팀 Fix로 고정
    flag = new boolean[N + 1];
    flag[1] = true;

    team1 = new int[N / 2];
    team1[0] = 1;

    teamUp(1, 0);

    System.out.println(subMin);
  }
  static void teamUp(int ptr, int team1Score) {
    for(int i = 2; i < N + 1; i++) {
      if(!flag[i]) {
        if(team1[ptr - 1] < i) {
          int sum = 0;
          team1[ptr] = i;
          flag[i] = true;

          //team1 score 재계산 (현재 추가되는 팀원과 기존 팀원들과의 능력치 합계)
          for(int j = 0; j < ptr; j++) {
            sum += statusTable[team1[j]][i] + statusTable[i][team1[j]];
          }

          if(ptr == (N / 2) - 1) {
            calcStatus(team1Score + sum);
          }
          else {
            teamUp(ptr + 1, team1Score + sum);
          }
          flag[i] = false;
        }
      }
    }
  }
  static void calcStatus(int team1Score) {
    int[] team2 = new int[N / 2];

    int idx = 0;
    for(int i = 2; i < flag.length; i++) {
      if(!flag[i]) team2[idx++] = i;
    }

    int team2Score = 0;
    for(int i = 0; i < team2.length - 1; i++) {
      for(int j = i + 1; j < team2.length; j++) {
        team2Score += statusTable[team2[i]][team2[j]] + statusTable[team2[j]][team2[i]];
      }
    }

    subMin = Math.min(Math.abs(team1Score - team2Score) , subMin);
  }
}
