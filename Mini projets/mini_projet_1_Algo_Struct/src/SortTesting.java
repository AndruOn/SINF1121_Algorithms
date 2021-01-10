import java.util.Arrays;
import java.util.Random;
import java.util.Arrays.*;
import java.util.ArrayList;

import java.util.function.Consumer;




public class SortTesting {
  
    static int MAX_SIZE = 50000;

    static int[][] preOrdered = {{},
                                 {}};

  
      // array allant de 1 à 1000 mais parasité avec des nombres aléatoires entre 1 et 1000
    public static Integer[] ListeParasite(){

              ArrayList<Integer> N = new ArrayList<Integer>();

            for (int i=0;i<1000;i++){

              Random r = new Random();
              int low1 = 1;
              int high1 = 1000;
              int result1 = r.nextInt(high1-low1) + low1;

              Random q = new Random();
              int low2 = 1;
              int high2 = 10;
              int result2 = q.nextInt(high2-low2) + low2;

              N.add(i);
              if (result2<=5){ N.add(result1);}
           }
        return (Integer[]) N.toArray();
    }



    public static double timerAlgoRandom(int taille, int nbreTest, Consumer<Comparable[]> algoSort){
        long totalTime=0;
        Random r = new Random();
        for (int j = 0; j < nbreTest; j++) {
            Integer[] a = new Integer[taille];
            for(int i=0; i<taille; i++){
                a[i] = r.nextInt(2 * MAX_SIZE) - MAX_SIZE;  //Creat a random array of int (intervalle [-MAX_SIZE,MAX_SIZE])
            }
            long start = System.currentTimeMillis();
            algoSort.accept(a);
            long end = System.currentTimeMillis();
            totalTime+= (end - start);
        }
        return totalTime/nbreTest;
    }

    public static void plotTest(int taille_max, int nbreTest, Consumer<Comparable[]> algoSort){
        int[] taille = new int[10];
        for(int i = 250; i<taille_max; i *= 2){
            double t = timerAlgoRandom(i,nbreTest,algoSort);

        }
    }

    public static double timerAlgoOrdered(Consumer<Comparable[]> algoSort){
        //Renvoie la duree d'execution d'un algo
        Integer[] a = new Integer[]{};

        double start = System.currentTimeMillis();
        algoSort.accept(a);
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public static void printTableau(String name, Consumer<Comparable[]> algoSort,int nbreTest) {
        //Renvoie un tableau avec la taille n et le temps d'execution
        System.out.printf("%s:\n Taille Temps\n",name);
        for(int i = 250; i<MAX_SIZE; i *= 2){
            double t = timerAlgoRandom(i,nbreTest,algoSort);
            System.out.printf("%5d %5.1f\n", i, t);
        }
    }

    public static void main(String[] args){
        printTableau("SelectionSort", SelectionSort::sort,5);
        printTableau("InsertionSort", InsertionSort::sort,5);
        printTableau("ShellSort", ShellSort::sort,5);
        printTableau("QuickSort", QuickSort::sort,5);
        printTableau("MergeSort", MergeSort::sort,5);
        printTableau("Sort of Java.Array", Arrays::sort,5);
    }
}
