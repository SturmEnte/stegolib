package io.github.sturmente.stegolib.utility;

import java.util.ArrayList;
import java.util.Collections;

public class NumeralSystemConverter {
		
		// The first integer in the array is the least significant bit
		public static ArrayList<Integer> decToBin(int dec, Boolean invert, int trailingZeros) {
			ArrayList<Integer> res = new ArrayList<Integer>();
			
			int rest = dec;
			
			while(rest > 0) {
				int mod = rest % 2;
				rest = (rest - mod) / 2;
				res.add(mod);
			}
			
			// Prevent the array from being empty
			while(res.size() < trailingZeros) res.add(0);
			
			if(!invert) {
				Collections.reverse(res);
			}
			
			return res;
		}
	
}
