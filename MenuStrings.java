public class MenuStrings {

    public static String menuawal() {
        StringBuilder menuawal = new StringBuilder();
        menuawal.append(" ========================================================================== \n");
        menuawal.append("|--------------------------------------------------------------------------|\n");
        menuawal.append("|-----------##########--#------#--#######----#######----#######------------|\n");
        menuawal.append("|----------------#------#------#--#------#---#---------#-------------------|\n");
        menuawal.append("|----------------#------#------#--#######----#######----#######------------|\n");
        menuawal.append("|----------------#------#------#--#------#---#-----------------#-----------|\n");
        menuawal.append("|----------------#-------######---#######----#######----#######------------|\n");
        menuawal.append("|--------------------------------------------------------------------------|\n");
        menuawal.append(" ========================================================================== \n");
        menuawal.append("------------MAIN MENU-----------\n");
        menuawal.append("1. Sistem Persamaan Linear\n");
        menuawal.append("2. Determinan\n");
        menuawal.append("3. Matriks balikan\n");
        menuawal.append("4. Interpolasi Polinom\n");
        menuawal.append("5. Regresi linier berganda\n");
        menuawal.append("6. Keluar\n");
        menuawal.append("Pilihan: ");

        return menuawal.toString();
    }

    public static String strMenuSPL() {
        StringBuilder menuspl = new StringBuilder();
        menuspl.append("Pilih Metode yang ingin digunakan :\n");
        menuspl.append("1. Metode Eliminasi Gauss\n");
        menuspl.append("2. Metode Eliminasi Gauss-Jordan\n");
        menuspl.append("3. Metode matriks balikan\n");
        menuspl.append("4. Kaidah Cramer\n");
        menuspl.append("5. Main Menu\n");
        menuspl.append("Pilihan: ");

        return menuspl.toString();
    }
}