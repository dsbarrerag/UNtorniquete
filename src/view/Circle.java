package view;

import javax.swing.*;
import java.awt.*;

public class Circle extends JLabel {
    String nombre;
    Color color;
    int torniquete;

    public Circle(Color c, String nm) {
        this.color = c;
        this.nombre = nm;
    }

    public void setTorniquete(int t) {
        this.torniquete = t;
    }

    public void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillOval(0, 0, 20, 20);
        super.setAlignmentX(0.5F);
        super.setAlignmentY(0.5F);
        super.setFont(new Font("Trebuchet MS", 0, 12));
        super.setText(this.nombre);
        super.paintComponent(g);
    }
}
