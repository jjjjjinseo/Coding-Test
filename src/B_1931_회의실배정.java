import java.io.*;
import java.util.*;

public class B_1931_회의실배정 {
    static int answer = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        PriorityQueue <Meeting> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        while(!pq.isEmpty()){
            Meeting m = pq.poll();
            int start = m.start;
            int end = m.end;

            while(!pq.isEmpty()){
                Meeting nextM = pq.peek();
                int nextStart = nextM.start;
                int nextEnd = nextM.end;

                if(end > nextStart){
                    pq.poll();
                }
                else {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
    public static class Meeting implements Comparable<Meeting>{
        int start, end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo (Meeting m){
            if(this.end == m.end){
                return this.start - m.start;
            }
            return this.end - m.end;
        }
    }
}
