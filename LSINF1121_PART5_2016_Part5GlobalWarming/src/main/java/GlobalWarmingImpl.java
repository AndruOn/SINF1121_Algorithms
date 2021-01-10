import java.util.*;

   public class GlobalWarmingImpl extends GlobalWarming {
        int N;
        int[] id;
        int[] sz;
        int nbIslands;


       /**
        * In the following, we assume that the points are connected to
        * horizontal or vertical neighbors but not to the diagonal ones
        * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
        * @param waterLevel is the water level, every entry <= waterLevel is flooded
        */
       // expected pre-processing time in the constructror : O(n^2 log(n^2))
       public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
           super(altitude,waterLevel);
           N = altitude.length;
           //init QuickFind
           id = new int[N*N];
           sz = new int[N*N];
           nbIslands = 0;
           for (int i = 0; i < N*N; i++) {
               id[i] = i;
               sz[i] = i;
           }
           for (int i = 0; i < N * N; i++) {
               if(altitude[i/N][i%N]>waterLevel){
                   nbIslands++;
                   if(i/N!=N-1 && altitude[(i/N)+1][i%N]>waterLevel){ //check si pas dernier colonne et check en bas
                           //System.out.println("en bas ile index:"+i+" avec index en bas:"+(i+N));
                           union(i,i+N);
                   }
                   if((i+1)%N!=0 && altitude[i/N][(i+1)%N]>waterLevel){ //check sil reste un elem a droite /check a droite
                           //System.out.println("a droite ile index:"+i+" avec index a droite:"+(i+1));
                           union(i,i+1);
                   }
               }
               //System.out.print("index:"+i);
               //println();
           }
       }

       /**
        * An island is a connected components of safe points wrt to waterLevel
        * @return the number of islands
        */
       // expected time complexity O(1)
       public int nbIslands() {
           return nbIslands;
       }

       public int find(int p){
           int k =p;
           while(p!= id[p]) p=id[p];

           int next= k;
           while(p!=id[k]) {
               next = id[k];
               id[k] = p;
               k =next;
           }
           return p;
       }

       public void union(int p, int q){
           int i = find(p);
           int j = find(q);
           if(i==j) return;

           if (sz[i] < sz[j]) {
               id[i] = j;
               sz[j] += sz[i];
           } else {
               id[j] = i;
               sz[i] += sz[j];
           }

           nbIslands--;
       }


       /**
        *
        * @param p1 a point with valid coordinates on altitude matrix
        * @param p2 a point with valid coordinates on altitude matrix
        * @return true if p1 and p2 are on the same island, that is both p1 and p2 are safe wrt waterLevel
        *        and there exists a path (vertical/horizontal moves) from p1 to p2 using only safe positions
        */
       // expected time complexity O(1)
       public boolean onSameIsland(Point p1, Point p2) {
           //System.out.println(String.format("same islands? p1(%d) avec p2(%d)",p1.x+ p1.y*N,p2.x+ p2.y*N));
           //println();
           return !p1.equals(p2) && find(p1.x*N+ p1.y) == find(p2.x*N+ p2.y);
       }

       public void println(){
           for (int i = 0; i < N*N; i++) {
               if (i%N==0){
                   System.out.println();
               }
               System.out.print(id[i]+" ");
           }
           System.out.println();
           System.out.println("N:"+N+"  "+Arrays.toString(id)+"  nbIslands:"+nbIslands);
           System.out.println();
       }

       public static void main(String[] args) {
           int [][] tab = new int[][] {
                   {1,3,3,1,3},
                   {4,2,2,4,5},
                   {4,4,1,4,2},
                   {1,4,2,2,6},
                   {3,3,1,6,3}
           };
           GlobalWarmingImpl gw = new GlobalWarmingImpl(tab,2);
           gw.println();
           System.out.println("expected:false actual:"+gw.onSameIsland(new GlobalWarming.Point(1,3),new GlobalWarming.Point(3,4)));
           System.out.println("expected:true actual:"+gw.onSameIsland(new GlobalWarming.Point(0,1),new GlobalWarming.Point(1,3)));
       }

   }