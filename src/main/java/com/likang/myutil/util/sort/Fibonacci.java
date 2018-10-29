package com.likang.myutil.util.sort;

/**
 * 斐波那契    1 2 3 5 8 13 21。。。。
 * @author 32925
 *
 */
public class Fibonacci {
	
	static long fibonacci(int n) {
		if (n > 0) {
			if (1 == n) {
				return 1;
			} else if (2 == n) {
				return 2;
			} else {
				return fibonacci(n - 1) + fibonacci(n - 2);
			}
		} else {
			
			return 0;
		}
	}

	public static void main(String[] args) {
		System.err.println(fibonacci(7));
	}
}
