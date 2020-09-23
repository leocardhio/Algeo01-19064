public class matriks {
    // Atribut
    int [][] Mat = new int[10][10];

    // Method;
    matriks() {
        int i, j;

        for (i=0;i<10;i++) {
            for (j=0;j<10;j++)
                this.Mat[i][j] = 0;
        }
    }

    int get(matriks M, int i, int j) {
        return this.Mat[i][j];
    }

    void isimatriks(int N, int M) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++)
                this.Mat[i][j] = i+j;
        }
    }

    void tulismatriks(int N, int M) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++) {
                System.out.print(this.Mat[i][j]);
                if (j < M - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    void transpose(int N, int M, int Matt[][]) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++)
                Matt[i][j] = this.Mat[j][i];
        }
    }

    float determinan(int N, int M[][]) {
        int i, j, k;
        float D = 0;
        if (N!=2) {
            for (k = 0; k < N; ++k) {
                int [][] K = new int[N-1][N-1];
                for ( i = 1; i < N; ++i) {
                    for (j = 0; j < N; ++j) {
                        if (k!=j) {
                            if (j<k)
                                K[i-1][j] = M[i][j];
                            else
                                K[i-1][j-1] = M[i][j];
                        }
                    }
                }
                if (k%2 == 0)
                    D = D + (M[0][k] * determinan(N-1,K));
                else
                    D = D - (M[0][k] * determinan(N-1,K));
            }
        }
        else
            D = (M[0][0] * M[1][1]) - (M[0][1] * M[1][0]);
        return D;
    }

    void gauss(int M, int N, float G[][]) {
        int i, j, k, l;
        float div, mul;
        for ( i = 0; i < M; ++i) {
            div = G[i][i];
            for (j = i; j < N; ++j)
                G[i][j] = G[i][j] / div;
            if (i < (M - 1)) {
                for ( k = i + 1; k < M; ++k) {
                    mul = G[k][i] / G[i][i];
                    for (l = 0; l < N; ++l)
                        G[k][l] = G[k][l] - (mul*G[i][l]);
                }
            }
        }
    }

}