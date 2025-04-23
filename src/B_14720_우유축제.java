import java.io.*;
import java.util.*;

public class B_14720_우유축제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int [] shop = new int [N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        //0 1 2  반복
        int answer = 0;
        int cur = 0;
        for(int i=0; i<N; i++){
            shop[i] = Integer.parseInt(st.nextToken());
            if(shop[i]==cur){
                answer++;
                cur = (cur+1)%3;
            }
        }
        System.out.println(answer);

    }
}
