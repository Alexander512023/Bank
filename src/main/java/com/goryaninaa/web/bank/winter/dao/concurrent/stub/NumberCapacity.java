package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

public class NumberCapacity {
	private static int numberCounter = 1001;
	
	public NumberCapacity() {
	}
	
	public int getNumber() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return numberCounter++;
	}
}
