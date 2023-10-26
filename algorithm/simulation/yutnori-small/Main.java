import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int END = -2;
  static int[] map;
  static int[] shortMap;
  static boolean earlyStopError;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    int tc = 1;

    map = initMap();
    shortMap = initShortMap();

    while (tc <= T) {
      st = new StringTokenizer(br.readLine());
      int U = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      int[] points = new int[N];
      for (int i = 0; i < N; i++) {
        String yut = st.nextToken();
        points[i] = getPoint(yut);
      }

      int[] aPoints = new int[U];
      int[] bPoints = new int[U];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < A; i++) {
        aPoints[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < B; i++) {
        bPoints[i] = Integer.parseInt(st.nextToken());
      }

      earlyStopError = false;
      String result = simulation(points, aPoints, bPoints, U, N);
      bw.write("Case #" + tc + ": " + result +"\n");

      tc++;
    }
    bw.flush();
    bw.close();
  }
  static String simulation(int[] points, int[] aPoints, int[] bPoints, int U, int N) {

    boolean isValidGame = validationCheck(points, aPoints, bPoints, U, N, new int[U], new int[U], 0);

    if (!earlyStopError && isValidGame) return "YES";
    else return "NO";
  }
  static boolean validationCheck(int[] points, int[] aPoints, int[] bPoints, int U, int N, int[] mainBuffer, int[] subBuffer, int ptr) {

    if (earlyStopError) return false;
    boolean isEnd = false;
    if (ptr == N) {
      isEnd = isValidGameCheck(aPoints, bPoints, mainBuffer, subBuffer, U);
    }
    else {
      boolean[] isUsing = new boolean[U];
      for (int i = 0; i < U; i++) {
        if (isUsing[i] || mainBuffer[i] == END) continue;

        int[] copyMainBuffer = copyBuffer(mainBuffer);
        int[] copySubBuffer = copyBuffer(subBuffer);

        int movePoint = points[ptr];

        boolean isCatch = activeThisTurn(movePoint, copyMainBuffer, copySubBuffer, isUsing, U, i);

        if (earlyStop(copyMainBuffer, U)) {
          if (ptr == N - 1) isEnd = true;
          else earlyStopError = true;
          break;
        }

        if (isCatch) {
          isEnd = validationCheck(points, aPoints, bPoints, U, N, copyMainBuffer, copySubBuffer, ptr + 1);
        }
        else {
          if (movePoint <= 3) {
            isEnd = validationCheck(points, aPoints, bPoints, U, N, copySubBuffer, copyMainBuffer, ptr + 1);
          }
          else {
            isEnd = validationCheck(points, aPoints, bPoints, U, N, copyMainBuffer, copySubBuffer, ptr + 1);
          }
        }
        if (isEnd || earlyStopError) break;
      }
    }

    return isEnd;
  }
  static boolean earlyStop(int[] buffer, int U) {
    int i;
    for (i = 0; i < U; i++) {
      if (buffer[i] != END) break;
    }

    return i == U;
  }
  static boolean activeThisTurn(int movePoint, int[] mainBuffer, int[] subBuffer, boolean[] isUsing, int U, int idx) {

    int point = mainBuffer[idx];

    int nextPoint = getNextPoint(movePoint, point);

    if (point != 0) {
      for (int i = 0; i < U; i++) {
        if (i == idx) continue;
        if (mainBuffer[i] == point) {
          mainBuffer[i] = nextPoint;
          isUsing[i] = true;
        }
      }
    }

    mainBuffer[idx] = nextPoint;
    isUsing[idx] = true;

    boolean isCatch = false;

    if (nextPoint != END) {
      for (int i = 0; i < U; i++) {
        if (subBuffer[i] == nextPoint || ((nextPoint == 22 || nextPoint == 27) && (subBuffer[i] == 22 || subBuffer[i] == 27))) {
          subBuffer[i] = 0;
          isCatch = true;
        }
      }
    }

    return isCatch;
  }
  static int getNextPoint(int movePoint, int point) {

    if (isShortPath(point)) {
      int shortPathIdx = getShortPathIdx(point);
      point = shortMap[shortPathIdx];
      movePoint--;
    }
    for (int i = 0; i < movePoint; i++) {
      point = map[point];

      if (point == END) break;
    }

    return point;
  }
  static int[] copyBuffer(int[] buffer) {
    int[] copyBuffer = new int[buffer.length];

    for (int i = 0; i < buffer.length; i++) {
      copyBuffer[i] = buffer[i];
    }

    return copyBuffer;
  }
  static boolean isValidGameCheck(int[] aPoints, int[] bPoints, int[] buffer1, int[] buffer2, int U) {

    if (pointsCheck(aPoints, buffer1, U)) {
      return pointsCheck(bPoints, buffer2, U);
    }
    else {
      if (pointsCheck(aPoints, buffer2, U)) {
        return pointsCheck(bPoints, buffer1, U);
      }
    }

    return false;
  }
  static boolean pointsCheck(int[] points, int[] buffer, int size) {

    boolean[] isCheck = new boolean[size];
    for (int i = 0; i < size; i++) {
      int j;
      for (j = 0; j < size; j++) {
        int bufferPoint = getOriginPoint(buffer[j]);
        if (points[i] == bufferPoint && !isCheck[j]) {
          isCheck[j] = true;
          break;
        }
      }
      if (j == size) return false;
    }

    return true;
  }
  static int[] initMap() {
    int[] map = new int[31];

    for (int i = 0; i < 20; i++) {
      map[i] = i + 1;
    }
    map[19] = 30;

    for (int i = 20; i < 24; i++) {
      map[i] = i + 1;
    }
    map[24] = 15;

    for (int i = 25; i <= 29; i++) {
      map[i] = i + 1;
    }

    map[30] = END;

    return map;
  }
  static int getOriginPoint(int point) {
    if (point <= 26) return point;
    else if (point == 27) return 22;
    else if (point == 28) return 27;
    else if (point == 29) return 28;

    return 0;
  }
  static int getPoint(String yut) {
    if (yut.equals("Do")) return 1;
    else if (yut.equals("Gae")) return 2;
    else if (yut.equals("Gul")) return 3;
    else if (yut.equals("Yut")) return 4;
    else return 5;
  }
  static int getShortPathIdx(int point) {
    if (point == 5) return 0;
    else if (point == 10) return 1;
    else if (point == 15) return 2;
    else return 3;
  }
  static boolean isShortPath(int point) {
    return point == 5 || point == 10 || point == 15 || point == 22;
  }
  static int[] initShortMap() {
    int[] shortMap = new int[4];

    shortMap[0] = 20;
    shortMap[1] = 25;
    shortMap[2] = 16;
    shortMap[3] = 28;

    return shortMap;
  }
}