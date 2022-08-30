package src.Interfaces;

import java.io.IOException;
import src.Entities.ASearchInput;

/**
 * @author Bruno
 */
public interface IReader {
    void selectFile();
    ASearchInput generateInputASearch() throws IOException;
    boolean isSelectedFile();
}
