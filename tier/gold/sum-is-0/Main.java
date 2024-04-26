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

        int[] counter = new int[40001];

        long result = 0;
        for (int i = 0; i < N; i++) {
            result += counter[20000 - arr[i]];
            for (int j = 0; j < i; j++) {
                counter[20000 + (arr[j] + arr[i])]++;
            }
        }

        System.out.println(result);
    }
}