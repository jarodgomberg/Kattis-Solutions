//Jarod Gomberg - 5106937 - ja545977
//Feb. 28, 2022
//COP3503 - Guha
//kattis RP3 - ternarian weights

/*
	Kattis: ternarian weights

	Write a program that accepts as input the weight of an object in base 10 
		outputs the weights to be placed in both pans.
		
*/
import java.util.*;

public class weights {

	public static final int SIZE = 20;

	public static void main(String[]  args) 
	{	
		Scanner stdin = new Scanner(System.in);
		
		//Read input # of testcases
		int n = stdin.nextInt();
		
		//make array to store ternary numbers (powers of 3)
		int powers[] = new int[SIZE];
		powers[0] = 1;
		for(int i = 1; i < SIZE; i++) {
			powers[i] = (powers[i - 1])*3;
		}

		//process test cases and solve accordingly
		for(int m = 0; m < n; m++) {

			//get weight of obj
			int weight = stdin.nextInt();

			//to store values for each side of pan
			ArrayList<Integer> left = new ArrayList<Integer>();
			ArrayList<Integer> right = new ArrayList<Integer>();

			/*
				ensure weights are being processed in base 3 and that largest amounts
				are being used to satisfy conditions

				modify to pass negatives but maintain base 3
			*/ 
			int num[] = new int[SIZE];
			for(int i = SIZE-1; i>= 0; i--) {
				while(weight >= powers[i]) {
					weight -= powers[i];
					num[i]++;
				}
			}
			for(int i = 0; i < SIZE-1; i++) {
				if(num[i] == 3) {
					num[i + 1]++;
					num[i] = 0;
				}
				else if (num[i] == 2) {
					num[i + 1]++;
					num[i] = -1;
				}
			}
			
			//Get and store necessary weights per side of pan
			for(int i = SIZE-1; i >= 0; i--) {
				if(num[i] < 0) {
					left.add(i);
				}
				else if(num[i] > 0) {
					right.add(i);
				}
			}
			
			//print accordingly 
			System.out.print("left pan:");
			for(Integer i : left) {
				System.out.print(" " + powers[i]);
			}
			System.out.println();
	
			System.out.print("right pan:");
			for(Integer i : right) {
				System.out.println(" " + powers[i]);
			}
			System.out.println();
	
			System.out.println();
		}
	}
	
}
