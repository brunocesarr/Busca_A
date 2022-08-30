package src;

import src.Entities.State;
import src.Entities.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import src.Entities.ASearchInput;
import src.Entities.Matrix;
import src.Interfaces.IASearch;

/*
 * @author bruno
 */
public class ASearch implements IASearch {
    private final ASearchInput aSearchInput;
    private PriorityQueue<State> statesEdge;
    private List<State> bestPath;

    public ASearch(ASearchInput aSearchInput) {
        this.aSearchInput = aSearchInput;
        this.statesEdge = new PriorityQueue<>();
        this.bestPath = new LinkedList<>();
        this.startStarSearch();        
    }

    public ASearch(Position initialPosition, Position endPosition, Matrix matrix) {
        this.aSearchInput = new ASearchInput(matrix, initialPosition, endPosition);
        this.statesEdge = new PriorityQueue<>();
        this.bestPath = new LinkedList<>();
        this.startStarSearch();        
    }

    private void setBestPath(List<State> caminho) {
        this.bestPath = caminho;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.aSearchInput.getInitialPosition())
            .append("\n")
            .append(this.aSearchInput.getEndPosition())
            .append("\n")
            .append(this.aSearchInput.getMatrix())
            .append("\n");
        
        return s.toString();
    }
    
    @Override
    public ASearchInput getASearchInput(){
        return this.aSearchInput;
    }

    @Override
    public List<State> getBestPath() {
        return this.bestPath;
    }

    @Override
    public String getPositionsInBestPath() {
        StringBuilder s = new StringBuilder();
        State state;

        for (int j = 0; j <= this.bestPath.size() - 1; j++) {
            state = this.bestPath.get(j);
            s.append(" -> ").append(state.getAgentCurrentPosition());
        }

        return s.toString();
    }
    
    private void addStateEdge(State stateEdge) {
        statesEdge.add(stateEdge);
    }

    private int ManhattanDistance(State stateNode) {
        int totalManhattanDistance = this.getAbsoluteValue(this.aSearchInput.getEndPosition().getX(), stateNode.getAgentCurrentPosition().getX()) + this.getAbsoluteValue(this.aSearchInput.getEndPosition().getY(), stateNode.getAgentCurrentPosition().getY());
        stateNode.setHeuristic(totalManhattanDistance);
        return totalManhattanDistance;
    }
    
    private int getAbsoluteValue(int valueA, int valueB) {
        return Math.abs(valueA - valueB);
    }

    private boolean isObjective(State stateNode) {
        return stateNode.getAgentCurrentPosition().getX() == this.aSearchInput.getEndPosition().getX() 
                && stateNode.getAgentCurrentPosition().getY() == this.aSearchInput.getEndPosition().getY();
    }

    private void startStarSearch() {
        this.statesEdge = this.defineInitialState();

        while (!this.statesEdge.isEmpty()) {
            State v = this.statesEdge.remove();
            this.bestPath.add(v);
            if (this.isObjective(v)) {
                findFinalPath();
                return;
            } else {
                findSuccessors(v);
            }
        }
    }
    
    private PriorityQueue<State> defineInitialState(){
        State startState = new State(0, 0, this.aSearchInput.getInitialPosition());
        startState.setHeuristic(this.ManhattanDistance(startState));
        startState.setAvaliacao();
        startState.setIndexFather(-1);
        this.addStateEdge(startState);
        return this.statesEdge;        
    }

    private void findSuccessors(State currentState) {
        int x = currentState.getAgentCurrentPosition().getX();
        int y = currentState.getAgentCurrentPosition().getY();
        
        if ((x - 1) >= 0)
            this.statesEdge = this.getSucessorsToVisit(currentState, x - 1, y);
        if ((x + 1) < this.aSearchInput.getMatrix().getNumberOfLines())
            this.statesEdge = this.getSucessorsToVisit(currentState, x + 1, y);
        if ((y - 1) >= 0)
            this.statesEdge = this.getSucessorsToVisit(currentState, x, y - 1);
        if ((y + 1) < this.aSearchInput.getMatrix().getNumberOfCollumns())
            this.statesEdge = this.getSucessorsToVisit(currentState, x, y + 1);
    }
    
    private PriorityQueue<State> getSucessorsToVisit(State currentState, int nextPositionX, int nextPositionY) {
        if (this.aSearchInput.isValidPosition(nextPositionX, nextPositionY)) {
            State newState = new State(new Position(nextPositionX, nextPositionY));
            this.calculateSearch(newState, currentState);
            this.statesEdge.add(newState);
        }
        
        return this.statesEdge;
    }

    private void calculateSearch(State novo, State node) {
        novo.setCusto(node.getCusto() + 1);
        novo.setHeuristic(this.ManhattanDistance(novo));
        novo.setAvaliacao();
        novo.setIndexFather(this.bestPath.indexOf(node));
    }

    private void findFinalPath() {
        List<State> bestStatesPath = new LinkedList<>();
        State stateNode = this.bestPath.get(this.bestPath.size() - 1);
        bestStatesPath.add(stateNode);

        while (stateNode.getIndexFather() != -1) {
            stateNode = this.bestPath.get(stateNode.getIndexFather());
            bestStatesPath.add(stateNode);
        }

        Collections.reverse(bestStatesPath);
        this.setBestPath(bestStatesPath);
    }
}
