import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
  int idx;
  int value;
  int smallValueCount;

  Point(int idx, int value) {
    this.idx = idx;
    this.value = value;
  }

  @Override
  public int compareTo(Point o) {
    return this.value - o.value;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Point[] points = new Point[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      int value = Integer.parseInt(st.nextToken());
      points[i] = new Point(i , value);
    }

    Arrays.sort(points);

    points[0].smallValueCount = 0;
    int count = 0;
    for(int i = 1 ; i < points.length; i++) {
      // 서로 다른 좌표 중에서 작은 값들의 갯수를 계산
      if(points[i].value == points[i - 1].value) points[i].smallValueCount = count;
      // 값이 바뀌는 순간이 선행 조건이기 때문에 count++가 아닌 ++count로 설정
      else points[i].smallValueCount = ++count;
    }

    int[] ans = new int[N];

    for(int i = 0; i < N; i++) {
      ans[points[i].idx] = points[i].smallValueCount;
    }

    for(int i = 0; i < ans.length; i++)
      bw.write(ans[i] + " ");
    bw.flush();
    bw.close();

  }
}
