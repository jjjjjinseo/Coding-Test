import java.io.*;
import java.util.*;

public class B_14499_주사위굴리기 {
    static int N, M, X, Y, K ;
    static int [] dy = {1,-1,0,0};
    static int [] dx = {0, 0,-1,1};
    static int [][] dice = {{-1,0,-1},{0,0,0},{-1,0,-1},{-1,0,-1}};
    static int [][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int inst = Integer.parseInt(st.nextToken());

            switch (inst) {
                case 1:
                    //동
                    if (checkRange(X + dx[inst - 1], Y + dy[inst - 1])) {
                        X += dx[inst - 1];
                        Y += dy[inst - 1];
                        east();
                        if (map[X][Y] != 0) {
                            changeDice(X, Y);
                            changeMap(X, Y, 0);
                        } else {
                            changeMap(X, Y, -1);
                        }

                        printDice();
                    }

                    break;
                case 2:
                    //서
                    if (checkRange(X + dx[inst - 1], Y + dy[inst - 1])) {
                        X += dx[inst - 1];
                        Y += dy[inst - 1];
                        west();
                        if (map[X][Y] != 0) {
                            changeDice(X, Y);
                            changeMap(X, Y, 0);
                        } else {
                            changeMap(X, Y, -1);
                        }

                        printDice();
                    }

                    break;
                case 3:
                    //북
                    if (checkRange(X + dx[inst - 1], Y + dy[inst - 1])) {
                        X += dx[inst - 1];
                        Y += dy[inst - 1];

                        north();
                        if (map[X][Y] != 0) {
                            changeDice(X, Y);
                            changeMap(X, Y, 0);
                        } else {
                            changeMap(X, Y, -1);
                        }

                        printDice();
                    }

                    break;
                case 4:
                    //남
                    if (checkRange(X + dx[inst - 1], Y + dy[inst - 1])) {
                        X += dx[inst - 1];
                        Y += dy[inst - 1];

                        south();
                        if (map[X][Y] != 0) {
                            changeDice(X, Y);
                            changeMap(X, Y, 0);
                        } else {
                            changeMap(X, Y, -1);
                        }
                        printDice();
                    }

                    break;

            }
        }

    }
    public static void south(){
        int [] tmp = new int[4];
        for(int i=0; i<4; i++){
            tmp[i] = dice[i][1];
        }
        for(int i=0; i<4; i++){
            dice[(i+1)%4][1] = tmp[i];
        }
    }
    public static void east(){
        int [] tmp = {dice[1][1], dice[1][0], dice[1][2], dice[1][0], dice[2][1], dice[3][1]};

        dice[1][1] = tmp[3];
        dice[1][2] = tmp[0];
        dice[3][1] = tmp[2];
        dice[1][0] = tmp[5];

    }
    public static void west(){
        int [] tmp = {dice[1][1], dice[1][0], dice[1][2], dice[1][0], dice[2][1], dice[3][1]};

        dice[1][1] = tmp[2];
        dice[1][0] = tmp[0];
        dice[3][1] = tmp[3];
        dice[1][2] = tmp[5];

    }
    public static void north(){
        int [] tmp = new int[4];
        for(int i=0; i<4; i++){
            tmp[i] = dice[i][1];
        }
        for(int i=0; i<4; i++){
            dice[(i+3)%4][1] = tmp[i];
        }
    }

    public static void printDice(){
        System.out.println(dice[1][1]);
    }
    public static void changeMap (int i, int j, int num){
        if(num == 0){
            map[i][j] = 0;
            return;
        }
        map[i][j] = dice[3][1];
    }
    public static void changeDice(int i, int j){
        dice[3][1] = map[i][j];
    }
    public static boolean checkRange(int x, int y){
        if(x >=0 && y>=0 && x<N && y<M){
            return true;
        }
        return false;
    }
}
