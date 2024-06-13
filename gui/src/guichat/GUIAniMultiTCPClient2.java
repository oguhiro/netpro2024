package guichat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GUIAniMultiTCPClient2 {

    private GUIAnimationMain animation;

    public GUIAniMultiTCPClient2(GUIAnimationMain animation) {
        this.animation = animation;
        startServer();
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // 12345ポートで接続を待ちます

            while (true) {
                Socket clientSocket = serverSocket.accept(); // クライアントからの接続を待ちます
                System.out.println("クライアントが接続しました");

                // クライアントからのデータを受け取るためのReaderを作成します
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("クライアントからのメッセージ: " + line);
                    // 受け取ったメッセージに応じた処理を行います
                    // ここで必要な操作を実装してください
                }

                clientSocket.close(); // クライアントとの接続を閉じます
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
