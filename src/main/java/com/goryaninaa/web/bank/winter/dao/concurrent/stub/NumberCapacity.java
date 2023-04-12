package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

public class NumberCapacity {
	private static int numberCounter = 1001;
	
	public NumberCapacity() {
	}
	
	public int getNumber() {
		return numberCounter++;
	}
}
