package io.github.sturmente.stegolib.modes;

public class LeastSignificantBit {
	
	String[] args;
	
	public LeastSignificantBit(String[] args) {
		this.args = args;
	}
	
	public void run() {
		System.out.println("Least Significant Bit");
		
		if(args.length < 4) {
			System.out.println("Not enough arguments. Use the help command for further information.");
			return;
		}
			
			
	}
	
	public static void help() {
		System.out.println("Least Significant Bit:");
		System.out.println("lsb encode [input image path] [input data path] [output imgage path]");
		System.out.println("lsb decode [input image path] [output data path]");
	}
	
}
