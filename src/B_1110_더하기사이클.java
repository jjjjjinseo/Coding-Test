import java.io.*;
import java.util.*;

public class B_1110_더하기사이클 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();
        StringBuilder sb = new StringBuilder();
        if(N.length()==1){
            sb.append(0).append(N);
        }
        else {
            sb.append(N);
        }
        N = sb.toString();

        Queue<Character> que = new LinkedList<>();
        int sum = 0;
        for(int i=0; i<N.length(); i++){
            sum+=Character.getNumericValue(N.charAt(i));
        }

        que.add(N.charAt(N.length()-1));
        String sumS = String.valueOf(sum);
        que.add(sumS.charAt(sumS.length()-1));

        int answer = 0;
        while(!que.isEmpty()){
            int cur = Character.getNumericValue(que.poll());

            if(!que.isEmpty()){
                int peek = Character.getNumericValue(que.peek());
                StringBuilder nSb = new StringBuilder();
                nSb.append(cur).append(peek);
                answer++;

                if(nSb.toString().equals(N)){
                    break;
                }
                int next = cur+peek;
                String nextS = String.valueOf(next);

                que.add(nextS.charAt(nextS.length()-1));

            }
        }
        System.out.println(answer);

    }
}
