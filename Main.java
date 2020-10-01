public class Main {
    public static void main(String[] args) {
        // Matriks M = new Matriks(new float[][] { {3, 5, 6},
        //                                         {7 ,4, 8},
        //                                         {6, 5, 1}});

        MAugmented augmented = new MAugmented(new Matriks(new float[][] {   {3, 5, 3, 4, 9},
                                                                            {4, 3, 5, 6, 12},
                                                                            {2, 4, 4, 4, 7},
                                                                            {0, 0, 0, 0, 0} }));

        MAugmented augmentedgj = augmented.gaussjor();
        System.out.println(augmentedgj);
        System.out.println(augmentedgj.kalimatSolusi());

        Regresi a = new Regresi();
        // MAugmented augmented1 = new MAugmented(new float[][] {  {1, -1, 2, -1, -1},
        //                                                         {2, 1, -2, -2, -2},
        //                                                         {-1, 2, -4, 1, 1},
        //                                                         {3, 0, 0, -3, -3}});

        // System.out.println(augmented1.gaussjor());
    }
}
