package io.github.sturmente.stegolib.utility;

import java.util.ArrayList;

public class NumeralSystemConverter {
		
		// The first integer in the array is the least significant bit
		public static ArrayList<Integer> DecToBin(int dec) {
			ArrayList<Integer> res = new ArrayList<Integer>();
			
			int rest = dec;
			
			while(rest > 0) {
				int mod = rest % 2;
				rest = (rest - mod) / 2;
				res.add(mod);
			}
			
			// Prevent the array from being empty
			if(dec == 0) res.add(0);
			
			return res;
		}
	
}
