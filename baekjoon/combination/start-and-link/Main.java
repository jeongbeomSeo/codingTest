import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int min = Integer.MAX_VALUE;
  static int[][] points;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    points = new int[N][N];
    int[] team = new int[N / 2];
    team[0] = 0;
    int[] otherTeam = new int[N / 2];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        points[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    halfCombination(team, otherTeam, 1, 1, 0);

    System.out.println(min);
  }
  static void halfCombination(int[] team, int[] otherTeam, int ptr, int sizeM, int sizeOM) {

    if (ptr == N) {
      int diff = diffPoint(team, otherTeam);
      min = Math.min(min, diff);
    }

    else {
      if (sizeM != N / 2) {
        team[sizeM] = ptr;
        halfCombination(team, otherTeam, ptr + 1, sizeM + 1, sizeOM);
      }

      if (sizeOM != N / 2) {
        otherTeam[sizeOM] = ptr;
        halfCombination(team, otherTeam, ptr + 1, sizeM, sizeOM + 1);
      }
    }
  }

  static int diffPoint(int[] team, int[] otherTeam) {

    int diff = 0;
    for (int i = 0; i < N / 2 - 1; i++) {
      for (int j = i + 1; j < N / 2; j++)
        diff += (points[team[i]][team[j]] + points[team[j]][team[i]])
              - (points[otherTeam[i]][otherTeam[j]] + points[otherTeam[j]][otherTeam[i]]);

    }

    return Math.abs(diff);
  }
}
