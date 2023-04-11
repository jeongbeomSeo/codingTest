import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> list = new ArrayList<>();
    combination(nums, list, N, S, 0);

    System.out.println(count);
  }
  static void combination(int[] nums, ArrayList<Integer> list, int N, int S, int r) {
    if (r == N) {
      if (!list.isEmpty()) {
        int sum = 0;
        for (int num : list) {
          sum += num;
        }
        if (S == sum) count++;
      }
    }
    else {
      list.add(nums[r]);
      combination(nums, list, N, S, r + 1);
      list.remove(list.size() - 1);

      combination(nums, list, N, S, r + 1);
    }
  }
}
