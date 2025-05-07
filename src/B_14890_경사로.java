import java.io.*;
import java.util.*;

public class B_14890_경사로 {
    static int N, L, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            int pre = map[i][0];
            int cnt = 1;

            for (int j = 1; j < N; j++) {
                if (Math.abs(map[i][j] - pre) > 1) break;

                if (map[i][j] == pre) {
                    cnt++;
                } else if (map[i][j] > pre) { // 높아지는 경우
                    if (cnt < L) break;
                    for (int k = 1; k <= L; k++) {
                        if (visited[j - k]) break;
                        visited[j - k] = true;
                    }
                    cnt = 1;
                } else if (map[i][j] < pre) { // 낮아지는 경우
                    boolean valid = true;
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N || map[i][j + k] != map[i][j] || visited[j + k]) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) break;
                    for (int k = 0; k < L; k++) {
                        visited[j + k] = true;
                    }
                    j += L - 1;
                    cnt = 0;
                }

                pre = map[i][j];
                if (j == N - 1) answer++;
            }
        }

        // 세로
        for (int j = 0; j < N; j++) {
            boolean[] visited = new boolean[N];
            int pre = map[0][j];
            int cnt = 1;

            for (int i = 1; i < N; i++) {
                if (Math.abs(map[i][j] - pre) > 1) break;

                if (map[i][j] == pre) {
                    cnt++;
                } else if (map[i][j] > pre) { // 높아지는 경우
                    if (cnt < L) break;
                    for (int k = 1; k <= L; k++) {
                        if (visited[i - k]) break;
                        visited[i - k] = true;
                    }
                    cnt = 1;
                } else if (map[i][j] < pre) { // 낮아지는 경우
                    boolean valid = true;
                    for (int k = 0; k < L; k++) {
                        if (i + k >= N || map[i + k][j] != map[i][j] || visited[i + k]) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) break;
                    for (int k = 0; k < L; k++) {
                        visited[i + k] = true;
                    }
                    i += L - 1;
                    cnt = 0;
                }

                pre = map[i][j];
                if (i == N - 1) answer++;
            }
        }

        System.out.println(answer);
    }
}
