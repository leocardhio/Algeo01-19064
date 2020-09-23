class DriverMatriks {
    public static void main(String[] args) {
        matriks M = new matriks();

        M.isimatriks(4,4);

        System.out.println("Isi matriks: ");
        M.tulismatriks(4,4);

        int [][] M2 = new int[4][4];
        int i, j;
        for (i=0;i<4;i++) {
            for (j=0;j<4;j++) {
                M2[i][j] = M.get(M,i,j);
            }
        }
        // M.transpose(4,3,M2);

        // System.out.println("Isi matriks transpose: ");
        // int i, j;
        // for (i=0;i<4;i++) {
        //     for (j=0;j<3;j++) {
        //         System.out.print(M2[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        System.out.print("Determinan matriks: ");
        System.out.println(M.determinan(4,M2));
    }
}