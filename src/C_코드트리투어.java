import java.util.*;
import java.io.*;

public class C_코드트리투어 {
    static int INF = 987654321;
    static int start = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int Q = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        ArrayList<ArrayList<Land>> graph = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        for(int k=0; k<Q; k++){
            st = new StringTokenizer(br.readLine());
            String category = st.nextToken();

            int answer  = 0;
            switch (category){
                case "100": //그래프 생성
                    int n = Integer.parseInt(st.nextToken());
                    for(int i=0; i<n; i++){
                        graph.add(i, new ArrayList<>());
                    }
                    int m = Integer.parseInt(st.nextToken());
                    for(int i=0; i<m; i++){
                        int v = Integer.parseInt(st.nextToken());
                        int u = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());
                        graph.get(v).add(new Land(u,w));
                        graph.get(u).add(new Land(v,w));
                    }
                    break;
                case "200":
                    int id = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());
                    products.add(new Product(id, revenue, dest));
                    break;
                case "300": //여행 상품 취소
                    int rId = Integer.parseInt(st.nextToken());
                    for(Product p : products){
                        if(p.id == rId){
                            products.remove(p);
                            break;
                        }
                    }
                    break;
                case "400": //최적 여행상품 판매
                    int [] result = new int[graph.size()];
                    Arrays.fill(result, INF);

                    result = getCost(result, start, graph);
                    answer = selectProduct(products, result);
                    bw.write(answer+"\n");

                    break;
                case "500": //여행상품 출발지 변경
                    start = Integer.parseInt(st.nextToken());
                    break;
            }

        }
        bw.flush();
        bw.close();
    }
    // start에서 최단 거리 구하기
    public static int[] getCost(int [] result, int start, ArrayList<ArrayList<Land>> graph){
        PriorityQueue<Land> pq = new PriorityQueue<>();
        result[start] = 0;
        pq.add(new Land(start, 0));

        while(!pq.isEmpty()){
            Land peekLand = pq.poll();
            int id = peekLand.getId();
            int weight = peekLand.getWeight();

            if(result[id]<weight){
                continue;
            }

            for(int i=0; i<graph.get(id).size(); i++){
                Land nextLand = graph.get(id).get(i);
                //바로 직행 vs 돌아감.
                if(result[nextLand.getId()] > nextLand.getWeight() + weight) {
                    result[nextLand.getId()] = nextLand.getWeight() + weight;
                    pq.add(new Land(nextLand.getId(), result[nextLand.getId()]));
                }

            }
        }

        return result;

    }
    //최적 상품 고르기
    public static int selectProduct (ArrayList<Product> products, int [] result){
        PriorityQueue<Selection> pq = new PriorityQueue<>();
        int answer = 0;
        for(Product p : products){
            int dest = p.getDest();
            int revenue = p.getRevenue();
            int id = p.getId();

            int val = result[dest] == INF ? -1 : revenue - result[dest];
            pq.add(new Selection(val, id));
        }

        if(pq.isEmpty()) {return -1;}
        while (!pq.isEmpty()) {
            Selection peekS = pq.poll();
            int peekSId = peekS.getId();
            int peekSVal = peekS.getVal();

            if(peekSVal < 0) continue;

            // 삭제
            Iterator<Product> iter = products.iterator();
            while(iter.hasNext()) {
                Product p = iter.next();
                if(p.getId() == peekSId) {
                    iter.remove();
                    break;
                }
            }
            return peekSId;
        }
        return -1;
    }

}
class Land implements Comparable<Land>{
    int id;
    int weight;
    public Land(int id, int weight){
        this.id = id;
        this.weight = weight;
    }
    public int getId(){
        return this.id;
    }
    public int getWeight(){
        return this.weight;
    }

    @Override
    public int compareTo (Land l){
        return this.weight - l.weight;
    }
}
class Product {
    int id;
    int revenue;
    int dest;
    public Product(int id, int revenue, int dest){
        this.id = id;
        this.revenue = revenue;
        this.dest = dest;
    }
    public int getId(){
        return this.id;
    }
    public int getRevenue(){
        return this.revenue;
    }
    public int getDest(){
        return this.dest;
    }


}
class Selection implements Comparable<Selection>{
    int val;
    int id;
    public Selection(int val, int id){
        this.val = val;
        this.id = id;
    }
    public int getVal(){
        return this.val;
    }
    public int getId(){
        return this.id;
    }

    @Override
    public int compareTo (Selection s){
        if(this.val == s.val){
            return this.id - s.id;
        }
        return s.val - this.val;
    }
}