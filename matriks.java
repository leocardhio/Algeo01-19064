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

    void transpose(int N, int M, int Mat[][]) {
        int i, j;

        for (i=0;i<N;i++) {
            for (j=0;j<M;j++) {
                Mat[j][i] = Mat[i][j];
            }
        }
    }
}