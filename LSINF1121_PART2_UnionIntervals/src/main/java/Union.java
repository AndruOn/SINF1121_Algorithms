import java.util.*;

public class Union {

    public static Interval[] union(Interval [] intervals) {
        if (intervals.length==0) {
            return new Interval[]{};
        }

        Arrays.sort(intervals);
        print("trié:", intervals);

        List<Interval> return_union = new ArrayList<>();
        int min = intervals[0].min;
        int max = intervals[0].max;
        for (int i = 1; i < intervals.length; i++) {
            if (max < intervals[i].min) {
                return_union.add(new Interval(min, max));
                min = intervals[i].min;
                max = intervals[i].max;
            } else {
                if (max < intervals[i].max) {
                    max = intervals[i].max;
                }
            }
        }
        return_union.add(new Interval(min, max));
        return return_union.toArray(new Interval[]{});
    }

    public static Interval [] union_tp(Interval [] intervals) {
        if (intervals.length ==0) return new Interval[]{};

        int[][] inter = new int[intervals.length * 2][2];
        for (int i = 0; i < intervals.length; i++) {
            inter[2*i]= new int[]{intervals[i].min,1};
            inter[2*i+1]= new int[]{intervals[i].max,-1};
        }
        Arrays.sort(inter, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        /*
        for (int[] a :inter) {
            System.out.printf("(%d,%d), ",a[0],a[1]);
        }System.out.println();*/

        int i = 0;
        int current = inter[0][0];
        int total = 0;
        int min = current;
        LinkedList<Interval> final_inter = new LinkedList<>();
        while (i < inter.length) {
            while (inter[i][0] == current) {
                total += inter[i][1];
                i++;
                if(i>= inter.length) {
                    final_inter.add(new Interval(min,inter[i-1][0]));
                    return final_inter.toArray(new Interval[]{});
                }
            }
            //System.out.printf("current:%d total:%d i:%d\n",current,total,i);
            current = inter[i][0];

            if(total == 0){
                final_inter.add(new Interval(min,inter[i-1][0]));
                print("return interval",final_inter.toArray(new Interval[]{}));
                min = current;
            }
        }
        return final_inter.toArray(new Interval[]{});
    }


    public static void print(String description, Interval[] intervals) {
        if (intervals.length == 0){
            System.out.println(description+"\n"+"\n");
            return;
        }
        if (intervals.length == 1){
            System.out.println(description+"\n"+intervals[0].toString()+"\n");
            return;
        }
        StringBuilder strB = new StringBuilder();
        strB.append(description+"\n");
        for (Interval i :
                intervals) {
            strB.append(i.toString()+",");
        }
        strB.deleteCharAt(strB.lastIndexOf(","));
        strB.append("\n");
        System.out.print(strB.toString());
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{
                new Interval(10,10), new Interval(2,4), new Interval(3,4), new Interval(5,6),
                new Interval(6,9), new Interval(6,8), new Interval(2,3), new Interval(2,4),
                new Interval(10,11), new Interval(10,12), new Interval(12,14), new Interval(16,17)
        };
        print("avant trié",intervals);
        print("après union:",union(intervals));
        /*
        Interval[] inte = new Interval[]{
                new Interval(4,7), new Interval(5,8), new Interval(6,7), new Interval(8,11),
                new Interval(12,12), new Interval(12,15), new Interval(13,14), new Interval(13,15),
                new Interval(14,17), new Interval(16,16)
        };
        union(inte);
        */
    }

}
