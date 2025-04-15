import java.io.*;
import java.util.*;

public class B_2667_단지번호붙이기 {
    static int total, house;
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
                    house = 0;
                    dfs(i, j, visit);
                    total++;
                    result.add(house);
                }
            }

        }
        System.out.println(total);
        Collections.sort(result);
        for(int num: result){
            System.out.println(num);
        }

    }

    public static void dfs(int i, int j, boolean [][] visit){
        visit[i][j] = true;
        house ++;
        for(int k=0; k<4; k++) {
            int newI = i + di[k];
            int newJ = j + dj[k];
            if (newI >= 0 && newJ >= 0 && newI < N && newJ < N) {
                if (!visit[newI][newJ] && map[newI][newJ] == 1) {
                    dfs(newI, newJ, visit);
                }
            }
        }
    }

}

