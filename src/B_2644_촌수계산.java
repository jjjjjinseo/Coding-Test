
import java.io.*;
import java.util.*;

public class B_2644_촌수계산 {

    static int N;
    static int first, last, answer;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        first = Integer.parseInt(st.nextToken());
        last = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            //촌수는 방향이 없음.
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        boolean [] visit = new boolean [N+1];
        Arrays.fill(visit, false);

        visit[first] = true;
        dfs(first,0, visit);

        if(answer == 0 ){
            answer = -1;
        }
        System.out.println(answer);

    }
    public static void dfs(int start, int level, boolean [] visit){
        if(start == last){
            answer += level;
            return;
        }
        for(int i=0; i<graph.get(start).size(); i++){
            if(!visit[graph.get(start).get(i)]){
                visit[graph.get(start).get(i)] = true;
                //재귀
                dfs(graph.get(start).get(i), level+1, visit);
            }
        }
    }


}

