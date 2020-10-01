// import java.io.IOException;
import java.util.Scanner;

class Point {
    float x,y;
}

public class Interpolasi {
//  Membuat polinom yang dapat menafsir 
//  nilai Pn(Xn) dalam rentang [X0..Xn]
    private Point[] arrP;
    private float[] arr;
    private Matriks M, Mfile;
    // private MAugmented MAug;
    private float x,y,sumMultKoef;

    public Interpolasi(){
        int n,choose;
        boolean isValid,isFirst;
        Scanner scanner=new Scanner(System.in);
        String output1,output2,result;
        
        do {
            System.out.printf("1. Membaca Matriks dari keyboard\n2. Membaca Matriks dari File\nPilihan: ");
            choose = scanner.nextInt();
            if (choose==1){
                System.out.print("Masukkan n (derajat polinom): ");
                n=scanner.nextInt();
                BacaArr(n);
                isValid=true; 
            } else if (choose==2){
                this.Mfile = new Matriks();
                n = this.Mfile.getnBrs() - 1;
                InputFromFile(n);
                isValid=true;
            } else {
                System.out.println("Pilihan tidak tersedia!");
                isValid=false;
                n = -1;
            }
        }   while (!isValid);

        InputMatriks(n);
        
        this.M=this.M.gaussM();

        CalcKoef(n);

        isFirst=true;
        output2="";
        output2+="P(x)=";
        for (int i=0;i<n+1;i++){
            if (isFirst){
                if (i==0 && arr[i]!=0){
                    output2+=String.valueOf(arr[i]);
                    isFirst=false;
                } else if (arr[i]>0){
                    if (i==1){
                        output2+=String.valueOf(arr[i])+"x";
                    } else {
                        output2+=String.valueOf(arr[i])+"x^"+String.valueOf(i);
                    }
                    isFirst=false;
                } else if (arr[i]<0) {
                    if (i==1){
                        output2+=String.valueOf(arr[i])+"x";
                    } else {
                        output2+=String.valueOf(arr[i])+"x^"+String.valueOf(i);
                    }
                    isFirst=false;
                }
            } else if (arr[i]>0){
                if (i==1){
                    output2+="+"+String.valueOf(arr[i])+"x";
                } else {
                    output2+="+"+String.valueOf(arr[i])+"x^"+String.valueOf(i);
                }
            } else if (arr[i]<0) {
                if (i==1){
                    output2+=String.valueOf(arr[i])+"x";
                } else {
                    output2+=String.valueOf(arr[i])+"x^"+String.valueOf(i);
                }
            }
        }

        System.out.printf("\n"+output2);

        System.out.printf("\nMasukkan nilai yang ingin ditaksir: ");
        x = scanner.nextFloat();
        System.out.printf("\n");
        
        y=0;
        for (int i=0;i<n+1;i++){
            sumMultKoef=0;
            sumMultKoef+=arr[i];
            for (int j=0;j<i;j++){
                sumMultKoef*=x;
            }
            y+=sumMultKoef;
        }
        
        output1="";
        output1+="Nilai P("+String.valueOf(x)+")="+String.valueOf(y);
        System.out.println(output1);

        result=output2+"\n\n"+output1;

        do {
            System.out.printf("Apakah anda ingin menyimpan hasil ke suatu file?\n1.Yes\n2.No\nPilihan: ");
            choose=scanner.nextInt();
            if(choose==1){
                MakeFile mf = new MakeFile(result);
            } else if(choose==2){

            } else {
                isValid=false;
            }
        } while (!isValid);
    }

/* !!!!!*******************************************************!!!!! */
    private void BacaArr(int n){
        Scanner scanner=new Scanner(System.in);

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

    private void InputFromFile(int n){

        this.arrP=new Point[n+1];
        for (int i=0;i<this.arrP.length;i++){
            this.arrP[i]=new Point();
        }

        for (int i=0;i<n+1;i++){
            this.arrP[i].x=this.Mfile.get(i,0);
            this.arrP[i].y=this.Mfile.get(i,1);
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
