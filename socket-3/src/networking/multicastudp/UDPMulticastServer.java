package networking.multicastudp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastServer {
    public static void main(String[] argv) throws Exception {
        // マルチキャストアドレスとポートを指定
        InetAddress multicastAddress = InetAddress.getByName("230.0.0.0");
        int port = 5100;

        // マルチキャストソケットを作成して指定されたポートをバインド
        MulticastSocket multicastSocket = new MulticastSocket(port);
        multicastSocket.joinGroup(multicastAddress);

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        while (true) {
            // マルチキャストパケットを受信
            multicastSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message: " + message);

            // 終了条件
            if ("exit".equalsIgnoreCase(message.trim())) {
                break;
            }
        }

        multicastSocket.leaveGroup(multicastAddress);
        multicastSocket.close();
    }
}
