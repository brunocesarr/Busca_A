package screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import src.Entities.Matrix;
import src.Entities.Position;
import src.Interfaces.IASearch;

/**
 * @author bruno
 */
public class View extends JFrame {
    private IASearch aSearch;
    private Position currentPositionAgent;

    public View() {
        setTitle("Labirinto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public View(IASearch aSearch) {
        this();
        this.aSearch = aSearch;
        this.currentPositionAgent = aSearch.getASearchInput().getInitialPosition();
        percorrer(aSearch);
    }

    public void setCurrentPositionAgent(Position currentPositionAgent) {
        this.currentPositionAgent = currentPositionAgent;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.translate(50, 50);

        Matrix maze = aSearch.getASearchInput().getMatrix();
        for (int row = 0; row < maze.getNumberOfLines(); row++) {
            for (int collumn = 0; collumn < maze.getNumberOfCollumns(); collumn++) {
                Color color;
                switch ((int) maze.getValueInPosition(row, collumn)) {
                    case 1:
                        color = Color.WHITE;
                        break;
                    default:
                        color = Color.BLACK;
                }
                graphics.setColor(color);
                graphics.fillRect(30 * collumn, 30 * row, 30, 30);
                graphics.setColor(Color.BLACK);
                graphics.drawRect(30 * collumn, 30 * row, 30, 30);
            }
        }

        graphics.setColor(Color.RED);
        graphics.fillOval(30 * this.currentPositionAgent.getY(), 30 * this.currentPositionAgent.getX(), 30, 30);
    }

    private void percorrer(IASearch aSearch) {
        try {
            for (int index = 0; index < aSearch.getBestPath().size(); index++) {
                Position currentPosition = aSearch.getBestPath().get(index).getAgentCurrentPosition();
                this.setCurrentPositionAgent(currentPosition);
                revalidate();
                repaint();
                setVisible(true);
                Thread.sleep(500L);
            }

            JOptionPane.showMessageDialog(rootPane, "Objetivo concluido");
            this.setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start(IASearch aSearch) {
        SwingUtilities.invokeLater(() -> {
            View view = new View(aSearch);
            view.setVisible(true);
            percorrer(aSearch);
        });
    }

}
