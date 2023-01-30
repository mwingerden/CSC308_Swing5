import javax.swing.*;
import java.awt.*;

public class DrawArea extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 10, 100, 100);
    }
}
