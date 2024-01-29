import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] sizeInDistrictArray = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      sizeInDistrictArray[i] = Integer.parseInt(st.nextToken());
    }

    List<Integer>[] graph = new List[N + 1];
    for (int i = 0; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());

      for (int j = 0; j < size; j++) {
        int nearIdx = Integer.parseInt(st.nextToken());
        graph[i].add(nearIdx);
        graph[nearIdx].add(i);
      }
    }

    int result = gerrymandering(graph, sizeInDistrictArray, N);
    System.out.println(result != INF ? result : -1);
  }
  private static int gerrymandering(List<Integer>[] graph, int[] sizeInDistrictArray, int N) {

    int minSubtraction = INF;
    for (int i = 1; i < (1 << N) - 1; i++) {
      int groupASize = 0;
      int groupBSize = 0;
      boolean[] isGroupABoolArray = new boolean[N + 1];
      for (int j = 0; j < N; j++) {
        if ((i & (1 << j)) != 0) {
          isGroupABoolArray[j + 1] = true;
          groupASize++;
        }
        else groupBSize++;
      }

      if (!isValidGroup(graph, isGroupABoolArray, true, groupASize, N)) continue;
      if (!isValidGroup(graph, isGroupABoolArray, false, groupBSize, N)) continue;

      int groupACount = 0;
      int groupBCount = 0;
      for (int j = 1; j <= N; j++) {
        if (isGroupABoolArray[j]) groupACount += sizeInDistrictArray[j];
        else groupBCount += sizeInDistrictArray[j];
      }

      minSubtraction = Math.min(minSubtraction, Math.abs(groupACount - groupBCount));
    }

    return minSubtraction;
  }
  private static boolean isValidGroup(List<Integer>[] graph, boolean[] isGroupABoolArray,
                                      boolean checkGroup, int groupSize, int N) {
    // If Group is A then checkGroup is true

    int visitCount = 0;
    boolean[] isVisited = new boolean[N + 1];
    Queue<Integer> nodeIdxBuffer = new ArrayDeque<>();
    int startNodeIdx = getStartNode(isGroupABoolArray, checkGroup, N);
    nodeIdxBuffer.add(startNodeIdx);
    isVisited[startNodeIdx] = true;
    visitCount++;

    if (visitCount == groupSize) return true;

    while (!nodeIdxBuffer.isEmpty()) {
      int curNodeIdx = nodeIdxBuffer.poll();

      if (visitCount == groupSize) return true;

      for (int i = 0; i < graph[curNodeIdx].size(); i++) {
        int nearNodeIdx = graph[curNodeIdx].get(i);
        if (!isVisited[nearNodeIdx] && isGroupABoolArray[nearNodeIdx] == checkGroup) {
          nodeIdxBuffer.add(nearNodeIdx);
          isVisited[nearNodeIdx] = true;
          visitCount++;
        }
      }
    }

    return visitCount == groupSize;
  }
  private static int getStartNode(boolean[] isGroupBoolArray, boolean checkGroup, int N) {
    for (int i = 1; i < N + 1; i++) {
      if (isGroupBoolArray[i] == checkGroup) return i;
    }

    System.out.println("Logick Error: getStartNode");
    return -1;
  }
}