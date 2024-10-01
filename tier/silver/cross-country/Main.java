import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            int N = Integer.parseInt(br.readLine());

            Queue<Integer> q = new ArrayDeque<>();
            Map<Integer, Integer> teams = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int team = Integer.parseInt(st.nextToken());

                teams.put(team, teams.getOrDefault(team, 0) + 1);
                q.add(team);
            }

            Map<Integer, Integer> scores = new HashMap<>();
            Map<Integer, Integer> counts = new HashMap<>();
            Map<Integer, Integer> fiveCounts = new HashMap<>();
            int score = 1;
            while (!q.isEmpty()) {
                int team = q.poll();

                if (teams.get(team) != 6) continue;

                if (counts.getOrDefault(team, 0) < 4) {
                    scores.put(team, scores.getOrDefault(team, 0) + score);
                }
                score++;

                counts.put(team, counts.getOrDefault(team, 0) + 1);
                if (counts.get(team) == 5) {
                    fiveCounts.put(team, score);
                }
            }

            Set<Integer> keys = scores.keySet();
            int minScore = Integer.MAX_VALUE;
            int resultTeam = 0;
            for (Integer team : keys) {
                if (scores.get(team) < minScore) {
                    resultTeam = team;
                    minScore = scores.get(team);
                } else if (scores.get(team) == minScore) {
                    if (fiveCounts.get(team) < fiveCounts.get(resultTeam)) {
                        resultTeam = team;
                    }
                }
            }

            bw.write(resultTeam + "\n");
        }
        bw.flush();
        bw.close();
    }
}
