import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static BufferedWriter bw;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Wire[] wires = new Wire[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      wires[i] = new Wire(src, dst);
    }

    lis(wires, N);
  }
  static void lis(Wire[] wires, int N) throws IOException{

    int[] idxs = new int[N];
    int[] buffer = new int[N];
    int size = 0;
    Arrays.sort(wires);

    buffer[size++] = wires[0].dst;
    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < wires[i].dst) {
        idxs[i] = size;
        buffer[size] = wires[i].dst;
        size++;
      }
      else {
        int idx = lower_bound(buffer, 0, size, wires[i].dst);
        buffer[idx] = wires[i].dst;
        idxs[i] = idx;
      }
    }

    int[] result = new int[N - size];
    int res_ptr = N - size - 1;
    int ptr = size - 1;
    for (int i = N - 1; i >= 0; i--) {
      if (idxs[i] == ptr) ptr--;
      else {
        result[res_ptr--] = wires[i].src;
        if (res_ptr == -1) break;
      }
    }

    bw.write(N - size+"\n");
    for (int i = 0; i < N - size; i++) bw.write(result[i]+"\n");
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