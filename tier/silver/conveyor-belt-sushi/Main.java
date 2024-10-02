import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] dishes = new int[N + k];

        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N; i < N + k; i++) {
            dishes[i] = dishes[i - N];
        }

        int[] sorts = new int[d + 1];

        int count = 0;
        for (int i = 0; i < k; i++) {
            int dish = dishes[i];

            if (sorts[dish] == 0) count++;
            sorts[dish]++;
        }

        int maxCount = count;
        if (sorts[c] == 0) maxCount++;

        for (int i = k; i < N + k; i++) {
            int prevDish = dishes[i - k];
            if (--sorts[prevDish] == 0) count--;

            int dish = dishes[i];
            if (sorts[dish] == 0) count++;
            sorts[dish]++;

            if (sorts[c] == 0) {
                if (maxCount < count + 1) {
                    maxCount = count + 1;
                }
            } else {
                if (maxCount < count) {
                    maxCount = count;
                }
            }
        }

        System.out.println(maxCount);
    }
}
