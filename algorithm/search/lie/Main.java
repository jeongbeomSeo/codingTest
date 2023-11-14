import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[] parent = initParentTable(N);

    st = new StringTokenizer(br.readLine());
    int truthSize = Integer.parseInt(st.nextToken());
    boolean[] hasTruth = new boolean[N + 1];
    for (int i = 0; i < truthSize; i++) {
      int idx = Integer.parseInt(st.nextToken());

      hasTruth[idx] = true;
    }

    ArrayList<Integer>[] party = new ArrayList[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      party[i] = new ArrayList<>();
      int partySize = Integer.parseInt(st.nextToken());

      int num1 = Integer.parseInt(st.nextToken());
      party[i].add(num1);

      for (int j = 0; j < partySize - 1; j++) {
        int num2 = Integer.parseInt(st.nextToken());
        party[i].add(num2);

        unionMerge(parent, hasTruth, num1, num2);
      }
    }

    int count = M;
    for (int i = 0; i < M; i++) {
      for (int idx : party[i]) {
        if (hasTruth[getParent(parent, idx)]) {
          count--;
          break;
        }
      }
    }

    System.out.println(count);
  }
  private static int getParent(int[] parent, int child) {
    if (parent[child] == child) return child;

    return parent[child] = getParent(parent, parent[child]);
  }
  private static void unionMerge(int[] parent, boolean[] hasTruth, int child1, int child2) {
    int parent1 = getParent(parent, child1);
    int parent2 = getParent(parent, child2);

    if (parent1 != parent2) {
      if (parent1 > parent2) {
        parent[parent1] = parent2;
      } else {
        parent[parent2] = parent1;
      }
      if (hasTruth[parent1] || hasTruth[parent2]) {
        hasTruth[parent1] = hasTruth[parent2] = true;
      }
    }
  }
  private static int[] initParentTable(int N) {

    int[] parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    return parent;
  }
}