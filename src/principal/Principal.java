/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author bruno
 */
public class Principal {

    public static void main(String[] args) {
        Leitura ler = new Leitura();
        ler.fileChooser();
        
        System.out.println("Matriz:");
        System.out.println(ler.getMatriz());
        System.out.println("Ponto Inicial: " + ler.getInicio());
        System.out.println("Ponto Final: " + ler.getFim());
        System.out.println("");
        
        Busca busca = new Busca(ler.getInicio(), ler.getFim(), ler.getMatriz());
        System.out.println(busca.retornaCaminho());
    }
}
