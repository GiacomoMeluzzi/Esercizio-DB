package lepackage.enterprise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerSingleton {
	
	private static ScannerSingleton instance = null;
	private static Scanner scan;
	
	private ScannerSingleton() {
		scan = new Scanner(System.in);
	}
	
	public static ScannerSingleton getInstance () {
		if (instance == null) {
			instance = new ScannerSingleton();
		}
		return instance;
	}
	
	public static String getString () throws InputMismatchException {
		if (instance == null) {
			getInstance();
		}
		return scan.nextLine();
	}
	
	public static int getInt() throws InputMismatchException {
		if (instance == null) {
			getInstance();
		}
		return Integer.parseInt(scan.nextLine());
	}
	
	public static float getFloat() throws InputMismatchException {
		if (instance == null) {
			getInstance();
		}
		return Float.parseFloat(scan.nextLine());
	}
	
	public static String getString (String messaggio) throws InputMismatchException {
		if (instance == null) {
			getInstance();		
		}
		System.out.println(messaggio);
		return scan.nextLine();
	}
	
	public static int getInt(String messaggio) throws InputMismatchException {
	    if (instance == null) {
	        getInstance();
	    }
	    System.out.println(messaggio);
	    return Integer.parseInt(scan.nextLine());
	}
	
	public static float getFloat(String messaggio) throws InputMismatchException {
	    if (instance == null) {
	        getInstance();
	    }
	    System.out.println(messaggio);
	    return Float.parseFloat(scan.nextLine());
	}
}
