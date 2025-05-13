import java.io.*;
import java.util.*;

public class B_14889_스타트와링크 {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int answer = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];

        // 능력치 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int index) {
        if (depth == N / 2) {
            //팀 다 채워짐
            calculateDifference();
            return;
        }

        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    // 팀 능력치 차이 계산
    public static void calculateDifference() {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    sumA += S[i][j];
                } else if (!visited[i] && !visited[j]) {
                    sumB += S[i][j];
                }
            }
        }
        answer = Math.min(answer, Math.abs(sumA - sumB));
    }
}
