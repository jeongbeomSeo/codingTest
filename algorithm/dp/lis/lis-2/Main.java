import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] lis = new int[N];


    System.out.println(lis_dp(lis, arr, N));
  }
  static int binary_search(int[] lis, int left, int right, int target) {
    int mid;

    while (left < right) {
      mid = (left + right) / 2;

      if(lis[mid] < target) {
        left = mid + 1;
      }
      else {
        right = mid;
      }
    }

    return right;
  }

  static int lis_dp(int[] lis, int[] arr, int N) {
    lis[0] = arr[0];

    int len = 1;
    for (int i = 1; i < N; i++) {
      if (lis[len - 1] < arr[i]) {
        lis[len++] = arr[i];
      }
      else if (lis[len - 1] > arr[i]){
        int idx = binary_search(lis, 0, len - 1, arr[i]);
        lis[idx] = arr[i];
      }
    }
    return len;
  }
}
