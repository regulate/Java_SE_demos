package org.baddev.learning.demos.practical_tasks.squarematrix;

public class MatrixDemo {

    private static void check(double[][] arr) {
        AbstractSquareMatrix mtxD = new SquareMatrixD(arr.length);
        AbstractSquareMatrix mtx2D = new SquareMatrix2D(arr.length);

        try {
            mtxD.setElements(arr);
            mtx2D.setElements(arr);
            System.out.println("***" + arr.length + "x" + arr.length + "***" + " check");
            System.out.printf("One dimensional impl:%n%s", mtxD);
            System.out.printf("Two dimensional impl:%n%s", mtx2D);

            System.out.printf("One dimensional determinant:%n%s%n", mtxD.determinant());
            System.out.printf("Two dimensional determinant:%n%s%n", mtx2D.determinant());

            System.out.printf("Multiplication:%n%s", mtxD.multiply(mtx2D));

            System.out.printf("One dimensional inversion:%n%s", mtxD.inverse());
            System.out.printf("One dimensional inversion check:%n%s", mtxD.multiply(mtxD.inverse()));

            System.out.printf("Two dimensional inversion:%n%s", mtx2D.inverse());
            System.out.printf("Two dimensional inversion check:%n%s%n", mtx2D.multiply(mtx2D.inverse()));
        } catch (AbstractSquareMatrix.InvalidSqrMatrixSizeException e) {
            e.printStackTrace();
        } catch (AbstractSquareMatrix.NoInverseMatrixFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        double[][] arr3 = {{2, -3, 6}, {54, 23, 1}, {11, -9, 4}};
        double[][] arr4 = {{1, 5, -4, 76}, {22, 4, 51, 3}, {-11, 8, 9, 0}, {12, -5, 43, 23}};
        check(arr3);
        check(arr4);
    }
}
