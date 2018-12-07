/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @author bruno
 */
public class Busca {

    private Posicao inicio;
    private Posicao fim;
    private Matriz matriz;
    private PriorityQueue<Estado> borda;
    private List<Estado> caminho;

    public Busca(Posicao inicio, Posicao fim, Matriz matriz) {
        this.inicio = inicio;
        this.fim = fim;
        this.matriz = matriz;
        this.borda = new PriorityQueue<>();
        this.caminho = new ArrayList<>();
        this.inicializa();
    }

    public Posicao getInicio() {
        return inicio;
    }

    public void setInicio(Posicao inicio) {
        this.inicio = inicio;
    }

    public Posicao getFim() {
        return fim;
    }

    public void setFim(Posicao fim) {
        this.fim = fim;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public PriorityQueue<Estado> getBorda() {
        return borda;
    }

    public void setBorda(PriorityQueue<Estado> borda) {
        this.borda = borda;
    }

    public List<Estado> getCaminho() {
        return caminho;
    }

    public void setCaminho(List<Estado> caminho) {
        this.caminho = caminho;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.inicio).append("\n").append(this.fim).append("\n").append(this.matriz).append("\n");
        return s.toString();
    }

    //  Adiciona na lista de prioridades os Estados
    public void addBorda(Estado node) {
        borda.add(node);
    }

    //  Inicializa com estado inicial
    private void inicializa() {
        Estado node = new Estado(0, 0, inicio);
        node.setHeurist(this.Manhattan(node));
        node.setAvaliacao();
        node.setPai(-1);
        this.searchStar(node);
    }

    //  Retorna em int a distância de Manhattan
    public int Manhattan(Estado node) {
        int result = Math.abs(fim.getX() - node.getAgente().getX()) + Math.abs(fim.getY() - node.getAgente().getY());
        node.setHeurist(result);
        return result;
    }

    //  Retorna se o Estado é o objetivo
    public boolean objetivo(Estado node) {
        return (node.getAgente().getX() == this.fim.getX() && node.getAgente().getY() == this.fim.getY());
    }

    //  Realiza o busca estrela
    public void searchStar(Estado node) {
        this.addBorda(node);
        while (!this.borda.isEmpty()) {
            Estado v = this.borda.remove();
            this.caminho.add(v);
            if (this.objetivo(v)) {
                achaCaminho();
                return;
            } else {
                //  gerar sucessores
                //  inserir sucessores
                sucessores(v);
            }
        }
    }

    //  Busca sucessores
    public void sucessores(Estado node) {
        Estado novo;
        int x = node.getAgente().getX();
        int y = node.getAgente().getY();
        if ((x - 1) >= 0) {
            if ((int) this.matriz.retornaValor(x - 1, y) == 1) {
                novo = new Estado(new Posicao(x - 1, y));
                this.calcula(novo, node);
                this.borda.add(novo);
            }
        }
        if ((x + 1) < this.matriz.getLinhas()) {
            if ((int) this.matriz.retornaValor(x + 1, y) == 1) {
                novo = new Estado(new Posicao(x + 1, y));
                this.calcula(novo, node);
                this.borda.add(novo);
            }
        }
        if ((y - 1) >= 0) {
            if ((int) this.matriz.retornaValor(x, y - 1) == 1) {
                novo = new Estado(new Posicao(x, y - 1));
                this.calcula(novo, node);
                this.borda.add(novo);
            }
        }
        if ((y + 1) < this.matriz.getColunas()) {
            if ((int) this.matriz.retornaValor(x, y + 1) == 1) {
                novo = new Estado(new Posicao(x, y + 1));
                this.calcula(novo, node);
                this.borda.add(novo);
            }
        }
    }

    //  Realiza todos os calculos necessário e relaciona o indice do Estado pai
    public void calcula(Estado novo, Estado node) {
        novo.setCusto(node.getCusto() + 1);
        novo.setHeurist(this.Manhattan(novo));
        novo.setAvaliacao();
        novo.setPai(this.caminho.indexOf(node));
    }

    //  Acha o caminho a partir do indice do pai
    public void achaCaminho() {
        List<Estado> caminhoCerto = new ArrayList<>();
        Estado node = this.caminho.get(this.caminho.size() - 1);
        caminhoCerto.add(node);

        while (node.getPai() != -1) {
            node = this.caminho.get(node.getPai());
            caminhoCerto.add(node);
        }

        Collections.reverse(caminhoCerto);

        this.setCaminho(caminhoCerto);
    }

    //  Retorna o caminho encontrado
    public String retornaCaminho() {
        StringBuilder s = new StringBuilder();
        Estado node;

        for (int j = 0; j <= this.caminho.size() - 1; j++) {
            node = this.caminho.get(j);
            s.append(" -> ").append(node.getAgente());
        }

        //  System.out.println(s.toString());
        return s.toString();
    }
}
