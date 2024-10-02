import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pair[] pairs = new Pair[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(i, Integer.parseInt(st.nextToken()));
        }

        if (K == 1) {
            System.out.println(0);
            return;
        }

        TreeSet<Pair> treeSet = new TreeSet<>();

        for (int i = 0; i < K; i++) {
            treeSet.add(pairs[i]);
        }

        long ans = 0;

        // 초기 이동 거리 계산
        Pair prev = null;
        for (Pair pair : treeSet) {
            if (prev != null) {
                ans += Math.abs(pair.idx - prev.idx);
            }
            prev = pair;
        }

        long sum = ans;

        for (int i = K; i < N; i++) {
            Pair removed = pairs[i - K];
            Pair curr = removed;

            // 제거하기 전 prev와 next를 구함
            Pair prevPair = treeSet.lower(curr);    // 우선순위가 높은
            Pair nextPair = treeSet.higher(curr);   // 우선순위가 낮은

            // 이동 거리 업데이트 (제거 시)
            if (prevPair != null && nextPair != null) {
                sum -= Math.abs(curr.idx - prevPair.idx);
                sum -= Math.abs(nextPair.idx - curr.idx);
                sum += Math.abs(nextPair.idx - prevPair.idx);
            } else if (prevPair != null) {
                sum -= Math.abs(curr.idx - prevPair.idx);
            } else if (nextPair != null) {
                sum -= Math.abs(nextPair.idx - curr.idx);
            }

            treeSet.remove(curr);

            // 추가할 요소
            Pair added = pairs[i];
            treeSet.add(added);

            curr = added;
            prevPair = treeSet.lower(curr);    // 우선순위가 높은
            nextPair = treeSet.higher(curr);   // 우선순위가 낮은

            // 이동 거리 업데이트 (추가 시)
            if (prevPair != null && nextPair != null) {
                sum += Math.abs(curr.idx - prevPair.idx);
                sum += Math.abs(nextPair.idx - curr.idx);
                sum -= Math.abs(nextPair.idx - prevPair.idx);
            } else if (prevPair != null) {
                sum += Math.abs(curr.idx - prevPair.idx);
            } else if (nextPair != null) {
                sum += Math.abs(nextPair.idx - curr.idx);
            }

            ans = Math.min(ans, sum);
        }

        System.out.println(ans);
    }
}

class Pair implements Comparable<Pair> {
    int idx;
    int priority;

    public Pair(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.priority != o.priority) return o.priority - this.priority; // 우선순위 내림차순
        return this.idx - o.idx; // 우선순위가 같으면 인덱스 오름차순
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Pair) {
            Pair comp = (Pair) object;
            return this.idx == comp.idx && this.priority == comp.priority;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, priority);
    }
}
