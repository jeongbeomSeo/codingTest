// SPFA

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int src;
  int idx;
  int cost;

  Node(int src, int idx, int cost) {
    this.src = src;
    this.idx = idx;
    this.cost = cost;
  }
}

public class Main {
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int start = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for(int i = 0 ; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0 ; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(n1).add(new Node(n1, n2, cost));
    }

    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF);

    SPFA(N, start, dist, graph);

  }
  static void SPFA(int N, int start, int[] dist, ArrayList<ArrayList<Node>> graph) {
    Queue<Node> q = new LinkedList<>();
    boolean[] inQueue = new boolean[N + 1];
    int[] cycle = new int[N + 1];
    dist[start] = 0;


    q.add(new Node(-1, start, 0));
    inQueue[start] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      inQueue[curNode.idx] = false;

      for(int i = 0 ; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
          dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;

          if(!inQueue[adjNode.idx]) {
            if(++cycle[adjNode.idx] >= N) {
              System.out.println("음의 사이클 존재");
              return;
            }
            q.add(new Node(curNode.idx, adjNode.idx, dist[adjNode.idx]));
            inQueue[adjNode.idx] = true;
          }
        }
      }
    }

    for(int i = 1; i < dist.length; i++){
      if(dist[i] != INF) System.out.print(dist[i] + " ");
      else System.out.print("INF ");
    }
  }
}