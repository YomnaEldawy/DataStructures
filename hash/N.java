package eg.edu.alexu.csd.filestructure.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class N {
	int size;
	int[][] hFunction;
	ArrayList<Integer> hash;
	ArrayList<Integer> keys;
	int nKeys = 0;
	ArrayList<Integer>[] hashTable;

	public void readFile(File f) throws FileNotFoundException {
		// read text file and no. of keys
		nKeys = 0;
		Scanner s = new Scanner(f);
		while (s.hasNextInt()) {
			int key = s.nextInt();
			keys.add(key);
			nKeys++;
		}
		s.close();
		size = (int) (Math.log(nKeys * nKeys) / Math.log(2));
		generateHashFunction(size, 32);
		buildHash(nKeys);
	}

	public void buildHash(int n) {
		hashTable = new ArrayList[nKeys];
		for (int i = 0; i < n; i++) {
			int key = keys.get(i);
			int intIndex = calculateIndex(key);
			if(!hashTable[intIndex].contains(key)){
				hashTable[intIndex].add(key);
			}	
		}
		
		for(int i = 0 ; i < n ; i++){
			if(hashTable[i].size() > 1){
				N2 subHash = new N2();
				subHash.setKeys(hashTable[i]);
				int subn = hashTable[i].size();
				subHash.size = (int) (Math.log(subn * subn) / Math.log(2));
				subHash.generateHashFunction(size, 32);
				hashTable[i] = subHash.buildHash(nKeys);
			}
		}
	}

	public boolean find(int key) {
		int i = calculateIndex(key);
		return hash.get(i) == key;
	}

	private int calculateIndex(int n) {
		int[] binaryRep = convertToBinary(n);
		int[] binaryIndex = new int[size];
		int digit = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 32; j++) {
				digit = digit | (hFunction[i][j] & binaryRep[j]);
			}
			binaryIndex[i] = digit;
			digit = 0;
		}
		int intIndex = convertToDecimal(binaryIndex);
		return intIndex;
	}

	public int convertToDecimal(int[] n) {
		int value = 0;
		for (int i = 0; i < n.length; i++) {
			value = value + (int) (Math.pow(2, i)) * n[i];
		}
		return value;
	}

	public int[] convertToBinary(int n) {
		int[] binary = new int[32];
		int i = 0;
		while (n > 0) {
			binary[i] = n % 2;
			n = n / 2;
			i++;
		}
		return binary;
	}

	public void generateHashFunction(int n, int m) {
		hFunction = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Random r = new Random();
				hFunction[i][j] = r.nextInt(2) + 0;
			}
		}
	}

	public void setKeys(ArrayList<Integer> keys){
		this.keys = keys;
	}


}
