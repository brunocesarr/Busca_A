package principal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bruno
 */
public class Principal {
    public static void main(String[] args) {
      try {
        Reader reader = new Reader();
        reader.selectFile();
  
        if (!reader.isSelectedFile())
          System.out.println("Erro ao encontrar o arquivo");   
        
        System.out.println("Matriz:");
        System.out.println(reader.generateMatrix());
        System.out.println("Ponto Inicial: " + reader.getInitialPosition());
        System.out.println("Ponto Final: " + reader.getEndPosition());
        System.out.println("");
        
        Busca busca = new Busca(reader.getInitialPosition(), reader.getEndPosition(), reader.getMatrix());
        System.out.println(busca.retornaCaminho());        
      } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
