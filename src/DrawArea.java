import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
    int x1, y1,x2, y2;
    ArrayList<Shape> shapes = new ArrayList<>();
    String shape;
    String color;

    public DrawArea() {
        addMouseListener(this);
        addMouseMotionListener(this);
        shape = "rectangle";
        color = "BLACK";
    }

    public void paintComponent(Graphics g) {
        //TODO: Figure out how to draw with negative second release of click
        for(Shape current : shapes) {
            if(current.getType() == Shape.Type.RECTANGLE) {
                g.fillRect(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            else if(current.getType() == Shape.Type.CIRCLE) {
                g.fillOval(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1());
            }
            //TODO: Fix arc
            //TODO: Figure out drawing bug
            else if(current.getType() == Shape.Type.ARC) {
                g.fillArc(current.getX1(), current.getY1(),
                        current.getX2()-current.getX1(),
                        current.getY2()-current.getY1(), 0, 50);
            }

            switch (current.getColor()) {
                case "BLACK" -> g.setColor(Color.BLACK);
                case "RED" -> g.setColor(Color.RED);
                case "BLUE" -> g.setColor(Color.BLUE);
                case "GREEN" -> g.setColor(Color.GREEN);
                case "YELLOW" -> g.setColor(Color.YELLOW);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
//        System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);
        switch (shape) {
            case "rectangle" -> shapes.add(new Shape(x1, y1, x2, y2, color, Shape.Type.RECTANGLE));
            case "circle" -> shapes.add(new Shape(x1, y1, x2, y2, color, Shape.Type.CIRCLE));
            case "arc" -> shapes.add(new Shape(x1, y1, x2, y2, color, Shape.Type.ARC));
        }
        repaint();
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void undo() {
        shapes.remove(shapes.size() - 1);
        repaint();
    }

    public void erase() {
        shapes.clear();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
