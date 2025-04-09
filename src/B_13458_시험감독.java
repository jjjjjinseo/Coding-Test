import java.io.*;
import java.util.*;

public class B_13458_시험감독 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] students = new int [N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            students[i] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st2.nextToken());
        int C = Integer.parseInt(st2.nextToken());

        long answer = 0;

        for(int s: students){
            int num = s-B;
            if(num>0){
                answer ++;
                answer += num%C==0 ? num/C : num/C+1;
            }
            else{
                answer++;
            }
        }
        System.out.println(answer);
    }
}
