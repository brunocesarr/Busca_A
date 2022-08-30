package src.Entities;

/**
 * @author bruno
 */
public class Position {
  int X;
  int Y;

  public Position() {}

  public Position(int X, int Y) {
    this.X = X;
    this.Y = Y;
  }

  public int getX() {
    return X;
  }

  public void setX(int X) {
    this.X = this.getNumberValid(X);
  }

  public int getY() {
    return Y;
  }

  public void setY(int Y) {
    this.Y = this.getNumberValid(Y);
  }

  @Override
  public String toString() {
    String s = "(" + X + ", " + Y + ")";
    return s;
  }

  private int getNumberValid(int number) {
    return number >= 0 ? number : 0; 
  }
}
