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

        int[] numArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(queryByTwoPointer(numArray, N, S));
    }
    private static int queryByTwoPointer(int[] numArray, int N, int S) {

        int ptr1 = 0;
        int ptr2 = 0;

        int minLength = Integer.MAX_VALUE;
        int sum = numArray[0];
        while (ptr1 < N) {
            if (sum >= S) {
                minLength = Math.min(minLength, ptr2 - ptr1 + 1);
                sum -= numArray[ptr1++];
            } else {
                if (ptr2 == N - 1) break;
                sum += numArray[++ptr2];
            }
        }

        return minLength != INF ? minLength : 0;
    }
}