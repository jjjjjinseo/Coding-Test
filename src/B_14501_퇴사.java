import java.io.*;
import java.util.*;

public class B_14501_퇴사 {
    static int N, answer;
    static int [] time, cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        time = new int [N+1];
        cost = new int [N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }
        boolean [] visit = new boolean[N+1];
        Arrays.fill(visit, false);

        for(int i=1; i<=N; i++){
            if(i+time[i]-1 <= N){
                visit[i] = true;
                dfs(i, cost[i], visit);
                Arrays.fill(visit, false);
            }
        }

        System.out.println(answer);

    }
    public static void dfs(int start, int result, boolean [] visit){

        answer = Math.max(answer, result);

        for(int i = start + time[start]; i<=N; i++){
            if(!visit[i] && i+time[i]-1 <= N ){
                visit[i] = true;
                dfs(i, result + cost[i], visit);
                visit[i] = false;
            }
        }
    }

}
