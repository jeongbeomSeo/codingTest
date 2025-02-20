import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long[] weights = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();

        updateCombList(list1, weights, 0, new long[N], 0, N / 2);
        updateCombList(list2, weights, N / 2, new long[N], 0, N);

        Collections.sort(list2);

        int sum = 0;
        for (long weight : list1) {
            if (weight > C) continue;
            sum += searchUpperBoundary(list2, 0, list2.size(), C - weight);
        }

        System.out.println(sum);
    }
    private static int searchUpperBoundary(List<Long> list, int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static void updateCombList(List<Long> list, long[] weights, int ptr, long[] buffer, int size, int boundary) {

        if (ptr == boundary) {
            long weightSum = 0;
            for (int i = 0; i < size; i++) {
                weightSum += buffer[i];
            }

            list.add(weightSum);
            return;
        }

        buffer[size] = weights[ptr];
        updateCombList(list, weights, ptr + 1, buffer, size + 1, boundary);
        buffer[size] = 0;
        updateCombList(list, weights, ptr + 1, buffer, size, boundary);
    }
}