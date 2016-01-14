package org.baddev.learning.demos.practical_tasks.squarematrix;

import java.util.Locale;

public abstract class AbstractSquareMatrix {

    public static class InvalidSqrMatrixSizeException extends Exception {
        public enum MatrixStructure {
            ROW, COLUMN, ELEMENT;

            @Override
            public String toString() {
                return this.name().toLowerCase();
            }
        }

        private static final String defaultMsg = "Size of array doesn't match matrix size";
        private int expected;
        private int found;

        public InvalidSqrMatrixSizeException() {
            super(defaultMsg);
        }

        /**
         * @param rowOrCol whether row or column, size of which doesn't match with {@code expected}
         * @param expected expected size
         * @param found    actual size
         */
        public InvalidSqrMatrixSizeException(MatrixStructure rowOrCol, final int expected, final int found) {
            super(defaultMsg + ": expected number of " + rowOrCol + "s " + expected + ", found " + found);
            this.expected = expected;
            this.found = found;
        }

        /**
         * @return expected matrix(array) size
         */
        public int getExpectedValue() {
            return expected;
        }

        /**
         * @return actual matrix(array) size
         */
        public int getFoundValue() {
            return found;
        }
    }

    public static class NoInverseMatrixFoundException extends Exception {
        private static final String defaultMsg = "Can't find inverse matrix";
        private double determinant;

        public NoInverseMatrixFoundException() {
            super(defaultMsg);
        }

        public NoInverseMatrixFoundException(final double determinant) {
            super(defaultMsg + ": determinant " + determinant);
            this.determinant = determinant;
        }

        public double getDeterminant() {
            return determinant;
        }
    }

    public double determinant() {
        if (size() == 1) {
            return get(0, 0);
        } else if (size() == 0) {
            return 0;
        }
        int sign = 1;
        double result = 0;
        for (int i = 0; i < size(); i++) {
            result += sign * get(0, i) * findMinor(0, i).determinant();
            sign = -sign;
        }
        return result;
    }

    private AbstractSquareMatrix findMinor(int exclRow, int exclCol) {
        AbstractSquareMatrix minor = createInstance(size() - 1);
        int r = 0, c = 0;
        for (int i = 0; i < size(); i++) {
            if (i != exclRow) {
                for (int j = 0; j < size(); j++) {
                    if (j != exclCol) {
                        minor.set(r, c++, get(i, j));
                    }
                }
                c = 0;
                r++;
            }
        }
        return minor;
    }

    public AbstractSquareMatrix inverse() throws NoInverseMatrixFoundException {
        AbstractSquareMatrix result = createInstance(size());
        double det = determinant();
        if (det == 0) {
            throw new NoInverseMatrixFoundException(det);
        }
        double revValue = 0;
        double sign = 1;
        for (int i = 0; i < size(); i++) {
            sign = (i % 2 == 0) ? sign : -sign;
            for (int j = 0; j < size(); j++) {
                revValue = (sign * findMinor(i, j).determinant()) / det;
                result.set(j, i, revValue);
                sign = -sign;
            }
            sign = 1;
        }
        return result;
    }

    public AbstractSquareMatrix multiply(AbstractSquareMatrix matrix) throws InvalidSqrMatrixSizeException {
        checkSize(matrix.getElements());
        AbstractSquareMatrix result = createInstance(size());
        double element = 0;
        for (int r = 0; r < size(); r++) {
            for (int c = 0; c < size(); c++) {
                for (int k = 0; k < size(); k++) {
                    element += get(r, k) * matrix.get(k, c);
                }
                result.set(r, c, element);
                element = 0;
            }
        }
        return result;
    }

    /**
     * @param size matrix size
     * @return new instance of actual type
     */
    protected abstract AbstractSquareMatrix createInstance(int size);

    public abstract int size();

    public abstract void set(int row, int col, double value);

    public abstract void setElements(double[][] matrix) throws InvalidSqrMatrixSizeException;

    public abstract double get(int row, int col);

    public abstract double[][] getElements();

    protected void checkSize(double[][] elements) throws InvalidSqrMatrixSizeException {
        if (elements.length != size()) {
            throw new InvalidSqrMatrixSizeException(InvalidSqrMatrixSizeException.MatrixStructure.ROW, size(),
                    elements.length);
        }
        int colsCount = 0;
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                ++colsCount;
            }
            if (colsCount != size()) {
                throw new InvalidSqrMatrixSizeException(InvalidSqrMatrixSizeException.MatrixStructure.COLUMN, size(),
                        colsCount);
            }
            colsCount = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append("[ ");
            for (int j = 0; j < size(); j++) {
                sb.append(String.format(Locale.US, "%.2f ", get(i, j)));
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}