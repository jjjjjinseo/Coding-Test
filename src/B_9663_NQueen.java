import java.io.*;
import java.util.*;

public class B_9663_NQueen {
	static int N, answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		bt(0);

		System.out.println(answer);
	}

	public static void bt(int row) {
		if (row == N) {
			answer++;
			return;
		}
		for (int i = 0; i < N; i++) {

			if (canGo(row, i)) {
				map[row][i] = 1;
				bt(row + 1);
				map[row][i] = 0;
			}
		}
	}

	public static boolean canGo(int x, int y) {
		for (int i = 0; i < x; i++) {
			if (map[i][y] != 0) {
				return false;
			}
		}
		for (int i = 1; x - i >= 0 && y + i < N; i++) {
			if (map[x - i][y + i] != 0) {
				return false;
			}
		}
		for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
			if (map[x - i][y - i] != 0) {
				return false;
			}
		}
		return true;
	}
}
