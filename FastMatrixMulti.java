//package brandon_soncarty_00874311_cscd320_prog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FastMatrixMulti {

    public static void main(String[] args) throws FileNotFoundException {
        FastMatrixMulti brandon = new FastMatrixMulti();
        //scanner to read the file
        Scanner input = new Scanner(new File(args[0]));
        //ArrayList to hold the values from the data.txt because it is dynamically sized
        ArrayList<Integer> list = new ArrayList<>();
        //while there are still lines in the file
        while (input.hasNextLine()){
            //temp is the nextInt
            int temp = input.nextInt();
            //add temp to the list
            list.add(temp);
        }
        //make an array that is the same size as the ArrayList
        int[] p = new int[list.size()];
        //add the values from the ArrayList to the array
        for(int i = 0; i < list.size(); i++){
            p[i] = list.get(i);
        }

        int n = p.length-1;

        //arrayList to hold the resulting int[][] values gathered
        ArrayList<int[][]> arrList = new ArrayList<>(brandon.matrixChainOrder(p));
        //m stores the optimal time cost for multiplying the subchains
        int[][] m = arrList.get(0);
        //s stores the position of the optimal outermost pair of parenthesis for the subchains
        int[][] s = arrList.get(1);
        brandon.printOptimalParenthesis(s,1, n);
        System.out.println();
        //m[1][n] is the optimal time for the entire matrix chain multiplication
        System.out.println("Minimum time cost: " + m[1][n]);
    }


    public ArrayList<int[][]> matrixChainOrder(int[] p){

        int n = p.length;
        int[][] m = new int[n + 1][n + 1];
        for(int i = 1; i < p.length; i++){
            m[i][i] = 0;
        }

        int[][] s = new int[n + 1][n + 1];

        for(int h = 2; h < n ; h++){
            for(int i = 1; i < n-h+1; i++){
                int j = i + h - 1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j-1; k++){
                    int q = m[i][k] + m[k+1][j] + (p[i-1] * p[k] * p[j]);
                    if(q < m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                     }
                }
            }
        }
        ArrayList<int[][]> list = new ArrayList<>();
        list.add(m);
        list.add(s);
        return list;
    }

    public void printOptimalParenthesis(int[][] s, int i, int j){
        if(i == j){
            System.out.print("A" + i);
        }else{
            System.out.print("(");
            printOptimalParenthesis(s,i,s[i][j]);
            printOptimalParenthesis(s,s[i][j] + 1, j);
            System.out.print(")");
        }
    }

}
