public class Vector {

    private int [] array;
    private int nOp = 0;

    Vector(int n) {
        array = new int[n];
    }

    public int size() {
        return array.length;
    }

    public void set(int index, int value) {
        nOp++;
        array[index] = value;
    }

    public int get(int i) {
        nOp++;
        return array[i];
    }

    public void print(String descr){
        StringBuilder s = new StringBuilder();
        s.append(descr + "[");
        for (int i = 0; i <array.length-1; i++) {
            s.append(this.get(i)+", ");
        }
        s.append(this.get(array.length-1)+"]");
        System.out.println(s.toString());
    }

    public void swap(int i, int j) {
        nOp++;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public int nOp() {
        return nOp;
    }

}