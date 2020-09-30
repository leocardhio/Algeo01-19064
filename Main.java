public class Main {
/*
            NOTE LEO: kayaknya kita harus misahin matriks A dan 
                      matriks b di Ax=b
*/
    public static void main(String[] args) {
        Matriks M = new Matriks(new float[][] { {3, 5, 6},
                                                {7 ,4, 8},
                                                {6, 5, 1}});

        Matriks augmented = new Matriks(new float[][] { {1, 1, 1, 1},
                                                        {1, 2, 4, 2},
                                                        {1, 3, 9, 3} });
        
        
        MAugmented augmented2 = new MAugmented (augmented);
        System.out.println(augmented2.MatKoef);
        System.out.println(augmented2.MatVal);
        Matriks Mgauss = augmented.gauss();
        System.out.println(Mgauss);
        //System.out.println(M);
        //System.out.println("determinan: " + M.determinan());

        // Matriks augmented = new Matriks();
        // augmented.MakeFile();
        // augmented.gauss();
        // System.out.println(augmented);
        
        Interpolasi X=new Interpolasi();


        
    //    augmented.toString();
    //    Regresi reg = new Regresi();
    //    float [][] M3 = {{1, 2, 3},
    //                    {2, 5, 3},
    //                    {1, 0, 8}};
    //    float [][] I = new float[3][3];
    //    I = M.invers(3,3,M3);
    //    System.out.println("");
    //    M.tulismatriks(3,3,M3);
    //    System.out.println("");
    //    M.tulismatriks(3,3,I);
    }
}
