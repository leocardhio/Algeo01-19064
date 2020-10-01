<<<<<<< HEAD
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new scanner (System.in);
=======
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Matriks a = new Matriks(new float[][] {{1, 3, 3}, {3, 4, 5}});
        // Matriks b = new Matriks(new float[][] {{3}, {2}, {5}});
        
        // System.out.println(a.kaliMatriks(b));
>>>>>>> fb2b1698fd7e1a38c2792f97359e3d5a2e88c8d3
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

        // System.out.println(augmented1.gaussjor());
        int choose;
        boolean isOn;

            isOn=true;
            System.out.println(" ==========================================================================");
            System.out.println("|--------------------------------------------------------------------------|");
            System.out.println("|-----------##########--#------#--#######----#######----#######------------|");
            System.out.println("|----------------#------#------#--#------#---#---------#-------------------|");
            System.out.println("|----------------#------#------#--#######----#######----#######------------|");
            System.out.println("|----------------#------#------#--#------#---#-----------------#-----------|");
            System.out.println("|----------------#-------######---#######----#######----#######------------|");
            System.out.println("|--------------------------------------------------------------------------|");
            System.out.println(" ==========================================================================");

        while (isOn){
            System.out.printf("\n------------MAIN MENU-----------\n");
            System.out.printf("1. Sistem Persamaan Linear\n2. Determinan\n3. Matriks balikan\n4. Interpolasi Polinom\n5. Regresi linier berganda\n6. Keluar\nPilihan: ");
            choose = scanner.nextInt();
            if (choose==1){

            } else if (choose==2){
                //detred n eksko
            } else if (choose==3){
                //adj n gaussjor
            } else if (choose==4){
                
            } else if (choose==5){

            } else if (choose==6){
                System.out.println("Keluar dari program.");
                isOn=false;
            } else {
                System.out.println("Masukan invalid!");
            }
        }
        
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
