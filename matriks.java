public class matriks {
    // Atribut
    // int [][] Mat = new int[10][10];

    // Method;
    // matriks() {
    //     int i, j;

    //     for (i=0;i<10;i++) {
    //         for (j=0;j<10;j++)
    //             this.Mat[i][j] = 0;
    //     }
    // }

    void tulismatriks(int B, int K, float M[][]) {
        int i, j;
        for (i=0;i<B;i++) {
            for (j=0;j<K;j++) {
                System.out.print(M[i][j]);
                if (j < B) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // void transpose(int B, int K, int M[][]) {
    //     int i, j;

    //     for (i=0;i<B;i++) {
    //         for (j=0;j<K;j++)
    //             M[i][j] = this.Mat[j][i];
    //     }
    // }

    float determinan(int N, int M[][]) {
        int i, j, k;
        float D = 0;
        if (N!=2) {
            for (k = 0; k < N; ++k) {
                int [][] K = new int[N-1][N-1];
                for ( i = 1; i < N; ++i) {
                    for (j = 0; j < N; ++j) {
                        if (k!=j) {
                            if (j<k) {
                                K[i-1][j] = M[i][j];
                            }
                            else {
                                K[i-1][j-1] = M[i][j];
                            }
                        }
                    }
                }
                if (k%2 == 0) {
                    D = D + (M[0][k] * determinan(N-1,K));
                }
                else {
                    D = D - (M[0][k] * determinan(N-1,K));
                }
            }
        }
        else
            D = (M[0][0] * M[1][1]) - (M[0][1] * M[1][0]);
        return D;
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