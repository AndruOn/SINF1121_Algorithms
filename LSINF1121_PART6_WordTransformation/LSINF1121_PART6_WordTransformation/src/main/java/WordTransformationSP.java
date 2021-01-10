import java.util.*;

public  class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     *
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0,start)+new StringBuilder(s.substring(start,end)).reverse().toString()+s.substring(end);
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {

        class StringCost{
            String s;
            Integer cost;

            StringCost(String string , Integer cost){
                s = string;
                this.cost = cost;
            }

            @Override
            public String toString() {
                return String.format("string:%s cost:%d",s,cost);
            }



        }

        if (from.equals(to)) return 0;

        int len = from.length();
        HashMap<String,Integer> marked = new HashMap<>();
        marked.put(from,0);
        PriorityQueue<StringCost> pq = new PriorityQueue<>(new Comparator<StringCost>() {
            @Override
            public int compare(StringCost o1, StringCost o2) {
                return o1.cost - o2.cost;
            }
        });

        StringCost current = new StringCost(from,0);
        pq.add(current);
        String rotated;
        while (!pq.isEmpty()){
            current = pq.poll();
            //System.out.println(current);
            //System.out.println("marked: "+Arrays.toString(marked.toArray()));
            if (current.s.equals(to)) {
                //System.out.printf("found minimal cost:%d from:%s to:%s\n",current.cost,from,to);
                //System.out.printf("marked ofsize %d: %s\n",marked.size(),Arrays.toString(marked.keySet().toArray()));
                return current.cost;
            }


            for (int i = 0; i < len; i++) {
                for (int j = i+1; j < len+1; j++) {
                    int new_cost = current.cost + j-i;
                    rotated = rotation(current.s,i,j);
                    if(!marked.containsKey(rotated) || marked.get(current.s) + j-i < marked.get(rotated)){
                        //System.out.printf("newNode: i:%d j:%d str:%s cost:%d\n",i,j,rotated,new_cost);
                        pq.add(new StringCost(rotated,new_cost));
                        marked.put(rotated,new_cost);
                    }
                }
            }
        }
        System.out.printf("minimalcost launched with from:%s to:%s\n",from,to);
        System.out.printf("marked ofsize %d: %s\n",marked.size(),Arrays.toString(marked.keySet().toArray()));
        System.out.println("IMPOSSIBLE from -> to with rotate");
        return -1;
    }

    public static void main(String[] args) {
        System.out.printf("minimalcost:%d\n",minimalCost("ABC","CAB"));
        System.out.printf("minimalcost:%d\n",minimalCost("1234","2143"));
        //System.out.printf("minimalcost:%d\n",minimalCost("HAMBURGER","HAMBEGRUR"));
    }
}