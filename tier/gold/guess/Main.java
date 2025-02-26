import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[][] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        table = new int[N + 1][N + 1];

        String str = br.readLine();

        int idx = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = i; j < N + 1; j++) {
                int v = convertChar(str.charAt(idx++));

                table[i][j] = v;
            }
        }

        int[] nums = new int[N + 1];
        recur(nums, 1);
        for (int i = 1; i < N + 1; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private static boolean recur(int[] nums, int ptr) {

        if (ptr == N + 1) {
            return true;
        }

        for (int num = -10; num <= 10; num++) {

            nums[ptr] = num;
            int start;
            for (start = 1; start <= ptr; start++) {
                int rangeSum = getRangeSum(nums, start, ptr + 1);

                if (!isPass(table[start][ptr], rangeSum)) {
                    break;
                }
            }

            if (start == ptr + 1) {
                if (recur(nums, ptr + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isPass(int tableNum, int target) {
        if (tableNum > 0 && target > 0) {
            return true;
        } else if (tableNum < 0 && target < 0) {
            return true;
        } else if (tableNum == 0 && target == 0) {
            return true;
        }

        return false;
    }

    private static int getRangeSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += nums[i];
        }

        return sum;
    }

    private static int convertChar(char c) {
        if (c == '+') {
            return 1;
        } else if (c == '-') {
            return -1;
        } else {
            return 0;
        }
    }
}
