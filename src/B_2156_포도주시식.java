import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2156_포도주시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] wines = new int[n];
        for (int i = 0; i < n; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        dp[0] = wines[0];

        for(int i=1; i<n; i++){
            if(i==1){
                dp[1] = wines[0]+wines[1];
            }
            else if(i==2){
                dp[2] = Math.max(dp[1], Math.max(wines[0]+wines[2], wines[1]+wines[2]));
            }
            else{
                dp[i] = Math.max(dp[i-1],
                        Math.max(dp[i-2] + wines[i],
                                dp[i-3] + wines[i-1]+ wines[i]));
            }

        }

        System.out.println(dp[n-1]);
    }
}