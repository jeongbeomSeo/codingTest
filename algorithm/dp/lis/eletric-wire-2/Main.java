import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Wire[] wires = new Wire[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());

            wires[i] = new Wire(src, dst);
        }

        Arrays.sort(wires);

        int[] lisTable = active_LIS(wires, N);

        bw.write(lisTable.length + "\n");
        for (int i = 0; i < lisTable.length; i++) {
            bw.write(lisTable[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static int[] active_LIS(Wire[] wires, int N) {

        int[] table = new int[N];
        int[] idxs = new int[N];

        int size = 0;
        table[size++] = wires[0].dst;

        for (int i = 1; i < N; i++) {
            if (table[size - 1] < wires[i].dst) {
                table[size] = wires[i].dst;
                idxs[i] = size;
                size++;
            } else if (table[size - 1] > wires[i].dst) {
                int idx = lower_bound(table, 0, size, wires[i].dst);
                table[idx] = wires[i].dst;
                idxs[i] = idx;
            }
        }

        int tableSize = N - size;
        int[] lisTable = new int[tableSize];

        size--;
        for (int i = N - 1; i >= 0; i--) {
            if (idxs[i] == size) {
                size--;
            } else {
                lisTable[--tableSize] = wires[i].src;
            }
        }

        return lisTable;
    }
    private static int lower_bound(int[] table, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (table[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
class Wire implements Comparable<Wire>{
    int src;
    int dst;

    Wire(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public int compareTo(Wire o) {
        return this.src - o.src;
    }
}
