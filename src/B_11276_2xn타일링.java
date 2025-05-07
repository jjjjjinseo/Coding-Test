import java.io.*;
import java.util.*;

public class B_11276_2xn타일링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[1] = 1;
                continue;
            }
            if (i == 2) {
                dp[2] = 2;
                continue;
            }
            dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
        }
        System.out.println(dp[N]);
    }
}
