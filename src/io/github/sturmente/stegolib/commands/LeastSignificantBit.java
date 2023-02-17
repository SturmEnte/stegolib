package io.github.sturmente.stegolib.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

import io.github.sturmente.stegolib.utility.NumeralSystemConverter;

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
		
		try {
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
		} catch(Exception error) {
			System.out.println("Error while executing command");
			error.printStackTrace();
		}
			
	}
	
	private void encode() throws Exception {
		System.out.println("Mode: Encode");
		
		File inputImage = new File(args[2]);
		File inputData = new File(args[3]);
		File outputImageFile = new File(args[4]);
		
		System.out.println("Input image: " + inputImage.getPath());
		System.out.println("Input data: " + inputData.getPath());
		System.out.println("Output image: " + outputImageFile.getPath());
		
		// Check if the files exist
		if(!inputImage.exists()) {
			System.out.println("The input image does not exist!");
			return;
		}
		
		if(!inputData.exists()) {
			System.out.println("The input data does not exist!");
			return;
		}
		
		if(outputImageFile.exists()) {
			if(!Arrays.stream(args).anyMatch("-ovwr"::equals)) {
				System.out.println("The output image file already exists. Do you want to overwrite the current file? [y/n]");

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
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		byte[] tempData = Files.readAllBytes(inputData.toPath()); 
		for(int i = 0; i < tempData.length; i++) {
			NumeralSystemConverter.decToBin(tempData[i], 8).forEach((n) -> data.add(n));
		}
		
		// Add 00000000 to the end of the data, so the decoder knows when to stop
		for(int i = 0; i < 8; i++) data.add(0);
		
		BufferedImage inputImg = ImageIO.read(inputImage); 
		BufferedImage outputImg = new BufferedImage(inputImg.getWidth(), inputImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int inputImageCapacity = inputImg.getWidth() * inputImg.getHeight() * 3;
		int inputDataSize = data.size();
		
		if(inputImageCapacity < inputDataSize) {
			System.out.println("The input image is to small for the input data. Your image must be at least " + Math.round(inputDataSize / 3) + " bits in size.");
		}
		
		System.out.println("Input image capacity: " + inputImageCapacity + " bits");
		System.out.println("Input data size: " + inputDataSize + " bits");
		
		for (int y = 0; y < inputImg.getHeight(); y++) {
			for(int x = 0; x < inputImg.getWidth(); x++) {
				Color color = new Color(inputImg.getRGB(x, y));
				
				int[] colors = {color.getRed(), color.getGreen(), color.getBlue()};
				
				if(data.size() > 0) {
					for(int i = 0; (i < colors.length) && (data.size() > 0); i++) {
						ArrayList<Integer> c = NumeralSystemConverter.decToBin(colors[i], 8);
						c.set(c.size() - 1, data.remove(0));
						colors[i] = NumeralSystemConverter.binToDec(c);
					}
				}
				
				outputImg.setRGB(x, y, new Color(colors[0], colors[1], colors[2], color.getAlpha()).getRGB());
			}
		}
		
		ImageIO.write(outputImg, "png", outputImageFile);
		
		System.out.println("Finished process!");
		
	}
	
	private void decode() throws Exception {
		System.out.println("Mode: Decode");
		
		File inputImage = new File(args[2]);
		File outputDataFile = new File(args[3]);
		
		System.out.println("Input image: " + inputImage.getPath());
		System.out.println("Output data: " + outputDataFile.getPath());
		
		// Check if the files exist
		if(!inputImage.exists()) {
			System.out.println("The input image does not exist!");
			return;
		}
		
		if(outputDataFile.exists()) {
			if(!Arrays.stream(args).anyMatch("-ovwr"::equals)) {
				System.out.println("The output data file already exists. Do you want to overwrite the current file? [y/n]");

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
		
		String data = new String();
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		int j = 0;
		boolean stop = false;
		
		for (int y = 0; y < inputImg.getHeight(); y++) {
			for(int x = 0; x < inputImg.getWidth(); x++) {
				Color color = new Color(inputImg.getRGB(x, y));
				
				int[] colors = {color.getRed(), color.getGreen(), color.getBlue()};
				
				for(int i = 0; i < colors.length; i++) {
					ArrayList<Integer> c = NumeralSystemConverter.decToBin(colors[i], 8);
					buffer.add(c.get(c.size() - 1));
					j++;
					if(j == 8) {
						int d = NumeralSystemConverter.binToDec(buffer);
						
						if(d == 0) {
							stop = true;
							break;
						}
						
						data += (char) d;
						buffer.clear();
						j = 0;
					}
				}
				
				if(stop) break;
			}
			
			if(stop) break;
		}
		
		Files.writeString(outputDataFile.toPath(), data);
		
		System.out.println("Finished process!");
	}
	
	public static void help() {
		System.out.println("Least Significant Bit:");
		System.out.println("lsb encode [input image path] [input data path] [output imgage path]");
		System.out.println("lsb decode [input image path] [output data path]");
		System.out.println("You can also add following tags at the end of the command:");
		System.out.println("-ovwr : This tag will lead to all existing files being just overwritten instead of asking if they shall be overwritten");
	}
	
}
