import java.io.*;
import java.util.*;

public class B_7576_토마토 {
    static int N, M;
    static int [][] box;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};
    static int answer = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        int oCnt = 0;
        int eCnt = 0;
        Queue<int []> que = new LinkedList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j]==1){
                    oCnt++;
                    que.add(new int[]{i,j,0});
                }
                else if(box[i][j]==-1){
                    eCnt++;
                }
            }
        }
        if(oCnt==N*M-eCnt){
            answer = 0;
            System.out.println(answer);
            return;
        }

        boolean [][] visit = new boolean [N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(visit[i], false);
        }
        cnt = que.size();
        bfs(que, visit);

        if(cnt != M*N-eCnt){
            answer = -1;
        }
        System.out.println(answer);

    }
    public static void bfs(Queue<int[]> que , boolean [][] visit){

        int preLevel = 0;

        while(!que.isEmpty()){

            int [] peek = que.poll();
            visit[peek[0]][peek[1]] = true;

            if(peek[2]!=preLevel){
                answer ++;
                preLevel = peek[2];
            }

            for(int i=0; i<4; i++){
                int newX = peek[0] + dx[i];
                int newY = peek[1] + dy[i];

                if(newX>=0 && newY>=0 && newX<N && newY<M){
                    if(!visit[newX][newY] && box[newX][newY]==0){
                        cnt ++;
                        box[newX][newY] = 1;
                        visit[newX][newY] = true;
                        que.add(new int []{newX, newY, peek[2]+1});
                    }
                }
            }
        }
    }
}