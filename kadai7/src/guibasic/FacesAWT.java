package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class FacesAWT {

    public static void main(String[] args) {
        new FacesAWT();
    }

    FacesAWT() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {

        private int faceSize = 200;
        private int spacing = 50;
        private FaceObj[] faces;

        FaceFrame() {
            Random random = new Random();
            faces = new FaceObj[9];  // 3x3の顔を一つの配列に保存

            int startX = spacing;
            int startY = spacing;

            // 3x3の顔を作成
            for (int i = 0; i < 9; i++) {
                int row = i / 3;
                int col = i % 3;
                int x = startX + col * (faceSize + spacing);
                int y = startY + row * (faceSize + spacing);
                Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                int browAngle = random.nextInt(20) - 10;
                int eyeSize = 20 + random.nextInt(30);
                int noseLength = 40;  // Fixed length for simplicity
                int mouthWidth = 60 + random.nextInt(80);
                faces[i] = new FaceObj(x, y, color, browAngle, eyeSize, noseLength, mouthWidth);
            }
        }

        public void paint(Graphics g) {
            for (FaceObj face : faces) {
                drawFace(g, face);
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
            g.drawLine(x, y, x + faceSize, y);
            g.drawLine(x, y, x, y + faceSize);
            g.drawLine(x, y + faceSize, x + faceSize, y + faceSize);
            g.drawLine(x + faceSize, y, x + faceSize, y + faceSize);
        }

        public void drawBrow(Graphics g, int x, int y, int updown) {
            int wx1 = x + faceSize * 2 / 8;
            int wx2 = x + faceSize * 5 / 8;
            int wy = y + faceSize / 5;
            g.drawLine(wx1, wy + updown, wx1 + faceSize * 1 / 8, wy);
            g.drawLine(wx2, wy, wx2 + faceSize * 1 / 8, wy + updown);
        }

        public void drawNose(Graphics g, int x, int y, int nx) {
            int xMiddle = x + faceSize / 2;
            int yMiddle = y + faceSize / 2;
            g.drawLine(xMiddle, yMiddle, xMiddle, yMiddle + nx);
        }

        public void drawEye(Graphics g, int x, int y, int r) {
            g.drawOval(x + 45, y + 65, r, r);
            g.drawOval(x + 120, y + 65, r, r);
        }

        public void drawMouth(Graphics g, int x, int y, int mx) {
            int xMiddle = x + faceSize / 2;
            int yMiddle = y + faceSize - 30;
            g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
        }
    }

    // FaceObjクラスを作成
    private class FaceObj {
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
}
