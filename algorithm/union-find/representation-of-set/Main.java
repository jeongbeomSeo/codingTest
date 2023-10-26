import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] parent = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }

    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int query = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (query == 0) union_merge(parent, a, b);
      else {
        if (a == b) {
          System.out.println("YES");
          continue;
        }
        a = union_find(parent, a);
        b = union_find(parent, b);
        if (a == b) System.out.println("YES");
        else System.out.println("NO");
      }
    }

  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;
    return parent[x] = union_find(parent, parent[x]);
  }
  static void union_merge(int[] parent, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x != y) {
      if (x < y) parent[y] = x;
      else parent[x] = y;
    }
  }
}
