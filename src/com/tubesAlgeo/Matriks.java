package com.tubesAlgeo;

import java.util.Scanner;

public class Matriks {
    // Atribut
    private float[][] Mat;
    private int nBrs, nKol;
    private static final byte idxMin = 0;

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

    public float get(int i, int j) {
        return this.Mat[i][j];
    }

    public void set(int i, int j, float val) {
        this.Mat[i][j] = val;
    }

    // void transpose(int B, int K, int M[][]) {
    //     int i, j;

    //     for (i=0;i<B;i++) {
    //         for (j=0;j<K;j++)
    //             M[i][j] = this.Mat[j][i];
    //     }
    // }

    public float determinan() {
        if (this.nBrs != this.nKol) {
            throw new java.lang.RuntimeException("Matriks tidak persegi.");
        }
        else if (this.nBrs == 2) {
            return (get(0, 0) * get(1, 1) - get(0,1) * get(1, 0));
        }

        float det = 0;

        for (int n = idxMin; n < nKol; n++) {
            //Menghitung minor pada baris pertama
            Matriks minor = new Matriks(nBrs-1, nKol-1);

            for (int i = idxMin+1; i < nBrs; i++) {
                for (int j = idxMin; j < nKol; j++) {
                    if (j < n) {
                        minor.set(i-1, j, get(i, j));
                    }
                    else if (j > n) {
                        minor.set(i-1, j-1, get(i, j));
                    }
                }
            }
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

    float [][] gauss(int B, int K, float M[][]) {
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean valid = true;
        for ( i = 0; i < B; ++i) {
            div = M[i][i+skip];
            if (div == 0) {
                valid = false;
                for (j = i; j < K && !valid;) {
                    for ( a = i; a < B && !valid;) {
                        if (M[a][j] != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    if (valid) {
                        for (; j < K; ++j) {
                            temp = M[i][j];
                            M[i][j] = M[a][j];
                            M[a][j] = temp;
                        }
                        div = M[i][i+skip];
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }
            if (valid) {
                if (i + skip < (K - 1)) {
                    for (j = i + skip; j < K; ++j) {
                        M[i][j] /= div;
                    }
                }
                if (i < (B - 1)) {
                    for ( a = i + 1; a < B; ++a) {
                        mul = M[a][i+skip] / M[i][i+skip];
                        for (b = i + skip; b < K; ++b) {
                            M[a][b] -= (mul*M[i][b]);
                        }
                    }
                }
            }
        }
        return M;
    }

    float [][] gaussjor(int B, int K, float M[][]) {
        int i, j, a, b;
        float mul;
        boolean found;
        gauss(B, K, M);
        for ( i = 1; i < B; ++i) {
            found = false;
            for (j = i; j < K && !found;) {
                if (M[i][j] == 1) {
                    found = true;
                }
                else {
                    ++j;
                }
            }
            if (found) {
                for ( a = 0; a < i; ++a) {
                    mul = M[a][j] / M[i][j];
                    for (b = j; b < K; ++b) {
                        M[a][b] -= (mul*M[i][b]);
                    }
                }
            }
        }
        return M;
    }

    float [][] invers(int B, int K, float M[][]) {
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean found, valid = true;
        float [][] I = new float[B][K];
        for (i=0;i<B;i++) {
            I[i][i] = 1;
        }
        for ( i = 0; i < B; ++i) {
            div = M[i][i+skip];
            if (div == 0) {
                valid = false;
                for (j = i; j < K && !valid;) {
                    for ( a = i; a < B && !valid;) {
                        if (M[a][j] != 0) {
                            valid = true;
                        }
                        else {
                            a++;
                        }
                    }
                    if (valid) {
                        for (; j < K; ++j) {
                            temp = M[i][j];
                            M[i][j] = M[a][j];
                            M[a][j] = temp;
                            temp = I[i][j];
                            I[i][j] = I[a][j];
                            I[a][j] = temp;
                        }
                        div = M[i][i+skip];
                    }
                    else {
                        j += 1;
                        skip += 1;
                    }
                }
            }
            if (valid) {
                if (i + skip < K) {
                    for (j = i + skip; j < K; ++j) {
                        M[i][j] /= div;
                        I[i][j] /= div;
                    }
                }
                if (i < (B - 1)) {
                    for ( a = i + 1; a < B; ++a) {
                        mul = M[a][i+skip] / M[i][i+skip];
                        for (b = i + skip; b < K; ++b) {
                            M[a][b] -= (mul*M[i][b]);
                            I[a][b] -= (mul*I[i][b]);
                        }
                    }
                }
            }
        }
        for ( i = 1; i < B; ++i) {
            found = false;
            for (j = i; j < K && !found;) {
                if (M[i][j] == 1) {
                    found = true;
                }
                else {
                    ++j;
                }
            }
            if (found) {
                for ( a = 0; a < i; ++a) {
                    mul = M[a][j] / M[i][j];
                    for (b = j; b < K; ++b) {
                        M[a][b] -= (mul*M[i][b]);
                        I[a][b] -= (mul*I[i][b]);
                    }
                }
            }
        }
        return I;
    }
}
