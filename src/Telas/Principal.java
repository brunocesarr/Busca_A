/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import principal.Busca;
import principal.Leitura;

/**
 *
 * @author INSS-BENEF
 */
public class Principal {

    public static void main(Busca busca) {
        /*
        Inicial tela1 = new Inicial();
        tela1.setVisible(true);
        
        Leitura ler = new Leitura();
        ler.fileChooser();
        Busca busca = new Busca(ler.getInicio(), ler.getFim(), ler.getMatriz());
        */
        
        View tela = new View(busca);
        tela.iniciar(busca);
    }
}
