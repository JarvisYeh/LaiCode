package Algorithms.C17_CrossTraining1;

public class Test259_Move0sToTheEndII {
	// 将0移到最后，非零元素的相对位置保持不变
	public int[] moveZero(int[] array) {
		int slow = 0;
		int fast = 0;
		while (fast < array.length) {
			if (array[fast] != 0) {
				array[slow++] = array[fast++];
			} else {
				fast++;
			}
		}

		while (slow < array.length) {
			array[slow++] = 0;
		}
		return array;
	}

}
