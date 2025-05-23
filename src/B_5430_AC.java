import java.io.*;
import java.util.*;

public class B_5430_AC {
	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringTokenizer st = null;

		for (int t = 0; t < T; t++) {
			String answer = null;
			String ops = br.readLine();
			N = Integer.parseInt(br.readLine());

			//[] 제거
			String s = br.readLine();
			s = s.substring(1, s.length() - 1);
			st = new StringTokenizer(s);

			Deque<Integer> dq = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				dq.add(Integer.parseInt(st.nextToken(",")));
			}

			int rCnt = 0;
			boolean straight = true;

			for (int i = 0; i < ops.length(); i++) {
				char op = ops.charAt(i);
				if (N == 0 && op == 'D') {
					answer = "error";
					break;
				}
				switch (op) {
					case 'R':
						rCnt++;
						straight = rCnt % 2 == 0;
						break;
					case 'D':
						if (dq.isEmpty()) {
							answer = "error";
							break;
						}
						if (straight) {
							dq.pollFirst();
						} else {
							dq.pollLast();
						}
						break;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			int i = 0;
			int ds = dq.size() - 1;
			while (answer == null && !dq.isEmpty()) {
				if (i < ds) {
					if (straight) {
						sb.append(dq.pollFirst()).append(",");
					} else {
						sb.append(dq.pollLast()).append(",");
					}
				} else {
					if (straight) {
						sb.append(dq.pollFirst()).append("]");
					} else {
						sb.append(dq.pollLast()).append("]");
					}
				}
				i++;
			}
			if (answer != null) {
				System.out.println(answer);
			} else {
				answer = sb.toString();

				if (answer.charAt(answer.length() - 1) != ']') {
					sb.append("]");
					answer = sb.toString();
				}
				System.out.println(answer);
			}

		}
	}
}

