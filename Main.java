public class Main {
    public static void main(String[] args) {
        matriks M = new matriks();

        // M.isimatriks(4,4);

        // int [][] M2 = new int[4][4];
        // for (i=0;i<4;i++) {
        //     for (j=0;j<4;j++) {
        //         M2[i][j] = M.get(M,i,j);
        //     }
        // }
        // M.transpose(4,3,M2);
        // System.out.print("Determinan matriks: ");
        // System.out.println(M.determinan(4,M2));

         float [][] M3 = {{3, 3, -1, 5},
                         {0, 0, -3, 3}};

         M3 = M.gauss(2,4,M3);
         M.tulismatriks(2,4,M3);
        System.out.println();
         M3 = M.gaussjor(2,4,M3);
         M.tulismatriks(2,4,M3);

//         float [][] M3 = {{3, 6, 9, 3},
//                         {-1, 0, 1, 0},
//                         {1, 3, 2, -1},
//                         {-1, -2, -2, 1}};
//
//         float [][] M3 = {{0, 1, 5},
//                         {3, -6, 9},
//                         {2, 6, 1}};
//         System.out.print("Determinan matriks: ");
//         System.out.println(M.detred(4,M3));
//         M.tulismatriks(4,4,M3);
//
//        float [][] M3 = {{1, 2, 3},
//                        {2, 5, 3},
//                        {1, 0, 8}};
//        float [][] I = new float[3][3];
//        I = M.invers(3,3,M3);
//        M.tulismatriks(3,3,M3);
//        M.tulismatriks(3,3,I);

    }
}