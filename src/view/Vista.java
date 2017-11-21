package view;

import model.Persona;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vista extends JFrame {
    private Map<Integer, Circle> eliminando = new HashMap();
    private ArrayList<Circle> circulos = new ArrayList();
    private JPanel contentPane;
    BufferedImage torniquete = null;
    BufferedImage buho = null;
    JLabel[] torniquetes;
    private JTable table;

    public Vista() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 1000, 500);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        JPanel options = new ControlPanel();
        options.setBounds(700, 50, 260, 320);
        this.contentPane.add(options);
        JLabel lblNewLabel = new JLabel("La cola de personas es: ");
        lblNewLabel.setFont(new Font("Trebuchet MS", 0, 14));
        lblNewLabel.setBounds(10, 415, 313, 14);
        this.contentPane.add(lblNewLabel);

        try {
            this.torniquete = ImageIO.read(new File("./images/tor.png"));
            this.buho = ImageIO.read(new File("./images/buho.png"));
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public void crearTorniquetes(int n) {
        this.torniquetes = new JLabel[n];
        double pos = (double)(400 / n);
        double space = (pos - 60.0D) / 2.0D;

        for(int i = 0; i < n; ++i) {
            this.torniquetes[i] = new JLabel("");
            this.torniquetes[i].setBounds(200, (int)(50.0D + (double)i * pos + space), 60, 60);
            this.torniquetes[i].setIcon(new ImageIcon(this.torniquete));
            this.contentPane.add(this.torniquetes[i]);
        }

        JLabel in = new JLabel("");
        in.setBounds(420, 80, 229, 290);
        in.setIcon(new ImageIcon(this.buho));
        this.contentPane.add(in);
    }

    public void crearPersona(Persona p) {
        Circle c = new Circle(p.getColor(), p.getNombre());
        Point pnt = this.pos(p.isEntrada());
        c.setBounds(pnt.x, pnt.y, 20, 20);
        this.contentPane.add(c);
        this.circulos.add(c);
        this.contentPane.repaint();
    }

    public void eliminarPersona(int t) {
        Rectangle r = this.torniquetes[t].getBounds();
        if (!this.circulos.isEmpty()) {
            Circle c = (Circle)this.circulos.remove(0);
            this.contentPane.remove(c);
            if (c.getBounds().y < 200) {
                c.setBounds(170, r.y, 20, 20);
            } else {
                c.setBounds(270, r.y, 20, 20);
            }

            this.contentPane.add(c);
            this.eliminando.put(t, c);
        }

        this.contentPane.repaint();
    }

    public void paso(int i) {
        Circle c = (Circle)this.eliminando.get(i);
        if (c != null) {
            this.circulos.remove(c);
            this.contentPane.remove(c);
        }

    }

    private Point pos(boolean entra) {
        int x;
        int y;
        if (entra) {
            x = 0 + (int)(Math.random() * 151.0D);
            y = 50 + (int)(Math.random() * 351.0D);
        } else {
            x = 300 + (int)(Math.random() * 51.0D);
            y = 50 + (int)(Math.random() * 351.0D);
        }

        return new Point(x, y);
    }
}
