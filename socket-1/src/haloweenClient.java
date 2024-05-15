import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.Scanner;

public class haloweenClient {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.nextLine();  // 改行を消費
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println("メッセージを入力してください(例:　トリックオアトリート！) ↓");
                String message = scanner.next();

                if (message.equalsIgnoreCase("quit") || message.equalsIgnoreCase("exit")) {
                    System.out.println("終了コマンドが入力されたため、接続を終了します。");
                    break;
                }
                System.out.println("どんなお菓子がほしいですか？(例: チョコ) ↓");
                String content = scanner.next();

                if (content.equalsIgnoreCase("quit") || content.equalsIgnoreCase("exit")) {
                    System.out.println("終了コマンドが入力されたため、接続を終了します。");
                    break;
                }

                haloween present = new haloween();
                present.setMessage(message);
                present.setContent(content);

                oos.writeObject(present);
                oos.flush();

                haloween okaeshiPresent = (haloween) ois.readObject();
                String replayMsg = okaeshiPresent.getMessage();
                System.out.println("サーバからのメッセージは: " + replayMsg);
                String replayContent = okaeshiPresent.getContent();
                System.out.println(replayContent + "をもらいました！");
            }

            scanner.close();
            ois.close();
            oos.close();
            socket.close();

        } catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}