import java.util.*;
import java.io.*;

public class B_1654_랜선자르기 {
    static int K, N;
    static long [] lans;
    static long max = 1;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lans = new long [K];

        for(int k=0; k<K; k++){
            lans[k] = Integer.parseInt(br.readLine());
            max = Math.max(max, lans[k]);
        }

        //이분탐색
        long result = binarySearch();
        System.out.println(result);
    }
    public static long  binarySearch(){
        //1부터 최대 길이 까지
        long left = 1;
        long right = max;
        long mid = 0;
        long answer = 0;
        while(left<=right){
            mid = (left+right)/2;
            long cnt = calculateNum(mid);

            if(cnt >= N) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid-1;
            }
        }
        return answer;
    }
    public static long calculateNum (long mid){
        long cnt = 0;
        for(long l : lans){
            if(l >= mid){
                cnt += l/mid;
            }
        }
        return cnt;
    }
}
