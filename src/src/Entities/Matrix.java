package src.Entities;

/**
 * @author bruno
 * @param <T>
 */
public class Matrix<T> {
    private int numberOfLines;
    private int numberOfCollumns;
    private T M[][];

    public Matrix() {
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public int getNumberOfCollumns() {
        return numberOfCollumns;
    }

    public Matrix(int numberOfLines, int numberOfCollumns) {
        this.numberOfLines = numberOfLines;
        this.numberOfCollumns = numberOfCollumns;
        this.M = (T[][]) new Object[numberOfCollumns][numberOfLines];
    }

    public void addValue(int numberOfLine, int numberOfCollumn, T value) {
        this.M[numberOfCollumn][numberOfLine] = value;
    }

    public T getValueInPosition(int numberOfLine, int numberOfCollumn) {
        return this.M[numberOfCollumn][numberOfLine];
    }

    @Override
    public String toString() {
        StringBuilder tringBuilder = new StringBuilder();
        for (int indexX = 0; indexX < this.numberOfLines; indexX++) {
            for (int indexY = 0; indexY < this.numberOfCollumns; indexY++) {
                tringBuilder.append(M[indexY][indexX]).append(" ");
            }
            tringBuilder.append("\n");
        }
        return tringBuilder.toString();
    }
}
