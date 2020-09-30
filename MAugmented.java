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
}