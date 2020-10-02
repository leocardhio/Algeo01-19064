import java.util.Arrays;
import java.util.Scanner;

public class Regresi {
    private Matriks tabEQ; //tabel n persamaan dengan k variabel
    private MAugmented tabNormal; //tabel normal estimation eq
    private MAugmented solusiTabNormal;
    private Matriks tabTaksir; //tabel nilai yang akan di taksir


    public Regresi(boolean inputFile) {
        //input k dan n
        if (inputFile) {
            System.out.println("Membuat TabEQ...");

            tabEQ = new Matriks();
            System.out.println(tabEQ);
            makeTabNormal(tabEQ.getnKol() - 1);

            System.out.println("Membuat tabTaksir...");
            
            Matriks temp = new Matriks();
            makeTabTaksirFile(temp);
            this.solusiTabNormal = this.tabNormal.gaussjor();
            taksirNilai();
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan k (banyaknya variabel x): ");
            int k = scanner.nextInt();

            int n;
            do {
                System.out.print("Masukkan n (banyaknya persamaan): ");
                n = scanner.nextInt();
                if (n <= k + 1) {
                    System.out.println("n harus lebih besar dari k + 1");
                }
            } while (n <= k + 1);

            makeTabEQ(n, k);
            makeTabNormal(k);

            System.out.print("Masukkan banyak persamaan yang ingin ditaksir: ");
            int nTaksir = scanner.nextInt();

            makeTabTaksir(nTaksir, k);
            this.solusiTabNormal = this.tabNormal.gaussjor();
            taksirNilai();
        }
    }

    public Matriks getTabEQ() {
        return this.tabEQ;
    }

    public MAugmented getSolusiTabNormal() {
        return this.solusiTabNormal;
    }

    public Matriks getTabTaksir() {
        return this.tabTaksir;
    }

    public MAugmented getTabNormal() {
        return this.tabNormal;
    }

    public String taksiranToString() {
        StringBuilder stringTaksiran = new StringBuilder();
        stringTaksiran.append("taksiran:\n");
        for (int i = tabTaksir.getIdxMin(); i < tabTaksir.getnKol(); i++) {
            if (i != tabTaksir.getnKol() - 1) {
                stringTaksiran.append(String.format("x%d", i + 1));
                stringTaksiran.append("\t");
            }
            else {
                stringTaksiran.append("y");
                stringTaksiran.append("\n");
            }
        }

        stringTaksiran.append(tabTaksir.toString());

        return stringTaksiran.toString();
    }

    public float[] getArrayB() {
        //Mereturn array berisi konstanta Bn, n adalah indeks di array.
        //array didapat dari matrix solusitabnormal
        float[] arrayB = new float[solusiTabNormal.getnBrs()];

        for (int i = solusiTabNormal.getIdxMin(); i < solusiTabNormal.getnBrs(); i++) {
            arrayB[i] = solusiTabNormal.get(i, solusiTabNormal.getnKol() - 1);
        }

        return arrayB;
    }

    private void makeTabEQ(int n, int k) {
        //membuat tabel persamaan yang didapat dari input keyboard,
        //tiap baris isinya x1 x2 ... xn y
        Scanner scanner = new Scanner(System.in);
        this.tabEQ = new Matriks(n, k + 1);

        System.out.println("x[i][j], persamaan ke-i, variabel ke-j");

        for (int i = this.tabEQ.getIdxMin(); i < this.tabEQ.getnBrs(); i++) {
            for (int j = this.tabEQ.getIdxMin(); j < this.tabEQ.getnKol(); j++) {
                if (j == this.tabEQ.getnKol() - 1) {
                    System.out.printf("y[%d]: ", i+1);
                }
                else {
                    System.out.printf("x[%d][%d]: ", i+1, j+1);
                }
                this.tabEQ.set(i, j, scanner.nextFloat());
            }
        }
    }

    private void makeTabTaksir(int nTaksir, int k) {
        //Membuat tabel yang tiap barisnya x1, x2,...,xn untuk persamaan yang ingin
        //ditaksir, kolom terakhir berisi nilai taksirannya, awalnya 0, untuk mengisi
        //taksirannya run taksirNilai()
        Scanner scanner = new Scanner(System.in);
        this.tabTaksir = new Matriks(nTaksir, k + 1);

        for (int i = this.tabTaksir.getIdxMin(); i < this.tabTaksir.getnBrs(); i++) {
            for (int j = this.tabTaksir.getIdxMin(); j < this.tabTaksir.getnKol(); j++) {
                if (j == this.tabTaksir.getnKol() - 1) {
                    this.tabTaksir.set(i, j, 0);
                }
                else {
                    System.out.printf("x[%d][%d]: ", i + 1, j + 1);
                    this.tabTaksir.set(i, j, scanner.nextFloat());
                }
            }
        }
    }

    private void makeTabTaksirFile(Matriks temp) {
        //Membuat tabel yang tiap barisnya x1, x2,...,xn untuk persamaan yang ingin
        //ditaksir, kolom terakhir berisi nilai taksirannya, awalnya 0, untuk mengisi
        //taksirannya run taksirNilai()
        Scanner scanner = new Scanner(System.in);
        this.tabTaksir = new Matriks(temp.getnBrs(), temp.getnKol() + 1);

        for (int i = this.tabTaksir.getIdxMin(); i < this.tabTaksir.getnBrs(); i++) {
            for (int j = this.tabTaksir.getIdxMin(); j < this.tabTaksir.getnKol(); j++) {
                if (j == this.tabTaksir.getnKol() - 1) {
                    this.tabTaksir.set(i, j, 0);
                }
                else {
                    this.tabTaksir.set(i, j, temp.get(i, j));
                }
            }
        }
    }

    private void makeTabNormal(int l) {
        //Menghasilkan matriks Normal Equation utk regresi berganda, lebih detailnya
        //bisa diliat di spek tubes
        this.tabNormal = new MAugmented(l + 1, l + 2);

        for (int i = this.tabNormal.getIdxMin(); i < this.tabNormal.getnBrs(); i++) {
            for ( int j = this.tabNormal.getIdxMin(); j < this.tabNormal.getnKol(); j++) {
                for (int k = this.tabEQ.getIdxMin(); k < this.tabEQ.getnBrs(); k++) {
                    float temp = this.tabNormal.get(i, j);

                    float multiplier = (i == 0) ? 1 : this.tabEQ.get(k, i - 1);
                    float column = (j == 0) ? 1 : this.tabEQ.get(k, j - 1);

                    temp += (multiplier * column);
                    this.tabNormal.set(i, j, temp);
                }
            }
        }
        this.tabNormal.makeMatKoef();
        this.tabNormal.makeMatKoef();
    }

    private void taksirNilai() {
        //menghasilkan taksiran, y = B0 + B1x1+ B2x2 + ... + Bn+1xn
        float[] arrayB = getArrayB();
        int jVal = tabTaksir.getnKol() - 1;

        for (int i = tabTaksir.getIdxMin(); i < tabTaksir.getnBrs(); i++) {
            float taksiran = arrayB[0];
            for (int j = tabTaksir.getIdxMin(); j < jVal; j++) {
                taksiran += (arrayB[j + 1] * tabTaksir.get(i, j));
            }

            tabTaksir.set(i, jVal, taksiran);
        }
    }
}
