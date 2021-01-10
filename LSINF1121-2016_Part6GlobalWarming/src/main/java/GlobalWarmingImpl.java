import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
    }


    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    // expected time complexity O(n^2)
    public List<Point> shortestPath(Point p1, Point p2) {
        Queue<Point> stack = new LinkedList<>();
        int nb_columns = altitude[0].length;
        int nb_lines = altitude.length;
        boolean[][] marked = new boolean[nb_lines][nb_columns];
        Point[][] edgeTo = new Point[nb_lines][nb_columns];
        //System.out.println("start:" + p1 + " with alt:" + altitude[p1.x][p1.y] +"  waterlevel:" + waterLevel);
        if(altitude[p1.x][p1.y]<= waterLevel) return new ArrayList<Point>();
        stack.add(p1);
        marked[p1.x] [p1.y] = true;
        edgeTo[p1.x] [p1.y] = p1;
        int[][] adj = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};


        Point current = null;
        while(!stack.isEmpty()) {
            current = stack.remove();
            if (current.equals(p2)) {
                break;
            }
            //System.out.println("current: "+ current);
            for (int[] p : adj) {
                int x = current.x + p[0]>=0 ? (current.x + p[0]) % nb_lines : current.x;
                int y = current.y + p[1]>=0 ? (current.y + p[1]) % nb_lines : current.y;
                //System.out.printf("x:%d y:%d\n",x,y);
                if(altitude[x][y]>waterLevel){
                    if(!marked[x][y]){
                        //System.out.printf("new unmarked x:%d y:%d\n",x,y);
                        edgeTo[x][y] = current;
                        marked[x][y] = true;
                        stack.add(new Point(x, y));
                    }
                }
            }

        }
        if(!current.equals(p2)){
            return new ArrayList<Point>();
        }
        LinkedList<Point> path = new LinkedList<Point>();
        Point prev = null;
        for (prev = p2; !prev.equals(p1); prev = edgeTo[prev.x][prev.y]) {
            path.addFirst(prev);
        }
        path.addFirst(p1);
        return path;
    }

    public static void print_path(List<Point> path){
        System.out.println("shortest path:");
        for (Point p :
                path) {
            System.out.printf("[%d, %d], ",p.x,p.y);
        }
        System.out.println();
    }

    public static void test_on_matrix(int[][] tab,Point p1,Point p2){
        List<GlobalWarming.Point> path = new GlobalWarmingImpl(tab, 3).shortestPath(p1,p2);

        print_path(path);
    }

    public static void main(String[] args) {
        int [][] tab = new int[][] {
                {1,3,3,1,3},  // 0,1,1,0,1
                {4,2,2,4,5},  //s1,0,0,1,1
                {4,4,1,4,2},  // 1,1,0,1,0
                {1,4,2,3,6},  // 0,f1,0,1,1
                {1,1,1,6,3}}; // 0,0,0,1,1
        test_on_matrix(tab,new Point(1, 0), new Point(3, 1)); //chemin simple
        test_on_matrix(tab,new Point(1, 0), new Point(1, 0)); //meme start arrivée
        test_on_matrix(tab,new Point(0, 0), new Point(0, 0)); //start sous l'eau


    }

}