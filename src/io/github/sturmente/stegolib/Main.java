package io.github.sturmente.stegolib;

import io.github.sturmente.stegolib.modes.LeastSignificantBit;

public class Main {

	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println("Not enough arguments! Use the help command for further information.");
			return;
		}
		
		switch(args[0]) {
			case "lsb":
				LeastSignificantBit lsb = new LeastSignificantBit(args);
				lsb.run();
				break;
			case "help":
				LeastSignificantBit.help();
				break;
			default:
				System.out.println("Invalid command! Use the help command for further information.");
				return;
		}
	
	}

}
