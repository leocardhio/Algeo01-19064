public class matriks {
    // Atribut
    int [][] Mat = new int[10][10];

    // Method;
    matriks() {
        int i, j;

        for (i=0;i<10;i++) {
            for (j=0;j<10;j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    int get(matriks M, int i, int j) {
        return this.Mat[i][j];
    }

    void isimatriks(int N, int M) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++) {
                this.Mat[i][j] = i+j;
            }
        }
    }

    void tulismatriks(int N, int M) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++) {
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    void transpose(int N, int M, int Matt[][]) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++) {
                Matt[i][j] = this.Mat[j][i];
            }
        }
    }

    float determinan(int N, int Mat[][]) {
        int i, j, k;
        float D = 0;
        if (N!=2) {
            for (k = 0; k < N; ++k) {
                int [][] K = new int[N-1][N-1];
                for ( i = 1; i < N; ++i) {
                    for (j = 0; j < N; ++j) {
                        if (k!=j) {
                            if (j<k)
                                K[i-1][j] = Mat[i][j];
                            else
                                K[i-1][j-1] = Mat[i][j];
                        }
                    }
                }
                System.out.print("D awal: ");
                System.out.println(D);
                if (k%2 == 0)
                    D = D + (Mat[0][k] * determinan(N-1,K));
                else
                    D = D - (Mat[0][k] * determinan(N-1,K));
                System.out.print("D akhir: ");
                System.out.println(D);
            }
        }
        else
            D = (Mat[0][0] * Mat[1][1]) - (Mat[0][1] * Mat[1][0]);
        return D;
    }

}