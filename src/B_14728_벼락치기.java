import java.io.*;
import java.util.*;

public class B_14728_벼락치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int [][] dp = new int [N+1][T+1];
        for(int i=0; i<=N; i++){
            Arrays.fill(dp[i], 0);
        }
        int [] time = new int[N+1];
        int [] score = new int [N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++){
            for(int j=0; j<=T; j++){
                if(time[i]<=j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-time[i]]+score[i]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][T]);

    }
}
