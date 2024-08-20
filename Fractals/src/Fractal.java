import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fractal extends JFrame {

    JPanel buttonGroup = new JPanel();
    JPanel bGTop = new JPanel();
    JPanel bGBottom = new JPanel();

    ArrayList<JButton> tButtons = new ArrayList<>();
    ArrayList<JButton> bButtons = new ArrayList<>();
    Color[] cList = {new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)), Color.GRAY, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    JButton f0 = new JButton("Fractal Zero");
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
    Color prim = Color.CYAN, seco = Color.WHITE;

    public Fractal() {
        setTitle("Fractals");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        pack();
        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public static void main(String[] args) {
        new Fractal();
    }

    public void initGUI() {
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setPreferredSize(new Dimension(1100, 800));
        getContentPane().setLayout(new BorderLayout());
        DrawingPanel draw = new DrawingPanel();
        getContentPane().add(draw, BorderLayout.CENTER);
        draw.setPreferredSize(new Dimension(1100, 800));
        tButtons.add(f0);
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
        buttonGroup.setPreferredSize(new Dimension(800, 75)); // test
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

    public void drawFractalZero(int level, int x, int y, int size, Graphics g) {
        if (level > 0) {
            Turtle t = new Turtle(x, y, -60, Color.WHITE);
            // right side
            t.moveForward(size / 2);
            int rightX = t.getX();
            int rightY = t.getY();
            t.moveForward(size / 2);
            int right = t.getX();
            int bottom = t.getY();
            g.drawLine(x, y, right, bottom);
            t.rotate(-120);
            // bottom side
            t.moveForward(size);
            t.rotate(-120);
            int left = t.getX();
            g.drawLine(right, bottom, left, bottom);
            // left side
            t.moveForward(size / 2);
            int leftX = t.getX();
            int leftY = t.getY();
            t.moveForward(size / 2);
            g.drawLine(left, bottom, x, y);
            drawFractalZero(level - 1, x, y, size / 2, g);
            drawFractalZero(level - 1, rightX, rightY, size / 2, g);
            drawFractalZero(level - 1, leftX, leftY, size / 2, g);
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
        if (level > 0) {
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

    public void drawFractalThree(int level, int x, int y, int s, Graphics g) {
        if (level > 0) {
            g.drawRect(x, y, s, s);
            drawFractalThree(level - 1, x + s - (s / 4), y + s - (s / 4), (int) (s * .75), g);
            drawFractalThree(level - 1, x + s - (s / 4), y - (s / 2), (int) (s * .75), g);
            drawFractalThree(level - 1, x - (s / 2), y + s - (s / 4), (int) (s * .75), g);
            drawFractalThree(level - 1, x - (s / 2), y - (s / 2), (int) (s * .75), g);
        }
    }

    /*
    public void drawFractalThree(int level, int x, int y, int s, Graphics g) { // Sierpinski Carpet
        if (level > 0) {
            g.fillRect(x, y, s, s);

            int newSize = s / 3;
            int space = s * 2 / 3;
            drawFractalThree(level - 1, x - space, y - space, newSize, g); // top left
            drawFractalThree(level - 1, x + s / 3, y - space, newSize, g); // top
            drawFractalThree(level - 1, x + s + s / 3, y - space, newSize, g); // top right
            drawFractalThree(level - 1, x - space, y + s / 3, newSize, g); // left
            drawFractalThree(level - 1, x + s + s / 3, y + s / 3, newSize, g); // right
            drawFractalThree(level - 1, x - space, y + s + s / 3, newSize, g); // bottom left
            drawFractalThree(level - 1, x + s / 3, y + s + s / 3, newSize, g); // bottom
            drawFractalThree(level - 1, x + s + s / 3, y + s + s / 3, newSize, g); // bottom right
        }
    }
*/

    public void drawFractalFour(int level, int x, int y, int s, Graphics2D g) {
        if (level > 0) {
            if (level % 2 == 0) {
                g.setColor(seco);
            } else {
                g.setColor(prim);
            }
            int nx = x + (int) (Math.cos(Math.toRadians(s)) * level * fSlider.getValue());
            int ny = y + (int) (Math.sin(Math.toRadians(s)) * level * fSlider.getValue());
            ;
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

    public void drawFractalSix(int level, int x, int y, int s, Graphics2D g) {
        if (level > 0) {
            // middle
            int left = x - s / 2;
            int right = x + s / 2;
            g.drawLine(left, y, right, y);
            // left
            int bottom = y + s / 2;
            int top = y - s / 2;
            g.drawLine(left, bottom, left, top);
            g.drawLine(right, bottom, right, top);
            drawFractalSix(level - 1, left, top, s / 2, g);
            drawFractalSix(level - 1, right, top, s / 2, g);
            drawFractalSix(level - 1, left, bottom, s / 2, g);
            drawFractalSix(level - 1, right, bottom, s / 2, g);
        }
    }

    public void drawFractalSeven(int level, int x, int y, int s, double thick, int facing, Graphics2D g) {
        if (level > 0) {
            Turtle t = new Turtle(x, y, facing, Color.WHITE);
            g.setStroke(new BasicStroke((int) thick));
            // draw from the bottom left
            //int[] xPoints = new int[4];
            //int[] yPoints = new int[4];
            t.moveForward(s);
            g.drawLine(x, y, t.getX(), t.getY());
            drawFractalSeven(level - 1, t.getX(), t.getY(), s * 3 / 4, thick * 0.75, facing + 10, g);
            drawFractalSeven(level - 1, t.getX(), t.getY(), s / 2, thick * 0.75, facing + 100, g);
            drawFractalSeven(level - 1, t.getX(), t.getY(), s / 2, thick * 0.75, facing - 100, g);
        }
    }

    public void drawFractalEight(int level, double x, double y, double s, int facing, Graphics2D g) {
        if (level > 0) {
            Turtle t = new Turtle((int) x, (int) y, facing, Color.WHITE);
            // draw from the bottom left
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];

            for (int i = 0; i < 4; i++) {
                xPoints[i] = t.getX();
                yPoints[i] = t.getY();
                t.moveForward((int) s);
                t.rotate(-90);
            }
            for (int i = 0; i < 3; i++) {
                g.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
            }
            g.drawLine(xPoints[3], yPoints[3], xPoints[0], yPoints[0]);

            drawFractalEight(level - 1, xPoints[0], yPoints[0], s * 0.71, facing - 45, g);
            drawFractalEight(level - 1, xPoints[1], yPoints[1], s * 0.71, facing - 45, g);
        }
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
            if (cur.equals(f0.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalZero(level, 550, 50, 720, g2);
            }
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
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalThree(level, 520, 370, 100, g2);
            }
            if (cur.equals(f4.getName())) {
                frame.setVisible(true);
                frame.getContentPane().setSize(new Dimension(250, 150));
                frame.pack();
                drawFractalFour(level, 550, 720, -90, g2);
            }
            if (cur.equals(f5.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalFive(level, 520, 370, 250, g2);
            }
            if (cur.equals(f6.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalSix(level, 550, 370, 200, g2);
            }
            if (cur.equals(f7.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalSeven(level, 550, 520, 100, 10, 90, g2);
            }
            if (cur.equals(f8.getName())) {
                g.setColor(seco);
                frame.setVisible(false);
                drawFractalEight(level, 475.00, 300.00, 200.00, 0, g2);
            }
        }
    }

    /**
     * The Turtle class describes Turtle objects which
     * represent the drawing turtle in a turtle graphics
     * application. Each turtle has a position on the
     * screen, a color and an angle indicating the direction
     * in which the turtle is traveling.  Also, a turtle's
     * pen may either be down (drawing) or up (not drawing).
     */
    public class Turtle {

        /**
         * Constant indicating a Turtle's pen is down.
         */
        public boolean PEN_DOWN = true;
        /**
         * Constant indicating that a Turtle's pen is up.
         */
        public boolean PEN_UP = false;
        private int x;
        private int y;
        private int angle;
        private Color penColor;
        private boolean penPosition;

        /**
         * Construct a new default Turtle.  The new Turtle will
         * appear at position 50,50, will have an angle of 0,
         * its pen will be black and will be up.
         */
        public Turtle() {
            x = 50;
            y = 50;
            angle = 0;
            penColor = Color.black;
            penPosition = PEN_UP;
        }

        /**
         * Construct a new Turtle with the specified parameters. The
         * new Turtle's pen will be up.
         *
         * @param initX     the x coordinate for the new Turtle.
         * @param initY     the y coordinate for the new Turtle.
         * @param initAngle the angle for the new Turtle.
         * @param initColor the color of the new Turtle's pen.
         */
        public Turtle(int initX, int initY, int initAngle, Color initColor) {
            x = initX;
            y = initY;
            angle = initAngle;
            penColor = initColor;
            penPosition = PEN_UP;
        }

        /**
         * Move this Turtle forward by the specified number of
         * screen pixels.
         *
         * @param pixels the number of screen pixes by which
         *               to move this Turtle forward.
         */
        public void moveForward(int pixels) {
            double radAngle = Math.toRadians(angle);
            x = x + (int) Math.round(Math.cos(radAngle) * pixels);
            y = y - (int) Math.round(Math.sin(radAngle) * pixels);
        }

        /**
         * Rotate this Turtle counter-clockwise by the specified
         * number of degrees.
         *
         * @param degrees the number of degrees by which to rotate
         *                this Turtle.
         */
        public void rotate(int degrees) {
            int newAngle = angle + degrees;
            angle = newAngle % 360;
        }

        /**
         * Put this Turtle's pen down. When this Turtle's pen is
         * down it will draw a line in its color as it moves
         * forward.
         */
        public void putPenDown() {
            penPosition = PEN_DOWN;
        }

        /**
         * Pick this Turtle's pen up. When this Turtle's pen is
         * up it will not draw a line as it moves forward.
         */
        public void pickPenUp() {
            penPosition = PEN_UP;
        }

        /**
         * Ask this Turtle if it's pen is up or down. The value
         * returned will be either PEN_UP or PEN_DOWN.
         *
         * @return PEN_DOWN if this Turtle's pen is up or
         * PEN_UP if this Turtle's pen is up.
         */
        public boolean getPenPosition() {
            return penPosition;
        }

        /**
         * Get the x coordinate of this Turtle.
         *
         * @return the x coordinate of this Turtle.
         */
        public int getX() {
            return x;
        }

        /**
         * Get the y coordinate of this Turtle.
         *
         * @return the y coordinate of this Turtle.
         */
        public int getY() {
            return y;
        }

        /**
         * Get the color of this Turtle's pen. The color is
         * returned as a reference to a Color object.
         *
         * @return a reference to a Color object representing
         * the Color of this Turtle's pen.
         */
        public Color getColor() {
            return penColor;
        }

        /**
         * Get the the angle to which this Turtle is turned.
         * The angle of the Turtle is measured counter-clockwise
         * from horizontal.
         *
         * @return the angle to which this Turtle is turned.
         */
        public int getAngle() {
            return angle;
        }
    }
}
