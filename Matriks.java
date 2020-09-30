import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
// import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Matriks {
    // Atribut
    private float[][] Mat;
    private int nBrs, nKol;
    private static final byte idxMin = 0;

    //KONSTRUKTOR
    //Konstruktor persegi
    public Matriks(int dimensi) {
        this.Mat = new float[dimensi][dimensi];
        this.nBrs = dimensi;
        this.nKol = dimensi;
    }

    //Konstruktor non persegi
    public Matriks(int baris, int kolom) {
        this.Mat = new float[baris][kolom];
        this.nBrs = baris;
        this.nKol = kolom;
    }

    //Konstruktor terima jadi
    public Matriks(float[][] tabel) {
        this.Mat = tabel;
        this.nBrs = tabel.length;
        this.nKol = tabel[0].length;
    }

    public Matriks(Matriks M){
        this.Mat = M.Mat;
        this.nBrs = M.nBrs;
        this.nKol = M.nKol;
    }

    public Matriks(){
        String filename;
        boolean isValid;
        Scanner scanner= new Scanner(System.in);
        
        do{
            isValid=true;
            try {
                System.out.print("Baca file (tanpa ext.): ");
                filename=scanner.nextLine();
                Scanner sf = new Scanner(new File("./"+filename+".txt"));

                while(sf.hasNextLine()){
                    this.nBrs++;
                    sf.nextLine();
                }
                sf.close();

                sf = new Scanner(new File("./"+filename+".txt"));
                while(sf.hasNextFloat()){
                    this.nKol++;
                    sf.nextFloat();
                }
                this.nKol/=this.nBrs;
                System.out.printf("%d %d",this.nBrs,this.nKol);
                sf.close();


                this.Mat=new float[this.nBrs][this.nKol];
                sf = new Scanner(new File("./"+filename+".txt"));
                for (int i=0;i<this.nBrs;i++){
                    for (int j=0;j<this.nKol;j++){
                        
                        if(sf.hasNextFloat()){
                            set(i, j, sf.nextFloat());
                            System.out.println("tes");
                        }
                    }
                }
            } catch (FileNotFoundException err){
                System.out.println("File not found, coba lagi");
                isValid=false;
            }
        } while (!isValid);
        scanner.close();
    }

    //GETTER SETTER
    //get elemen baris i kolom j
    public float get(int i, int j) {
        return this.Mat[i][j];
    }

    //set elemen baris i kolom j
    public void set(int i, int j, float val) {
        this.Mat[i][j] = val;
    }

    public int getnBrs() {
        return this.nBrs;
    }

    public int getnKol() {
        return this.nKol;
    }

    public int getIdxMin() {
        return idxMin;
    }

    //BACA TULIS
    public void bacaMatriks() {
        Scanner scanner = new Scanner(System.in);

        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                System.out.printf("baris %d kolom %d: ", i, j);
                this.Mat[i][j] = scanner.nextFloat();
            }
        }
        scanner.close();
    }

    public String toString() {
        //me-return matriks dalam bentuk string, dibikin tostring bukannya
        //tulisMatriks() biar gampang output ke file
        StringBuilder matStr = new StringBuilder();
        //stringBuilder() soalnya concat jelek performa nya kalo di loop
        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                matStr.append(String.format("%.2f", this.Mat[i][j]));
                if (j < nKol - 1) {
                    matStr.append("\t");
                }
            }
            matStr.append("\n");
        }

        return matStr.toString();
    }

    //SOAL/TUGAS
    public Matriks transpose() {
        //Me-return new Matriks dengan Mat berupa transpose dari this.Mat
        Matriks transpose = new Matriks(nKol, nBrs);

        for (int j = idxMin; j < nKol; j++) {
            for (int i = idxMin; i < nBrs; i++) {
                transpose.set(j, i, this.get(i, j));
            }
        }

        return transpose;
    }

    public void kaliKonstanta(double c) {
        //mengalikan semua elemen this.Mat dengan c
        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                Mat[i][j] *= c;
            }
        }
    }

    public Matriks gauss() {
        //jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean valid = true;

        Matriks gaussMat = new Matriks(nBrs, nKol);
        for (i = idxMin; i < nBrs; i++) {
            for (j = idxMin; j < nKol; j++) {
                gaussMat.set(i, j, get(i, j));
            }
        }

        for (i = 0; i < nBrs; ++i) {
            div = gaussMat.get(i, i+skip);
            if (div == 0) {
                valid = false;
                for (j = i; j < nKol && !valid;) {
                    for (a = i; a < nBrs && !valid;) {
                        if (gaussMat.get(a, j) != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    
                    if (valid) {
                        for (; j < nKol; ++j) {
                            temp = gaussMat.get(i, j);
                            gaussMat.set(i, j, gaussMat.get(a, j));
                            gaussMat.set(a, j, temp);
                        }
                        div = gaussMat.get(i, i+skip);
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }

            if (valid) {
                if (i + skip < (nKol - 1)) {
                    for (j = 0; j < nKol; ++j) {
                        if (gaussMat.get(i, j) != 0) {
                            gaussMat.set(i, j, gaussMat.get(i, j)/div);
                        }
                    }
                }
                if (i < (nBrs - 1)) {
                    for (a = i + 1; a < nBrs; ++a) {
                        mul = gaussMat.get(a, i+skip) / gaussMat.get(i, i+skip);
                        for (b = 0; b < nKol; ++b) {
                            gaussMat.set(a, b, gaussMat.get(a, b) - (mul*gaussMat.get(i, b)));
                        }
                    }
                }
            }
        }
        return gaussMat;
    }

    public Matriks gaussjor() {
        //Mengubah this.Mat jadi eselon tereduksi
        //jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b;
        float mul;
        boolean found;
        Matriks gaussMat = gauss();
        for (i = 1; i < nBrs; ++i) {
            found = false;
            for (j = i; j < nKol && !found;) {
                if (gaussMat.get(i, j) == 1) {
                    found = true;
                }
                else {
                    ++j;
                }
            }
            if (found) {
                for (a = 0; a < i; ++a) {
                    mul = gaussMat.get(a, j) / gaussMat.get(i, j);
                    for (b = 0; b < nKol; ++b) {
                        gaussMat.set(a, b, gaussMat.get(a, b) - (mul*gaussMat.get(i, b)));
                    }
                }
            }
        }
        return gaussMat;
    }

    public float determinan() {
        //Menghitung determinan dengan ekspansi kofaktor baris pertama
        if (this.nBrs != this.nKol) {
            throw new java.lang.RuntimeException("Matriks tidak persegi.");
        }
        else if (this.nBrs == 2) {
            return (get(0, 0) * get(1, 1) - get(0,1) * get(1, 0));
        }

        float det = 0;

        for (int n = idxMin; n < nKol; n++) {
            //Menghitung minor pada baris pertama
            Matriks minor = makeMinor(idxMin, n);

            int tanda = (n % 2 == 0) ? 1 : -1;
            det += tanda * get(idxMin, n) * minor.determinan();
        }

        return det;
    }

    public float detred() {
        int i, j, a, b, skip = 0, swap = 1;
        float div, mul, temp, D = 1;
        boolean valid = true;

        if (this.nBrs != this.nKol) {
            throw new java.lang.RuntimeException("Matriks tidak persegi.");
        }

        Matriks gaussMat = new Matriks(nBrs, nKol);
        for (i = idxMin; i < nBrs; i++) {
            for (j = idxMin; j < nKol; j++) {
                gaussMat.set(i, j, get(i, j));
            }
        }
    
        for (i = 0; i < nBrs; ++i) {
            div = gaussMat.get(i, i+skip);
            if (div == 0) {
                valid = false;
                for (j = i; j < nBrs && !valid;) {
                    for (a = i; a < nBrs && !valid;) {
                        if (gaussMat.get(a, j) != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }

                    if (valid) {
                        for (; j < nBrs; ++j) {
                            temp = gaussMat.get(i, j);
                            gaussMat.set(i, j, gaussMat.get(a, j));
                            gaussMat.set(a, j, temp);
                        }
                        swap*=(-1);
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }

            if (valid) {
                if (i < (nBrs - 1)) {
                    for (a = i + 1; a < nBrs; ++a) {
                        mul = gaussMat.get(a, i+skip) / gaussMat.get(i, i+skip);
                        for (b = 0; b < nKol; ++b) {
                            gaussMat.set(a, b, gaussMat.get(a, b) - (mul*gaussMat.get(i, b)));
                        }
                    }
                }
            }
        }

        for (i=0; i<nBrs; i++)
            D *= gaussMat.get(i, i);
        return D*swap;
    }

    private Matriks makeMinor(int k, int l) {
        //membuat minor dari Mat pada indeks kl
        Matriks minor = new Matriks(this.nBrs-1);

        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                if (i != k && j != l) {
                    int setI = (i > k) ? i - 1 : i;
                    int setJ = (j > l) ? j - 1 : j;
                    minor.set(setI, setJ, get(i, j));
                }
            }
        }

        return minor;
    }

    public Matriks invers() {
        //Return new Matriks yang Mat nya invers dari this.Mat
        //invers didapat dengan cara adjoin
        float determinan = this.determinan();
        if (this.nBrs != this.nKol) {
            throw new java.lang.RuntimeException("Matriks tidak persegi.");
        }
        else if (determinan == 0) {
            System.out.println("Matriks ini tidak punya invers, me-return matriks ini.");
            return this;
        }

        Matriks cofactor = new Matriks(nBrs);
        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                Matriks minor = makeMinor(i, j);
                int tanda = ((i + j) % 2 == 0) ? 1 : -1;
                float val = minor.determinan() * tanda;

                cofactor.set(i, j, val);
            }
        }

        Matriks adjoin = cofactor.transpose();
        adjoin.kaliKonstanta((1/determinan));

        return adjoin;
    }

    public Matriks inversOBE() {
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean found, valid = true;

        float determinan = this.determinan();
        if (determinan == 0) {
            System.out.println("Matriks ini tidak punya invers, me-return matriks ini.");
            return this;
        }
        
        Matriks gaussMat = new Matriks(nBrs, nKol);
        for (i = idxMin; i < nBrs; i++) {
            for (j = idxMin; j < nKol; j++) {
                gaussMat.set(i, j, get(i, j));
            }
        }
        
        Matriks I = new Matriks(nBrs);
        for (i = 0; i < nBrs; i++) {
            I.set(i, i, 1);
        }

        for (i = 0; i < nBrs; ++i) {
            div = gaussMat.get(i, i+skip);
            if (div == 0) {
                valid = false;
                for (j = i; j < nKol && !valid;) {
                    for (a = i; a < nBrs && !valid;) {
                        if (gaussMat.get(a, j) != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    if (valid) {
                        for (; j < nKol; ++j) {
                            temp = gaussMat.get(i, j);
                            gaussMat.set(i, j, gaussMat.get(a, j));
                            gaussMat.set(a, j, temp);
                            temp = I.get(i, j);
                            I.set(i, j, I.get(a, j));
                            I.set(a, j, temp);
                        }
                        div = gaussMat.get(i, i+skip);
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }
            if (valid) {
                if (i + skip < nKol) {
                    for (j = 0; j < nKol; ++j) {
                        if (gaussMat.get(i, j) != 0) {
                            gaussMat.set(i, j, gaussMat.get(i, j)/div);
                        }
                        if (I.get(i, j) != 0) {
                            I.set(i, j, (I.get(i, j))/div);
                        }
                    }
                }
                if (i < (nBrs - 1)) {
                    for (a = i + 1; a < nBrs; ++a) {
                        mul = gaussMat.get(a, i+skip) / gaussMat.get(i, i+skip);
                        for (b = 0; b < nKol; ++b) {
                            gaussMat.set(a, b, gaussMat.get(a, b) - (mul*gaussMat.get(i, b)));
                            I.set(a, b, ((I.get(a, b)) - mul*(I.get(i, b))));
                        }
                    }
                }
            }
        }
        for (i = 1; i < nBrs; ++i) {
            found = false;
            for (j = i; j < nKol && !found;) {
                if (gaussMat.get(i, j) == 1) {
                    found = true;
                }
                else {
                    ++j;
                }
            }
            if (found) {
                for (a = 0; a < i; ++a) {
                    mul = gaussMat.get(a, j) / gaussMat.get(i, j);
                    for (b = 0; b < nKol; ++b) {
                        gaussMat.set(a, b, gaussMat.get(a, b) - (mul*gaussMat.get(i, b)));
                        I.set(a, b, ((I.get(a, b)) - mul*(I.get(i, b))));
                    }
                }
            }
        }
        return I;
    }


    //  UNDER CONSTRUCTION - LEO
        // public float SPLCramer(){
        //     Matriks Mtemp;
        //     float detMtemp, detM;
    
        //     detM=determinan();
        //     return detM;
        // }
    
    
    public void MakeFile(){
        String filename;
        boolean isFail;
        Scanner scanner= new Scanner(System.in);

        do {
            isFail=false;
            
            System.out.print("Masukkan nama file (tanpa ext.): ");  
            filename=scanner.nextLine();  

            File nf=new File("./",filename+".txt");
            if(!nf.exists() && !nf.isDirectory()){
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".txt"));
                    writer.write(this.toString());
                    writer.flush();
                    writer.close();
                    System.out.println("File sukses dibuat");
                } catch (IOException err){};
            } else {
                System.out.println("Nama file sudah diambil, coba dengan nama yang lain");
                isFail=true;
            }
        } while (isFail);
        scanner.close();
    }

}