import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int L, C;
  static char[] arr;
  static char[] chars;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    arr = new char[C];
    chars = new char[L];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    Arrays.sort(arr);

    backTracking(0, 0);

  }
  static void backTracking(int x, int s) {

    if (s == L) {
      if (isValid()) {
        System.out.println(chars);
      }
    }

    else {
      if (x != C) {
        chars[s] = arr[x];
        backTracking(x + 1, s + 1);
        backTracking(x + 1, s);
      }
    }
  }
  static boolean isValid() {
    int j = 0;
    int m = 0;

    for (int i = 0; i < L; i++) {
      char c = chars[i];
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') m++;
      else j++;
    }

    return (m >= 1 && j >= 2);
  }
}