import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        Set<Integer> addSet = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            for (int value : set) {
                addSet.add(Math.abs(num - value));
                addSet.add(num + value);
            }

            set.add(num);
            set.addAll(addSet);
            addSet.clear();
        }

        int cnt = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        while (cnt-- != 0) {
            int num = Integer.parseInt(st.nextToken());

            if (set.contains(num)) {
                System.out.println("Y");
            } else {
                System.out.println("N");
            }
        }
    }
}
