import java.io.*;
import java.util.*;

public class B_15683_감시 {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][][] dir = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] office = new int[N][M];
        List<int[]> cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, office[i][j]});
                }
            }
        }

        dfs(0, cctvs, office);

        System.out.println(answer);
    }

    // DFS로 CCTV 방향 설정
    public static void dfs(int idx, List<int[]> cctvs, int[][] office) {
        if (idx == cctvs.size()) {
            answer = Math.min(answer, getHiddenSpace(office));
            return;
        }

        int[] cctv = cctvs.get(idx);
        int x = cctv[0];
        int y = cctv[1];
        int type = cctv[2];

        for (int[] directions : dir[type]) {
            int[][] copy = copyMap(office);
            markArea(copy, x, y, directions);
            dfs(idx + 1, cctvs, copy);
        }
    }

    static void markArea(int[][] office, int x, int y, int[] directions) {
        for (int dir : directions) {
            int nx = x, ny = y;
            while (true) {
                nx += dx[dir];
                ny += dy[dir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || office[nx][ny] == 6) break; // 벽 만나면 종료
                if (office[nx][ny] == 0) office[nx][ny] = 7; // 감시 영역만 마킹
            }
        }
    }

    // 사각지대 계산
    static int getHiddenSpace(int[][] office) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) count++;
            }
        }
        return count;
    }

    static int[][] copyMap(int[][] office) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = office[i][j];
            }
        }
        return copy;
    }
}
