import java.util.HashSet;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class cd {

    public static void main(String[] args) 
        throws Exception
    {
        /*  Must use HashSet, TreeSet, HashMap, TreeMap
            Cannot use Scanner for runtime error

            The first line of each test case contains two positive integers N and M, each at most one million, 
            specifying the number of CDs owned by Jack and by Jill, respectively.
            This line is followed by N lines listing the catalog numbers of the CDs owned by Jack in increasing order,
            and M more lines listing the catalog numbers of the CDs owned by Jill in increasing order.
            Each catalog number is a positive integer no greater than one billion. The input is terminated by a line containing two zeros.
        */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //declare variables, using to split to separate initial integers
        String cds = reader.readLine();
        String[] getInts = cds.split(" ");
        int n = Integer.parseInt(getInts[0]);
        int m = Integer.parseInt(getInts[1]);

        //loop as n is not 0
        while(n != 0) {
            int matches = 0;    //counter variable

            //use hashSet for storing cds
            HashSet<String> match = new HashSet<String>();

            //loop through all Jack's cds
            for(int i = 0; i < n; i++) {
                String cd = reader.readLine();
                match.add(cd);
            }

            //loop through jills cds and increment number of matches if contains is true
            for(int i = 0; i < m; i++) {
                String cd = reader.readLine();
                if(match.contains(cd)) matches++;
            }
            System.out.println(matches); //print number of matches

            //repeat variables for next case
            cds = reader.readLine();
            getInts = cds.split(" ");
            n = Integer.parseInt(getInts[0]);
            m = Integer.parseInt(getInts[1]);
        }
    }
}