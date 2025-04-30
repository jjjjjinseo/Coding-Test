import java.io.*;
import java.util.*;

public class B_14503_로봇청소기 {
    static int N, M;
    static int answer = 1;
    // 북 동 남 서
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};
    static int [][] map ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int [N][M];

        st = new StringTokenizer(br.readLine());
        //로봇 시작
        int rX = Integer.parseInt(st.nextToken());
        int rY = Integer.parseInt(st.nextToken());
        int rD = Integer.parseInt(st.nextToken());

        boolean [][] visit = new boolean[N][M];
        for(int i=0; i<N; i++){
            Arrays.fill(visit[i], false);
        }
        //0청소 안된 칸, 1은 벽이 있는 것
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(rX, rY, rD, visit);
        System.out.println(answer);

    }
    //0 청소 x, 1 벽, 2 청소완
    public static void bfs(int rX, int rY, int rD, boolean [][] visit){
        Queue<int [] > que = new LinkedList<>();
        que.add(new int [] {rX, rY, rD});
        visit[rX][rY] = true;

        while(!que.isEmpty()){

            int [] peek = que.poll();
            int x = peek[0];
            int y = peek[1];
            int d = peek[2];
            map[x][y] = 2;

            int cnt = 0;
            int dCnt = 0;
            int dir = (d+3)%4;

            //반시계 방향으로 탐색
            while(dCnt < 4){
                int nX = x+dx[dir];
                int nY = y+dy[dir];

                if(check(nX, nY) && !visit[nX][nY] && map[nX][nY]==0){
                    cnt++;
                    answer ++;
                    visit[nX][nY] = true;
                    que.add(new int [] {nX, nY, dir});
                    break;
                }

                dir = (dir+3)%4;
                dCnt ++;
            }

            //만약 사방에 갈 수 있는 곳이 아예 없음
            if(cnt==0){
                int [] backIdx = back(x, y, d);
                if((map[backIdx[0]][backIdx[1]] == 1)|| (backIdx[0] == x && backIdx[1] == y)){
                    break;
                }
                visit[backIdx[0]][backIdx[1]] = true;
                que.add(backIdx);
            }
        }

    }
    public static int [] back(int x, int y, int d){
        switch (d){
            case 0:
                //북쪽일때  남쪽으로 한칸
                if(check(x+1, y)){
                    x+=1;
                }
                break;
            case 1:
                //동쪽일때 후진이면 서쪽으로 한칸
                if(check(x, y-1)){
                    y-=1;
                }
                break;
            case 2:
                //남쪽일때 후진이면 북쪽으로 한칸
                if(check(x-1, y)){
                    x-=1;
                }
                break;
            case 3:
                //서쪽일때 후진이면 동쪽으로 한칸
                if(check(x, y+1)){
                    y+=1;
                }
                break;
        }
        return new int []{x, y, d};
    }
    public static boolean check(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

}
