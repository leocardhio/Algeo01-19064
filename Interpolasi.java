import java.util.Scanner;

class Point {
    float x,y;
}

public class Interpolasi {
//  Membuat polinom yang dapat menafsir 
//  nilai Pn(Xn) dalam rentang [X0..Xn]
    private Point[] arrP;
    private Matriks M;
    private MAugmented MAug;
    Scanner scanner=new Scanner(System.in);

    public Interpolasi(){
        int n;
        
        System.out.println("Masukkan n (derajat polinom): ");
        n=scanner.nextInt();

        BacaArr(n);        
        
        InputMatriks(n);
        System.out.println(this.M);
        this.MAug = new MAugmented(this.M);
   
        
        this.M.gauss();
        System.out.println(this.M);
    }

/* !!!!!*******************************************************!!!!! */
    private void BacaArr(int n){
        this.arrP=new Point[n+1];
        for (int i=0;i<this.arrP.length;i++){
            this.arrP[i]=new Point();
        }
        
        for (int i=0;i<=n;i++){
            System.out.printf("Masukkan x[%d]: ",i);
            this.arrP[i].x=scanner.nextFloat();
            System.out.printf("Masukkan y[%d]: ",i);
            this.arrP[i].y=scanner.nextFloat();
        } 
    }

    private void InputMatriks(int n){
        this.M=new Matriks(new float[n+1][n+2]);
        
        for (int i=0;i<=n;i++){
            for (int j=0;j<=n;j++){
                this.M.set(i,j,1);
                for (int k=1;k<=j;k++){    
                    this.M.set(i,j,(this.M.get(i, j)*this.arrP[i].x));
                }
            }
            this.M.set(i,n+1,this.arrP[i].y);
        }
    }

}
