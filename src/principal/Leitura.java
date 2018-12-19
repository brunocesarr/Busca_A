/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bruno
 */
public class Leitura {

    private Matriz matriz;
    private Posicao inicio;
    private Posicao fim;

    public Leitura() {
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public Posicao getInicio() {
        return inicio;
    }

    public Posicao getFim() {
        return fim;
    }

    public boolean fileChooser() {

        //selecting the txt file gui
        JFileChooser jFileChooser = new JFileChooser();

        //only accept text files
        FileNameExtensionFilter textFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        jFileChooser.setFileFilter(textFilter);

        int result = jFileChooser.showOpenDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();

        //if file select print text function
        if (result == JFileChooser.APPROVE_OPTION) {
            this.ler(selectedFile);
            return true;
        } else {
            System.out.println("Error selecting file");
            return false;
        }
    }

    private void ler(File selectFile) {
        File arquivo = selectFile;
        //  File arquivo = new File("Matriz.txt");

        try {
            if (!arquivo.exists()) {
                System.out.println("Arquivo Inexistente");
            } else {
                //  Faz Leitura do arquivo
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);

                //  Lendo o tamanho da matriz
                String[] linha = br.readLine().split(" ");
                int n = Integer.parseInt(linha[0]);
                int m = Integer.parseInt(linha[1]);

                //  Lendo a posição inicial
                linha = br.readLine().split(" ");
                this.inicio = new Posicao(Integer.parseInt(linha[0]), Integer.parseInt(linha[1]));

                //  Lendo a posição final
                linha = br.readLine().split(" ");
                this.fim = new Posicao(Integer.parseInt(linha[0]), Integer.parseInt(linha[1]));

                //  Cria a matriz
                this.matriz = new Matriz(n, m);

                //  Enquanto houver mais linhas
                int i = 0;
                while (br.ready()) {
                    linha = br.readLine().split(" ");
                    int j = 0;
                    while (j < linha.length) {
                        this.matriz.adiciona(i, j, Integer.parseInt(linha[j]));
                        j++;
                    }
                    i++;
                }

                //  Fecha recursos usados
                br.close();
                fr.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Leitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
