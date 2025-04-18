import java.io.*;
import java.util.*;


public class B_1987_알파벳 {
    static int R,C;
    static String [][] map;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new String [R][C];

        for(int i=0; i<R; i++){
            String [] tmp = br.readLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = tmp[j];
            }
        }

        //dfs
        boolean [][] visit = new boolean[R][C];
        for(int i=0; i<R; i++){
            Arrays.fill(visit[i], false);
        }
        Set<String> set = new HashSet<>();
        set.add(map[0][0]);
        visit[0][0] = true;
        dfs(new int []{0,0}, visit, set, 1);
        System.out.println(answer);
    }
    public static void dfs(int [] start, boolean [][] visit, Set<String> set, int cnt){

        for(int i=0; i<4; i++){

            int newX = start[0]+dx[i];
            int newY = start[1]+dy[i];

            if(newX>=0 && newY>=0 && newX<R && newY <C){
                if(!visit[newX][newY] && !set.contains(map[newX][newY])){
                    visit[newX][newY] = true;
                    set.add(map[newX][newY]);

                    dfs(new int [] {newX, newY}, visit, set, cnt+1);

                    visit[newX][newY] = false;
                    set.remove(map[newX][newY]);
                }
            }
        }
        answer = Math.max(answer, cnt);
    }
}
