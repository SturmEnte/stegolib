package io.github.sturmente.stegolib.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
				try {
					encode();
				} catch(Exception error) {
					System.out.println("Error while executing command");
					error.printStackTrace();
				}
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
	
	private void encode() throws Exception {
		System.out.println("Mode: Encode");
		
		File inputImage = new File(args[2]);
		File inputData = new File(args[3]);
		File outputImage = new File(args[4]);
		
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
		
		if(outputImage.exists()) {
			if(!Arrays.stream(args).anyMatch("-ovwr"::equals)) {
				System.out.println("The output image path already exists. Do you want to overwrite the current file? [y/n]");

				Scanner in = new Scanner(System.in);
			    String answer = in.nextLine();
			    in.close();
				
			    if(answer.toLowerCase().equals("y")) {
			    	System.out.println("Process will be continued");
			    } else {
			    	System.out.println("Process will be canceled!");
			    	return;
			    }
			}
		}
		
		BufferedImage inputImg = ImageIO.read(inputImage); 
		BufferedImage outputImg = new BufferedImage(inputImg.getWidth(), inputImg.getHeight(), inputImg.getType());
		
		for (int y = 0; y < inputImg.getHeight(); y++) {
			for(int x = 0; x < inputImg.getWidth(); x++) {
				Color color = new Color(inputImg.getRGB(x, y));
				outputImg.setRGB(x, y, color.getRGB());
			}
		}
		
		ImageIO.write(outputImg, "png", outputImage);
		
		System.out.println("Finished process!");
		
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
