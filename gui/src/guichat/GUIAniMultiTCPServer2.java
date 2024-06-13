package guichat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GUIAniMultiTCPServer2 {

    private GUIAnimationMain animation;

    public GUIAniMultiTCPServer2(GUIAnimationMain animation) {
        this.animation = animation;
        new Thread(() -> startServer()).start();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // ポート12345でリッスン
            System.out.println("Server is listening on port 12345");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket, animation).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ServerThread extends Thread {
        private Socket socket;
        private GUIAnimationMain animation;

        public ServerThread(Socket socket, GUIAnimationMain animation) {
            this.socket = socket;
            this.animation = animation;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    switch (inputLine.toLowerCase()) {
                        case "smile":
                        case "angry":
                        case "normal":
                            animation.setBallEmotion(inputLine.toLowerCase());
                            break;
                        default:
                            out.println("Unknown emotion: " + inputLine);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
