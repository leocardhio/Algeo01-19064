package com.tubesAlgeo;

import java.util.Scanner;

public class Regresi {
    private Matriks tabEQ; //tabel n persamaan dengan k variabel
    private Matriks tabNormal; //tabel normal estimation eq
    private Matriks tabTaksir; //tabel nilai yang akan di taksir

    public Regresi() {
        //input k dan n
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan k (banyaknya variabel x): ");
        int k = scanner.nextInt();

        int n;
        do {
            System.out.print("Masukkan n (banyaknya persamaan): ");
            n = scanner.nextInt();
            if (n <= k + 1) {
                System.out.println("n harus lebih besar dari k + 1");
            }
        } while (n <= k + 1);

        makeTabEQ(n, k);
        makeTabNormal(k);

        System.out.print("Masukkan banyak persamaan yang ingin ditaksir: ");
        int nTaksir = scanner.nextInt();

        makeTabTaksir(nTaksir, k);

        this.tabEQ.tulisMatriks();
        System.out.println();
        this.tabNormal.tulisMatriks();
        System.out.println();
        System.out.println("taksiran:");
        this.tabNormal.gaussjor();
        this.tabNormal.tulisMatriks();
    }

    private void makeTabEQ(int n, int k) {
        Scanner scanner = new Scanner(System.in);
        this.tabEQ = new Matriks(n, k + 1);

        System.out.println("x[i][j], persamaan ke-i, variabel ke-j");

        for (int i = this.tabEQ.getIdxMin(); i < this.tabEQ.getnBrs(); i++) {
            for (int j = this.tabEQ.getIdxMin(); j < this.tabEQ.getnKol(); j++) {
                if (j == this.tabEQ.getnKol() - 1) {
                    System.out.printf("y[%d]: ", i+1);
                }
                else {
                    System.out.printf("x[%d][%d]: ", i+1, j+1);
                }
                this.tabEQ.set(i, j, scanner.nextFloat());
            }
        }
    }

    private void makeTabTaksir(int nTaksir, int k) {
        Scanner scanner = new Scanner(System.in);
        this.tabTaksir = new Matriks(nTaksir, k);

        for (int i = this.tabTaksir.getIdxMin(); i < this.tabTaksir.getnBrs(); i++) {
            for (int j = this.tabTaksir.getIdxMin(); j < this.tabTaksir.getnKol(); j++) {
                System.out.printf("x[%d][%d]: ", i+1, j+1);
                this.tabTaksir.set(i, j, scanner.nextFloat());
            }
        }
    }

    private void makeTabNormal(int l) {
        this.tabNormal = new Matriks(l + 1, l + 2);

        for (int i = this.tabNormal.getIdxMin(); i < this.tabNormal.getnBrs(); i++) {
            for ( int j = this.tabNormal.getIdxMin(); j < this.tabNormal.getnKol(); j++) {
                for (int k = this.tabEQ.getIdxMin(); k < this.tabEQ.getnBrs(); k++) {
                    float temp = this.tabNormal.get(i, j);

                    float multiplier = (i == 0) ? 1 : this.tabEQ.get(k, i - 1);
                    float column = (j == 0) ? 1 : this.tabEQ.get(k, j - 1);

                    temp += (multiplier * column);
                    this.tabNormal.set(i, j, temp);
                }
            }
        }
    }
}
