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
                    // 익은 토마토 위치 (i,j) & 날짜
                    que.add(new int[]{i,j,0});
                }
                else if(box[i][j]==-1){
                    eCnt++;
                }
            }
        }
        //담길 때부터 다 익어있을 때
        if(oCnt==N*M-eCnt){
            answer = 0;
            System.out.println(answer);
            return;
        }

        cnt = oCnt;
        bfs(que);

        //모두 익을 수 없을 때
        if(cnt != M*N-eCnt){
            answer = -1;
        }
        System.out.println(answer);

    }
    public static void bfs(Queue<int[]> que){
        int day = 0;
        while(!que.isEmpty()){

            int [] peek = que.poll();
            day = peek[2];

            for(int i=0; i<4; i++){
                int newX = peek[0] + dx[i];
                int newY = peek[1] + dy[i];

                if(newX>=0 && newY>=0 && newX<N && newY<M){
                    if(box[newX][newY]==0){
                        cnt ++;
                        box[newX][newY] = 1;
                        que.add(new int []{newX, newY, peek[2]+1});
                    }
                }
            }
        }
        answer = day;
    }
}