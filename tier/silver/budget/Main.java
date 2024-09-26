import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }

        int M = Integer.parseInt(br.readLine());

        System.out.println(upperBound(nums, 0, max + 1, M) - 1);

    }
    private static int upperBound(int[] nums, int left, int right, int M) {
        while (left < right) {
            int mid = (left + right) / 2;

            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                count += Math.min(mid, nums[i]);
            }

            if (count <= M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
