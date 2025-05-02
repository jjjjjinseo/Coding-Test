import java.io.*;
import java.util.*;

public class B_14888_연산자끼워넣기 {
    static int N;
    static int [] A;
    static int min=1000000001, max=-1000000001;
    //덧셈, 뺄셈 곱샘 나눗셈
    static int [] operations ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());

        A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operations = new int [4];
        for(int i=0; i<4; i++){
            operations[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, A[0]);
        System.out.println(max);
        System.out.println(min);
    }
    public static void dfs(int aIdx, int result){
        if(aIdx == N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i=0; i<4; i++){
            //다음 연산자.
            if( operations[i] != 0 ){
                operations[i]--;

                // 다음 값 계산
                int next = calc(result, A[aIdx], i);
                dfs(aIdx+1, next);

                operations[i]++;
            }

        }
    }
    public static int calc(int x, int y, int op){
        int result = 0;
        switch(op){
            case 0 :
                result = x+y;
                break;
            case 1 :
                result = x-y;
                break;
            case 2:
                result = x*y;
                break;
            case 3 :
                result = Math.abs(x)/y;
                if(x<0){
                    result *= -1;
                }
                break;
        }
        return result;
    }
}
