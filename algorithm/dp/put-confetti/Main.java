import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Confetti[] confettis = new Confetti[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      confettis[i] = new Confetti(n1, n2);
    }

    Arrays.sort(confettis);

    System.out.println(activeDp(confettis, N));
  }
  private static int activeDp(Confetti[] confettis, int N) {

    int[] table = new int[N];

    int size = 0;
    table[size++] = confettis[0].col;

    for (int i = 1; i < N; i++) {
      if (table[size - 1] <= confettis[i].col) {
        table[size] = confettis[i].col;
        size++;
      } else if (table[size - 1] > confettis[i].col) {
        int idx = upper_bound(table, 0, size, confettis[i].col);

        table[idx] = confettis[i].col;
      }
    }

    return size;
  }
  private static int upper_bound(int[] array, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (array[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return right;
  }
  private static int lower_bound(int[] array, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
}
class Confetti implements Comparable<Confetti>{
  int row;
  int col;

  Confetti(int a, int b) {
    if (a > b) {
      this.row = a;
      this.col = b;
    } else {
      this.row = b;
      this.col = a;
    }
  }

  @Override
  public int compareTo(Confetti o) {
    if (this.row - o.row != 0) return this.row - o.row;
    else return this.col - o.col;
  }
}