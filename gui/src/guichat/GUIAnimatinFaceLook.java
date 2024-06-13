package guichat;

import java.awt.Color;
import java.awt.Graphics;

class GUIAnimatinFaceLook { // 顔のオブジェクト

    int h = 100;
    int w = 100;

    int xStart = 0;
    int yStart = 0;

    public void setXY(int x, int y) {
        this.xStart = x;
        this.yStart = y;
    }

    public void setSize(int h, int w) {
        this.h = h;
        this.w = w;
    }

    public GUIAnimatinFaceLook() {
    }

    void makeFace(Graphics g, String emotion) {
        makeEyes(g, w / 5);
        makeNose(g, h / 5);
        makeMouth(g, w / 2);

        if (emotion.equals("smile")) {
            makeEyebrows(g, -10); // 眉毛を下げる
        } else if (emotion.equals("angry")) {
            makeEyebrows(g, 10); // 眉毛を上げる
        } else {
            makeEyebrows(g, 0); // 普通の眉毛
        }
    }

    void makeEyes(Graphics g, int eyeSize) {
        g.setColor(Color.blue);
        g.fillArc(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize, 0, 300);
        g.setColor(Color.black);
        g.drawOval(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);
        g.drawOval(xStart + (h * 4 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);
    }

    void makeNose(Graphics g, int noseSize) {
        g.drawLine(xStart + (h * 1 / 2), yStart + (w * 2 / 4), xStart + (h * 1 / 2), yStart + (w * 2 / 4) + noseSize);
    }

    void makeMouth(Graphics g, int mouthSize) {
        int xMiddle = xStart + h / 2;
        int yMiddle = yStart + 3 * w / 4;
        g.drawLine(xMiddle - mouthSize / 2, yMiddle, xMiddle + mouthSize / 2, yMiddle);
    }

    void makeEyebrows(Graphics g, int angle) {
        int eyebrowX1 = xStart + w / 4;
        int eyebrowY1 = yStart + h / 4;
        int eyebrowX2 = eyebrowX1 + w / 4;
        int eyebrowY2 = eyebrowY1 + angle;

        g.drawLine(eyebrowX1, eyebrowY1, eyebrowX2, eyebrowY2);

        eyebrowX1 = xStart + 3 * w / 4;
        eyebrowY1 = yStart + h / 4;
        eyebrowX2 = eyebrowX1 - w / 4;
        eyebrowY2 = eyebrowY1 + angle;

        g.drawLine(eyebrowX1, eyebrowY1, eyebrowX2, eyebrowY2);
    }
}
