public class MAugmented extends Matriks{
    Matriks MatKoef, MatVal;

    // KONSTRUKTOR
    public MAugmented (Matriks M){
        super(M);
        makeMatKoef();
        makeMatVal();
    }

    public Matriks inversSPL() {
        Matriks inversKoef = this.MatKoef.invers();

        return inversKoef.kaliMatriks(this.MatVal);
    }

    private void makeMatKoef (){
        this.MatKoef = new Matriks(super.getnBrs() ,super.getnKol()-1);
        setMatKoef();
    }

    private void makeMatVal (){
        this.MatVal = new Matriks(super.getnBrs(), 1);
        setMatVal();
    }

    //  SET
    private void setMatKoef (){
        for (int i=0; i<this.MatKoef.getnBrs(); i++){
            for (int j=0; j<this.MatKoef.getnKol(); j++){
                this.MatKoef.set(i, j, super.get(i,j));
            }
        }
    }

    private void setMatVal (){
        for (int i=0; i<this.MatVal.getnBrs(); i++){
            this.MatVal.set(i, 0, super.get(i,super.getnKol()-1));
        }
    }

    // SOAL/TUGAS

    public Matriks cramer() {
        int i, j, k;
        float detj, det;
        
        det = MatKoef.determinan();
        if (det == 0) {
            System.out.println("Matriks ini tidak memiliki solusi tunggal, me-return matriks ini.");
            return this;
        }
        System.out.println("det: " + det);

        for (k = MatKoef.getIdxMin(); k < MatKoef.getnKol(); k++) {
            for (i = MatKoef.getIdxMin(); i < MatKoef.getnBrs(); i++) {
                for (j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++) {
                    if (j == k) {
                        MatKoef.set(i, k, get(i, MatKoef.getnKol()));
                    } else {
                        MatKoef.set(i, j, get(i, j));
                    }
                }
            }
            System.out.println(MatKoef);
            detj = MatKoef.determinan();
            System.out.println("detj: " + detj);
            MatVal.set(k, 0, detj/det);
        }

        return MatVal;
    }
}