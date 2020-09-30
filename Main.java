public class Main {
/*
            NOTE LEO: kayaknya kita harus misahin matriks A dan 
                      matriks b di Ax=b
*/
    public static void main(String[] args) {
	// write your code here
        Matriks M = new Matriks(new float[][] { {3, 5, 6},
                                                {7 ,4, 8},
                                                {6, 5, 1}});
        System.out.println(M);
        System.out.println();
        Matriks Minvers = M.invers();
        System.out.println(Minvers);
        System.out.println();
        System.out.println("determinan: " + M.determinan());

 /*       Matriks augmented = new Matriks(new float[][] { {0, 3, -1, 5},
                                                        {0, 0, -3, 3},
                                                        {-2, 0, -12, 1} });
*/
        Matriks augmented = new Matriks();
       // augmented.MakeFile();
        //augmented.gauss();
        System.out.println(augmented);
        
        Interpolasi X=new Interpolasi();


        
//        augmented.toString();
//        Regresi reg = new Regresi();
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
