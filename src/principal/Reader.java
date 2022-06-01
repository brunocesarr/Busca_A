package principal;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author bruno
 */
public class Reader {
  private Matrix matrix;
  private Position initialPosition;
  private Position endPosition;
  private boolean isSelectedFile;
  private File selectedFile;

  public Reader() {
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

  public boolean isSelectedFile() {
    return isSelectedFile;
  }

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

  public Matrix generateMatrix() throws IOException {
    try {
      FileReader fileReader = new FileReader(this.selectedFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      this.matrix = this.createNewMatrix(bufferedReader);
      this.initialPosition = this.getPosition(bufferedReader);
      this.endPosition = this.getPosition(bufferedReader);

      int indexY = 0;
      while (bufferedReader.ready()) {
        this.matrix = this.generateLineMatrix(bufferedReader, indexY);
        indexY++;
      }

      bufferedReader.close();
      fileReader.close();
      
      return this.matrix;
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

  private Matrix generateLineMatrix(BufferedReader bufferedReader, int indexY) throws IOException {
    String[] textsInLine = bufferedReader.readLine().split(" ");
    
    for(int indexX = 0; indexX < textsInLine.length; indexX++) {
      this.matrix.adiciona(indexY, indexX, Integer.parseInt(textsInLine[indexX]));    
    }
    
    return this.matrix;
  }
}
