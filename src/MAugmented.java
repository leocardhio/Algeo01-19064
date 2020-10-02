import java.util.Arrays;

public class MAugmented extends Matriks{
    public Matriks MatKoef, MatVal;

//  KONSTRUKTOR
    public MAugmented() {
        super();
        makeMatKoef();
        makeMatKoef();
    }

    public MAugmented (int i, int j) {
        super(i, j);
        makeMatKoef();
        makeMatVal();
    }
    
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
            boolean[] kolomNol = new boolean[MatKoef.getnKol()];
            boolean[] varBebas = new boolean[MatKoef.getnKol()];
            for (int j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++) {
                if (isKolomNol(j)) {
                    kolomNol[j] = true;
                }
            }
            StringBuilder kalimat = new StringBuilder();
            for (int i = MatKoef.getIdxMin(); i < nBrsEff(); i++) {
                kalimat.append(String.format("x%d = %.2f", idxLeadingOne(i) + 1, MatVal.get(i, 0)));
                
                for (int j = idxLeadingOne(i) + 1; j < MatKoef.getnKol(); j++) {
                    if (Math.abs(MatKoef.get(i, j)) > 0.0000001 && !kolomNol[j]) {
                        varBebas[j] = true;
                        String tanda = (MatKoef.get(i, j) > 0) ? "-" : "+";
                        kalimat.append(String.format(" %s %.2fx%d", tanda, Math.abs(MatKoef.get(i, j)), j + 1));
                    }
                }

                kalimat.append("\n");
            }

            for (int j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++){
                if (kolomNol[j] || varBebas[j]) {
                    kalimat.append(String.format("x%d", j + 1));
                    if (j != MatKoef.getnKol() - 1) {
                        kalimat.append(", ");
                    }
                }
            }
            // for (int j = nBrsEff(); j < MatKoef.getnKol(); j++) {
            //     kalimat.append(String.format("x%d", j + 1));
            //     if (j != MatKoef.getnKol() - 1) {
            //         kalimat.append(", ");
            //     }
            // }
            kalimat.append(" bilangan riil\n");

            return kalimat.toString();
        }
    }

    public void makeMatKoef (){
        this.MatKoef = new Matriks(super.getnBrs() ,super.getnKol()-1);
        setMatKoef();
    }

    public void makeMatVal (){
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

    // SOAL/TUGAS

    public MAugmented inversSPL() {
        //hasil.tulisMatriks -> inversKoef, hasil.kalimatSolusi -> x1,x2,x3 dll
        Matriks inversKoef = this.MatKoef.invers();
        MAugmented hasil = new MAugmented(inversKoef);
        
        hasil.MatKoef = this.gaussjor().MatKoef;
        hasil.MatVal = inversKoef.kaliMatriks(this.MatVal);

        return hasil;
    }

    public MAugmented gauss() {
        //jadiin me-return new Matriks mirip kayak invers(), coba testcase
        //dari spek tubes (kalo belom)
        int i, j, a, b, skip = 0;
        float mul, temp, div = 0;
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
            if (i+skip < nKol) {
                div = gaussMat.get(i, i+skip);
            }
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
                        if (i -  1 >= 0) {
                            if (gaussMat.get(i - 1, j) == 0) {
                                skip += 1;
                            }
                        } else if (gaussMat.get(i, j) == 0) {
                            skip += 1;
                        }
                        j += 1;
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
                    for (a = i + 1; a < nBrs && (i + skip) < nKol; ++a) {
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

    public MAugmented cramer() {
        int i, j, k;
        float detj, det;
        Matriks gaussjorKoef = this.gaussjor().MatKoef;
        Matriks koef = new Matriks(this.MatKoef);
        Matriks val = this.MatVal;

        det = koef.determinan();
        if (det == 0) {
            System.out.println("Matriks ini tidak memiliki solusi tunggal, me-return matriks ini.");
            return this;
        }

        for (k = koef.getIdxMin(); k < koef.getnKol(); k++) {
            for (i = koef.getIdxMin(); i < koef.getnBrs(); i++) {
                for (j = koef.getIdxMin(); j < koef.getnKol(); j++) {
                    if (j == k) {
                        koef.set(i, k, get(i, koef.getnKol()));
                    } else {
                        koef.set(i, j, get(i, j));
                    }
                }
            }
            detj = koef.determinan();
            val.set(k, 0, detj/det);
        }
        MAugmented hasil = new MAugmented(koef);
        hasil.MatKoef = gaussjorKoef;
        hasil.MatVal = val;
        return hasil;
    }

    private int idxLeadingOne(int i) {
        for (int j = MatKoef.getIdxMin(); j < MatKoef.getnKol(); j++) {
            if (MatKoef.get(i, j) == 1) {
                return j;
            }
        }

        return -1;
    }

    private boolean isKolomNol(int j) {
        for (int i = MatKoef.getIdxMin(); i < MatKoef.getnBrs(); i++) {
            if (MatKoef.get(i, j) != 0) {
                return false;
            }
        }

        return true;
    }
}