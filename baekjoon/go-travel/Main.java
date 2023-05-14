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

    int[] parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int num = Integer.parseInt(st.nextToken());
        if (i == j) continue;
        if (num == 1) union_merge(parent, i + 1, j + 1);
      }
    }

    st = new StringTokenizer(br.readLine());
    int target_parent = parent[Integer.parseInt(st.nextToken())];
    int i = 1;
    for (; i < M; i++) {
      if (target_parent != parent[Integer.parseInt(st.nextToken())]) break;
    }
    if (i == M) System.out.println("YES");
    else System.out.println("NO");

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