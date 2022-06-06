package src;

import src.Entities.Matrix;
import src.Entities.Position;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import src.Entities.ASearchInput;
import src.Interfaces.IReader;

/**
 * @author bruno
 */
public class Reader implements IReader{
  private boolean isSelectedFile;
  private File selectedFile;

  public Reader() {
  }

  public boolean isSelectedFile() {
    return isSelectedFile;
  }

  @Override
  public void selectFile() {
    try {
      JFileChooser jFileChooser = new JFileChooser();
      FileNameExtensionFilter fileNameExtensionsFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
      jFileChooser.setFileFilter(fileNameExtensionsFilter);
  
      int typeResultOptionSelect = jFileChooser.showOpenDialog(null);
      this.selectedFile = jFileChooser.getSelectedFile();
  
      this.isSelectedFile = (typeResultOptionSelect == JFileChooser.APPROVE_OPTION && this.selectedFile.exists());      
    } catch (HeadlessException ex) {
      Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
      throw ex;
    }
  }

  @Override
  public ASearchInput generateInputASearch() throws IOException {
    try {
      FileReader fileReader = new FileReader(this.selectedFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      Matrix matrix = this.createNewMatrix(bufferedReader);
      Position initialPosition = this.getPosition(bufferedReader);
      Position endPosition = this.getPosition(bufferedReader);

      int indexY = 0;
      while (bufferedReader.ready()) {
        matrix = this.generateLineMatrix(bufferedReader, indexY, matrix);
        indexY++;
      }

      bufferedReader.close();
      fileReader.close();
      
      return new ASearchInput(matrix, initialPosition, endPosition);
    } catch (IOException ex) {
      Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
      throw ex;
    }
  }

  private Position getPosition(BufferedReader bufferedReader) throws IOException {
    String[] textsInLine = bufferedReader.readLine().split(" ");
    return new Position(Integer.parseInt(textsInLine[0]), Integer.parseInt(textsInLine[1]));
  }

  private Matrix createNewMatrix(BufferedReader bufferedReader) throws IOException {
    String[] textsInLine = bufferedReader.readLine().split(" ");
    int width = Integer.parseInt(textsInLine[0]);
    int height = Integer.parseInt(textsInLine[1]);
    return new Matrix(width, height);
  }

  private Matrix generateLineMatrix(BufferedReader bufferedReader, int indexY, Matrix matrix) throws IOException {
    String[] textsInLine = bufferedReader.readLine().split(" ");
    
    for(int indexX = 0; indexX < textsInLine.length; indexX++) {
      matrix.addValue(indexY, indexX, Integer.parseInt(textsInLine[indexX]));    
    }
    
    return matrix;
  }
}
