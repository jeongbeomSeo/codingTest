import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        List<Ball> list = new ArrayList<>();
        char ball = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);

            if (ball == c) {
                count++;
            } else {
                list.add(new Ball(ball, count));
                ball = c;
                count = 1;
            }
        }

        list.add(new Ball(ball, count));

        int redCount = 0;
        int blueCount = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).ball == 'B') {
                blueCount += list.get(i).count;
            } else {
                redCount += list.get(i).count;
            }
        }

        int rightRes = Math.min(redCount, blueCount);

        if (list.get(0).ball == 'R') redCount -= list.get(0).count;
        else blueCount -= list.get(0).count;

        if (list.get(list.size() - 1).ball == 'R') redCount += list.get(list.size() - 1).count;
        else blueCount += list.get(list.size() -1 ).count;

        int leftRes = Math.min(redCount, blueCount);

        System.out.println(Math.min(leftRes, rightRes));
    }
}
class Ball {
    char ball;
    int count;

    Ball(char ball, int count) {
        this.ball = ball;
        this.count = count;
    }
}
