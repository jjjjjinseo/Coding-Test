import java.io.*;
import java.util.*;

public class B_2468_안전영역 {
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        List<Integer> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        int maxLen = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > maxLen) {
                    maxLen = map[i][j];
                }
            }
        }

        int maxArea = 0;

        for (int len = 0; len <= maxLen; len++) {
            boolean[][] visit = new boolean[N][N];
            int answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j] && map[i][j] > len) {
                        Queue<int[]> que = new LinkedList<>();
                        que.add(new int[]{i, j});
                        visit[i][j] = true;
                        BFS(que, visit, map, N, len);
                        answer++;
                    }
                }
            }
            maxArea = Math.max(maxArea, answer);
        }

        System.out.println(maxArea);
    }

    public static void BFS(Queue<int[]> que, boolean[][] visit, int[][] map, int N, int len) {
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int newX = x + dir[k][0];
                int newY = y + dir[k][1];
                if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                    if (!visit[newX][newY] && map[newX][newY] > len) {
                        visit[newX][newY] = true;
                        que.add(new int[]{newX, newY});
                    }
                }
            }
        }
    }
}
