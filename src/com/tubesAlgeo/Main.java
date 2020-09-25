package com.tubesAlgeo;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Matriks M = new Matriks(new float[][] { {3, 5, 6, 3},
                                                {7 ,4, 8, 8},
                                                {6, 5, 1, 7},
                                                {2, 8, 6, -3}});
        M.tulisMatriks();
        System.out.println("determinan: " + M.determinan());

        Regresi reg = new Regresi();
//        float [][] M3 = {{1, 2, 3},
//                        {2, 5, 3},
//                        {1, 0, 8}};
//        float [][] I = new float[3][3];
//        I = M.invers(3,3,M3);
//        System.out.println("");
//        M.tulismatriks(3,3,M3);
//        System.out.println("");
//        M.tulismatriks(3,3,I);
    }
}
