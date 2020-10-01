import java.util.Scanner;

public class Menu {
    public boolean isOn;
    
    public Menu() {
        runInversSPL();
        // isOn = true;
        // System.out.print(MenuStrings.menuawal());
        // Scanner scanner = new Scanner(System.in);

        // int choice;
        // do {
        //     choice = scanner.nextInt();
        //     if (choice < 1 || choice > 6) {
        //         System.out.print("Silahkan input angka yang valid (1-6)\nPilihan: ");
        //     }
        // } while (choice < 1 || choice > 6);
        // System.out.println();
        
        // switch(choice) {
        //     case 1:
        //         menuSPL();
        //         break;
        //     case 2:
        //         menuDet();
        //         break;
        //     case 3:
        //         menuInvers();
        //         break;
        //     case 4:
        //         menuInterp();
        //         break;
        //     case 5:
        //         menuRegresi();
        //         break;
        //     case 6:
        //         System.out.println("keluar dari program\n");
        //         break;
        // }
    }

    // private void menuSPL() {
    //     System.out.println(MenuStrings.strMenuSPL());
    //     Scanner scanner = new Scanner(System.in);

    //     int choice;
    //     do {
    //         choice = scanner.nextInt();
    //         if (choice < 1 || choice > 5) {
    //             System.out.print("Silahkan input angka yang valid (1-5)\nPilihan: ");
    //         }
    //     } while (choice < 1 || choice > 5);
    //     System.out.println();

    //     switch(choice) {
    //         case 1:
    //             runGauss();
    //             break;
    //         case 2:
    //             runGaussJor();
    //             break;
    //         case 3:
    //             runInversSPL();
    //             break;
    //         case 4:
    //             runCramer();
    //             break;
    //         case 5:
    //             break;
    //     }
    // }

    private void runGauss() {
        System.out.printf("Pilih metode pembuatan matriks Gauss: \n1. Membaca dari keyboard\n2. Import dari file (.txt)\n3. Main Menu\nPilihan: ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        MAugmented mat;
        do {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.print("Silahkan input angka yang valid (1-3)\nPilihan: ");
            }
        } while (choice < 1 || choice > 3);

        if (choice == 3) {
            return;
        }
        else if (choice == 2) {
            mat = new MAugmented();
        }
        else {
            System.out.print("Jumlah baris : ");
            int i = scanner.nextInt();
            System.out.print("Jumlah kolom : ");
            int j = scanner.nextInt();

            Matriks matTemp = new Matriks(i, j);
            matTemp.bacaMatriksSPL();

            mat = new MAugmented(matTemp);
        }
        MAugmented matgauss = mat.gauss();
        StringBuilder result = new StringBuilder();

        result.append("matriks:\n");
        result.append(mat);
        result.append("\nhasil gauss:\n");
        result.append(matgauss);
        result.append("\nvariabel x:\n");
        result.append(matgauss.gaussjor().kalimatSolusi());

        System.out.println(result.toString());
        System.out.println("\nSave hasil ke file?\n1.Ya\n2.Tidak");
        int save;
        do {
            save = scanner.nextInt();
            if (save != 1 && save != 2) {
                System.out.println("Input 1 (Ya) atau 2 (Tidak)");
            }
        } while (save != 1 && save != 2);
        if (save == 1) {
            MakeFile saving = new MakeFile(result.toString());
        }
    }

    private void runGaussJor() {
        //Membuat MAugmented mat
        //DARI LINE INI HAMPIR SAMA UTK SEMUA runSPL
        System.out.printf("Pilih metode pembuatan matriks GaussJor: \n1. Membaca dari keyboard\n2. Import dari file (.txt)\n3. Main Menu\nPilihan: ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        MAugmented mat;
        do {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.print("Silahkan input angka yang valid (1-3)\nPilihan: ");
            }
        } while (choice < 1 || choice > 3);

        if (choice == 3) {
            return;
        }
        else if (choice == 2) {
            mat = new MAugmented();
        }
        else {
            System.out.print("Jumlah baris : ");
            int i = scanner.nextInt();
            System.out.print("Jumlah kolom : ");
            int j = scanner.nextInt();

            Matriks matTemp = new Matriks(i, j);
            matTemp.bacaMatriksSPL();

            mat = new MAugmented(matTemp);
        }
        //SAMPAI LINE INI HAMPIR SAMA UTK SEMUA runSPL
        MAugmented matgaussjor = mat.gaussjor();
        StringBuilder result = new StringBuilder();

        result.append("matriks:\n");
        result.append(mat);
        result.append("\nhasil gaussjor:\n");
        result.append(matgaussjor);
        result.append("\nvariabel x:\n");
        result.append(matgaussjor.gaussjor().kalimatSolusi());

        System.out.println(result.toString());
        System.out.println("\nSave hasil ke file?\n1.Ya\n2.Tidak");
        int save;
        do {
            save = scanner.nextInt();
            if (save != 1 && save != 2) {
                System.out.println("Input 1 (Ya) atau 2 (Tidak)");
            }
        } while (save != 1 && save != 2);
        if (save == 1) {
            MakeFile saving = new MakeFile(result.toString());
        }
    }

    private void runInversSPL() {
        //Membuat MAugmented mat
        //DARI LINE INI HAMPIR SAMA UTK SEMUA runSPL
        System.out.printf("Pilih metode pembuatan matriks (inversSPL): \n1. Membaca dari keyboard\n2. Import dari file (.txt)\n3. Main Menu\nPilihan: ");
        Scanner scanner = new Scanner(System.in);
        int choice;
        MAugmented mat;
        do {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.print("Silahkan input angka yang valid (1-3)\nPilihan: ");
            }
        } while (choice < 1 || choice > 3);

        if (choice == 3) {
            return;
        }
        else if (choice == 2) {
            Matriks matTemp = new Matriks();
            mat = new MAugmented(matTemp);
        }
        else {
            System.out.print("Jumlah baris : ");
            int i = scanner.nextInt();
            System.out.print("Jumlah kolom : ");
            int j = scanner.nextInt();

            Matriks matTemp = new Matriks(i, j);
            matTemp.bacaMatriksSPL();

            mat = new MAugmented(matTemp);
        }
        //SAMPAI LINE INI HAMPIR SAMA UTK SEMUA runSPL
        MAugmented matInversSPL = mat.inversSPL();
        StringBuilder result = new StringBuilder();

        result.append("matriks:\n");
        result.append(mat);
        result.append("\ninvers matriks koefisien:\n");
        result.append(matInversSPL);
        result.append("\nnilai b awal:\n");
        result.append(mat.MatVal);
        result.append("\nvariabel x:\n");
        result.append(matInversSPL.kalimatSolusi());

        System.out.println(result.toString());
        System.out.println("\nSave hasil ke file?\n1.Ya\n2.Tidak");
        int save;
        do {
            save = scanner.nextInt();
            if (save != 1 && save != 2) {
                System.out.println("Input 1 (Ya) atau 2 (Tidak)");
            }
        } while (save != 1 && save != 2);
        if (save == 1) {
            MakeFile saving = new MakeFile(result.toString());
        }

    }


}