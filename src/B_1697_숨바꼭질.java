import java.io.*;
import java.util.*;

public class B_1697_숨바꼭질 {
    static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start, target));
    }

    public static int bfs(int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        queue.add(start);
        visited[start] = true;
        time[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == target) {
                return time[now];
            }

            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    time[next] = time[now] + 1;
                    queue.add(next);
                }
            }
        }

        return -1;
    }
}
