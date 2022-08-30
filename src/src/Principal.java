package src;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Entities.ASearchInput;
import src.Interfaces.IASearch;
import src.Interfaces.IReader;

/**
 * @author bruno
 */
public class Principal {
    public static void main(String[] args) {
      try {
        IReader reader = new Reader();
        reader.selectFile();
  
        if (!reader.isSelectedFile())
          System.out.println("Erro ao encontrar o arquivo");  
        
        ASearchInput inputASearch = reader.generateInputASearch();
        
        System.out.println("Matriz:");
        System.out.println(inputASearch.getMatrix());
        System.out.println("Ponto Inicial: " + inputASearch.getInitialPosition());
        System.out.println("Ponto Final: " + inputASearch.getEndPosition());
        System.out.println("");
        
        IASearch busca = new ASearch(inputASearch);
        System.out.println(busca.getPositionsInBestPath());        
      } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
