package src.Entities;

/**
 * @author bruno
 */
public class State implements Comparable<State> {

    private int heuristic;
    private int travelCost;
    private double pathEvaluation;
    private Position agentCurrentPosition;
    private int indexFather;

    public State() {
    }

    public State(Position agentCurrentPosition) {
        this.agentCurrentPosition = agentCurrentPosition;
    }

    public State(int travelCost, int pathEvaluation, 
        Position agentCurrentPosition) {
        this.travelCost = travelCost;
        this.pathEvaluation = pathEvaluation;
        this.agentCurrentPosition = agentCurrentPosition;
    }

    public State(int heuristic, int travelCost, int pathEvaluation, 
        Position agentCurrentPosition) {
        this.heuristic = heuristic;
        this.travelCost = travelCost;
        this.pathEvaluation = pathEvaluation;
        this.agentCurrentPosition = agentCurrentPosition;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getCusto() {
        return travelCost;
    }

    public void setCusto(int custo) {
        this.travelCost = custo;
    }

    public double getPathEvaluation() {
        return pathEvaluation;
    }

    public void setAvaliacao() {
        this.pathEvaluation = (this.travelCost + this.heuristic);
    }

    public Position getAgentCurrentPosition() {
        return agentCurrentPosition;
    }

    public void setAgentCurrentPosition(Position agentCurrentPosition) {
        this.agentCurrentPosition = agentCurrentPosition;
    }

    public int getIndexFather() {
        return indexFather;
    }

    public void setIndexFather(int indexFather) {
        this.indexFather = indexFather;
    }

    @Override
    public String toString() {
        return "Estado{" + "heurist=" + heuristic + ", custo=" + travelCost + ", avaliacao=" + pathEvaluation + ", agente=" + agentCurrentPosition + '}';
    }

    @Override
    public int compareTo(State node) {
        if (this.pathEvaluation > node.getPathEvaluation()) {
            return 1;
        } else if (this.pathEvaluation < node.getPathEvaluation()) {
            return -1;
        } else {
            return 0;
        }
    }
}
