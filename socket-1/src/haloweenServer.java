import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class haloweenServer {

    private static final int times = 2;

    private static String serverProcess(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎁");
        for (int i = 0; i < times; i++) {
            sb.append(content);
        }
        sb.append("🎁");
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();

            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port);

            while (true) {
                Socket socket = server.accept();
                System.out.println("接続しました。相手の入力を待っています......");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    try {
                        haloween present = (haloween) ois.readObject();
                        String msgPresent = present.getMessage();
                        String presentFromClient = present.getContent();

                        System.out.println("メッセージは: " + msgPresent);
                        System.out.println("お菓子は: " + presentFromClient);

                        if (presentFromClient.equalsIgnoreCase("quit") || presentFromClient.equalsIgnoreCase("exit")) {
                            System.out.println("クライアントから終了コマンドを受信したため、接続を終了します。");
                            break;
                        }

                        haloween response = new haloween();
                        response.setMessage("サーバーです。トリックオアトリート！！\n" + presentFromClient + "ありがとう。\nお菓子のお返しは" + times + "倍" + "です");
                        response.setContent(serverProcess(presentFromClient));

                        oos.writeObject(response);
                        oos.flush();
                    } catch (Exception e) {
                        System.out.println("クライアントの接続が切れました。");
                        break;
                    }
                }

                ois.close();
                oos.close();
                socket.close();
                System.out.println("接続を終了しました。次の接続を待ちます。");
            }
        } catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、またはポートが使用中です。");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します。");
            throw new RuntimeException(e);
        }
    }
}