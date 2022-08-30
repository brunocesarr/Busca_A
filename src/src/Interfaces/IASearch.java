package src.Interfaces;

import java.util.List;
import src.Entities.ASearchInput;
import src.Entities.State;

/**
 * @author Bruno
 */
public interface IASearch {
    ASearchInput getASearchInput();
    List<State> getBestPath();
    String getPositionsInBestPath();
}
