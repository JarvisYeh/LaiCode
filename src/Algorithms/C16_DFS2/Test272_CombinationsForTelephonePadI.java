package Algorithms.C16_DFS2;

import com.sun.tools.classfile.CharacterRangeTable_attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test272_CombinationsForTelephonePadI {
	public String[] combinations(int number) {
		HashMap<Character, String> keypad = new HashMap<>();
		keypad.put('0', "");
		keypad.put('1', "");
		keypad.put('2', "abc");
		keypad.put('3', "def");
		keypad.put('4', "ghi");
		keypad.put('5', "jkl");
		keypad.put('6', "mno");
		keypad.put('7', "pqrs");
		keypad.put('8', "tuv");
		keypad.put('9', "wxyz");
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		DFS(0, keypad, sb, res, Integer.toString(number));
		String[] stringArr = new String[res.size()];
		for (int i = 0; i < stringArr.length; i++) {
			stringArr[i] = res.get(i);
		}
		return stringArr;
	}

	private void DFS(int index, HashMap<Character, String> keypad, StringBuilder sb, List<String> res, String num) {
		if (index == num.length()) {
			res.add(new String(sb));
			return;
		}
		String characters = keypad.get(num.charAt(index));
		if (characters.length() == 0) {
			DFS(index + 1, keypad, sb, res, num);
		} else {
			for (char c : characters.toCharArray()) {
				sb.append(c);
				DFS(index + 1, keypad, sb, res, num);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Test272_CombinationsForTelephonePadI test = new Test272_CombinationsForTelephonePadI();
		String[] res = test.combinations(10);
		System.out.println(res.length);
	}
}
