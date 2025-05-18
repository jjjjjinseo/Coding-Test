import java.io.*;
import java.util.*;

public class B_15686_치킨배달 {
    static int N, M;
    static int[][] city;
    static int[] answer;
    static int result = 987654321;
    static List<int[]> houses, chickens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N + 1][N + 1];

        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                } else if (city[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }
        answer = new int[houses.size()];

        boolean[] visit = new boolean[chickens.size()];
        dfs(0, 0, visit);

        System.out.println(result);
    }

    public static void dfs(int idx, int cnt, boolean[] visit) {
        if (cnt == M) {
            int i = 0;
            Arrays.fill(answer, 987654321);

            for (int[] house : houses) {
                for (int j = 0; j < chickens.size(); j++) {
                    if (visit[j]) {
                        int[] cIdx = chickens.get(j);
                        answer[i] = Math.min(answer[i], getDistance(cIdx[0], cIdx[1], house[0], house[1]));
                    }
                }
                i++;
            }

            int cur = 0;
            for (int num : answer) {
                cur += num;
            }
            result = Math.min(result, cur);
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i + 1, cnt + 1, visit);
                visit[i] = false;
            }
        }
    }

    //집마다 계산하기
    public static int getDistance(int cx, int cy, int hx, int hy) {
        return Math.abs(cx - hx) + Math.abs(cy - hy);
    }
}
