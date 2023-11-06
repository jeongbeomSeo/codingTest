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

    int[] parentArray = unionArray(N);

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < N; j++) {
        if (Integer.parseInt(st.nextToken()) == 1) {
          connect(parentArray, i, j);
        }
      }
    }

    st = new StringTokenizer(br.readLine());

    int[] list = new int[M];
    for (int i = 0; i < M; i++) {
      list[i] = Integer.parseInt(st.nextToken()) - 1;
    }

    int parent = getParent(parentArray, list[0]);
    int j;
    for (j = 1; j < M; j++) {
      if (parent != getParent(parentArray, list[j])) {
        System.out.println("NO");
        break;
      }
    }
    if (j == M) {
      System.out.println("YES");
    }

  }
  private static int getParent(int[] parentArray, int child) {
    if (parentArray[child] == child) return child;

    return parentArray[child] = getParent(parentArray, parentArray[child]);
  }
  private static void connect(int[] parentArray, int child1, int child2) {
    int parent1 = getParent(parentArray, child1);
    int parent2 = getParent(parentArray, child2);

    if (parent1 != parent2) {
      if (parent1 > parent2) {
        parentArray[parent1] = parent2;
      } else {
        parentArray[parent2] = parent1;
      }
    }
  }
  private static int[] unionArray(int N) {
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = i;
    }

    return array;
  }
}