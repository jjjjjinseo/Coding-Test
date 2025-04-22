import java.io.*;
import java.util.*;

public class B_1316_그룹단어체커 {

    static int answer ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String [] words = new String[N];

        for(int i=0; i<N; i++){
            words[i] = br.readLine();
        }

        for(String word: words){
            if(isGroupWord(word)){
                answer++;
            };
        }
        System.out.println(answer);
    }
    public static boolean isGroupWord(String word) {
        boolean[] used = new boolean[26];
        char prev = 0;
        for (char ch : word.toCharArray()) {
            if (ch != prev) {
                if (used[ch - 'a']) return false;
                used[ch - 'a'] = true;
            }
            prev = ch;
        }
        return true;
    }

}
