import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {
    int[] alt;

    // expected pre-processing time in the constructror : O(n^2 log(n^2))
    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        alt = new int[altitude.length*altitude.length];
        for (int i = 0; i < altitude.length; i++) {
            for (int j = 0; j < altitude.length; j++) {
                alt[i*altitude.length+j] = altitude[i][j];
            }
        }
        print("pas trié len="+alt.length,alt);
        Arrays.sort(alt);
        print("trié len="+alt.length,alt);
    }

    // expected time complexity O(log(n^2))
    public int nbSafePoints(int waterLevel) {
        int index = Arrays.binarySearch(alt, waterLevel);
        System.out.println("avec waterlevel: "+waterLevel+" _ index rendu par binarysearch: "+index);
        if (index <= -1) {
            return alt.length + index +1;
        } else {
            while (index<alt.length && alt[index] <= waterLevel) {
                index++;
            }
            return alt.length - index;
        }
    }

    public static void print(String description, int[] alt) {
        StringBuilder s = new StringBuilder();
        s.append(description + "\n");
        for (int i = 0; i < alt.length; i++) {
            s.append(alt[i] + " ");
        }
        s.append("\n");
        System.out.println(s.toString());
    }

    public static void main(String[] args) {
        int [][] tab = new int[][] {{0,5,1,1,2},
                {4,2,2,4,5},
                {4,4,1,4,2},
                {1,4,2,2,6},
                {1,0,1,9,2}};
        GlobalWarming gw = new GlobalWarmingImpl(tab);
        System.out.println("safe points waterlevel=3  :"+gw.nbSafePoints(3));
        System.out.println("safe points waterlevel=0  :"+gw.nbSafePoints(0));
        System.out.println("safe points waterlevel=-1  :"+gw.nbSafePoints(-1));
        System.out.println("safe points waterlevel=9  :"+gw.nbSafePoints(9));
    }

}