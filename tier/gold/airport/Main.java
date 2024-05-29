import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        int[] orders = new int[P];
        for (int i = 0; i < orders.length; i++) {
            orders[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> remainGates = new ArrayList<>();
        for (int i = 1; i <= G; i++) {
            remainGates.add(i);
        }

        int count = 0;
        for (; count < Math.min(P, G); count++) {
            int gateNum = orders[count];

            int remainGateIdx = lowerBound(remainGates, 0, remainGates.size(), gateNum);
            if (remainGateIdx == remainGates.size()) remainGateIdx = remainGates.size() - 1;

            if (remainGates.get(remainGateIdx) <= gateNum) {
                remainGates.remove(remainGateIdx);
            } else if (remainGateIdx > 0) {
                remainGates.remove(remainGateIdx - 1);
            } else {
                break;
            }
        }
        System.out.println(count);
    }
    private static int lowerBound(List<Integer> list, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
