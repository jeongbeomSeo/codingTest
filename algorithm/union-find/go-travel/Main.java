import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    int[] parent = new int[N];
    for (int i = 0; i < N; i++) parent[i] = i;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int n = Integer.parseInt(st.nextToken());
        if (n == 1) {
          union_merge(parent, i, j);
        }
      }
    }

    boolean success = true;
    st = new StringTokenizer(br.readLine());
    int target = union_find(parent, Integer.parseInt(st.nextToken()) - 1);
    while (st.hasMoreTokens()) {
      if (target != union_find(parent, Integer.parseInt(st.nextToken()) - 1)) {
        success = false;
      }
    }
    if (success) System.out.println("YES");
    else System.out.println("NO");
  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static void union_merge(int[] parent, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x != y) parent[x] = y;
  }
}

