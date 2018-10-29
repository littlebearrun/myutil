package com.likang.myutil.util.sort;

/**
 *两个有序数组合并成一个有序数组
 * @author 32925
 *
 */
public class SortMerge {

	public static void merge(int[] a, int[] b) {
		int l = a.length + b.length;
		int[] temp = new int[l];
		int i = 0, j = 0, h = 0;
		
		while (i < a.length || j < b.length) {
			
			if (i == a.length && j < b.length) {
				temp[h++] = b[j++];
				
			} else if (i < a.length && j == b.length) {
				temp[h++] = a[i++];
				
			} else if (a[i] <= b[j]) {
				temp[h++] = a[i++];
				
			} else if (a[i] > b[j]) {
				temp[h++] = b[j++];
			}
		}
		
		for (int m : temp) {
			System.out.print(m + "  ");
		}
	}
	
	
	

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		int[] b = { 2, 3, 4, 7 };
		merge(a, b);
	}
}

