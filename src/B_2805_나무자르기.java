import java.io.*;
        import java.util.*;

public class B_2805_나무자르기 {
    static long [] trees;
    static int N, M;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        trees = new long [N];
        for(int i=0; i<N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);
        binarySearch();
        System.out.println(answer);
    }
    public static void binarySearch(){
        long left = 1;
        long right = trees[N-1];
        long mid = 0;
        answer = 0;

        while(left <= right){
            mid = (left+right)/2;
            if(getTree(mid) >= M ){
                answer = mid;
                left = mid +1;
            }
            else{
                right = mid-1;
            }
        }
    }
    public static long getTree(long H){
        long total = 0;
        for(long t: trees){
            if(t>H){
                total += t-H;
            }
        }
        return total;
    }
}
