package principal;

/**
 *
 * @author bruno
 * @param <T>
 */
public class Matrix<T> {

    private int linhas;
    private int colunas;
    private T M[][];

    public Matrix() {
    }

    public Matrix(int n, int m) {
        this.linhas = n;
        this.colunas = m;
        this.M = (T[][]) new Object[n][m];
    }

    public T[][] getM() {
        return M;
    }

    public void setM(T[][] M) {
        this.M = M;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public void adiciona(int n, int m, T valor) {
        this.M[n][m] = valor;
    }

    public T retornaValor(int n, int m) {
        return this.M[n][m];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                s.append(M[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
