package networking.multicastudp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class UDPMulticastClient {
    public static void main(String[] argv) throws Exception {
        // マルチキャストアドレスとポートを指定
        InetAddress multicastAddress = InetAddress.getByName("230.0.0.0");
        int port = 5100;

        MulticastSocket multicastSocket = new MulticastSocket();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            byte[] sendBuffer = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, multicastAddress, port);
            multicastSocket.send(sendPacket);

            // 終了条件
            if ("exit".equalsIgnoreCase(message)) {
                break;
            }
        }

        multicastSocket.close();
        scanner.close();
    }
}
