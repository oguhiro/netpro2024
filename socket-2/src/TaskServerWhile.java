import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TaskServerWhile {

    // 素数かどうかを判定する関数
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    // 入力された数値以下の最大素数を返す関数
    public static int findLargestPrime(int n) {
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // 素数が見つからない場合
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

                try {
                    while (true) {
                        // クライアントから数字を受信
                        int receivedNumber = ois.readInt();
                        System.out.println("受信した数字: " + receivedNumber);

                        // 入力された数値以下の最大素数を計算
                        int largestPrime = findLargestPrime(receivedNumber);
                        String result;
                        if (largestPrime == -1) {
                            result = "入力された数値以下に素数は存在しません。";
                        } else {
                            result = receivedNumber + " 以下の最大素数は " + largestPrime + " です。";
                        }

                        // 判定結果をクライアントに送信
                        oos.writeObject(result);
                        oos.flush();
                    }
                } catch (Exception e) {
                    System.out.println("クライアントの接続が切れました");
                } finally {
                    ois.close();
                    oos.close();
                    socket.close();
                }

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