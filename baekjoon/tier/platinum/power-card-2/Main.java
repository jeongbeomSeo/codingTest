import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Wire[] wires = new Wire[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      wires[i] = new Wire(n1, n2);
    }

    Arrays.sort(wires);

    int[] idxList = new int[N];
    int[] buffer = new int[N];
    int size = 0;
    buffer[size++] = wires[0].dstIdx;

    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < wires[i].dstIdx) {
        buffer[size] = wires[i].dstIdx;
        idxList[i] = size++;
      }
      else {
        int idx = lower_bound(buffer, 0, size, wires[i].dstIdx);
        buffer[idx] = wires[i].dstIdx;
        idxList[i] = idx;
      }
    }

    int[] result = new int[size];
    int j = N - 1;
    for (int i = size - 1; i >= 0; i--) {
      for (; j >= 0; j--) {
        if (i == idxList[j]) {
          result[i] = j--;
          break;
        }
      }
    }

    System.out.println(N - size);
    j = 0;
    for (int i = 0; i < N; i++) {
      if (j >= size || i != result[j]) System.out.println(wires[i].srcIdx);
      else j++;
    }

  }
  static int lower_bound(int[] buffer, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (buffer[mid] < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}

class Wire implements Comparable<Wire>{
  int srcIdx;
  int dstIdx;

  Wire(int srcIdx, int dstIdx) {
    this.srcIdx = srcIdx;
    this.dstIdx = dstIdx;
  }

  @Override
  public int compareTo(Wire o) {
    return this.srcIdx - o.srcIdx;
  }
}