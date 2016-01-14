package org.baddev.learning.demos.practical_tasks.squarematrix;

public class SquareMatrix2D extends AbstractSquareMatrix {

    private double[][] elements;

    public SquareMatrix2D(int size) {
        this.elements = new double[size][size];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public void set(int row, int col, double value) {
        elements[row][col] = value;
    }

    @Override
    public void setElements(double[][] elements) throws InvalidSqrMatrixSizeException {
        checkSize(elements);
        this.elements = elements;
    }

    @Override
    public double get(int row, int col) {
        return elements[row][col];
    }

    @Override
    public double[][] getElements() {
        return this.elements;
    }

    @Override
    protected AbstractSquareMatrix createInstance(int size) {
        return new SquareMatrix2D(size);
    }

}
