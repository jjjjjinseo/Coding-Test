
import java.io.*;
import java.util.*;

public class B_2644_촌수계산 {
    static int N;
    static int answer;
    static int parent;
    static StringBuilder sb, sb1, sb2;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int first = Integer.parseInt(st.nextToken());
        int last = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph.get(parent).add(child);
        }
        sb = new StringBuilder();
        sb.append(first).append(" ");
        dfs(first);
        sb1 = sb;

        sb = new StringBuilder();
        sb.append(last).append(" ");
        dfs(last);
        sb2 = sb;

        String [] tmp1 = sb1.toString().split(" ");
        String [] tmp2 = sb2.toString().split(" ");

        boolean sig = false;
        for (String s : tmp1) {
            if(!sig){
                for (String s2 : tmp2) {
                    if (Integer.parseInt(s) == Integer.parseInt(s2)) {
                        parent = Integer.parseInt(s2);
                        sig = true;
                        break;
                    }
                }
            }
        }

        if(parent == 0){
            System.out.println(-1);
            return;
        }
        bfs(parent, first, last);

        System.out.println(answer);

    }
    public static void dfs(int me){
        for(int i=1; i<=N; i++){
            for(int j=0; j<graph.get(i).size(); j++){
                if(graph.get(i).get(j)==me){

                    //부모를 찾음
                    sb.append(i).append(" ");
                    dfs(i);
                }
            }
        }
    }

    public static void bfs(int parent, int first, int last){
        Queue<int []> que = new LinkedList<>();
        int cnt = 0;
        que.add(new int []{ parent, 0 });

        if(parent == first || parent == last){
            cnt ++;
        }

        while(!que.isEmpty()){
            int [] peek = que.poll();

            for(int i=0; i<graph.get(peek[0]).size(); i++){
                if(graph.get(peek[0]).get(i) == first || graph.get(peek[0]).get(i) == last){
                    cnt++;
                    answer += peek[1]+1;
                }
                que.add(new int []{graph.get(peek[0]).get(i), peek[1]+1});
            }
            if(cnt == 2){
                break;
            }
        }
    }

}

