class DriverMatriks {
    public static void main(String[] args) {
        matriks M = new matriks();

        // M.isimatriks(4,4);

        // System.out.println("Isi matriks: ");
        // M.tulismatriks(4,4);

        // int [][] M2 = new int[4][4];
        // for (i=0;i<4;i++) {
        //     for (j=0;j<4;j++) {
        //         M2[i][j] = M.get(M,i,j);
        //     }
        // }
        // M.transpose(4,3,M2);
        // System.out.print("Determinan matriks: ");
        // System.out.println(M.determinan(4,M2));

        float [][] M3 = {{2, 3, -1, 5}, 
                        {4, 4, -3, 3}, 
                        {-2, 3, -1, 1}};

        // System.out.println("Isi matriks transpose: ");
        // for (i=0;i<4;i++) {
        //     for (j=0;j<4;j++) {
        //         System.out.print(M3[i][j]);
        //         if (j < 3)
        //             System.out.print(" ");
        //     }
        //     System.out.println();
        // }

        M.gauss(3,4,M3);

        System.out.println("Isi matriks transpose: ");
        int i, j;
        for (i=0;i<3;i++) {
            for (j=0;j<4;j++) {
                System.out.print(M3[i][j]);
                if (j < 3)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}