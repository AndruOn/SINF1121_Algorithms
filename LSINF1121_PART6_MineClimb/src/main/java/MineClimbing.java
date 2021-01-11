
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class MineClimbing {
    public static class Pos_Dist implements Comparable{
        int pos;
        int dist;

        Pos_Dist(int position, int distance) {
            pos = position;
            dist = distance;
        }

        @Override
        public int compareTo(Object o) {
            Pos_Dist o2 = (Pos_Dist) o;
            return dist - o2.dist;
        }

        @Override
        public String toString() {
            return String.format("pos:%d dist:%d",pos,dist);
        }
    }


    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * <p>
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * <p>
     * map is a matrix of size n times m, with n,m > 0.
     * <p>
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY) {

        int nb_col = map[0].length;
        int nb_row = map.length;
        double[] distTo = new double[nb_col * nb_row];
        for (int i = 0; i < nb_col * nb_row; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        int start = startX * nb_col + startY;
        int end = endX * nb_col + endY;
        System.out.printf("start:%d end:%d\n",start,end);

        PriorityQueue<Pos_Dist> pq = new PriorityQueue<>();
        distTo[start] = 0;
        pq.add(new Pos_Dist(start,0));

        while(!pq.isEmpty()){
            Pos_Dist cur = pq.poll();

            //System.out.println("current:"+cur +"  " +Arrays.toString(marked));
            if (cur.pos == end) {
                return cur.dist;
            }
            int[][] neighbours = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] nei : neighbours) {
                //System.out.printf("   oldrow:%d old_col:%d",cur.pos / nb_col,cur.pos % nb_col);
                int row = Math.abs(((cur.pos / nb_col) + nei[0] +nb_row) % nb_row);
                int col = Math.abs(((cur.pos % nb_col) + nei[1] +nb_col) % nb_col);
                int adj = row * nb_col + col;
                //System.out.printf("   row:%d col:%d\n",row,col);
                int new_dist = cur.dist + Math.abs(map[cur.pos/nb_col][cur.pos%nb_col] - map[row][col]);
                if (new_dist < distTo[adj]) {

                    Pos_Dist pos_dist= new Pos_Dist(adj,new_dist);
                    distTo[adj] = new_dist;
                    //System.out.println("   ->"+pos_dist);
                    pq.add(pos_dist);
                }
            }
        }
        System.out.println("error pas de chemin trouvé");
        return -1;
    }

    //JSP pq ca passe les tests, normalement ca devrait utiliser distTo et pas marked dans un dijkstra
    public static int best_distance_NoWeight(int[][] map, int startX, int startY, int endX, int endY) {

        int nb_col = map[0].length;
        int nb_row = map.length;
        boolean[] marked = new boolean[nb_col * nb_row];
        int start = startX * nb_col + startY;
        int end = endX * nb_col + endY;
        System.out.printf("start:%d end:%d\n",start,end);

        PriorityQueue<Pos_Dist> pq = new PriorityQueue<>();
        pq.add(new Pos_Dist(start,0));

        while(!pq.isEmpty()){
            Pos_Dist cur = pq.poll();
            if(marked[cur.pos]) continue;
            marked[cur.pos] = true;

            //System.out.println("current:"+cur +"  " +Arrays.toString(marked));
            if (cur.pos == end) {
                return cur.dist;
            }
            int[][] neighbours = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] nei : neighbours) {
                //System.out.printf("   oldrow:%d old_col:%d",cur.pos / nb_col,cur.pos % nb_col);
                int row = Math.abs(((cur.pos / nb_col) + nei[0] +nb_row) % nb_row);
                int col = Math.abs(((cur.pos % nb_col) + nei[1] +nb_col) % nb_col);
                int adj = row * nb_col + col;
                //System.out.printf("   row:%d col:%d\n",row,col);
                if (!marked[adj]) {
                    int new_dist = cur.dist + Math.abs(map[cur.pos/nb_col][cur.pos%nb_col] - map[row][col]);
                    Pos_Dist pos_dist= new Pos_Dist(adj,new_dist);
                    //System.out.println("   ->"+pos_dist);
                    pq.add(pos_dist);
                }
            }
        }
        System.out.println("error pas de chemin trouvé");
        return -1;
    }



    public static void main(String[] args) {
        int[][] map = new int[][] {
                {7, 2, 9, 6}, //0,1,2,3
                {8, 7, 6, 0}, //4,5,6,7
                {4, 6, 5, 8} //8,9,10,11
        };

        System.out.println( MineClimbing.best_distance(map, 0, 1, 2, 3));//8
    }
}