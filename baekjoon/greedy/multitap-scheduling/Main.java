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

    int[] tap = new int[N];
    int size = 0;
    boolean[] isUsing = new boolean[K + 1];

    int[] items = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      items[i] = Integer.parseInt(st.nextToken());
    }

    int count = 0;
    for (int i = 0; i < K; i++) {
      int item = items[i];
      if (isUsing[item]) continue;

      if (size < N) tap[size++] = item;
      else {
        int nextUsingCount = 0;
        int[] nextUsingIdx = new int[N];
        for (int j = i + 1; j < K; j++) {
          if (isUsing[items[j]]) {
            for (int k = 0; k < N; k++) {
              if (tap[k] == items[j]) {
                if (nextUsingIdx[k] != 0) break;
                nextUsingIdx[k] = j;
                nextUsingCount++;
                break;
              }
            }
          }
        }

        if (nextUsingCount != size) {
          for (int j = 0; j < N; j++) {
            if (nextUsingIdx[j] == 0) {
              isUsing[tap[j]] = false;
              tap[j] = item;
              break;
            }
          }
        }
        else {
          int maxIdx = 0;
          for (int j = 1; j < N; j++) {
            if (nextUsingIdx[j] > nextUsingIdx[maxIdx]) maxIdx = j;
          }
          isUsing[tap[maxIdx]] = false;
          tap[maxIdx] = item;
        }

        count++;
      }
      isUsing[item] = true;
    }
    System.out.println(count);
  }
}