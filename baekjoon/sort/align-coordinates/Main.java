import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Point o) {
    if(this.x - o.x != 0) return this.x - o.x;
    else return this.y - o.y;
  }

  Comparator<Point> comparator = new Comparator<Point>() {
    @Override
    public int compare(Point o1, Point o2) {
      if(o1.x - o2.x != 0) return o1.x - o2.x;
      else return o1.y - o2.y;
    }
  };

}



public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Point[] points = new Point[N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      points[i] = new Point(x, y);
    }

    Arrays.sort(points);

    for(int i = 0; i < N; i++) {
      bw.write(points[i].x + " " + points[i].y + "\n");
    }
    bw.flush();
    bw.close();
  }
}
