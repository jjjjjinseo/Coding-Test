import java.util.*;
import java.io.*;
public class B_18405_경쟁적전염 {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int K = Integer.parseInt(tmp[1]);

        int [][] map = new int [N+1][N+1];
        for(int i=0; i<=N; i++){
            Arrays.fill(map[i], 0);
        }

        Queue<int []> que = new LinkedList<>();
        ArrayList<int []> kList = new ArrayList<>();
        for(int i=0; i<=K; i++){
            kList.add(i,new int[]{});
        }
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                int k = Integer.parseInt(st.nextToken());
                map[i][j] = k;
                if(k!=0){
                    kList.add(k, new int[]{i,j,k});
                }
            }
        }

        String [] tmp2 = br.readLine().split(" ");
        int S = Integer.parseInt(tmp2[0]);
        int X = Integer.parseInt(tmp2[1]);
        int Y = Integer.parseInt(tmp2[2]);

        //처음에는 k번호 순으로 que에 넣어야함.
        for(int [] arr : kList){
            if(arr.length>0){
                que.add(new int[]{arr[0],arr[1],arr[2],1});
            }
        }
        int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

        while(!que.isEmpty()){
            int peekX = que.peek()[0];
            int peekY = que.peek()[1];
            int peekK = que.peek()[2];
            int peekL = que.peek()[3];
            que.poll();

            if(peekL > S){
                break;
            }
            //상하좌우 탐색
            for(int i=0; i<4; i++){
                int newX = peekX+dir[i][0];
                int newY = peekY+dir[i][1];
                //범위확인
                if(newX>=1 && newX<=N && newY>=1 && newY<=N ){
                    //방문한 적 있음
                    if(map[newX][newY]!=0){
                        continue;
                    }
                    map[newX][newY] = peekK;
                    que.add(new int[]{newX, newY, peekK, peekL+1});
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}

