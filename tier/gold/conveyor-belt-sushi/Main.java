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

        int[] dishes = new int[2 * N];

        for (int i = 0; i < N; i++) {
            int dish = Integer.parseInt(br.readLine());
            dishes[i] = dishes[i + N] = dish;
        }

        int[] sortes = new int[d + 1];

        int count = 0;
        for (int i = 0; i < k; i++) {
            int dish = dishes[i];

            if (sortes[dish] == 0) {
                count++;
            }

            sortes[dish]++;
        }

        int maxCount = count;
        if (sortes[c] == 0) maxCount++;

        for (int i = k; i < N + k; i++) {
            int prevDish = dishes[i - k];
            if (--sortes[prevDish] == 0) {
                count--;
            }

            int dish = dishes[i];
            if (sortes[dish]++ == 0) {
                count++;
            }

            if (sortes[c] == 0) maxCount = Math.max(maxCount, count + 1);
            else maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
    }
}
