package Practice.P14_PracticeProblems;

public class LeetCode65_ValidNumber {
	public boolean isNumber(String s) {
		char[] arr = s.trim().toCharArray();
		boolean seeE = false;
		boolean seeNum = false;
		boolean seeNumAftE = false;
		boolean seeDot = false;
		boolean seeSign = false;
		boolean seeSignAftE = false;
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (c >= '0' && c <= '9') {
				// set flags
				if (!seeE) seeNum = true;
				if (seeE) seeNumAftE = true;
			} else if (c == 'e' || c == 'E') {
				if (seeE) return false;     // EE
				if (!seeNum) return false;  // +E
				// set flag
				seeE = true;
			} else if (c == '.') {
				if (seeDot) return false;   // 8.9.
				if (seeE) return false;     // E2.
				// set flag
				seeDot = true;
			} else if (c == '+' || c == '-') {
				if (seeSign && !seeNum) return false;   // ++
				if (seeNumAftE) return false;           // E2+
				if (seeNum && !seeE) return false;      // 2+
				if (seeSignAftE) return false;          // 2E++
				if (!seeE && seeDot) return false;      // .-
				// set flags
				if (!seeE) seeSign = true;
				if (seeE) seeSignAftE = true;
			} else {
				return false;
			}
		}
		if (seeDot && !seeNum) return false;            //.
		if (seeE && !seeNumAftE) return false;          //123E
		return true;
	}

}
