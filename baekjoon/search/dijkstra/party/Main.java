import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;
 static int V, E;
 static int[] dist;

 static ArrayList<ArrayList<Node>> graph;

 static class Node {
   int idx;
   int cost;

   Node(int idx, int cost) {
     this.idx = idx;
     this.cost = cost;
   }
 }

 public static void main(String[] args) throws IOException {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringTokenizer st = new StringTokenizer(br.readLine());

   V = Integer.parseInt(st.nextToken());
   E = Integer.parseInt(st.nextToken());
   int partyNode = Integer.parseInt(st.nextToken());

   graph = new ArrayList<>();
   for(int i = 0 ; i < V + 1; i++) {
     graph.add(new ArrayList<>());
   }

   for(int i = 0; i < E; i++) {
     st = new StringTokenizer(br.readLine());
     int n1 = Integer.parseInt(st.nextToken());
     int n2 = Integer.parseInt(st.nextToken());
     int cost = Integer.parseInt(st.nextToken());

     graph.get(n1).add(new Node(n2, cost));
   }

   dist = new int[V + 1];
   Arrays.fill(dist, INF);

   Dijkstra(partyNode);

   int max = Integer.MIN_VALUE;
   for(int i = 1; i < dist.length; i++) {
     max = max < dist[i] + startDijkstra(i, partyNode) ? dist[i] + startDijkstra(i, partyNode) : max;
   }

   System.out.println(max);

 }
 static void Dijkstra(int start) {

   dist[start] = 0;
   PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

   q.offer(new Node(start, 0));

   while (!q.isEmpty()) {
     Node curNode = q.poll();

     if(dist[curNode.idx] < curNode.cost) continue;

     for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
       Node adjNode = graph.get(curNode.idx).get(i);

       if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
         dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
         q.offer(new Node(adjNode.idx, dist[adjNode.idx]));
       }
     }
   }
 }

  static int startDijkstra(int start, int end) {

    int[] StarttoEndDist = new int[V + 1];
    Arrays.fill(StarttoEndDist, INF);
    StarttoEndDist[start] = 0;
    boolean[] isVisited = new boolean[V + 1];

    for (int i = 0; i < V; i++) {
      int nodeIdx = 0;
      int nodeValue = INF;

      for (int j = 1; j < StarttoEndDist.length; j++) {
        if (!isVisited[j] && StarttoEndDist[j] < nodeValue) {
          nodeIdx = j;
          nodeValue = StarttoEndDist[j];
        }
      }

      if(nodeIdx == end) {
        return  StarttoEndDist[nodeIdx];
      }

      isVisited[nodeIdx] = true;

      for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
        Node adjNode = graph.get(nodeIdx).get(j);

        if (StarttoEndDist[adjNode.idx] > StarttoEndDist[nodeIdx] + adjNode.cost) {
          StarttoEndDist[adjNode.idx] = StarttoEndDist[nodeIdx] + adjNode.cost;
        }
      }
    }
    return  StarttoEndDist[end];
  }
}