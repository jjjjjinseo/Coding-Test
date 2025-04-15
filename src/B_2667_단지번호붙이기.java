import java.io.*;
import java.util.*;

public class B_2667_단지번호붙이기 {
    static int total;
    static int N ;
    static int [][] map;
    static int [] di ={-1,1,0,0};
    static int [] dj = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            String [] tmp = br.readLine().split("");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        boolean [][] visit = new boolean[N][N];
        for(int i=0 ; i<N; i++){
            Arrays.fill(visit[i], false);
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visit[i][j] && map[i][j]==1){
                    int houses = bfs(i, j, visit);
                    result.add(houses);
                }
            }

        }
        System.out.println(total);
        Collections.sort(result);
        for(int num: result){
            System.out.println(num);
        }

    }
    public static int bfs(int startI, int startJ, boolean [][] visit){
        Queue <int[]> que = new LinkedList<>();
        que.add(new int [] { startI, startJ});
        visit[startI][startJ]  = true;
        int house = 1;
        while(!que.isEmpty()){
            int [] peek = que.poll();
            int peekI = peek[0];
            int peekJ = peek[1];

            for(int i=0; i<4; i++){
                int newI = peekI+di[i];
                int newJ = peekJ+dj[i];
                if(newI>=0 && newJ>=0 && newI<N && newJ <N){
                    if(!visit[newI][newJ] && map[newI][newJ]==1){
                        visit[newI][newJ] = true;
                        house ++;
                        que.add(new int []{newI, newJ});
                    }
                }
            }
        }
        total ++;
        return house;
    }
}
