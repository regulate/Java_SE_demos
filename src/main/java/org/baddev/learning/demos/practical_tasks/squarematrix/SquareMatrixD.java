package org.baddev.learning.demos.practical_tasks.squarematrix;

public class SquareMatrixD extends AbstractSquareMatrix {

    double[] matrix;

    public SquareMatrixD(int size) {
        this.matrix = new double[size * size];
    }

    @Override
    public int size() {
        return (int) Math.sqrt(matrix.length);
    }

    @Override
    public void set(int row, int col, double value) {
        matrix[determineIdx(row, col)] = value;
    }

    @Override
    public void setElements(double[][] matrix) throws InvalidSqrMatrixSizeException {
        checkSize(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                set(i, j, matrix[i][j]);
            }
        }
    }

    @Override
    public double get(int row, int col) {
        return matrix[determineIdx(row, col)];
    }

    @Override
    public double[][] getElements() {
        double[][] result = new double[size()][size()];
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                result[i][j] = get(i, j);
            }
        }
        return result;
    }

    private int determineIdx(int row, int col) {
        return size() * (row + 1) - (size() - col);
    }

    @Override
    protected AbstractSquareMatrix createInstance(int size) {
        return new SquareMatrixD(size);
    }
}
