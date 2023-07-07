import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int MAX = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dices = new int[10];
    for (int i = 0; i < 10; i++) {
      dices[i] = Integer.parseInt(st.nextToken());
    }

    int[] map = initMap();
    int[] shortMap = new int[4];
    shortMap[0] = 1; shortMap[1] = 21; shortMap[2] = 30; shortMap[3] = 27;
    boolean[] isUsing = new boolean[33];
    int[] points = initPoint();

    int[] horses = new int[4];

    dfs(horses, map, shortMap, points, dices, 0, 0, isUsing);

    System.out.println(MAX);
  }
  static void dfs(int[] horses, int[] map, int[] shortMap, int[] points, int[] dices, int ptr, int score, boolean[] isUsing) {
    if (ptr == 10) {
      MAX = Math.max(MAX, score);
    }
    else {
      int diceNum = dices[ptr];
      for (int i = 0; i < 4; i++) {
        if (horses[i] == -1) continue;

        int originPoint = horses[i];
        isUsing[horses[i]] = false;
        for (int j = 0; j < diceNum; j++) {
          if (j == 0 && horses[i] % 5 == 0 && horses[i] < 20) {
            horses[i] = shortMap[horses[i] / 5];
          }
          else horses[i] = map[horses[i]];

          if (horses[i] == -1) break;
        }

        if (horses[i] != -1) {
          if (isUsing[horses[i]]) {
            horses[i] = originPoint;
            isUsing[horses[i]] = true;
            continue;
          }
          else isUsing[horses[i]] = true;
        }

        if (horses[i] != -1) dfs(horses, map, shortMap, points, dices, ptr + 1, score + points[horses[i]], isUsing);
        else dfs(horses, map, shortMap, points, dices, ptr + 1, score, isUsing);

        if (horses[i] != -1) isUsing[horses[i]] = false;
        horses[i] = originPoint;
        isUsing[horses[i]] = true;
      }
    }
  }
  static int[] initPoint() {
    int[] points = new int[33];

    for (int i = 1; i <= 20; i++) {
      points[i] = 2 * i;
    }

    points[21] = 13; points[22] = 16; points[23] = 19; points[24] = 25;
    points[25] = 30; points[26] = 35; points[27] = 28; points[28] = 27;
    points[29] = 26; points[30] = 22; points[31] = 24;

    return points;
  }
  static int[] initMap() {
    int[] map = new int[33];

    for (int i = 0; i < 20; i++) {
      map[i] = i + 1;
    }

    map[20] = -1; map[21] = 22; map[22] = 23; map[23] = 24; map[24] = 25; map[25] = 26;
    map[26] = 20; map[27] = 28; map[28] = 29; map[29] = 24; map[30] = 31; map[31] = 24;

    return map;
  }
}
