package Algorithms.C02_Recursion1AndSortingAlgorithms;

public class Test340_InsertionSort {
	public void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		Test340_InsertionSort t = new Test340_InsertionSort();
		int[] arr = {2, 6, 3, 1, 9};
		t.insertionSort(arr);
		for (int i : arr) {
			System.out.print(i + "\t");
		}
	}

}
