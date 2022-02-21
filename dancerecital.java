
//Jarod Gomberg
//Feb. 11, 2022
//COP3503
//RP2 - dancerecital.java

import java.util.Scanner;

/*

    Reports the minimum number of quick changes needed for a specific recital.
        given that the order of the dance routines is changeable. 
        Due to "exorbitant charge amount" for each quick change.

    Dancers are given an identifying uppercase letter. 
    List of routines is given with a string of characters. 
        defines which dancers appear in a routine.

*/

public class dancerecital {

    //declare initial variables
    public static int R;
    public static String[] idents;
    public static int[][] val;

    public static void main(String[] args) {

        //Scan input and get number of routines
        Scanner stdin = new Scanner(System.in);
        R = stdin.nextInt();

        //get number of identifiers needed for each dancer.
        idents = new String[R];

        //declare basic permuation variables with size of number of recitals.
        boolean[] used = new boolean[R];
		int[] perm = new int[R];
        
        //Scan in dancer idents
        for(int i = 0; i < R; i++) {
            idents[i] = stdin.next(); 
        }

        //get value of changes needed through number of recitals
        val = new int[R][R];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < R; j++) {
                val[i][j] = quickChange(idents[i], idents[j]);
            }
        }
        
        //Get and print solution
		System.out.println(getSoln(perm, used, 0));

    }

    /*
        Method that gets number of quick changes necessary 
        by comparing strings and returning number of duplicated chars
    */
    public static int quickChange(String d1, String d2) {
        int dup = 0;

        //search for similarities
        for(int i = 0; i < d1.length(); i++) {
            if(d2.contains(d1.charAt(i)+ "")) {
                dup++;
            }
        }
        //number of QC's after duplicate chars
        return dup;
    }

    /*
        method for solving and returning final answer 
        takes in all standard permutation variables and follows pretty common structure of solve methods
        from backtracking and permutation examples in Guha's CS2 notes.
    */
    public static int getSoln(int[] perm, boolean[] used, int k) {

        //initial check, if k is equal to length of perm, finished check charge
        if(k == R) return charge(perm);

        //declare variable to track number of changes
        //make it large enough that we won't worry when compared with math.min
        int changes = 999999;

        //go through each slot and try all possible permutations
        for(int i = 0; i < R; i++) {
            if(!used[i]) {
                perm[k] = i;
                used[i] = true;

                //Now make sure we get the lowest possible answer
                changes = Math.min(changes, getSoln(perm, used, k+1));

                used[i] = false;
            }
        }
        //return answer for minimum changes
        return changes;
    }

    //get's total cost of a change
    public static int charge(int perm[]) {
       //track cost of charge
        int cost = 0;

        //for every group of dancers, track the number of quick changes. This will be our total cost
        for(int i = 0; i < R-1; i++) {
            cost += val[perm[i]][perm[i+1]];
        }

        //return amount to charge
        return cost;
    }
}