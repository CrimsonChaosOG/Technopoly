package com.technopoly.utils;

public class LogTester {

	public static void main(String[] args) {
		
		LogListener log = new LogListener(){
            @Override
            public void onLog(String msg) {
            	PrintMessage(msg);
            }
        };
        
        Debug.Log("Hello");
        Debug.Log("World");
        Debug.LogError("Big problem");
		

	}
	
	public static void PrintMessage(String s) {
		System.out.println(s);
	}

}
