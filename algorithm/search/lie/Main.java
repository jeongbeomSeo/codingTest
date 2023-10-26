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
    for (int i = 1; i <= N; i++) parent[i] = i;

    st = new StringTokenizer(br.readLine());
    int known = Integer.parseInt(st.nextToken());
    int[] knownList = new int[known];
    for (int i = 0; i < known; i++) {
      knownList[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    for (int i = 0; i < M; i++) party.add(new ArrayList<>());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());

      for (int j = 0; j < size; j++) {
        int curNumber = Integer.parseInt(st.nextToken());

        party.get(i).add(curNumber);
        for (int k = j - 1; k >= 0; k--) {
          int otherNumber = party.get(i).get(k);

          union_merge(parent, curNumber, otherNumber);
        }
      }
    }

    int count = 0;
    for (int i = 0; i < M; i++) {
      boolean success = true;
      for (int j = 0; j < party.get(i).size(); j++) {
        int checkNumber = union_find(parent, party.get(i).get(j));
        for (int k = 0; k < known; k++) {
          if (checkNumber == union_find(parent, knownList[k])) {
            success = false;
          }
        }
        if (!success) break;
      }
      if (success) count++;
    }

    System.out.println(count);
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