import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientOnce {
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

            
                System.out.println("数字を入力してください ↓");
                int n = scanner.nextInt();
                scanner.nextLine();  // 改行を消費

                // 数字をサーバに送信
                oos.writeInt(n);
                oos.flush();

                // サーバからの応答を受信
                String result = (String) ois.readObject();
                System.out.println("サーバからの応答: " + result);
            

            scanner.close();
            ois.close();
            oos.close();
            socket.close();

        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        }
    }
}