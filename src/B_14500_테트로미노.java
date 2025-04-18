import java.io.*;
import java.util.*;

public class B_14500_테트로미노 {
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,0};

    static int [][] paper;
    static int N, M;
    static int max =0 ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int [N][M];

        for(int i=0; i<N;  i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean [][] visit = new boolean [N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(visit[i], false);
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visit[i][j] = true;
                dfs(new int [] {i ,j}, paper[i][j], visit, 1);
                visit[i][j] =false;
            }
        }
        System.out.println(max);

    }
    public static void dfs(int [] start, int answer, boolean [][] visit, int cnt){
        if(cnt == 4){
            max = Math.max(answer, max);
            return;
        }
        for(int i=0; i<4; i++){
            int newX = start[0] + dx[i];
            int newY = start[1] + dy[i];
            if(check(newX,newY) && !visit[newX][newY]){
                if (cnt == 2) {
                    visit[newX][newY] = true;
                    dfs(new int [] {start[0] , start[1]}, answer + paper[newX][newY], visit, cnt+1);
                    visit[newX][newY] = false;
                }
                visit[newX][newY] = true;
                dfs(new int [] {newX , newY}, answer + paper[newX][newY], visit, cnt+1);
                visit[newX][newY] = false;
            }
        }
    }
    public static boolean check(int startX, int startY){
        return startX >= 0 && startY >= 0 && startX < N && startY < M;
    }
}
