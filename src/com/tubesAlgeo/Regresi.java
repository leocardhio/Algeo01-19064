package com.tubesAlgeo;

import java.util.Arrays;
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
        System.out.println("tabel B:");
        this.tabNormal.gaussjor();
        this.tabNormal.tulisMatriks();
        System.out.println("konstanta B:");
        System.out.println(Arrays.toString(getArrayB()));
        System.out.println();
        System.out.println("taksiran:");
        taksirNilai();
        this.tabTaksir.tulisMatriks();
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
        this.tabTaksir = new Matriks(nTaksir, k + 1);

        for (int i = this.tabTaksir.getIdxMin(); i < this.tabTaksir.getnBrs(); i++) {
            for (int j = this.tabTaksir.getIdxMin(); j < this.tabTaksir.getnKol(); j++) {
                if (j == this.tabTaksir.getnKol() - 1) {
                    this.tabTaksir.set(i, j, 0);
                }
                else {
                    System.out.printf("x[%d][%d]: ", i + 1, j + 1);
                    this.tabTaksir.set(i, j, scanner.nextFloat());
                }
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

    private float[] getArrayB() {
        float[] arrayB = new float[tabNormal.getnBrs()];

        for (int i = tabNormal.getIdxMin(); i < tabNormal.getnBrs(); i++) {
            arrayB[i] = tabNormal.get(i, tabNormal.getnKol() - 1);
        }

        return arrayB;
    }

    private void taksirNilai() {
        float[] arrayB = getArrayB();
        int jVal = tabTaksir.getnKol() - 1;

        for (int i = tabTaksir.getIdxMin(); i < tabTaksir.getnBrs(); i++) {
            float taksiran = arrayB[0];
            for (int j = tabTaksir.getIdxMin(); j < jVal; j++) {
                taksiran += (arrayB[j + 1] * tabTaksir.get(i, j));
            }

            tabTaksir.set(i, jVal, taksiran);
        }
    }
}
