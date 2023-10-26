import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] notWorkingBtn = new boolean[10];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int brokenBtnNum = Integer.parseInt(st.nextToken());

                notWorkingBtn[brokenBtnNum] = true;
            }
        }

        System.out.println(queryResult(notWorkingBtn, N));
    }
    static int queryResult(boolean[] notWorkingBtn, int target) {
        int result = Math.abs(target - 100);

        return Math.min(result, getMinCount(notWorkingBtn, String.valueOf(target)));
    }
    private static int getMinCount(boolean[] notWorkingBtn, String target) {
        Queue<Node> q = new ArrayDeque<>();

        int size = target.length();

        q.add(new Node("", 0));

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.ptr == size) {
                min = Math.min(calcCount(curNode, target, size), min);
                continue;
            }

            updateQueue(q, curNode, notWorkingBtn, target);
        }
        return min;
    }
    private static int calcCount(Node node, String target, int size) {
        return Math.abs(Integer.parseInt(node.value) - Integer.parseInt(target)) + size;
    }
    private static void updateQueue(Queue<Node> q, Node curNode, boolean[] notWorkingBtn, String target) {

        int upNum = findNearestUpNum(notWorkingBtn, target, curNode.ptr);
        int downNum = findNearestDownNum(notWorkingBtn, target, curNode.ptr);

        if (upNum == downNum) {
            q.add(new Node(
                    curNode.value + getNumChar(upNum), curNode.ptr + 1));
        } else {
            q.add(new Node(
                    curNode.value + getNumChar(upNum), curNode.ptr + 1));

            q.add(new Node(
                    curNode.value + getNumChar(downNum), curNode.ptr + 1));
        }
    }
    private static int findNearestUpNum (boolean[] notWorkingBtn, String target, int ptr) {
        int idx = getNumIdx(target.charAt(ptr));
        while (notWorkingBtn[idx]) {
            idx = getUpIdx(idx);
        }
        return idx;
    }
    private static int findNearestDownNum (boolean[] notWorkingBtn, String target, int ptr) {
        int idx = getNumIdx(target.charAt(ptr));

        while (notWorkingBtn[idx]) {
            idx = getDownIdx(idx);
        }
        return idx;
    }
    private static int getNumIdx(char c) {
        return c - '0';
    }
    private static char getNumChar(int idx) {
        return (char)(idx + '0');
    }
    private static int getUpIdx(int idx) {
        return idx + 1 != 10 ? idx + 1 : 0;
    }
    private static int getDownIdx(int idx) {
        return idx - 1 != -1 ? idx - 1 : 9;
    }
}
class Node {
    String value;
    int ptr;

    Node(String value, int ptr) {
        this.value = value;
        this.ptr = ptr;
    }
}