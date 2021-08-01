package Algorithms.C26_Final;

import java.util.ArrayList;
import java.util.List;

public class Test_EventSchedule {
	// each capital case represents an event
	// 'x' represents a break
	// input "ABC", all event
	// return all possible schedule if there could be a break('x') between two events
	// return {"ABC", "AxBC", "ABxC", "AxBxC"}
	// TC: O(2^n)
	// SC: O(n)
	public List<String> schedule(String events) {
		List<String> res = new ArrayList<>();
		if (events.length() == 0) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(events.charAt(0));
		dfs(events, 1, sb, res);
		return res;
	}
	private void dfs(String events, int idx, StringBuilder sb, List<String> res) {
		// base case
		if (idx == events.length()) {
			res.add(sb.toString());
			return;
		}

		// case 1: take a break
		sb.append("x" + events.charAt(idx));
		dfs(events, idx + 1, sb, res);
		sb.delete(sb.length() - 2, sb.length());
		// case 2: not take a break
		sb.append(events.charAt(idx));
		dfs(events, idx + 1, sb, res);
		sb.deleteCharAt(sb.length() - 1);
	}

	public static void main(String[] args) {
		Test_EventSchedule t = new Test_EventSchedule();
		System.out.println(t.schedule("ABCDEF"));
	}
}
