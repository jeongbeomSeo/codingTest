import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line.equals("end")) {
                break;
            }

            board = new char[3][3];
            int xCount = 0;
            int oCount = 0;

            // 보드 초기화 및 X, O 개수 카운트
            for (int i = 0; i < 9; i++) {
                char c = line.charAt(i);
                board[i / 3][i % 3] = c;
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }

            boolean xWin = checkWin('X');
            boolean oWin = checkWin('O');

            if (isValid(xCount, oCount, xWin, oWin)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }

    // 게임 상태의 유효성을 판별하는 메소드
    public static boolean isValid(int xCount, int oCount, boolean xWin, boolean oWin) {
        // X와 O의 개수 차이가 0 또는 1이어야 함
        if (xCount < oCount || xCount - oCount > 1) {
            return false;
        }
        // 둘 다 승리하는 경우는 없음
        if (xWin && oWin) {
            return false;
        }
        // X가 승리한 경우, X의 수는 O보다 1개 많아야 함
        if (xWin && xCount != oCount + 1) {
            return false;
        }
        // O가 승리한 경우, X의 수는 O와 같아야 함
        if (oWin && xCount != oCount) {
            return false;
        }
        // 게임이 끝났는데 승자가 없는 경우
        if (!xWin && !oWin) {
            // 보드가 꽉 찬 경우만 유효함
            if (xCount + oCount != 9) {
                return false;
            }
        }
        return true;
    }

    // 특정 플레이어의 승리 여부를 확인하는 메소드
    public static boolean checkWin(char player) {
        // 가로 확인
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                return true;
            }
        }
        // 세로 확인
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player) {
                return true;
            }
        }
        // 대각선 확인
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }
        return false;
    }
}
