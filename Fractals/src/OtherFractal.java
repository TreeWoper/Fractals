
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class OtherFractal extends JFrame {

    JPanel buttonGroup = new JPanel();
    JPanel bGTop = new JPanel();
    JPanel bGBottom = new JPanel();

    ArrayList<JButton> tButtons = new ArrayList<>();
    ArrayList<JButton> bButtons = new ArrayList<>();
    Color[] cList = {new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)), Color.GRAY, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    JButton f1 = new JButton("Fractal One");
    JButton f2 = new JButton("Fractal Two");
    JButton f3 = new JButton("Fractal Three");
    JButton f4 = new JButton("Fractal Four");
    JButton f5 = new JButton("Fractal Five");
    JButton f6 = new JButton("Fractal Six");
    JButton f7 = new JButton("Fractal Seven");
    JButton f8 = new JButton("Fractal Eight");

    JButton levD = new JButton("Decrease");
    JButton levI = new JButton("Increase");
    JButton levR = new JButton("Reset");
    JButton fCol = new JButton("Colorful");
    String cur = "";
    String pas = "";

    JSlider fSlider = new JSlider(JSlider.HORIZONTAL, 1, 30, 1);
    JSlider sSlider = new JSlider(JSlider.HORIZONTAL, 1, 200, 1);
    JSlider tSlider = new JSlider(JSlider.HORIZONTAL, 1, 200, 1);

    JFrame frame = new JFrame("Slide 'em 'round");

    int level = 1;
    boolean c = false;
    Color prim = Color.CYAN, seco = Color.WHITE;

    public OtherFractal() {
        setTitle("Fractals");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        pack();
        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public void initGUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("c.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(getContentPane().getX(), getContentPane().getY()), "img");
        getContentPane().setCursor(c);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setPreferredSize(new Dimension(1100, 800));
        getContentPane().setLayout(new BorderLayout());
        DrawingPanel draw = new DrawingPanel();
        getContentPane().add(draw, BorderLayout.CENTER);
        draw.setPreferredSize(new Dimension(1100, 800));
        tButtons.add(f1);
        tButtons.add(f2);
        tButtons.add(f3);
        tButtons.add(f4);
        tButtons.add(f5);
        tButtons.add(f6);
        tButtons.add(f7);
        tButtons.add(f8);
        for (JButton b : tButtons) {
            b.setBackground(Color.BLUE);
            b.setForeground(Color.CYAN);
            bGTop.add(b);
            b.setName(b.toString());
            makeListiner(b);
        }

        bButtons.add(levR);
        bButtons.add(levI);
        bButtons.add(levD);
        bButtons.add(fCol);
        for (JButton b : bButtons) {
            b.setBackground(Color.CYAN);
            b.setForeground(Color.BLUE);
            bGBottom.add(b);
        }
        levR.addActionListener((al) -> {
            level = 1;
            repaint();
        });
        levI.addActionListener((al) -> {
            level++;
            repaint();
        });
        levD.addActionListener((al) -> {
            if (level - 1 > 0) {
                level--;
            }
            repaint();
        });
        fCol.addActionListener((al) -> {
            prim = cList[(int) (Math.random() * 12)];
            seco = cList[(int) (Math.random() * 12)];
            repaint();
        });
        buttonGroup.setLayout(new BoxLayout(buttonGroup, BoxLayout.PAGE_AXIS));
        bGTop.setBackground(Color.BLUE);
        bGBottom.setBackground(Color.CYAN);
        buttonGroup.add(bGTop);
        buttonGroup.add(bGBottom);
        getContentPane().add(buttonGroup, BorderLayout.SOUTH);

        JPanel p = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), 1));
        frame.getContentPane().setBackground(Color.BLACK);
        fSlider.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
        sSlider.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
        tSlider.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
        fSlider.setBackground(Color.WHITE);
        sSlider.setBackground(Color.WHITE);
        tSlider.setBackground(Color.WHITE);
        tSlider.setPreferredSize(new Dimension(380, 25));
        fSlider.addChangeListener((pl) -> {
            repaint();
        });
        sSlider.addChangeListener((pl) -> {
            repaint();
        });
        tSlider.addChangeListener((pl) -> {
            repaint();
        });
        frame.add(fSlider);
        frame.add(sSlider);
        frame.add(tSlider);
        frame.getContentPane().add(p);
        frame.setVisible(false);
        frame.setResizable(true);
        frame.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public void makeListiner(JButton b) {
        b.addActionListener((al) -> {
            if (cur != b.getName()) {
                level = 1;
            }
            cur = b.getName();
            repaint();
        });
    }

    class DrawingPanel extends JPanel {
        // add a constructor

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.fill3DRect(0, 0, 1100, 800, rootPaneCheckingEnabled);
            // add code here and call helper methods
            if (cur.equals(f1.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalOne(level, 600, 700, 150, 150, g2);
            }
            if (cur.equals(f2.getName())) {
                frame.setVisible(false);
                drawFractalTwo(level, 200, 10, 705, g2);
            }
            if (cur.equals(f3.getName())) {
                g.setColor(Color.CYAN);
                frame.setVisible(false);
                drawFractalThree(level, 200, 200, 100, g2);
            }
            if (cur.equals(f4.getName())) {
                frame.setVisible(true);
                frame.getContentPane().setSize(new Dimension(250, 150));
                frame.pack();
                drawFractalFour(level, 550, 720, -90, g2);
            }
            if (cur.equals(f5.getName())) {
                frame.setVisible(false);
                drawFractalFive(level, 520, 370, 250, g2);
            }
        }
    }

    public void drawFractalOne(int level, int x, int y, int nx, int ny, Graphics2D g) {
        if (level == 0) {
            g.drawLine(x, y, nx, ny);
        } else {
            int ox = (x + nx) / 2;
            int oy = (y + ny) / 2;

            int inX = x + (ox - x) / 2 - (oy - y) / 2;
            int inY = y + (oy - y) / 2 + (ox - x) / 2;

            drawFractalOne(level - 1, inX, inY, x, y, g);
            drawFractalOne(level - 1, inX, inY, ox, oy, g);
            drawFractalOne(level - 1, inX, inY, nx, ny, g);
        }
    }

    public void drawFractalTwo(int level, int x, int y, int s, Graphics2D g) {
        if (level != 0) {
            g.setColor(prim);
            g.fillRect(x, y, s, s);
            g.setColor(seco);
            g.fillRect(x + (s / 3), y + (s / 3), s / 3, s / 3);
            g.setColor(prim);

            g.drawLine(x + (s / 3), y + (s / 3), x + 2 * (s / 3), y + 2 * (s / 3));
            g.drawLine(x + (s / 3), y + 2 * (s / 3), x + 2 * (s / 3), y + (s / 3));

            drawFractalTwo(level - 1, x, y, (int) (s / 3), g);
            drawFractalTwo(level - 1, x + (s / 3), y, (int) (s / 3), g);
            drawFractalTwo(level - 1, x + 2 * (s / 3), y, (int) (s / 3), g);
            drawFractalTwo(level - 1, x, y + (s / 3), (int) (s / 3), g);
            drawFractalTwo(level - 1, x, y + 2 * (s / 3), (int) (s / 3), g);
            drawFractalTwo(level - 1, x + 2 * (s / 3), y + 2 * (s / 3), (int) (s / 3), g);
            drawFractalTwo(level - 1, x + 2 * (s / 3), y + (s / 3), (int) (s / 3), g);
            drawFractalTwo(level - 1, x + (s / 3), y + 2 * (s / 3), (int) (s / 3), g);
        }
    }

    public void drawFractalThree(int level, int x, int y, int s, Graphics2D g) {
        if (level > 0) {

        }
    }

    public void drawFractalFour(int level, int x, int y, int s, Graphics2D g) {
        if (level > 0) {
            if (level % 2 == 0) {
                g.setColor(seco);
            } else {
                g.setColor(prim);
            }
            int nx = x + (int) (Math.cos(Math.toRadians(s)) * level * fSlider.getValue());
            int ny = y + (int) (Math.sin(Math.toRadians(s)) * level * fSlider.getValue());;
            g.drawLine(x, y, nx, ny);
            drawFractalFour(level - 1, nx, ny, s - sSlider.getValue(), g);
            drawFractalFour(level - 1, nx, ny, s + tSlider.getValue(), g);
        }
    }

    public void drawFractalFive(int level, int x, int y, int s, Graphics2D g) {
        if (level > 0) {
            g.setColor(seco);
            g.fillOval(x - (s / 2), y - (s / 2), s, s);
            g.setColor(prim);
            g.drawLine(x - s, y, x + s, y);
            g.drawLine(x, y - s, x, y + s);
            g.drawLine(x - 3 * (s / 4), y - 3 * (s / 4), x + 3 * (s / 4), y + 3 * (s / 4));
            g.drawLine(x + 3 * (s / 4), y - 3 * (s / 4), x - 3 * (s / 4), y + 3 * (s / 4));
            drawFractalFive(level - 1, x - s, y, s / 3, g);
            drawFractalFive(level - 1, x + s, y, s / 3, g);
            drawFractalFive(level - 1, x, y - s, s / 3, g);
            drawFractalFive(level - 1, x, y + s, s / 3, g);
            drawFractalFive(level - 1, x - 3 * (s / 4), y - 3 * (s / 4), s / 3, g);
            drawFractalFive(level - 1, x + 3 * (s / 4), y + 3 * (s / 4), s / 3, g);
            drawFractalFive(level - 1, x + 3 * (s / 4), y - 3 * (s / 4), s / 3, g);
            drawFractalFive(level - 1, x - 3 * (s / 4), y + 3 * (s / 4), s / 3, g);
        }
    }

    public static void main(String[] args) {
        new OtherFractal();
    }
}
