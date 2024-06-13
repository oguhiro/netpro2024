package guibasic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingAnimationFaceObj extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private Ellipse2D.Float ellipse = new Ellipse2D.Float();
    final int framespeed = 50;
    final private double MAXCounter = 500;
    private boolean isResetProcess = true;
    private double counter;
    private Timer timer;
    private int ballnum = 2;
    private BallRim[] myBalls = new BallRim[ballnum];

    public static void main(String[] args) {
        /* おまじない：開始 */
        SwingAnimationFaceObj animation = new SwingAnimationFaceObj();
        JFrame frame = new JFrame("SwingFaceObject");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(animation);
        frame.setSize(550, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        /* おまじない：終了 */
    }

    public SwingAnimationFaceObj() {
        timer = new Timer(framespeed, this);
        timer.setInitialDelay(500);
        timer.start();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        /* おまじない：開始 */
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
        Dimension windowSize = getSize();
        if (isResetProcess) {
            initProcess(windowSize.width, windowSize.height);
            isResetProcess = false;
        }
        this.doNextStep(windowSize.width, windowSize.height);
        paintProcess(windowSize.width, windowSize.height, g2);
        /* おまじない：終了 */
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void setEllipseSize(double size, int w, int h) {
        counter = size;
        ellipse.setFrame(10, 10, size, size);
    }

    public void initProcess(int w, int h) {
        for (int i = 0; i < myBalls.length; i++) {
            myBalls[i] = new BallRim(w, h);
        }
        setEllipseSize(1, w, h);
    }

    public void doNextStep(int w, int h) {
        counter++;
        if (counter > MAXCounter) {
            initProcess(w, h);
        } else {
            for (int i = 0; i < myBalls.length; i++) {
                myBalls[i].move();
            }
            ellipse.setFrame(ellipse.getX(), ellipse.getY(), counter, counter);
        }
    }

    public void paintProcess(int w, int h, Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.draw(ellipse);
        g2.drawString(counter + "Step経過", 10, 10);
        for (int i = 0; i < myBalls.length; i++) {
            myBalls[i].draw(g2);
        }
    }

    class BallRim {
        FaceObj fobj;
        String str = "";
        Random rdn;
        int w = 500;
        int h = 500;
        int x;
        int y;
        int radius; // 半径
        Color basicColor = Color.gray;
        double xDir = -1; // 1:+方向 -1: -方向
        double yDir = 1;
        private int strCounter;
        private boolean isStopped = false; // 停止フラグ
        private boolean showMessage = false; // メッセージ表示フラグ

        BallRim(int w, int h) {
            rdn = new Random();
            xDir = rdn.nextDouble() * 2 - 1;
            yDir = rdn.nextDouble() * 2 - 1;
            this.w = w;
            this.h = h;
            setPosition(rdn.nextInt(w), rdn.nextInt(h));
            setRadius(rdn.nextInt(30) + 30); // 30-60のサイズの顔の輪郭
            Color bcolor = new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255));
            setBasicColor(bcolor);
            int browAngle = rdn.nextInt(20) - 10;
            int eyeSize = 20 + rdn.nextInt(30);
            int noseLength = 40; // Fixed length for simplicity
            int mouthWidth = 60 + rdn.nextInt(80);
            fobj = new FaceObj(x, y, bcolor, browAngle, eyeSize, noseLength, mouthWidth);
        }

        void setBasicColor(Color bcolor) {
            this.basicColor = bcolor;
        }

        public void setCollisionText(String str, int strCounter) {
            this.str = str;
            this.strCounter = strCounter;
        }

        void move() {
            if (isStopped) return; // 停止している場合は何もしない
            if ((xDir > 0) && (x + this.radius * 2 >= w)) {
                xDir = -1 * xDir;
                setCollisionText("右が痛いわぁ", 3);
            } else if ((xDir < 0) && (x <= 0)) {
                xDir = -1 * xDir;
                setCollisionText("左の頬がめちゃ痛いわ！", 6);
            } else if (xDir > 0) {
                x = x + 10;
            } else {
                x = x - 10;
            }

            if ((yDir > 0) && (y + this.radius * 2 >= h)) {
                yDir = -1 * yDir;
                setCollisionText("顎が痛いわぁ", 3);
            }
            if ((yDir < 0) && (y <= 0)) {
                yDir = -1 * yDir;
                setCollisionText("頭がめちゃ痛いわ！", 6);
            }

            if (yDir > 0) {
                y = y + 10;
            } else {
                y = y - 10;
            }

            // Update FaceObj position
            fobj.x = x;
            fobj.y = y;
        }

        void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setRadius(int r) {
            this.radius = r;
        }

        void draw(Graphics g) {
            drawFace(g, fobj);
            if (strCounter > 0) {
                g.drawString(str, x, y);
                strCounter--;
            } else {
                str = "";
            }
            if (showMessage) {
                g.setColor(Color.BLACK);
                g.drawString("ありがとう", x, y - 10);
            }
        }

        boolean isInside(int mx, int my) {
            int centerX = x + radius;
            int centerY = y + radius;
            int distX = Math.abs(mx - centerX);
            int distY = Math.abs(my - centerY);
            return distX <= radius && distY <= radius;
        }

        void setStopped(boolean stopped) {
            this.isStopped = stopped;
        }

        void setShowMessage(boolean showMessage) {
            this.showMessage = showMessage;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        for (BallRim ball : myBalls) {
            if (ball.isInside(mx, my)) {
                ball.setStopped(true);
                ball.setShowMessage(true);
            } else {
                ball.setStopped(false);
                ball.setShowMessage(false);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // FaceObjクラスを統合
    class FaceObj {
        int x, y;
        Color color;
        int browAngle;
        int eyeSize;
        int noseLength;
        int mouthWidth;

        FaceObj(int x, int y, Color color, int browAngle, int eyeSize, int noseLength, int mouthWidth) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.browAngle = browAngle;
            this.eyeSize = eyeSize;
            this.noseLength = noseLength;
            this.mouthWidth = mouthWidth;
        }
    }

    public void drawFace(Graphics g, FaceObj face) {
        g.setColor(face.color);
        drawRim(g, face.x, face.y);
        drawBrow(g, face.x, face.y, face.browAngle);
        drawEye(g, face.x, face.y, face.eyeSize);
        drawNose(g, face.x, face.y, face.noseLength);
        drawMouth(g, face.x, face.y, face.mouthWidth);
    }

    public void drawRim(Graphics g, int x, int y) {
        int faceSize = 100;
        g.drawLine(x, y, x + faceSize, y);
        g.drawLine(x, y, x, y + faceSize);
        g.drawLine(x, y + faceSize, x + faceSize, y + faceSize);
        g.drawLine(x + faceSize, y, x + faceSize, y + faceSize);
    }

    public void drawBrow(Graphics g, int x, int y, int updown) {
        int faceSize = 100;
        int wx1 = x + faceSize * 2 / 8;
        int wx2 = x + faceSize * 5 / 8;
        int wy = y + faceSize / 5;
        g.drawLine(wx1, wy + updown, wx1 + faceSize * 1 / 8, wy);
        g.drawLine(wx2, wy, wx2 + faceSize * 1 / 8, wy + updown);
    }

    public void drawNose(Graphics g, int x, int y, int nx) {
        int faceSize = 100;
        int xMiddle = x + faceSize / 2;
        int yMiddle = y + faceSize / 2;
        g.drawLine(xMiddle, yMiddle, xMiddle, yMiddle + nx);
    }

    public void drawEye(Graphics g, int x, int y, int r) {
        g.drawOval(x + 20, y + 30, r, r);
        g.drawOval(x + 60, y + 30, r, r);
    }

    public void drawMouth(Graphics g, int x, int y, int mx) {
        int faceSize = 100;
        int xMiddle = x + faceSize / 2;
        int yMiddle = y + faceSize - 20;
        g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
    }
}
