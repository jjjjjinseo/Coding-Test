import java.io.*;
import java.util.*;

public class B_3190_뱀 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        int [][] map = new int [N+1][N+1];
        for(int i=0; i<=N; i++){
            Arrays.fill(map[i], 0);
        }
        for(int i=0; i<K ; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
        //방향전환
        int L = Integer.parseInt(br.readLine());
        PriorityQueue<Spin> sQue = new PriorityQueue<>();

        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            sQue.add(new Spin(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        //이제 시작 (1,1)에서
        // 오른쪽, 왼쪽, 아리쪽, 위쪽,
        int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

        LinkedList<Snake> que = new LinkedList<>();
        que.add(new Snake(1,1));
        map[1][1] = 2;
        int time = 1;
        int dIdx = 0;
        while(!que.isEmpty()){
            Snake head = que.peekLast();
            int row = head.row;
            int col = head.col;
            //일단 한칸 이동
            int newR = row+dir[dIdx][0];
            int newC = col+dir[dIdx][1];
            //범위 확인
            if(newR < 1 || newR > N || newC< 1 || newC>N){
                break;
            }
            if(map[newR][newC] == 2){
                break;
            }

            //사과 있음, 꼬리 안움직
            if(map[newR][newC] == 1){
                map[newR][newC] = 2;
                que.add(new Snake(newR, newC));
            }
            else if(map[newR][newC] == 0){
                que.add(new Snake(newR, newC));
                map[newR][newC] = 2;

                Snake tail = que.poll();
                map[tail.row][tail.col] = 0;
            }

            //방향 변환
            if(!sQue.isEmpty()){
                if(time == sQue.peek().time){
                    if(sQue.peek().dir.equals("D")){
                        //오른쪽 회전
                        switch (dIdx){
                            case 0 :
                                dIdx = 2;
                                break;
                            case 1 :
                                dIdx = 3;
                                break;
                            case 2 :
                                dIdx = 1;
                                break;
                            case 3 :
                                dIdx = 0;
                                break;
                        }
                    }
                    else if(sQue.peek().dir.equals("L")){
                        //왼쪽 회전
                        switch (dIdx){
                            case 0 :
                                dIdx = 3;
                                break;
                            case 1 :
                                dIdx = 2;
                                break;
                            case 2 :
                                dIdx = 0;
                                break;
                            case 3 :
                                dIdx = 1;
                                break;
                        }
                    }
                    sQue.poll();
                }

            }
            time++;
        }
        System.out.println(time);
    }

}
class Spin implements Comparable<Spin>{
    int time;
    String dir;
    Spin(int time, String dir){
        this.time= time;
        this.dir= dir;
    }

    @Override
    public int compareTo(Spin s){
        return this.time - s.time;
    }
}
class Snake{
    int row, col;
    Snake(int row, int col){
        this.row = row;
        this.col = col;
    }
}
