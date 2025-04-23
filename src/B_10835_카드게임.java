import java.io.*;
import java.util.*;

public class B_10835_카드게임{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] left = new int[N];
        int[] right = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            right[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];

        for(int l=N-1; l>=0; l--){
            for(int r=N-1; r>=0; r--){
                if(right[r] < left[l]){
                    dp[l][r] = dp[l][r+1]+right[r];
                }
                else{
                    dp[l][r] = Math.max(dp[l+1][r], dp[l+1][r+1]);
                }
            }
        }
        System.out.println(dp[0][0]);
    }
}
