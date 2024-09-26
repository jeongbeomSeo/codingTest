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

        Arrays.sort(nums);

        int resultA = -1;
        int resultB = -1;
        int difference = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            int numA = nums[i];

            int left = i + 1;
            int right = nums.length;
            while (left < right) {
                int mid = (left + right) / 2;

                int numB = nums[mid];
                int sum = numA + numB;

                if (Math.abs(sum) < difference) {
                    difference = Math.abs(sum);
                    resultA = numA;
                    resultB = numB;
                }

                if (sum < 0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        System.out.println(resultA + " " + resultB);
    }
}