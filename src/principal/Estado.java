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
public class Estado implements Comparable<Estado> {

    private int heurist;
    private int custo;
    private double avaliacao;
    private Position agente;
    private int pai;

    public Estado() {
    }

    public Estado(Position agente) {
        this.agente = agente;
    }

    public Estado(int custo, int avaliacao, Position agente) {
        this.custo = custo;
        this.avaliacao = avaliacao;
        this.agente = agente;
    }

    public Estado(int heurist, int custo, int avaliacao, Position agente) {
        this.heurist = heurist;
        this.custo = custo;
        this.avaliacao = avaliacao;
        this.agente = agente;
    }

    public int getHeurist() {
        return heurist;
    }

    public void setHeurist(int heurist) {
        this.heurist = heurist;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao() {
        //  System.out.println("1 / " + this.custo + " + " + this.heurist);
        this.avaliacao = (this.custo + this.heurist);
    }

    public Position getAgente() {
        return agente;
    }

    public void setAgente(Position agente) {
        this.agente = agente;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        return "Estado{" + "heurist=" + heurist + ", custo=" + custo + ", avaliacao=" + avaliacao + ", agente=" + agente + '}';
    }

    @Override
    public int compareTo(Estado node) {
        if (this.avaliacao > node.getAvaliacao()) {
            return 1;
        } else if (this.avaliacao < node.getAvaliacao()) {
            return -1;
        } else {
            return 0;
        }
    }

}
