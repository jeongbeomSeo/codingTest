import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] switches = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken()) - 1;

            if (gender == 1) {
                for (int k = pos; k < N; k += (pos + 1)) {
                    switches[k] = switches[k] == 1 ? 0 : 1;
                }
            } else {
                int wide = 1;
                for (; pos - wide >= 0 && pos + wide < N; wide++) {
                    if (switches[pos - wide] != switches[pos + wide]) {
                        break;
                    }
                }

                for (int j = pos - wide + 1; j < pos + wide; j++) {
                    switches[j] = switches[j] == 1 ? 0 : 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (i > 0 && i % 20 == 0) System.out.println();
            System.out.print(switches[i] + " ");
        }
    }
}
