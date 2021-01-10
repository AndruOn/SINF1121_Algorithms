
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MineClimbing {


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
        class distToNode {
            final int weight;
            final int v;

            public distToNode(int vertex1, int weight) {
                this.weight = weight;
                v = vertex1;
            }
        }
        int nbColumns = map[0].length;
        int nbLines = map.length;
        int N = nbColumns * nbLines;
        boolean[] marked = new boolean[N];
        int[] distTo = new int[N];
        for (int i = 0; i < N; i++) {
            distTo[i] = (int) Double.POSITIVE_INFINITY;
        }

        PriorityQueue<distToNode> pq = new PriorityQueue<>(new Comparator<distToNode>() {
            @Override
            public int compare(distToNode o1, distToNode o2) {
                return o1.weight - o2.weight;
            }
        });

        int start = ind(startX, startY, nbColumns);
        distTo[start] = 0;
        pq.add(new distToNode(start,0));

        int current;
        int weight;
        while (!pq.isEmpty()) {
            current = pq.poll().v; // a check
            for (Integer i : adjacents(current, nbColumns, nbLines)) {
                //System.out.println(String.format("olddist:%d newdist:%d", distTo[i], distTo[current] + height_between(current, i, height)));
                weight = Math.abs(map[row(current,nbColumns)][column(current,nbColumns)] - map[row(i,nbColumns)][column(i,nbColumns)]);
                if (distTo[i] > weight + distTo[current] ) {
                    distTo[i] = weight + distTo[current];
                    pq.add(new distToNode(i,weight));
                }
            }
        }

        int end = ind(endX, endY, nbColumns);
        //System.out.println(Arrays.toString(distTo));
        return distTo[end];
    }

    private static int ind(int X, int Y, int nbColumns) {
        return X * nbColumns + Y;
    }

    private static int row(int index, int nbColumns) {
        return index / nbColumns;
    }

    private static int column(int index, int nbColumns) {
        return index % nbColumns;
    }

    private static Iterable<Integer> adjacents(int current, int nbColumns, int nbLines) {
        int i = row(current, nbColumns);
        int j = column(current, nbColumns);
        //System.out.println(String.format("current:%d i:%d j:%d", current, i, j));
        LinkedList<Integer> list = new LinkedList<>();
        list.add(ind(i, (j + 1) % nbColumns, nbColumns));
        list.add(ind(i, j - 1 >= 0 ? j - 1 : nbColumns - 1, nbColumns));
        list.add(ind((i + 1) % nbLines, j, nbColumns));
        list.add(ind(i - 1 >= 0 ? i - 1 : nbLines - 1, j, nbColumns));
        //System.out.println(Arrays.toString(list.toArray()));
        return (Iterable<Integer>) list;
    }

}