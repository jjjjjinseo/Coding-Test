import java.io.*;
import java.util.*;

public class B_14502_연구소 {
    static int N, M, zero, sum;
    static int [][] map;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0){
                    zero++;
                }
            }
        }
        boolean [][] installed = new boolean[N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(installed[i], false);
        }
        install(0, copyMap(map), installed);
        System.out.println(max);
    }

    public static void spread(int[][] copyMap) {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> que = new LinkedList<>();

        // 모든 바이러스 위치 먼저 큐에 넣음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2) {
                    que.add(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }

        while (!que.isEmpty()) {
            int[] peek = que.poll();
            for (int i = 0; i < 4; i++) {
                int newX = peek[0] + dx[i];
                int newY = peek[1] + dy[i];

                if (checkRange(newX, newY) && !visit[newX][newY] && copyMap[newX][newY] == 0) {
                    visit[newX][newY] = true;
                    copyMap[newX][newY] = 2;
                    que.add(new int[]{newX, newY});
                }
            }
        }
    }

    // 벽 설치
    public static void install(int cnt ,int [][] copyMap, boolean [][] installed){
        if(cnt == 3){
            int[][] temp = copyMap(copyMap);
            spread(temp);
            max = Math.max(getSafeSpace(temp), max);
            return;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(copyMap[i][j] == 0 && !installed[i][j]){
                    installed[i][j] = true;
                    copyMap[i][j] = 1;

                    install(cnt+1, copyMap, installed);

                    installed[i][j] = false;
                    copyMap[i][j] = 0;
                }
            }
        }
    }

    public static int getSafeSpace(int [][] result){
        int answer = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(result[i][j] == 0){
                    answer++;
                }
            }
        }
        return answer;
    }

    public static int [][] copyMap(int [][] origin){
        int [][] copyMap = new int [N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copyMap[i][j] = origin[i][j];
            }
        }
        return copyMap;
    }

    public static boolean checkRange(int x, int y){
        return x>=0 && y>=0 && x<N && y<M ;
    }
}
