import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] buffer = new int[N];
        int[] index = new int[N];
        int[] parent = new int[N];

        int size = 0;
        buffer[size] = nums[0];
        index[size] = 0;
        parent[0] = -1;
        size++;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            if (buffer[size - 1] < num) {
                buffer[size] = num;
                index[size] = i;
                parent[i] = index[size - 1];
                size++;
            } else {
                int idx = lowerBound(buffer, 0, size, num);

                buffer[idx] = num;
                index[idx] = i;
                if (idx == 0) {
                    parent[i] = -1;
                } else {
                    parent[i] = index[idx - 1];
                }
            }
        }
        int[] lis = new int[size];
        int idx = index[size - 1];
        for (int i = size - 1; i >= 0; i--) {
            lis[i] = nums[idx];
            idx = parent[idx];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(size + "\n");
        for (int i = 0; i < size; i++) {
            bw.write(lis[i] + " ");
        }
        bw.flush();
        bw.close();
    }
    private static int lowerBound(int[] array, int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}