package src.Entities;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bruno
 */
public class ASearchInput {
  private final List<Integer> valuesPositionValid;
  private final Matrix<Integer> matrix;
  private final Position initialPosition;
  private final Position endPosition;

  public ASearchInput(Matrix matrix, Position initialPosition, Position endPosition) {
    this.matrix = matrix;
    this.initialPosition = initialPosition;
    this.endPosition = endPosition;
    this.valuesPositionValid = Arrays.asList(1);
  }

  public Matrix getMatrix() {
    return matrix;
  }

  public Position getInitialPosition() {
    return initialPosition;
  }

  public Position getEndPosition() {
    return endPosition;
  }
  
  public boolean isValidPosition(int positionX, int positionY) {
    try {
        return this.valuesPositionValid.contains(this.matrix.getValueInPosition(positionX, positionY));        
    } catch (Exception ex) {
        return false;
    }
  }
}
