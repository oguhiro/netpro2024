package guichat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SrvWorkerThread implements Runnable {
    private Socket socket;
    private GUIAnimationMain animationMain;

    public SrvWorkerThread(Socket socket, GUIAnimationMain animationMain) {
        this.socket = socket;
        this.animationMain = animationMain;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (inputLine.startsWith("emotion:")) {
                    String emotion = inputLine.substring(8).trim();
                    animationMain.setFaceEmotion(0, emotion);  // Assuming we only handle the first face
                }
                out.println("OK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
