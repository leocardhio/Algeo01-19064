import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Matriks a = new Matriks(new float[][] {{1, 3, 3}, {3, 4, 5}});
        // Matriks b = new Matriks(new float[][] {{3}, {2}, {5}});
        
        // System.out.println(a.kaliMatriks(b));
        // Matriks M = new Matriks(new float[][] { {3, 5, 6},
        //                                         {7 ,4, 8},
        //                                         {6, 5, 1}});

        MAugmented augmented = new MAugmented(new Matriks(new float[][] { {1, 1, 1, 1},
                                                        {1, 2, 4, 2},
                                                        {1, 3, 9, 3} }));

        System.out.println(augmented.gaussjor());
        System.out.println(augmented.inversSPL());
        
        
        // MAugmented augmented2 = new MAugmented (augmented);
        // System.out.println(augmented2.MatKoef);
        // System.out.println(augmented2.MatVal);
        // Matriks Mgauss = augmented.gauss();
        // System.out.println(Mgauss);
        //System.out.println(M);
        //System.out.println("determinan: " + M.determinan());

        // Matriks augmented = new Matriks();
        // augmented.MakeFile();
        // augmented.gauss();
        // System.out.println(augmented);
        
        // Interpolasi X=new Interpolasi();

        Regresi a = new Regresi();
        System.out.println(a.getTabNormal().toString());
        System.out.println(Arrays.toString(a.getArrayB()));
        System.out.println(a.taksiranToString());
        // MAugmented augmented1 = new MAugmented(new float[][] {  {1, -1, 2, -1, -1},
        //                                                         {2, 1, -2, -2, -2},
        //                                                         {-1, 2, -4, 1, 1},
        //                                                         {3, 0, 0, -3, -3}});

        
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
