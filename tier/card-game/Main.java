import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] orders = new int[K];
        for (int i = 0; i < K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int[] disJointSet = new int[M];
        for (int i = 0; i < M; i++) {
            disJointSet[i] = i;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < orders.length; i++) {
            int idx = upperBound(cards, 0, cards.length, orders[i]);

            int notUsedIdx = getNotUsedIdx(disJointSet, idx);
            bw.write(cards[notUsedIdx] + "\n");
            disJointSet[notUsedIdx] = notUsedIdx + 1;
        }

        bw.flush();
        bw.close();
    }
    private static int upperBound(int[] cards, int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (cards[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private static int getNotUsedIdx(int[] disJointSet, int idx) {
        if (disJointSet[idx] == idx) return disJointSet[idx];

        return disJointSet[idx] = getNotUsedIdx(disJointSet, disJointSet[idx]);
    }
}