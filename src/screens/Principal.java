package screens;

import src.ASearch;

/**
 * @author Bruno
 */
public class Principal {

    public static void main(ASearch aSearch) {
        View tela = new View(aSearch);
        tela.start(aSearch);
    }
}
