import java.io.*;
import java.util.*;

public class B_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        int [] day = new int[N+1];
        int [] cost = new int[N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int [] dp = new int[N+1];
        Arrays.fill(dp, 0);

        for(int i=1; i<=N; i++){

            dp[i] = Math.max(dp[i], dp[i-1]);

            if(i+day[i] <= N+1){
                int nextDay = i+day[i]-1;
                dp[nextDay] = Math.max(dp[nextDay], cost[i]+dp[i-1]);
            }
        }

        System.out.println(dp[N]);
    }
}
