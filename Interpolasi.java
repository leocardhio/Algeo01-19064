import java.util.Scanner;

class Point {
    float x,y;
}

public class Interpolasi {
//  Membuat polinom yang dapat menafsir 
//  nilai Pn(Xn) dalam rentang [X0..Xn]
    private Point[] arrP;
    private float[] arr;
    private Matriks M;
    private MAugmented MAug;
    private float x,y,sumMultKoef;
    Scanner scanner=new Scanner(System.in);

    public Interpolasi(){
        int n;
        
        System.out.println("Masukkan n (derajat polinom): ");
        n=scanner.nextInt();

        BacaArr(n);        
        
        InputMatriks(n);
        System.out.println(this.M);
        
        this.M=this.M.gauss();
        System.out.println(this.M);

        
        CalcKoef(n);
        for (int i=0;i<n+1;i++){
            System.out.printf("%.4f\n",arr[i]);
        }

        for (int i=0;i<n+1;i++){
            if (i==0){
                System.out.printf("\nP(x)=");
                System.out.printf("%.4f",arr[i]);
            } else if (i!=0 && arr[i]>=0){
                if (i==1){
                    System.out.printf("+%.4fx",arr[i]);
                } else {
                    System.out.printf("+%.4fx^%d",arr[i],i);
                }
            } else {
                if (i==1){
                    System.out.printf("%.4fx",arr[i]);
                } else {
                    System.out.printf("%.4fx^%d",arr[i],i);
                }
            }
        }
        System.out.printf("\n\nMasukkan nilai yang ingin ditafsir: ");
        x = scanner.nextFloat();
        
        y=0;
        for (int i=0;i<n+1;i++){
            sumMultKoef=0;
            sumMultKoef+=arr[i];
            for (int j=0;j<i;j++){
                sumMultKoef*=x;
            }
            y+=sumMultKoef;
        }
        
        System.out.printf("Nilai P(%f)=%.4f\n",x,y);

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
        this.M=new Matriks(n+1, n+2);
        
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

    private void CalcKoef (int n){
        this.arr = new float[n+1];
        for (int i=0;i<n+1;i++){
            this.arr[n-i] = this.M.get(n-i, n+1);
            for (int j=0;j<i;j++){
                this.arr[n-i] -= this.arr[n-j]*this.M.get(n-i, n-j);   
            }
        }
    }

}
