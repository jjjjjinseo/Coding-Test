import java.io.*;
import java.util.*;

public class B_18870_좌표압축 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Set<Integer> set = new HashSet<>();
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			set.add(num);
			numbers[i] = num;
		}

		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);

		Map<Integer, Integer> map = new HashMap<>();

		int idx = 0;
		for (int num : list) {
			map.put(num, idx);
			idx++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int num : numbers) {
			int i = map.get(num);
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
