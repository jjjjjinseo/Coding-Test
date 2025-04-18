import java.io.*;
import java.util.*;

public class B_11724_연결요소의개수 {
    static int N, M;
    static  ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        boolean [] visit = new boolean [N+1];
        Arrays.fill(visit,false);

        for(int i=1; i<=N; i++){
            //시작
            if(!visit[i]){
                answer++;
                bfs(i, visit);
            }
        }
        System.out.println(answer);

    }
    public static void bfs(int start, boolean [] visit){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            int peek = que.poll();
            for(int i=0; i<graph.get(peek).size(); i++){
                if(!visit[graph.get(peek).get(i)]){
                    visit[graph.get(peek).get(i)] = true;
                    que.add(graph.get(peek).get(i));
                }
            }
        }

    }
}
