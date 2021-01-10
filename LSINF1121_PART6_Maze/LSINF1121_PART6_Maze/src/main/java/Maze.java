
import java.util.LinkedList;
import java.util.Queue;


public class Maze {
    public static Iterable<Integer> shortestPath(int [][] maze,  int x1, int y1, int x2, int y2) {
        if(maze[x1][y1]==1 || maze[x2][y2]==1){
            return new LinkedList<>();
        }
        Queue<Integer> Q = new LinkedList<>();
        int m = maze[0].length;
        int n =  maze.length;
        boolean[] marked = new boolean[n*m];
        int[] pathTo = new int[n*m];
        Q.add(ind(x1,y1,m));
        marked[ind(x1,y1,m)] = true;
        int a;
        while(!Q.isEmpty()){
            a = Q.remove();
            if(a == ind(x2,y2,m)){
                LinkedList<Integer> path = new LinkedList<>();
                for(int x = a; x!=ind(x1,y1,m); x = pathTo[x]){
                    path.addFirst(x);
                }
                path.addFirst(ind(x1,y1,m));
                return path;
            }
            int x = row(a,m);
            int y = col(a,m);
            if(col(a,m)<m-1){
                if(maze[x][y+1]==0 && !marked[ind(x,y+1,m)]){
                    int ind = ind(x,y+1,m);
                    Q.add(ind);
                    pathTo[ind] = a;
                    marked[ind] = true;
                }
            }
            if(col(a,m)>0){
                if(maze[x][y-1]==0 && !marked[ind(x,y-1,m)]){
                    int ind = ind(x,y-1,m);
                    Q.add(ind);
                    pathTo[ind] = a;
                    marked[ind] = true;
                }
            }
            if(row(a,m)<n-1){
                if(maze[x+1][y]==0 && !marked[ind(x+1,y,m)]){
                    int ind = ind(x+1,y,m);
                    Q.add(ind);
                    pathTo[ind] = a;
                    marked[ind] = true;
                }
            }
            if(row(a,m)>0){
                if(maze[x-1][y]==0 && !marked[ind(x-1,y,m)]){
                    int ind = ind(x-1,y,m);
                    Q.add(ind);
                    pathTo[ind] = a;
                    marked[ind] = true;
                }
            }
        }

        return new LinkedList<>();
    }

    public static int ind(int x,int y, int lg) {return x*lg + y;}

    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }
}
