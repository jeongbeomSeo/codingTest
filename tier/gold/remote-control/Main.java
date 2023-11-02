import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = br.readLine();
        M = Integer.parseInt(br.readLine());

        boolean[] isWorkingBtn = new boolean[10];
        Arrays.fill(isWorkingBtn, true);
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int btn = Integer.parseInt(st.nextToken());
                isWorkingBtn[btn] = false;
            }
        }

        System.out.println(queryResult(isWorkingBtn));
    }
    private static int queryResult(boolean[] isWorkingBtn) {
        Queue<Node> q = new ArrayDeque<>();

        int size = N.length();

        q.add(new Node("", 0, false, false));

        int result = onlyChannelMoveCount();
        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.ptr == size) {
                result = Math.min(result, getCount(curNode));
                continue;
            }

            if (!curNode.isConsiderValueSize) {
                updateQueueByNearNumber(isWorkingBtn, q, curNode);
            } else {
                updateQueue(isWorkingBtn, q, curNode);
            }
        }

        return result;
    }
    private static void updateQueue(boolean[] isWorkingBtn, Queue<Node> q, Node curNode) {
        if (curNode.isSmall) {
            int idx = getBigestNum(isWorkingBtn);
            q.add(new Node(curNode.value + getChar(idx), curNode.ptr + 1, true, true));
        } else {
            int idx = getSmallestNum(isWorkingBtn);
            q.add(new Node(curNode.value + getChar(idx), curNode.ptr + 1, true, false));
        }
    }
    private static int getBigestNum(boolean[] isWorkingBtn) {
        for (int i = 9; i >= 0; i--) {
            if (isWorkingBtn[i])
                return i;
        }
        return -1;
    }
    private static int getSmallestNum(boolean[] isWorkingBtn) {
        for (int i = 0; i <= 9; i++) {
            if (isWorkingBtn[i])
                return i;
        }
        return -1;
    }
    private static void updateQueueByNearNumber(boolean[] isWorkingBtn, Queue<Node> q, Node node) {
        char c = N.charAt(node.ptr);

        int idx = getIdx(c);

        if (isWorkingBtn[idx]) {
            q.add(new Node(node.value + c, node.ptr + 1, false, false));
        }

        int downNum = getNearestWorkingDownNumBtn(isWorkingBtn, idx);
        if (idx != downNum)
            q.add(new Node(node.value + getChar(downNum), node.ptr + 1, true, downNum < idx));

        int upNum = getNearestWorkingUpNumBtn(isWorkingBtn, idx);
        if (downNum != upNum)
            q.add(new Node(node.value + getChar(upNum), node.ptr + 1, true, upNum < idx));

    }
    private static int getNearestWorkingUpNumBtn(boolean[] isWorkingBtn, int idx) {

        while (!isWorkingBtn[idx]) {
            idx = upIdx(idx);
        }

        return idx;
    }
    private static int getNearestWorkingDownNumBtn(boolean[] isWorkingBtn, int idx) {

        while (!isWorkingBtn[idx]) {
            idx = downIdx(idx);
        }

        return idx;
    }
    private static char getChar(int idx) {
        return (char)(idx + '0');
    }
    private static int upIdx(int idx) {
        return idx + 1 != 10 ? idx + 1 : 0;
    }
    private static int downIdx(int idx) {
        return idx - 1 != -1 ? idx - 1 : 9;
    }
    private static int getIdx(char c) {
        return c - '0';
    }
    private static int getCount(Node node) {
        return Math.abs(Integer.parseInt(node.value) - Integer.parseInt(N)) + N.length();
    }
    private static int onlyChannelMoveCount() {
        return Math.abs(100 - Integer.parseInt(N));
    }
    static class Node {
        String value;
        int ptr;
        boolean isConsiderValueSize;
        boolean isSmall;

        Node(String value, int ptr, boolean isConsiderValueSize, boolean isSmall) {
            this.value = value;
            this.ptr = ptr;
            this.isConsiderValueSize = isConsiderValueSize;
            this.isSmall = isSmall;
        }
    }
}
