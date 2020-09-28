package com.tubesAlgeo;

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
    }

    public void tulisMatriks() {
        //tulis matriks di command line
        for (int i = idxMin; i < nBrs; i++) {
            for (int j = idxMin; j < nKol; j++) {
                System.out.printf("%.2f", this.Mat[i][j]);
                if (j < nKol - 1) {
                    System.out.printf("\t");
                }
            }
            System.out.println();
        }
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

    float detred(int N, float M[][]) {
        int i, j, a, b, skip = 0, swap = 1;
        float mul, temp, D = 1;
        boolean valid = true;
        for ( i = 0; i < N; ++i) {
            if (M[i][i+skip] == 0) {
                valid = false;
                for (j = i; j < N && !valid;) {
                    for ( a = i; a < N && !valid;) {
                        if (M[a][j] != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    if (valid) {
                        for (; j < N; ++j) {
                            temp = M[i][j];
                            M[i][j] = M[a][j];
                            M[a][j] = temp;
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
                if (i < (N - 1)) {
                    for ( a = i + 1; a < N; ++a) {
                        mul = M[a][i+skip] / M[i][i+skip];
                        for (b = i + skip; b < N; ++b) {
                            M[a][b] -= (mul*M[i][b]);
                        }
                    }
                }
            }
        }
        for (i=0; i<N; i++)
            D *= M[i][i];
        return D*swap;
    }

    public void gauss() {
        //TODO jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean valid = true;
        for ( i = 0; i < nBrs; ++i) {
            div = Mat[i][i+skip];
            if (div == 0) {
                valid = false;
                for (j = i; j < nKol && !valid;) {
                    for ( a = i; a < nBrs && !valid;) {
                        if (Mat[a][j] != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    if (valid) {
                        for (; j < nKol; ++j) {
                            temp = Mat[i][j];
                            Mat[i][j] = Mat[a][j];
                            Mat[a][j] = temp;
                        }
                        div = Mat[i][i+skip];
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }
            if (valid) {
                if (i + skip < (nKol - 1)) {
                    for (j = i + skip; j < nKol; ++j) {
                        Mat[i][j] /= div;
                    }
                }
                if (i < (nBrs - 1)) {
                    for ( a = i + 1; a < nBrs; ++a) {
                        mul = Mat[a][i+skip] / Mat[i][i+skip];
                        for (b = i + skip; b < nKol; ++b) {
                            Mat[a][b] -= (mul*Mat[i][b]);
                        }
                    }
                }
            }
        }
    }

    public void gaussjor() {
        //Mengubah this.Mat jadi eselon tereduksi
        //TODO jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b;
        float mul;
        boolean found;
        gauss();
        for ( i = 1; i < nBrs; ++i) {
            found = false;
            for (j = i; j < nKol && !found;) {
                if (Mat[i][j] == 1) {
                    found = true;
                }
                else {
                    ++j;
                }
            }
            if (found) {
                for ( a = 0; a < i; ++a) {
                    mul = Mat[a][j] / Mat[i][j];
                    for (b = j; b < nKol; ++b) {
                        Mat[a][b] -= (mul*Mat[i][b]);
                    }
                }
            }
        }
    }

    public Matriks invers() {
        //Me return new Matriks yang Mat nya invers dari this.Mat
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
}
