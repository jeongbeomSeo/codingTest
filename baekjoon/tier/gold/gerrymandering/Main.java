import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int MAX = 10001;

  // 목표: 최솟값 설정
  static int diffMin = MAX;
  static int[] areaA, areaB;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] population = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      population[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int connectionCount = Integer.parseInt(st.nextToken());

      for (int j = 0; j < connectionCount; j++) {
        graph.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    // A구역, B구역 배열 생성
    areaA = new int[N];
    areaB = new int[N];

    // 팀나누기는 미리 한명 넣어놓고 시작
    areaA[0] = 1;

    // Logic

    // 조합으로 인수 나누기 -> 조합 방식을 사용하고 싶다면 되도록이면 재귀 함수를 사용하는 것을 지향해야 할 것 같다.
    // 문제는 재귀 함수로 사용하는 순간 함수 내에서 변수 초기화는 힘들다.

    // 조합으로 나눠지는 경우 나올 때 마다 구역별로 방분 가능 여부 확인 (BFS)
    // 방문 가능할 경우 인구수 차이 계산후 최솟값일 경우 갱신

    combination(population, graph, 2, 1, 0, N);

    if (diffMin == MAX) System.out.println(-1);
    else System.out.println(diffMin);
  }

  static void combination(int[] population,  ArrayList<ArrayList<Integer>> graph , int ptr, int sizeA, int sizeB, int N) {

    if (ptr == N + 1) {
      if (sizeA != 0 && sizeB != 0) {
        if (bfs(graph, sizeA, sizeB, N)) {
          diffMin = Math.min(diffMin, calcDiff(population, sizeA, sizeB));
        }
      }
    } else {
      areaA[sizeA] = ptr;
      combination(population, graph, ptr + 1, sizeA + 1, sizeB, N);
      areaA[sizeA] = 0;


      areaB[sizeB] = ptr;
      combination(population, graph, ptr + 1, sizeA, sizeB + 1, N);
      areaB[sizeB] = ptr;
    }
  }

  // A구역 탐색 -> B구역 탐색 -> isVisited 1 ~ N까지 전부 ture일 경우 return true
  static boolean bfs(ArrayList<ArrayList<Integer>> graph, int sizeA, int sizeB, int N) {

    boolean[] isVisited = new boolean[N + 1];
    Queue<Integer> q = new ArrayDeque<>();


    // A구역 확인
    q.add(areaA[0]);
    isVisited[areaA[0]] = true;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);


        for (int j = 1; j < sizeA; j++) {
          if (adjNode == areaA[j] && !isVisited[areaA[j]]) {
            q.add(adjNode);
            isVisited[adjNode] = true;
          }
        }
      }
    }

    // B구역 확인
    q.add(areaB[0]);
    isVisited[areaB[0]] = true;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);


        for (int j = 1; j < sizeB; j++) {
          if (adjNode == areaB[j] && !isVisited[areaB[j]]) {
            q.add(adjNode);
            isVisited[adjNode] = true;
          }
        }
      }
    }

    // 1 ~ N번 노드 미방문 여부 확인
    for (int i = 1; i <= N; i++) {
      if (isVisited[i] == false) return false;
    }
    return true;
  }

  static int calcDiff(int[] population, int sizeA, int sizeB) {
    int sumA = 0;
    int sumB = 0;

    for (int i = 0; i < sizeA; i++) {
      sumA += population[areaA[i]];
    }

    for (int i = 0; i < sizeB; i++) {
      sumB += population[areaB[i]];
    }

    return Math.abs(sumA - sumB);
  }
}