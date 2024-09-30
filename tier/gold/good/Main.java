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
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        Arrays.sort(nums);
        for (int k = 0; k < N; k++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                if (k == left) {
                    left++;
                    continue;
                }

                if (k == right) {
                    right--;
                    continue;
                }

                if (nums[k] == nums[left] + nums[right]) {
                    ans++;
                    break;
                }

                if (nums[k] > nums[left] + nums[right]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(ans);
    }
}