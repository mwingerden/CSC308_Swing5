import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame implements ActionListener {
    DrawArea drawArea = new DrawArea();

    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(500,500);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    public Main() {
        super("My Paint App");

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.CYAN);
        JButton b1 = new JButton("Undo");
        JButton b2 = new JButton("Erase");
        southPanel.add(b1);
        southPanel.add(b2);

        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.PINK);
        String [] colors = {"Black", "Red", "Blue", "Green", "Yellow"};
        JComboBox list = new JComboBox(colors);
        JRadioButton rectangle = new JRadioButton("Rectangle");
        JRadioButton circle = new JRadioButton("Circle");
        JRadioButton arc = new JRadioButton("Arc");
        rectangle.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rectangle);
        group.add(circle);
        group.add(arc);
        GridLayout grid = new GridLayout(7, 1);
        westPanel.setLayout(grid);
        westPanel.add(list);
        westPanel.add(rectangle);
        westPanel.add(circle);
        westPanel.add(arc);


        JPanel centerPanel = drawArea;
        centerPanel.setBackground(Color.GRAY);

        list.addActionListener(this);
        rectangle.addActionListener(this);
        circle.addActionListener(this);
        arc.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("comboBoxChanged")) {
            JComboBox tmp = (JComboBox) e.getSource();
            drawArea.setColor(Objects.requireNonNull(tmp.getSelectedItem()).toString().toUpperCase());
        }
        else if(e.getActionCommand().equalsIgnoreCase("rectangle")
        || e.getActionCommand().equalsIgnoreCase("circle")
        || e.getActionCommand().equalsIgnoreCase("arc")) {
            drawArea.setShape(e.getActionCommand().toLowerCase());
        }
        else if(e.getActionCommand().equalsIgnoreCase("undo")) {
            drawArea.undo();
        }
        else if(e.getActionCommand().equalsIgnoreCase("erase")) {
            drawArea.erase();
        }
    }
}