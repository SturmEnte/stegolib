package io.github.sturmente.stegolib.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeastSignificantBit {
	
	String[] args;
	
	public LeastSignificantBit(String[] args) {
		this.args = args;
	}
	
	public void run() {
		System.out.println("Least Significant Bit");
		
		if(args.length < 2) {
			System.out.println("Not enough arguments. Use the help command for further information.");
			return;
		}
		
		switch(args[1]) {
			case "encode":
				if(args.length < 5) {
					System.out.println("Not enough arguments. Use the help command for further information.");
					break;
				}
				encode();
				break;
			case "decode":
				if(args.length < 4) {
					System.out.println("Not enough arguments. Use the help command for further information.");
					break;
				}
				decode();
				break;
			default:
				System.out.println("Mode \"" + args[1] + "\" not found! Valid modes: encode, decode. Use the help command for further information.");
				break;
		}
			
	}
	
	private void encode() {
		System.out.println("Mode: Encode");
		
		File inputImage = new File(args[2]);
		File inputData = new File(args[3]);
		
		System.out.println("Input image: " + inputImage.getPath());
		System.out.println("Input data: " + inputData.getPath());
		
		// Check if the files exist
		if(!inputImage.exists()) {
			System.out.println("The input image does not exist!");
			return;
		}
		
		if(!inputData.exists()) {
			System.out.println("The input data does not exist!");
			return;
		}
		
	}
	
	private void decode() {
		System.out.println("Mode: Decode");
		System.out.println("Not implemented yet!");
	}
	
	public static void help() {
		System.out.println("Least Significant Bit:");
		System.out.println("lsb encode [input image path] [input data path] [output imgage path]");
		System.out.println("lsb decode [input image path] [output data path]");
	}
	
}
