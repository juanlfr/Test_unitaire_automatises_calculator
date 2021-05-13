package com.openclassrooms.testing;

import java.util.LinkedList;
import java.util.List;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public void longCalcul() {
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	List<Integer> integers = new LinkedList<Integer>();

	public List<Integer> digitsSet(int number) {

		if (number > 0) {
			digitsSet(number / 10);
			integers.add(number % 10);
		}
		System.out.println(integers);
		return integers;

//		while (number > 0) {
//			integers.add(number % 10);
//			number = number / 10;
//		}
//		return integers;
//	}
	}
}