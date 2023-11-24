import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
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

        System.out.println(queryResult(nums, N, S));
    }
    private static int queryResult(int[] nums, int N, int S) {

        long sum = 0;
        int prevPtr = 0;
        int nextPtr = 0;

        int minLength = INF;
        while (prevPtr < N) {
            if (sum >= S) {
                minLength = Math.min(minLength, nextPtr - prevPtr);
                sum -= nums[prevPtr];
                prevPtr++;
            } else {
                if (nextPtr == N) break;
                sum += nums[nextPtr];
                nextPtr++;
            }
        }

        return minLength != INF ? minLength : 0;
    }
}
