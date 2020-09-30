public class MAugmented extends Matriks{
    Matriks MatKoef, MatVal;

//  KONSTRUKTOR
    public MAugmented (Matriks M){
        super(M);
        makeMatKoef();
        makeMatVal();
    }

    public Matriks inversSPL() {
        Matriks inversKoef = this.MatKoef.invers();

        return inversKoef.kaliMatriks(this.MatVal);
    }



    public MAugmented gauss() {
        //jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b, skip = 0;
        float div, mul, temp;
        boolean valid = true;
        int nBrs = super.getnBrs();
        int nKol = super.getnKol();
        int idxMin = super.getIdxMin();

        MAugmented gaussMat = new MAugmented(new Matriks(nBrs, nKol));

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
        gaussMat.setMatKoef();
        gaussMat.setMatVal();
        return gaussMat;
    }

    public MAugmented gaussjor() {
        //Mengubah this.Mat jadi eselon tereduksi
        //jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b;
        float mul;
        boolean found;
        MAugmented gaussMat = gauss();
        int nBrs = super.getnBrs();
        int nKol = super.getnKol();
        int idxMin = super.getIdxMin();

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
        gaussMat.setMatKoef();
        gaussMat.setMatVal();
        return gaussMat;
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
    public void setMatKoef (){
        for (int i=0; i<this.MatKoef.getnBrs(); i++){
            for (int j=0; j<this.MatKoef.getnKol(); j++){
                this.MatKoef.set(i, j, super.get(i,j));
            }
        }
    }

    public void setMatVal (){
        for (int i=0; i<this.MatVal.getnBrs(); i++){
            this.MatVal.set(i, 0, super.get(i,super.getnKol()-1));
        }
    }
}