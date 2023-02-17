package io.github.sturmente.stegolib.utility;

import java.util.ArrayList;
import java.util.Collections;

public class NumeralSystemConverter {
		
		// The first integer in the array is the least significant bit
		public static ArrayList<Integer> decToBin(int dec, int trailingZeros) {
			ArrayList<Integer> res = new ArrayList<Integer>();
			
			int rest = dec;
			
			while(rest > 0) {
				int mod = rest % 2;
				rest = (rest - mod) / 2;
				res.add(mod);
			}
			
			// Prevent the array from being empty
			while(res.size() < trailingZeros) res.add(0);
			
			Collections.reverse(res);
			
			return res;
		}
		
		public static int binToDec(ArrayList<Integer> bin) {
			int dec = 0;
			
			Collections.reverse(bin);
			
			for(int i = 0; i < bin.size(); i++) {
				dec += bin.get(i) * (Math.pow(2, i));
			}
			
			return dec;
		}
	
}
