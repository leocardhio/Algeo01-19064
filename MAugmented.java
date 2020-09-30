public class MAugmented extends Matriks{
    public Matriks MatKoef, MatVal;

//  KONSTRUKTOR
    public MAugmented (float[][] mat) {
        super(mat);
        makeMatKoef();
        makeMatVal();
    }

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
        gaussMat.makeMatKoef();
        gaussMat.makeMatVal();
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
        gaussMat.makeMatKoef();
        gaussMat.makeMatVal();
        return gaussMat;
    }

    public String kalimatSolusi() {
        if (isInconsistent()) {
            return "Tidak ada solusi\n";
        }
        else if (nBrsEff() == MatKoef.getnKol()) {
            StringBuilder kalimat = new StringBuilder();
            for (int j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++) {
                kalimat.append(String.format("x%d = %.2f\n", j + 1, MatVal.get(j, 0)));
            }

            return kalimat.toString();
        }
        else {
            StringBuilder kalimat = new StringBuilder();
            for (int i = MatKoef.getIdxMin(); i < nBrsEff(); i++) {
                kalimat.append(String.format("x%d = %.2f", i + 1, MatVal.get(i, 0)));
                
                for (int j = nBrsEff(); j < MatKoef.getnKol(); j++) {
                    if (Math.abs(MatKoef.get(i, j)) > 0.0000001) {
                        String tanda = (MatKoef.get(i, j) > 0) ? "-" : "+";
                        kalimat.append(String.format(" %s %.2fx%d", tanda, Math.abs(MatKoef.get(i, j)), j + 1));
                    }
                }

                kalimat.append("\n");
            }

            for (int j = nBrsEff(); j < MatKoef.getnKol(); j++) {
                kalimat.append(String.format("x%d", j + 1));
                if (j != MatKoef.getnKol() - 1) {
                    kalimat.append(", ");
                }
            }
            kalimat.append(" bilangan riil\n");

            return kalimat.toString();
        }
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

    private boolean isBarisKoefNol(int i) {
        for (int j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++) {
            if (MatKoef.get(i, j) != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isInconsistent() {
        for (int i = MatKoef.getIdxMin(); i < MatKoef.getnBrs(); i++) {
            if (isBarisKoefNol(i) && MatVal.get(i, 0) != 0) {
                return true;
            }
        }

        return false;
    }

    private int nBrsEff() {
        int nEff = MatKoef.getnBrs();

        for (int i = MatKoef.getIdxMin(); i < MatKoef.getnBrs(); i++) {
            if (isBarisKoefNol(i) && MatVal.get(i, 0) == 0) {
                nEff -= 1;
            }
        }

        return nEff;
    }
}