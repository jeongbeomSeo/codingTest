import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] gear = new int[4][8];

    for (int i = 0; i < 4; i++) {
      String str = br.readLine();
      for (int j = 0; j < 8; j++) {
        int num = str.charAt(j) - '0';
        gear[i][j] = num;
      }
    }

    int K = Integer.parseInt(br.readLine());

    int[][] rotate = new int[K][2];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      rotate[i][0] = Integer.parseInt(st.nextToken()) - 1;
      rotate[i][1] = Integer.parseInt(st.nextToken());
    }

    simulation(gear, rotate, K);
    int sum = 0;
    for (int i = 0; i < 4; i++) {
      if (gear[i][0] == 1) {
        if (i == 0) sum += 1;
        else if (i == 1) sum += 2;
        else if (i == 2) sum += 4;
        else sum += 8;
      }
    }
    System.out.println(sum);
  }
  static void simulation(int[][] gear, int[][] rotate, int K) {

    for (int cc = 0; cc < K; cc++) {
      int gearPtr = rotate[cc][0];
      int direction = rotate[cc][1];

      boolean[] isMoveGear = new boolean[4];
      isMoveGear[gearPtr] = true;
      for (int i = gearPtr + 1; i < 4; i++) {
        if (gear[i - 1][2] != gear[i][6] && isMoveGear[i - 1]) isMoveGear[i] = true;
      }
      for (int i = gearPtr - 1; i >= 0; i--) {
        if (gear[i][2] != gear[i + 1][6] && isMoveGear[i + 1]) isMoveGear[i] = true;
      }

      for (int i = 0; i < 4; i++) {
        if (i == 0) moveGear(gear[gearPtr], direction);
        else {
          if (gearPtr + i < 4 && isMoveGear[gearPtr + i]) moveGear(gear[gearPtr + i], direction * (i % 2 != 0 ? -1 : 1));
          if (gearPtr - i >= 0 && isMoveGear[gearPtr - i]) moveGear(gear[gearPtr - i], direction * (i % 2 != 0 ? -1 : 1));
        }
      }
    }
  }
  static void moveGear(int[] gear, int direction) {
    if (direction == 1) {
      int temp = gear[7];
      for (int i = 7; i > 0; i--) {
        gear[i] = gear[i - 1];
      }
      gear[0] = temp;
    }
    else {
      int temp = gear[0];
      for (int i = 0; i < 7; i++) {
        gear[i] = gear[i + 1];
      }
      gear[7] = temp;
    }
  }
}