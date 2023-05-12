import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] products = new int[K];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      products[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(greedy(products, N, K));

  }
  static int greedy(int[] products, int N, int K) {

    int count = 0;
    int[] tap = new int[N];
    boolean[] using = new boolean[K + 1];
    int size = 0;

    for (int i = 0; i < K; i++) {
      if (!using[products[i]]) {
        if (size < N) {
          tap[size++] = products[i];
        }
        else {
          boolean[] isNextUse = new boolean[N];
          int nextUseCount = 0;
          int maxIdx = 0;
          for (int j = i + 1; j < K; j++) {
            if (using[products[j]]) {
              for (int k = 0; k < N; k++) {
                if (tap[k] == products[j]) {
                  if (isNextUse[k]) break;
                  maxIdx = k;
                  isNextUse[k] = true;
                  nextUseCount++;
                  break;
                }
              }
            }
          }
          if (nextUseCount == N) {
            using[tap[maxIdx]] = false;
            tap[maxIdx] = products[i];
            count++;
          }
          else {
            for (int j = 0; j < N; j++) {
              if (!isNextUse[j]) {
                using[tap[j]] = false;
                tap[j] = products[i];
                count++;
                break;
              }
            }
          }
        }
        using[products[i]] = true;
      }
    }
    return count;
  }
}
