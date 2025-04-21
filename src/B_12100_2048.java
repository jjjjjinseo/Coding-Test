import java.io.*;
import java.util.*;

public class B_12100_2048 {
    static int N;
    static int [][] map;
    static int answer ;
    //0 상 1 하 2 좌 3 우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st = null;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bt(0, map);
        System.out.println(answer);
    }

    public static int [][] copyMap (int [][] board){
        int [][] copyMap = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                copyMap[i][j] = board[i][j];
            }
        }
        return copyMap;
    }
    public static int [][] move (int dir, int [][] board){
        int [][] copy  = copyMap(board);
        switch(dir){
            case 0:
                //위쪽으로 압축
                //열 추출
                for(int j=0; j<N; j++){
                    int [] col = new int[N];
                    for(int i=0; i<N; i++){
                        col[i] = copy[i][j];
                    }
                    int [] result = compressLine(col);
                    for(int i=0; i<N; i++){
                        copy[i][j] = result[i];
                    }
                }
                break;
            case 1:
                //아래쪽으로 압축
                //열 추출
                for(int j=0; j<N; j++){
                    int [] col = new int[N];
                    for(int i=N-1; i>=0; i--){
                        col[i] = copy[i][j];
                    }
                    //반대로 넣어줘야함
                    int [] result = compressLine(col);
                    for(int i=0; i<N; i++){
                        copy[i][j] = result[N-i-1];
                    }
                }
                break;
            case 2:
                //왼쪽으로 압축
                for(int i=0; i<N; i++){
                    int [] result = compressLine(copy[i]);
                    copy[i] = result;
                }
                break;
            case 3:
                //오른쪽으로 압축
                for(int i=0; i<N; i++){
                    int [] row = new int[N];
                    for(int j=0; j<N; j++){
                        row[N-j-1]=copy[i][j];
                    }
                    int [] result = compressLine(row);

                    //반대로 넣어줘야함
                    for(int j=0; j<N; j++){
                        copy[i][j] = result[N-j-1];
                    }
                }
                break;
        }
        return copy;
    }
    public static int [] compressLine(int [] line){
        Queue<Integer> que = new LinkedList<>();
        for(int num: line){
            if(num!=0){
                que.add(num);
            }
        }

        Queue<Integer> merged = new LinkedList<>();
        while(!que.isEmpty()){
            int cur = que.poll();

            //합쳐질 수 있음
            if(!que.isEmpty() && cur == que.peek()){
                merged.add(cur*2);
                que.poll();
            }
            else{
                merged.add(cur);
            }
        }

        while(merged.size()<N){
            merged.add(0);
        }
        int [] result = new int[N];
        int i=0;
        while(!merged.isEmpty()){
            result[i] = merged.poll();
            i++;
        }
        return result;
    }
    public static void bt(int cnt, int [][] board){
        if(cnt==5){
            answer = Math.max(answer, getMax(board));
            return;
        }
        for(int i=0; i<4; i++){
            int [][] result = move(i,board);
            bt(cnt+1, result);
        }
    }
    public static int getMax(int [][] copyMap){
        int max = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                max = Math.max(copyMap[i][j], max);
            }
        }
        return max;
    }
}
