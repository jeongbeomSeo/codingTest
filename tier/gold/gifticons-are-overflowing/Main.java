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

        Gifticon[] gifticons = new Gifticon[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gifticons[i] = new Gifticon(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gifticons[i].B = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(gifticons, (o1, o2) -> {
            if (o1.B != o2.B) return o1.B - o2.B;
            return o1.A - o2.A;
        });

        long count = 0L;
        int prevMax = gifticons[0].B;
        int curMax = -1;
        for (int i = 0; i < N; i++) {
            /**
             * 조건 정리
             * 1. 현재 기프티콘 기준으로 사용할 계획 날짜보다 남은 기한이 더 높아야 한다.
             * 2. 기프티콘의 사용할 계획 날짜가 이전 날짜인 기프티콘보다 남은 기한이 더 높아야 한다.
             */
            if (gifticons[i].A < prevMax) {
                if (gifticons[i].B > prevMax) prevMax = gifticons[i].B;

                int cnt = (prevMax - gifticons[i].A + 29) / 30;
                gifticons[i].A += (30 * cnt);
                count += cnt;
            }

            curMax = Math.max(curMax, gifticons[i].A);
            if (i + 1 < N && gifticons[i].B != gifticons[i + 1].B) {
                prevMax = curMax;
            }
        }

        System.out.println(count);
    }
}
class Gifticon {
    int A;
    int B;

    public Gifticon(int a) {
        A = a;
    }
}