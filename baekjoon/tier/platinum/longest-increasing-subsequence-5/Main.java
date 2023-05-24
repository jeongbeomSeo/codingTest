import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    int[] buffer = new int[N];
    int size = 0;
    int[] idxList = new int[N];
    buffer[0] = nums[0];
    size++;

    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < nums[i]) {
        buffer[size] = nums[i];
        idxList[i] = size;
        size++;
      }
      else {
        int idx = lower_bound(buffer, 0, size, nums[i]);
        buffer[idx] = nums[i];
        idxList[i] = idx;
      }
    }

    int[] result = new int[size];
    int ptr = N - 1;
    for (int i = size - 1; i >= 0; i--) {
      while (idxList[ptr] != i) ptr--;
      result[i] = nums[ptr];
    }

    bw.write(size + "\n");
    for (int ans : result) bw.write(ans + " ");
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