import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[] buffer = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      buffer[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(buffer));
  }
  private static int queryResult(int[] buffer) {

    int count = 0;

    int[] tap = new int[N];
    boolean[] isUsing = new boolean[K + 1];

    int tapSize = 0;
    for (int i = 0; i < K; i++) {
      int item = buffer[i];

      if (isUsing[item]) continue;

      if (tapSize != N) {
        tap[tapSize++] = item;
      } else {
        int itemIdxPlugedTap = getRemoveableOutletIdx(tap, buffer, i);
        int itemPlugedTap = tap[itemIdxPlugedTap];

        tap[itemIdxPlugedTap] = item;
        isUsing[itemPlugedTap] = false;
        count++;
      }

      isUsing[item] = true;
    }

    return count;
  }
  private static int getRemoveableOutletIdx(int[] tap, int[] buffer, int curIdx) {

    int[] idxList = new int[N];
    for (int i = 0; i < N; i++) {
      int item = tap[i];
      idxList[i] = getNextUsageIndexOfItem(buffer, item, curIdx);
    }

    return findIndexForOutletRemovalFromArray(idxList);
  }
  private static int findIndexForOutletRemovalFromArray(int[] idxList) {
    int idx = 0;

    if (idxList[idx] == -1) return idx;

    for (int i = 1; i < N; i++) {
      if (idxList[i] == -1) return i;
      else if (idxList[idx] < idxList[i]) {
        idx = i;
      }
    }

    return idx;
  }
  private static int getNextUsageIndexOfItem(int[] buffer, int item, int curIdx) {

    for (int i = curIdx + 1; i < K; i++) {
      if (buffer[i] == item) return i;
    }

    return -1;
  }
}