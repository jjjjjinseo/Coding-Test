import java.io.*;

public class B_2579_계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N + 1];
        int[] dp = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(br.readLine());
            stair[i] = h;
        }
        dp[0] = 0;
        stair[0] = 0;

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[1] = stair[1];
                continue;
            }
            if (i == 2) {
                dp[2] = stair[1] + stair[2];
                continue;
            }
            dp[i] = Math.max(
                    dp[i - 2] + stair[i],
                    dp[i - 3] + stair[i - 1] + stair[i]
            );
        }

        System.out.println(dp[N]);
    }
}
