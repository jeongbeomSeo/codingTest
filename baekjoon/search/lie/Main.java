import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    st = new StringTokenizer(br.readLine());
    int knowing_number = Integer.parseInt(st.nextToken());
    boolean[] know = new boolean[N + 1];
    for (int i = 0; i < knowing_number; i++) {
      know[Integer.parseInt(st.nextToken())] = true;
    }

    ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      party.add(new ArrayList());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      int baseNumber = Integer.parseInt(st.nextToken());
      party.get(i).add(baseNumber);
      for (int j = 1; j < size; j++) {
        int num = Integer.parseInt(st.nextToken());
        union(parent, know, baseNumber, num);
        party.get(i).add(num);
      }
    }

    int count = 0;
    for (int i = 0; i < party.size(); i++) {
      int j;
      int size = party.get(i).size();
      for (j = 0; j < size; j++) {
        if (know[find(parent, party.get(i).get(j))]) break;
      }
      if (j == size) count++;
    }
    System.out.println(count);
  }
  static int find(int[] parent, int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent, parent[x]);
  }
  static void union(int[] parent, boolean[] know, int x, int y) {
    x = find(parent, x);
    y = find(parent, y);

    if (x != y) {
      if (know[x]) parent[y] = x;
      else parent[x] = y;
    }
  }
}