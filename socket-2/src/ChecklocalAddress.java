import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChecklocalAddress {
    public static void main(String[] args){
        try{
    InetAddress addr = InetAddress.getByName("133.14.216.72");

    System.out.println("Host name is :" + addr.getHostName());

    System.out.println("IP address is :" + addr.getHostAddress());
    }catch (UnknownHostException e) {
        System.out.println("Could not find the host: " + e.getMessage());
        e.printStackTrace();
    }}}
