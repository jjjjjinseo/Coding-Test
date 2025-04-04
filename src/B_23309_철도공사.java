import java.util.*;
import java.io.*;

public class B_23309_철도공사 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        Station station = new Station();

        int [] arr = new int[N];
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        station.init(arr);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int curNode = 0;
            int newNode = 0;
            switch (st.nextToken()){
                case "BN":
                    curNode = Integer.parseInt(st.nextToken());
                    newNode = Integer.parseInt(st.nextToken());
                    station.print(station.next[curNode]);
                    station.addAfter(curNode, newNode);
                    break; //i 담역 출력, 그 사이에 j 설립
                case "BP":
                    curNode = Integer.parseInt(st.nextToken());
                    newNode = Integer.parseInt(st.nextToken());
                    station.print(station.prev[curNode]);
                    station.addBefore(curNode, newNode);
                    break; //i 전역 출력, 그 사이에 j 설립
                case "CN":
                    curNode = Integer.parseInt(st.nextToken());
                    station.print(station.next[curNode]);
                    station.delete(station.next[curNode]);
                    break; //i 담역 폐쇄 그 역 출력,
                case "CP":
                    curNode = Integer.parseInt(st.nextToken());
                    station.print(station.prev[curNode]);
                    station.delete(station.prev[curNode]);
                    break; //i 전역 폐쇄 그 역 출력,
            }
        }
        System.out.println(sb.toString());
    }
    static class Station{
        public int [] prev;
        public int [] next;

        public Station(){
            prev = new int[1000001];
            next = new int[1000001];
        }

        public void init(int [] arr){
            for(int i=0; i<arr.length; i++){
                //시작노드
                if(i==0){
                    prev[arr[i]] = arr[arr.length-1];
                    next[arr[i]] = arr[i+1];
                }
                //마지막 노드
                else if(i==arr.length-1){
                    next[arr[i]] = arr[0];
                    prev[arr[i]] = arr[i-1];
                }
                else{
                    next[arr[i]] = arr[i+1];
                    prev[arr[i]] = arr[i-1];
                }
            }

        }
        public void addAfter (int target, int node){
            prev[node] = target;
            next[node] = next[target];
            prev[next[target]] = node;
            next[target] = node;
        }
        public void addBefore (int target, int node){
            prev[node] = prev[target];
            next[node] = target;
            next[prev[target]] = node;
            prev[target] = node;

        }
        public void delete(int target){
            //그럼 원래 target next는 prev의 next가 됨
            //원래 targe prev는 next의 prev가 됨

            next[prev[target]] = next[target];
            prev[next[target]] = prev[target];
        }
        public void print(int node){
            sb.append(node).append('\n');
        }
    }
}

