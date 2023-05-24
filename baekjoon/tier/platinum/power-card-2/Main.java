import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Wire[] wires = new Wire[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      wires[i] = new Wire(src, dst);
    }
    Arrays.sort(wires);

    int[] idxList = new int[N];
    int[] buffer = new int[N];
    int size = 0;

    buffer[size++] = wires[0].dst;

    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < wires[i].dst) {
        buffer[size] = wires[i].dst;
        idxList[i] = size;
        size++;
      }
      else {
        int idx = lower_bound(buffer, 0, size, wires[i].dst);
        buffer[idx] = wires[i].dst;
        idxList[i] = idx;
      }
    }

    int[] result = new int[size];
    int ptr = N - 1;

    for (int i = size - 1; i >= 0; i--) {
      while (idxList[ptr] != i) ptr--;
      result[i] = wires[ptr--].src;
    }

    bw.write(N - size + "\n");
    ptr = 0;
    for (int i = 0; i < N; i++) {
      if (ptr == size || wires[i].src != result[ptr]) {
        bw.write(wires[i].src + "\n");
      }
      else ptr++;
    }
    bw.flush();
    bw.close();

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
  int src;
  int dst;

  Wire(int src, int dst) {
    this.src = src;
    this.dst = dst;
  }

  @Override
  public int compareTo(Wire o) {
    return this.src - o.src;
  }
}