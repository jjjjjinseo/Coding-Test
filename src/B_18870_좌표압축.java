import java.io.*;
import java.util.*;

public class B_18870_좌표압축 {
	static List<Integer> list;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();

		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] tmp = new int[2000001];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			tmp[num + 1000000]++;
			numbers[i] = num;
		}

		for (int num : numbers) {
			if (tmp[num + 1000000] > 1) {
				tmp[num + 1000000]--;
				continue;
			}
			list.add(num);
		}

		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		for (int num : numbers) {
			int idx = Collections.binarySearch(list, num);
			sb.append(idx).append(" ");
		}
		System.out.println(sb);
	}
}
