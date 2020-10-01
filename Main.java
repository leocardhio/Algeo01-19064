import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new scanner (System.in);
        // Matriks M = new Matriks(new float[][] { {3, 5, 6},
        //                                         {7 ,4, 8},
        //                                         {6, 5, 1}});

        MAugmented augmented = new MAugmented(new Matriks(new float[][] {   {3, 5, 3, 4, 9},
                                                                            {4, 3, 5, 6, 12},
                                                                            {2, 4, 4, 4, 7},
                                                                            {0, 0, 0, 0, 0} }));

        MAugmented augmentedgj = augmented.gaussjor();
        System.out.println(augmentedgj);
        System.out.println(augmentedgj.kalimatSolusi());

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
    }
}
