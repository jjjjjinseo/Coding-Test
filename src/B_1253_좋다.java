import java.io.*;
import java.util.*;

public class B_1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int answer = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = N - 1; j > i + 1; j--) {
                int result = arr[i + 1] + arr[j];
                if (arr[i] == result) {
                    answer++;
                    break;
                }
            }
            if (i > 1) {
                for (int j = 0; j < i; j++) {
                    int result = arr[i - 1] + arr[j];
                    if (arr[i] == result) {
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
