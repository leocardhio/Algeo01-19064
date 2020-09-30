public class MAugmented extends Matriks{
    Matriks MatKoef, MatVal;

//  KONSTRUKTOR
    public MAugmented (Matriks M){
        makeMatKoef(M);
        makeMatVal(M);
    }


    private void makeMatKoef (Matriks M){
        this.MatKoef = new Matriks(M.getnBrs(),M.getnKol()-1);
        setMatKoef(M);
    }

    private void makeMatVal (Matriks M){
        this.MatVal = new Matriks(M.getnBrs(), 1);
        setMatVal(M);
    }

//  SET
    private void setMatKoef (Matriks M){
        for (int i=0; i<this.MatKoef.getnBrs(); i++){
            for (int j=0; j<this.MatKoef.getnKol(); j++){
                this.MatKoef.set(i, j, M.get(i,j));
            }
        }
    }

    private void setMatVal (Matriks M){
        for (int i=0; i<this.MatVal.getnBrs(); i++){
            this.MatVal.set(i, 0, M.get(i,M.getnKol()-1));
        }
    }
}